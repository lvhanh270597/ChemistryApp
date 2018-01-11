
package testchemistry;

import java.util.*;
import org.jpl7.*;
import process.*;
import math.*;

public class TestChemistry {            
    public static void main(String[] args) {        
        knowledge.knowledge.prepare();        
        System.out.println();             
        /*String st = "Fe FeSO4 Fe(OH)2 FeCl2 FeCl3 Fe(OH)3 Fe2O3 Fe FeCl3";               
        System.out.println(chuoiPhanUng.find(st));     
        
        canBang cb = new canBang();
        
        for (String str : chuoiPhanUng.showAll(st)){                   
            str = cb.canbang(str);
            System.out.println(str);
        }*/
        
        /*String pt = "Fe + Cl2 = FeCl3";
        canBang cb = new canBang();
        System.out.println(cb.canbang(pt)); */
        
        /*hienTuong ht = new hienTuong("H2SO4");
        ht.showAll();
        ht.add("FeCl2");
        ht.showAll();
        ht.add("Ba(OH)2");
        ht.showAll();
        ht.del_kettua();
        ht.showAll();*/
        //System.out.println(phanBiet.getColor("BaSO4"));
        
        /*phanBiet pb = new phanBiet("NaCl Na2CO3 Na2SO4 BaCO3 BaSO4", true);
        pb.process();               
        */
        /*dieuChe dc = new dieuChe("KMnO4");
        int id = dc.find("O2");
        List <String> pth = dc.showPath(id);
        for (int i=pth.size() - 1; i>=0; i--){
            System.out.println(pth.get(i));
        }*/
        
        /*hopchat hc = knowledge.knowledge.getCA("FeCl3");
        System.out.println(hc.getCation());       
        System.out.println(hc.getAnion()); */
        timChat.Found();
    }     
}
