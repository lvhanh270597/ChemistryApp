
package NetWork;

import Basic.*;
import knowledge.*;

public class NormalNode {
    private HopChat chat;
    private float mol;
    private float m;
    private CenterNode prev;
    private CenterNode next;
    
    public boolean active;
    
    NormalNode(){}
    NormalNode(HopChat chat){
        this.chat = chat;
        this.active = false;
        this.prev = null;
        this.next = null;
    }
    
    NormalNode(HopChat chat, float mol){
        this.chat = chat;
        this.active = true;
        this.mol = mol;
        this.m = (this.chat.getM() * this.mol);
        this.prev = null;
        this.next = null;
    }
    
    public HopChat getChat(){
        return this.chat;
    }
    public float getMol(){
        return this.mol;
    }
    public float getM(){
        return this.m;
    }
    public CenterNode getPrev(){
        return this.prev;
    }
    public CenterNode getNext(){
        return this.next;
    }
    public void setPrev(CenterNode ct){
        this.prev = ct;
    }
    public void setNext(CenterNode ct){
        this.next = ct;
    }
    public void setMol(float mol){
        this.mol = mol;
        this.m = mol * this.chat.getM();
        this.active = true;
    }
    public void setM(float M){
        this.m = M;
        this.mol = (M / this.chat.getM());
        this.active = true;
    }
}
