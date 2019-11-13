package brickbreaker.main;


import brickbreaker.gui.MainGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BrickBreaker extends JPanel implements KeyListener, ActionListener,
        Runnable {

 // movement keys..
 static boolean right = false;
 static boolean left = false;
 // ..............
 // variables declaration for ball.................................
 int ballx = 160;
 int bally = 218;
 // variables declaration for ball.................................
 // ===============================================================
 // variables declaration for bat..................................
 int batx = 280;
 int baty = 450;
 // variables declaration for bat..................................
 // ===============================================================
 // variables declaration for brick...............................
 int brickx = 140;
 int bricky = 150;

 int brickBreadth = 30;
 int brickHeight = 20;
 // variables declaration for brick...............................
 // ===============================================================
 // declaring ball, paddle,bricks
 Rectangle Ball = new Rectangle(ballx, bally, 10, 10);
 Rectangle Bat = new Rectangle(batx, baty, 70, 14);
 //Rectangle Brick = new Rectangle(brickx, bricky, 30, 10);
 Rectangle[] Brick = new Rectangle[12];
public Image img;
//reverses......==>
int movex = -1;
int movey = -1;
boolean ballFallDown = false;
boolean bricksOver = false;
int count = 0;
String status;


 public static void main(String[] args) 
 {
    MainGUI frame = new MainGUI();

    BrickBreaker game = new BrickBreaker();
    //frame.setSize(350, 450);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(null);   
      game.setLocation(3,30);
      game.setSize(600, 500);
    frame.add(game);

    frame.setLocationRelativeTo(game);
    frame.setResizable(false);
    frame.setSize(937, 540);
    frame.setVisible(true);
    game.addKeyListener(game);
    game.setFocusable(true);
    Thread t = new Thread(game);
    t.start();

 }

 // declaring ball, paddle,bricks

 public void paintComponent(Graphics g) {
  g.setColor(Color.LIGHT_GRAY);
  g.fillRect(0, 0, 600, 500);
  g.setColor(Color.red);
  
  g.fillOval(Ball.x, Ball.y, Ball.width, Ball.height);
  try {
           img = ImageIO.read(new File("D:\\Users\\adam.sorrenti\\Documents\\NetBeansProjects\\Objects\\src\\paddle.png"));
       } catch (IOException ex) {
           Logger.getLogger(BrickBreaker.class.getName()).log(Level.SEVERE, null, ex);
       }
  g.drawImage(img,Bat.x, Bat.y, Bat.width, Bat.height,null);
  g.setColor(Color.black);
  g.drawRect(0, 0, 599, 499);
  
     g.setColor(Color.BLACK);
        for (int i = 0; i < Brick.length; i++) 
        {
          if (Brick[i] != null) 
          {
               g.fill3DRect(Brick[i].x, Brick[i].y, Brick[i].width,
                   Brick[i].height, true);
          }
        }

  if (ballFallDown == true || bricksOver == true) {
   Font f = new Font("Arial", Font.BOLD, 20);
   g.setFont(f);
   g.drawString(status, 70, 120);

   ballFallDown = false;
   bricksOver = false;
  }

 }
 public void bLayout()
 {
 
 }

 // /...Game Loop...................

 // /////////////////// When ball strikes borders......... it

 public void run() {

  // //////////// =====Creating bricks for the game===>.....
     createBricks();
  // ===========BRICKS created for the game new ready to use===

  // ====================================================
  // == ball reverses when touches the brick=======
//ballFallDown == false && bricksOver == false
  while (true) {
//   if(gameOver == true){return;}
   for (int i = 0; i < Brick.length; i++) {
    if (Brick[i] != null) {
     if (Brick[i].intersects(Ball)) {
      Brick[i] = null;
      // movex = -movex;
      movey = -movey;
      count++;
     }// end of 2nd if..
    }// end of 1st if..
   }// end of for loop..

   // /////////// =================================

   if (count == Brick.length) {// check if ball hits all bricks
    bricksOver = true;
    status = "YOU WON THE GAME";
    repaint();
   }
   // /////////// =================================
   repaint();
   Ball.x += movex;
   Ball.y += movey;

   if (left == true) {

    Bat.x -= 3;
    right = false;
   }
   if (right == true) {
    Bat.x += 3;
    left = false;
   }
   if (Bat.x >= 530) {
    Bat.x = 530;
   } else if (Bat.x <= 0) {
    Bat.x = 0;
   }
   // /===== Ball reverses when strikes the bat
   if (Ball.intersects(Bat)) {
    movey = -movey;
    // if(Ball.y + Ball.width >=Bat.y)
   }
   // //=====================================
   // ....ball reverses when touches left and right boundary
   if (Ball.x <= 0 || Ball.x + Ball.height >= 599) {
    movex = -movex;
   }// if ends here
   if (Ball.y <= 0) {// ////////////////|| bally + Ball.height >= 250
    movey = -movey;
   }// if ends here.....
   if (Ball.y >= 447) {// when ball falls below bat game is over...
    ballFallDown = true;
    status = "YOU LOST THE GAME";
    repaint();
//    System.out.print("game");
   }
   try {
    Thread.sleep(10);
   } catch (Exception ex) {
   }// try catch ends here

  }// while loop ends here

 }

 // loop ends here

 // ///////..... HANDLING KEY EVENTS................//
 @Override
 public void keyPressed(KeyEvent e) {
  int keyCode = e.getKeyCode();
  if (keyCode == KeyEvent.VK_LEFT) {
   left = true;
   // System.out.print("left");
  }

  if (keyCode == KeyEvent.VK_RIGHT) {
   right = true;
   // System.out.print("right");
  }
 }

 @Override
 public void keyReleased(KeyEvent e) {
  int keyCode = e.getKeyCode();
  if (keyCode == KeyEvent.VK_LEFT) {
   left = false;
  }

  if (keyCode == KeyEvent.VK_RIGHT) {
   right = false;
  }
 }

 @Override
 public void keyTyped(KeyEvent arg0) {

 }

 @Override
 public void actionPerformed(ActionEvent e) {
  String str = e.getActionCommand();
  if (str.equals("restart")) {
   this.restart();

  }
 }

 public void restart() {

  requestFocus(true);
  initializeVariables();
  createBricks();
  repaint();
 }

 public void initializeVariables(){
     // ..............
      // variables declaration for ball.................................
      ballx = 160;
      bally = 218;
      // variables declaration for ball.................................
      // ===============================================================
      // variables declaration for bat..................................
      batx = 160;
      baty = 245;
      // variables declaration for bat..................................
      // ===============================================================
      // variables declaration for brick...............................
      brickx = 70;
      bricky = 50;
      // variables declaration for brick...............................
      // ===============================================================
      // declaring ball, paddle,bricks
      Ball = new Rectangle(ballx, bally, 5, 5);
      Bat = new Rectangle(batx, baty, 40, 5);
      // Rectangle Brick;// = new Rectangle(brickx, bricky, 30, 10);
      Brick = new Rectangle[12];

      movex = -1;
      movey = -1;
      ballFallDown = false;
      bricksOver = false;
      count = 0;
      status = null;

     
 }
 public void createBricks(){
     // //////////// =====Creating bricks for the game===>.....
      /*
       * creating bricks again because this for loop is out of while loop in
       * run method
       */
      for (int i = 0; i < Brick.length; i++) {
       Brick[i] = new Rectangle(brickx, bricky, brickBreadth, brickHeight);
       if (i == 5) {
        brickx = 140;
        bricky = (bricky + brickHeight + 2);
        
       }
       if (i == 9) {
        brickx = 172;
        bricky = (bricky + brickHeight + 2);
       
       }
       brickx += (brickBreadth+1);
      }
 }

}