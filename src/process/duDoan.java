/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.util.*;

/**
 *
 * @author Hanh
 */
public class duDoan {
    private List <String> ds;
    public duDoan(String X){
        this.ds = new Vector<String>();
        this.ds.add(X);
        this.ds.add("H2O");
    }            
    public duDoan(){}
    private boolean checkIn(List <String> R, String X){
        for (String Y : R)
            if (X.equals(Y)) return true;
        return false;
    }
    private List <String> push(List <String> R, List <String> L){
        List <String> res = new Vector<String>();
        for (String X : R) res.add(X);
        if (L == null) return res;
        for (String X : L){
            if (!checkIn(res, X)) res.add(X);
        }
        return res;
    }
    private List <String> deleted(List <String> R, List <String> del){
        List <String> res = new Vector<String>();
        for (String X : R){
            if (checkIn(this.ds, X) || !checkIn(del, X)){
                res.add(X);
            }
        }
        return res;
    }
    public void add(String X){
        List <String> mtp = new Vector<String>();
        List <String> tmp = new Vector<String>();
        for (String Y : this.ds) tmp.add(Y);
        for (String Y : tmp){            
            mtp = push(mtp, phanUng.phanUng(X + " " + Y));
        }
                        
        tmp = push(tmp, mtp);
        
        List <String> del = new Vector<String>();
        while (true){
            mtp.clear();
            del.clear();
            for (String Y : tmp){                
                for (String Z : tmp){
                    if (!Y.equals(Z)){
                        List <String> pu = phanUng.phanUng(Y + " " + Z);
                        mtp = push(mtp, pu);                                   
                        if (pu != null){                            
                            del.add(Z);
                            del.add(Y);
                        }
                    }                    
                }
            }            
            
            tmp = push(tmp, mtp);
            tmp = deleted(tmp, del);
                      
            if (mtp.size() == 0) break;                           
        }
        
        this.ds = push(this.ds, tmp);     
    }
    public List <String> get(){
        return this.ds;
    }
    public void showAll(){
        for (String X : this.ds) 
            System.out.println(X + " ");
        System.out.println();
    }
}
