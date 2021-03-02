package lab5;

/**
 * This method is used to create location object
 * @author bruno
 *
 */

public class Location {
    private int x;
    private long y;
    private long z;


    Location() {}
    Location(int x, long y, long z) {
    	this.x = x;
    	this.y = y;
    	this.z = z;
    }
    
    /**
     *  
     * @return y location
     */

    public long getYloc() {
    	return y;
    }
    
    /**
     * 
     * @return x location
     */
    
    public int getXloc() {
    	return x;
    }
    
    /**
     * 
     * @return z location
     */
    
    public long getZloc() {
    	return z;
    }
    
    /**
     * this method return all information about location object
     * @return information
     */
    
    public String getShow() {
    	return " Location: ("+getXloc()+ ", " +getYloc()+ ", " +getZloc()+")";
    }
}