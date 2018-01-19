
package knowledge;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import static knowledge.phanUng.phanUng;
import math.*;
import org.jpl7.*;
import java.lang.Integer;


public class knowledge {
    
<<<<<<< HEAD
    public static List <String> allOfHC;
    public static List <String> allOfAx;
    public static List <String> allOfBz;
    public static List <String> allOfMu;
    public static Map<String, Color> colors;    
    public static final String pathColor = "knowledge/colors.txt";
    public static final String pathHC = "knowledge/hc.txt";
    public static final String pathAX = "knowledge/axit.txt";
    public static final String pathBZ = "knowledge/bazo.txt";
    public static final String pathMu = "knowledge/muoi.txt";
    
    public static void prepare() throws IOException{                        
        
=======

    public static void prepare(){
>>>>>>> b6a3b76a7aaf77cd47fa1869cff39ae95a82b6b6
        Query q1 = new Query("consult('knowledge/app.pl')");        
        q1.hasSolution();
        q1 = new Query("readKnowledge");
        q1.hasSolution();
                        
        readColors();
        allOfHC = readFile(pathHC);
        allOfAx = readFile(pathAX);
        allOfBz = readFile(pathBZ);
        allOfMu = readFile(pathMu);
    }
       
    
    public static float nguyenTo(String X){        
        Query q = new Query("nguyento('" + X + "\', X)");        
        if (!q.hasSolution()) return -1;
        Term a = q.oneSolution().get("X");
        return a.floatValue();
    }
<<<<<<< HEAD
=======
    
>>>>>>> b6a3b76a7aaf77cd47fa1869cff39ae95a82b6b6
    public static int getOxh(String T){        
        Query qr = new Query("name('" + T + "', X, Y)");
        String X = qr.oneSolution().get("X").toString();
        qr = new Query("cation(" + X + ", _ , X, Y)");
        X = qr.oneSolution().get("X").toString();
<<<<<<< HEAD
        int result = java.lang.Integer.parseInt(X);
        return result;
    }  
    public static boolean axit(String X){        
=======
        int result = Integer.parseInt(X);
        return result;
    }    
    
    public static boolean axit(String X){
>>>>>>> b6a3b76a7aaf77cd47fa1869cff39ae95a82b6b6
        Query qr = new Query("axit(" + X + ")");
        return qr.hasSolution();
    }
    public static boolean oxit(String X){
        Query qr = new Query("oxit('" + X + "')");
        return qr.hasSolution();
    }
    public static boolean bazo(String X){        
        Query qr = new Query("bazo(" + X + ")");
        return qr.hasSolution();
    }
<<<<<<< HEAD
    public static boolean muoi(String X){        
=======
    public static boolean oxit(String X){
        Query qr = new Query("oxit('" + X + "')");
        return qr.hasSolution();
    }
    public static boolean muoi(String X){
>>>>>>> b6a3b76a7aaf77cd47fa1869cff39ae95a82b6b6
        Query qr = new Query("muoi(" + X + ")");
        return qr.hasSolution();
    }
    public static String mauQuy(String X){        
        if (axit(X)) return "red";
        if (bazo(X)) return "blue";
        return "violet";
    }
    public static List<String> getAllMauQ(List<String> L){
        List<String> result = new Vector<String>();
        for (String st : L){
            result.add(mauQuy("\'" + st + "\'"));
        }
        return result;
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
        return phanUng.clean(qr.oneSolution().get("Y").toString());
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
    
    public static boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }  
    
    public static void readColors(){
        
        colors = new HashMap<String, Color>();
        
        BufferedReader br = null;
        FileReader fr = null;

        try {

                //br = new BufferedReader(new FileReader(FILENAME));
                fr = new FileReader(pathColor);
                br = new BufferedReader(fr);

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    
                    String nextLine = br.readLine();                    
                    String[] v = nextLine.split("\\s");                    
                    int r = java.lang.Integer.parseInt(v[0]);
                    int g = java.lang.Integer.parseInt(v[1]);
                    int b = java.lang.Integer.parseInt(v[2]);
                    Color c = new Color(r, g, b);
                    colors.put(sCurrentLine, c);
                                       
                }

        } catch (IOException e) {

                e.printStackTrace();

        } finally {

                try {

                        if (br != null)
                                br.close();

                        if (fr != null)
                                fr.close();

                } catch (IOException ex) {

                        ex.printStackTrace();

                }

        }

    }
    
    
    public static Vector<String> readFile(String path){
        
        Vector<String> result = new Vector<String>();
        
        BufferedReader br = null;
        FileReader fr = null;

        try {

                //br = new BufferedReader(new FileReader(FILENAME));
                fr = new FileReader(path);
                br = new BufferedReader(fr);

                String sCurrentLine;

                while ((sCurrentLine = br.readLine()) != null) {
                    
                    result.add(sCurrentLine);
                                       
                }

        } catch (IOException e) {

                e.printStackTrace();

        } finally {

                try {

                        if (br != null)
                                br.close();

                        if (fr != null)
                                fr.close();

                } catch (IOException ex) {

                        ex.printStackTrace();

                }

        }
        
        return result;

    }
    public static List<String> getAllMuoi(){
        return allOfMu;
    }     
    public static List<String> getAllAxit(){
        return allOfAx;
    }            
    public static List<String> getAllBazo(){
        return allOfBz;
    }
    public static List<String> getAllHC(){
        return allOfHC;
    }
    
    // Phản ứng sẽ có các loại sau:
    // + không phản ứng
    // + phản ứng tạo khí
    // + phản ứng tạo kết tủa màu x
    // + phản ứng tạo khí và kết tủa màu x
    // lưu theo kiểu: none, khi, kt_Yellow, kkt_Yellow
    // Đưa vào 1 phản ứng trả ra nhãn của nó
    public static String productLabelOne(String pu){
        List <String> result = phanUng.phanUng(pu);
        String label = "none";
        String k = khi(result);
        String kt = ketTua(result);
        boolean _khi = (k != null);
        boolean _kt = (kt != null);
        if (_khi && !_kt) label = "Khí";
        if (!_khi && _kt) {
            String cl = color(kt);
            label = "kết tủa màu " + cl;
        } 
        if (_khi && _kt){
            String cl = color(kt);
            label = "khí + kết tủa " + cl;
        }
        
        return label;
    }
    // X + tt có hiện tượng gì?
    public static List<String> productLabelList(List<String> L, String tt){
        List <String> result = new Vector<String> ();
        for (String X : L){
            String pu = X + " " + tt;
            result.add(productLabelOne(pu));
        }        
        return result;
    }            
    
}
