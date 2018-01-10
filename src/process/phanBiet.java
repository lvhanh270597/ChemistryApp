/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import knowledge.phanUng;
import java.util.*;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author Hanh
 */
public class phanBiet {
    private List<String> chuoi;
    private List<String> ds;
    private boolean quy;
    public phanBiet(){}
    public phanBiet(String X, boolean quy){
        this.chuoi = new Vector<String>();
        String[] v = X.split("\\s");
        for (String st : v){
            this.chuoi.add(st);
        }
        this.quy = quy;
        this.ds = new Vector<String>();
        String[] u = {"AgNO3", "BaCl2", "Ba(OH)2", "HCl", "H2SO4", "Pb(NO3)2", "CaCl2", "Ca(OH)2"};
        for (String st : u){
            this.ds.add(st);
        }
    }
    
    
    public void process(){
        execute(this.chuoi, this.quy);
    }
    
    private boolean unique(List <String> v){
        int n = v.size();        
        for (int i=0; i<n; i++) 
            if (v.get(i) == null) return false;
        for (int i=0; i<n - 1; i++){
            for (int j=i + 1; j<n; j++)
                if (v.get(i).equals(v.get(j))){
                    return false;
                }
        }
        return true;
    }            
    
    public void execute(List <String> v, boolean quy){                     
        System.out.println("---------------------------------------------------------");
        if (v.size() == 0) return ;              
        
        System.out.print("Cần phân biệt các chất: ");
        for (String st : v)
            System.out.print(st + " ");
        System.out.println();
        
        // phan biet bang mau
        List <String> mau = knowledge.knowledge.getColor(v);
        if (unique(mau)){
            System.out.println("Các chất cần phân biệt có màu khác nhau, nên có thể phân biệt được. ");
            for (int i=0; i<v.size(); i++){
                System.out.println(v.get(i) + " : " + mau.get(i));
            }
            return ;
        }
        
        List <String> X = new Vector<String>(); //axit  //khong hien tuong
        List <String> Y = new Vector<String>(); //bazo  //ket tua
        List <String> Y_T = new Vector<String>();       //chat ket tua
        List <String> Z = new Vector<String>(); //muoi  //khi
        List <String> Z_T = new Vector<String>();       //chat khi
        List <String> T = new Vector<String> ();        //ket tua va khi
        List <String> T_T = new Vector<String>();       //luu va ket tua va khi
        if (quy){
            for (String st : v){
                if (knowledge.knowledge.axit(st)){
                    X.add(st);
                }
                else
                    if (knowledge.knowledge.bazo(st)){
                        Y.add(st);
                    }
                    else{
                        Z.add(st);
                    }
            }            
            int cnt = X.size() == 0 ? 0 : 1;
            cnt += Y.size() == 0 ? 0 : 1;
            cnt += Z.size() == 0 ? 0 : 1;
            if (cnt > 1) {
                System.out.println("Dùng quỳ tím, ta chia làm " + cnt + " nhóm để phân biệt tiếp.");
                if (X.size() > 0){
                    System.out.print("Nhóm gồm chất hóa đỏ là: " );                    
                    for (String st : X) System.out.print(st + " ");
                    System.out.println();
                }
                if (Y.size() > 0){
                    System.out.print("Nhóm gồm chất hóa xanh là: " );                    
                    for (String st : Y) System.out.print(st + " ");
                    System.out.println();
                }
                if (Z.size() > 0){
                    System.out.print("Nhóm gồm chất không đổi màu là: " );                    
                    for (String st : Z) System.out.print(st + " ");
                    System.out.println();
                }
                execute(X, false);
                execute(Y, false);
                execute(Z, false);              
                return ;
            }       
            
            X.clear();
            Y.clear();
            Z.clear();
        }               
        
        for (String thuocthu : this.ds){
            for (String st : v){
                List <String> pu = phanUng.phanUng(thuocthu + " " + st);
                String kt = knowledge.knowledge.ketTua(pu);
                String kh = knowledge.knowledge.khi(pu);
                
                if (kh == null && kt != null){
                    Y.add(st);
                    Y_T.add(kt);                    
                }
                if (kh != null && kt == null){
                    Z.add(st);
                    Z_T.add(kh);
                }
                if (kh != null && kt != null){
                    T.add(st);
                    T_T.add(kh + " va " + kt);
                } 
                if (kh == null && kt == null){
                    X.add(st);
                }                
            }
            
            int cnt = X.size() == 0 ? 0 : 1;
            cnt += Y.size() == 0 ? 0 : 1;
            cnt += Z.size() == 0 ? 0 : 1;
            cnt += T.size() == 0 ? 0 : 1;

            if (cnt == 1){
                X.clear();
                Y.clear();
                Z.clear();
                T.clear();
                Y_T.clear();
                Z_T.clear();
                T_T.clear();
                continue;
            }
            
            System.out.println("Chọn thuốc thử là " + thuocthu);
            System.out.println("Ta chia tập hợp này thành " + cnt + " nhóm...");
            
            boolean ok = false;
            
            if (X.size() > 0){            
                System.out.print("Chất phản ứng không hiện tượng: ");
                for (String st : X){
                    System.out.print(st + " ");                
                }
                System.out.println();
                if (X.size() == 1){
                    System.out.println("Đã phân biệt được " + X.get(0));
                }
            }
            if (Y.size() > 0){                
                List <String> colors = knowledge.knowledge.getColor(Y_T);                
                ok = unique(colors);
                System.out.println("Chất phản ứng tạo ra kết tủa: ");
                for (int i=0; i<Y.size(); i++){
                    String st = Y.get(i);
                    String kt = Y_T.get(i);
                    String cl = colors.get(i);
                    System.out.println("chất cho " + st + " kết tủa " + kt + " có màu " + cl);                
                }                     
                if (Y.size() == 1){
                    System.out.println("Đã phân biệt được " + Y.get(0));
                }
            }
            if (Z.size() > 0){                                
                System.out.println("Chất phản ứng tạo ra khí: ");
                for (int i=0; i<Z.size(); i++){
                    String st = Z.get(i);
                    String kh = Z_T.get(i);
                    System.out.println("chất: " + st + " xuất hiện khí là: " + kh);                
                }   
                if (Z.size() == 1){
                    System.out.println("Đã phân biệt được " + Z.get(0));
                }
            }
            if (T.size() > 0){
                System.out.println("Chất phản ứng tạo kết tủa và khí: ");
                for (int i=0; i<T.size(); i++){
                    String st = T.get(i);
                    String kt_va_khi = T_T.get(i);
                    System.out.println("chất " + st + " tạo ra kết tủa và khí là: " + kt_va_khi);                
                }   
                if (T.size() == 1){
                    System.out.println("Đã phân biệt được " + T.get(0));
                }
            }

            if (X.size() > 0){            
                if (X.size() > 1)
                    execute(X, false);            
            }
            if (Y.size() > 0){
                if (!ok) execute(Y, false);
                else{
                    if (Y.size() > 1){
                        System.out.print("Đã phân biệt được ");
                        for (String st : Y) System.out.print(st + " ");
                        System.out.println();
                    }
                }
            }
            if (Z.size() > 0){
                if (Z.size() > 1){
                    execute(Z, false);
                }
            }
            if (T.size() > 0){
                if (T.size() > 1){
                    execute(T, false);
                }
            }
            
            return ;
        }
        
        System.out.print("Không phân biệt được: ");        
        for (String st : v) System.out.print(st + " ");
        System.out.println();
        
    }
}
