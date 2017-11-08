
package process;

import java.util.*;
import Basic.*;
import process.*;

public class phanBiet {
    
    public static boolean axit(HopChat X){
        return X.getCation().getSymbol().equals("H");
    }
    
    public static boolean bazo(HopChat X){
        return X.getAnion().getSymbol().equals("OH");
    }
    
    public static boolean muoi(HopChat X){
        return !(axit(X) || bazo(X));
    }
    
    public static boolean coKetTua(List <String> G){
        for (int i=0; i<G.size(); i++){
            String v = G.get(i);
            if (pu.khongTan.containsKey(v)){
                return true;
            }
        }
        return false;
    }    
    
    public static boolean coKhi(List <String> G){
        for (int i=0; i<G.size(); i++){
            String v = G.get(i);
            if (pu.khi.containsKey(v)){
                return true;
            }
        }
        return false;
    }
    
    public static void Show(List<HopChat> G, HopChat A){
        for (int i=0; i<G.size(); i++){
            HopChat v = G.get(i);
            List <String> phanUng = pu.pu(v, A);
            if (phanUng.size() == 0){
                phanUng = pu.pu(A, v);
            }
            // Không có hiện tượng
            boolean x = coKetTua(phanUng);
            boolean y = coKhi(phanUng);
            boolean z = !x && !y;
            if (phanUng.size() == 0){                
                System.out.println(v.getCTHH() + " không phản ứng " + A.getCTHH() + " ==> không thấy hiện tượng");           
            }            
            else{
                if (z){                    
                    System.out.print(v.getCTHH() + " + " + A.getCTHH() + " = ");
                    for (int j=0; j<phanUng.size() - 1; j++){
                        System.out.print(phanUng.get(j) + " + ");
                    }
                    System.out.println(phanUng.get(phanUng.size() - 1) + " ==> không thấy hiện tượng");
                }
                else{
                    if (x && !y){
                        System.out.print(v.getCTHH() + " + " + A.getCTHH() + " = ");
                        for (int j=0; j<phanUng.size() - 1; j++){
                            System.out.print(phanUng.get(j) + " + ");
                        }
                        System.out.println(phanUng.get(phanUng.size() - 1) + " ==> kết tủa");
                    }
                    else{
                        if (!x && y){
                            System.out.print(v.getCTHH() + " + " + A.getCTHH() + " = ");
                            for (int j=0; j<phanUng.size() - 1; j++){
                                System.out.print(phanUng.get(j) + " + ");
                            }
                            System.out.println(phanUng.get(phanUng.size() - 1) + " ==> khí");
                        }
                        else{
                            System.out.print(v.getCTHH() + " + " + A.getCTHH() + " = ");
                            for (int j=0; j<phanUng.size() - 1; j++){
                                System.out.print(phanUng.get(j) + " + ");
                            }
                            System.out.println(phanUng.get(phanUng.size() - 1) + " ==> vừa khí vừa kết tủa");
                        }
                    }
                }
            }
        }
    }
    
    public static List<List<HopChat>> Collect(List<HopChat> G, HopChat A){        
        List <HopChat> X = new Vector<HopChat>();   // Không có hiện tượng
        List <HopChat> Y = new Vector<HopChat>();   // Kết tủa và không khí
        List <HopChat> Z = new Vector<HopChat>();   // Có khí và không kết tủa
        List <HopChat> T = new Vector<HopChat>();   // Vừa kết tủa vừa có khí
             
        for (int i=0; i<G.size(); i++){
            HopChat v = G.get(i);
            List <String> phanUng = pu.pu(v, A);
            if (phanUng.size() == 0){
                phanUng = pu.pu(A, v);
            }
            // Không có hiện tượng
            boolean x = coKetTua(phanUng);
            boolean y = coKhi(phanUng);
            boolean z = !x && !y;
            if (phanUng.size() == 0 || z){
                X.add(v);
            }            
            else{
                if (x && !y){
                    Y.add(v);
                }
                else{
                    if (!x && y){
                        Z.add(v);
                    }
                    else{
                        T.add(v);
                    }
                }
            }
        }
        
        
        List<List<HopChat>> result = new Vector<List<HopChat>>();
        result.add(X);
        result.add(Y);
        result.add(Z);
        result.add(T);
        return result;
    }    
    
    public static void phanBiet(List <HopChat> G){
        if (G.size() == 0) return;
        if (G.size() == 1) {
            System.out.println("Đã phân biệt được " + G.get(0).getCTHH());
            return ;
        }
        // Luật quỳ tím
        List <HopChat> X = new Vector<HopChat>();
        List <HopChat> Y = new Vector<HopChat>();   
        List <HopChat> Z = new Vector<HopChat>();
        List <HopChat> T = new Vector<HopChat>();
        for (int i=0; i<G.size(); i++){
            HopChat v = G.get(i);            
            if (axit(v)){
                X.add(v);
            }
            if (bazo(v)){
                Y.add(v);
            }
            if (muoi(v)){
                Z.add(v);
            }
        }
        int cnt = (X.size() >= 1 ? 1 : 0) + (Y.size() >= 1 ? 1 : 0) + (Z.size() >= 1 ? 1 : 0);
        if (cnt > 1){
            System.out.println("Chúng ta dùng quỳ tím để chia làm " + cnt + " nhóm chất: ");
            if (X.size() > 0){
                System.out.print("Làm quỳ tím hóa đỏ gồm những chất: ");
                for (int i=0; i<X.size(); i++) System.out.print(X.get(i).getCTHH() + " ");
                System.out.println();
            }
            if (Y.size() > 0){
                System.out.print("Làm quỳ tím hóa xanh gồm những chất: ");
                for (int i=0; i<Y.size(); i++) System.out.print(Y.get(i).getCTHH() + " ");
                System.out.println();
            }
            if (Z.size() > 0){
                System.out.print("Làm quỳ tím không đổi màu gồm những chất: ");
                for (int i=0; i<Z.size(); i++) System.out.print(Z.get(i).getCTHH() + " ");
                System.out.println();
            }
            phanBiet(X);
            phanBiet(Y);
            phanBiet(Z);
            return ;
        }
        
        // Luật phản ứng với một chất
        X.clear();  Y.clear();  Z.clear();        
        for (String keyC: pu.cation.keySet()){
            Cation c = pu.cation.get(keyC);
            for (String keyA: pu.anion.keySet()){
                Anion a = pu.anion.get(keyA);
                
                HopChat any = new HopChat(c, a);
                
                List <List<HopChat> > L = Collect(G, any);                               
                
                X = L.get(0);   Y = L.get(1);   Z = L.get(2);   T = L.get(3);
                
                cnt = (X.size() >= 1 ? 1 : 0) + (Y.size() >= 1 ? 1 : 0) + (Z.size() >= 1 ? 1 : 0) + (T.size() >= 1 ? 1 : 0);
                if (cnt > 1){                                                           
                    System.out.println("Chúng ta dùng chất " + any.getCTHH() + " để chia làm " + cnt + " nhóm");
                    if (X.size() > 0){
                        System.out.print("Phản ứng không có hiện tượng gồm những chất: ");
                        for (int i=0; i<X.size(); i++) System.out.print(X.get(i).getCTHH() + " ");
                        System.out.println();
                    }
                    if (Y.size() > 0){
                        System.out.print("Phản ứng tạo kết tủa gồm những chất: ");
                        for (int i=0; i<Y.size(); i++) System.out.print(Y.get(i).getCTHH() + " ");
                        System.out.println();
                    }
                    if (T.size() > 0){
                        System.out.print("Phản ứng tạo khí gồm những chất: ");
                        for (int i=0; i<Z.size(); i++) System.out.print(Z.get(i).getCTHH() + " ");
                        System.out.println();
                    }
                    if (T.size() > 0){
                        System.out.print("Phản ứng vừa tạo khí vừa tạo kết tủa gồm những chất: ");
                        for (int i=0; i<T.size(); i++) System.out.print(T.get(i).getCTHH() + " ");
                        System.out.println();
                    }
                    
                    Show(G, any);
                    
                    phanBiet(X);
                    phanBiet(Y);
                    phanBiet(Z);
                    phanBiet(T);
                    return ;
                }
            }
        }
    } 
}