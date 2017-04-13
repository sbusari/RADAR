package uk.ac.ucl.cs.radar.userinterface;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/*
 */
public class TutorialFrame {
	
	  public TutorialFrame()
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
                    +"<h1> How to Use RADAR </h1>"
                    +"<p>RADAR has three main panels:"
                    +"<ul align =\"justify\">"
                    +"<li><strong>Model Board </strong> where modellers can write their own models and also load existing models for editing.</li>"	
                    +"<li><strong>Model Analysis Settings</strong> for specifying parameters for model analysis. Examples of these parameters includes: the number of simulation, output directory where model analysis results are stored, information value objective for computing the expected value of total and partial perfect information (evtpi and evppi), sub-graph objective for restricting the AND/OR graph to a single specified objective, and some checkboxes used to indicate users' preferences on whether the tool should generate AND/OR dependency graph, decision dependency graph and the Pareto front.  </li>"
                    +"<li><strong>Model Decisions</strong> which displays all specified model decisions and their corresponding options, once the model has been parsed successfully.</li>"
                    +"</ul>"
                    +"<p>To analyse an existing model, the following steps can be followed:</p>"
                    +"<ol align =\"justify\">"
                    +"<li>Enable the model board by either clicking <strong>enable model board </strong> under the Radar menu or clicking the <strong>write model</strong> under the Action menu.</li>"
                    +"<li>Open the RADAR file (we recommend starting with the first example below i.e. refactoring cost-benefit analysis) by simply clicking on the file menu and then click  <strong>open</strong> to load the existing model on the model board. if successful, you will see the model displayed in the model board. At this point, users can edit the model and save changes by clicking on <strong>save</strong> under the file menu. </li>"
                    +"<li>Go to the Action menu and  click  <strong>parse model </strong> to check that the specified model conforms to RADAR syntaxes defined in the paper. If not, an error message is displayed. If successful, you will be prompted to either continue with analysing the model or you could decide to further review the model and later analyse the model by clicking <strong>analyse model</strong> under the Action menu. Note that before parsing the model, you will be required by the tool to specify the output directory, which stores model analysis results. </li>"
                    +"<li> If you click continue with model analysis, RADAR analyses the model as described in the paper, and the analysis results, such as the optimisation analysis, Pareto front (if checkbox is enabled) and information value analysis (if the information value objective is specified), are displayed in a tabular format within another window. In addition, the model analysis result (in .csv and .out extensions), AND/OR variable dependency graph (DOT format), decision dependency graph (in DOT format) and the Pareto front (in .PNG) are saved in the specified output directory.  </li>"
                    +"</ol>"
                    +"<p align =\"justify\">To analyse your own model, simply follow the same steps after having edited your model in the tool or using an external text editor.</p>"
                    +"</body>\n";
	        // create a document, set it on the jeditorpane, then add the html
	        Document doc = kit.createDefaultDocument();
	        jEditorPane.setDocument(doc);
	        jEditorPane.setText(htmlString);
	        jEditorPane.setBounds(100, 100, 30, 20);

	        // now add it all to a frame
	        JFrame j = new JFrame("How to Use RADAR");
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
