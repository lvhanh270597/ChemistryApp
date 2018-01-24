/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javafx.util.Pair;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import process.canBang;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import knowledge.*;
import math.PwL;
import process.ptk;
/**
 *
 * @author Thien Trang
 */
public class canBangF extends javax.swing.JFrame implements KeyListener{

    public static canBang a = new canBang();
    private JPanel panel = (JPanel) getContentPane();
    
    public canBangF() {
        initComponents();
        initUI();        
        initEvent();               
    }
    
    private void initEvent(){        
        input.addKeyListener((KeyListener) this);        
    }
    
    private void initUI(){
        setTitle("Cân bằng");
        initImage(jLabel7,"images/canbang1.png");
    }
    
    private void initImage(JLabel x, String path){
        ImageIcon ii = new ImageIcon(path);
        Image image = ii.getImage().getScaledInstance(x.getWidth(), x.getHeight(), Image.SCALE_SMOOTH);
        x.setIcon(new ImageIcon(image));
    }
    
    public void keyTyped(KeyEvent arg0) {
        
    }
    public void keyPressed(KeyEvent arg0) {               
        
    }

    public void keyReleased(KeyEvent arg0) {
        int key = arg0.getKeyCode();
        System.out.println("Key Pressed " + key);                
        
        if (key == 8){
            return;
        }
        if (key == 37) return;
        if (key == 16) return;
                        
        String X = input.getText();
        
        int pos_space = index_char(X, ' ');
        
        String Front = "";
        if (pos_space > -1) Front = X.substring(0, pos_space);
        
        X = X.substring(pos_space + 1, X.length());
        
        System.out.println(X);
        
        int id = X.length();                           
        
        if (X.length() == 0){
            return ;
        }        
                        
        String m = match(X);
        System.out.println("Match : " + m);
        if (m != null){                    
            input.setText(Front + " " + m);                        
            input.setSelectionStart(Front.length() + 1 + id);                                      
        }                                              
    }

    private int index_char(String X, char c){
        if (X == null) return -1;
        if (X.length() == 0) return -1;        
        int i;
        for (i=X.length() - 1; i>=0; i--){
            if (X.charAt(i) == c){
                return i;
            }
        }
        return i;
    }
    
    private String match(String X){
        String res  = null;
        int m = 100;
        int d = X.length();
        for (String st : knowledge.allOfHC_tan){
            if (st.length() < d) continue;
            String tmp = st.substring(0, d);
            if (tmp.equals(X)){
                if (m > st.length()){
                    m = st.length();     
                    res = st;
                }
            }
        }
        return res;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        input = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        output = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        yes = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nhập phương trình:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));
        jPanel2.add(input, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 342, 40));

        jButton1.setText("Cân bằng");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 142, -1, -1));

        jLabel2.setText("Kết quả:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, -1, -1));
        jPanel2.add(output, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 342, 32));

        jLabel3.setText("Cân bằng electron:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        yes.setText("Có");
        yes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yesMouseClicked(evt);
            }
        });
        jPanel2.add(yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, -1, -1));

        jButton2.setText("Hướng dẫn");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 112, 633, 290));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 110));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Pair<String, String> binarySplit(String s){
        int index_equals = index_char(s, '=');
        
        if (index_equals == -1){            
            return null;
        }
        
        String Front = s.substring(0, index_equals);
        String Last = s.substring(index_equals + 1);
                
        String begin = "", end = "";
        
        for (int i=0; i<Front.length(); i++){
            char c = Front.charAt(i);
            if (c != ' ') begin += c;            
        }
                
        for (int i=0; i<Last.length(); i++){
            char c = Last.charAt(i);
            if (c != ' ') end += c;
        }
        
        Pair <String, String> ss = new Pair<String, String>(begin, end);
        
        return ss;
    }
    
    private String makeBeautiful(String [] v1, String[] v2){
        String res = "";
        for (int i=0; i<v1.length - 1; i++) res += v1[i] + " + ";
        res += v1[v1.length - 1] + " = ";
        for (int i=0; i<v2.length - 1; i++) res += v2[i] + " + ";
        res += v2[v2.length - 1];
        return res;
    }
    
    private String[] split(String X){
        String[] v = X.split("\\+");
        Vector <String> res = new Vector<String>();
        for (String st : v){
            if(st == null || st.length() == 0) continue;
            res.add(st);
        }
        String[] r = new String[res.size()];
        for (int i=0; i<res.size(); i++)
            r[i] = res.get(i);
        return r;
    }
    
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        
        String s = input.getText();                
        
        Pair <String, String> p = binarySplit(s);
        // Nhập sai cú pháp (thiếu dấu = )
        if (p == null){
            JOptionPane.showMessageDialog(
                        panel, 
                        "Bạn cần nhập đúng cú pháp của phương trình hóa học.\nPhải có dấu \"=\" ngăn" +
                        " cách giữa chất tham gia và chất sản phẩm", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        
        String begin = p.getKey(), end = p.getValue();        
          
        List <String> hc = knowledge.allOfHC_tan;
        
        String[] v1 = split(begin);
        
        String[] v2 = split(end);                
        
        for (int i=0; i<v1.length; i++){
            System.out.println(i + ":" + v1[i]);
        }            
        
        // Chất tham gia bị rỗng        
        if (v1.length == 0){
            JOptionPane.showMessageDialog(
                        panel, 
                        "Chất tham gia chưa nhập. Mời nhập.",
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
        }
        // Chất sản phẩm bị rỗng
        if (v2.length == 0){
            JOptionPane.showMessageDialog(
                        panel, 
                        "Chất sản phẩm chưa nhập. Mời nhập.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
        }
        // Nhập sai chất tham gia
        for (String st : v1){
            if (!PwL.checkIn(st, hc)){
                JOptionPane.showMessageDialog(
                        panel, 
                        "Chương trình chưa hiểu chất " + st + " là chất gì.\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        // Nhập sai chất sản phẩm
        for (String st : v2){
            if (!PwL.checkIn(st, hc)){
                JOptionPane.showMessageDialog(
                        panel, 
                        "Chương trình chưa hiểu chất " + st + " là chất gì.\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        
        System.out.println(makeBeautiful(v1, v2));
        
        // Bảo toàn nguyên tố
        Vector <String> L1 = new Vector<String>();
        for (String st : v1){
            for (String e : ptk.split(st).keySet()){
                if (!PwL.checkIn(e, L1)) L1.add(e);
            }
        }
        Vector <String> L2 = new Vector<String>();
        for (String st : v2){
            for (String e : ptk.split(st).keySet()){
                if (!PwL.checkIn(e, L2)) L2.add(e);
            }
        }
        for (String st : L1){
            if (!PwL.checkIn(st, L2)){
                JOptionPane.showMessageDialog(
                        panel, 
                        "Nguyên tố " + st + " chỉ có trong tham gia mà không có trong sản phẩm.\n" + 
                        "Theo định luật bảo toàn nguyên tố. Điều này không đúng.\n" +
                        "\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        for (String st : L2){
            if (!PwL.checkIn(st, L1)){
                JOptionPane.showMessageDialog(
                        panel, 
                        "Nguyên tố " + st + " chỉ có trong sản phẩm mà không có trong tham gia.\n" + 
                        "Theo định luật bảo toàn nguyên tố. Điều này không đúng.\n" +
                        "\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        
        // Kiểm tra số oxi hóa
        List <String> names;
        List <Integer> oxh;
        
        Pair <List<String>, List<Integer>> pp = knowledge.getAllOxhList(v1);
        names = pp.getKey();
        oxh = pp.getValue();
        
        
        List <String> _names;
        List <Integer> _oxh;
        
        pp = knowledge.getAllOxhList(v2);
        
        _names = pp.getKey();
        _oxh = pp.getValue();
        
        String cho, nhan;
        Pair <String, String> chonhan = knowledge.chatChoNhan(names, oxh, _names, _oxh);
        cho = chonhan.getKey();
        nhan = chonhan.getValue();
        System.out.println(chonhan);
        if (cho == null && nhan != null){
            JOptionPane.showMessageDialog(
                        panel, 
                        "Chất nhận electron là " + nhan + " nhưng lại không có chất cho electron\n" + 
                        "Nên phương trình này chưa đúng.\n" +
                        "\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
        }
        
        if (cho != null && nhan == null){
            JOptionPane.showMessageDialog(
                        panel, 
                        "Chất cho electron là " + cho + " nhưng lại không có chất nhận electron\n" + 
                        "Nên phương trình này chưa đúng.\n" +
                        "\nCó lẽ bạn nên check lại.", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
        }
        
       
        s = makeBeautiful(v1, v2);
        
        if(!yes.isSelected()){
            if (cho != null){
                JOptionPane.showMessageDialog(
                        panel, 
                        "Đây là phương trình có cho và nhận election.\nBạn nên thực hiện cân bằng electron", 
                        "Thông báo", 
                         JOptionPane.INFORMATION_MESSAGE);
                return;      
            }
            else{
                // Kiểm tra nếu sản phẩm không đúng.
                String thamgia = "";
                for (String st : v1) thamgia += st + " ";
                List <String> p1 = phanUng.phanUng(thamgia);
                List <String> p2 = PwL.convertToList(v2);
                if (PwL.equals(p1, p2)){
                    output.setText(a.canbang(s));
                }
                else{
                    
                    String sp = "";
                    for (String st : p1) sp += st + " "; 
                    
                    JOptionPane.showMessageDialog(
                        panel, 
                        "Phương trình này chưa đúng.\nCó lẽ bạn nên check lại.\n" + 
                        "Sản phẩm có thể là: " + sp,                        
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            }
        }
        else{
            String t = a.canBangElectron(s);
            if(t == null){
                JOptionPane.showMessageDialog(
                        panel, 
                        "Phương trình này không có sự trao đổi electrion nên không cân bằng " + 
                        "theo kiểu cân bằng e đươc." + 
                        ".\nBạn nên thực hiện cân bằng theo cách bình thường", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }                 
            output.setText(t);
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void yesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesMouseClicked
                
    }//GEN-LAST:event_yesMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
       hdCanBang b = new hdCanBang();
       b.initText(a.huongdan);
       b.show();
    }//GEN-LAST:event_jButton2MouseClicked

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
            java.util.logging.Logger.getLogger(canBangF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(canBangF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(canBangF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(canBangF.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new canBangF().setVisible(true);
            }
        });
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField input;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField output;
    private javax.swing.JRadioButton yes;
    // End of variables declaration//GEN-END:variables
}
