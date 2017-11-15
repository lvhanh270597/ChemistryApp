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
import knowledge.knowledge;
/**
 *
 * @author OS
 */
public class DieuChe {
    public static void  DieuChe(String b){
        String[] word = DieuChe.TachChuoi(b);
        int i =0;
        while (i<=word.length-2){
            System.out.println("từ "+word[i]+ " -> "+ word[i+2]);
                    String d = DieuChe.ChatDC(word[i], word[i+2]);
                    System.out.println("Chất dieu che "+ d);
            i = i+2;
        }
    }
    public static String[] TachChuoi(String a){
        String [] word = a.split("\\s");
        return word;
    }
    public static boolean Kiemtra (String a){
        for(String x: knowledge.donChat.keySet())// chay het ds 
        {
            DonChat b = knowledge.donChat.get(x);
            if(a.equals(b.getCTHH()))return true ;
        }
        return false ;
    }
    
    public static DonChat LayDC(String a){
        return knowledge.donChat.get(pu.getKeyDonChatFromName(a));
    }
    public static HopChat LayHC(String a){
        HopChat u = new HopChat();
        for(String w:knowledge.cation.keySet()){
            for(String z: knowledge.anion.keySet()){
                HopChat y = new HopChat(knowledge.cation.get(w),knowledge.anion.get(z));
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
            String st=" ";
            HopChat x = LayHC(a);
            if(Kiemtra(b)==true){
                DonChat y = LayDC(b);
                if(pu.pu(x).size()!=0){
                List <String> result = pu.pu(x);
                for(int i=0;i<result.size();i++){
                    //System.out.println(result.get(i)+ " = "+ y.getCTHH());
                    if(result.get(i).equals(y.getCTHH())){
                         //System.out.println("Yes");
                        k = "Nhiet do";
                        st = x.getCTHH()+ " -> ";
                        for(int j =0;j< result.size()-1 ;j++){
                             st += result.get(j) + " + ";
                        }
                        st+= result.get(result.size()-1);
                        System.out.println(st);
                        return k;
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
                            for(int j =0;j< result.size();j++){
                                 st += result.get(j) + " + ";
                            }
                            st+= result.get(result.size()-1);
                            System.out.println(st);
                            return k;
                        }
                    }
                }
            }
        }
        if(Kiemtra(a)== true&&Kiemtra(b)==false)  {
            DonChat x = LayDC(a);
            HopChat z = LayHC(b);
            String k="NULL";
            String st="";
            // TH Don chat + DonChat -> Hop chat
            for(String w:knowledge.donChat.keySet()){
                DonChat y = knowledge.donChat.get(w);
                //System.out.println(w);
                if(pu.pu(x, y).size()!= 0|| pu.pu(y, x).size()!=0){
                   List <String> result ;
                    if(pu.pu(x,y).size()==0)
                      result = pu.pu(y,x);
                    else result = pu.pu(x,y);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(z.getCTHH())){
                        k=y.getCTHH();
                        st=  y.getCTHH() + " + " + x.getCTHH()+ " = ";
                        for(int j = 0;j<result.size()-1;j++){
                            st += result.get(j)+ " + ";
                        }
                        st += result.get(result.size() - 1);
                        System.out.println(st);
                        return k;   
                    }
                }
            }
            }
            // Don chat + Hopchat(Tim Cation) -> Hop chat
            for(String w:knowledge.cation.keySet()){// Tim = hop chat 
                HopChat c = new HopChat(knowledge.cation.get(w),LayHC(b).getAnion());
                if((pu.pu(x,c).size()!=0)){
                //System.out.println(w);
                    List <String> result = pu.pu(x, c);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(z.getCTHH())){
                            k=c.getCTHH();
                            st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                            for(int j = 0;j<result.size()-1;j++){
                                //System.out.println(result.get(j));
                                st += result.get(j)+ " + ";
                            }
                            st += result.get(result.size() - 1);
                            System.out.println(st);
                            return k;
                        } 
                    }
                }
            }
            // Don chat + Hop chat (Tìm Anion) -> Hop chat
            for(String w:knowledge.anion.keySet()){
                HopChat c = new HopChat(LayHC(b).getCation(),knowledge.anion.get(w));
                if((pu.pu(x,c).size()!=0)){
                    List <String> result = pu.pu(x, c);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(z.getCTHH())){
                            k=c.getCTHH();
                            st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                            for(int j = 0;j<result.size()-1;j++){
                                //System.out.println(result.get(j));
                                st += result.get(j)+ " + ";
                            }
                            st += result.get(result.size() - 1);
                            System.out.println(st);
                            return k;
                        } 
                    }
                }
            }
        }
        // TH Hop chat + Đơn chất -> Dơn chất
        else if (Kiemtra(a)== false&&Kiemtra(b)==true){//Hop chat vs don chat 
            HopChat x = LayHC(a);
            DonChat y = LayDC(b);
            String k="NULL";
            String st=" ";
            for(String w:knowledge.donChat.keySet()){
                 DonChat c = knowledge.donChat.get(w);
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
                            System.out.println(st);
                            return k;
                        } 
                    }
                }
            }
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
            for(String w: knowledge.donChat.keySet()){
                DonChat c = knowledge.donChat.get(w);
                if(pu.pu(c, x).size()!=0){
                    List <String> result = pu.pu(c, x);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(y.getCTHH())){
                            z=c.getCTHH();
                            st= x.getCTHH() + " + " + c.getCTHH()+ " = ";
                            for(int j = 0;j<result.size()-1;j++){
                                //System.out.println(result.get(j));
                                st += result.get(j)+ " + ";
                            }
                            st += result.get(result.size() - 1);
                            System.out.println(st);
                            return z ;
                        }
                    }
                }
            }
            //--------------TH Hop chat + Hop chat => Hop chat----------------- 
            // Th tìm Anion cho Hop chat
            if(x.getAnion()==y.getAnion()){
                for(String w:knowledge.anion.keySet()){
                    HopChat k = new HopChat(y.getCation(),knowledge.anion.get(w));
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
                                st=x.getCTHH() + " + " + k.getCTHH()+ " = ";
                                for(int j = 0 ;j<result.size()-1;j++){
                                    st += result.get(j)+ " + ";
                                }
                            st += result.get(result.size()-1);   
                            System.out.println(st);
                            return z;
                            }
                        }
                    }
                }
            }
            // TH tìm Cation cho hop chat 
            else {
               for(String w: knowledge.cation.keySet()){
                    HopChat k = new HopChat(knowledge.cation.get(w),y.getAnion());
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
                                st= x.getCTHH() + " + " + k.getCTHH()+ " = ";
                                for(int j = 0 ;j<n-1;j++){
                                    st += result.get(j)+ " + ";
                                }
                                st += result.get(result.size()-1);
                                System.out.println(st);
                                return z;
                            }
                        }
                    }   
                }
               //--------TH HC + HC -> Chất Mới-----------------
            for(String i:knowledge.cation.keySet()){
                for(String j: knowledge.anion.keySet()){
                    HopChat k = new HopChat(knowledge.cation.get(i),knowledge.anion.get(j));
                    if(pu.pu(x, k).size()!=0){
                        List <String> result= pu.pu(x,k);
                        for(int m=0;m<result.size();m++){
                            if(result.get(m).equals(y.getCTHH())){
                                z=k.getCTHH();
                                st= x.getCTHH() + " + " + k.getCTHH()+ " = ";
                                for(int n = 0 ;n<result.size()-1;n++){
                                    st += result.get(n)+ " + ";
                                }
                                st += result.get(result.size()-1);
                                System.out.println(st);
                                return z;
                            }
                        }
                    }
                }
                }
            }
        }
        else if(Kiemtra(a)==true && Kiemtra(b)==true) {
            //TH DonChat => DonChat(Moi)
            String st ="";
            String z="NULL";
            DonChat x= LayDC(a);
            DonChat y = LayDC(b);
            for(String i:knowledge.cation.keySet()){
                for(String j: knowledge.anion.keySet()){
                    HopChat k = new HopChat(knowledge.cation.get(i),knowledge.anion.get(j));
                    if(pu.pu(x, k).size()!=0){
                        List <String> result= pu.pu(x,k);
                        System.out.println(result);
                        for(int m=0;m<result.size();m++){
                            if(result.get(m).equals(y.getCTHH())){
                                z=k.getCTHH();
                                st= x.getCTHH() + " + " + k.getCTHH()+ " = ";
                                for(int n = 0 ;n<result.size()-1;n++){
                                    st += result.get(n)+ " + ";
                                }
                                st += result.get(result.size()-1);
                                System.out.println(st);return z;
                            }
                        }
                    }
                }
            }
        }
        return "không tìm thấy dc";
    }
}
