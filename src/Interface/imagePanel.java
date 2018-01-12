/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 *
 * @author hanh
 */
public class imagePanel extends JComponent implements ActionListener{
    private Image[] image;
    private final int X_BOARD = 800;
    private final int Y_BOARD = 600;    
    private Timer timer;
    private int delay;
    private int next;
    public imagePanel(String[] path, int delay){        
        initImage(path);            
        initTimer(delay);
    }
    private void initImage(String[] path){
        
        next = 0;
        
        image = new Image[path.length];        
        for (int i=0; i<path.length; i++){
            String p = path[i];
            ImageIcon ii = new ImageIcon(p);
            image[i] = ii.getImage().getScaledInstance(X_BOARD, Y_BOARD, Image.SCALE_SMOOTH);
        }
    }
    private void initTimer(int DeLay){
        delay = DeLay;
        timer = new Timer(delay, this);
        timer.start();
    }    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image[next], 0, 0, this);        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        next++;
        if (next == image.length){
            next = 0;
        }
        repaint();
    }
}
