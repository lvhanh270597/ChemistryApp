/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import ChemistryApp.functions;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import knowledge.knowledge;
import process.*;
import static process.timChat.talk;

/**
 *
 * @author Thien Trang
 */
public class chatBotInterface extends javax.swing.JFrame {
    private int weight_ = 15;
    private int height_ = 10;
    private int x_size = 200;
    private int y_size = 50;
    
   
    private String getText;
    private boolean initGame;
    /**
     * Creates new form chatBotInterface
     */
    public chatBotInterface() {
        initComponents();        
    }
    public void initText(String s, int x, int y){
        javax.swing.JTextArea area = new JTextArea();
        area.setOpaque(true);
        area.setText(s);
        area.setLocation(weight_, height_);
        area.setSize(x,y);
        area.setVisible(true);
        height_ += y + 10;
        jPanel3.add(area);
        area.disable();
        repaint();
    }
    public void Bot(){
        java.util.List <String> dsc = new Vector<String>();
        java.util.List <String> temp = new Vector<String>();
        
        functions.buttonClicked = false;
        while (functions.buttonClicked == false){
            System.out.print("");
        }                
        repaint();
        String ans;
        ans = text.getText();
        if(ans.equals("yes")){
            initText("Ban thay quy tim mau gi ? \n 1: Mau do \n 2: Mau xanh \n 3: Khong mau\nHãy chọn một số.....",200,85);
            
            functions.buttonClicked = false;
            while (functions.buttonClicked == false){
                System.out.print("");
            }                
            repaint();
            
            Integer a = Integer.parseInt(text.getText());
            if(a== 1) temp = knowledge.getAllAxit(); 
            else if (a ==2) temp = knowledge.getAllBazo();
            else if (a==3){
                java.util.List<String> temp1 = knowledge.getAllHC();
                for(int i =0 ;i<temp1.size();i++){
                    if(knowledge.getCA(temp1.get(i)).getCation() != "H" && knowledge.getCA(temp1.get(i)).getCation() != "OH")
                        temp.add(temp1.get(i));
                }
            }
        }
        else {
            temp = knowledge.getAllHC();
        }
        for(int i = 0; i < temp.size(); i++)
            dsc.add(temp.get(i));
        temp.clear();
        String []a = {"AgNO3","BaCl2","Ba(OH)2","HCl","H2SO4","Pb(NO3)2","CaCl2","Ca(OH)2"};
        java.util.List<String> u = new Vector<String>();
        for(String i : a){
            u.add(i);
        }
        ///Nhận dạng bằng cách chạy qua các chất trên
        for(int i =0; i< u.size(); i++){
            if(dsc.size() == 1 || dsc.size() == 0)
                initText(dsc.get(0),200,100);
            initText("Ban co "+ u.get(i) + " khong?\nYes or No?",200,35);
            
            functions.buttonClicked = false;
            while (functions.buttonClicked == false){
                System.out.print("");
            }                
            repaint();
            ans = text.getText();
            
            if (ans.equals("yes")){
                initText("Ban co the cho biet no co hien tuong gi khong? \n 1: kết tủa đồng thời có khí \n 2: khí \n 3: kết tủa \n 4: không có hiện tượng\nHãy chọn một số.....",200,100);
                functions.buttonClicked = false;
                while (functions.buttonClicked == false){
                    System.out.print("");
                }                
                repaint();
                Integer t = Integer.parseInt(text.getText());
                if( t == 1){
                    /* Lấy các chất vừa có kết tủa vừa có khí*/
                    initText("Ban cho toi biet ket tua mau gi?\nHãy nhập tên màu.....",200,35);
                    functions.buttonClicked = false;
                    while (functions.buttonClicked == false){
                        System.out.print("");
                    }                
                    repaint();
                    ans = text.getText();
                    
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b != null && c != null) // có kết tủa và có khí
                            if(knowledge.color(b).equals(ans)) // màu giống màu kết tủa
                                temp.add(dsc.get(j));
                    }
                }
                if(t == 2){
                    /*Lấy các chất có khí*/
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b == null && c != null) // có khí
                            temp.add(dsc.get(j));
                    }
                }
                if(t == 3){
                    /*Lấy các chất có kết tủa*/
                    initText("Ban cho toi biet ket tua mau gi?\nHãy nhập tên màu.....",200,35);
                    functions.buttonClicked = false;
                    while (functions.buttonClicked == false){
                        System.out.print("");
                    }                
                    repaint();
                    ans = text.getText();
                    
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b != null && c == null) // có kết tủa
                           // System.out.println(knowledge.color(b) + " " + in);
                            if(knowledge.color(b).equals(ans)){ // màu giống màu kết tủa
                                temp.add(dsc.get(j));
                              //  System.out.println("a");
                            }
                    }
                   // System.out.println(temp);
                }
                if(t == 4){
                    /*Lấy các chất không có hiện tượng*/
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b == null && c == null)
                            temp.add(dsc.get(j));
                    }
                }
                dsc.clear();
                for(int g = 0; g < temp.size(); g++)
                    dsc.add(temp.get(g));
                temp.clear();
            }
        }
        initText(dsc.get(0),200,100);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textMouseClicked(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        javax.swing.JTextArea area = new JTextArea();
        area.setOpaque(true);
        area.setText(text.getText());
        area.setLocation(weight_, height_);
        area.setSize(20, 20);
        area.setVisible(true);
        area.disable();
        height_ += 30;
        jPanel4.add(area);               
                
        functions.buttonClicked = true;
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textMouseClicked
        // TODO add your handling code here:
        text.setText("");
    }//GEN-LAST:event_textMouseClicked

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
            java.util.logging.Logger.getLogger(chatBotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chatBotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chatBotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chatBotInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chatBotInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField text;
    // End of variables declaration//GEN-END:variables
}
