/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escapetheghost;

import java.awt.* ;
/**
 *
 * @author user
 */
 public abstract class Character {
     protected int x = 0 ;
     protected int y = 0 ;
     protected int Width = 0 ;
     protected int Height = 0 ;
     protected Image img ;
     public Character(){}
     
        public int getX(){
        return this.x ;
        }
         public int getY(){
             return this.y ;
        }
         public void setX(int x){
         this.x = x;
        }
         public void setY(int y){
         this.y = y;
        }
        public int getMW() {
             return img.getWidth(null);
        }

        public int getMH() {
            return img.getHeight(null);
        }
          public void setImage(Image img) {
            this.img = img ;
        }
        public Image getImage(){
             return this.img ;
        }
     
     public void moving(){}
     public void update(int a){}
     public void draw(Graphics g) {
        g.drawImage(img, x, y, Width,Height , null);
    }
 }
