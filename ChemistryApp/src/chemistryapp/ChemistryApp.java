
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
        
    } 
}
