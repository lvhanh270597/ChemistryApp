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
    public static Vector DieuChe(String b,String a){
        String[] word = DieuChe.TachChuoi(b);
        String[] wordx = DieuChe.TachChuoi(a);
        Vector vt = new Vector();
        int i =0;
        while (i<=word.length-2){
            String k = "từ "+word[i]+ " -> "+ word[i+2];
            vt.add(k);
            String d = DieuChe.ChatDC(word[i], word[i+2],wordx);
            if(d=="NULL"){
                vt.add("Phản ứng không thể tìm thấy !!!");
            }
            else{
                vt.add(d);
            }
            i = i+2;
        }
        return vt ;
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
        return knowledge.donChat.get(knowledge.getKeyDonChatFromName(a));
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
    public static String ChatDC(String a,String b,String [] q){
        //---------------Phan Huy---------------------------
        if(Kiemtra(a)==false&& q.length==1){
            String st="NULL";
            HopChat x = LayHC(a);
            if(Kiemtra(b)==true){
                DonChat y = LayDC(b);
                if(pu.execute(x).size()!=0){
                List <String> result = pu.execute(x);
                for(int i=0;i<result.size();i++){
                    if(result.get(i).equals(y.getCTHH())){
                        st = x.getCTHH()+ " -> ";
                        for(int j =0;j< result.size()-1 ;j++){
                             st += result.get(j) + " + ";
                        }
                        st+= result.get(result.size()-1);
                        return st ;
                    }
                }
            }
            }else {
                HopChat y = LayHC(b);
                if(pu.execute(x).size()!=0){
                    List <String> result = pu.execute(x);
                    for(int i=0;i<result.size();i++){
                        if(result.get(i).equals(y.getCTHH())){
                            st = x.getCTHH()+ " -> ";
                            for(int j =0;j< result.size();j++){
                                 st += result.get(j) + " + ";
                            }
                            st+= result.get(result.size()-1);
                            pu p = new pu(st);
                            st = p.getPTHH();
                            return st;
                        }
                    }
                }
            }
        }
        if(Kiemtra(a)== true&&Kiemtra(b)==false) {
            DonChat x = LayDC(a);
            HopChat z = LayHC(b);
            String st="NULL";
            // TH Buộc phải sử dụng các chất 
            if(q.length!=1){
                String st1="NULL";
                for(int i =0;i<q.length;i++){
                    DonChat n;
                    HopChat m ;
                    if(Kiemtra(q[i])==true){
                       n = LayDC(q[i]);
                       if(pu.execute(x,n).size()!=0){
                            List <String> result = pu.execute(x,n);
                            for(int j = 0;j<result.size();j++){
                               if(result.get(j).equals(z.getCTHH())){
                                   st1=  n.getCTHH() + " + " + x.getCTHH()+ " = ";
                                   for(int k = 0;k<result.size()-1;k++){
                                       st1 += result.get(k)+ " + ";
                                   }
                                   st1 += result.get(result.size() - 1);
                                   pu p = new pu(st);
                                    st1 = p.getPTHH();
                                   return st1;
                               }
                            }
                        }
                    }
                    else {
                        m = LayHC(q[i]);
                        if(pu.execute(x,m).size()!=0){
                            List <String> result = pu.execute(x,m);
                            for(int j = 0;j<result.size();j++){
                               if(result.get(j).equals(z.getCTHH())){
                                   st= x.getCTHH() + " + " +  m.getCTHH()+ " = ";
                                   for(int k = 0;k<result.size()-1;k++){
                                       st += result.get(k)+ " + ";
                                   }
                                   st += result.get(result.size() - 1);
                                   pu p = new pu(st1);
                                   st1 = p.getPTHH();
                                   return st1;
                               }
                            }
                        }
                    }
                }
                return st1;
            }
            // TH Don chat + DonChat -> Hop chat
            for(String w:knowledge.donChat.keySet()){
                DonChat y = knowledge.donChat.get(w);
                //System.out.println(w);
                if(pu.execute(x, y).size()!= 0|| pu.execute(y, x).size()!=0){
                   List <String> result ;
                    if(pu.execute(x,y).size()==0)
                      result = pu.execute(y,x);
                    else result = pu.execute(x,y);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(z.getCTHH())){
                        st=  y.getCTHH() + " + " + x.getCTHH()+ " = ";
                        for(int j = 0;j<result.size()-1;j++){
                            st += result.get(j)+ " + ";
                        }
                        st += result.get(result.size() - 1);
                        pu p = new pu(st);
                        st = p.getPTHH();
                        return st; 
                    }
                }
            }
            }
            // Don chat + Hopchat(Tim Cation) -> Hop chat
            for(String w:knowledge.cation.keySet()){// Tim = hop chat 
                HopChat c = new HopChat(knowledge.cation.get(w),LayHC(b).getAnion());
                if((pu.execute(x,c).size()!=0)){
                //System.out.println(w);
                    List <String> result = pu.execute(x, c);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(z.getCTHH())){
                            st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                            for(int j = 0;j<result.size()-1;j++){
                                st += result.get(j)+ " + ";
                            }
                            st += result.get(result.size() - 1);
                            pu p = new pu(st);
                            st = p.getPTHH();
                            return st;
                        } 
                    }
                }
            }
            // Don chat + Hop chat (Tìm Anion) -> Hop chat
            for(String w:knowledge.anion.keySet()){
                HopChat c = new HopChat(LayHC(b).getCation(),knowledge.anion.get(w));
                if((pu.execute(x,c).size()!=0)){
                    List <String> result = pu.execute(x, c);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(z.getCTHH())){
                            st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                            for(int j = 0;j<result.size()-1;j++){
                                st += result.get(j)+ " + ";
                            }
                            st += result.get(result.size() - 1);
                            pu p = new pu(st);
                            st = p.getPTHH();
                            return (st);
                        } 
                    }
                }
            }
            // DonChat + HopChat -> HopChat(Moi)
            for(String i: knowledge.cation.keySet()){
                for(String j: knowledge.anion.keySet()){
                    HopChat c = new HopChat(knowledge.cation.get(i),knowledge.anion.get(j));
                    if(pu.execute(x,c).size()!=0){
                        List <String> result = pu.execute(x,c);
                        for(int w=0 ;w<result.size();w++){
                           if(result.get(w).equals(z.getCTHH())){
                                st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                                for(int p = 0;p<result.size()-1;p++){
                                    //System.out.println(result.get(j));
                                    st += result.get(p)+ " + ";
                                }
                                st += result.get(result.size() - 1);
                                pu p = new pu(st);
                            st = p.getPTHH();
                                return st;
                           }
                        }
                    }
                }
            }
        }
        // TH Hop chat + Đơn chất -> Dơn chất
        else if (Kiemtra(a)== false&&Kiemtra(b)==true){//Hop chat vs don chat 
            HopChat x = LayHC(a);
            DonChat y = LayDC(b); 
            String st="NULL";
            // TH Buộc phải sử dụng các chất 
            if(q.length!=1){
                String st1="NULL";
                for(int i =0;i<q.length;i++){
                    DonChat n;
                    HopChat m ;
                    if(Kiemtra(q[i])==true){
                       n = LayDC(q[i]);
                       if(pu.execute(n, x).size()!=0){
                            List <String> result = pu.execute(n, x);
                            for(int j = 0;j<result.size();j++){
                               if(result.get(j).equals(y.getCTHH())){
                                   st1=  n.getCTHH() + " + " + x.getCTHH()+ " = ";
                                   for(int k = 0;k<result.size()-1;k++){
                                       st1 += result.get(k)+ " + ";
                                   }
                                   st1 += result.get(result.size() - 1);
                                   pu p = new pu(st1);
                                    st1 = p.getPTHH();
                                   return st1;
                               }
                            }
                        }
                    }
                    else {
                        m = LayHC(q[i]);
                        if(pu.execute(x,m).size()!=0){
                            List <String> result = pu.execute(x,m);
                            for(int j = 0;j<result.size();j++){
                               if(result.get(j).equals(y.getCTHH())){
                                   st1= x.getCTHH() + " + " +  m.getCTHH()+ " = ";
                                   for(int k = 0;k<result.size()-1;k++){
                                       st1 += result.get(k)+ " + ";
                                   }
                                   st1 += result.get(result.size() - 1);
                                   pu p = new pu(st);
                                    st = p.getPTHH();
                                   return st1;
                               }
                            }
                        }
                    }
                }
                return st1;
            }
            //TH HopChat + DonChat => DonChat
            for(String w:knowledge.donChat.keySet()){
                 DonChat c = knowledge.donChat.get(w);
                if(pu.execute(c, x).size()!=0){
                List <String> result = pu.execute(c, x);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(y.getCTHH())){
                            st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                            for(int j = 0;j<result.size()-1;j++){
                                st += result.get(j)+ " + ";
                            }
                            st += result.get(result.size() - 1);
                            pu p = new pu(st);
                            st = p.getPTHH();
                            return st;
                        } 
                    }
                }
            }
            // Hop chat + Hop chat => Don Chat
            for(String w: knowledge.cation.keySet()){
                for(String p: knowledge.anion.keySet()){
                    HopChat c = new HopChat(knowledge.cation.get(w),knowledge.anion.get(p));{
                        if(pu.execute(x, c).size()!=0){
                            List<String> result = pu.execute(x,c);
                            //System.out.println(result);
                            for(int i =0;i<result.size();i++){
                                if(result.get(i).equals(b)){
                                st=  c.getCTHH() + " + " + x.getCTHH()+ " = ";
                                for(int j = 0;j<result.size()-1;j++){
                                    st += result.get(j)+ " + ";
                                }
                                st += result.get(result.size() - 1);
                                pu k = new pu(st);
                                st = k.getPTHH();
                                return st;
                                }
                            }
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
            String st ="NULL";
            // TH Buộc phải sử dụng các chất 
            if(q.length!=1){
                String st1="NULL";
                for(int i =0;i<q.length;i++){
                    DonChat n;
                    HopChat m ;
                    if(Kiemtra(q[i])==true){
                       n = LayDC(q[i]);
                       if(pu.execute(n, x).size()!=0){
                            List <String> result = pu.execute(n, x);
                            for(int j = 0;j<result.size();j++){
                               if(result.get(j).equals(y.getCTHH())){
                                   st1=  n.getCTHH() + " + " + x.getCTHH()+ " = ";
                                   for(int k = 0;k<result.size()-1;k++){
                                       st1 += result.get(k)+ " + ";
                                   }
                                   st1 += result.get(result.size() - 1);
                                   pu p = new pu(st1);
                                    st1 = p.getPTHH();
                                   return st1;
                               }
                            }
                        }
                    }
                    else {
                        m = LayHC(q[i]);
                        if(pu.execute(x,m).size()!=0){
                            List <String> result = pu.execute(x,m);
                            for(int j = 0;j<result.size();j++){
                               if(result.get(j).equals(y.getCTHH())){
                                   st1= x.getCTHH() + " + " +  m.getCTHH()+ " = ";
                                   for(int k = 0;k<result.size()-1;k++){
                                       st1 += result.get(k)+ " + ";
                                   }
                                   st1 += result.get(result.size() - 1);
                                   pu p = new pu(st1);
                                    st1 = p.getPTHH();
                                   return st1;
                               }
                            }
                        }
                    }
                }
                return st1;
            }
            //--------------TH Hop Chat + Don chat -> Hop chat------------------
            for(String w: knowledge.donChat.keySet()){
                DonChat c = knowledge.donChat.get(w);
                if(pu.execute(c, x).size()!=0){
                    List <String> result = pu.execute(c, x);
                    for(int i =0;i<result.size();i++){
                        if(result.get(i).equals(y.getCTHH())){
                            st= x.getCTHH() + " + " + c.getCTHH()+ " = ";
                            for(int j = 0;j<result.size()-1;j++){
                                //System.out.println(result.get(j));
                                st += result.get(j)+ " + ";
                            }
                            st += result.get(result.size() - 1);
                            pu p = new pu(st);
                            st = p.getPTHH();
                            return st ;
                        }
                    }
                }
            }
            //--------------TH Hop chat + Hop chat => Hop chat----------------- 
            // Th tìm Anion cho Hop chat
            if(x.getAnion()==y.getAnion()){
                for(String w:knowledge.anion.keySet()){
                    HopChat k = new HopChat(y.getCation(), knowledge.anion.get(w));
                    String k_name = k.getCTHH();
                    if (knowledge.khongTan.containsKey(k_name) || knowledge.notExist.containsKey(k_name)) continue;                   
                    if(x.getAnion().getSymbol()=="OH"&& k.getCation().getSymbol()=="H"){
                        HopChat temp = x;
                        x=k;
                        k = temp;
                    }   
                    if(pu.execute(x, k).size()!=0){
                        List <String> result = pu.execute(x, k);
                        st=  "NULL";
                        for(int i =0;i< result.size();i++){
                            if(result.get(i).equals(y.getCTHH())){
                                st=x.getCTHH() + " + " + k.getCTHH()+ " = ";
                                for(int j = 0 ;j<result.size()-1;j++){
                                    st += result.get(j)+ " + ";
                                }
                            st += result.get(result.size()-1); 
                            pu p = new pu(st);
                            st = p.getPTHH();
                            return st;
                            }
                        }
                    }
                }
            }
            // TH tìm Cation cho hop chat 
               for(String w: knowledge.cation.keySet()){
                    HopChat k = new HopChat(knowledge.cation.get(w),y.getAnion());
                    String k_name = k.getCTHH();  
                    if (knowledge.khongTan.containsKey(k_name) || knowledge.notExist.containsKey(k_name)) continue; 
                    if(x.getAnion().getSymbol()=="OH"&& k.getCation().getSymbol()=="H"){
                        HopChat temp = x;
                        x=k;
                        k = temp;
                    }
                    if(pu.execute(x, k).size()!=0){
                        List <String> result = pu.execute(x, k);
                        st=  "NULL";
                        int n = result.size();
                        for(int i =0;i<n;i++){
                            if(result.get(i).equals(y.getCTHH())){
                                st= x.getCTHH() + " + " + k.getCTHH()+ " = ";
                                for(int j = 0 ;j<n-1;j++){
                                    st += result.get(j)+ " + ";
                                }
                                st += result.get(result.size()-1);
                                pu p = new pu(st);
                                st = p.getPTHH();
                                return st;
                            }
                        }
                    }   
                }
               //--------TH HC + HC -> Chất Mới-----------------
            for(String i:knowledge.cation.keySet()){
                for(String j: knowledge.anion.keySet()){
                    HopChat k = new HopChat(knowledge.cation.get(i),knowledge.anion.get(j));
                    if(pu.execute(x, k).size()!=0|| pu.execute(k, x).size()!=0){
                       // System.out.println("Yes");
                        List <String> result;
                        if(pu.execute(x,k).size()!=0) result= pu.execute(x, k);
                        else result= pu.execute(k,x);
                        for(int m=0;m<result.size();m++){
                            if(result.get(m).equals(y.getCTHH())){
                                System.out.println("Yes");
                                st= x.getCTHH() + " + " + k.getCTHH()+ " = ";
                                for(int n = 0 ;n<result.size()-1;n++){
                                    st += result.get(n)+ " + ";
                                }
                                st += result.get(result.size()-1);
                                pu p = new pu(st);
                                st = p.getPTHH();
                                return st;
                            }
                        }
                    }
                }
            }
        }
        else if(Kiemtra(a)==true && Kiemtra(b)==true){
            //TH DonChat => DonChat(Moi)
            String st ="NULL";
            DonChat x= LayDC(a);
            DonChat y = LayDC(b);
            // TH Buộc phải sử dụng các chất 
            if(q.length!=1){
                String st1="NULL";
                for(int i =0;i<q.length;i++){
                    DonChat n;
                    HopChat m ;
                    if(Kiemtra(q[i])==true){
                       n = LayDC(q[i]);
                       if(pu.execute(n, x).size()!=0){
                            List <String> result = pu.execute(n, x);
                            for(int j = 0;j<result.size();j++){
                               if(result.get(j).equals(y.getCTHH())){
                                   st1=  n.getCTHH() + " + " + x.getCTHH()+ " = ";
                                   for(int k = 0;k<result.size()-1;k++){
                                       st1 += result.get(k)+ " + ";
                                   }
                                   st1 += result.get(result.size() - 1);
                                   pu p = new pu(st);
                                    st = p.getPTHH();
                                   return st1;
                               }
                            }
                        }
                    }
                    else {
                        m = LayHC(q[i]);
                        if(pu.execute(x,m).size()!=0){
                            List <String> result = pu.execute(x,m);
                            for(int j = 0;j<result.size();j++){
                               if(result.get(j).equals(y.getCTHH())){
                                   st1= x.getCTHH() + " + " +  m.getCTHH()+ " = ";
                                   for(int k = 0;k<result.size()-1;k++){
                                       st1 += result.get(k)+ " + ";
                                   }
                                   st1 += result.get(result.size() - 1);
                                   pu p = new pu(st1);
                                     st1 = p.getPTHH();
                                   return st1;
                               }
                            }
                        }
                    }
                }
                return st1;
            }
            for(String i:knowledge.cation.keySet()){
                for(String j: knowledge.anion.keySet()){
                    HopChat k = new HopChat(knowledge.cation.get(i),knowledge.anion.get(j));
                    if(pu.execute(x, k).size()!=0){
                        List <String> result= pu.execute(x,k);
                        for(int m=0;m<result.size();m++){
                            if(result.get(m).equals(y.getCTHH())){
                                st= x.getCTHH() + " + " + k.getCTHH()+ " = ";
                                for(int n = 0 ;n<result.size()-1;n++){
                                    st += result.get(n)+ " + ";
                                }
                                st += result.get(result.size()-1);
                                pu p = new pu(st);
                                st = p.getPTHH();
                                return st;
                            }
                        }
                    }
                }
            }
        }
        return "NULL";
    }
}
