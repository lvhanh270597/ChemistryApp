
package Basic;

import Math.*;
import process.*;
import java.util.*;
import javafx.util.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
class PhanSo {
    
    private int a;
    private int b;
    
    public int getA(){
        return this.a;        
    }
    public int getB(){
        return this.b;
    }    
      
    
    public PhanSo(){}
    public PhanSo(int a, int b){
        int g = SoHoc.gcd(a, b);
        this.a = a / g;
        this.b = b / g;        
    }
}


public class HopChat{
    
    private Cation C;
    private Anion A;    
    private PhanSo tile;       
    
    private String shorted(String X){        
        String result = X;
        int i = 0;
        int cnt = 0;
        while (X.charAt(i) == 'H'){
            cnt += 1;
            i += 1;
        }
        if (X.charAt(i) >= '0' && X.charAt(i) <= '9'){
            cnt += Integer.valueOf(X.charAt(i) - '0') - 1;
            i += 1;
        }
        if (cnt > 1){
            result = "H" + String.valueOf(cnt) + X.substring(i, X.length());
        }
        return result;
    }
    
    private String simpleCTHH(){
        String s = C.getSymbol();
        if (this.tile.getB() > 1) s += Integer.toString(this.tile.getB());
        String t = A.getSymbol();
        if (this.tile.getA() > 1){
            if (this.A.getCnt() > 1) t = "(" + A.getSymbol() + ")";
            t += Integer.toString(this.tile.getA());
        }                   
        String X = s + t;
        return X;
    }
    
    public String getCTHH(){
        String X = simpleCTHH();
        String Y = shorted(X);
        if (X.equals(Y)) return X;        
        Cation C = this.C;
        for (String key: pu.anion.keySet()){
            Anion A = pu.anion.get(key);
            HopChat hc = new HopChat(C, A);
            if (hc.simpleCTHH().equals(Y)){
                this.A = A;
                return Y;
            }
        } 
        return X;
    }
    
    public float getM(){
        return C.getM() * this.tile.getB() + A.getM() * this.tile.getA();
    } 
    
    public Pair<List, List> getComponents(){
        List <String> res1 = new Vector <String>();
        List <Integer> res2 = new Vector <Integer>();   
        
        res1.add(this.C.getSymbol());
        res2.add(this.tile.getB());
        
        List <String> t1 = A.getComponents().getKey();
        List <Integer> t2  = A.getComponents().getValue();
        for (int i=0; i<t1.size(); i++){
            res1.add(t1.get(i));
            res2.add(t2.get(i) * this.tile.getA());
        }        
        Pair <List, List> e = new Pair <List, List>(res1, res2);
        return e;
    }
    
    public Cation getCation(){
        return this.C;
    }
    
    public Anion getAnion(){
        return this.A;
    }
    
    public HopChat(){}
    public HopChat(Cation C, Anion A){
        this.A = A;
        this.C = C;        
        this.tile = new PhanSo(C.getHoaTri(), A.getHoaTri());
    }
    
}
