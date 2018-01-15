/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author hanh
 */
public class PwL {    
    public static List<String> copy(List<String> L){
        List <String> res = new Vector<String>();
        for (String st : L){
            res.add(st);
        }            
        return res;
    }
    public static boolean checkIn(String X, List<String> L){
        for (String st : L)
            if (X.equals(st)) return true;
        return false;
    }
    public static boolean unique(List <String> v){
        int n = v.size();        
        for (int i=0; i<n; i++) 
            if (v.get(i) == null) return false;
        for (int i=0; i<n - 1; i++){
            for (int j=i + 1; j<n; j++)
                if (v.get(i).equals(v.get(j))){
                    return false;
                }
        }
        return true;
    }    
    public static boolean available(List <String> v){
        for (String o : v)
            if (o == null) return false;
        return true;
    }
    // Phân hoạch theo L1
    public static Map<String, List<String>> phanHoach(List <String> L1, List <String> L2){
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        for (int i=0; i<L1.size(); i++){
            String st1 = L1.get(i);
            String st2 = L2.get(i);
            
            if (result.containsKey(st1)){
                result.get(st1).add(st2);
            }
            else{
                List <String> list = new Vector<String>();
                list.add(st2);
                result.put(st1, list);
            }
        }
        return result;
    }
}
