/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 *
 * @author adam.sorrenti
 * @descrption Creates bricks
 */
public class BrickLayout extends JPanel 
{
 int brickx = 220;
 int bricky = 100;
 public int brickBreadth = 30;
public int brickHeight = 20;
 public Rectangle[]Brick = new Rectangle[12];
 public int[][] map;
 public int brickWidth;



    public void bLayout1(Graphics g, Game b)
    {
     
      g.setColor(Color.BLACK);
        for (int i = 0; i < Brick.length; i++) 
        {
          if (Brick[i] != null) 
          {
               g.fill3DRect(Brick[i].x, Brick[i].y, Brick[i].width,
                   Brick[i].height, true);
          }
        }
    } 
    public void bLayout2(Graphics g, Game b)
    {
       //Brick = new Rectangle[7];
        g.setColor(Color.BLACK);
        for (int i = 0; i < Brick.length; i++) 
        {
          if (Brick[i] != null) 
          {
               g.fill3DRect(Brick[i].x+5*i, Brick[i].y, Brick[i].width,
                   Brick[i].height, true);
          }
        }
    }
    public void create(int level)
    {
        if (level == 1 )
        {
              brickx = 220;
              bricky = 100;
              brickBreadth = 30;
              brickHeight = 20;
            
            
            for (int i = 0; i < Brick.length; i++) {
            Brick[i] = new Rectangle(brickx, bricky, brickBreadth, brickHeight);
            if (i == 5) 
            {
             brickx = 220;
             bricky = (bricky + brickHeight + 2);

            }
            if (i == 9) 
            {
             brickx = 250;
             bricky = (bricky + brickHeight + 2);

            }
            brickx += (brickBreadth+1);
      }

        }
        else
        {
          
            brickx = 220;
                bricky = 100;
              brickBreadth = 30;
              brickHeight = 20;
            Brick = new Rectangle[12];
            for (int i = 0; i < Brick.length; i++) {
            Brick[i] = new Rectangle(brickx, bricky, brickBreadth,
                   brickHeight);
            if (i == 7) 
            {
             brickx = 200;
             bricky = (bricky + brickHeight + 2);

            }
            if (i == 9) 
            {
             brickx = 220;
             bricky = (bricky + brickHeight + 2);

            }
            brickx += (brickBreadth+1);
            }
            
        }
    }
}
