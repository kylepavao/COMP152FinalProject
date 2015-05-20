package galaxia;
import javax.swing.JPanel;
import java.awt.Color;
import java.io.*;
import java.awt.Graphics;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements ActionListener {
   private Player player1;                                                       //player object
   PlayerBeam playerBeam;                                                        //player shot object
   EnemyBeam enemyBeam;                                                          //enemy shot object
   Timer tm = new Timer(5, this);                                                //new timer goes off every 5 milliseconds
   private int velX = 0;                                                         //speed that player's x-position changes
   boolean playerFire = false, enemyFire = false, gameOver = false;              //boolean flag values
   private static ArrayList<Enemy> enemyList = new ArrayList();                  //ArrayList of enemy objects
   private Enemy enemy1 = new Enemy(30, 200, 10, 1);                             //creates 15 different enemy object
   private Enemy enemy2 = new Enemy(60, 200, 10, 1);
   private Enemy enemy3 = new Enemy(90, 200, 10, 1);
   private Enemy enemy4 = new Enemy(120, 200, 10, 1);
   private Enemy enemy5 = new Enemy(150, 200, 10, 1);
   private Enemy enemy6 = new Enemy(30, 150, 50, 2);
   private Enemy enemy7 = new Enemy(60, 150, 50, 2);
   private Enemy enemy8 = new Enemy(90, 150, 50, 2);
   private Enemy enemy9 = new Enemy(120, 150, 50, 2);
   private Enemy enemy10 = new Enemy(150, 150, 50, 2);
   private Enemy enemy11 = new Enemy(30, 100, 100, 3);
   private Enemy enemy12 = new Enemy(60, 100, 100, 3);
   private Enemy enemy13 = new Enemy(90, 100, 100, 3);
   private Enemy enemy14 = new Enemy(120, 100, 100, 3);
   private Enemy enemy15 = new Enemy(150, 100, 100, 3);
   
    public GraphicsPanel() throws IOException {                                  //GraphicsPanel constructor
      setBackground(Color.BLACK);                                                //sets background to black                                                     
      player1 = new Player();                                                    //creates player object
      tm.start();                                                                //starts timer
      addKeyListener(new MyKeyListener());                                       //creates a KeyListener
      setFocusable(true);                                                        //needed with KeyListnener initialization
      enemyList.add(enemy1);                                                     //adds all 15 enemies to array list
      enemyList.add(enemy2);
      enemyList.add(enemy3);
      enemyList.add(enemy4);
      enemyList.add(enemy5);
      enemyList.add(enemy6);
      enemyList.add(enemy7);
      enemyList.add(enemy8);
      enemyList.add(enemy9);
      enemyList.add(enemy10);
      enemyList.add(enemy11);
      enemyList.add(enemy12);
      enemyList.add(enemy13);
      enemyList.add(enemy14);
      enemyList.add(enemy15);
      }
    
   public void paintComponent(Graphics g) {
       super.paintComponent(g);                                                  //calls superclass' paintComponent method (must be called)
       for (int i = 0; i < enemyList.size(); i++) {          
           enemyList.get(i).paintComponent(g);                                   //paints each element in arraylist
        }
       g.setColor(Color.RED);                                                    //sets text font color to red
       g.drawString("Score: " + player1.getScore(), 160, 20);                    //displays score at top of panel
       g.drawString("Lives: " + player1.getLives(), 40, 450);                    //displays player's lives at top of panel
       player1.paintComponent(g);                                                //calls player1's paintComponent
       if (player1.getFire() == true) {
           playerBeam.setYCoordinate(-3);                                        //if player1.fire equals true moves playerBeam up by 3
           playerBeam.paintComponent(g);                                         //paints playerBeam
       }
       if (enemyFire == true) {
               enemyBeam.paintComponent(g);                                      //if enemyFire equals true paint enemyBeam
               }
       if (enemyList.isEmpty() == true)
           g.drawString("YOU WIN!!!!", 165, 200);                                //if there are no enemies left display "You Win" message on screen
       if (player1.getLives() == 0) {
           beamOff();                                                            //if player's lives equal 0 turns enemyBeam off
           g.drawString("Game Over!!", 165, 400);                                //paints "Game Over" message on screen in player's area
           tm.stop();                                                            //stops timer          
}
   }
   public void removeUnit(int n) {
       int enemyScore = enemyList.get(n).getScore();                             //assigns removed enemy's score value to integer
       player1.setScore(enemyScore);                                             //adds enemy's score to player's score  
       enemyList.remove(n);                                                      //removes selected enemy from enemyList
       playerFire = false;                                                       //takes player shot off the screen
       }

   public void actionPerformed(ActionEvent e) {                                  //performed every 5 milliseconds
       player1.setPlayerPosition(velX);                                          //sets player's x-position by increments of velX
       if (playerFire == true) 
           playerBeam = new PlayerBeam(player1.getPlayerPosition(), 400);        //if playerFire equals true print new player beam starting from player's position
       for (int i = 0; i < enemyList.size(); i++) {
           if (enemyFire == false && enemyList.get(i).getFire() == true) {
            beamOn();                                                            //if enemy's fire is true set enemyFire flag to true
            enemyBeam = new EnemyBeam(enemyList.get(i).getX(), enemyList.get(i).getY() + 50);  //creates new enemyBeam from enemy's x and y positions
           }
       } 
       repaint();                                                                //calls paintComponent() again
}
   public void beamOn() {
       enemyFire = true;                                                         //allows enemyBeam to be created
   }
   
   public void beamOff() {
       enemyFire = false;                                                        //cancels enemyBeam object
   }
   private class MyKeyListener implements KeyListener {                          //KeyListener
   public void keyTyped(KeyEvent ke) {}                                          //Nothing happens on keyTyped
   public void keyReleased(KeyEvent ke) {                                        //When key is released make player graphic stand in place
       velX = 0; 
   }
   public void keyPressed(KeyEvent ke) {
        int c = ke.getKeyCode();                                                 //assign integer to KeyCode of the pressed key
        if (c == KeyEvent.VK_RIGHT){                                             
        velX = 2;                                                                //if right key is pressed increment player's x-position by 2
        } else if(c == KeyEvent.VK_LEFT) {
        velX = -2;                                                               //if left key is pressed decrement player's x-position by 2
        } else if (c == KeyEvent.VK_SPACE && player1.getFire() == false && gameOver == false) {
            player1.setFireOn();                                                 //if space bar is pressed allow playerBeam to be created
            if (player1.getFire() == true) {
                playerBeam = new PlayerBeam(player1.getPlayerPosition(), 380);   //create playerBeam from player1's  x-position and 380 y-position
            }
        }
   }
   }
   
   private class PlayerBeam {
       private int x, y;                                                         //x and y value of playerBeam
       Rectangle rect;                                                           //bounding rectangle
       public PlayerBeam(int locx, int locy) {
       x = locx + 17;                                                            //sets playerBeam to middle of player1's width
       y = locy;                                                                 //sets playerBeam's y-value to 380
       rect = new Rectangle(x, y, 5, 10);                                        //sets bounding rectangle to new position
     }
       
       public void paintComponent(Graphics g) {
           g.setColor(Color.YELLOW);                                             //makes shot graphic yellow
           g.fillRect(x, y, 5, 10);                                              //paints shot grapic
       }
       
       public void setYCoordinate(int n) {
           y = n + y;                                                            //increments playerShot's y-coordinate by n
           rect.setLocation(x, y);                                               //sets bounding rectangle to new position
           if (y < 0)
               player1.setFireOff();                                             //if y goes off top of panel disable playerBeam object
           for (int i = 0; i < enemyList.size(); i++) {
              if (enemyList.get(i).doesCollide(rect) == true) {
                  removeUnit(i);                                                 //if playerBeam collides with any enemies' rectangles remove that enemy
                  player1.setFireOff();                                          //disable playerBeam object
              }
           }
       }
       }
   
      private class EnemyBeam implements ActionListener {
       private int x, enemyFireY;                                                //enemyBeam x and y coordinates
       Rectangle rect;                                                           //bounding rectangle
       Timer t = new Timer(30, this);                                            //new timer goes off every 30 milliseconds
       public EnemyBeam(int locx, int locy) {
       x = locx + 17;                                                            //sets enemyBeam to center of enemy's width
       enemyFireY = locy;                                                        //sets enemyBeam y-coordinate to enemy's y-coordinate
       rect = new Rectangle(x, enemyFireY, 5, 10);                               //creates new bounding rectangle
       t.start();                                                                //start timer
     }
       
       public void paintComponent(Graphics g) {
           g.setColor(Color.YELLOW);                                             //makes enemyBeam graphic yellow
           g.fillRect(x, enemyFireY, 5, 10);                                     //paints enemyBeam graphic
       }
       public void actionPerformed(ActionEvent ae) {
           setYCoordinate(3);                                                    //calls and passes 3 to setYCoordinate every 30 milliseconds
           repaint();                                                            //call paintComponent() again
       }
   
       public void setYCoordinate(int n) {
           enemyBeam.enemyFireY = enemyBeam.enemyFireY + n;                      //increments y-coordinate by n
           rect.setLocation(enemyBeam.x, enemyBeam.enemyFireY);                  //resets bounding rectangle to new position
           if (enemyBeam.enemyFireY > 450) {
               beamOff();                                                        //if enemyBeam leaves screen gets disabled
               t.stop();                                                         //sets enemyBeam timer off
           } else if (player1.doesCollide(rect) == true) {
               beamOff();                                                        //if enemyBeam collides with player disable enemyBeam
               t.stop();                                                         //stops enemyBeam timer
               player1.loseALife();                                              //takes away one life
          }
       }
   }
}

    