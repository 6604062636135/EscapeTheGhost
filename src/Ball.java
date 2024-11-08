/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escapetheghost;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class Ball  extends JPanel {
      private int x = 0 ;
      private int y = 0 ;
      private int Bspeed = 0;
    Ball(int x,int y){
        this.x = x ;
        this.y = y ;
        
    }
    public int getX(){
        return this.x ; 
    }
     public int getY(){
        return this.y ; 
    }
     public int getW() {
             return 30 ;
     }

     public int getH() {
            return 30;
     }
    public void moving() {
        this.x -= this.Bspeed; 
    }
    public void update(int speed){
        this.Bspeed = speed ;
    }
     public boolean isOffScreen() {
        return x < -150; 
    }
    public void draw(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.yellow); 
    g.fillOval(x, y, 30, 30);
    }
    
}
