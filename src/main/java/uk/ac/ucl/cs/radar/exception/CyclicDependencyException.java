package uk.ac.ucl.cs.radar.exception;

public class CyclicDependencyException extends Exception {
	/**
	   * Constructor
	   * @param message error message
	   */
	  public CyclicDependencyException (String message){
	     super(message);      
	  } // CyclicDependencyException
}
