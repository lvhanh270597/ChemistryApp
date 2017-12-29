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
import static process.phanUng.makeQuery;

/**
 *
 * @author Hanh
 */
public class chuoiPhanUng {
    private String[] chuoi;
    public static String makeQuery(String X){
        String [] v = X.split("\\s");
        String res = "";
        for (int i=0; i<v.length - 1; i++){
            String x = v[i];
            res += "\'" + x + "\',";
        }
        res += "\'" + v[v.length - 1] + "\'";
        return res;
    }
    public chuoiPhanUng(String X){        
        this.chuoi = X.split("\\s");
    }
    public List<String> find(String X){
        List <String> res = new Vector<String>();
              
        String chuoi = makeQuery(X);
        chuoi = "dieuche([" + chuoi + "], X)";
        
        Query qr = new Query(chuoi);
        
        if (!qr.hasSolution()){
            System.out.println("Khong ton tai");
            return res;
        }
        else{
            Term []arr = qr.oneSolution().get("X").toTermArray();            
            for (int i=0; i<arr.length; i++){
                res.add(arr[i].toString());
            }            
        }
        return res;
    }
    public List <String> getChuoi(){
        List <String> res = new Vector<String>();
        for (int i=0; i<this.chuoi.length; i++)
            res.add(this.chuoi[i]);
        return res;
    }
}
