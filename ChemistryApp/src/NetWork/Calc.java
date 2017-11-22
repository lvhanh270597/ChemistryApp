
package NetWork;

import java.util.*;
import javafx.util.*;
import knowledge.*;
import process.*;
import Basic.*;

public class Calc {
    
    private static Map <NormalNode, Boolean> travel;
    
    public static void print(String X){
        System.out.print(X);
    }    
    public static float getMol(float m, HopChat X){
        return m / X.getM();
    }
    public static float getWeight(float mol, HopChat X){
        return mol * X.getM();
    }
    public static float getV(float mol){
        return mol * (float) 22.4;
    }
    public static float getMol(float V){
        return V / (float) 22.4;
    }        
    
    public static int hasElement(String X, List <String> elements){        
        for (int i=0; i<elements.size(); i++)
            if (X.equals(elements.get(i))) return i;
        return -1;
    }
    
    public static Pair<Boolean, Float> getWeight(CenterNode center, NormalNode goal, boolean root){   // root = true, means sanpham             
        if (goal.active) {
            Pair<Boolean, Float> tmp = new Pair<Boolean, Float>(true, goal.getM());            
            return tmp;
        }
        if (center == null) 
            return null;
        if (travel.containsKey(goal)) 
            return null; 
        travel.put(goal, true);
        
        String CTHH = goal.getChat().getCTHH();
        
        // Bảo toàn khối lượng        
        boolean OK = true;
        Pair <Boolean, Float> result = null;
        if (root){            
            float M1 = 0;
            for (NormalNode node : center.getIn()){                             
                Pair <Boolean, Float> tmp = getWeight(node.getPrev(), node, true);
                if (tmp == null){
                    OK = false;
                    break;
                }
                M1 += tmp.getValue();                
            }    
            float M2 = 0;
            for (NormalNode node : center.getOut()){
                String CTHH_node = node.getChat().getCTHH();
                if (CTHH.equals(CTHH_node)) continue;
                Pair <Boolean, Float> tmp = getWeight(node.getNext(), node, false);
                if (tmp == null){
                    OK = false;
                    break;
                }
                M2 += tmp.getValue();
            }
            result = new Pair<Boolean, Float> (true, M1 - M2);
        }
        else{
            float M1 = 0;
            for (NormalNode node : center.getIn()){
                String CTHH_node = node.getChat().getCTHH();
                if (CTHH.equals(CTHH_node)) continue;
                Pair <Boolean, Float> tmp = getWeight(node.getPrev(), node, true);
                if (tmp == null){
                    OK = false;
                    break;
                }
                M1 += tmp.getValue();
            }
            float M2 = 0;
            for (NormalNode node : center.getOut()){                             
                Pair <Boolean, Float> tmp = getWeight(node.getNext(), node, false);
                if (tmp == null){
                    OK = false;
                    break;
                }
                M2 += tmp.getValue();                
            }            
            result = new Pair<Boolean, Float> (true, M2 - M1);
        }        
        if (OK){
            goal.setM(result.getValue());
            return result;
        }
                
        //Bảo toàn nguyên tố
        int index_ok = 0;
        if (root){       
            
            HopChat hc = goal.getChat();
            Pair <List, List> components = hc.getComponents();
            List <String> elements = components.getKey();
            List <Integer> heso = components.getValue();
            
            float Mol_1 = 0;
            float Mol_2 = 0;
            for (int i=0; i<elements.size(); i++){   
                
                OK = true;
                
                for (NormalNode node : center.getIn()){  
                        
                    Pair <List, List> components_node = node.getChat().getComponents();
                    List <String> elements_node = components_node.getKey();
                    List <Integer> heso_node = components_node.getValue();
                                        
                    int index = hasElement(elements.get(i), elements_node);
                    if (index >= 0){                                               
                        Pair <Boolean, Float> tmp = getWeight(node.getPrev(), node, true);
                        if (tmp == null){
                            OK = false;
                            break;
                        }                                                
                        Mol_1 += getMol(tmp.getValue(), node.getChat()) * heso_node.get(index);
                    }
                }                    
                for (NormalNode node : center.getOut()){
                    
                    String CTHH_node = node.getChat().getCTHH();
                    if (CTHH.equals(CTHH_node)) continue;
                    
                    Pair <List, List> components_node = node.getChat().getComponents();
                    List <String> elements_node = components_node.getKey();
                    List <Integer> heso_node = components_node.getValue();
                    
                    int index = hasElement(elements.get(i), elements_node);
                    if (index >= 0){
                        Pair <Boolean, Float> tmp = getWeight(node.getNext(), node, false);
                        if (tmp == null){
                            OK = false;
                            break;
                        }
                        Mol_2 += getMol(tmp.getValue(), node.getChat()) * heso_node.get(index);
                    }
                }
                
                if (OK) {
                    index_ok = i;
                    break;
                }
                
            }            
            if (OK){
                result = new Pair<Boolean, Float> (true, getWeight((Mol_1 - Mol_2) / heso.get(index_ok), goal.getChat()));
                goal.setM(result.getValue());
                return result;
            }
        }
        else{
            HopChat hc = goal.getChat();
            Pair <List, List> components = hc.getComponents();
            List <String> elements = components.getKey();
            List <Integer> heso = components.getValue();                        
            
            float Mol_1 = 0;
            float Mol_2 = 0;
            for (int i=0; i<elements.size(); i++){   
                
                OK = true;
                
                for (NormalNode node : center.getIn()){                      
                    String CTHH_node = node.getChat().getCTHH();
                    if (CTHH.equals(CTHH_node)) continue;
                    
                    Pair <List, List> components_node = node.getChat().getComponents();
                    List <String> elements_node = components_node.getKey();
                    List <Integer> heso_node = components_node.getValue();
                    
                    int index = hasElement(elements.get(i), elements_node);
                    if (index >= 0){                                            
                        Pair <Boolean, Float> tmp = getWeight(node.getPrev(), node, true);
                        if (tmp == null){
                            OK = false;
                            break;
                        }                        
                        Mol_1 += getMol(tmp.getValue(), node.getChat()) * heso_node.get(index);
                    }
                }                    
                for (NormalNode node : center.getOut()){                                       
                    
                    Pair <List, List> components_node = node.getChat().getComponents();
                    List <String> elements_node = components_node.getKey();
                    List <Integer> heso_node = components_node.getValue();
                    
                    int index = hasElement(elements.get(i), elements_node);
                    if (index >= 0){
                        Pair <Boolean, Float> tmp = getWeight(node.getNext(), node, false);
                        if (tmp == null){
                            OK = false;
                            break;
                        }
                        Mol_2 += getMol(tmp.getValue(), node.getChat()) * heso_node.get(index);
                    }
                }
                
                if (OK) {
                    index_ok = i;
                    break;
                }
                
            }
            if (OK){
                result = new Pair<Boolean, Float> (true, getWeight((Mol_2 - Mol_1) / heso.get(index_ok), goal.getChat()));
                goal.setM(result.getValue());
                return result;
            }
        }                
        
        return null;
    }        
    
    public static void run(){
        HopChat NaOH = knowledge.getHC("NaOH");
        HopChat H2SO4 = knowledge.getHC("H2SO4");
        
        NormalNode tg1 = new NormalNode(NaOH);
        NormalNode tg2 = new NormalNode(H2SO4, (float) 0.02);
        List <NormalNode> ThamGia = new Vector<NormalNode>();
        List <NormalNode> SanPham = new Vector<NormalNode>();
        ThamGia.add(tg1);   
        ThamGia.add(tg2);
        
        List <String> sp = pu.pu(NaOH, H2SO4);        
        if (sp.size() == 0) sp = pu.pu(H2SO4, NaOH);
        for (String key : sp){
            HopChat A = knowledge.getHC(key);
            NormalNode tmp = new NormalNode(A);
            SanPham.add(tmp);
            print(key +" ");            
        }                
        print("\n");
        
        //SanPham.get(0).setM((float) 2.84);
        
        CenterNode ct1, ct2;
        ct1 = new CenterNode(ThamGia, SanPham);    
        
        ThamGia.clear();        
        
        HopChat Na2SO4 = SanPham.get(0).getChat();
        HopChat BaOH = knowledge.getHC("Ba(OH)2");
        
        NormalNode tg3 = SanPham.get(0);
        NormalNode tg4 = new NormalNode(BaOH);
        
        ThamGia.add(tg3);   ThamGia.add(tg4);
        
        SanPham.clear();
        
        sp = pu.pu(Na2SO4, BaOH);        
        if (sp.size() == 0) sp = pu.pu(BaOH, Na2SO4);
        for (String key : sp){
            HopChat A = knowledge.getHC(key);
            NormalNode tmp = new NormalNode(A);
            SanPham.add(tmp);
            print(key +" ");            
        }                
        print("\n");
        
        ct2 = new CenterNode(ThamGia, SanPham);
        
        travel = new HashMap <NormalNode, Boolean>();
        NormalNode goal = ct2.getOut().get(0);                       
        
        Pair <Boolean, Float> result = getWeight(ct2, goal, true);
        if (result == null) 
            print("Can not calculate " + goal.getChat().getCTHH() + "\n");
        else {
            print("m" + goal.getChat().getCTHH() + " = " + result.getValue() + "\n");
            print(getMol(result.getValue(), goal.getChat()) + "\n");
        }
    } 
}
