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
    private Map<String, Boolean> check;
    
    public duDoan(){}
    public duDoan(String X){
        String[] thamGia = X.split("\\s");        
        
        this.thamGia = new Vector<String>();        
        this.check = new HashMap<String, Boolean>();               
        
        for (int i=0; i<thamGia.length; i++){
            this.thamGia.add(thamGia[i]);
            this.check.put(thamGia[i], Boolean.TRUE);            
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
                    String tmp = this.thamGia.get(i) + " " + this.thamGia.get(j);
                    //System.out.println(tmp);
                    pu p = new pu(tmp);
                    List <String> result = p.predict();
                    if (result != null){
                        boolean ok = false;
                        for (int k=0; k<result.size(); k++)
                            if (!check.containsKey(result.get(k))){
                                check.put(result.get(k), Boolean.TRUE);
                                addition.add(result.get(k));
                                ok = true;
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
}
