
package process;

import Basic.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javafx.util.*;

public class pu {
    private static final String NguyenToPath = "D:\\src\\knowledge\\nguyento.txt";
    private static final String AnionPath = "D:\\src\\knowledge\\anion.txt";
    private static final String CationPath = "D:\\src\\knowledge\\cation.txt";
    private static final String DonChatPath = "D:\\src\\knowledge\\donchat.txt";
    private static final String DienHoaPath = "D:\\src\\knowledge\\dienhoa.txt";
    private static final String LuatPath = "D:\\src\\knowledge\\luat.txt";
    private static final String KienThucPath = "D:\\src\\knowledge\\class.txt";
    private static final String NotExistPath = "D:\\src\\knowledge\\khongtontai.txt";
    private static final String KhongTanPath = "D:\\src\\knowledge\\khongtan.txt";
    private static final String KhiPath = "D:\\src\\knowledge\\khi.txt";
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
                NguyenTo temp = new NguyenTo(v[0], Float.valueOf(v[1]), kl);
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
                anion.put(v[0] + "_" + v[2], temp);
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
                cation.put(v[0] + "_" + v[1], temp);
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
                String name = v[0] + "_" + v[1];
                donChat.put(name, temp);
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
                HopChat temp = new HopChat(cation.get(v[0]), anion.get(v[1]));
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
    public static boolean hasValue(String key, NguyenTo X){        
        for (int i=0; i<kienThuc.get(key).size(); i++){            
            if (kienThuc.get(key).get(i).getSymbol().equals(X.getSymbol())) return true;
        }
        return false;
    }  
    
    public static boolean checkNameInNguyenTo(String X){
        for (String key: nguyenTo.keySet()){
            if (nguyenTo.get(key).getSymbol().equals(X)) return true;
        }
        return false;
    }
    
    public static boolean checkNameInAnion(String X){
        for (String key: anion.keySet()){
            if (anion.get(key).getSymbol().equals(X)) return true;
        }
        return false;
    }
    
    public static boolean checkNameInCation(String X){
        for (String key: cation.keySet()){
            if (cation.get(key).getSymbol().equals(X)) return true;
        }
        return false;
    }
    
    public static boolean checkNameInDonChat(String X){
        for (String key: donChat.keySet()){
            if (donChat.get(key).getCTHH().equals(X)) return true;
        }
        return false;
    }
    
    public static boolean checkForHC(String y){
        for (int i=0; i<y.length(); i++){
            if (y.charAt(i) == '_') return true;
        }
        return false;
    }
    
    public static boolean checkForDC(String x){
        if (x.length() > 1 && !checkNameInDonChat(x)) return false;                
        return true;
    }
    
    public static boolean dchc(String x, String y){        
        return checkForDC(x) && checkForHC(y);
    }    
    
    public static boolean hchc(String x, String y){
        return checkForHC(x) && checkForHC(y);
    }
    
    public static boolean checkRule(List <String> listOfCondition, 
                                    List <String> listOfVariables,
                                    List <String> listOfReferences,
                                    List <String> listOfType){             
        for (String cdt: listOfCondition){
            String []v = cdt.split("\\s");            
            if (v.length == 2){
                if (checkForHC(v[1])){
                    Pair <String, String> get = takeCA(v[1]);
                    String c = get.getKey();
                    String a = get.getValue();
                    int t = checkIn(c, listOfVariables);
                    Cation C;
                    Anion A;                    
                    if (t > -1){                        
                        C = cation.get(listOfReferences.get(t));
                        A = getAnionFromName(a);                           
                    }
                    else{
                        t = checkIn(a, listOfVariables);
                        C = getCationFromName(c);
                        A = anion.get(listOfReferences.get(t));
                    }                    
                    HopChat hc = new HopChat(C, A);
                    if (v[0].equals("KETTUA")){
                        return khongTan.containsKey(hc.getCTHH());                        
                    }
                    else if (v[0].equals("KB")){
                        return notExist.containsKey(hc.getCTHH());
                    }
                }
                else{
                    int t = checkIn(v[1], listOfVariables);                
                    if (listOfType.get(t) == "dc"){                                        
                        NguyenTo x = donChat.get(getKeyDonChatFromName(listOfReferences.get(t))).getDaiDien();                      
                        if (!hasValue(v[0], x)) return false;                    
                    }                
                    else{
                        if (listOfType.get(t) == "ca"){
                            NguyenTo x = cation.get(listOfReferences.get(t)).getDaiDien();
                            if (!hasValue(v[0], x)) return false;
                        }                    
                    }    
                }
            }
            else{
                if (v[1].equals("#")){
                    int t = checkIn(v[0], listOfVariables);
                    Anion an = anion.get(listOfReferences.get(t));                                        
                    if (an.getSymbol().equals(v[2])) return false;                    
                }
                else{
                    if (v[0].equals("DH")){
                        int t = checkIn(v[1], listOfVariables);
                        String X;
                        if (t > -1){
                            X = listOfReferences.get(t) + "_0";
                        }
                        else X = v[1] + "_0";
                        String Y;
                        t = checkIn(v[2], listOfVariables);
                        if (t > -1){
                            Y = listOfReferences.get(t);                            
                        }                        
                        else{
                            Y = v[2] + "_0";
                        }                        
                        int t1 = posOfDienHoa2(X);
                        int t2 = posOfDienHoa1(Y);                                                
                        if (t1 >= t2) return false;                        
                        if (t1 == -1 || t2 == -1) return false;
                    }
                }
                
            }
        }
        return true;
    }
    
    public static Pair<String, String> takeCA(String X){        
        int index = X.indexOf("_");
        String c = X.substring(0, index);
        String a = X.substring(index + 1, X.length());
        Pair <String, String> e = new Pair<String, String>(c, a);
        return e;
    }
    
    public static int checkIn(String X, List <String> L){
        for (int i=0; i<L.size(); i++){
            if (L.get(i).equals(X)) return i;
        }
        return -1;
    }
    
    public static Cation getCationFromName(String name){
        for (String key: cation.keySet()){
            if (cation.get(key).getSymbol().equals(name)) return cation.get(key);
        }
        return cation.get("H_1");
    }
    
    public static int getHighestCation(String name){        
        int max = 0;
        for (String key: cation.keySet()){
            if (cation.get(key).getSymbol().equals(name)) 
                if (max < cation.get(key).getHoaTri()){
                    max = cation.get(key).getHoaTri();                    
                }
        }
        return max;
    }
    
    public static Anion getAnionFromName(String name){        
        Anion result = anion.get("OH_1");
        for (String key: anion.keySet()){
            if (anion.get(key).getSymbol().equals(name)) result = anion.get(key);            
        }        
        return result;
    }
    
    public static String getKeyDonChatFromName(String name){
        for (String key: donChat.keySet()){
            if (donChat.get(key).getCTHH().equals(name)) return key;
        }
        return "";
    }
    
    public static DonChat getDonChatFromDaiDien(NguyenTo dd){
        for (String key: donChat.keySet()){
            if (donChat.get(key).getDaiDien().getSymbol().equals(dd.getSymbol())) return donChat.get(key);
        }
        return donChat.get("O_2");
    }  

    public static DonChat getDonChatFromAnion(Anion an){
        DonChat result = donChat.get("O_2");
        String name = an.getSymbol();
        for (int i=1; i<=5; i++){
            String temp = name + "_" + String.valueOf(i);
            if (donChat.containsKey(temp)) {
                result = donChat.get(temp);
                break;
            }
        }
        return result;
    }
    
    public static int posOfDienHoa2(String X){
        for (int i=0; i<dienHoa.size(); i++){
            if (dienHoa.get(i).getValue().equals(X)) return i;
        }
        return -1;
    }
    
    public static int posOfDienHoa1(String X){
        for (int i=0; i<dienHoa.size(); i++){
            if (dienHoa.get(i).getKey().equals(X)) return i;
        }
        return -1;
    }
    
    public static boolean isACation(String X){
        return (X.charAt(X.length() - 1) != '0');
    }
    
    public static List<String> create(  String rule, 
                                List <String> listOfVariables,
                                List <String> listOfReferences,
                                List <String> listOfType,
                                String useage){
                
        List <String> result = new Vector<String>();        
        if (useage.equals("alpha")){
            int t = checkIn("X", listOfVariables);
            String X = listOfReferences.get(t) + "_0";                                          

            t = checkIn("Y", listOfVariables);                
            String Y = listOfReferences.get(t);         

            t = checkIn("Z", listOfVariables);                
            String Z = listOfReferences.get(t);

            int t1 = posOfDienHoa2(X);
            int t2 = posOfDienHoa1(Y);                     
            //System.out.println(X + " " + Y);
            X = dienHoa.get(t1).getKey();
            Y = dienHoa.get(t2).getValue();
            //System.out.println(X + " " + Y);

            Cation F = cation.get(X);
            Anion S = anion.get(Z);

            HopChat result1 = new HopChat(F, S);

            //System.out.print(result1.getCTHH());         
            result.add(result1.getCTHH());
            if (!X.equals(Y)){                    
                if (isACation(Y)){
                    Cation new_F = cation.get(Y);
                    HopChat result2 = new HopChat(new_F, S);
                    //System.out.print(result2.getCTHH());        
                    result.add(result2.getCTHH());
                }
                else{
                    Y = Y.replace('0', '1');
                    if (!donChat.containsKey(Y)) Y = Y.replace('1', '2');
                    //System.out.print(donChat.get(Y).getCTHH()); 
                    result.add(donChat.get(Y).getCTHH());
                }
            }
            //System.out.println();                
            return result;
        }        
        if (useage.equals("beta")){
            int t = checkIn("X", listOfVariables);
            String X = listOfReferences.get(t);
            Cation XC = cation.get(X);            
            Cation F = cation.get("H_1");
            Anion S = anion.get("OH_1");
            for (String k: anion.keySet()){
                Anion a = anion.get(k);    
                if (a.differ().equals(XC.getSymbol())){                        
                    if (a.getOxiHoa() == XC.getHoaTri()){                        
                        S = a;
                        break;
                    }
                }
            }  
            HopChat hc = new HopChat(F, S);
            //System.out.println(hc.getCTHH());
            result.add(hc.getCTHH());            
            return result; 
        }            
               
        if (useage.equals("theta")){                        
            int t = checkIn("X", listOfVariables);                     
            String a = listOfReferences.get(t);
            String c = anion.get(a).differ() + "_" + anion.get(a).getOxiHoa();
            Cation C = cation.get(c);
            HopChat hc = new HopChat(C, anion.get("O_2"));
            HopChat H2O = new HopChat(cation.get("H_1"), anion.get("OH_1"));
            result.add(hc.getCTHH());
            result.add(H2O.getCTHH());
            return result;
        }
                
        if (useage.equals("oxihoa")){
            int t = checkIn("X", listOfVariables);                     
            String c = listOfReferences.get(t);            
            Cation C = cation.get(c);
            int Highest = getHighestCation(C.getSymbol());
            if (Highest > C.getHoaTri()){
                C = cation.get(C.getSymbol() + "_" + Highest);
            }
            HopChat hc = new HopChat(C, anion.get("O_2"));            
            result.add(hc.getCTHH());            
            return result;
        }
        
        String[] v = rule.split("\\s");               
        int index = 0;
        for (int i=0; i<v.length; i++) 
            if (v[i].equals("=")){
                index = i + 1;
                break;
            }                       
        
        for (int i=index; i<v.length; i++){
            if (checkForDC(v[i])){                
                int t = checkIn(v[i], listOfVariables);
                if (t > -1){                    
                    DonChat d;
                    if (listOfType.get(t) == "ca"){
                        NguyenTo dd = cation.get(listOfReferences.get(t)).getDaiDien();
                        d = getDonChatFromDaiDien(dd);
                    }
                    else
                        if (listOfType.get(t) == "an"){
                            String name = listOfReferences.get(t);    
                            d = getDonChatFromAnion(anion.get(name));
                        }
                        else{
                            d = donChat.get(listOfReferences.get(t));                    
                        }
                    
                    result.add(d.getCTHH());                    
                }
                else{
                    result.add(v[i]);
                }
            }
            else{                
                Pair <String, String> get = takeCA(v[i]);
                String c = get.getKey();
                String a = get.getValue();                
                int t = checkIn(c, listOfVariables);                
                Cation ca;
                if (t > -1){     
                    if (listOfType.get(t) == "ca")
                        ca = cation.get(listOfReferences.get(t));
                    else{                        
                        ca = getCationFromName(listOfReferences.get(t));
                    }
                }
                else{
                    ca = getCationFromName(c);
                }                                
                Anion an = anion.get("OH_1");
                t = checkIn(a, listOfVariables);
                if (t > -1){
                    if (listOfType.get(t) == "an")
                        an = anion.get(listOfReferences.get(t));
                    else{
                        if (listOfType.get(t) == "dc"){
                            String key = getKeyDonChatFromName(listOfReferences.get(t));
                            NguyenTo temp = donChat.get(key).getDaiDien();                            
                            an = getAnionFromName(temp.getSymbol());                            
                        }
                    }
                }
                else{
                    an = getAnionFromName(a);
                }                                                
                HopChat hc = new HopChat(ca, an);                
                result.add(hc.getCTHH());                       
            }
        }
        for (int i=0; i<result.size() - 1; i++){
            //System.out.print(result.get(i) + " + ");
        }
        //System.out.println(result.get(result.size() - 1));
        return result;
    }
    
    public static List<String> checkAfter(List <String> L){              
        List <String> result = new Vector<String>();
        boolean ok = false;        
        for (int i=0; i<L.size(); i++){
            String X = L.get(i);
            if (notExist.containsKey(X) || khongTan.containsKey(X)){
                ok = true;
                break;
            }
        }
        if (!ok) return result;                
        for (int i=0; i<L.size(); i++){            
            String X = L.get(i);            
            if (notExist.containsKey(X)){                
                List <String> v = pu(notExist.get(X));                
                for (int j=0; j<v.size(); j++) 
                    result.add(v.get(j));                
            }
            else{
                result.add(X);
            }                        
        }
        return result;
    }
    
    public static List<String> pu(HopChat X){        
        List <String> result = new Vector<String>();
        for (int i=0; i<luat.size(); i++){
            String[] v = luat.get(i).getRule().split("\\s");             
            if (checkForHC(v[0]) && (v.length == 1 || v[1].equals("="))){                                   
                Pair <String, String> get = takeCA(v[0]);                
                String c = get.getKey();
                String a = get.getValue();
                //System.out.println(X.getCTHH());
                //System.out.print(c);
                //System.out.println(a);
                String my_cation = X.getCation().getSymbol();
                String my_anion = X.getAnion().getSymbol();
                //System.out.print(my_cation0);
                //System.out.println(my_anion0);
                if (checkNameInCation(c) && !my_cation.equals(c)) continue;
                if (checkNameInAnion(a) && !my_anion.equals(a)) continue;
                
                my_cation = X.getCation().getSymbol() + "_" + X.getCation().getHoaTri();
                my_anion = X.getAnion().getSymbol() + "_" + X.getAnion().getHoaTri();                
                
                List <String> listOfCondition = luat.get(i).getCdt();
                List <String> listOfVariables = new Vector<String>();
                List <String> listOfReferences = new Vector<String>();
                List <String> listOfType = new Vector<String>();
                String useage = luat.get(i).getUse();
                //System.out.println(my_cation0 + " " + my_anion0 + "; " + my_cation1 + " " + my_anion1);
                
                if (!checkNameInCation(c)){
                    listOfVariables.add(c);
                    listOfReferences.add(my_cation);
                    listOfType.add("ca");
                }
                if (!checkNameInAnion(a)){
                    listOfVariables.add(a);
                    listOfReferences.add(my_anion);
                    listOfType.add("an");
                }
                
                //System.out.println(luat.get(i).getRule());
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                    
                    String rule = luat.get(i).getRule();                                          
                    //System.out.println(rule);                    
                    List <String> temp = create(rule, listOfVariables, listOfReferences, listOfType, useage);                                    
                    return temp;                    
                }                                             
            }
        }
        //System.out.println("Can not make a reaction!");
        return result;
    }
    
    public static List<String> pu(DonChat X, HopChat Y){
        //System.out.print(X.getCTHH() + " + " + Y.getCTHH() + " = "); 
        List <String> result = new Vector<String>();
        for (int i=0; i<luat.size(); i++){
            String[] v = luat.get(i).getRule().split("\\s"); 
            if (v.length == 1) continue;
            if (dchc(v[0], v[1])){                            
                Pair <String, String> get = takeCA(v[1]);
                String c = get.getKey();
                String a = get.getValue();
                String my_cation = Y.getCation().getSymbol();
                String my_anion = Y.getAnion().getSymbol();
                // Đơn chất để ở dạng công thức hóa học nha
                if (checkNameInCation(c) && !my_cation.equals(c)) continue;
                if (checkNameInAnion(a) && !my_anion.equals(a)) continue;
                if (checkNameInDonChat(v[0]) && !v[0].equals(X.getCTHH())) continue;                                               
                
                my_cation = Y.getCation().getSymbol() + "_" + Y.getCation().getHoaTri();
                my_anion = Y.getAnion().getSymbol() + "_" + Y.getAnion().getHoaTri();                
                List <String> listOfCondition = luat.get(i).getCdt();
                List <String> listOfVariables = new Vector<String>();
                List <String> listOfReferences = new Vector<String>();
                List <String> listOfType = new Vector<String>();
                String useage = luat.get(i).getUse();
                
                if (!checkNameInDonChat(v[0])){
                    listOfVariables.add(v[0]);                    
                    listOfReferences.add((X.getCTHH()));                    
                    listOfType.add("dc");
                }
                if (!checkNameInCation(c)){
                    listOfVariables.add(c);
                    listOfReferences.add(my_cation);
                    listOfType.add("ca");
                }
                if (!checkNameInAnion(a)){
                    listOfVariables.add(a);
                    listOfReferences.add(my_anion);
                    listOfType.add("an");
                }                                                   
                
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                                           
                    String rule = luat.get(i).getRule();                                            
                    result = create(rule, listOfVariables, listOfReferences, listOfType, useage);                    
                    System.out.println(" --- use rule: " + rule);
                    return result;
                }                                                    
            }
        }
        //System.out.println("Can not make a reaction!");
        return result;
    }
    
    public static List<String> pu(DonChat X, DonChat Y){
        //System.out.print(X.getCTHH() + " + " + Y.getCTHH() + " = ");
        List <String> result = new Vector<String>();
        for (int i=0; i<luat.size(); i++){
            String[] v = luat.get(i).getRule().split("\\s");             
            if (checkForDC(v[0]) && checkForDC(v[1])){           
               
                // Đơn chất để ở dạng công thức hóa học nha                
                if (checkNameInDonChat(v[0]) && !v[0].equals(X.getCTHH())) continue;                
                if (checkNameInDonChat(v[1]) && !v[1].equals(Y.getCTHH())) continue;
                
                List <String> listOfCondition = luat.get(i).getCdt();
                List <String> listOfVariables = new Vector<String>();
                List <String> listOfReferences = new Vector<String>();
                List <String> listOfType = new Vector<String>();
                String useage = luat.get(i).getUse();
                
                if (!checkNameInDonChat(v[0])){
                    listOfVariables.add(v[0]);                    
                    listOfReferences.add((X.getCTHH()));                    
                    listOfType.add("dc");
                }
                if (!checkNameInDonChat(v[1])){
                    listOfVariables.add(v[1]);                    
                    listOfReferences.add((Y.getCTHH()));                    
                    listOfType.add("dc");
                }                                
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                      
                    String rule = luat.get(i).getRule();                               
                    result = create(rule, listOfVariables, listOfReferences, listOfType, useage);
                    //System.out.println();
                    return result;
                }                    
                                
            }
        }
        return result;
        //System.out.println("Can not make a reaction!");        
    }
    public static List<String> pu(HopChat X, HopChat Y){
        //System.out.print(X.getCTHH() + " + " + Y.getCTHH() + " = ");  
        List <String> result = new Vector<String>();
        for (int i=0; i<luat.size(); i++){            
            String[] v = luat.get(i).getRule().split("\\s");                         
            if (v.length == 1) continue;
            if (hchc(v[0], v[1])){                            
                Pair <String, String> get = takeCA(v[0]);
                String c0 = get.getKey();
                String a0 = get.getValue();                
                //System.out.print(c0);
                //System.out.println(a0);
                String my_cation0 = X.getCation().getSymbol();
                String my_anion0 = X.getAnion().getSymbol();
                //System.out.print(my_cation0);
                //System.out.println(my_anion0);
                if (checkNameInCation(c0) && !my_cation0.equals(c0)) continue;
                if (checkNameInAnion(a0) && !my_anion0.equals(a0)) continue;
                get = takeCA(v[1]);
                String c1 = get.getKey();
                String a1 = get.getValue();
                //System.out.print(c1);
                //System.out.println(a1);
                String my_cation1 = Y.getCation().getSymbol();
                String my_anion1 = Y.getAnion().getSymbol();
                //System.out.print(my_cation1);
                //System.out.println(my_anion1);
                // Đơn chất để ở dạng công thức hóa học nha
                if (checkNameInCation(c1) && !my_cation1.equals(c1)) continue;
                if (checkNameInAnion(a1) && !my_anion1.equals(a1)) continue;                                                            
                
                my_cation0 = X.getCation().getSymbol() + "_" + X.getCation().getHoaTri();
                my_anion0 = X.getAnion().getSymbol() + "_" + X.getAnion().getHoaTri();                
                my_cation1 = Y.getCation().getSymbol() + "_" + Y.getCation().getHoaTri();
                my_anion1 = Y.getAnion().getSymbol() + "_" + Y.getAnion().getHoaTri();                
                List <String> listOfCondition = luat.get(i).getCdt();
                List <String> listOfVariables = new Vector<String>();
                List <String> listOfReferences = new Vector<String>();
                List <String> listOfType = new Vector<String>();
                String useage = luat.get(i).getUse();
                //System.out.println(my_cation0 + " " + my_anion0 + "; " + my_cation1 + " " + my_anion1);
                
                if (!checkNameInCation(c0)){
                    listOfVariables.add(c0);
                    listOfReferences.add(my_cation0);
                    listOfType.add("ca");
                }
                if (!checkNameInAnion(a0)){
                    listOfVariables.add(a0);
                    listOfReferences.add(my_anion0);
                    listOfType.add("an");
                }
                if (!checkNameInCation(c1)){
                    listOfVariables.add(c1);
                    listOfReferences.add(my_cation1);
                    listOfType.add("ca");
                }
                if (!checkNameInAnion(a1)){
                    listOfVariables.add(a1);
                    listOfReferences.add(my_anion1);
                    listOfType.add("an");
                }                                 
                
                //System.out.println(luat.get(i).getRule());
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                                     
                    String rule = luat.get(i).getRule();                                          
                    //System.out.println(rule);                    
                    List <String> temp = create(rule, listOfVariables, listOfReferences, listOfType, useage);   
                    if (useage.equals("after")){                                                   
                        temp = checkAfter(temp);
                        if (temp.size() > 0){                            
                            System.out.println(" --- use rule: " + rule);
                            return temp;
                        }
                    }                    
                    else{
                        return temp;
                    }
                }                                             
            }
        }
        //System.out.println("Can not make a reaction!");
        return result;
    }
}
