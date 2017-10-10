package homeAway;

import java.io.Serializable;

/**
 * This is the Stay Interface
 * A stay is container that offer a abstraction to represent when a user
 * stayed on a certain Property
 * @see StayClass
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public interface Stay extends Serializable{
	
	Property getProperty();
	
	int getPoints();
	
	

}
