package galaxia;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.Timer;
public class Enemy implements ActionListener{
    Random r = new Random();                                                     //initializes random object
    private int score = 50, xPos, yPos, attackCount = 0, attackInt = 0;          //initializes integer values
    BufferedImage sprite;                                                        //enemy sprite image
    Timer tm = new Timer(1500, this);                                            //new timer action event every 1.5 seconds
    private int moveCount = 0;                                                   
    private Rectangle boundingRect = new Rectangle (xPos, yPos, 40, 40);         //bounding rectangle
    boolean enemyFire = false;                                                   //keeps track of if the enemy has fired
    
    public Enemy(int locx, int locy, int s, int type) throws IOException {
      xPos = locx;                                                               //sets enemy's x position
      yPos = locy;                                                               //sets enemy's y position
      score = s;                                                                 //sets enemy's score value
      File imageFile = new File("enemy" + type + ".png");                        //reads sprites image from file depending on value of type parameter
      sprite = javax.imageio.ImageIO.read(imageFile);                            //sets image to sprite value
      tm.start();                                                                //starts timer
      attackInt= r.nextInt(10);                                                  //new random int from 0 to 10
    }
    
       public void paintComponent(Graphics g) {
        g.drawImage(sprite, xPos, yPos, 40, 40, null);                           //draw enemy's image
    }

    public void actionPerformed(ActionEvent ae) {
        moveSprite();                                                            //calls moveSprite() every 1.5 seconds
       if (attackInt < 10) {
            attackInt++;                                                         //if attackInt is less than 10 increment by one
            setFireOff();                                                        //enemyFire is set to false
       } else if (attackInt == 10) {
            setFireOn();                                                         //if attackInt is 10 enemyFire is set to true
            attackInt = 0;                                                       //attackInt is reset back to 0 so count to 10 is started again
        }   
    }
    
    public void moveSprite() {
        if (moveCount < 12) {
        xPos = xPos + 15;                                                        //if moveCount is less than 12 move sprite x-position right 15 pixels
        moveCount++;                                                             //increment moveCount by 1
        boundingRect.setLocation(xPos, yPos);                                    //set bounding rectangle to new position
    } else if (moveCount >= 12 && moveCount < 24) {
        xPos = xPos - 15;                                                        //if moveCount is between 12 and 24 move sprite x-position left 15 pixels
        moveCount++;                                                             //increment moveCount by 1
        boundingRect.setLocation(xPos, yPos);                                    //set bounding rectangle to new position
    } else if (moveCount == 24) {
        moveCount = 0;                                                           //if moveCount is 24, reset moveCount so repositioning can start again
    }
    }
    
   public boolean doesCollide(Rectangle r) {
        if (boundingRect.intersects(r)) {
            return true;                                                         //if player shot rectangle collides with boundingRect return true
        } else {
            return false;                                                        //else return false
    }
    }
   
   public int getScore() {
       return score;                                                             //return score
   }
   
   public boolean getFire() {
       return enemyFire;                                                         //return if the enemy is firing
   }
   
   public void setFireOn() {
       enemyFire = true;                                                         //sets enemyFire to true
   }
   
   public void setFireOff() {
       enemyFire = false;                                                        //sets enemyFire to false
   }
   
   public int getX() {
       return xPos;                                                              //returns objects x coordinate
   }
   
   public int getY() {
       return yPos;                                                              //returns objects y coordinate                                                          
   }
   
   public int getAttackInt() {
       return attackInt;                                                         //returns attackInt
   }
}

