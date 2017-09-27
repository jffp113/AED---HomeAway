package homeAway;

/**
 * @author Jorge Pereira (49771) jff.pereira@campus.fct.unl.pt 
 * @author Tiago Fornelos (49780) t.fornelos@campus.fct.unl.pt
 */
interface PropertyWritable extends Property{
	
	void evaluateStay(int points,User user);
	void stay(User user);
}
