/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
/**
 *
 * @author Thien Trang
 */
public class giaodien extends javax.swing.JFrame {

    /**
     * Creates new form giaodien
     */
    public giaodien() {
        initComponents();
        createInterface();
    }
    gdcanbang cb = new gdcanbang();
    gdphantukhoi ptk = new gdphantukhoi();
    gdnhanbiet nb = new gdnhanbiet();
    gddieuche dc = new gddieuche();
    gddudoan dd = new gddudoan();
    gdbaitoan bt = new gdbaitoan();
    public void createInterface(){
        this.setSize(810, 569);
        this.setLocation(450,250);
        this.setBackground(Color.WHITE);
        nen1.setSize(810,213);
        nen1.setLocation(0,36);
        nen1.setBackground(Color.LIGHT_GRAY);
        nen1.setLayout(null);
        nen2.setSize(810,320);
        nen2.setLocation(0,249);
        nen2.setBackground(new Color(32,47,90));
        nen2.setLayout(null);
        this.add(nen2);
        this.add(nen1);
        exit.setSize(36,36);
        exit.setLocation(760, 0);
        exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon exitIcon = new ImageIcon("hoahoc\\exit.png");
        exit.setIcon(exitIcon);
        exit.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
                System.exit(0);
            }
        });
        this.add(exit);
        mini.setSize(36,36);
        mini.setLocation(724, 0);
        mini.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ImageIcon miniIcon = new ImageIcon("hoahoc\\minimize.png");
        mini.setIcon(miniIcon);
      /*  mini.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
                System.exit(0);
            }
        });*/
        this.add(mini);
        icon.setSize(50,36);
        icon.setLocation(0, 0);
        ImageIcon iconIcon = new ImageIcon("hoahoc\\icon.png");
        icon.setIcon(iconIcon);
        this.add(icon);
        name.setSize(60,36);
        name.setLocation(55, 0);
        this.add(name);
        picture.setSize(431,213);
        picture.setLocation(422, 0);
        ImageIcon pictureIcon = new ImageIcon("hoahoc\\dc.png");
        picture.setIcon(pictureIcon);
        nen1.add(picture);
        cn[0] = new JPanel();
        cn[0].setSize(190,190);
        cn[0].setLocation(65,62);
        cn[0].setBackground(new Color(106,116,146));
        cn[0].setLayout(null);
        cn[0].setVisible(true);
        cn[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
        nhan[0] = new JLabel("Phân tử khối");
        nhan[1] = new JLabel("Điều chế");
        nhan[2] = new JLabel("Cân bằng");
        nhan[3] = new JLabel("Dự đoán");
        nhan[4] = new JLabel("Nhận biết");
        nhan[5] = new JLabel("Bài toán");
        nhan[0].setSize(100, 15);
        nhan[0].setLocation(55, 20);
        cn[0].add(nhan[0]);
        nen2.add(cn[0]);
        for(int i=1;i<6;i++){
            cn[i] = new JPanel();
            cn[i].setSize(cn[i-1].getHeight(), cn[i-1].getWidth());
            cn[i].setBackground(new Color(106,116,146));
            cn[i].setLayout(null);
            cn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            if(i%2==0){
                cn[i].setLocation(cn[i-1].getX() + 55 + cn[i-1].getHeight(), 62);
                cn[i].setVisible(true);
                nhan[i].setVisible(true);
            }
            else{
                cn[i].setLocation(cn[i-1].getX(), 62);
                cn[i].setVisible(false);
                nhan[i].setVisible(false);
            }
            nhan[i].setSize(100,15);
            nhan[i].setLocation(70, 20);
            cn[i].add(nhan[i]);
            nen2.add(cn[i]);
        }
        right.setSize(32,320);
        right.setLocation(770, 0);
        ImageIcon rightIcon = new ImageIcon("hoahoc\\Forward.png");
        right.setIcon(rightIcon);
        right.setCursor(new Cursor(Cursor.HAND_CURSOR));
        right.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
                for(int i=0; i<6;i++){
                    if(i%2==0){
                        cn[i].setVisible(false);
                        nhan[i].setVisible(false);
                    }
                    else{
                        cn[i].setVisible(true);
                        nhan[i].setVisible(true);
                    }
                }
            }
        });
        nen2.add(right);
        left.setSize(32,320);
        left.setLocation(0, 0);
        ImageIcon leftIcon = new ImageIcon("hoahoc\\Back.png");
        left.setIcon(leftIcon);
        left.setCursor(new Cursor(Cursor.HAND_CURSOR));
        left.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
                for(int i=0; i<6;i++){
                    if(i%2==0){
                        cn[i].setVisible(true);
                        nhan[i].setVisible(true);
                    }
                    else{
                        cn[i].setVisible(false);
                        nhan[i].setVisible(false);
                    }
                }
            }
        });
        nen2.add(left);
        cn[0].addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
               ptk.setVisible(true);
               giaodien.this.setVisible(false);
            }
        });
        cn[1].addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
               dc.setVisible(true);
               giaodien.this.setVisible(false);
            }
        });
        cn[2].addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
               cb.setVisible(true);
               giaodien.this.setVisible(false);
            }
        });
        cn[3].addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
               dd.setVisible(true);
               giaodien.this.setVisible(false);
            }
        });
        cn[4].addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
               nb.setVisible(true);
               giaodien.this.setVisible(false);
            }
        });
        cn[5].addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent m){
               bt.setVisible(true);
               giaodien.this.setVisible(false);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(giaodien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(giaodien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(giaodien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(giaodien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new giaodien().setVisible(true);
            }
        });
    }
    JPanel nen1 = new JPanel();
    JPanel nen2 = new JPanel();
    JLabel exit = new JLabel();
    JLabel mini = new JLabel();
    JLabel icon = new JLabel();
    JLabel name = new JLabel("Chemistry");
    JLabel picture = new JLabel();
    JPanel []cn = new JPanel[6];
    JLabel []nhan = new JLabel[6];
    JLabel right = new JLabel();
    JLabel left = new JLabel();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
