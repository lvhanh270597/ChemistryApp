
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
        String x = "S -> SO2 -> Na2SO3 -> Na2SO4 -> O2 -> ZnO -> Zn(NO3)2 -> Zn";
        String y = "";// các chất được tác dụng
        Vector vt = dieuChe.DieuChe(x,y);
        for(int i=0;i<vt.size();i++){
            System.out.println(vt.get(i));
        }
        //DonChat a = knowledge.donChat.get("Fe_1");  
        /*
        HopChat a = knowledge.getHC("ZnO");
        HopChat b = knowledge.getHC("HNO3");
        List <String> result = pu.execute(a, b);
        System.out.println(result);*/
        /*pu p = new pu("H2O KOH");
        System.out.println(p.predict()); 
        //p.predict();
        System.out.println(p.getPTHH());*/
        
        //String thamGia = "K H2O H2SO4";
        ///duDoan dd = new duDoan(thamGia);
        //List <String> rs = dd.deduce();
        //for (int i=0; i<rs.size(); i++){
         //   System.out.println(rs.get(i));
        //}
    } 
} 
