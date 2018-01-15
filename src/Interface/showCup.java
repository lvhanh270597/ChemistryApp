/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import math.Node;
import math.PwL;

/**
 *
 * @author hanh
 */
public class showCup extends JFrame {
    private final int X_BOARD = 800;
    private final int Y_BOARD = 600;
    private final int DX = 50;
    private final int DY = 150;    
    private final int X_SPACE = 10;
    private int X_CUP_SIZE;
    private int Y_CUP_SIZE;
    private List<Node> travel;
    private Node root;
    private List<Cup> cups;
    private List<String> chats;
    private Map<String, String> budget;
    private String pp;
    private Map<String, List<String>> backet;    
    private int cnt = 0;
    private int cnt_status = 0;
    
    private final int LEFT_MARGIN = 50;
    private final int TOP_MARGIN = 50;
    private final int X_L_SIZE = X_BOARD - 2 * LEFT_MARGIN;
    private final int Y_L_SIZE = 20;
    private final int Y_SPACE = 10;
    private JLabel chat_label;
    private JLabel pp_label;
    private List<JLabel> labels;
    private List<JLabel> labels2;
    private JButton next, back;
    private boolean nextButton;
    private boolean backButton;
    
    public showCup(List<Node> trl){
        
        travel = trl;
        
        root = travel.get(0);
        
        initUI();
        
        extractInfo();
              
        present();                
           
        setVisible(true);        
        
        //process();
        
        //revalidate();
        repaint();
    
    }        
    
    private void getBudget(){
        budget = new HashMap<String, String>();
        for (String key : backet.keySet()){
            List<String> v = backet.get(key);
            for (String val : v){
                // Chat H2SO4 in budget Key
                budget.put(val, key);
            }
        }
    }        
    
    private void extractInfo(){
        pp = root.getEdge();
        chats = root.getChats();
        Map<String, Node> childs = root.getChilds();
        backet = new HashMap<String, List<String>>();
        for (String key : childs.keySet()){
            backet.put(key, childs.get(key).getChats());
        }        
        
        next = new JButton();        
        next.setSize(60, 30);
        next.setLayout(new FlowLayout());
        next.setFont(new Font("Arial", Font.PLAIN, 10));
        next.setText("Next");
        next.setLocation(X_BOARD - 100, 100);        
        next.addActionListener(new ActionListener()                
        {
          public void actionPerformed(ActionEvent e)
          {
              nextButton = true;
              backButton = false;
              cnt++;
              if (cnt % 2 == 1){
                  process();
              }
              else getNext();
          }
        });
        next.setVisible(true);
        add(next);                ;
        
        
        getBudget();       
    }
    
    private void initUI(){        
        setSize(X_BOARD, Y_BOARD);
        setTitle("Similation");                           
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        setLocationRelativeTo(null);
    }
    private void present(){
        // show chats and pp
        chat_label = new JLabel();
        chat_label.setText(chats.toString());
        chat_label.setLocation(LEFT_MARGIN, TOP_MARGIN);
        chat_label.setSize(X_L_SIZE, Y_L_SIZE);                  
        add(chat_label);
        chat_label.setVisible(true);
        
        pp_label = new JLabel();
        pp_label.setText(pp);
        pp_label.setSize(X_L_SIZE, Y_L_SIZE);                  
        pp_label.setLocation(LEFT_MARGIN, TOP_MARGIN + Y_L_SIZE + Y_SPACE);
        add(pp_label);
        pp_label.setVisible(true);
        // show cup
        int n = chats.size();
        
        X_CUP_SIZE = (X_BOARD - (n - 1) * X_SPACE - 2 * DX) / n;
        Y_CUP_SIZE = 150;
        
        int x = DX;
        int y = DY;
        cups = new Vector<Cup>();
        for (int i=1; i<=n; i++){            
            cups.add(new Cup(x, y, X_CUP_SIZE, Y_CUP_SIZE));
            x += X_CUP_SIZE + X_SPACE;
        }
                                
        for (int i=0; i<chats.size(); i++){
            cups.get(i).addItem(chats.get(i));            
            cups.get(i).refresh();
            add(cups.get(i));
        }
    }
    
    private void process(){                
        
        labels = new Vector<JLabel>();        
        for (int i=0; i<chats.size(); i++) labels.add(new JLabel());
        labels2 = new Vector<JLabel>();
        for (int i=0; i<chats.size(); i++) labels2.add(new JLabel());
        
        for (int i=0; i<chats.size(); i++){                                   
            
            JLabel label = labels.get(i);
            
            String X = chats.get(i);
            Cup c = cups.get(i);
                         
            label.setText(budget.get(X));
            
            int x = c.getX();
            int y = c.getY() + Y_CUP_SIZE;
            
            label.setLocation(x, y + Y_SPACE);
            
            label.setSize(c.getWidth(), Y_L_SIZE);                                                    
            
            add(label);                                                                               
            
            label.setVisible(true);                        
            
            //----------------------------------------
            JLabel label2 = labels2.get(i);
                        
            label2.setText(backet.get(budget.get(X)).toString());
            
            x = c.getX();
            y = c.getY() + Y_CUP_SIZE;
            
            label2.setLocation(x, y + 3 * Y_SPACE);
            
            label2.setSize(c.getWidth(), Y_L_SIZE);                                                    
            
            add(label2);                                                                               
            
            label2.setVisible(true);                        
        }
                
        JLabel label = new JLabel();
        label.setVisible(true);
        add(label);        
        repaint();                
        
        if (pp == null) return;
        
        if (!pp.equals("mau") && !pp.equals("quy")){            
            for (Cup c : cups){
                c.addItem(pp);
                c.refresh();
            }
        }                    
             
    }
    public void getNext(){                                
        
        if (nextButton) cnt_status++;
        else cnt_status--;
        
        if (cnt_status < 0) {
            cnt_status++;
            return;
        }
        
        for (int i=0; i<chats.size(); i++){
            this.remove(labels.get(i));
            labels.get(i).setVisible(false);
            this.remove(labels2.get(i));
            labels2.get(i).setVisible(false);
            cups.get(i).setVisible(false);
            this.remove(cups.get(i));
        }                
        
        chat_label.setVisible(false);
        this.remove(chat_label);
        pp_label.setVisible(false);
        this.remove(pp_label);                
        
        
        if (cnt_status == travel.size()) {            
            this.dispose();
        }
        
        if (cnt_status <travel.size()) root = travel.get(cnt_status);                
        
        extractInfo();
        
        present();
                
        repaint();
    }    
    
}
