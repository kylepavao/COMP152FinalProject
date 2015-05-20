package galaxia;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Player  {
   private int score = 0,velX = 0, lives = 3;                       //initializes integer values
   private BufferedImage playerSprite;                              //player sprite image
   private Rectangle rect = new Rectangle(playerPosition, 400, 40, 40);  //creates new rectangle object
   private static int playerPosition = 175;                         //holds player's x position
   boolean fire = false;                                            //flag determines if player fires
    
   public Player() throws IOException {
      File imageFile = new File("playerShip.png");                  //reads image from .png
      playerSprite = javax.imageio.ImageIO.read(imageFile);         //assigns image to playerSprite BufferedImage
   }    
   public void paintComponent(Graphics g) {
        g.drawImage(playerSprite, playerPosition, 400, 40, 40, null);   //paints the player's graphic
        rect.setLocation(playerPosition, 400);                          //sets bounding rectangle to player's position
   }
      public int getScore() {
          return score;                                                 //returns player's score
      }
      
      public void setPlayerPosition(int n) {
          if (playerPosition > 350) {
              playerPosition = 350;                                    //if player tries to leave right side of frame set back to 350
      } else if (playerPosition < 0) {
              playerPosition = 0;                                      //if player tries to leave left side of frame set back to 0
      } else {
          playerPosition = n + playerPosition;                         //else increment playerPosition by 1 or -1
          rect.setLocation(playerPosition , 400);                      //set bounding rectangle to same position
      }
      }
      
      public int getPlayerPosition() {
          return playerPosition;                                       //returns player x-position
      }
      
      public Rectangle setRectangle(int n) {
          return new Rectangle(n, 400, 40, 40);                        //sets rectangle to current x-position
      }
      
      public boolean doesCollide(Rectangle r) {
          if (rect.intersects(r)) {
              return true;                                             //if shot rectangle collides with player return true
          } else {
              return false;                                            //else return false
      }
      }
      
      public void loseALife() {
      lives = lives - 1;                                               //when called decrement lives by one
      }
      
      public int getLives() {
          return lives;                                                //returns lives
      }
      
      public int getVelX() {
          return velX;                                                 //get interval that x-position is changing by
      }
      
      public boolean getFire() {
          return fire;                                                 //return if fire is true or false
      }
      
      public void setFireOn() {
          fire = true;                                                 //declares the player is taking a shot
      }
      
      public void setFireOff() {
          fire = false;                                                //declares the player is not taking a shot
      }
      
      public void setScore(int n) {
          score = score + n;                                          //increments score by n
      }
}


