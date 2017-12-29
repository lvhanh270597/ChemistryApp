/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.util.List;
import java.util.Vector;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author Hanh
 */
public class phanUng {
    public static String makeQuery(String X){
        String [] v = X.split("\\s");
        String res = "";
        for (String x : v){
            res += "\'" + x + "\',";
        }
        return res;
    }
    
    public static List <String> phanUng(String v){        
        String in = makeQuery(v);
        String []inp = v.split("\\s");
        
        List <String> input = new Vector<String>();
        for (int i=0; i<inp.length; i++) input.add(inp[i]);
        
        Query q = new Query("pu(" + in + " X)");                
        if (!q.hasSolution()){
            int n = input.size();
            String X = "";
            for (int i=n - 1; i>=0; i--)
                X += inp[i];
            in = makeQuery(X); 
            q = new Query("pu(" + in + " X)");    
        }
        
        if (!q.hasSolution()) return null;
        
        Term []arr = q.oneSolution().get("X").toTermArray();
        
        List <String> res = new Vector<String>();
        for (int i=0; i<arr.length; i++){
            res.add(arr[i].toString());
        }            
        return res;
    }
}
