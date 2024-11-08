/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escapetheghost;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */

public class Player extends Character {
   
    private int jumpSpeed = 25;
    private int velocity = 0; 
    private boolean isJumping = false;
    private int jumpCount = 0; 
    private int groundLevel = 410; 
    private boolean isCrouching = false ;
    private boolean Death = false ;
   
    private URL imgcrouchURL = getClass().getResource("crouch.png");
    private Image imgc = new ImageIcon(imgcrouchURL).getImage();
    private URL imgDeathURL = getClass().getResource("death.png");
    private Image imgd = new ImageIcon(imgDeathURL).getImage();
    public Player(Image img,int x,int y,int Width,int Height){
         this.img = img ;
         this.x = x ;
         this.y = y ;
         this.Width = Width ;
         this.Height = Height ;
     }
   
     public void crouch() {
        isCrouching = true;
        
    }
     public void standup(){
         isCrouching = false;
        
     }

     public boolean isCrouching() {
     return this.isCrouching;
     }
      public void setImg(Image img){
            this.img = img ;
      }
    
    @Override
    public void moving() {
        if (jumpCount < 2) { 
            velocity = -jumpSpeed; 
            isJumping = true;
            jumpCount++; 
        }
    }
   
    public void update() {
        if (isJumping) {
            this.y += velocity;
            velocity += 2; 
            if (this.y >= groundLevel) { 
                this.y = groundLevel;
                isJumping = false; 
                jumpCount = 0; 
                velocity = 0; 
                
            }
        }
    }
    
    //public void increaseJumpSpeed(int increment) {
        //this. jumpSpeed += increment; 
    //}

    public void isDeath(int d) {
       if(d==1){
           Death = true ;
       }
       else{
           Death = false ;
       }
    }

    @Override
    public void draw(Graphics g) {
        if(isCrouching && !Death){
            g.drawImage(imgc, x, y+65,Width-5 ,Height-50,null ); 
        }
        else if(Death == true){
            g.drawImage(imgd, x, y, Width, Height, null);
        }
        else{
        g.drawImage(img, x, y, Width, Height, null);
        
        }
    }
  
}
