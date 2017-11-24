
package chemistryapp;

import Basic.*;
import Math.*;
import java.io.*;
import java.util.*;
import javafx.util.*;
import knowledge.knowledge;
import Interface.*;
import java.util.LinkedList;
import process.*;
import Interface.*;
public class ChemistryApp {    
    public static void print(String X){
        System.out.print(X);
    }
    
    public static void main(String[] args) {          
        knowledge.readData();
       /* String x = "S -> SO2 -> Na2SO3 -> Na2SO4 -> O2 -> ZnO -> Zn(NO3)2 -> Zn";
        String y = "";// các chất được tác dụng
        Vector vt = dieuChe.DieuChe(x,y);
        for(int i=0;i<vt.size();i++){
            System.out.println(vt.get(i));
        }
        //DonChat a = knowledge.donChat.get("Fe_1");  
        */
       
        
        /*System.out.println(p.predict()); 
        //p.predict();
        System.out.println(p.getPTHH());
        */
        /*System.out.println("du doan");
        String thamGia = "K H2O H2SO4 FeCl2";
        duDoan dd = new duDoan(thamGia);
        List <String> rs = dd.deduce();
        for (int i=0; i<rs.size(); i++){
            System.out.println(rs.get(i));
        }
        System.out.println("can bang");
     //   giaodien gd = new giaodien();
     //   gd.show();*/
        /*String s = "Fe + HNO3 = Fe(NO3)3 + NO + N2O + NO2 + N2 + H2O";
        pu p = new pu(s);
        System.out.println(p.getPTHH());        
        System.out.println("dieu che"); 
        //String s = "Fe -> FeSO4 -> FeCl2 -> FeCl3 -> Fe(OH)3 -> Fe2O3 -> Fe";
        s = "BaCl2 -> BaSO3 -> SO2 -> H2SO4 -> Fe2(SO4)3 -> Fe(OH)3 -> Fe2O3 -> FeCl3 -> AgCl";
        List <String> L = dieuChe.DieuChe(s, "");
        for (String st : L) System.out.println(st);
        /*DonChat a = knowledge.getDC("S");
        DonChat b = knowledge.getDC("O2");
        System.out.println(pu.execute(a, b));*/
        
/*        duDoan a = new duDoan("K NaOH");
        System.out.println(a.deduce());
        System.out.println(a.getReaction());
  */
           inter gd = new inter();
           gd.show();
        //System.out.println(pu.phanUng("N2", "O2", false));
        //System.out.println(pu.phanUng("P", "O3", false));
    } 
} 
