package uk.ac.ucl.cs.radar.model;

/**
 * @author Saheed Busari and Emmanuel Letier
 */
public class Value {

    public static Value VOID = new Value(new Object());
    
    final Object value;
    
    public Value(Object value) {
        this.value = value;
    }
    
    public Distribution getDistribution(){
    	return (Distribution)value;
    }
    
    public Expression getExpression (){
    	Expression result = null;
    	if (value instanceof ArithmeticExpression){
    		result = (ArithmeticExpression)value;
    	}
    	if (value instanceof OR_Refinement){
    		result = (OR_Refinement)value;
    	}
    	if (value instanceof AND_Refinement){
    		result = (AND_Refinement)value;
    	}
    	if (value instanceof Parameter){
    		result = (Parameter)value;
    	}
    	return result;
    }
    
    public ArithmeticExpression getArithmeticExpression(){
    	return (ArithmeticExpression)value;
    }
    
    public OR_Refinement getOR_Refinement(){
    	return (OR_Refinement)value;
    }
    
    public Statistic getStatistic(){
    	return (Statistic)value;
    }
    
    public Boolean convertToBoolean() {
        return (Boolean)value;
    }
    
    public Double convertToDouble() {
    	if (value instanceof Number){
    		String stringValue  =String.valueOf(((Number) value).getValue());
    		return Double.parseDouble(stringValue);
    	} 
    	if (!(value instanceof Double) ){
    		String stringValue = String.valueOf(value);
    		return Double.parseDouble(stringValue);
    	}
        return (Double)value;
    }
    
    public String convertToString() {
        return String.valueOf(value);
    }
    
    public boolean isDouble() {
    	
        return value instanceof Double;
    }
    
    public boolean isString() {
        return value instanceof String;
    }
    
    @Override
    public int hashCode() {
        if(value == null) {
            return 0;
        }

        return this.value.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
        if(value == o) {
            return true;
        }
        if(value == null || o == null || o.getClass() != value.getClass()) {
            return false;
        }
        Value that = (Value)o;
        return this.value.equals(that.value);
    }
    
    @Override
    public String toString() {
    	if (value instanceof Number){
    		return String.valueOf(((Number) value).getValue());
    	}
    	if (value instanceof Identifier){
    		return String.valueOf(((Identifier) value).getID());
    	}
        return String.valueOf(value);
    }
}
