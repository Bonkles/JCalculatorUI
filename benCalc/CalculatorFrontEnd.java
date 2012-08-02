package benCalc;

import java.awt.Container;

/* 
 * This Interface is intended to handle the actual placement & layout of the calculator UI controls. 
 * Suppose in the future we wanted to implement several different calculator UIs, 
 * for example; Scientific, Programmer, Simple, Reverse Polish Notation, Graphing, Tip... 
 */
public interface CalculatorFrontEnd {
	/*This method should add the calculator widgets to the main container 
	 * and lay them out. 
	 * @param calcContainer the main container supplied by the calling UI 
	 * program- most likely from a JFrame's getContentPane() call. 
	 */
	public abstract void createCalculatorLayout(Container calcContainer); 

}
