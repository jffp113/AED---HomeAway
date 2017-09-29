package homeAway;

import java.io.Serializable;

/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public interface Property extends Serializable{
	
	 String getIdHome();
	 
	 String getAdress();
	 
	 String getDescription();
	 
	 String getLocal();
	 
	 int getPrice();
	  
	 int getMaxPersons();
	  
	 User getOwner();
	  
	 int getPoints();

	 int getNumberOfvisits();
	 
	 
}
