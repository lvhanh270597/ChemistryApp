
package chemistryapp;

import Basic.*;
import Math.*;
import java.io.*;
import java.util.*;
import javafx.util.*;
import knowledge.knowledge;
import java.util.LinkedList;
import process.*;
import Interface.*;
public class ChemistryApp {
    public static void printReaction(List<String> result, List <Integer> vl){
        int index = result.indexOf("=");                
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
     public static String printReaction1(List<String> result, List <Integer> vl){
         String s ="";
        int index = result.indexOf("=");                
        int t = 0;
        for (int i=0; i<index - 1; i++){
            if (vl.get(i) > 1)
                s+=vl.get(i);
            s+=result.get(i) + " + ";
        }            
        if (vl.get(index - 1) > 1)
            s+=vl.get(index - 1);
        s+=result.get(index - 1) + " = ";        
        for (int i=index+1; i<result.size() - 1; i++){
            if (vl.get(i - 1) > 1){
                s+=vl.get(i - 1);
            }
            s+=result.get(i) + " + ";
        }        
        index = vl.size();
        if (vl.get(index - 1) > 1)
            s+=vl.get(index - 1);
        s+=result.get(index);
        return s;
    }
    public static void print(String X){
        System.out.print(X);
    }
    
    public static void main(String[] args) {          
        knowledge.readData();                                       
        /*HopChat[] A = {new HopChat(knowledge.cation.get("Na_1"), knowledge.anion.get("Cl_1")), 
                       new HopChat(knowledge.cation.get("Na_1"), knowledge.anion.get("CO3_2")),
                       new HopChat(knowledge.cation.get("Na_1"), knowledge.anion.get("SO4_2"))};      
        List <HopChat> G = new Vector<HopChat>();
        for (int i=0; i<A.length; i++) G.add(A[i]);
        HopChat[] B = {new HopChat(knowledge.cation.get("C_4"), knowledge.anion.get("O_2")),
                       new HopChat(knowledge.cation.get("H_1"), knowledge.anion.get("O_2"))};  
        List <HopChat> E = new Vector<HopChat>();*/
        //for (int i=0; i<B.length; i++) E.add(B[i]);
       //phanBiet.phanBiet(G, E);  
        String x = "FeS -> H2S -> S -> NO2 -> SO3 -> H2SO4 -> SO2 -> H2SO4 -> CO2 -> CO -> S -> Na2SO3 -> Na2S -> H2S -> Cu";// page 14
       // String x = "FeS -> H2S -> SO2 -> Na2SO3 -> Na2SO4 -> NaCl -> Cl2 -> H2SO4 -> SO2 -> H2SO3 -> S -> FeS";
        //String x ="FeS -> H2S -> S -> NO2 -> HNO3 -> Al(NO3)3 -> O2";
        //String x = "FeS -> H2S -> SO2 -> H2SO4 -> ZnSO4 -> Zn(NO3)2 -> O2";
       //String x = "S -> SO2 -> Na2SO3 -> Na2SO4 -> O2 -> ZnO -> Zn(NO3)2 -> Zn";
        //String x = "MnO2 -> Cl2";
        //String x = "KClO3 -> O2 -> CO2 -> BaCO3 -> BaCl2 -> Ba(NO3)2 -> O2";
        //String x ="KMnO4 -> O2 -> O3 -> I2 -> KI -> I2 -> S";
        DieuChe.DieuChe(x);
        //DonChat a = knowledge.donChat.get("S_1");
        //DonChat b = knowledge.donChat.get("O_3");
        //HopChat a= new HopChat(knowledge.cation.get("Cu_2"),knowledge.anion.get("O_2"));
        //HopChat b = new HopChat(knowledge.cation.get("H_1"),knowledge.anion.get("S_2"));
        //List <String> k = pu.pu(a,b);
        //System.out.println(k);
        /*HopChat A = new HopChat(knowledge.cation.get("Na_1"), knowledge.anion.get("Cl_1"));
        HopChat B = new HopChat(knowledge.cation.get("H_1"), knowledge.anion.get("O_2"));
        List<String> result = pu.pu(A, B)       ;
        for (String vl : result){
            print(vl + " ");
        }*/
        /*String s = "Na + H2O = NaOH + H2";
        List<Integer> sb = canbang.canbang(s);
        for(int i=0; i<sb.size(); i++)
            System.out.print(sb.get(i) + " ");
        System.out.println();*/
       // giaodien gd = new giaodien();
       // gd.show();
    } 
} 
