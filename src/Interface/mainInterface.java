/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import ChemistryApp.multiThread;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author hanh
 */
public class mainInterface extends javax.swing.JFrame {

    private Image image_title;
    
    private final int X_BOARD = 800;
    private final int Y_BOARD = 600;
    private final int X_TITLE = 800;
    private final int Y_TITLE = 150;
    private final int X_SIZE_ICON = 120;
    private final int Y_SIZE_ICON = 120;
    private final int TOP_MARGIN = 250;    
    private final int X_SPACE = 50;    
    private final int X_LABEL_SIZE = 120;
    private final int Y_LABEL_SIZE = 20;
    private final int Y_SPACE = 50;    
    private int Dx;
    private JLabel[] L_image;
    private JLabel[] L_text;
    
    
    private final String[] paths = {"ptk.png", "canbang.png", "dieuche.png", "gd1.jpg", "dc.png", "cb.png", "tracuu.png"};
    private final String[] st = {"Phân tử khối", "Cân bằng", "Điều chế", "Chuỗi phản ứng", "Hiện tượng", "Phân biệt", "Tìm chất"};
    /**
     * Creates new form mainInterface
     */
    public mainInterface() {        
        initComponents();
        
        preProcessing();   
        
        setImageForAll();
    }

    private void preProcessing(){       
        
        setSize(X_BOARD, Y_BOARD);
        setResizable(false);
        setTitle("Chemistry");        
        
        ImageIcon ii = new ImageIcon("hoahoc/chemistry.png");
        image_title = ii.getImage().getScaledInstance(X_TITLE, Y_TITLE, Image.SCALE_SMOOTH);
        TitleBar.setIcon(new ImageIcon(image_title));        
        
        L_image = new JLabel[7];
        L_text = new JLabel[7];
        
        for (int i=0; i<7; i++){
            L_image[i] = new JLabel();
            L_text[i] = new JLabel();
        }
        
        
    }
    
    private void getDx(int n){
        Dx = (X_BOARD - (n - 1) * X_SPACE - n * X_SIZE_ICON) / 2;        
    }    
    
    
    
    private void setImageForAll(){        
        
        getDx(4);
        
        int x = Dx;
        int y = TOP_MARGIN;        
                
        for (int i=0; i<4; i++){
            L_image[i].setSize(X_SIZE_ICON, Y_SIZE_ICON); 
            System.out.println(x + " " + y); 
            L_image[i].setLocation(x, y);
            
            ImageIcon ii = new ImageIcon("hoahoc/" + paths[i]);
            Image image = ii.getImage().getScaledInstance(L_image[i].getWidth(), L_image[i].getHeight(), Image.SCALE_SMOOTH);
            L_image[i].setIcon(new ImageIcon(image));
            
            L_text[i].setText(st[i]); 
            L_text[i].setSize(X_LABEL_SIZE, Y_LABEL_SIZE);
            L_text[i].setLocation(x, y - Y_LABEL_SIZE - 10);
            
            x += X_SIZE_ICON + X_SPACE;           
            
            L_text[i].setVisible(true);
            L_image[i].setVisible(true);
            add(L_text[i]);
            add(L_image[i]);
        }
        
        getDx(3);
        
        x = Dx;
        y += Y_SIZE_ICON + Y_SPACE;
        
        for (int i=4; i<7; i++){
            L_image[i].setSize(X_SIZE_ICON, Y_SIZE_ICON); 
            System.out.println(x + " " + y); 
            L_image[i].setLocation(x, y);
            
            ImageIcon ii = new ImageIcon("hoahoc/" + paths[i]);
            Image image = ii.getImage().getScaledInstance(L_image[i].getWidth(), L_image[i].getHeight(), Image.SCALE_SMOOTH);
            L_image[i].setIcon(new ImageIcon(image));
            
            
            L_text[i].setText(st[i]); 
            L_text[i].setSize(X_LABEL_SIZE, Y_LABEL_SIZE);
            L_text[i].setLocation(x, y - Y_LABEL_SIZE - 10);
            
            x += X_SIZE_ICON + X_SPACE;           
            
            L_text[i].setVisible(true);
            L_image[i].setVisible(true);
            add(L_text[i]);
            add(L_image[i]);
        }

        
        ptkForm a = new ptkForm();
        canBangF b = new canBangF();
        dieuChe c = new dieuChe();        
        chuoiPhanUngForm d = new chuoiPhanUngForm();
        hienTuongForm e = new hienTuongForm();
        phanBietForm f = new phanBietForm();
      
        
        L_image[0].addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
            {  
                new ptkForm().show();
            }  
        });
        
        L_image[1].addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
            {                  
                new canBangF().show();
                new hdCanBang().show();
            }  
        });
        //-----------------        
        //-----------------
        L_image[2].addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
            {  
                new dieuChe().show();
            }  
        });
        L_image[3].addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
            {  
                new chuoiPhanUngForm().show();
            }  
        });
        L_image[4].addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
            {  
                new hienTuongForm().show();
            }  
        });
        L_image[5].addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
            {  
                new phanBietForm().show();
            }  
        });
        L_image[6].addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
            {  
                multiThread t1 = new multiThread("Create a winndow", 1);
                multiThread t2 = new multiThread("Chat bot", 2);        
                t1.start();
                t2.start();
            }  
        });
                
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        TitleBar = new javax.swing.JLabel();

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TitleBar.setFont(new java.awt.Font("Ubuntu Condensed", 1, 24)); // NOI18N
        TitleBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TitleBarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TitleBar, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(TitleBar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(337, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TitleBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitleBarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TitleBarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleBar;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    // End of variables declaration//GEN-END:variables
}
