package brickbreaker.main;
import brickbreaker.gui.MainGUI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 *
 * @author Adam Sorrenti
 * @description This main class controls the start of splash, intro, and info
 * screens. As well as the display of the MainGUI using various constructors.
 * @date 1/1/2017
 */
public class Main
{
        public static void main(String[] args)// Magic Line
        {
           MainGUI frame = new MainGUI();//Instants MainGUI
           Game game = new Game();//new instance of game
           Thread t = new Thread(game);//new thread instance of game
           final JButton next = new JButton("next");//next level button
           final JButton frestart = new JButton("frestart");//next level butt
           //settings 
           
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(null);   
            game.setLocation(3,30);
            game.setSize(600, 500); 
            frame.add(game);//adds JComponent
            game.addKeyListener(game);//key listener
              
              
            next.setLocation(800, 130);
            next.setSize(120, 40);
            next.setOpaque(false);
            next.setContentAreaFilled(false);
            next.setBorderPainted(false);
                next.setBackground(new Color(0, 255, 0, 0));
                    next.addActionListener((ActionEvent e) -> {
                        game.reset();//nexts current level
                        frame.levelUP("2");
                        frame.levelName();

                    });
            frame.add(next);
            frestart.setLocation(640, 480);
            frestart.setSize(260, 40);
            frestart.setOpaque(false);
            frestart.setContentAreaFilled(false);
            frestart.setBorderPainted(false);
            frestart.setBackground(new Color(0, 255, 0, 0));
                frestart.addActionListener((ActionEvent e) -> {
                    game.restart();// FULL RESTART
                    frame.levelUP("1");
                    frame.levelName();
                });
            frame.add(frestart);
                    
            frame.setLocationRelativeTo(game);
            frame.setSize(937, 533);
            frame.setResizable(false);
            frame.levelUP("1");
            frame.levelName();
            frame.setVisible(true);
            game.setFocusable(true);
            
            t.start();
            
        }
}