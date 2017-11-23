/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knowledge;

import Basic.Anion;
import Basic.Cation;
import Basic.DonChat;
import Basic.HopChat;
import Basic.NguyenTo;
import Basic.Rule;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javafx.util.Pair;

/**
 *
 * @author Hanh
 */
public class knowledge {
    private static final String NguyenToPath = "knowledge/nguyento.txt";
    private static final String AnionPath = "knowledge/anion.txt";
    private static final String CationPath = "knowledge/cation.txt";
    private static final String DonChatPath = "knowledge/donchat.txt";
    private static final String DienHoaPath = "knowledge/dienhoa.txt";
    private static final String LuatPath = "knowledge/luat.txt";
    private static final String KienThucPath = "knowledge/class.txt";
    private static final String NotExistPath = "knowledge/khongtontai.txt";
    private static final String KhongTanPath = "knowledge/khongtan2.txt";
    private static final String KhiPath = "knowledge/khi.txt";
    public static Map <String, NguyenTo> nguyenTo;
    public static Map <String, Anion> anion;
    public static Map <String, Cation> cation;
    public static Map <String, DonChat> donChat;
    public static List <Pair <String, String>> dienHoa;
    public static List <Rule> luat;    
    public static Map <String, List<NguyenTo>> kienThuc;
    public static Map <String, HopChat> notExist;
    public static Map <String, HopChat> khongTan;
    public static Map <String, Boolean> khi;
    
    public static void readData(){
        
        //----------------------- read Nguyen to -----------------------//
        
        nguyenTo = new HashMap<String, NguyenTo>();
        
        String line = null;
        try{
            FileReader fileReader = new FileReader(NguyenToPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {                
                String v[] = line.split("\\s+");                            
                boolean kl = (Integer.valueOf(v[2]) == 1);                                
                NguyenTo temp = new NguyenTo(v[0], Float.valueOf(v[1]), kl, Float.valueOf(v[3]));
                nguyenTo.put(v[0], temp);
            }                   
            
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                NguyenToPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + NguyenToPath + "'");                              
        }
        //--------------------------------------------------------------------//
        //----------------------- read Anion -----------------------//
        anion = new HashMap<String, Anion>();
        
        line = null;
        try{
            FileReader fileReader = new FileReader(AnionPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {                
                String v[] = line.split("\\s+");                                                        
                Anion temp = new Anion(v[0], Float.valueOf(v[1]), Integer.valueOf(v[2]));
                anion.put(temp.getKey(), temp);
            }                   
            
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                AnionPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + AnionPath + "'");                              
        }
        //--------------------------------------------------------------------//
        //----------------------- read Cation -----------------------//
        cation = new HashMap<String, Cation>();
        
        line = null;
        try{
            FileReader fileReader = new FileReader(CationPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {                
                String v[] = line.split("\\s+");                                                        
                Cation temp = new Cation(nguyenTo.get(v[0]), Integer.valueOf(v[1]));
                cation.put(temp.getKey(), temp);
            }                   
            
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                CationPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + CationPath + "'");                              
        }
        //--------------------------------------------------------------------//
        //----------------------- read Don chat -----------------------//
        donChat = new HashMap<String, DonChat>();
        
        line = null;
        try{
            FileReader fileReader = new FileReader(DonChatPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {                
                String v[] = line.split("\\s+");                                                        
                DonChat temp = new DonChat(nguyenTo.get(v[0]), Integer.valueOf(v[1]));                
                donChat.put(temp.getKey(), temp);
            }                   
            
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                DonChatPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + DonChatPath + "'");                              
        }
        //--------------------------------------------------------------------//
        //----------------------- read Dien Hoa -----------------------//
        dienHoa = new ArrayList <Pair<String, String>>();
        
        line = null;
        try{
            FileReader fileReader = new FileReader(DienHoaPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int cnt = 0;
            String temp1="", temp2="";
            while((line = bufferedReader.readLine()) != null) {                                
                String v[] = line.split("\\s+");                               
                cnt += 1;
                if (cnt % 2 == 1){
                    temp1 = v[0] + "_" + v[1];
                }                
                else{
                    temp2 = v[0] + "_" + v[1];
                    Pair<String, String> e = new Pair<String, String>(temp1, temp2);
                    dienHoa.add(e);
                }                
            }                   
            
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                DienHoaPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + DienHoaPath + "'");                              
        }
        //--------------------------------------------------------------------//
        //----------------------- read Luat -----------------------//
            
        luat = new Vector<Rule>();
        line = null;
        try{
            FileReader fileReader = new FileReader(LuatPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);            
            while((line = bufferedReader.readLine()) != null) {                                
                String rule = line;                
                line = bufferedReader.readLine();               
                int cnt_dk = Integer.valueOf(line);
                List <String> ctd = new Vector<String>();
                for (int i=0; i<cnt_dk; i++){
                    line = bufferedReader.readLine();                
                    ctd.add(line);                    
                }
                line = bufferedReader.readLine();                
                int cnt_use = Integer.valueOf(line);                                
                String use = "";
                if (cnt_use > 0) use = bufferedReader.readLine(); 
                Rule r = new Rule(rule, ctd, use);
                luat.add(r);
            }                   
            
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                LuatPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + LuatPath + "'");                              
        }
        //--------------------------------------------------------------------//
        //----------------------- read Kien thuc -----------------------//
        
        kienThuc = new HashMap<String, List<NguyenTo>>();
        
        line = null;
        try{
            FileReader fileReader = new FileReader(KienThucPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
               
            while((line = bufferedReader.readLine()) != null) {                
                String v[] = line.split("\\s+");                                                        
                List <NguyenTo> temp = new Vector<NguyenTo>();
                for (int i=2; i<v.length; i++){
                    temp.add(nguyenTo.get(v[i]));                    
                }                
                kienThuc.put(v[0], temp);
            }                   
            
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                KienThucPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + KienThucPath + "'");                              
        }
        
        //--------------------------------------------------------------------//
        //----------------------- read Khong ben hoac khong ton tai -----------------------//
        
        notExist = new HashMap<String, HopChat>();
        
        line = null;
        try{
            FileReader fileReader = new FileReader(NotExistPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
               
            while((line = bufferedReader.readLine()) != null) {                                                                
                String []v = line.split("\\s");                    
                HopChat temp = new HopChat(cation.get(v[0]), anion.get(v[1]));
                notExist.put(temp.getCTHH(), temp);                
            }                               
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                NotExistPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + NotExistPath + "'");                              
        }
        
        //--------------------------------------------------------------------//
        //----------------------- read khong tan -----------------------//
        
        khongTan = new HashMap<String, HopChat>();
        
        line = null;
        try{
            FileReader fileReader = new FileReader(KhongTanPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
               
            while((line = bufferedReader.readLine()) != null) {                                                                
                String []v = line.split("\\s");                             
                HopChat temp = new HopChat(cation.get(v[0]), anion.get(v[1]), v[2]);
                khongTan.put(temp.getCTHH(), temp);
            }                               
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                KhongTanPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + KhongTanPath + "'");                              
        }                
        
        //--------------------------------------------------------------------//
        
        //--------------------------------read khi ------------------------------------//
        khi = new HashMap<String, Boolean>();
        
        line = null;
        try{
            FileReader fileReader = new FileReader(KhiPath);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {                                                                               
                String []v = line.split("\\s");                    
                HopChat temp = new HopChat(cation.get(v[0]), anion.get(v[1]));
                khi.put(temp.getCTHH(), true);
            }                               
            bufferedReader.close();         
        }        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                KhiPath + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + KhiPath + "'");                              
        }
        
        //--------------------------------------------------------------------//
    }
    public static HopChat getHC(String X){                
        for (Anion an : anion.values()){
            for (Cation ca : cation.values()){
                HopChat tmp = new HopChat(ca, an);
                if (tmp.getCTHH().equals(X)) return tmp;
            }
        }
        return null;
    }
    public static DonChat getDC(String X){
        for (DonChat dc : donChat.values()){
            if (dc.getCTHH().equals(X)) return dc;
        }
        return null;
    }
    
    public static Cation getCationFromName(String name){
        int min = 8;
        Cation ans = knowledge.cation.get("H_1");
        for (String key: knowledge.cation.keySet()){
            Cation choose = knowledge.cation.get(key);
            if (choose.getSymbol().equals(name) &&
                min > choose.getHoaTri()) {
                ans = choose;
                min = choose.getHoaTri();
            }
        }
        return ans;
    }
    
    public static int getHighestCation(String name){        
        int max = 0;
        for (String key: knowledge.cation.keySet()){
            if (knowledge.cation.get(key).getSymbol().equals(name)) 
                if (max < knowledge.cation.get(key).getHoaTri()){
                    max = knowledge.cation.get(key).getHoaTri();                    
                }
        }
        return max;
    }
    
    public static Anion getAnionFromName(String name){        
        Anion result = knowledge.anion.get("OH_1");
        for (String key: knowledge.anion.keySet()){
            if (knowledge.anion.get(key).getSymbol().equals(name)) result = knowledge.anion.get(key);            
        }        
        return result;
    }
    
    public static String getKeyDonChatFromName(String name){
        for (String key: knowledge.donChat.keySet()){
            if (knowledge.donChat.get(key).getCTHH().equals(name)) return key;
        }
        return "";
    }
    
    public static DonChat getDonChatFromDaiDien(NguyenTo dd){
        for (String key: knowledge.donChat.keySet()){
            if (knowledge.donChat.get(key).getDaiDien().getSymbol().equals(dd.getSymbol())) return knowledge.donChat.get(key);
        }
        return knowledge.donChat.get("O_2");
    }  

    public static DonChat getDonChatFromAnion(Anion an){
        DonChat result = knowledge.donChat.get("O_2");
        String name = an.getSymbol();
        for (int i=1; i<=5; i++){
            String temp = name + "_" + String.valueOf(i);
            if (knowledge.donChat.containsKey(temp)) {
                result = knowledge.donChat.get(temp);
                break;
            }
        }
        return result;
    }
    
    public static int posOfDienHoa2(String X){
        for (int i=0; i<knowledge.dienHoa.size(); i++){
            if (knowledge.dienHoa.get(i).getValue().equals(X)) return i;
        }
        return -1;
    }
    
    public static int posOfDienHoa1(String X){
        for (int i=0; i<knowledge.dienHoa.size(); i++){
            if (knowledge.dienHoa.get(i).getKey().equals(X)) return i;
        }
        return -1;
    }
}
