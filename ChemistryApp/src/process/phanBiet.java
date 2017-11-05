
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
        
    }
    
    public static List<List<HopChat>> Collect(List<HopChat> G, HopChat A){        
        List <HopChat> X = new Vector<HopChat>();   // Không có hiện tượng
        List <HopChat> Y = new Vector<HopChat>();   // Kết tủa và không khí
        List <HopChat> Z = new Vector<HopChat>();   // Có khí và không kết tủa
        List <HopChat> T = new Vector<HopChat>();   // Vừa kết tủa vừa có khí
             
        for (int i=0; i<G.size(); i++){
            HopChat v = G.get(i);
            List <String> phanUng = pu.pu(v, A);
            // Không có hiện tượng
            boolean x = coKetTua(v);
            boolean y = coKhi(v);
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
            System.out.println("Đã phân biệt được " + G.get(0).getCTHH() + "\n");
            return ;
        }
        // Luật quỳ tím
        List <HopChat> X = new Vector<HopChat>();
        List <HopChat> Y = new Vector<HopChat>();   
        List <HopChat> Z = new Vector<HopChat>();
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
                X = L.get(0);   Y = L.get(1);   Z = L.get(2);
                
                cnt = (X.size() >= 1 ? 1 : 0) + (Y.size() >= 1 ? 1 : 0) + (Z.size() >= 1 ? 1 : 0);
                if (cnt > 1){
                    phanBiet(X);
                    phanBiet(Y);
                    phanBiet(Z);
                    return ;
                }
            }
        }
    }
}
