/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import ChemistryApp.functions;
import com.sun.glass.events.KeyEvent;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import knowledge.*;
import math.PwL;
import math.sh;
import java.util.List;
import knowledge.phanUng;
import math.LCS;
import math.hopchat;

/**
 *
 * @author Thien Trang
 */
public class chatBotInterface extends javax.swing.JFrame {
    private int weight_ = 50;
    private int height_ = 10;
    private int x_size = 200;
    private int y_size = 50;
    
   
  //  private String getText;
   // private boolean initGame;
    private int xScroll = 5000;
    /**
     * Creates new form chatBotInterface
     */
   
    public chatBotInterface() {
        initComponents();
        setTitle("Chat bot");
        initText("Xin chào!!!",70,35);
        initText("Để nhận biết một chất chưa rõ ",180,35);
        initText("Bạn hãy trả lời những câu hỏi của tôi",220,35);
        initText("Bạn có quỳ tím không? Có hay không?",220,35);
        initText("Xin trả lời không dấu VD: có trả lời co, không trả lời khong",330,35);
        initScroll();
    }
    public void initText(String s, int x, int y){
        
        x = sh.max(100, s.length() * 7);
        
        javax.swing.JLabel area = new JLabel();
        area.setOpaque(true);
        area.setText(s);
        area.setFont(new Font("Helvetica", Font.PLAIN, 12));
        area.setLocation(weight_, height_);
        area.setSize(x,y);
        area.setVisible(true);
        initAva(height_);
        height_ += y + 10;
        jPanel3.add(area);
    }
    public void initAva(int x){
        javax.swing.JLabel ava = new JLabel();
        ava.setOpaque(true);
        ava.setIcon(new javax.swing.ImageIcon("hoahoc/Bot.png"));
        ava.setLocation(5, x);
        ava.setSize(35,35);
        ava.setBackground(Color.white);
        ava.setVisible(true);
        jPanel3.add(ava);
        repaint();
    }
    public void initScroll(){
        jScrollPane1 =  new JScrollPane(jPanel3);  
        getContentPane().setLayout(null);
        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 50, 530, 503);
        jPanel3.setPreferredSize(new Dimension(100,jPanel3.getHeight()+ xScroll));
    }
    
    public void waitBot(){
        functions.buttonClicked = false;
        while (functions.buttonClicked == false){
            System.out.print("");
        }                
        repaint();
    }
    
    public void Bot(){
        java.util.List <String> dsc = new Vector<String>();
        java.util.List <String> temp = new Vector<String>();
      
        waitBot();
        String ans;
        ans = text.getText();
        while(!ans.equals("co") && !ans.equals("khong")){
           initText("Bạn vui lòng nhập đúng câu trả lời",200,35); 
           waitBot();
           ans = text.getText();
        }
        List<String> u = Arrays.asList("AgNO3","BaCl2","Ba(OH)2","HCl","H2SO4","Pb(NO3)2","CaCl2","Ca(OH)2");
        List<String> v = new Vector<String>();
        if(ans.equals("co")){
            initText("Bạn thấy quỳ tím màu gì?",160,35);
            initText("1: Màu đỏ 2: Màu xanh 3: Không màu",220,35);
            initText("Bây giờ bạn hãy chọn một số.....",200,35);
            waitBot();
                        
        //--hanh
            
            boolean ok = knowledge.isNumeric(text.getText());
            int a = 0;
            if (ok){
                a = Integer.parseInt(text.getText());
                if (a != 1 && a != 2 && a != 3) ok = false;
            }
            while (!ok){
                initText("Bạn vui lòng nhập đúng câu trả lời",200,35); 
                waitBot();
                ok = knowledge.isNumeric(text.getText());                                
                if (ok){
                    a = Integer.parseInt(text.getText());
                    if (a != 1 && a != 2 && a != 3) ok = false;
                }
            }                                                                        
                        
            if(a==1){
                temp = knowledge.getAllAxit();
                for(int j = 0; j <u.size(); j++){
                    if(!knowledge.axit("'"+u.get(j)+"'"))
                        v.add(u.get(j));
                }
            } 
            else if (a ==2){
                temp = knowledge.getAllBazo();
                for(int j = 0; j <u.size(); j++){
                    hopchat x = knowledge.getCA(u.get(j));
                    if(!knowledge.bazo("'"+u.get(j)+"'") && !(x.getAnion().equals("'Cl'")))
                        v.add(u.get(j));
                }
            }
            else if (a==3){
                temp = knowledge.getAllMuoi();
                v = u;
            }
        }
        else {
            temp = knowledge.getAllHC();
            v = u;
        }
        u = v;
        
        dsc = PwL.copy(temp);                
        
        temp.clear();                        
        
        
        
        ///Nhận dạng bằng cách chạy qua các chất trên
        for(int i =0; i< u.size(); i++){                                              
            initText("Tôi đã khoanh vùng được khoảng " + dsc.size() + " chất", 240, 35);
            initText("Các chất điển hình là: ", 200, 35);
            for (int k=0; k<sh.min(3, dsc.size()); k++) initText(dsc.get(k), 50, 35);
            if(dsc.size() == 1){
                initText("Tôi nghĩ chất đó có thể là:.........",200,35);
                initText(dsc.get(0),200,35);
                return;
            }
            if ( dsc.size() == 0){
                initText("Bạn đùa tôi rồi. Chẳng có chất nào như vậy cả.", 270, 35);                
                return;
            }
            
            initText("Bạn có "+ u.get(i) + " không? Có hay không?",220,35);
            initText("Xin trả lời không dấu VD: có trả lời co, không trả lời khong",330,35);
            waitBot();
            ans = text.getText();
            while(!ans.equals("co") && !ans.equals("khong")){
                initText("Bạn vui lòng nhập đúng câu trả lời",200,35); 
                waitBot();
                ans = text.getText();
            } 
            if (ans.equals("co")){
                initText("Bạn có thể cho biết nó có hiện tượnng gì không?",280,35);
                initText("1: kết tủa đồng thời có khí   2: khí",200,35);
                initText("3: kết tủa    4: không có hiện tượng",200,35);
                initText("Bây giờ bạn hãy chọn một số.....",200,35);
                waitBot();
                
                // hanh
                boolean ok = knowledge.isNumeric(text.getText());
                int t = 0;
                if (ok){
                    t = Integer.parseInt(text.getText());
                    if (t != 1 && t != 2 && t != 3 && t != 4) ok = false;
                }
                while (!ok){
                    initText("Bạn vui lòng nhập đúng câu trả lời",200,35); 
                    waitBot();
                    ok = knowledge.isNumeric(text.getText());                                
                    if (ok){
                        t = Integer.parseInt(text.getText());
                        if (t != 1 && t != 2 && t != 3 && t != 4) ok = false;
                    }
                }   
                
                if( t == 1){
                    /* Lấy các chất vừa có kết tủa vừa có khí*/
                    initText("Bạn cho tôi biết kết tủa màu gì?",200,35);
                    initText("Hãy nhập tên màu bằng tiếng anh.....",210,35);
                    waitBot();
                    ans = text.getText();
                    int[] lcs = new int[dsc.size()];
                    List<String> colr = new Vector<String>();
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b != null && c != null){// có kết tủa và có khí
                            lcs[j] = (new LCS(ans, knowledge.color(b))).lcs();
                            colr.add(knowledge.color(b));
                        }
                        else{
                            colr.add(null);
                        }
                    }
                    List <Integer> items = sh.argMax(lcs, 3);
                    List<String> col = new Vector<String>();
                    for (int j=0; j<items.size(); j++){          
                        int index = items.get(j);
                        int w = 0;
                        for(int k = 0; k < col.size(); k++){
                            if(col.get(k).equals(colr.get(index))){
                                w = 1;
                                break;
                            }
                        }
                        if(w == 0)
                            col.add(colr.get(index));
                    }
                    initText("Có phải ý bạn là:.........",150,35);
                    for(int j = 0; j< col.size(); j++)
                        initText(j + ": " + col.get(j),150,35);
                    initText("Hãy chọn một số.....",150,35);
                    ok = knowledge.isNumeric(text.getText());
                    int k = 0;
                    if (ok){
                        k = Integer.parseInt(text.getText());
                        if (k != 1 && k != 2 && k != 3 && k != 4) ok = false;
                    }
                    while (!ok){
                        initText("Bạn vui lòng nhập đúng câu trả lời",200,35); 
                        waitBot();
                        ok = knowledge.isNumeric(text.getText());                                
                        if (ok){
                            k = Integer.parseInt(text.getText());
                            if (k != 1 && k != 2 && k != 3 && k != 4) ok = false;
                        }
                    }   
                
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b != null && c != null){// có kết tủa và có khí
                            if(knowledge.color(b).equals(col.get(k)))
                               temp.add(dsc.get(j));
                        }
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
                    initText("Bạn cho tôi biết kết tủa màu gì?",200,35);
                    initText("Hãy nhập tên màu bằng tiếng anh.....",210,35);
                    waitBot();
                    ans = text.getText();
                    int[] lcs = new int[dsc.size()];
                    List<String> colr = new Vector<String>();
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b != null && c == null){// có kết tủa
                            lcs[j] = (new LCS(ans, knowledge.color(b))).lcs();
                            colr.add(knowledge.color(b));
                        }
                        else{
                            colr.add(null);
                        }
                    }
                    for(int j = 0; j< lcs.length; j++)
                         System.out.print(lcs[j] + " ");
                    List <Integer> items = sh.argMax(lcs, 3);
                    List<String> col = new Vector<String>();
                    for (int j=0; j<items.size(); j++){          
                        int index = items.get(j);
                        int w = 0;
                        for(int k = 0; k < col.size(); k++){
                            if(col.get(k).equals(colr.get(index))){
                                w = 1;
                                break;
                            }
                        }
                        if(w == 0)
                            col.add(colr.get(index));
                    }
                    initText("Có phải ý bạn là:.........",150,35);
                    for(int j = 0; j< col.size(); j++)
                        initText(j + ": " + col.get(j),150,35);
                    initText("Hãy chọn một số.....",150,35);
                    ok = knowledge.isNumeric(text.getText());
                    int k = 0;
                    if (ok){
                        k = Integer.parseInt(text.getText());
                        if (k != 1 && k != 2 && k != 3 && k != 4) ok = false;
                    }
                    while (!ok){
                        initText("Bạn vui lòng nhập đúng câu trả lời",200,35); 
                        waitBot();
                        ok = knowledge.isNumeric(text.getText());                                
                        if (ok){
                            k = Integer.parseInt(text.getText());
                            if (k != 1 && k != 2 && k != 3 && k != 4) ok = false;
                        }
                    }  
                    for(int j = 0; j < dsc.size(); j++){
                        String b = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKT(); 
                        String c = knowledge.getKetTua_Khi(dsc.get(j) + " " + u.get(i)).getKhi();
                        if(b != null && c == null){// có kết tủa
                            if(knowledge.color(b).equals(col.get(k)))
                               temp.add(dsc.get(j));
                        }
                    }
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
        initText("Tôi nghĩ chất đó có thể là:.........",200,20);
        for(int i = 0; i< dsc.size(); i++)
            initText(dsc.get(i),50,35);
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        text = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("ChatBot");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textMouseClicked(evt);
            }
        });
        text.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textKeyPressed(evt);
            }
        });

        jButton1.setText("Send");
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
                .addContainerGap(78, Short.MAX_VALUE))
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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        javax.swing.JLabel area = new JLabel();
        area.setOpaque(true);
        area.setText(text.getText());
        area.setLocation(450, height_);
        area.setSize(50, 20);
        area.setVisible(true);
        //area.disable();
        height_ += 30;
        jPanel3.add(area);
        functions.buttonClicked = true;
      //  repaint();
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textMouseClicked
        // TODO add your handling code here:
        text.setText("");
    }//GEN-LAST:event_textMouseClicked

    private void textKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            javax.swing.JLabel area = new JLabel();
        area.setOpaque(true);
        area.setText(text.getText());
        area.setLocation(450, height_);
        area.setSize(30, 20);
        area.setVisible(true);
       // text.setText("");
        //area.disable();
        height_ += 30;
        jPanel3.add(area);
        functions.buttonClicked = true;
        }
    }//GEN-LAST:event_textKeyPressed

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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chatBotInterface().setVisible(true);
            }
        });
    }
    private javax.swing.JScrollPane jScrollPane1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField text;
    // End of variables declaration//GEN-END:variables
}
