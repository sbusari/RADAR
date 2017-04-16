package uk.ac.ucl.cs.radar.parser.error.handler;
import org.antlr.v4.runtime.*;

import java.util.*;


public class UnderlineModelExceptionListener extends BaseErrorListener {
	String errorMessage = "";
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol,
			int line, int charPositionInLine,
			String msg,
			RecognitionException e)
	{
		String err = underlineError(recognizer,(Token)offendingSymbol,line, charPositionInLine);
		errorMessage += "Syntax error at line "+line+ ", character position "+(charPositionInLine+1)+".\nCheck the section of the model with the input: " + err;
	    System.err.println("Syntax error at line "+line+":"+charPositionInLine+" "+msg + ".\nCheck the input below: " + "\n" + err);
	}
	public String getErrorMsg (){
		return errorMessage;
	}
	protected String underlineError(Recognizer recognizer,
	                              Token offendingToken, int line,
	                              int charPositionInLine) {
		StringBuilder buf = new StringBuilder(); 
		CommonTokenStream tokens =
	        (CommonTokenStream)recognizer.getInputStream();
	    String input = tokens.getTokenSource().getInputStream().toString();
	    String[] lines = input.split("\n");
	    String errorLine = lines[line - 1];
	    //System.err.println(errorLine);
	    buf.append(errorLine + "\n");
	    for (int i=0; i<charPositionInLine; i++) {
	    	//System.err.print(" ");
	    	 buf.append(" ");
	    }
	    int start = offendingToken.getStartIndex();
	    int stop = offendingToken.getStopIndex();
	    if ( start>=0 && stop>=0 ) {
	        for (int i=start; i<=stop; i++) {
	        	//System.err.print("*");
	        	//buf.append("^"); commented bcos it does not add useful information.
	        }
	    }
	    //System.err.println();
	    return buf.toString();
	}
}
