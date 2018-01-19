/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChemistryApp;

import knowledge.phanUng;
import Interface.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import math.LCS;
import process.dieuChe;
/**
 *
 * @author hanh
 */
public class main {
    public static void main(String[] args) throws IOException{

        knowledge.knowledge.prepare();        
        System.out.println(); 
        //System.out.println(phanUng.phanUng("Na2SO4 BaCl2"));
        mainInterface a = new mainInterface();
        a.show();            
        

    }
}
