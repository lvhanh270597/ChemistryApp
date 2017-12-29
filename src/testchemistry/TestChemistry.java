
package testchemistry;

import java.util.*;
import org.jpl7.*;
import process.chuoiPhanUng;
import process.phanUng;

public class TestChemistry {            
    public static void main(String[] args) {        
        process.knowledge.prepare();        
        String st = "Fe FeSO4 Fe(OH)2 FeCl2 FeCl3 Fe(OH)3";
        chuoiPhanUng chuoi = new chuoiPhanUng(st);
        System.out.println();             
        System.out.println(chuoi.find(st));
    } 
    
}
