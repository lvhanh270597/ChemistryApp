
package Basic;

public class Cation {
        
    private NguyenTo daiDien;    
    private float M;
    private int hoaTri;    
    
    public NguyenTo getDaiDien(){
        return this.daiDien;
    }
    
    /*public String getName(){
        return this.name;
    }*/
    
    public String getSymbol(){
        return this.daiDien.getSymbol();
    }
    
    public float getM(){
        return this.M;
    }            
    
    public int getHoaTri(){
        return this.hoaTri;
    }
    
    public void print(){
        System.out.println(this.getSymbol() + " " + this.M + " " + this.hoaTri);
    }
    
    public Cation(){}
    public Cation(NguyenTo daiDien, int hoaTri){        
        this.daiDien = daiDien;        
        this.M = daiDien.getM();
        this.hoaTri = hoaTri;       
    }
}
