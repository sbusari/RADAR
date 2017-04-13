package uk.ac.ucl.cs.radar.model;

import java.util.ArrayList;
import java.util.List;

public class Decision {
	
	private String decisionLabel;
	
	private List<String> options;
	
	public Decision(){
		options = new ArrayList<String>();
	}
	
	public String getDecisionLabel (){
		return decisionLabel;
	}
	/**
	 * Sets the name of a decision.
	 * @param decisonLabel name of decision.
	 */
	public void setDecisionLabel (String decisonLabel){
		this.decisionLabel =decisonLabel;
	}
	/**
	 * Sets the option names for a decision instance.
	 * @param options the list of option names for a decision instance.
	 */
	public void setOptions (List<String>  options){
		this.options =options;
	}
	/**
	 * @return the list of option names for a decision instance.
	 */
	public List<String> getOptions (){
		return options;
	}
	/**
	 * adds an option name to a decision instance.
	 * @param option an option name of a decision instance.
	 */
	public void addOption (String  option){
			this.options.add(option);
	}
	@Override
    public boolean equals(Object o){
        if (o.getClass() !=  getClass()) return false;
        Decision d = (Decision) o;
        return decisionLabel.equals(d.decisionLabel);
    }
	/**
	 * @return the decision name.
	 */
    @Override
    public String toString(){
        return decisionLabel;
    }
    /**
     * @param d decision to compare.
	 * @return true if two decision instances are equal
	 */
	boolean equals(Decision d){
		return decisionLabel.equals(d.decisionLabel);
	}

	public int hashCode(){
		return decisionLabel.hashCode();
	}
}
