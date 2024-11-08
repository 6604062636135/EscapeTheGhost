package escapetheghost;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */

import java.awt.* ;
public class bgAnimation{
  private Image imgbg ;
  private int xPos = 0  ;
  private int speed = 0 ;
  int W ;
  int H ;
  String NameBg ;
  public bgAnimation(){
      xPos = 0 ;
      speed = 0 ;  
      NameBg = "" ;
      xPos = 0 ;
      W  = 0 ;
      H = 0 ;
      imgbg = ImageRS.IMG_BG ;
  }
  
   public bgAnimation(int getW,int getH,Image Bg,String NameBg){
     this.W = getW ;
     this.H  = getH ;
     this.imgbg = Bg ;
     this.NameBg = NameBg ;
  }
   public void setWH(int w,int h){
       this.W = w ;
       this.H  = h ;
   }
    public void setNImage(String N){
       this.NameBg = N ;
   }
   public String getNImage(){
       return this.NameBg ;
   }
   public Image getBg(){
       return imgbg ;
   }
   public void setImageBg(Image Bg ){
       this.imgbg = Bg ;
   }
   public void setSpeed(int speed) {
        this.speed = speed;
   }
   public void update() {
       xPos -= speed;
       if (xPos <= -W) {
           xPos = 0;
       }
   }
    public void draw(Graphics g) {
        g.drawImage(imgbg, xPos, 0, W, H, null);
        g.drawImage(imgbg, xPos + W, 0, W, H, null);
    }
       
}
