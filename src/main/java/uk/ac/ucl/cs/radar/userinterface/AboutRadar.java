package uk.ac.ucl.cs.radar.userinterface;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/*
 * http://alvinalexander.com/blog/post/jfc-swing/how-create-simple-swing-html-viewer-browser-java
 */
public class AboutRadar {
	
	  public AboutRadar()
	  {
	        // create jeditorpane
	        JEditorPane jEditorPane = new JEditorPane();
	        
	        
	        // make it read-only
	        jEditorPane.setEditable(false);
	        
	        // create a scrollpane; modify its attributes as desired
	        JScrollPane scrollPane = new JScrollPane(jEditorPane);
	        
	        // add an html editor kit
	        HTMLEditorKit kit = new HTMLEditorKit();
	        jEditorPane.setEditorKit(kit);
	        
	        // add some styles to the html
	        StyleSheet styleSheet = kit.getStyleSheet();
	        styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
	        styleSheet.addRule("h1 {color: blue;}");
	        styleSheet.addRule("h2 {color: #ff0000;}");
	        styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");

	        // create some simple html as a string
	        
	        String htmlString = "<html>\n"
                    			+ "<body>\n"
                    			+ "<h1>RADAR: Requirements and Architecture Decision Analyser</h1>\n"
                    			+ "<h2><a href=\"http://www0.cs.ucl.ac.uk/staff/S.Busari/\">Saheed Busari</a> and <a href=\"http://letier.cs.ucl.ac.uk/\">Emmanuel Letier</a></h2>"
	        					+"<p align =\"justify\">RADAR is a lightweight modelling language and decision analysis tool to support multi-objective decision under uncertainty in software requirements engineering and architectural design.</p>"
	        					+"<p>Build ID: 20160828-1159 </p>"
	        					+"<p>Version Nunmber: v1.0 </p>"
	        					+"<p>Webpage: http://www0.cs.ucl.ac.uk/staff/S.Busari/RADAR/ </p>"
	        					+"<p>Contact email: <a href=\"mailto:{saheed.busari.13, e.letier}@ucl.ac.uk\">{saheed.busari.13, e.letier}@ucl.ac.uk</a></p>"
	        					+"</body>\n";
	        // create a document, set it on the jeditorpane, then add the html
	        Document doc = kit.createDefaultDocument();
	        jEditorPane.setDocument(doc);
	        jEditorPane.setText(htmlString);
	        
	        
	        jEditorPane.setBounds(100, 100, 30, 20);

	        // now add it all to a frame
	        JFrame j = new JFrame("About RADAR");
	        j.getContentPane().add(scrollPane);

	        // make it easy to close the application
	        //j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        // display the frame
	        j.setSize(600,500);
	        //j.setBounds(100, 100, 600, 500);
	        
	        // pack it, if you prefer
	        j.pack();
	        
	        // center the jframe, then make it visible
	        j.setLocationRelativeTo(null);
	        j.setVisible(true);
	  }
}
