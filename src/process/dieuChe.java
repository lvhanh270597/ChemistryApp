/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import knowledge.phanUng;
import math.edge;
import java.util.*;
import org.jpl7.Query;
import org.jpl7.Term;
/**
 *
 * @author Hanh
 */
public class dieuChe {
    private List <String> chats;
    private edge[] trace;
    public dieuChe(String X){
        trace = new edge[1000];
        this.chats = new Vector<String>();
        String []v = X.split("\\s");
        for (String st : v){            
            this.chats.add(st);
        }
    }
    
    private boolean checkIn(List <String> v, String X){
        for (String st : v) 
            if (st.equals(X)) return true;
        return false;
    }
    
    private List<String> getCopyOf(List <String> v){
        List <String> res = new Vector<String>();
        for (String st : v) res.add(st);
        return res;
    }
    
    private String make(List <String> L1, List <String> L2){
        String res = "";
        for (int i=0; i<L1.size() - 1; i++) res += L1.get(i) + " + ";        
        res += L1.get(L1.size() - 1) + " = ";        
        for (int i=0; i<L2.size() - 1; i++) res += L2.get(i) + " + ";
        res += L2.get(L2.size() -  1);
        return res;
    }
    
    public int find(String X){
        List <String> s = new Vector<String>();
        for (String st : this.chats) s.add(st);
        List < List <String> > queue = new Vector<List<String>>();        
        queue.add(s);
        int L = 0, R = 0;
        while (L <= R){     
            
            for (int k=L; k<=R; k++){
                List <String> u = queue.get(k);
                for (int i=0; i<u.size(); i++){
                    String c = u.get(i);
                    // phan huy
                    List <String> pu = phanUng.phanUng(c);
                    
                    if (pu != null){
                        List <String> v = getCopyOf(pu);
                        for (String c2 : u){
                            v.add(c2);
                        }
                        List <String> thamgia = new Vector<String>();
                        thamgia.add(c);
                        queue.add(v);
                        trace[queue.size() - 1] = new edge(k, make(thamgia, pu));
                        
                        //////////////////////
                        if (checkIn(pu, X)){                            
                            return queue.size() - 1;
                        }
                        
                    }                    
                    // ket hop
                    for (int j=i+1; j<u.size(); j++){                        
                        List <String> pu2 = phanUng.phanUng(c + " " + u.get(j));                       
                        if (pu2 != null){
                            List <String> v = getCopyOf(pu2);
                            for (String c2 : u){
                                v.add(c2);
                            }
                            List <String> thamgia = new Vector<String>();
                            thamgia.add(c);
                            thamgia.add(u.get(j));
                            queue.add(v);
                            
                            trace[queue.size() - 1] = new edge(k, make(thamgia, pu2));
                            ////////////////////////////////
                            if (checkIn(pu2, X)){
                                return queue.size() - 1;
                            }
                            
                        }                        
                    } 
                }
            }
            
            L = R + 1;
            R = queue.size() - 1;              
        }
        return 0;
    }
    public List<String> showPath(int id){
        
        List <String> res = new Vector<String>();
        
        int k = id;
        
        while (k > 0){
            res.add(trace[k].getPt());
            k = trace[k].getId();
        }
        return res;
    }
}
