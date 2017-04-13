package uk.ac.ucl.cs.radar.parser.error.handler;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.IntervalSet;

public class ModelParserErrorStrategy extends DefaultErrorStrategy {
    /** Instead of recovering from exception e, rethrow it wrapped
     *  in a generic RuntimeException so it is not caught by the
     *  rule function catches.  Exception e is the "cause" of the
     *  RuntimeException.
     */
	 
    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        throw new RuntimeException(e);
    }

    /** Make sure we don't attempt to recover inline; if the parser
     *  successfully recovers, it won't throw an exception.
     */
    @Override
    public Token recoverInline(Parser recognizer)
        throws RecognitionException
    {
        throw new RuntimeException(new InputMismatchException(recognizer));
    	
    }

    /** Make sure we don't attempt to recover from problems in subrules. */
    @Override
    public void sync(Parser recognizer) { }
    
    @Override
    public void reportNoViableAlternative(Parser parser,
                                          NoViableAltException e)
        throws RecognitionException
    {
        // ANTLR generates Parser subclasses from grammars and
        // Parser extends Recognizer. Parameter parser is a
        // pointer to the parser that detected the error
        String msg = "can't choose between alternatives"; // nonstandard msg
        parser.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }
    @Override
    public void reportInputMismatch(Parser recognizer, InputMismatchException e) throws RecognitionException {
        String msg = "mismatched input " + getTokenErrorDisplay(e.getOffendingToken());
        msg += " expecting one of "+e.getExpectedTokens().toString(recognizer.getTokenNames());
        System.out.println ( msg);
        RecognitionException ex = new RecognitionException(msg, recognizer, recognizer.getInputStream(), recognizer.getContext());
        ex.initCause(e);
        throw ex;
    }

    @Override
    public void reportMissingToken(Parser recognizer) {
        beginErrorCondition(recognizer);
        Token t = recognizer.getCurrentToken();
        IntervalSet expecting = getExpectedTokens(recognizer);
        String msg = "missing "+expecting.toString(recognizer.getTokenNames()) + " at " + getTokenErrorDisplay(t);
        System.out.println ( msg);
        throw new RecognitionException(msg, recognizer, recognizer.getInputStream(), recognizer.getContext());
    }
    
 /*   @Override
    public void reportInputMismatchException(Parser parser, InputMismatchException e)
        throws RecognitionException
    {
        // ANTLR generates Parser subclasses from grammars and
        // Parser extends Recognizer. Parameter parser is a
        // pointer to the parser that detected the error
        String msg = "an input is mismatched"; // nonstandard msg
        parser.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }*/
    
    
}
