/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package escapetheghost;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class ImageRS {
    public static Image IMG_ACTOR;
    public static Image IMG_ACTORRUN ;
    public static Image IMG_NGHOST;
    public static Image IMG_KRASEU;
    public static Image IMG_PRED;
    public static Image IMG_KY;
    public static Image IMG_KH;
    public static Image IMG_CR;
    public static Image IMG_BG ;
    public static Image IMG_BG2 ;

    static {
        IMG_ACTOR = new ImageIcon(ImageRS.class.getResource("Huatang.png")).getImage();
        IMG_ACTORRUN = new ImageIcon(ImageRS.class.getResource("HTrun.gif")).getImage();
        IMG_NGHOST = new ImageIcon(ImageRS.class.getResource("Nghost.png")).getImage();
        IMG_KRASEU = new ImageIcon(ImageRS.class.getResource("Kraseu.png")).getImage();
        IMG_PRED = new ImageIcon(ImageRS.class.getResource("Pred.png")).getImage();
        IMG_KY = new ImageIcon(ImageRS.class.getResource("KYspeed.png")).getImage();
        IMG_KH = new ImageIcon(ImageRS.class.getResource("Krahang.png")).getImage();
        IMG_CR = new ImageIcon(ImageRS.class.getResource("Crow.png")).getImage();
        IMG_BG = new ImageIcon(ImageRS.class.getResource("bg.png")).getImage();
        IMG_BG2 = new ImageIcon(ImageRS.class.getResource("bg2.png")).getImage();
    }
}

