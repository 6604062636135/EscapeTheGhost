/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escapetheghost;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.Timer;
import java.util.ArrayList ;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;


public class DrawArea extends JPanel implements ActionListener {

    private Image imgActor = ImageRS.IMG_ACTOR;
    private Image imgNghost = ImageRS.IMG_NGHOST;
    private Image imgKraseu = ImageRS.IMG_KRASEU;
    private Image imgPred = ImageRS.IMG_PRED;
    private Image KYimg = ImageRS.IMG_KY;
    private Image KHimg = ImageRS.IMG_KH;
    private Image Crimg = ImageRS.IMG_CR;
    private Image imgActor2 = ImageRS.IMG_ACTORRUN ;
    
    private Player player;
    private JButton retryButton, backButton;
    private bgAnimation bgSpeed;
    private Title t ;
    private long Starttime ;
    private int Gspeed = 8 ;
    private int score = 0 ;
    private int gameUpdate = 0;
    private int TimeLimit = 60*60 ;
    private ArrayList<Ghost> ghosts;
    private ArrayList<Ball> balls;
    private Timer timer ;
    private Timer scoreTimer;
    private Timer timeTimer;
    private Random random = new Random();
    private Color scoreColor = Color.ORANGE; 
    private Color timeColor = Color.ORANGE;
    private boolean gameStarted = false;
   
    public DrawArea(bgAnimation Bg,Title t) {
        this.t = t ;
        this.player = new Player(imgActor,100, 410, 180, 180);
        this.Starttime = System.currentTimeMillis() ;
        this.ghosts = new ArrayList<>();
        this.balls = new ArrayList<>();
        this.bgSpeed = Bg;
        initTimer();
        setFocusable(true); 
        this.retryButton = new JButton("Retry");
        Font font = new Font("Courier", Font.BOLD, 30);
        retryButton.setFont(font);
        retryButton.setForeground(Color.BLACK);
        retryButton.setBackground(Color.red);
        retryButton.setVisible(false); 
        retryButton.addActionListener(e -> {
            resetGame(); 
        });
        this.setLayout(null);
        this.add(retryButton);
        this.backButton = new JButton("Back to Title");
        Font font2 = new Font("Courier", Font.BOLD, 25);
        backButton.setFont(font2);
        backButton.setForeground(Color.BLACK);
        backButton.setBackground(Color.blue);
        backButton.setVisible(false); 
        backButton.addActionListener(e -> {
            BacktoTitle(); 
        });
        this.setLayout(null);
        this.add(backButton);

        addKeyListener(new KeyAdapter(){
              @Override 
              public void keyPressed(KeyEvent e){

                  if(e.getKeyCode()==KeyEvent.VK_W){
                      player.moving();   
                  }
                  else if(e.getKeyCode()==KeyEvent.VK_S){
                     player.crouch();  
              }  
              }
               @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_S) {
                        player.standup(); 
                    }
                }
    });
    }
    
  private void initTimer() {
    timer = new Timer(1000/60, e -> {
        spawnGhosts(); 
        spawnBalls(); 
        updateGame();
        repaint();
    });
    timer.start(); 
}
 private void spawnGhosts() {
    if (!gameStarted || !ghosts.isEmpty()) {
        return;
    }
    int ghostType;
    int NyPosition = 453;
    int yPosition = 0 ;
    int Gwidth = 0, Gheight = 0;
    Image ghostImg = null;
    Ghost newGhost;

    if (bgSpeed.getNImage().equals("bg")) {
        ghostType = random.nextInt(3); 
    } else {
        ghostType = random.nextInt(7); 
    }

    switch (ghostType) {
        case 0:
            ghostImg = imgNghost;
            yPosition = NyPosition;
            Gwidth = 180;
            Gheight = 180;
            break;
        case 1:
            ghostImg = imgKraseu;
            yPosition = NyPosition/2 + 60;
            Gwidth = 160;
            Gheight = 200;
            break;
        case 2:
            ghostImg = imgPred;
            yPosition = 355;
            Gwidth = 230;
            Gheight = 250;
            break;
        case 3:
            ghostImg = KHimg;
            yPosition = random.nextBoolean() ? 400 : NyPosition/2 + 60 ;
            Gwidth = 150;
            Gheight = 180;
            break;
        case 4:
            ghostImg = KYimg;
            yPosition = 425;
            Gwidth = 180;
            Gheight = 180;
            break;
        case 5:
            ghostImg = Crimg;
            yPosition = random.nextBoolean() ? 450 : 360 ;
            Gwidth = 100;
            Gheight = 100;
            break;
    }

    if (ghostImg != null) {
        switch (ghostType) {
            case 5:
                newGhost = new KYSpeed(ghostImg, getWidth(), yPosition, Gwidth, Gheight);
                break;
            case 6:
                newGhost = new Crow(ghostImg, getWidth(), yPosition, Gwidth, Gheight);
                break;
            default:
                newGhost = new Ghost(ghostImg, getWidth(), yPosition, Gwidth, Gheight);
                break;
        }
        ghosts.add(newGhost);
    }
  }


  private void spawnBalls() {
    if (!gameStarted) {
        return;
    }
    int yPosBall = random.nextInt(200,400 );
    if (balls.size() < 2) {
        int xPosBall = random.nextInt(300, 1200);
        balls.add(new Ball(xPosBall, yPosBall));
       }
  }

    private void updateGame() {
       player.setImage(imgActor2);
       TimeLimit-- ;
       if (!gameStarted) { 
        return; 
       }
        player.update();
        repaint();
        long currentTime = System.currentTimeMillis();
        if((currentTime-Starttime)>=10000){
            Gspeed += 1 ;
            Starttime = currentTime ;
        }
        bgSpeed.update();
         for (int i = balls.size() - 1; i >= 0; i--) { 
             Ball ball = balls.get(i);
             ball.update(Gspeed);
             ball.moving();
             
             if(ball.isOffScreen()){
                balls.remove(i);
            }   
         }
         
        for (int i = ghosts.size() - 1; i >= 0; i--) {
            Ghost ghost = ghosts.get(i);
            ghost.update(Gspeed);
            bgSpeed.setSpeed(Gspeed);
            ghost.moving();
            
            if(ghost.isOffScreen()) {
                ghosts.remove(i); 
            } 
            
        }
        
        Collision();
        if(score==8000){
            gameUpdate = 2 ;
        }
        if(TimeLimit/60<=0){
            gameUpdate = 1 ;
            player.isDeath(1);
            
        } 
    }
    
    private void Collision(){
        int paddingX = 56 ;
        int paddingY = player.isCrouching() ? 60 : -30;
        
        Iterator<Ball> ballIterator = balls.iterator();
        while (ballIterator.hasNext()) {
        Ball ball = ballIterator.next();
        if (player.getX() < ball.getX() + ball.getW() &&
            player.getX() + player.getMW() > ball.getX() &&
            player.getY() < ball.getY() + ball.getH() &&
            player.getY() + player.getMH() > ball.getY()) {
            ballIterator.remove();
            score += 100;
        }
        }
           
        Iterator<Ghost> ghostIterator = ghosts.iterator();
        while (ghostIterator.hasNext()) {
        Ghost ghost = ghostIterator.next();

            if (player.getX() + paddingX < ghost.getX() + ghost.getMW() && 
                player.getX() - paddingX + player.getMW() > ghost.getX() &&
                player.getY() + paddingY < ghost.getY() + ghost.getMH() &&
                player.getY() - paddingY + player.getMH() > ghost.getY()) { 
              if (ghost instanceof KYSpeed) {
                    ghostIterator.remove();
                    score = score - 200 ;
                    scoreColor = Color.WHITE; 
                    ColorReset(1);
             } else if (ghost instanceof Crow) {
                    ghostIterator.remove();
                    TimeLimit = TimeLimit - 5*60;
                    timeColor = Color.WHITE; 
                    ColorReset(2);
             } else if (ghost instanceof Ghost) {
                    player.isDeath(1);
                    gameUpdate = 1;
                    break;
            }
            }
    }
    }


private void ColorReset(int a) {
    if (a == 1) { 
        if (scoreTimer != null && scoreTimer.isRunning()) {
            scoreTimer.stop(); 
        }
        scoreTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scoreColor = Color.ORANGE; 
                scoreTimer.stop();
            }
        });
        scoreTimer.start(); 
    } else if (a == 2) { 
        if (timeTimer != null && timeTimer.isRunning()) {
            timeTimer.stop(); 
        }
        timeTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeColor = Color.ORANGE; 
                timeTimer.stop();
            }
        });
        timeTimer.start(); 
    }
}


    public void startGame() {    
        gameStarted = true ;
        timer.start(); 
        repaint(); 
    }
    
    public void resetGame() {
    ghosts.clear(); 
    balls.clear();
    player.isDeath(0);
    player.setX(100);
    player.setY(410);
    score = 0;
    Gspeed = 8;
    TimeLimit = 60*60 ;
    Starttime = System.currentTimeMillis();
    gameUpdate = 0;
    timer.start();
    requestFocusInWindow();       
    }
    
    public void BacktoTitle(){
    resetGame(); 
    repaint();    
    t.showTitleScreen();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);         
        if(gameStarted){
            bgSpeed.draw(g);
            player.draw(g);
            for (Ball ball : balls) {
            ball.draw(g);
            }
            for (Ghost ghost : ghosts) {
            ghost.draw(g);
            }
            g.setColor(Color.orange);
            g.setFont(new Font("Courier", Font.PLAIN, 32));
            g.setColor(scoreColor); 
            g.drawString("SCORE : " + String.valueOf(score), getWidth() - 250, 35);
            g.setColor(timeColor);
            g.setFont(new Font("Courier", Font.PLAIN, 32));
            g.drawString(String.valueOf(TimeLimit/60), 30, 35);
        
                 if(gameUpdate>0){
                     if(gameUpdate == 1){
                        g.setColor(Color.orange);
                        g.setFont(new Font("Courier",Font.PLAIN,70));
                        g.drawString("GAME OVER",getWidth()/2-190, getHeight()/2-80);
                       }
                      else{
                        g.setColor(Color.GREEN);
                        g.setFont(new Font("Courier",Font.PLAIN,100));
                        g.drawString("YOU WIN !",getWidth()/2-220, getHeight()/2-80); 
                      }
                   timer.stop();
                   retryButton.setBounds(getWidth() / 2 - 50, getHeight() / 2, 150, 50);
                   retryButton.setVisible(true);
                   backButton.setBounds(getWidth() / 2 - 73, getHeight() / 2 + 60, 200, 50); 
                   backButton.setVisible(true); 

                 }
                else{
                    retryButton.setVisible(false);
                    backButton.setVisible(false); 
                    } 
            }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    player.update();
    repaint(); 
    }
}




    



