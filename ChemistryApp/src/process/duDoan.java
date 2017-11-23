/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import Basic.DonChat;
import Basic.HopChat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import knowledge.knowledge;

/**
 *
 * @author hanh
 */
public class duDoan {
    private List<String> thamGia;    
    private Map<String, Boolean> ThamGia;
    private Map<String, Boolean> SanPham;
    private Map<String, Boolean> check;
    
    
    public duDoan(){}
    public duDoan(String X){
        String[] thamGia = X.split("\\s");        
        
        this.thamGia = new Vector<String>();        
        this.SanPham = new HashMap<String, Boolean>();
        this.ThamGia = new HashMap<String, Boolean>();
        this.check = new HashMap<String, Boolean>();               
                
        // add water
        HopChat H2O = knowledge.getHC("H2O");
        
        for (int i=0; i<thamGia.length; i++){
            this.thamGia.add(thamGia[i]);            
            this.check.put(thamGia[i], Boolean.TRUE);        
            if (!this.check.containsKey(H2O.getCTHH())){
                HopChat hc = knowledge.getHC(thamGia[i]);
                if (hc == null) continue;
                if (!knowledge.khi.containsKey(thamGia[i]) && !knowledge.khongTan.containsKey(thamGia[i])){
                    this.thamGia.add(H2O.getCTHH());
                    this.check.put(H2O.getCTHH(), Boolean.TRUE);                    
                }
            }
        }
                
    }
    
    public List<String> deduce(){
        List <String> res = new Vector<String>();
        boolean stop = false;
        while (!stop){
            List <String> addition = new Vector<String>();
            int n = this.thamGia.size();
            for (int i=0; i<n-1; i++){
                for (int j=i+1; j<n; j++){
                    String A = this.thamGia.get(i);
                    String B = this.thamGia.get(j);
                    String tmp = A + " " + B;             
                    //System.out.println(tmp);
                    pu p = new pu(tmp);
                    List <String> result = p.predict();
                    if (result != null){
                        // update ThamGia
                        if (!this.ThamGia.containsKey(A)){
                            this.ThamGia.put(A, Boolean.TRUE);
                        }
                        if (!this.ThamGia.containsKey(B)){
                            this.ThamGia.put(B, Boolean.TRUE);
                        }
                        
                        boolean ok = false;
                        for (int k=0; k<result.size(); k++){
                            A = result.get(k);
                            if (!check.containsKey(A)){
                                check.put(A, Boolean.TRUE);
                                addition.add(A);
                                ok = true;
                            }             
                            // update SanPham
                            if (!this.SanPham.containsKey(A)){
                                this.SanPham.put(A, Boolean.TRUE);
                            }                            
                        }
                        if (ok){
                            res.add(p.getPTHH());
                        }                            
                    }
                }                    
            }
            if (addition.size() == 0) stop = true;
            for (String st : addition){
                this.thamGia.add(st);                
            }                
        }
        return res;
    }  
    
    public List<String> deduce2(String ADD){
        List <String> res = new Vector<String>();
        boolean stop = false;
        while (!stop){
            List <String> addition = new Vector<String>();
            int n = this.thamGia.size();
            
            for (int j=0; j<n; j++){        
                String A = ADD;
                String B = this.thamGia.get(j);
                String tmp = A + " " + B;                             
                pu p = new pu(tmp);
                List <String> result = p.predict();
                if (result != null){
                    // update ThamGia
                    if (!this.ThamGia.containsKey(A)){
                        this.ThamGia.put(A, Boolean.TRUE);
                    }
                    if (!this.ThamGia.containsKey(B)){
                        this.ThamGia.put(B, Boolean.TRUE);
                    }

                    boolean ok = false;
                    for (int k=0; k<result.size(); k++){
                        A = result.get(k);
                        if (!check.containsKey(A)){
                            check.put(A, Boolean.TRUE);
                            addition.add(A);
                            ok = true;
                        }             
                        // update SanPham
                        if (!this.SanPham.containsKey(A)){
                            this.SanPham.put(A, Boolean.TRUE);
                        }                            
                    }
                    if (ok){
                        res.add(p.getPTHH());
                    }                            
                }
            }                                
            if (addition.size() == 0) stop = true;
            for (String st : addition){
                this.thamGia.add(st);                
            }                
        }
        return res;
    }  
    
    public String getReaction(){
        List <String> f = new Vector<String>();        
        List <String> s = new Vector<String>();
        
        for (String st : this.ThamGia.keySet()){
            if (!this.SanPham.containsKey(st)) f.add(st);
        }
        for (String st : this.SanPham.keySet()){
            if (!this.ThamGia.containsKey(st)) s.add(st);
        }
        
        this.ThamGia.clear();
        this.SanPham.clear();
        
        for (String st : f) this.ThamGia.put(st, Boolean.TRUE);
        for (String st : s) this.SanPham.put(st, Boolean.TRUE);
        
        String result = "";
        if (f.size() == 0 || s.size() == 0) return null;
        int n = f.size();
        for (int i=0; i<n - 1; i++) result += f.get(i) + " + ";
        result += f.get(n - 1) + " = ";        
        
        n = s.size();
        for (int i=0; i<n - 1; i++) result += s.get(i) + " + ";
        result += s.get(n - 1);
        
        pu p = new pu(result);
        result = p.getPTHH();
        return result;
    }
    
    public List<String> getSanPham(){
        List <String> tmp = new Vector<String>();
        for (String st : this.SanPham.keySet()){
            tmp.add(st);
        }
        return tmp;
    }
}
