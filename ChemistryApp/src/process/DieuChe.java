/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;
import Basic.*;
import java.util.List;
import java.util.Vector;
import javafx.util.Pair;
import java.util.LinkedList;
/**
 *
 * @author OS
 */
public class DieuChe {
    public static String[] TachChuoi(String a){
        String [] word = a.split("\\s");
        return word;
    }
    public static boolean Kiemtra (String a){
        for(String x: pu.donChat.keySet())// chay het ds 
        {
            DonChat b = pu.donChat.get(x);
            if(a.equals(b.getCTHH()))return true ;
        }
        return false ;
    }
    
    public static DonChat LayDC(String a){
        return pu.donChat.get(pu.getKeyDonChatFromName(a));
    }
    public static HopChat LayHC(String a){
        HopChat u = new HopChat();
        for(String w:pu.cation.keySet()){
            for(String z: pu.anion.keySet()){
                HopChat y = new HopChat(pu.cation.get(w),pu.anion.get(z));
                if(a.equals(y.getCTHH())) {
                    u =y;
                }
            }
        }
        return u;
    }
    public static String ChatDC(String a,String b){
        //---------------Phan Huy---------------------------
        if(Kiemtra(a)==false){
            String k ="NULL";
            String st="";
            HopChat x = LayHC(a);
            if(Kiemtra(b)==true){
                DonChat y = LayDC(b);
                if(pu.pu(x).size()!=0){
                List <String> result = pu.pu(x);
                for(int i=0;i<result.size()-1;i++){
                    if(result.get(i).equals(y.getCTHH())){
                        k = y.getCTHH();
                        st = x.getCTHH()+ " -> ";
                        for(int j =0;j< result.size()-1 ;j++){
                             st += result.get(j) + " + ";
                        }
                        st+= result.get(result.size()-1);
                    }
                }
            }
            }else {
                HopChat y = LayHC(b);
                if(pu.pu(x).size()!=0){
                List <String> result = pu.pu(x);
                for(int i=0;i<result.size();i++){
                    if(result.get(i).equals(y.getCTHH())){
                        k = y.getCTHH();
                        st = x.getCTHH()+ " -> ";
                        for(int j =0;j< result.size() ;j++){
                             st += result.get(j) + " + ";
                        }
                        st+= result.get(result.size()-1);
                    }
                }
            }
            }
            if(k!="NULL"){
                System.out.println(st);
                return k;
            }
        }
        if(Kiemtra(a)== true&&Kiemtra(b)==false)  {
            DonChat x = LayDC(a);
            HopChat z = LayHC(b);
            String k="NULL";
            String st="";
            // TH Don chat + DonChat -> Hop chat
            for(String w:pu.donChat.keySet()){
                DonChat y = pu.donChat.get(w);
                //System.out.println(w);
                if(pu.pu(x, y).size()!= 0|| pu.pu(y, x).size()!=0){
                    //System.out.println(w);
                    List <String> result ;
                    if(pu.pu(x,y).size()==0)
                      result = pu.pu(y,x);
                    else result = pu.pu(x,y);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(z.getCTHH())){
                         //System.out.println(result);
                        k=y.getCTHH();
                        st=  y.getCTHH() + " + " + x.getCTHH()+ " = ";
                        for(int j = 0;j<result.size()-1;j++){
                            //System.out.println(result.get(j));
                            st += result.get(j)+ " + ";
                        }
                        st += result.get(result.size() - 1);
                        } 
                    }
                }
            }
            if(k!="NULL"){
            System.out.println(st);
            return k;   
            }
            // Don chat + Hop  chat -> Hop chat
            for(String w:pu.cation.keySet()){// Tim = hop chat 
                //System.out.println(w);
                HopChat c = new HopChat(pu.cation.get(w),LayHC(b).getAnion());
                if((pu.pu(x,c).size()!=0)){
                //System.out.println(w);
                    List <String> result = pu.pu(x, c);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(z.getCTHH())){
                         //System.out.println(result);
                        k=c.getCTHH();
                        st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                        for(int j = 0;j<result.size()-1;j++){
                            //System.out.println(result.get(j));
                            st += result.get(j)+ " + ";
                        }
                        st += result.get(result.size() - 1);
                        } 
                    }
                }
            }
            System.out.println(st);
            return k;
        }
        // TH Hop chat + Đơn chất -> Dơn chất
        else if (Kiemtra(a)== false&&Kiemtra(b)==true){//Hop chat vs don chat 
            HopChat x = LayHC(a);
            DonChat y = LayDC(b);
            String k="NULL";
            String st=" ";
            for(String w:pu.donChat.keySet()){
                 DonChat c = pu.donChat.get(w);
                if(pu.pu(c, x).size()!=0){
                List <String> result = pu.pu(c, x);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(y.getCTHH())){
                        k=c.getCTHH();
                        st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                        for(int j = 0;j<result.size()-1;j++){
                            st += result.get(j)+ " + ";
                        }
                        st += result.get(result.size() - 1);
                        } 
                    }
                }
            }
            System.out.println(st);
            return k;
        }
        else if (Kiemtra(a) == false && Kiemtra(b)== false ){
            HopChat x = LayHC(a);
            HopChat y = LayHC(b);
            if(x.getAnion().getSymbol()=="OH"&& y.getCation().getSymbol()=="H"){
                HopChat temp = x;
                x=y;
                y = temp;
            }
            String z="NULL";
            String st =" ";
            //--------------TH Hop Chat + Don chat -> Hop chat------------------
            boolean p = false; 
            for(String w: pu.donChat.keySet()){
                DonChat c = pu.donChat.get(w);
                if(pu.pu(c, x).size()!=0){
                    List <String> result = pu.pu(c, x);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(y.getCTHH())){
                        z=c.getCTHH();
                        st= "TH3 " + x.getCTHH() + " + " + c.getCTHH()+ " = ";
                        for(int j = 0;j<result.size()-1;j++){
                            //System.out.println(result.get(j));
                            st += result.get(j)+ " + ";
                        }
                        st += result.get(result.size() - 1);
                        p = true ;
                        break;
                        }
                    }
                }
                if(p==true) break;
            }
            if(z!="NULL"){
                System.out.println(st);
                return z ;
            }
            //--------------TH Hop chat + Hop chat => Hop chat----------------- 
            // Th tìm Anion cho Hop chat
            if(x.getAnion()==y.getAnion()){
                for(String w:pu.anion.keySet()){
                    HopChat k = new HopChat(y.getCation(),pu.anion.get(w));
                    if(x.getAnion().getSymbol()=="OH"&& k.getCation().getSymbol()=="H"){
                        HopChat temp = x;
                        x=k;
                        k = temp;
                    }
                    if(pu.pu(x, k).size()!=0){
                    List <String> result = pu.pu(x, k);
                    st=  " ";
                    for(int i =0;i< result.size();i++){
                        if(result.get(i).equals(y.getCTHH())){
                            z=k.getCTHH();
                            st= "TH1 " + x.getCTHH() + " + " + k.getCTHH()+ " = ";
                            for(int j = 0 ;j<result.size()-1;j++){
                                st += result.get(j)+ " + ";
                            }
                        st += result.get(result.size()-1);
                        }
                    }
                    }
                }
                System.out.println(st);
                return z;
            }
            // TH tìm Cation cho hop chat 
            else {
                for(String w: pu.cation.keySet()){
                    HopChat k = new HopChat(pu.cation.get(w),y.getAnion());
                    if(x.getAnion().getSymbol()=="OH"&& k.getCation().getSymbol()=="H"){
                        HopChat temp = x;
                        x=k;
                        k = temp;
                    }
                    if(pu.pu(x, k).size()!=0){
                        List <String> result = pu.pu(x, k);
                        st=  "";
                        int n = result.size();
                        for(int i =0;i<n;i++){
                            if(result.get(i).equals(y.getCTHH())){
                            z=k.getCTHH();
                            st= "TH2 " + x.getCTHH() + " + " + k.getCTHH()+ " = ";
                            for(int j = 0 ;j<n-1;j++){
                                st += result.get(j)+ " + ";
                            }
                            st += result.get(result.size()-1);
                            }
                        }
                    }   
                }
                System.out.println(st);
            }
            return z;
        }
        return "không tìm thấy dc";
    }
}
