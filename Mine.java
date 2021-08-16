
public class Mine {

    private boolean active;
    private boolean hidden;
    private int neighbors;
    
    public  Mine(){
        active = false;
        hidden = true;
        neighbors = 0;
    }
    public void setActive (boolean active){
        this.active = active;
    }
    public void setHidden( boolean hidden){
        this.hidden = hidden;
    }
    public void setNeighbors (int neighbors){
        this.neighbors = neighbors;
    }
    public boolean getActive(){
        return active;
    }
    public boolean getHidden(){
        return hidden;
    }
    public int getNeighbors(){
        return neighbors;
    }
}
