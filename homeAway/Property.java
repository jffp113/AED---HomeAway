package homeAway;

import java.io.Serializable;

/**
 * This is the Interface of a Property, this class contains
 * only getter of the informations so that outside the package 
 * information can not be modifies. If you are working inside the package
 * please use UserWritable
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
public interface Property extends Serializable{
	
	/**
	 * This method gets the id of a certain Home.
	 * This can be used in order to check if this Property is what user
	 * is looking for. But can be used in other ways
	 * @return idHome
	 */
	 String getIdHome();
	 
	 /**
	  * This method gets the address of the Property
	  * @return Address as a String
	  */
	 String getAdress();
	 
	 /**
	  * This method gets the Description of the property
	  * @return Description of the Property as String
	  */
	 String getDescription();
	 
	 /**
	  * This method gets the Local of the property
	  * @return Locals of the Property as String
	  */
	 String getLocal();
	 
	 /**
	  * This method gets the night price of a property
	  * @return price of the night as a Integer
	  */
	 int getPrice();
	  
	 /**
	  * This method gets the number of the maximum persons that can
	  * stay a night on a Property
	  * @return number of persons that can stay a night as Integer
	  */
	 int getMaxPersons();
	  
	 /**
	  * This method gets the owner of the Property
	  * Note: The owner is readable only for more information read User and UserWritable
	  * @return User (only as getters to the information)
	  */
	 User getOwner();
	  
	 /**
	  * This method gets the number of points evaluated by
	  * non-owners (the evaluation is sum of all points of all non-owner 
	  * users that stayed here a night)
	  * @return points as Integer
	  */
	 int getPoints();
	 
	 /**
	  * This method gets the number of visits/stays
	  * @return number of visits as Integers
	  */
	 int getNumberOfvisits();

	 
	 
	 
}
