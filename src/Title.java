/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escapetheghost;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;



    public class Title extends JFrame {
    private URL imgStartURL = getClass().getResource("start.png");
    private URL imgTitleURL = getClass().getResource("title.png");

    private Image imgBg = ImageRS.IMG_BG;  
    private Image imgBg2 = ImageRS.IMG_BG2;
    private Image imgActor = ImageRS.IMG_ACTOR;
    private Image imgGhost1 = ImageRS.IMG_NGHOST;
    private Image imgGhost2 = ImageRS.IMG_KRASEU;
    private Image imgGhost3 = ImageRS.IMG_PRED;
    private Image imgStart = new ImageIcon(imgStartURL).getImage();
    private Image imgTitle = new ImageIcon(imgTitleURL).getImage();

    private DrawArea d;
    private TitleDraw Td = new TitleDraw();
    private bgAnimation Bg;

    private JPanel p2;
    private JPanel buttonPanel ;
    private JButton Buttonscene1 = new JButton("Twisted Forest");
    private JButton Buttonscene2 = new JButton("Crimson Woods");
        
        
    public Title() {

        Bg = new bgAnimation(1366,710,imgBg,"bg.png");
        d = new DrawArea(Bg,this);
        setLayout(new BorderLayout());
        add(Td, BorderLayout.CENTER); 

        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); 
        JButton startButton = new JButton(new ImageIcon(imgStart));
        startButton.setPreferredSize(new Dimension(280, 160));  
        Image resizedImage = imgStart.getScaledInstance(280, 160, Image.SCALE_SMOOTH); 
        startButton.setIcon(new ImageIcon(resizedImage)); 

      
        startButton.setContentAreaFilled(false); //remove fill
        startButton.setBorderPainted(false); //remove line
        startButton.setMargin(new Insets(0, 0, 0, 0)); // Remove extra space around the image
        buttonPanel.add(startButton); 

        Td.setLayout(null);
        Td.add(buttonPanel);
        buttonPanel.setBounds((1366 - 280) / 2, (786 - 160) / 2, 280, 160);
        
        p2 = new JPanel(new GridLayout(2,1,0,15));
        Font font = new Font("Courier", Font.BOLD, 20);
        Buttonscene1.setFont(font);
        Buttonscene1.setForeground(Color.BLACK);
        Buttonscene1.setBackground(Color.decode("#CC99FF"));

        Buttonscene2.setFont(font);
        Buttonscene2.setForeground(Color.BLACK);
        Buttonscene2.setBackground(Color.decode("#CC99FF"));
        
        p2.add(Buttonscene1);
        p2.add(Buttonscene2);
        p2.setPreferredSize(new Dimension(200, 300));
        p2.setVisible(false);
        Td.add(p2);
        p2.setBounds((1366 - 200) / 2, (786 - 150) / 2, 200, 150); 

        Buttonscene1.addActionListener(new ButtonListener());
        Buttonscene2.addActionListener(new ButtonListener());
        startButton.addActionListener(e -> {
            p2.setOpaque(false);
            p2.setVisible(true);
            Td.add(p2,BorderLayout.CENTER);
            buttonPanel.setVisible(false);
            Td.repaint();
   
        });
        
    }
   
    class ButtonListener implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
             if(e.getActionCommand().equals("Twisted Forest")){
                   Bg.setNImage("bg");
                   Bg.setImageBg(imgBg);
                   
             }
             else if(e.getActionCommand().equals("Crimson Woods")){
                   Bg.setNImage("bg2");
                   Bg.setImageBg(imgBg2);
             }
             
             Td.getshowTitle(true);
             p2.setVisible(false);
             d.setVisible(true);
             d.startGame();
             add(d);
             d.requestFocusInWindow();  
             
                
            
        }
 
    }
    class TitleDraw extends JPanel{
    private boolean showTitle = false ;
    public void getshowTitle(boolean s){
        this.showTitle = s ;
    }
      
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);   
         g.drawImage(imgBg, 0, 0, getWidth(), getHeight(), this);
        if(!showTitle){
            g.drawImage(imgTitle, (getWidth() / 2) - 330, (getHeight() / 2) - 230, 650, 130, this);
            g.drawImage(imgGhost1, getWidth() - 400, 453, 180, 180, this); // normal g
            g.drawImage(imgGhost2, getWidth() - 300, 265, 160, 200, this); // kraseu
            g.drawImage(imgGhost3, getWidth() - 200, 355, 230, 250, this); // pred
            g.drawImage(imgActor, 100, 410, 180, 180, this); // Draw actor
        }
    }
    }
    
    public void showTitleScreen() {
    d.setVisible(false);
    remove(d);
    Td.setVisible(true); 
    Td.getshowTitle(false);
    buttonPanel.setVisible(true);
    p2.setVisible(false); 
    Bg.setNImage("bg"); 
    Bg.setImageBg(imgBg); 
    }
    public static void main(String[] args) {
        Title f = new Title();
        f.setSize(1366,750);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
   
}

