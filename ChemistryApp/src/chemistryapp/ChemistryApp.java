
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
        String St = "";
        HopChat X = new HopChat(pu.cation.get("Na_1"), pu.anion.get("OH_1"));
        //HopChat Y = new HopChat(pu.cation.get("H_1"), pu.anion.get("O_2"));
        HopChat Y = new HopChat(pu.cation.get("H_1"), pu.anion.get("SO4_2"));
        //HopChat Y = new HopChat(pu.cation.get("H_1"), pu.anion.get("O_2"));
        //DonChat X = pu.donChat.get("H_2");        
        List <String> result = pu.pu(X, Y);
        if (result.size() == 0){
            System.out.println("Can not make a reaction");            
        }
        else{                        
            St = X.getCTHH() + " + " + Y.getCTHH() + " = ";
            for (int i=0; i<result.size() - 1; i++){             
                St += result.get(i) + " + ";
            }                      
            St += result.get(result.size() - 1);            
            System.out.println(St);            
            List <Integer> vl = canbang.canbang(St);
            result.add(0, " = ");
            result.add(0, Y.getCTHH());
            result.add(0, X.getCTHH());        
            printReaction(result, vl);            
        }
        /*HopChat X = new HopChat(pu.cation.get("Fe_2"), pu.anion.get("OH_1"));
        List <String> result = pu.pu(X);
        if (result.size() == 0){
            System.out.println("Can not make a reaction");            
        }
        else{                        
            St = X.getCTHH() + " = ";
            for (int i=0; i<result.size() - 1; i++){             
                St += result.get(i) + " + ";
            }                      
            St += result.get(result.size() - 1);            
            System.out.println(St);            
            List <Integer> vl = canbang.canbang(St);
            result.add(0, " = ");
            //result.add(0, Y.getCTHH());
            result.add(0, X.getCTHH());        
            printReaction(result, vl);            
        }*/
    } 
}
