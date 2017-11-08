
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
        pu.readData();                               
        List <HopChat> G = new Vector<HopChat>();;
        HopChat X = new HopChat(pu.cation.get("H_1"), pu.anion.get("SO4_2"));
        HopChat Y = new HopChat(pu.cation.get("H_1"), pu.anion.get("Cl_1"));
        HopChat Z = new HopChat(pu.cation.get("Ba_2"), pu.anion.get("Cl_1"));
        HopChat T = new HopChat(pu.cation.get("Na_1"), pu.anion.get("CO3_2"));
        G.add(X);   G.add(Y);   G.add(Z);   G.add(T);        
        phanBiet.phanBiet(G);        
    } 
}
