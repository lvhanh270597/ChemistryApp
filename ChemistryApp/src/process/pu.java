
package process;

import Basic.*;
import knowledge.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javafx.util.*;

public class pu {
    
    public static boolean hasValue(String key, NguyenTo X){        
        for (int i=0; i<knowledge.kienThuc.get(key).size(); i++){            
            if (knowledge.kienThuc.get(key).get(i).getSymbol().equals(X.getSymbol())) return true;
        }
        return false;
    }  
    
    public static boolean checkNameInNguyenTo(String X){
        for (String key: knowledge.nguyenTo.keySet()){
            if (knowledge.nguyenTo.get(key).getSymbol().equals(X)) return true;
        }
        return false;
    }
    
    public static boolean checkNameInAnion(String X){
        for (String key: knowledge.anion.keySet()){
            if (knowledge.anion.get(key).getSymbol().equals(X)) return true;
        }
        return false;
    }
    
    public static boolean checkNameInCation(String X){
        for (String key: knowledge.cation.keySet()){
            if (knowledge.cation.get(key).getSymbol().equals(X)) return true;
        }
        return false;
    }
    
    public static boolean checkNameInDonChat(String X){
        for (String key: knowledge.donChat.keySet()){
            if (knowledge.donChat.get(key).getCTHH().equals(X)) return true;
        }
        return false;
    }
    
    public static boolean checkForHC(String y){
        return (y.indexOf("hc") >= 0);
    }
    
    public static boolean checkForDC(String x){
        return (x.indexOf("dc") >= 0);        
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
                        C = knowledge.cation.get(listOfReferences.get(t));
                        A = getAnionFromName(a);                           
                    }
                    else{
                        t = checkIn(a, listOfVariables);
                        C = getCationFromName(c);
                        A = knowledge.anion.get(listOfReferences.get(t));
                    }                    
                    HopChat hc = new HopChat(C, A);
                    if (v[0].equals("KETTUA")){
                        return knowledge.khongTan.containsKey(hc.getCTHH());                        
                    }
                    else if (v[0].equals("KB")){
                        return knowledge.notExist.containsKey(hc.getCTHH());
                    }
                }
                else{
                    int t = checkIn(v[1], listOfVariables);                      
                    if (listOfType.get(t) == "dc"){                                 
                        NguyenTo x = knowledge.donChat.get(getKeyDonChatFromName(listOfReferences.get(t))).getDaiDien();                      
                        if (!hasValue(v[0], x)) return false;                    
                    }                
                    else{
                        if (listOfType.get(t) == "ca"){                                                           
                            NguyenTo x = knowledge.cation.get(listOfReferences.get(t)).getDaiDien();                                                           
                            if (!hasValue(v[0], x)) return false;
                        }                    
                    }    
                }
            }
            else{
                if (v[1].equals("#")){
                    int t = checkIn(v[0], listOfVariables);
                    Anion an = knowledge.anion.get(listOfReferences.get(t));    
                    //print(listOfReferences.get(t) + " " + v[2]);                    
                    if (an != null && an.getSymbol().equals(v[2])) return false;                    
                    Cation ca = knowledge.cation.get(listOfReferences.get(t));
                    if (ca != null && ca.getSymbol().equals(v[2])) return false;
                }
                else{
                    if (v[0].equals("DH")){
                        int t = checkIn(v[1], listOfVariables);
                        String X;
                        if (t > -1){                            
                            X = listOfReferences.get(t);                            
                        }
                        else X = v[1];
                        if (!isACation(X)) X = X + "_0";                                                    
                        String Y;
                        t = checkIn(v[2], listOfVariables);
                        if (t > -1){
                            Y = listOfReferences.get(t);                            
                        }                        
                        else{
                            Y = v[2];                                                    
                        }            
                        
                        if (!isACation(Y)) Y += "_0";    
                                                
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
    
    public static void print(String X){
        System.out.print(X);
    }
    
    public static Pair<String, String> takeCA(String X){        
        X = X.substring(3, X.length() - 1);        
        int index = X.indexOf(",");
        String c = X.substring(0, index);
        String a = X.substring(index + 1, X.length());        
        Pair <String, String> e = new Pair<String, String>(c, a);
        
        return e;
    }
    
    public static String bocVo(String X){
        return X.substring(3, X.length() - 1);        
    }
    
    public static int checkIn(String X, List <String> L){
        for (int i=0; i<L.size(); i++){
            if (L.get(i).equals(X)) return i;
        }
        return -1;
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
    
    public static HopChat map(Cation XC){
        Cation F = knowledge.cation.get("H_1");
        Anion S = knowledge.anion.get("OH_1");
        // tìm một gốc axit tương ứng
        for (String k: knowledge.anion.keySet()){
            Anion a = knowledge.anion.get(k);    
            if (a.differ().equals(XC.getSymbol())){                        
                if (a.getOxiHoa() == XC.getHoaTri()){                        
                    S = a;
                    break;
                }
            }
        }  
        HopChat hc = new HopChat(F, S);
        return hc;
    }
    
    public static boolean isACation(String X){
        return (X.charAt(X.length() - 1) != '0') && (X.indexOf("_") > -1);
    }
    
    public static List<String> create(  String rule, 
                                List <String> listOfVariables,
                                List <String> listOfReferences,
                                List <String> listOfType,
                                String useage){
                
        List <String> result = new Vector<String>();        
        if (useage.equals("alpha")){            
            String X = listOfReferences.get(0) + "_0";                                          
            //print(X+"\n");            
            String Y = listOfReferences.get(1);         
            //print(Y+"\n");            
            String Z = listOfReferences.get(2);
            //print(Z+"\n");
            //if (Z.equals("O_2")) return result;
            
            int t1 = posOfDienHoa2(X);
            int t2 = posOfDienHoa1(Y);                     
            //System.out.println(X + " " + Y);
            X = knowledge.dienHoa.get(t1).getKey();
            Y = knowledge.dienHoa.get(t2).getValue();
            //System.out.println(X + " " + Y);

            Cation F = knowledge.cation.get(X);
            Anion S = knowledge.anion.get(Z);

            HopChat result1 = new HopChat(F, S);

            //System.out.print(result1.getCTHH());         
            result.add(result1.getCTHH());
            if (!X.equals(Y)){                    
                if (isACation(Y)){
                    Cation new_F = knowledge.cation.get(Y);
                    HopChat result2 = new HopChat(new_F, S);
                    //System.out.print(result2.getCTHH());        
                    result.add(result2.getCTHH());
                }
                else{
                    Y = Y.replace('0', '1');
                    if (!knowledge.donChat.containsKey(Y)) Y = Y.replace('1', '2');
                    //System.out.print(donChat.get(Y).getCTHH()); 
                    result.add(knowledge.donChat.get(Y).getCTHH());
                }
            }
            //System.out.println();                
            return result;
        }        
        if (useage.equals("beta")){                                       
            String X = listOfReferences.get(0);
            Cation XC = knowledge.cation.get(X);            
            HopChat hc = map(XC);            
            if (!hc.getAnion().getSymbol().equals("OH")) 
                result.add(hc.getCTHH());            
            return result; 
        }            
               
        if (useage.equals("beta2")){
            String X = listOfReferences.get(0);
            String Y = listOfReferences.get(1);
            Cation XC = knowledge.cation.get(X);            
            HopChat hc1 = map(XC);            
            HopChat hc2 = new HopChat(knowledge.cation.get(Y), knowledge.anion.get("OH_1"));
            return pu(hc1, hc2);
        }
        
        if (useage.equals("phanhuy")){                        
            int t = 0;
            String a = listOfReferences.get(t);
            String c = knowledge.anion.get(a).differ() + "_" +knowledge. anion.get(a).getOxiHoa();
            Cation C = knowledge.cation.get(c);
            HopChat hc = new HopChat(C, knowledge.anion.get("O_2"));
            HopChat H2O = new HopChat(knowledge.cation.get("H_1"), knowledge.anion.get("O_2"));
            result.add(hc.getCTHH());
            result.add(H2O.getCTHH());
            return result;
        }
                
        if (useage.equals("oxihoa")){
            int t = 0;
            String c = listOfReferences.get(t);            
            Cation C = knowledge.cation.get(c);
            int Highest = getHighestCation(C.getSymbol());
            if (Highest > C.getHoaTri()){
                C = knowledge.cation.get(C.getSymbol() + "_" + Highest);
                HopChat hc = new HopChat(C, knowledge.anion.get("O_2"));            
                result.add(hc.getCTHH());                            
            }
            return result;                        
        }
              
        
    //----------------------------------------------------------------------------//    
        String[] v = rule.split("\\s");               
        int index = 0;
        for (int i=0; i<v.length; i++) 
            if (v[i].equals("=")){
                index = i + 1;
                break;
            }                       
                    
        for (int i=index; i<v.length; i++){            
            if (checkForDC(v[i])){      
                v[i] = bocVo(v[i]);                
                int t = checkIn(v[i], listOfVariables);                
                if (t > -1){                    
                    DonChat d;
                    if (listOfType.get(t) == "ca"){                        
                        NguyenTo dd = knowledge.cation.get(listOfReferences.get(t)).getDaiDien();
                        d = getDonChatFromDaiDien(dd);
                    }
                    else
                        if (listOfType.get(t) == "an"){
                            String name = listOfReferences.get(t);    
                            d = getDonChatFromAnion(knowledge.anion.get(name));
                        }
                        else{                            
                            d = knowledge.donChat.get(listOfReferences.get(t));                    
                        }                   
                    result.add(d.getCTHH());                    
                }
                else{                                        
                    result.add(knowledge.donChat.get(v[i]).getCTHH());
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
                        ca = knowledge.cation.get(listOfReferences.get(t));
                    else{                        
                        ca = getCationFromName(listOfReferences.get(t));
                    }
                }
                else{
                    ca = knowledge.cation.get(c);
                }                                
                Anion an;
                t = checkIn(a, listOfVariables);
                if (t > -1){
                    if (listOfType.get(t) == "an"){
                        an = knowledge.anion.get(listOfReferences.get(t));                        
                    }
                    else{                                               
                        String key = getKeyDonChatFromName(listOfReferences.get(t));
                        NguyenTo temp = knowledge.donChat.get(key).getDaiDien();                            
                        an = getAnionFromName(temp.getSymbol());                                  
                    }
                }
                else{
                    an = knowledge.anion.get(a);
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
            if (knowledge.notExist.containsKey(X) || knowledge.khongTan.containsKey(X)){
                ok = true;
                break;
            }
        }
        if (!ok) return result;                
        for (int i=0; i<L.size(); i++){            
            String X = L.get(i);            
            if (knowledge.notExist.containsKey(X)){                
                List <String> v = pu(knowledge.notExist.get(X));                                
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
        for (int i=0; i<knowledge.luat.size(); i++){
            String[] v = knowledge.luat.get(i).getRule().split("\\s");                   
            if (checkForHC(v[0]) && (v.length == 1 || v[1].equals("="))){                                   
                //System.out.println(luat.get(i).getRule());
                Pair <String, String> get = takeCA(v[0]);                
                String c = get.getKey();
                String a = get.getValue();                
                
//                System.out.println(X.getCTHH());
//                System.out.println(c);
//                System.out.println(a);
                String my_cation = X.getCation().getKey();
                String my_anion = X.getAnion().getKey();
                //System.out.println(my_cation);
                //System.out.println(my_anion);
                if (knowledge.cation.containsKey(c) && !my_cation.equals(c)) continue;
                if (knowledge.anion.containsKey(a) && !my_anion.equals(a)) continue;                                
                
                List <String> listOfCondition = knowledge.luat.get(i).getCdt();
                List <String> listOfVariables = new Vector<String>();
                List <String> listOfReferences = new Vector<String>();
                List <String> listOfType = new Vector<String>();
                String useage = knowledge.luat.get(i).getUse();                
                
                if (!knowledge.cation.containsKey(c)){
                    listOfVariables.add(c);
                    listOfReferences.add(my_cation);
                    listOfType.add("ca");
                }
                if (!knowledge.anion.containsKey(a)){
                    listOfVariables.add(a);
                    listOfReferences.add(my_anion);
                    listOfType.add("an");
                }
                
               // System.out.println(luat.get(i).getRule());
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                    
                    String rule = knowledge.luat.get(i).getRule();                                          
                   // System.out.println("use rule: " + rule);                    
                    List <String> temp = create(rule, listOfVariables, listOfReferences, listOfType, useage);  
                    result = temp;
//                    for (int j=0; j<result.size() - 1; j++){
//                        print(result.get(j) + " + ");
//                    }
//                    if (result.size() > 0) print(result.get(result.size() - 1) + "\n");
                    return result;
                }                                             
            }
        }
        //System.out.println("Can not make a reaction!");
        return result;
    }
    
    public static List<String> pu(DonChat X, HopChat Y){
        //System.out.print(X.getCTHH() + " + " + Y.getCTHH() + " = "); 
        List <String> result = new Vector<String>();
        for (int i=0; i<knowledge.luat.size(); i++){
            String[] v = knowledge.luat.get(i).getRule().split("\\s"); 
            if (v.length == 1) continue;
            if (dchc(v[0], v[1])){                            
                Pair <String, String> get = takeCA(v[1]);
                String c = get.getKey();
                String a = get.getValue();
                String my_cation = Y.getCation().getKey();
                String my_anion = Y.getAnion().getKey();
                // Đơn chất để ở dạng công thức hóa học nha
                
                //print(c + " : " + a + "\n" + my_cation +" : "+my_anion +"\n");
                String dc = bocVo(v[0]);
                
                if (knowledge.cation.containsKey(c) && !my_cation.equals(c)) continue;
                if (knowledge.anion.containsKey(a) && !my_anion.equals(a)) continue;
                
                if (knowledge.donChat.containsKey(dc)){
                    String name = knowledge.donChat.get(dc).getCTHH();
                    if (!name.equals(X.getCTHH())) continue;                                                               
                }
                
                List <String> listOfCondition = knowledge.luat.get(i).getCdt();
                List <String> listOfVariables = new Vector<String>();
                List <String> listOfReferences = new Vector<String>();
                List <String> listOfType = new Vector<String>();
                String useage = knowledge.luat.get(i).getUse();
                
                
                
                if (!knowledge.donChat.containsKey(dc)){                    
                    listOfVariables.add(dc);                    
                    listOfReferences.add((X.getCTHH()));                    
                    listOfType.add("dc");                    
                }
                if (!knowledge.cation.containsKey(c)){                    
                    listOfVariables.add(c);
                    listOfReferences.add(my_cation);
                    listOfType.add("ca");
                }
                if (!knowledge.anion.containsKey(a)){                    
                    listOfVariables.add(a);
                    listOfReferences.add(my_anion);
                    listOfType.add("an");
                }                                                   
                
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                                           
                    String rule = knowledge.luat.get(i).getRule();                                            
                    result = create(rule, listOfVariables, listOfReferences, listOfType, useage);                    
//                    System.out.println(" --- use rule: " + rule);                    
//                    for (int j=0; j<result.size() - 1; j++){
//                        print(result.get(j) + " + ");
//                    }
//                    if (result.size() > 0) print(result.get(result.size() - 1) + "\n");
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
        for (int i=0; i<knowledge.luat.size(); i++){
            String[] v = knowledge.luat.get(i).getRule().split("\\s");             
            if (checkForDC(v[0]) && checkForDC(v[1])){           
                v[0] = bocVo(v[0]);
                v[1] = bocVo(v[1]);
                // Đơn chất để ở dạng công thức hóa học nha                
                if (knowledge.donChat.containsKey(v[0]) && !v[0].equals(X.getCTHH())) continue;                
                if (knowledge.donChat.containsKey(v[1]) && !v[1].equals(Y.getCTHH())) continue;
                
                List <String> listOfCondition = knowledge.luat.get(i).getCdt();
                List <String> listOfVariables = new Vector<String>();
                List <String> listOfReferences = new Vector<String>();
                List <String> listOfType = new Vector<String>();
                String useage = knowledge.luat.get(i).getUse();
                
                if (!knowledge.donChat.containsKey(v[0])){
                    listOfVariables.add(v[0]);                    
                    listOfReferences.add((X.getCTHH()));                    
                    listOfType.add("dc");
                }
                if (!knowledge.donChat.containsKey(v[1])){
                    listOfVariables.add(v[1]);                    
                    listOfReferences.add((Y.getCTHH()));                    
                    listOfType.add("dc");
                }                     
                
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                      
                    String rule = knowledge.luat.get(i).getRule();                               
                    result = create(rule, listOfVariables, listOfReferences, listOfType, useage);
//                    System.out.println(" --- use rule: " + rule);                    
//                    for (int j=0; j<result.size() - 1; j++){
//                        print(result.get(j) + " + ");
//                    }
//                    if (result.size() > 0) print(result.get(result.size() - 1) + "\n");
                    return result;
                }                    
                                
            }
        }
        return result;
        //System.out.println("Can not make a reaction!");        
    }
    public static List<String> pu(HopChat X, HopChat Y){                
        //System.out.print(X.getCTHH() + " + " + Y.getCTHH() + " = \n");  
        List <String> result = new Vector<String>();
        boolean ok = (!knowledge.khongTan.containsKey(X.getCTHH()) || !knowledge.khongTan.containsKey(Y.getCTHH()));
        ok = ok || knowledge.khi.containsKey(X.getCTHH()) || knowledge.khi.containsKey(Y.getCTHH());
        if (!ok){
            return result;
        }        
        for (int i=0; i<knowledge.luat.size(); i++){                        
            String[] v = knowledge.luat.get(i).getRule().split("\\s");  
            
            if (v.length == 1) continue;
            if (hchc(v[0], v[1])){                                 
                Pair <String, String> get = takeCA(v[0]);
                String c0 = get.getKey();
                String a0 = get.getValue();                
                //System.out.println(c0);
                //System.out.println(a0);
                String my_cation0 = X.getCation().getKey();
                String my_anion0 = X.getAnion().getKey();
                //System.out.print(X.getCation().getKey()+"\n");
                //System.out.println(my_anion0);
                if (knowledge.cation.containsKey(c0) && !my_cation0.equals(c0)) continue;
                if (knowledge.anion.containsKey(a0) && !my_anion0.equals(a0)) continue;
                get = takeCA(v[1]);
                String c1 = get.getKey();
                String a1 = get.getValue();
                //System.out.println(c1);
                //System.out.println(a1);
                String my_cation1 = Y.getCation().getKey();
                String my_anion1 = Y.getAnion().getKey();
                //System.out.println(my_cation1);
                //System.out.println(my_anion1);
                // Đơn chất để ở dạng công thức hóa học nha
                if (knowledge.cation.containsKey(c1) && !my_cation1.equals(c1)) continue;
                if (knowledge.anion.containsKey(a1) && !my_anion1.equals(a1)) continue;                                                            
                                
                List <String> listOfCondition = knowledge.luat.get(i).getCdt();
                List <String> listOfVariables = new Vector<String>();
                List <String> listOfReferences = new Vector<String>();
                List <String> listOfType = new Vector<String>();
                String useage = knowledge.luat.get(i).getUse();
                //System.out.println(my_cation0 + " " + my_anion0 + "; " + my_cation1 + " " + my_anion1);                
                if (!knowledge.cation.containsKey(c0)){
                    listOfVariables.add(c0);
                    listOfReferences.add(my_cation0);
                    listOfType.add("ca");
                }
                if (!knowledge.anion.containsKey(a0)){
                    listOfVariables.add(a0);
                    listOfReferences.add(my_anion0);
                    listOfType.add("an");
                }
                if (!knowledge.cation.containsKey(c1)){
                    listOfVariables.add(c1);
                    listOfReferences.add(my_cation1);
                    listOfType.add("ca");
                }
                if (!knowledge.anion.containsKey(a1)){
                    listOfVariables.add(a1);
                    listOfReferences.add(my_anion1);
                    listOfType.add("an");
                }                                                 
                //System.out.println(luat.get(i).getRule());
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                       
                    String rule = knowledge.luat.get(i).getRule();                                          
                    //System.out.println(rule);                    
                    List <String> temp = create(rule, listOfVariables, listOfReferences, listOfType, useage);                       
                    if (useage.equals("after")){                        
                        temp = checkAfter(temp);
                        if (temp.size() > 0){                            
                            //System.out.println(" --- use rule: " + rule);
//                            result = temp;
//                            for (int j=0; j<result.size() - 1; j++){
//                                print(result.get(j) + " + ");
//                            }
//                            if (result.size() > 0) print(result.get(result.size() - 1) + "\n");    
                            return temp;
                        }
                    }                    
                    else{     
                        result = temp;
//                        for (int j=0; j<result.size() - 1; j++){
//                            print(result.get(j) + " + ");
//                        }
//                        if (result.size() > 0) print(result.get(result.size() - 1) + "\n");  
                        return result;
                    }
                }                                             
            }
        }
        //System.out.println("Can not make a reaction!");
        return result;
    }
}
