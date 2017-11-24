/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import Basic.HopChat;
import Math.Array;
import Math.SoHoc;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javafx.util.Pair;

/**
 *
 * @author Thien Trang
 */
public class canBang {
    private static int soChat;
    private static int heso;
    private static List<String> tt;
    private static List<String> sp;
    private static List <String> TT1 = new LinkedList<String>();
    private static List <Integer> TT2 = new LinkedList<Integer>();
    private static List <String> SP1 = new LinkedList<String>();
    private static List <Integer> SP2 = new LinkedList<Integer>();
    private static List <String> VT = new LinkedList<String>();
    private List<String> chat = new Vector<String>();
    private List<Integer> bien = new LinkedList<Integer>();
    private static void setTT(String s){
        tt = tachchat(s).getKey();
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
    }
    private static void setSP(String s){
        sp = tachchat(s).getValue();
        List <String> temp1;
        List <Integer> temp2;
        for(int i=0; i<sp.size(); i++){
            temp1 = setChat(sp.get(i)).getKey();
            temp2 = setChat(sp.get(i)).getValue();
            for(int j=0; j<temp1.size(); j++){
                SP1.add(temp1.get(j));
                SP2.add(temp2.get(j));
            }
        }
    }
    private static void setVT(){
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
    }
    public static void setNum(String s){
        setTT(s);
        setSP(s);
        heso = tt.size() + sp.size();   
        soChat = VT.size();
        System.out.println(heso + " "+ soChat);
    }
    private static List<Integer> giaimatran(int [][]a, int n, int m){
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
    private static Pair<List, List> setChat(String s){
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
    private static Pair<List, List> tachchat(String s){
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
    public static void hihi(String s){
        setNum(s);
        if (soChat >= heso -1){
            return;
        }
        if(soChat < heso - 1){
            String s1="";
            String s2="";
            List <String> temp5 = new LinkedList<String>();
            for(int i=0; i<tt.size(); i++){
                s1+=tt.get(i)+" ";
                s2+=tt.get(i)+" ";
                if(i < tt.size()-1){
                    s1+="+ ";
                    s2+="+ ";
                }
            }
            s1+="= ";
            s2+="= ";
            for(int i=0; i<sp.size()-1; i++){
                List<String> temp3 = setChat(sp.get(i)).getKey();
                for(int j=i+1; j<sp.size();i++){
                    List<String> temp4 = setChat(sp.get(j)).getKey();
                    int temp = 0;
                    for(int k=0; k<temp3.size(); k++){
                        for(int p=0; p<temp4.size(); p++){
                            if(!temp3.get(k).equals(temp4.get(p))){
                                temp=1;
                            }
                            break;
                        }
                        if(temp==1){
                            if(temp5.size() == 0){
                                s1+=sp.get(i)+ " ";
                                s2+=sp.get(i)+ " ";
                                temp5.add(sp.get(i));
                            }
                            else{
                                int temp6=0;
                                for(int q=0; q<temp5.size();q++){
                                    if(sp.get(i).equals(temp5.get(q))){
                                        temp6=1;
                                        break;
                                    }
                                }
                                if(temp6==1){
                                    s1+=sp.get(j)+ " ";
                                    s2+=sp.get(j)+ " ";
                                    temp5.add(sp.get(j));
                                }
                            }
                        }
                        break;
                    }
                    if(temp==0){
                        if(temp5.size() == 0){
                                s1+=sp.get(i)+" ";
                                s2+=sp.get(j)+" ";
                                temp5.add(sp.get(i));
                                temp5.add(sp.get(j));
                                
                        }
                        else{
                            
                            int temp6=0;
                            for(int q=0; q<temp5.size();q++){
                                if(sp.get(i).equals(temp5.get(q))){
                                    temp6=1;
                                    break;
                                }
                            }
                            if(temp6==0){
                                s1+=sp.get(i)+ " ";
                                temp5.add(sp.get(i));
                            }
                            temp6=0;
                            for(int q=0; q<temp5.size();q++){
                                if(sp.get(j).equals(temp5.get(q))){
                                    temp6=1;
                                    break;
                                }
                            }
                            if(temp6==0){
                                s2+=sp.get(j)+ " ";
                                temp5.add(sp.get(j));
                            }
                        }
                    }
                    break;
                }
            }
            System.out.println(s1);
            System.out.println(s2);
            System.out.println("de quy");
            hihi(s1);
            hihi(s2);
        }
    }
    public static void canbang(String s){
        List <String> temp1;
        List <Integer> temp2;
        setTT(s);
        setSP(s);
        setVT();
        ///Thành lập ma trận
        setNum(s);
        hihi(s);
        /*int [][]matrix = new int [soChat][heso];
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
        List<Integer> e = giaimatran(matrix,soChat,heso);
        return e;*/
    }
}
