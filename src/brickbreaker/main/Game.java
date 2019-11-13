/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker.main;
import brickbreaker.gui.MainGUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable 
{
   public BrickLayout brickLays = new BrickLayout();
   public Image img;
   public Image backgroundTile;
   boolean rightMove = false;
   boolean leftMove = false;
   boolean outBound = false;
   boolean bricksOver = false;
   int paddleX = 280;
   int paddleY = 450;
   int reverseXvector = -1;
   int revcerseYvector = -1;
   int ballx = 160;
   int bally = 218;
   int count = 0;
   int level = 1;
   Rectangle Ball = new Rectangle(ballx, bally, 20, 20);
   Rectangle Paddle = new Rectangle(paddleX, paddleY, 70, 14);
 public void paintComponent(Graphics g) 
    {
      
        try 
        {
            backgroundTile = ImageIO.read(getClass().getResource("/brickbreaker/main/Game Textures/BgTiles.png"));
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int x = 1; x < 600; x+=backgroundTile.getWidth(null)) 
        {
           for (int y = 1; y < 500; y+=backgroundTile.getHeight(null)) 
           {
               g.drawImage(backgroundTile, x, y, backgroundTile.getWidth(null)*2,
                       backgroundTile.getHeight(null)*2, null);
           }
        }
       try 
       {
         img = ImageIO.read(getClass().getResource("/brickbreaker/main/Game Textures/paddle.png"));
       } 
       catch (IOException ex) 
       {
         Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
       }
     g.drawImage(img,Paddle.x, Paddle.y, Paddle.width, Paddle.height,null);
     g.setColor(Color.yellow);
     g.drawRect(0, 0, 599, 499);
     g.setColor(Color.red);
     g.fillOval(Ball.x, Ball.y, Ball.width, Ball.height);
     g.setColor(Color.black);
     g.drawRect(0, 0, 599, 499);
     nextLvl(g);
        if (outBound == true || bricksOver == true) // END
        { 
        
            if(outBound == true)
            {
                   stop();
            }
            if(bricksOver == true)
            {
                win(g);
            }
        level = 2;
         outBound = false;
         bricksOver = false;
        }
        
 }
public void nextLvl(Graphics g)
{
if (level == 1)
     {
         brickLays.bLayout1(g, this);
     }
     else if (level == 2)
     {
         brickLays.bLayout2(g, this);
     }
}
public void stop()
{
   
System.exit(0);
}
public void win(Graphics g)
{
   g.drawString("YOU WIN", 1, 1);
System.exit(0);
}
 public void run() 
 {

    brickLays.create(level);
 
  while (true) 
  {
   for (int i = 0; i < brickLays.Brick.length; i++) 
   {
    if (brickLays.Brick[i] != null) 
    {
     if (brickLays.Brick[i].intersects(Ball)) 
     {
      brickLays.Brick[i] = null;      
      revcerseYvector = -revcerseYvector;
      count++; 
     }
    }
   }
   if (count == brickLays.Brick.length) 
   {// check if ball hits all bricks
    bricksOver = true;
    level++;
    repaint();
   }
   repaint();
   Ball.x += reverseXvector;
   Ball.y += revcerseYvector;
   if (leftMove == true) 
   {
    Paddle.x -= 3;
    rightMove = false;
   }
   if (rightMove == true) 
   {
    Paddle.x += 3;
    leftMove = false;
   }
   if (Paddle.x >= 530) 
   {
    Paddle.x = 530;
   } 
   else if (Paddle.x <= 0) 
   {
    Paddle.x = 0;
   }
  
   if (Ball.intersects(Paddle)) 
   { // Ball reverses when hits the bat
    revcerseYvector = -revcerseYvector;
   } 
   if (Ball.x <= 0 || Ball.x + Ball.height >= 599) 
   {// ball reverses when touches left and right boundary
    reverseXvector = -reverseXvector;
   }
   if (Ball.y <= 0) 
   {
    revcerseYvector = -revcerseYvector;
   }
   if (Ball.y >= 444) 
   {// when ball falls below paddle game is over
    outBound = true;  
  //  repaint();
   }
   try 
   {
    Thread.sleep(10);
   } 
   catch (Exception ex) {}
  }
 }
public void initComponents()
 {   
    
    Ball = new Rectangle(ballx, bally, 20, 20);
    Paddle = new Rectangle(paddleX, paddleY, 70, 14);
    outBound = false;
    bricksOver = false;
    count = 0;
    level =1;
    ballx = 160;
    bally = 218;
    paddleX = 280;
    paddleY = 450;
   
 }
 public void restart() 
 {
  this.requestFocus(true);
    this.initComponents();
  brickLays.create(level);
  repaint();
 }
 public void reset() 
 {
  this.requestFocus(true);
  this.initComponents();
  level = 2;
  brickLays.create(level);
  repaint();
 }
 
 //Key Handlers//
 @Override
 public void keyPressed(KeyEvent e) 
 {
  int key = e.getKeyCode();
  if (key == KeyEvent.VK_LEFT) 
  {
   leftMove = true;
  }

  if (key == KeyEvent.VK_RIGHT) 
  {
   rightMove = true;
  }
 }

 @Override
 public void keyReleased(KeyEvent evt) 
 {
  int key = evt.getKeyCode();
  if (key == KeyEvent.VK_LEFT) 
  {
   leftMove = false;
  }

  if (key == KeyEvent.VK_RIGHT) 
  {
   rightMove = false;
  }
 }

 @Override
 public void keyTyped(KeyEvent evt) {}
}