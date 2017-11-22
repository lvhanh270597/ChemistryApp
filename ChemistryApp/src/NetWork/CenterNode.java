
package NetWork;

import Basic.*;
import java.util.*;

public class CenterNode {
    private List <NormalNode> in;
    private List <NormalNode> out;
    
    CenterNode(){}
    CenterNode(List <NormalNode> in, List <NormalNode> out){
        this.in = new Vector<NormalNode>();
        this.out = new Vector<NormalNode>();
        for (NormalNode node : in){
            this.in.add(node);
            node.setNext(this);            
        }        
        for (NormalNode node : out){
            this.out.add(node);
            node.setPrev(this);
        }        
    }
    
    List <NormalNode> getIn(){
        return this.in;
    }
    List <NormalNode> getOut(){
        return this.out;
    }
    
}
