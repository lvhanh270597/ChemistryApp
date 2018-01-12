/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Button;
import javax.swing.JFrame;

/**
 *
 * @author hanh
 */
public class mainInterface extends JFrame{
    
    private final int X_BOARD = 800;
    private final int Y_BOARD = 600;
    
    private final String[] paths = {"hoahoc/dc.png", "hoahoc/gd1.jpg"};
    
    public mainInterface(){
                        
        initUI();                                
        
    }
    private void initUI(){
        
        
        
        setTitle("Chemistry");
        setSize(X_BOARD, Y_BOARD);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);               
        
        
        
        setContentPane(new imagePanel(paths, 1000));
        setVisible(true);
    }
}
