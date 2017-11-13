
package chemistryapp;

import Basic.*;
import Math.*;
import java.io.*;
import java.util.*;
import javafx.util.*;
import knowledge.knowledge;
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
    
    public static void print(String X){
        System.out.print(X);
    }
    
    public static void main(String[] args) {          
        knowledge.readData();                                       
        HopChat[] A = {new HopChat(knowledge.cation.get("Na_1"), knowledge.anion.get("Cl_1")), 
                       new HopChat(knowledge.cation.get("Na_1"), knowledge.anion.get("CO3_2")),
                       new HopChat(knowledge.cation.get("Na_1"), knowledge.anion.get("SO4_2"))};      
        List <HopChat> G = new Vector<HopChat>();
        for (int i=0; i<A.length; i++) G.add(A[i]);
        HopChat[] B = {new HopChat(knowledge.cation.get("C_4"), knowledge.anion.get("O_2")),
                       new HopChat(knowledge.cation.get("H_1"), knowledge.anion.get("O_2"))};  
        List <HopChat> E = new Vector<HopChat>();
        //for (int i=0; i<B.length; i++) E.add(B[i]);
        phanBiet.phanBiet(G, E);                     
        /*HopChat A = new HopChat(knowledge.cation.get("Na_1"), knowledge.anion.get("Cl_1"));
        HopChat B = new HopChat(knowledge.cation.get("H_1"), knowledge.anion.get("O_2"));
        List<String> result = pu.pu(A, B);
        for (String vl : result){
            print(vl + " ");
        }*/
    } 
} 
