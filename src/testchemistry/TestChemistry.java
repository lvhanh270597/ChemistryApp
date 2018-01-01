
package testchemistry;

import java.util.*;
import org.jpl7.*;
import process.*;

public class TestChemistry {            
    public static void main(String[] args) {        
        process.knowledge.prepare();        
        System.out.println();             
//        String st = "Fe FeSO4 Fe(OH)2 FeCl2 FeCl3 Fe(OH)3 Fe2O3 Fe FeCl3";               
//        System.out.println(chuoiPhanUng.find(st));     
//        
//        for (String str : chuoiPhanUng.showAll(st))
//            System.out.println(str);
//        
        String pt = "Fe + HCl = FeCl2 + H2";
        canBang cb = new canBang();
        System.out.println(cb.canbang(pt));       
        
    } 
    
}
