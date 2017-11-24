
package process;

import Basic.*;
import Math.SoHoc;
import knowledge.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import javafx.util.*;

public class pu {   
    
    private String PTHH;
    private List<DonChat> X;
    private List<HopChat> Y;
    private Map<String, Boolean> Check;
    
    public pu(){}
    public pu(String X){        
        Check = new HashMap<String, Boolean>();
        String [] temp = {"N2O", "NO", "NO2", "N2", "S", "SO2", "H2S"};
        for (String st : temp) Check.put(st, Boolean.TRUE);          
        if (X.indexOf("=") > -1){
            this.PTHH = makeBalance(X);
        }
        else{       
            this.X = new Vector<DonChat> ();
            this.Y = new Vector<HopChat> ();
            String[] thamGia = X.split("\\s");            
            String tmp;
            this.PTHH = "";
            for (int i=0; i<thamGia.length; i++){
                if (thamGia[i].equals("+") || thamGia[i].equals("=")) continue;                
                DonChat dc = knowledge.getDC(thamGia[i]);
                if (dc == null){
                    HopChat hc = knowledge.getHC(thamGia[i]);
                    this.Y.add(hc);
                    tmp = hc.getCTHH();
                }
                else{
                    this.X.add(dc);
                    tmp = dc.getCTHH();
                }
                if (i < thamGia.length - 1){
                    this.PTHH += tmp + " + ";
                }
                else{
                    this.PTHH += tmp + " = ";
                }
            }            
        }
    }
    
    private int pos(String X, List<String> v){
        int res = -1;
        for (int i=0; i<v.size(); i++)
            if (X.equals(v.get(i))) {
                res = i;
                break;
            }
        return res;
    }
    
    private String createReaction(List <List<String>> list, List <List<Integer>> hs, int _index){
        List <String> result_list = new Vector<String>();
        List <Integer> result_hs = new Vector<Integer>();
        
        int n = list.size();
        for (int i=0; i<n; i++){
            List<String> tmp_list = list.get(i);
            List<Integer> tmp_hs = hs.get(i);
            for (int j=0; j<tmp_list.size(); j++){
                int index = pos(tmp_list.get(j), result_list);
                if (index == -1){
                    result_list.add(tmp_list.get(j));
                    result_hs.add(tmp_hs.get(j));                    
                }
                else{
                    int old = result_hs.get(index);
                    int _new = tmp_hs.get(j);
                    result_hs.set(index, old + _new);
                }
            }            
        }
        
        n = result_hs.size();
        int u = result_hs.get(0);
        for (int i=1; i<n; i++) u = SoHoc.gcd(u, result_hs.get(i));
        for (int i=0; i<n; i++){
            int old = result_hs.get(i);
            result_hs.set(i, old / u);
        }
                
        String res = "";
        for (int i=0; i<_index - 2; i++) res += (result_hs.get(i) > 1 ? result_hs.get(i) : "") + result_list.get(i) + " + ";        
        res += (result_hs.get(_index - 2) > 1 ? result_hs.get(_index - 2) : "") + result_list.get(_index - 2) + " = ";        
        for (int i=_index - 1; i<n - 1; i++) res += (result_hs.get(i) > 1 ? result_hs.get(i) : "") + result_list.get(i) + " + ";
        res += (result_hs.get(n - 1) > 1 ? result_hs.get(n - 1) : "") + result_list.get(n - 1);
        return res;
    }
    
    private String makeBalance(String X){
        String[] result = X.split("\\s");
        List <String> all_except = new Vector<String>();
        String patern = "";
        int index = 0;
        for (int i=0; i<result.length; i++){
            patern += result[i] + " ";
            if (result[i].equals("=")) {
                index = i;
                break;
            }
            if (!result[i].equals("=") && !result[i].equals("+")){
                all_except.add(result[i]);
            }
        }
        
        List <String> children = new Vector<String>();
        List <String> except = new Vector<String>();
        for (int i=index + 1; i<result.length; i++){
            String st = result[i];
            if (this.Check.containsKey(st)){
                children.add(st);
            }
            else{
                if (!st.equals("+") && !st.equals("=")){
                    except.add(st);
                    all_except.add(st);
                }
            }                
        }
        
        if (children.size() <= 1){        
            List <Integer> heso = canbang(X);        
            return printReaction(result, heso);        
        }
        else{
            String Y = patern;            
            int n = except.size();            
            for (int i=0; i<n; i++){
                Y += except.get(i) + " + ";                
            }
            
            List <List<String>> list = new Vector<List<String>>();
            List <List<Integer>> hs = new Vector<List<Integer>>();
            n = children.size();
            
            for (int i=0; i<n; i++){
                List <String> str = new Vector<String>(all_except);
                str.add(children.get(i));
                list.add(str);
                
                //System.out.println(str);
                
                List <Integer> cb = canbang(Y + children.get(i));                
                hs.add(cb);
            }
                        
            return createReaction(list, hs, index);
                        
        }
    }    
    
    public String getPTHH(){
        return this.PTHH;
    }
    
    public List<String> predict(){
        List <String> result;
        int n = this.X.size();
        int m = this.Y.size();
        if (n == 2){
            result = execute(this.X.get(0), this.X.get(1));
        }
        else
            if (n == 1){
                result = execute(this.X.get(0), this.Y.get(0));
            }
            else{
                if (m == 2){                   
                    result = execute(this.Y.get(0), this.Y.get(1)); 
                    if (result.size() == 0) {                        
                        result = execute(this.Y.get(1), this.Y.get(0));                     
                    }
                }
                else{
                    result = execute(this.Y.get(0));
                }
            }
        
        String tmp = this.PTHH;        
        n = result.size();
        //System.out.println(result);
        if (n == 0){
            this.PTHH += " không phản ứng!";
            return null;
        }
        
        for (int i=0; i<n - 1; i++){
            tmp += result.get(i) + " + ";
        }
        tmp += result.get(n - 1);
        //System.out.println(tmp);
        pu p = new pu(tmp);
        this.PTHH = p.getPTHH();        
        return result;
    }
    
    public static List<String> phanUng(String X, String Y, boolean addition){
        duDoan dd = new duDoan(X + " " + Y);                
        if (addition) dd.deduce2(X); 
        else dd.deduce2(Y);
        dd.getReaction();
        return dd.getSanPham();
    }
    
// Trang
    private List<Integer> giaimatran(int [][]a, int n, int m){
        ///biến về ma trận tam giác trên
        int t = 0;
        while(t < n - 1){
            for(int j=0; j<m; j++){
                if(a[t][j] == 0){
                    int temp;
                    for(int k=t+1; k<n; k++){
                        if(a[k][j] != 0){
                            for(int z=0; z<m; z++){
                                temp = a[k][z];
                                a[k][z] = a[t][z];
                                a[t][z] = temp;
                            }
                            break;
                        }
                    }
                }
                int v = a[t][j];
                for(int k=t+1; k<n; k++){
                    if(a[k][j] != 0){
                        int u = a[k][j];
                        for(int z=j; z<m; z++)
                            a[k][z] = a[k][z]*v - a[t][z]*u;
                    }
                }
                t++;
                if(t >= n - 1)break;
            }
        }
        int []x = new int [100];
        ///tìm nghiệm
        x[m-1] = Math.abs(a[m-2][m-2]);
        for (int i=m-2; i>=0; i--){
            x[i] = 0;
            for (int j=0; j<m; j++)
                if (i != j) x[i] = x[i] - a[i][j] * x[j];
            if(((x[i]%2==0 && a[i][i]%2==1) ||(x[i]%2==1 && a[i][i]%2==0))&& a[i][i]!=1 && i<n-1){
                for(int j=0; j<m; j++)
                    x[j] = x[j]*a[i][i];
            }
            x[i] = x[i] / a[i][i];
        }
        ///tối giản        
        int uc = x[0];
        for(int i=1; i<m; i++){            
            uc = SoHoc.gcd(uc, x[i]);
        }      
        for(int i=0; i<m; i++){
            x[i] /= uc;
        }
        List<Integer> e = new LinkedList();
        for(int i=0; i<m; i++)
            e.add(x[i]);
        return e;
    }
    private Pair<List, List> setChat(String s){
        List <String> res1 = new Vector <String>();
        List <Integer> res2 = new Vector <Integer>();
        if(DieuChe.Kiemtra(s) == true){
           res1.add(DieuChe.LayDC(s).getDaiDien().getSymbol());
           res2.add(DieuChe.LayDC(s).getCnt());
        }
        else{
            return DieuChe.LayHC(s).getComponents();
        }
        Pair <List, List> e = new Pair <List, List>(res1, res2);
        return e;
    }
    private Pair<List, List> tachchat(String s){
        String []words = s.split("\\s");
        int k =0;
        for(String w:words){
            k++;
            if(w.equalsIgnoreCase("=")) break;
        }
        List<String> tt = new LinkedList<String>();
        List<String> sp = new LinkedList<String>();
        for(int i=0; i<words.length; i++){
            if(i < k - 1)
                if(!words[i].equalsIgnoreCase("+")){
                    tt.add(words[i]);
                }
            if(i >= k)
                if(!words[i].equalsIgnoreCase("+")){
                    sp.add(words[i]);
                }
        }
        Pair<List, List> e = new Pair<List,List>(tt,sp);
        return e;
    }
    private List<Integer> canbang(String s){
        int sobien, sochat = 0;
        List <String> TT1 = new LinkedList<String>();
        List <Integer> TT2 = new LinkedList<Integer>();
        List <String> SP1 = new LinkedList<String>();
        List <Integer> SP2 = new LinkedList<Integer>();
        List <String> VT = new LinkedList<String>(); 
        //tìm các chất tham gia, sản phẩm trong chuỗi
        List<String> tt = tachchat(s).getKey();
        List<String> sp = tachchat(s).getValue();
        ///Tách các chất tham gia, sản phẩm
        List <String> temp1;
        List <Integer> temp2;
        for(int i=0; i<tt.size(); i++){
            temp1 = setChat(tt.get(i)).getKey();
            temp2 = setChat(tt.get(i)).getValue();
            for(int j=0; j<temp1.size(); j++){
                TT1.add(temp1.get(j));
                TT2.add(temp2.get(j));  
            }
        }
        for(int i=0; i<sp.size(); i++){
            temp1 = setChat(sp.get(i)).getKey();
            temp2 = setChat(sp.get(i)).getValue();
            for(int j=0; j<temp1.size(); j++){
                SP1.add(temp1.get(j));
                SP2.add(temp2.get(j));
            }
        }
        ///Thành lập ma trận
        sobien = tt.size() + sp.size();
        for(int i=0; i<TT1.size(); i++){
            VT.add(TT1.get(i));
            if(i>0){
                for(int j=i-1; j>=0; j--){
                    if(TT1.get(j).equals(TT1.get(i))){
                       VT.remove(VT.size()-1);
                        break;
                    }
                }
            }
        }   
        sochat = VT.size();
        int [][]matrix = new int [sochat][sobien];
        for(int i=0; i<tt.size(); i++){
            int j=0;
            temp1 = setChat(tt.get(i)).getKey();
            for(int e=0; e<temp1.size(); e++){
                while(j<TT1.size()){
                    int kt=0;
                    if(temp1.get(e).equals(TT1.get(j))){  
                        for(int t=0; t<VT.size(); t++){
                            if(TT1.get(j).equals(VT.get(t))){
                                if(matrix[t][i] == 0){
                                    matrix[t][i] = TT2.get(j);
                                    TT1.remove(j);
                                    TT2.remove(j);
                                    kt=1;
                                    break;
                                }
                            } 
                        }
                        break;
                    }
                    if(kt == 0)  j++;
                    else{
                        kt=0;
                        j=0;
                    }
                }
            }
        }
        for(int i=0; i<sp.size(); i++){
            int j=0;
            temp1 = setChat(sp.get(i)).getKey();
            for(int e=0; e<temp1.size(); e++){
                while(j<SP1.size()){
                    int kt=0;
                    if(temp1.get(e).equals(SP1.get(j))){  
                        for(int t=0; t<VT.size(); t++){
                            if(SP1.get(j).equals(VT.get(t))){
                                if(matrix[t][i+tt.size()] == 0){
                                    matrix[t][i+tt.size()] = -SP2.get(j);
                                    SP1.remove(j);
                                    SP2.remove(j);
                                    kt=1;
                                    break;
                                }
                            } 
                        }
                        break;
                    }
                    if(kt == 0)  j++;
                    else{
                        kt=0;
                        j=0;
                    }
                }
            }
        }
        List<Integer> e = giaimatran(matrix,sochat,sobien);
        return e;
    }
    private String printReaction(String[] result, List <Integer> heso){
        String s = "";
        int d = 0;
        for (int i=0; i<result.length; i++){
            if (result[i].equals("+") || result[i].equals("=")){
                s += result[i];
            }
            else{
                if (heso.get(d) > 1){
                    s += heso.get(d);                    
                }
                s += result[i];
                d += 1;
            }
            s += " ";
        }
        return s;
    }
// Hanh    
    
    public static boolean hasValue(String key, NguyenTo X){        
        for (int i=0; i<knowledge.kienThuc.get(key).size(); i++){            
            if (knowledge.kienThuc.get(key).get(i).getSymbol().equals(X.getSymbol())) return true;
        }
        return false;
    }  
    
    /*public static boolean checkNameInNguyenTo(String X){
        for (String key: knowledge.nguyenTo.keySet()){
            if (knowledge.nguyenTo.get(key).getSymbol().equals(X)) return true;
        }
        return false;
    }*/
    
    /*public static boolean checkNameInAnion(String X){
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
    */
    /*public static boolean checkNameInDonChat(String X){
        for (String key: knowledge.donChat.keySet()){
            if (knowledge.donChat.get(key).getCTHH().equals(X)) return true;
        }
        return false;
    }*/
    
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
                        A = knowledge.getAnionFromName(a);                           
                    }
                    else{
                        t = checkIn(a, listOfVariables);
                        C = knowledge.getCationFromName(c);
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
                        NguyenTo x = knowledge.donChat.get(knowledge.getKeyDonChatFromName(listOfReferences.get(t))).getDaiDien();                      
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
                                                
                        int t1 = knowledge.posOfDienHoa2(X);
                        int t2 = knowledge.posOfDienHoa1(Y);                              
                        if (t1 >= t2) return false;                        
                        if (t1 == -1 || t2 == -1) return false;
                    }
                }
                
            }
        }
        return true;
    }
    
    private void print(String X){
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
    
    public static HopChat map(Cation XC){
        Cation F = knowledge.cation.get("H_1");
        Anion S = knowledge.anion.get("OH_1");
        // tìm một gốc axit tương ứng
        for (String k: knowledge.anion.keySet()){
            Anion a = knowledge.anion.get(k);    
            if (a.getSymbol().contains("H")) continue; 
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
        
//        System.out.println(rule);
        List <String> result = new Vector<String>();        
        if (useage.equals("alpha")){            
            String X = listOfReferences.get(0) + "_0";                                          
            //print(X+"\n");            
            String Y = listOfReferences.get(1);         
            //print(Y+"\n");            
            String Z = listOfReferences.get(2);
            //print(Z+"\n");
            //if (Z.equals("O_2")) return result;
            
            int t1 = knowledge.posOfDienHoa2(X);
            int t2 = knowledge.posOfDienHoa1(Y);                     
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
        
        if (useage.equals("taolao")){
            String X = listOfReferences.get(1);
            Cation XC = knowledge.cation.get(X);            
            HopChat hc = map(XC);            
            Anion XA = hc.getAnion();
                        
            String Y = listOfReferences.get(0);
            XC = knowledge.cation.get(Y);
            
            hc = new HopChat(XC, XA);
            
            if (!hc.getAnion().getSymbol().equals("OH")) 
                result.add(hc.getCTHH());            
            return result; 
        }
               
        if (useage.equals("beta2")){
            String X = listOfReferences.get(0);
            String Y = listOfReferences.get(1);
            Cation XC = knowledge.cation.get(Y);                 
            HopChat hc1 = map(XC);                        
            HopChat hc2 = new HopChat(knowledge.cation.get(X), knowledge.anion.get("OH_1"));
            return execute(hc1, hc2);
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
            String c = listOfReferences.get(0);            
            Cation C = knowledge.cation.get(c);
            String a = listOfReferences.get(1);
            Anion A = knowledge.anion.get(a);
            //System.out.print(c);
            int Highest = knowledge.getHighestCation(C.getSymbol());
            if (Highest > C.getHoaTri()){
                C = knowledge.cation.get(C.getSymbol() + "_" + Highest);
                HopChat hc = new HopChat(C, A);            
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
                        d = knowledge.getDonChatFromDaiDien(dd);
                    }
                    else
                        if (listOfType.get(t) == "an"){
                            String name = listOfReferences.get(t);    
                            d = knowledge.getDonChatFromAnion(knowledge.anion.get(name));
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
                        DonChat dc = knowledge.getDC(listOfReferences.get(t));
                        ca = knowledge.getCationFromName(dc.getDaiDien().getSymbol());
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
                        String key = knowledge.getKeyDonChatFromName(listOfReferences.get(t));
                        NguyenTo temp = knowledge.donChat.get(key).getDaiDien();                            
                        an = knowledge.getAnionFromName(temp.getSymbol());                                  
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
            if (knowledge.notExist.containsKey(X) || knowledge.khongTan.containsKey(X) 
                || knowledge.khi.containsKey(X)){
                ok = true;
                break;
            }
        }
        if (!ok) return result;                
        for (int i=0; i<L.size(); i++){            
            String X = L.get(i);            
            if (knowledge.notExist.containsKey(X)){                
                List <String> v = execute(knowledge.notExist.get(X));                                
                for (int j=0; j<v.size(); j++) 
                    result.add(v.get(j));                
            }
            else{                
                result.add(X);
            }                        
        }
        return result;
    }
    
    public static List<String> execute(HopChat X){        
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
                    //   System.out.println("use rule: " + rule);                    
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
    
    public static List<String> execute(DonChat X, HopChat Y){
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
                    if (useage.equals("oxihoa")) 
                        listOfReferences.add(my_anion);
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
    
    public static List<String> execute(DonChat X, DonChat Y){
        //System.out.print(X.getCTHH() + " + " + Y.getCTHH() + " = ");
        List <String> result = new Vector<String>();
        for (int i=0; i<knowledge.luat.size(); i++){
            //System.out.println(knowledge.luat.get(i).getRule());
            String[] v = knowledge.luat.get(i).getRule().split("\\s");             
            if (checkForDC(v[0]) && checkForDC(v[1])){           
                v[0] = bocVo(v[0]);
                v[1] = bocVo(v[1]);                
                
                if (knowledge.donChat.containsKey(v[0])){                    
                    String name = knowledge.donChat.get(v[0]).getCTHH();
                    if (!name.equals(X.getCTHH())) continue;                                                               
                }                                               

                 if (knowledge.donChat.containsKey(v[1])){                     
                    String name = knowledge.donChat.get(v[1]).getCTHH();
                    if (!name.equals(Y.getCTHH())) continue;                                                                                   
                }   
                
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
    
    public static boolean sameType(HopChat X, HopChat Y){
        return (phanBiet.bazo(X) && phanBiet.bazo(Y));               
    }
    
    public static List<String> execute(HopChat X, HopChat Y){                        
        //System.out.print(X.getCTHH() + " + " + Y.getCTHH() + " = \n");  
        // Nếu X hoặc Y không bền thì không phản ứng được                
        List <String> result = new Vector<String>();
        
        if (sameType(X, Y)) return result;
        
        /*if (knowledge.notExist.containsKey(Y.getCTHH()) || knowledge.notExist.containsKey(X.getCTHH()))  
            return result;*/
        
        boolean ok = true;
        
        if (phanBiet.muoi(X) && phanBiet.muoi(Y)){               
            ok = (!knowledge.khongTan.containsKey(X.getCTHH()) && !knowledge.khongTan.containsKey(Y.getCTHH()));                        
        }
        
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
               // System.out.println(luat.get(i).getRule());
                if (checkRule(listOfCondition, listOfVariables, listOfReferences, listOfType)){                       
                    String rule = knowledge.luat.get(i).getRule();                                          
                    //System.out.println(rule);                    
                    List <String> temp = create(rule, listOfVariables, listOfReferences, listOfType, useage);                       
                    if (useage.equals("after")){                        
                        temp = checkAfter(temp);
                        if (temp.size() > 0){                            
 //                           System.out.println(" --- use rule: " + rule);
 //                           result = temp;
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
