
package process;

import java.util.*;
import Basic.*;
import knowledge.knowledge;
import process.*;

public class phanBiet {
    
    private List<String> phanTich;
    private boolean quy;
    private List<HopChat> except;
    
    public phanBiet(){}
    public phanBiet(String X, boolean quy, String E){
        phanTich = new Vector<String>();
        List <HopChat> list_phanBiet = new Vector<HopChat>();        
        String[] v = X.split("\\s");
        for (String st : v){            
            HopChat hc = knowledge.getHC(st);
            if (hc != null){
                list_phanBiet.add(hc);
            }
        }
        
        this.quy = quy;
        
        this.except = new Vector<HopChat>();
        v = E.split("\\s");
        for (String st : v){            
            HopChat hc = knowledge.getHC(st);
            if (hc != null){
                this.except.add(hc);
            }
        }
        
        process(list_phanBiet);
    }
    
    public List<String> getPhanTich(){
        return this.phanTich;
    }
    
    private boolean axit(HopChat X){
        return X.getCation().getSymbol().equals("H");
    }
    
    private boolean bazo(HopChat X){
        return X.getAnion().getSymbol().equals("OH");
    }
    
    private boolean muoi(HopChat X){
        return !(axit(X) || bazo(X));
    }
    
    private HopChat coKetTua(List <String> G){
        for (int i=0; i<G.size(); i++){
            String v = G.get(i);
            if (knowledge.khongTan.containsKey(v)){
                return knowledge.khongTan.get(v);
            }
        }
        return null;
    }    
    
    private boolean coKhi(List <String> G){
        for (int i=0; i<G.size(); i++){
            String v = G.get(i);
            if (knowledge.khi.containsKey(v)){
                return true;
            }
        }
        return false;
    }
    
    private void Show(List<HopChat> G, HopChat A){
        for (int i=0; i<G.size(); i++){
            HopChat v = G.get(i);
            List <String> phanUng = pu.execute(v, A);
            if (phanUng.size() == 0){
                phanUng = pu.execute(A, v);
            }
            // Không có hiện tượng
            HopChat Color = coKetTua(phanUng);
            boolean x = (Color != null);
            boolean y = coKhi(phanUng);
            boolean z = !x && !y;
            if (phanUng.size() == 0){                
                //System.out.println(v.getCTHH() + " không phản ứng " + A.getCTHH() + " ==> không thấy hiện tượng");           
                this.phanTich.add(v.getCTHH() + " không phản ứng " + A.getCTHH() + " ==> không thấy hiện tượng\n");
            }            
            else{
                if (z){                    
                    //System.out.print(v.getCTHH() + " + " + A.getCTHH() + " = ");
                    this.phanTich.add(v.getCTHH() + " + " + A.getCTHH() + " = ");
                    for (int j=0; j<phanUng.size() - 1; j++){
                        //System.out.print(phanUng.get(j) + " + ");
                        this.phanTich.add(phanUng.get(j) + " + ");
                    }
                    //System.out.println(phanUng.get(phanUng.size() - 1) + " ==> không thấy hiện tượng");
                    this.phanTich.add(phanUng.get(phanUng.size() - 1) + " ==> không thấy hiện tượng\n");
                }
                else{
                    if (x && !y){
                        //System.out.print(v.getCTHH() + " + " + A.getCTHH() + " = ");
                        this.phanTich.add(v.getCTHH() + " + " + A.getCTHH() + " = ");
                        for (int j=0; j<phanUng.size() - 1; j++){
                            //System.out.print(phanUng.get(j) + " + ");
                            this.phanTich.add(phanUng.get(j) + " + ");
                        }
                        this.phanTich.add(phanUng.get(phanUng.size() - 1) + " ==> kết tủa\n");
                        //System.out.println(phanUng.get(phanUng.size() - 1) + " ==> kết tủa");
                    }
                    else{
                        if (!x && y){
                            //System.out.print(v.getCTHH() + " + " + A.getCTHH() + " = ");
                            this.phanTich.add(v.getCTHH() + " + " + A.getCTHH() + " = ");
                            for (int j=0; j<phanUng.size() - 1; j++){
                                //System.out.print(phanUng.get(j) + " + ");
                                this.phanTich.add(phanUng.get(j) + " + ");
                            }
                            //System.out.println(phanUng.get(phanUng.size() - 1) + " ==> khí");
                            this.phanTich.add(phanUng.get(phanUng.size() - 1) + " ==> khí\n");
                        }
                        else{
                            //System.out.print(v.getCTHH() + " + " + A.getCTHH() + " = ");
                            this.phanTich.add(v.getCTHH() + " + " + A.getCTHH() + " = ");
                            for (int j=0; j<phanUng.size() - 1; j++){
                                //System.out.print(phanUng.get(j) + " + ");
                                this.phanTich.add(phanUng.get(j) + " + ");
                            }
                            //System.out.println(phanUng.get(phanUng.size() - 1) + " ==> vừa khí vừa kết tủa");
                            this.phanTich.add(phanUng.get(phanUng.size() - 1) + " ==> vừa khí vừa kết tủa\n");
                        }
                    }
                }
            }
        }
    }
    
    private List<List<HopChat>> Collect(List<HopChat> G, HopChat A){        
        List <HopChat> X = new Vector<HopChat>();       // Không có hiện tượng
        List <HopChat> Y = new Vector<HopChat>();       // Kết tủa và không khí
        List <HopChat> Y_color = new Vector<HopChat>(); // Màu của Y
        List <HopChat> Z = new Vector<HopChat>();       // Có khí và không kết tủa        
        List <HopChat> T = new Vector<HopChat>();       // Vừa kết tủa vừa có khí
        List <HopChat> T_color = new Vector<HopChat>(); // Màu của T
             
        for (int i=0; i<G.size(); i++){
            HopChat v = G.get(i);
            List <String> phanUng = pu.execute(v, A);
            if (phanUng.size() == 0){
                phanUng = pu.execute(A, v);
            }
            // Không có hiện tượng
            HopChat Color = coKetTua(phanUng);
            boolean x = (Color != null);
            boolean y = coKhi(phanUng);
            boolean z = !x && !y;
            if (phanUng.size() == 0 || z){
                X.add(v);
            }            
            else{
                if (x && !y){                    
                    Y.add(v);
                    Y_color.add(Color);
                }
                else{
                    if (!x && y){
                        Z.add(v);
                    }
                    else{
                        T.add(v);
                        T_color.add(Color);
                    }
                }
            }
        }
        
        
        List<List<HopChat>> result = new Vector<List<HopChat>>();
        result.add(X);
        result.add(Y);
        result.add(Y_color);
        result.add(Z);
        result.add(T);
        result.add(T_color);
        return result;
    }    
    
    private boolean phanBietMau(List <HopChat> G){
        Map <String, Integer> differences = new HashMap<String, Integer>();
        
        for (HopChat hc :  G){                                    
            differences.put(hc.getColor(), 0);
        }        
        for (HopChat hc :  G){                       
            int cnt = differences.get(hc.getColor());
            cnt += 1;
            differences.replace(hc.getColor(), cnt);            
        }

        return (differences.size() == G.size());

    }
    
    private void showMau(List <HopChat> G, List <HopChat> R){                                     
        for (HopChat any : G){
            HopChat hc = knowledge.khongTan.get(any.getCTHH()); 
            //System.out.print(hc.getCTHH() + " có màu: " + hc.getColor() + "\n");
            this.phanTich.add(hc.getCTHH() + " có màu: " + hc.getColor() + "\n");
        }                
        //System.out.print("Đã phân biệt được các chất: ");
        this.phanTich.add("Đã phân biệt được các chất: ");
        for (int i=0; i<G.size(); i++) this.phanTich.add(R.get(i).getCTHH() + " ");//System.out.print(R.get(i).getCTHH() + " ");                
        this.phanTich.add("\n");
        //System.out.println();        
    }
    
    private void process(List <HopChat> G){
        if (G.size() == 0) return;
        if (G.size() == 1) {
            //System.out.println("Đã phân biệt được " + G.get(0).getCTHH());
            this.phanTich.add("Đã phân biệt được " + G.get(0).getCTHH() + "\n");
            return ;
        }                
        
        this.phanTich.add("Cần phân biệt các chất: ");
        //System.out.print("Cần phân biệt các chất: ");
        for (int i=0; i<G.size(); i++) this.phanTich.add(G.get(i).getCTHH() + " "); //System.out.print(G.get(i).getCTHH() + " ");
        //System.out.println();
        this.phanTich.add("\n");
        
        boolean tat_ca_khong_tan = true;
        for (HopChat hc : G){
            if (!knowledge.khongTan.containsKey(hc.getCTHH())){
                tat_ca_khong_tan = false;
                break;
            }
        }
        if (tat_ca_khong_tan){
            List <HopChat> Temp = new Vector<HopChat>();
            for (HopChat hc : G){
                HopChat tl = knowledge.khongTan.get(hc.getCTHH());                
                Temp.add(tl);
            }
            if (phanBietMau(Temp)){
                showMau(Temp, G);
                return;
            }
        }
        /*
                  
        */                    
            List <HopChat> X = new Vector<HopChat>();
            List <HopChat> Y = new Vector<HopChat>();   
            List <HopChat> Y_color = new Vector<HopChat>();
            List <HopChat> Z = new Vector<HopChat>();
            List <HopChat> T = new Vector<HopChat>();
            List <HopChat> T_color = new Vector<HopChat>();
        
        if (this.quy){
            // Luật quỳ tím
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
                this.phanTich.add("Chúng ta dùng quỳ tím để chia làm " + cnt + " nhóm chất: \n");
                //System.out.println("Chúng ta dùng quỳ tím để chia làm " + cnt + " nhóm chất: ");
                if (X.size() > 0){
                    this.phanTich.add("Làm quỳ tím hóa đỏ gồm những chất: ");
                    //System.out.print("Làm quỳ tím hóa đỏ gồm những chất: ");
                    for (int i=0; i<X.size(); i++) this.phanTich.add(X.get(i).getCTHH() + " "); //System.out.print(X.get(i).getCTHH() + " ");
                    this.phanTich.add("\n");
                    //System.out.println();
                }
                if (Y.size() > 0){
                    this.phanTich.add("Làm quỳ tím hóa xanh gồm những chất: ");
                    //System.out.print("Làm quỳ tím hóa xanh gồm những chất: ");
                    for (int i=0; i<Y.size(); i++) this.phanTich.add(Y.get(i).getCTHH() + " "); //System.out.print(Y.get(i).getCTHH() + " ");
                    //System.out.println();
                    this.phanTich.add("\n");
                }
                if (Z.size() > 0){
                    this.phanTich.add("Làm quỳ tím không đổi màu gồm những chất: ");
                    //System.out.print("Làm quỳ tím không đổi màu gồm những chất: ");
                    for (int i=0; i<Z.size(); i++) this.phanTich.add(Z.get(i).getCTHH() + " "); //System.out.print(Z.get(i).getCTHH() + " ");
                    //System.out.println();
                    this.phanTich.add("\n");
                }
                process(X);
                process(Y);
                process(Z);
                return ;
            }
        }
        // Luật phản ứng với một chất
        X.clear();  Y.clear();  Z.clear();        
        if (this.except.size() == 0){
            for (String keyC: knowledge.cation.keySet()){
                Cation c = knowledge.cation.get(keyC);
                for (String keyA: knowledge.anion.keySet()){
                    Anion a = knowledge.anion.get(keyA);

                    HopChat any = new HopChat(c, a);

                    if (knowledge.khongTan.containsKey(any.getCTHH())){
                        continue;
                    }
                    
                    List <List<HopChat> > L = Collect(G, any);                               

                    X = L.get(0);
                    Y = L.get(1);
                    Y_color = L.get(2);
                    Z = L.get(3);
                    T = L.get(4);
                    T_color = L.get(5);

                    List <List<HopChat>> NeedTo = new Vector<List<HopChat>>();
                    
                    int cnt = (X.size() >= 1 ? 1 : 0) + (Y.size() >= 1 ? 1 : 0) + (Z.size() >= 1 ? 1 : 0) + (T.size() >= 1 ? 1 : 0);
                    if (cnt > 1){                          
                        this.phanTich.add("Chúng ta dùng chất " + any.getCTHH() + " để chia làm " + cnt + " nhóm\n");
                        //System.out.println("Chúng ta dùng chất " + any.getCTHH() + " để chia làm " + cnt + " nhóm");
                        if (X.size() > 0){
                            this.phanTich.add("Phản ứng không có hiện tượng gồm những chất: ");
                            //System.out.print("Phản ứng không có hiện tượng gồm những chất: ");
                            for (int i=0; i<X.size(); i++) this.phanTich.add(X.get(i).getCTHH() + " "); //System.out.print(X.get(i).getCTHH() + " ");
                            this.phanTich.add("\n");
                            //System.out.println();
                            NeedTo.add(X);
                        }
                        if (Y.size() > 0){
                            this.phanTich.add("Phản ứng tạo kết tủa gồm những chất: ");
                            //System.out.print("Phản ứng tạo kết tủa gồm những chất: ");
                            for (int i=0; i<Y.size(); i++) this.phanTich.add(Y.get(i).getCTHH() + " "); //System.out.print(Y.get(i).getCTHH() + " ");
                            this.phanTich.add("\n");
                            //System.out.println();
                            if (phanBietMau(Y_color)){
                                showMau(Y_color, Y);
                            }
                            else{
                                NeedTo.add(Y);
                            }
                        }
                        if (Z.size() > 0){
                            this.phanTich.add("Phản ứng tạo khí gồm những chất: ");
                            //System.out.print("Phản ứng tạo khí gồm những chất: ");
                            for (int i=0; i<Z.size(); i++) this.phanTich.add(Z.get(i).getCTHH() + " "); //System.out.print(Z.get(i).getCTHH() + " ");
                            this.phanTich.add("\n");
                            //System.out.println();
                            NeedTo.add(Z);
                        }
                        if (T.size() > 0){
                            this.phanTich.add("Phản ứng vừa tạo khí vừa tạo kết tủa gồm những chất: ");
                            //System.out.print("Phản ứng vừa tạo khí vừa tạo kết tủa gồm những chất: ");
                            for (int i=0; i<T.size(); i++) System.out.print(T.get(i).getCTHH() + " ");
                            System.out.println();
                             if (phanBietMau(T_color)){
                                showMau(T_color, T);
                            }
                            else{
                                NeedTo.add(T);
                            }
                        }

                        Show(G, any);

                        for (int i=0; i<NeedTo.size(); i++) 
                            process(NeedTo.get(i));
                        return ;
                    }
                }
            }
        }
        else{            
            for (HopChat any: this.except){                                                

                List <List<HopChat> > L = Collect(G, any);                               

                X = L.get(0);
                Y = L.get(1);
                Y_color = L.get(2);
                Z = L.get(3);
                T = L.get(4);
                T_color = L.get(5);

                List <List<HopChat>> NeedTo = new Vector<List<HopChat>>();

                int cnt = (X.size() >= 1 ? 1 : 0) + (Y.size() >= 1 ? 1 : 0) + (Z.size() >= 1 ? 1 : 0) + (T.size() >= 1 ? 1 : 0);
                if (cnt > 1){                     
                    this.phanTich.add("Chúng ta dùng chất " + any.getCTHH() + " để chia làm " + cnt + " nhóm");
                    //System.out.println("Chúng ta dùng chất " + any.getCTHH() + " để chia làm " + cnt + " nhóm");
                    if (X.size() > 0){
                        this.phanTich.add("Phản ứng không có hiện tượng gồm những chất: ");
                        //System.out.print("Phản ứng không có hiện tượng gồm những chất: ");
                        for (int i=0; i<X.size(); i++) this.phanTich.add(X.get(i).getCTHH() + " ");// System.out.print(X.get(i).getCTHH() + " ");
                        this.phanTich.add("\n");
                        //System.out.println();
                        NeedTo.add(X);
                    }
                    if (Y.size() > 0){
                        this.phanTich.add("Phản ứng tạo kết tủa gồm những chất: ");
                        //System.out.print("Phản ứng tạo kết tủa gồm những chất: ");
                        for (int i=0; i<Y.size(); i++) this.phanTich.add(Y.get(i).getCTHH() + " ");// System.out.print(Y.get(i).getCTHH() + " ");
                        this.phanTich.add("\n");
                        //System.out.println();
                        if (phanBietMau(Y_color)){
                            showMau(Y_color, Y);
                        }
                        else{
                            NeedTo.add(Y);
                        }
                    }
                    if (Z.size() > 0){
                        this.phanTich.add("Phản ứng tạo khí gồm những chất: ");
                        //System.out.print("Phản ứng tạo khí gồm những chất: ");
                        for (int i=0; i<Z.size(); i++) System.out.print(Z.get(i).getCTHH() + " ");
                        System.out.println();
                        NeedTo.add(Z);
                    }
                    if (T.size() > 0){
                        this.phanTich.add("Phản ứng vừa tạo khí vừa tạo kết tủa gồm những chất: ");
                        //System.out.print("Phản ứng vừa tạo khí vừa tạo kết tủa gồm những chất: ");
                        for (int i=0; i<T.size(); i++) this.phanTich.add(T.get(i).getCTHH() + " "); //System.out.print(T.get(i).getCTHH() + " ");
                        this.phanTich.add("\n");
                        //System.out.println();
                         if (phanBietMau(T_color)){
                            showMau(T_color, Y);
                        }
                        else{
                            NeedTo.add(T);
                        }
                    }

                    Show(G, any);

                    for (int i=0; i<NeedTo.size(); i++) 
                        process(NeedTo.get(i));
                    return ;
                }
            }            
        }
        this.phanTich.add("Không thể phân biệt được: ");
        //System.out.print("Không thể phân biệt được: ");
        for (int i=0; i<G.size(); i++) this.phanTich.add(G.get(i).getCTHH() + " ");// System.out.print(G.get(i).getCTHH() + " ");
        this.phanTich.add("\n");
        //System.out.println();
    } 
}
