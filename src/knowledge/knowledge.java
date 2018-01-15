
package knowledge;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import static knowledge.phanUng.phanUng;
import math.*;
import org.jpl7.*;
import java.lang.Integer;


public class knowledge {
    

    public static void prepare(){
        Query q1 = new Query("consult('knowledge/app.pl')");        
        q1.hasSolution();
        q1 = new Query("readKnowledge");
        q1.hasSolution();
    }
   
    public static float nguyenTo(String X){
        Query q = new Query("nguyento('" + X + "\', X)");        
        if (!q.hasSolution()) return -1;
        Term a = q.oneSolution().get("X");
        return a.floatValue();
    }
    
    public static int getOxh(String T){        
        Query qr = new Query("name('" + T + "', X, Y)");
        String X = qr.oneSolution().get("X").toString();
        qr = new Query("cation(" + X + ", _ , X, Y)");
        X = qr.oneSolution().get("X").toString();
        int result = Integer.parseInt(X);
        return result;
    }    
    
    public static boolean axit(String X){
        Query qr = new Query("axit(" + X + ")");
        return qr.hasSolution();
    }
    public static boolean bazo(String X){
        Query qr = new Query("bazo(" + X + ")");
        return qr.hasSolution();
    }
    public static boolean oxit(String X){
        Query qr = new Query("oxit('" + X + "')");
        return qr.hasSolution();
    }
    public static boolean muoi(String X){
        Query qr = new Query("muoi(" + X + ")");
        return qr.hasSolution();
    }
    public static String mauQuy(String X){
        if (axit(X)) return "red";
        if (bazo(X)) return "blue";
        return "none";
    }
    public static boolean kettua(String X){
        Query qr = new Query("khongtan(\'" + X + "\', _)");
        return qr.hasSolution();             
    }
    
    public static String ketTua(List <String> v){
        if (v == null) return null;
        for (String st : v){                        
            if (kettua(st)) return st;
        }
        return null;
    }
    public static boolean isKhi(String X){
        Query qr = new Query("khi(\'" + X + "\')");
        return qr.hasSolution();            
    }
    
    public static String khi(List <String> v){
        if (v == null) return null;
        for (String st : v){
            if (isKhi(st)) return st;
        }
        return null;
    }
    
    public static String color(String X){
        Query qr = new Query("mau(\'" + X + "\', Y)");
        if (!qr.hasSolution()){
            return null;
        }
        return qr.oneSolution().get("Y").toString();
    }
    
    public static List <String> getColor(List <String> v){
        List <String> res = new Vector<String>();
        for (String st : v){
            res.add(color(st));
        }
        return res;
    }
    
    public static ktk getKetTua_Khi(String X){
        List <String> result = phanUng.phanUng(X);
        ktk res = new ktk(knowledge.ketTua(result), knowledge.khi(result));        
        return res;
    }
    
    public static hopchat getCA(String X){        
        Query qr = new Query("getCA(\'" + X + "\', C, A)");
        if (!qr.hasSolution()) return null;
        String A = qr.oneSolution().get("A").toString();
        String C = qr.oneSolution().get("C").toString();
        hopchat result = new hopchat(C, A);
        return result;
    }
    
    public static List <String> getAllHC(){
        List <String> result = new Vector<String>();
        Query qr = new Query("getHC(X)");
        if (!qr.hasSolution()) return null;
        for (Map <String, Term> res : qr.allSolutions()){
            String X = res.get("X").toString();
            X = phanUng.clean(X);
            result.add(X);
        }        
        return result;
    }
    
    public static List <String> getAllAxit(){
        List <String> result = new Vector<String>();
        Query qr = new Query("getAxit(X)");
        if (!qr.hasSolution()) return null;
        for (Map <String, Term> res : qr.allSolutions()){
            String X = res.get("X").toString();
            X = phanUng.clean(X);
            result.add(X);
        }
        return result;
    }
    
    public static List <String> getAllBazo(){
        List <String> result = new Vector<String>();
        Query qr = new Query("getBazo(X)");
        if (!qr.hasSolution()) return null;
        for (Map <String, Term> res : qr.allSolutions()){
            String X = res.get("X").toString();
            X = phanUng.clean(X);
            result.add(X);
        }
        return result;
    }
}
