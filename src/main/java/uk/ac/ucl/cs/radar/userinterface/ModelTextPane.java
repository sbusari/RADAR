package uk.ac.ucl.cs.radar.userinterface;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import javax.swing.text.Utilities;

/**
 * JTextPane implementation that can handle xml text. The IndentKeyListener
 * implements smart indenting.
 *  
 * @author kees
 * @date 27-jan-2006
 *
 */

/**
 * Improved by
 * @author INTEGRALSABIOLA
 *
 */
public class ModelTextPane extends JTextPane {

    private static final long serialVersionUID = 6270183148379328084L;
    private Logger logger = Logger.getLogger(getClass().getName());
    JLabel status;
    public ModelTextPane(JLabel label) {
    	status = label;
        // Set editor kit
        this.setEditorKitForContentType("text/xml", new ModelEditorKit());
        this.setContentType("text/xml");
        //"text/plain" 
        
        addKeyListener(new IndentKeyListener());
        
        //==========imple of redo and undo
        addCaretListener (new CaretListener() {
            // Each time the caret is moved, it will trigger the listener and its method caretUpdate.
            // It will then pass the event to the update method including the source of the event (which is our textarea control)
            public void caretUpdate(CaretEvent e) {
            	JTextPane editArea = (JTextPane)e.getSource();

                // Lets start with some default values for the line and column.
                int linenum = 1;
                int columnnum = 1;

                // We create a try catch to catch any exceptions. We will simply ignore such an error for our demonstration.
                try {
                    // First we find the position of the caret. This is the number of where the caret is in relation to the start of the JTextArea
                    // in the upper left corner. We use this position to find offset values (eg what line we are on for the given position as well as
                    // what position that line starts on.
                    int caretpos = editArea.getCaretPosition();
                    linenum =getLineAtCaret(editArea);
                    if (caretpos == 0){
                   	 	linenum += 1;
                    }

                    // We subtract the offset of where our line starts from the overall caret position.
                    // So lets say that we are on line 5 and that line starts at caret position 100, if our caret position is currently 106
                    // we know that we must be on column 6 of line 5.
                    columnnum = getCaretColumnPosition(editArea);

                    // We have to add one here because line numbers start at 0 for getLineOfOffset and we want it to start at 1 for display.
                    if (caretpos != 0){
                    	 linenum += 1;
                    }
                   
                }
                catch(Exception ex) { }

                // Once we know the position of the line and the column, pass it to a helper function for updating the status bar.
                updateStatus(linenum, columnnum);
            }
        });
        //add(status, BorderLayout.SOUTH);

        // Give the status update value
        updateStatus(1,1);
        
    }
    //https://community.oracle.com/thread/1369109
    public static int getLineAtCaret(JTextComponent component)
    {
         int caretPosition = component.getCaretPosition();
         Element root = component.getDocument().getDefaultRootElement();

         return root.getElementIndex( caretPosition ) ;
    }
  /*  public static int getCaretRowPosition(JTextComponent comp) {
        try {
             int y = comp.modelToView(comp.getCaretPosition()).y;
             int line = y/getRowHeight(comp);
             return ++line;
        } catch (BadLocationException e) {
        }
        return -1;
   }*/

   public static int getCaretColumnPosition(JTextComponent comp) {
        int offset = comp.getCaretPosition();
        int column;
        try {
             column = offset - Utilities.getRowStart(comp, offset) +1;
        } catch (BadLocationException e) {
             column = -1;
        }
        return column;
   }
    private int rowNumber (JTextPane pane, int caretPos){
        int linenum = (caretPos == 0) ? 1 : 0;
        //int linenum =  0;
        for (int offset = caretPos; offset > 0;) {
            try {
				offset = Utilities.getRowStart(pane, offset) - 1;
			} catch (BadLocationException e) {

			}
            linenum++;
        }
        return linenum;
    }
 // This helper function updates the status bar with the line number and column number.
    private void updateStatus(int linenumber, int columnnumber) {
        status.setText("Line: " + linenumber + " Column: " + columnnumber);
    }
	private class IndentKeyListener implements KeyListener {

		private boolean enterFlag;
		private final Character NEW_LINE = '\n';

		public void keyPressed(KeyEvent event) {
			enterFlag = false;
			if ((event.getKeyCode() == KeyEvent.VK_ENTER)
					&& (event.getModifiers() == 0)) {
				if (getSelectionStart() == getSelectionEnd()) {
					enterFlag = true;
					event.consume();
				}
			}
		}

		public void keyReleased(KeyEvent event) {
			if ((event.getKeyCode() == KeyEvent.VK_ENTER)
					&& (event.getModifiers() == 0)) {
				if (enterFlag) {
					event.consume();

					int start, end;
					String text = getText();

					int caretPosition = getCaretPosition();
					try {
						if (text.charAt(caretPosition) == NEW_LINE) {
							caretPosition--;
						}
					} catch (IndexOutOfBoundsException e) {
					}

					start = text.lastIndexOf(NEW_LINE, caretPosition) + 1;
					end = start;
					try {
						if (text.charAt(start) != NEW_LINE) {
							while ((end < text.length())
									&& (Character
											.isWhitespace(text.charAt(end)))
									&& (text.charAt(end) != NEW_LINE)) {
								end++;
							}
							if (end > start) {
								getDocument()
										.insertString(
												getCaretPosition(),
												NEW_LINE
														+ text.substring(start,
																end), null);
							} else {
								getDocument().insertString(getCaretPosition(),
										NEW_LINE.toString(), null);
							}
						} else {
							getDocument().insertString(getCaretPosition(),
									NEW_LINE.toString(), null);
						}
					} catch (IndexOutOfBoundsException e) {
						try {
							getDocument().insertString(getCaretPosition(),
									NEW_LINE.toString(), null);
						} catch (BadLocationException e1) {
							logger.log(Level.WARNING, e1.toString());
						}
					} catch (BadLocationException e) {
						logger.log(Level.WARNING, e.toString());
					}
				}
			}
		}

		public void keyTyped(KeyEvent e) {
		}
	}
    
}