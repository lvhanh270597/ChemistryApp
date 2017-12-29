
package process;

import org.jpl7.*;


public class knowledge {

    public static void prepare(){
        Query q1 = new Query("consult('knowledge/app.pl')");        
        q1.hasSolution();
        q1 = new Query("readKnowledge");
        q1.hasSolution();
    }
   
    public static float nguyenTo(String X){
        Query q = new Query("nguyento('" + X + "\', X)");        
        if (!q.hasSolution()) return -1;
        Term a = q.oneSolution().get("X");
        return a.floatValue();
    }
}
