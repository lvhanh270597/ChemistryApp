
package Basic;

public class NguyenTo{
   
    private String symbolChemical;
    private float M;
    private boolean KL;
    
    public float getM(){
        return this.M;
    }
    
    /*public String getName(){
        return this.name;
    }*/
    
    public String getSymbol(){
        return this.symbolChemical;
    }
    
    public boolean getKL(){
        return this.KL;
    }             
    
    public void print(){
        System.out.println(this.symbolChemical + " " + this.M + " " + this.KL);
    }
    
    public NguyenTo(){}
    public NguyenTo(String symbol, float value, boolean kl){        
        this.symbolChemical = symbol;
        this.M = value;
        this.KL = kl;
    }    
    
}
