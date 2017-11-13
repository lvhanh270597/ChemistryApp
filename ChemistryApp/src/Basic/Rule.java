
package Basic;

import java.util.*;
import javafx.util.*;

public class Rule {
    private String rule;
    private List<String> cdt;
    private String use;
    public Rule(){}
    public Rule(String rule, List<String> cdt, String use){
        this.rule = rule;
        this.cdt = cdt;
        this.use = use;
    }
    public String getRule(){
        return this.rule;        
    }
    public List<String> getCdt(){
        return this.cdt;
    }
    public String getUse(){
        return this.use;
    }
    public void print(){
        System.out.println(this.rule);
        for (int i=0; i<this.cdt.size(); i++){
            System.out.print(this.cdt.get(i) + " ");            
        }
        System.out.println();
        System.out.println(this.use);
    }
}
