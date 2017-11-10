
package chemistryapp;

import Basic.*;
import Math.*;
import java.io.*;
import java.util.*;
import javafx.util.*;
import process.*;

public class ChemistryApp {
                    
    public static void printReaction(List<String> result, List <Integer> vl){
        int index = result.indexOf(" = ");                
        int t = 0;
        for (int i=0; i<index - 1; i++){
            if (vl.get(i) > 1)
                System.out.print(vl.get(i));
            System.out.print(result.get(i) + " + ");
        }            
        if (vl.get(index - 1) > 1)
            System.out.print(vl.get(index - 1));
        System.out.print(result.get(index - 1) + " = ");        
        for (int i=index+1; i<result.size() - 1; i++){
            if (vl.get(i - 1) > 1){
                System.out.print(vl.get(i - 1));
            }
            System.out.print(result.get(i) + " + ");
        }        
        index = vl.size();
        if (vl.get(index - 1) > 1)
            System.out.print(vl.get(index - 1));
        System.out.print(result.get(index));
        System.out.println();
    }
    public static void main(String[] args) {          
        pu.readData();/*                               
        List <HopChat> G = new Vector<HopChat>();;
        HopChat X = new HopChat(pu.cation.get("H_1"), pu.anion.get("SO4_2"), "none");
        HopChat Y = new HopChat(pu.cation.get("H_1"), pu.anion.get("Cl_1"), "none");
        HopChat Z = new HopChat(pu.cation.get("Ba_2"), pu.anion.get("Cl_1"), "none");
        HopChat T = new HopChat(pu.cation.get("Na_1"), pu.anion.get("CO3_2"), "none");
        G.add(X);   G.add(Y);   G.add(Z);   G.add(T);        
<<<<<<< HEAD
        phanBiet.phanBiet(G);    */  
       List<String> a = new LinkedList<String>();
        //String x = "FeS -> H2S -> SO2 -> Na2SO3 -> Na2SO4 -> NaCl -> Cl2 -> H2SO4 -> SO2 -> H2SO3 -> S -> FeS";
        //String x ="FeS -> H2S -> NO2 -> HNO3 -> Al(NO3)3 -> O2";
        String x = "FeS -> H2S -> S -> SO2 -> H2SO4 -> ZnSO4 -> Zn(NO3)2 -> O2";
        String[] word = DieuChe.TachChuoi(x);
        int i =0;
        while (i<=word.length-2){
            System.out.println("từ "+word[i]+ " -> "+ word[i+2]);
                    String d = DieuChe.ChatDC(word[i], word[i+2]);
                    System.out.println("Chất dieu che "+ d);
            i = i+2;
        }
        //HopChat X = new HopChat(pu.cation.get("H_ 1"),pu.anion.get("S_2"));
       // HopChat Y = new HopChat(pu.cation.get("H_1"),pu.anion.get("Cl_1"));
       DonChat Y = new DonChat(pu.nguyenTo.get("Fe"),1);
       DonChat X = new DonChat(pu.nguyenTo.get("S"),1);
        List <String> result= pu.pu(Y,X);
        System.out.println(result);
=======
        phanBiet.phanBiet(G);  
>>>>>>> 9e6cc7ce9b1630f0ec8c590b6b4fe98d8594c451
    } 
}
