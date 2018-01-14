/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChemistryApp;

import knowledge.phanUng;
import Interface.*;
import org.jpl7.Query;
import process.canBang;
/**
 *
 * @author hanh
 */
public class main {
    public static void main(String[] args){
        knowledge.knowledge.prepare();        
        System.out.println(); 
        /*
        String s = "NaOH + HCl = NaCl + H2O";
        canBang a = new canBang();
        System.out.println(a.canbang(s));
        System.out.println(a.huongdan);
        */
        
        multiThread t1 = new multiThread("Create a winndow", 1);
        multiThread t2 = new multiThread("Chat bot", 2);        
        t1.start();
        t2.start();
        //chatBotInterface a = new chatBotInterface();
        //a.show();
       // canBangF a = new canBangF();
        //a.show();
        //System.out.println(phanUng.phanUng("Na2SO4 BaCl2"));
      //  new mainInterface();
    }
}
