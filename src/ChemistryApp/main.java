/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChemistryApp;

import knowledge.phanUng;
import Interface.*;
<<<<<<< HEAD
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import math.LCS;
<<<<<<< HEAD
import process.dieuChe;
=======
<<<<<<< HEAD
import process.dieuChe;
=======
import process.phanBiet;
=======
import org.jpl7.Query;
import process.canBang;
>>>>>>> b6a3b76a7aaf77cd47fa1869cff39ae95a82b6b6
>>>>>>> cfea36dd956f8a020992ef0da2093da5f6fa760e
>>>>>>> 8d1858fbae17070df787f26f77a5cc13e25f36fd
/**
 *
 * @author hanh
 */
public class main {
<<<<<<< HEAD
    public static void main(String[] args) throws IOException{

        knowledge.knowledge.prepare();        
        System.out.println(); 
        //System.out.println(phanUng.phanUng("Na2SO4 BaCl2"));
        mainInterface a = new mainInterface();
<<<<<<< HEAD
        a.show();            
        

=======
<<<<<<< HEAD
        a.show();            
        

=======
        a.show();    
                
/*        */
=======
    public static void main(String[] args){
        knowledge.knowledge.prepare();        
        System.out.println(); 
        /*
        String s = "NaOH + HCl = NaCl + H2O";
        canBang a = new canBang();
        System.out.println(a.canbang(s));
        System.out.println(a.huongdan);
        */
        
      /*  multiThread t1 = new multiThread("Create a winndow", 1);
        multiThread t2 = new multiThread("Chat bot", 2);        
        t1.start();
        t2.start();*/
        //chatBotInterface a = new chatBotInterface();
        //a.show();
        canBangF a = new canBangF();
        a.show();
        //System.out.println(phanUng.phanUng("Na2SO4 BaCl2"));
      //  new mainInterface();
>>>>>>> b6a3b76a7aaf77cd47fa1869cff39ae95a82b6b6
>>>>>>> cfea36dd956f8a020992ef0da2093da5f6fa760e
>>>>>>> 8d1858fbae17070df787f26f77a5cc13e25f36fd
    }
}
