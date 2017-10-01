package homeAway;

import java.io.Serializable;

public interface Stay extends Serializable{
	
	Property getProperty();
	
	int getPoints();
	
	

}
