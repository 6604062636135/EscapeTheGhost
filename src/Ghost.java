/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escapetheghost;

import java.awt.Image;

public class Ghost extends Character {
    private int Gspeed = 0;
    public Ghost(Image img,int x,int y,int Width,int Height){
         this.img = img ;
         this.x = x ;
         this.y = y ;
         this.Width = Width ;
         this.Height = Height ;
     }

    @Override
    public void moving() {
        this.x -= this.Gspeed; // Move left
    }
    @Override 
    public void update(int speed){
        this.Gspeed = speed ;
    }
   
    public boolean isOffScreen() {
        return x < -150; // Ghost is off screen
    }
}

class KYSpeed extends Ghost{
    private int kyspeed = 0;
    public KYSpeed(Image img, int x, int y, int Width, int Height) {
        super(img, x, y, Width, Height);
    }
    @Override
    public void moving() {
        this.x -= this.kyspeed; 
        
    }
    @Override 
    public void update(int speed){
        this.kyspeed = speed * 3 ;
    }   
}

class Crow extends Ghost{
    private int Cspeed = 0;
    public Crow(Image img, int x, int y, int Width, int Height) {
        super(img, x, y, Width, Height);
    }
    
    @Override
    public void moving() {
        this.x -= this.Cspeed;    
    }
    @Override 
    public void update(int speed){
        this.Cspeed = speed * 2 ;
    }    
}



