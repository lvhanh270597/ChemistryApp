/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;
import math.LCS;
import math.sh;
import process.hienTuong;

/**
 *
 * @author hanh
 */
public class hienTuongForm extends javax.swing.JFrame implements KeyListener {

        
    /**
     * Creates new form hienTuong
     */                
    private Cup _cup;
    private String addition;
    private Color addColor;    
    
    
    public hienTuongForm() {
        initComponents();
        
        initVars();
        
        initEvent();        

        setTitle("Hiện tượng");
    }

    private void initEvent(){
        editor.addKeyListener((KeyListener)this);
               
    }
    
    private void initVars(){
        ImageIcon ii = new ImageIcon("hoahoc/nhanbiet.png");
        Image i = ii.getImage().getScaledInstance(title.getWidth(), title.getHeight(), Image.SCALE_SMOOTH);
        title.setIcon(new ImageIcon(i));
        
        setTitle("Simulation");
        
        _cup = new Cup(170, 80, 205, 255);
        
        add(_cup);
                
    }        
    
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title2 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        editor = new javax.swing.JTextField();
        combo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        info = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pthh = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(56, 157, 216));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboItemStateChanged(evt);
            }
        });
        combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboMouseClicked(evt);
            }
        });
        combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jButton1.setText("Add");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        pthh.setColumns(20);
        pthh.setRows(5);
        jScrollPane1.setViewportView(pthh);

        jLabel1.setText("CTHH:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(407, 407, 407)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(editor)
                                .addComponent(combo, 0, 130, Short.MAX_VALUE))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(435, 435, 435)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(title2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboActionPerformed
        if (combo.getSelectedItem() == null) return;
        String X = combo.getSelectedItem().toString();
        
        addition = X;
        String cl = knowledge.knowledge.color(X);
        System.out.println(cl);
        info.setText(X + " : " + cl);  
        addColor = knowledge.knowledge.colors.get(cl);
    }//GEN-LAST:event_comboActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (addColor == null){
            addColor = Color.white;
        }
        System.out.println(addition + " " + addColor.toString());
        addItem(addition, addColor);    
        _cup.refresh();
        //refresh();
    }//GEN-LAST:event_jButton1MouseClicked

    private void comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboItemStateChanged

    }//GEN-LAST:event_comboItemStateChanged

    private void comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboMouseClicked
        
    }//GEN-LAST:event_comboMouseClicked

    private void addItem(String X, Color c){
        _cup.addItem(X, c);
        
        hienTuong ht = _cup.getHT();
        
        if (ht == null){            
            String t = "Các chất có trong dung dịch là: ";
            t += X;
            pthh.setText(t);            
        }
        else{
            
            String t="";
                                                      
            List <String> kt = ht.get_kettua();
                 
            if (kt.size() > 0){
                t += "Các chất kết tủa là: ";
                for (String k : kt) t += k + " ";  
                t += "\n";
            }
                                                
            List <String> khi = ht.get_khi();
            if (khi.size() > 0){        
                
                t += "Các chất khí là: ";
                
                for (String k : khi){
                    t += k + " ";
                }                        
                t += "\n";
                
                add(new sinhkhi(_cup.getX() + 10, _cup.getY() - 100, _cup.getWidth() - 30, 200));
                            
            }
            
            String tmp = "";
            
            for (String st : ht.get_cl()){
                tmp += st + " ";
            }           
            
            _cup.del_kt_k();
            
            t += "Các chất có trong dung dịch là: ";            
            pthh.setText(t + tmp);
        }        
    }
    
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
            java.util.logging.Logger.getLogger(hienTuongForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hienTuongForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hienTuongForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hienTuongForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new hienTuongForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo;
    private javax.swing.JTextField editor;
    private javax.swing.JLabel info;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea pthh;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent arg0) {
        int key = arg0.getKeyCode();        
        
        String X = editor.getText();                
        
        if (X.length() == 0){
            return ;
        }
        
        List <String> names = knowledge.knowledge.allOfHC;
        
        int[] lcs = new int[names.size()];
        for (int i=0; i<names.size(); i++){
            String Y = names.get(i);            
            lcs[i] = (new LCS(X, Y)).lcs();                        
        }               
        
        combo.removeAllItems();                
        
        List <Integer> items = sh.argMax(lcs, 3);
        for (int i=0; i<items.size(); i++){          
            int index = items.get(i);
            combo.addItem(names.get(index));
        }
        
        if (X.length() > 0) combo.addItem(X);                
             
    }       
    
}
