/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Basic;

import java.util.*;
import javafx.util.*;

/**
 *
 * @author Thien Trang
 */
public class Anion { 
    private String symbolChemical;
    private float M;
    private int hoaTri;
    
    public float getM(){
        return this.M;
    }
        
    public Pair<List, List> getComponents(){
        List <String> res1 = new Vector <String>();
        List <Integer> res2 = new Vector <Integer>();   
        int n = this.symbolChemical.length();
        int i = 0;
        while (i < n){            
            char c = this.symbolChemical.charAt(i);
            String temp = String.valueOf(c);
            int cnt = 1;
            if (c >= 'A' && c <= 'Z'){
                if (i < n - 1){
                    char c_next = this.symbolChemical.charAt(i + 1);
                    if (c_next >= 'a' && c_next <= 'z'){
                        i++;                                     
                        temp += c_next;
                    }
                }
                if (i < n - 1){
                    char c_next = this.symbolChemical.charAt(i + 1);
                    if (c_next >= '1' && c_next <= '9'){
                        i += 1;
                        cnt = c_next - '0';
                    }                    
                }
            }
            res1.add(temp);
            res2.add(cnt);
            i += 1;
        }
        Pair <List, List> e = new Pair <List, List>(res1, res2);
        return e;
    }
    
    /*public String getName(){
        return this.name;
    }*/
    
    public String differ(){
        Pair <List, List> g = this.getComponents();
        List <String> k = g.getKey();                
        for (int i=0; i<k.size(); i++){            
            if (!k.get(i).equals("O") && !k.get(i).equals("H")) 
                return k.get(i);            
        }   
        return "None";
    }
    
    public int getOxiHoa(){
        Pair <List, List> g = this.getComponents();
        List <String> k = g.getKey();
        List <Integer> v = g.getValue();
        int slO = 0, slH = 0, slX = 0, X;        
        for (int i=0; i<k.size(); i++){            
            if (k.get(i).equals("O")) slO = v.get(i);
            else 
                if (k.get(i).equals("H")) slH = v.get(i);
                else slX = v.get(i);
            
        }        
        X = (slO * (2) + slH * (-1) - this.hoaTri) / slX;        
        return X;
    }
    
    public String getSymbol(){
        return this.symbolChemical;
    }
    
    public int getHoaTri(){
        return this.hoaTri;
    }
    
    public int getCnt(){
        int cnt = 0;
        for (int i=0; i<this.symbolChemical.length(); i++){
            char c = this.symbolChemical.charAt(i);
            if (c >= 'A' && c <= 'Z') cnt += 1;
        }
        return cnt;
    }
    
    public void print(){
        System.out.println(this.symbolChemical + " " + this.M + " " + this.hoaTri);
    }
    
    public Anion(){}
    public Anion(String symbol, float value, int hoaTri){
        //this.name = name;
        this.symbolChemical = symbol;
        this.M = value;
        this.hoaTri = hoaTri;
    }    
}
