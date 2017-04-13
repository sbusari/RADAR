package uk.ac.ucl.cs.radar.parser.error.handler;
import org.antlr.v4.runtime.*;

import java.util.*;

import org.antlr.v4.runtime.BaseErrorListener;

public class ModelExceptionListener extends BaseErrorListener {
	
	String errorMessage = "";
	@Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e)
    {
        List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
        Collections.reverse(stack);
        charPositionInLine = charPositionInLine +1;
        errorMessage += "Syntax error at line "+line+", character position "+charPositionInLine + ".\n"+msg + " \n";
    }
	public String getErrorMsg (){
		return errorMessage;
	}
}
