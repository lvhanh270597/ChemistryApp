
package Basic;

public class DonChat {
    
    private NguyenTo daiDien;    
    private int soLuong;
    private String key;
           
    public String getCTHH(){
        String s = this.daiDien.getSymbol();
        if (this.soLuong > 1) 
            s += Integer.toString(this.soLuong);
        return s;
    }
    
    public NguyenTo getDaiDien(){
        return this.daiDien;
    }            
    
    public int getCnt(){
        return this.soLuong;
    }
    
    public float getM(){
        return this.daiDien.getM() * this.soLuong;
    }
    
    public String getKey(){
        return this.key;
    }
    
    public void print(){
        System.out.println(this.getCTHH());
    }
    
    public DonChat(){} 
    public DonChat(NguyenTo DD, int cnt){
        this.daiDien = DD;
        this.soLuong = cnt;        
        this.key = this.daiDien.getSymbol() + "_" + cnt;
    }
}
