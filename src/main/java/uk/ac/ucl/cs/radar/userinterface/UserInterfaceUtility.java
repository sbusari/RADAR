package uk.ac.ucl.cs.radar.userinterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import org.apache.commons.lang3.StringUtils;

import com.github.jabbalaci.graphviz.GraphViz;

import uk.ac.ucl.cs.radar.model.AnalysisResult;
import uk.ac.ucl.cs.radar.model.Decision;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.ScatterPlotPanel3D;
import uk.ac.ucl.cs.radar.model.TwoDPanelPlotter;
import uk.ac.ucl.cs.radar.utilities.ConfigSetting;
import uk.ac.ucl.cs.radar.utilities.Helper;

public class UserInterfaceUtility {

	static void initialiseModelSubgraphAndInfoValueObjective(String modelExample,JTextField textSubgraphObjective, JTextField textInformationValueObjective  ){
		String infoValueAndSubgraphObjective ="";
		switch(modelExample){
			case "CBA": infoValueAndSubgraphObjective = ConfigSetting.INFOVALUESUBGRAPHOBJECTIVE_CBA; break;
			case "FDM": infoValueAndSubgraphObjective = ConfigSetting.INFOVALUESUBGRAPHOBJECTIVE_FDM; break;
			case "ECS": infoValueAndSubgraphObjective = ConfigSetting.INFOVALUESUBGRAPHOBJECTIVE_ECM; break;
			case "BSPDM": infoValueAndSubgraphObjective = ConfigSetting.INFOVALUESUBGRAPHOBJECTIVE_BSPDM; break;
			case "SAS" : infoValueAndSubgraphObjective = ConfigSetting.INFOVALUESUBGRAPHOBJECTIVE_SAS; break;
		}
		textSubgraphObjective.setText(infoValueAndSubgraphObjective);
		textInformationValueObjective.setText(infoValueAndSubgraphObjective);
	}
	private static void populateDecisionTable (Model semanticModel, DefaultTableModel decisionTableModel, JTable  decisionsTable){
		if (semanticModel != null){
			decisionTableModel.setNumRows(0);
			List<Decision> decisions = semanticModel.getDecisions();
			setDecisionTableHeader(decisionTableModel,decisionsTable);
			populateDecisionOptionTable(decisionTableModel, decisions);
		}
	}
	private static void populateDecisionOptionTable (DefaultTableModel decisionTableModel,List<Decision> decisions){
		
		Map<String, List<String>> decisionsEntry = new LinkedHashMap<String, List<String>>();
		for (Decision d  : decisions){
			decisionsEntry.put(d.getDecisionLabel(), d.getOptions());
		}
		fillDecisionTable(decisionsEntry,decisionTableModel);		
	}
	private static void fillDecisionTable (Map<String, List<String>> decisionsEntry, DefaultTableModel decisionTableModel){
		if (decisionsEntry != null){
			for (Map.Entry<String, List<String>> entry: decisionsEntry.entrySet()){
				Vector<Object> data = new Vector<Object>();
				data.add(entry.getKey());
				String options ="[";
				for (String option : entry.getValue()){
					options += option + ", ";
				}
				options = options.trim(); // remove last white space.
				options = options.substring(0, options.length()-1);
				options +="]";
				data.add(options);
				decisionTableModel.addRow(data);
			}
		}
	}
	private static void setDecisionTableHeader(DefaultTableModel decisionTableModel, JTable tableDecisionOptions){
		ArrayList<String> decisionOption = new ArrayList<String>();
		decisionOption.add("Decision");
		decisionOption.add("Option");
		String[]header =  decisionOption.toArray(new String[decisionOption.size()]);
		decisionTableModel.setColumnIdentifiers(header);
		tableDecisionOptions.setModel(decisionTableModel);
		
		
	}
	static void loadSolutionTable (DefaultTableModel solutionTableModel, AnalysisResult result){
		List<String> solutions= result.solutionTable();
		for (int i =0; i < solutions.size(); i ++){
			String [] entry = solutions.get(i).split(",");
			Vector<Object> data = new Vector<Object>();
			for (String value: entry){
				data.add(value);
			}
			solutionTableModel.addRow(data);
		}
	}
	static void loadOptimisationTable (DefaultTableModel optimiastionTableModel, AnalysisResult result){
		List<String> optimisationDetail= result.optimisationAnalysisDetails();
		for (int i =0; i < optimisationDetail.size(); i ++){
			String [] entry = optimisationDetail.get(i).split(",");
			Vector<Object> data = new Vector<Object>();
			for (String value: entry){
				data.add(value);
			}
			optimiastionTableModel.addRow(data);
		}
	}
	static void loadInfoValueTable (DefaultTableModel infoValueTableModel, AnalysisResult result){
		List<String> infoValueDetail= result.infoValueDetails();
		for (int i =0; i < infoValueDetail.size(); i ++){
			String [] entry = infoValueDetail.get(i).split(",");
			Vector<Object> data = new Vector<Object>();
			for (String value: entry){
				data.add(value);
			}
			infoValueTableModel.addRow(data);
		}
	}
	static void populateTable (JTable table, String detail, AnalysisResult result){
		DefaultTableModel tableModel =  new DefaultTableModel( );
		if (detail.equals("solution")){
			tableModel.setColumnIdentifiers(result.getSolutionTableColumnIdentifier().split(","));
			loadSolutionTable(tableModel, result);
		}else if (detail.equals("optimisation")){
			tableModel.setColumnIdentifiers(new String[]{"Optimisation Analysis", " "});
			loadOptimisationTable(tableModel, result);
		}else if (detail.equals("infoValue")){
			tableModel.setColumnIdentifiers(new String[]{"Information Value Analysis", " "});
			loadInfoValueTable(tableModel, result);
		}
		table.setModel(tableModel);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	
	}
	static void displayOptimisationAnalysis(Model semanticModel, AnalysisResult result, DefaultTableModel decisionTableModel, JTabbedPane tabbedPane, JCheckBoxMenuItem chckbxmntmOptimisationAnalysis){
		String tabName = ConfigSetting.TABOPTIMISATIONANALYSIS;
		if (result != null){
			JPanel  optimisationAnalysis= new JPanel();
			optimisationAnalysis.setName(tabName);
			optimisationAnalysis.setForeground(Color.LIGHT_GRAY);
			//tabbedPane.addTab(tabName, optimisationAnalysis);

			JScrollPane scrollPaneOptimisationDetails = new JScrollPane();
			scrollPaneOptimisationDetails.setPreferredSize(new Dimension(850, 155));
			optimisationAnalysis.add(scrollPaneOptimisationDetails);
			
			
			JTable optimisationTable = new JTable();
			populateTable(optimisationTable, "optimisation", result);
			scrollPaneOptimisationDetails.setViewportView(optimisationTable);
			
			JScrollPane scrollPaneOptimisationSolution = new JScrollPane();
			scrollPaneOptimisationSolution.setPreferredSize(new Dimension(850, 440));
			optimisationAnalysis.add(scrollPaneOptimisationSolution);
			
			JTable solutionTable = new JTable();
			populateTable(solutionTable, "solution", result);
			scrollPaneOptimisationSolution.setViewportView(solutionTable);
			Component [] allTabbedComponent = tabbedPane.getComponents();
			for (Component comp: allTabbedComponent){
				if (tabName.equals(comp.getName())){
					tabbedPane.remove(comp);
				}
			}
			tabbedPane.addTab(tabName, optimisationAnalysis);
			tabbedPane.setSelectedComponent(optimisationAnalysis);
			chckbxmntmOptimisationAnalysis.setSelected(true);

		}else{
			JOptionPane.showMessageDialog(null, "No analysis result to disolay." , "", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	static void displayInformationValueAnalysis(Model semanticModel, AnalysisResult result, DefaultTableModel decisionTableModel, JTabbedPane tabbedPane, JCheckBoxMenuItem chckbxmntmInformationValueAnalysis){
		String tabName = ConfigSetting.TABINFOVALUEANALYSIS;
		if (result != null){
			
			JPanel  infoValueAnalysis= new JPanel();
			infoValueAnalysis.setName(tabName);
			infoValueAnalysis.setForeground(Color.LIGHT_GRAY);
			//tabbedPane.addTab(tabName, optimisationAnalysis);

			JScrollPane scrollPaneInfoValueAnalysis = new JScrollPane();
			scrollPaneInfoValueAnalysis.setPreferredSize(new Dimension(850, 590));
			infoValueAnalysis.add(scrollPaneInfoValueAnalysis);
			
			
			JTable infoValueAnalysisTable = new JTable();
			populateTable(infoValueAnalysisTable, "infoValue", result);
			scrollPaneInfoValueAnalysis.setViewportView(infoValueAnalysisTable);
			
			
			Component [] allTabbedComponent = tabbedPane.getComponents();
			for (Component comp: allTabbedComponent){
				if (tabName.equals(comp.getName())){
					tabbedPane.remove(comp);
				}
			}
			tabbedPane.addTab(tabName, infoValueAnalysis);
			tabbedPane.setSelectedComponent(infoValueAnalysis);
			chckbxmntmInformationValueAnalysis.setSelected(true);

		}else{
			JOptionPane.showMessageDialog(null, "No analysis result to disolay." , "", JOptionPane.WARNING_MESSAGE);
			chckbxmntmInformationValueAnalysis.setSelected(false);
			return;
		}
	}
	static void displayModeldDecision(Model semanticModel, DefaultTableModel decisionTableModel, JTabbedPane tabbedPane, JCheckBoxMenuItem chckbxmntmModelDecisions ){
		if (semanticModel != null){
			//String modelResultPath = outPutDirectory + semanticModel.getModelName() + "/ICSE/AnalysisResult/" ;
			String title = ConfigSetting.TABDECISIONS;
			
			JPanel  decision= new JPanel();
			decision.setName(title);
			decision.setForeground(Color.LIGHT_GRAY);
			
			JScrollPane scrollPaneDecision = new JScrollPane();
			scrollPaneDecision.setPreferredSize(new Dimension(850, 590));
			decision.add(scrollPaneDecision);
			
			JTable decisionTable = new JTable();
			scrollPaneDecision.setViewportView(decisionTable);
			
			populateDecisionTable(semanticModel, decisionTableModel, decisionTable);
			
			Component [] allTabbedComponent = tabbedPane.getComponents();
			for (Component comp: allTabbedComponent){
				if (title.equals(comp.getName())){
					tabbedPane.remove(comp);
				}
			}
			tabbedPane.addTab(title, decision);
			tabbedPane.setSelectedComponent(decision);
			chckbxmntmModelDecisions.setSelected(true);
		}else{
			JOptionPane.showMessageDialog(null, "No model decision to disolay. Write a new model." , "", JOptionPane.WARNING_MESSAGE);
			chckbxmntmModelDecisions.setSelected(false);
			return;
		}
		
	}
	static void viewPareto (JPanel panel, String title, JTabbedPane tabbedPane){
		Component [] allTabbedComponent = tabbedPane.getComponents();
		for (Component comp: allTabbedComponent){
			if (panel.getName().equals(comp.getName())){
				tabbedPane.remove(comp);
			}
		}
		tabbedPane.addTab(title,panel);
		tabbedPane.setSelectedComponent(panel); // added this part to ensure the pareto front is focussed
		
	}
	static void displayParetoFront(AnalysisResult result, Model semanticModel, String outPutDirectory, JPanel editModel, JTabbedPane tabbedPane, JCheckBoxMenuItem chckbxmntmParetoFront ){
		if (result != null){
			String modelResultPath = outPutDirectory + semanticModel.getModelName() + "/AnalysisResult/" ;
			String imageOutput = modelResultPath + "/";
			String title = ConfigSetting.TABPARETO;
			if (result.getShortListObjectives().get(0).length == 2){
				TwoDPanelPlotter twoDPlot = new TwoDPanelPlotter();
				twoDPlot.setSize(editModel.getSize());
				twoDPlot.setName(title);
				twoDPlot.setLayout(new BorderLayout());
				viewPareto(twoDPlot, title,tabbedPane);
				twoDPlot.plot(semanticModel,imageOutput, result);
			}else if (result.getShortListObjectives().get(0).length == 3){
				ScatterPlotPanel3D sc3D2= new ScatterPlotPanel3D( );
				sc3D2.setSize(editModel.getSize());
				sc3D2.setName(title);
				sc3D2.setLayout(new BorderLayout());
				viewPareto(sc3D2, title,tabbedPane);
				try {
					sc3D2.plot(semanticModel, imageOutput, result);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			chckbxmntmParetoFront.setSelected(true);
		}else{
			JOptionPane.showMessageDialog(null, "No analysis result to disolay." , "", JOptionPane.WARNING_MESSAGE);
			chckbxmntmParetoFront.setSelected(false);
			return;
		}
	}
	static boolean tabbedComponentExits(String componentName, JTabbedPane tabbedPane ){
		boolean exist =false;
		Component [] allTabbedComponent = tabbedPane.getComponents();
		for (Component comp: allTabbedComponent){
			if (componentName.equals(comp.getName())){
				exist = true;
			}
		}
		return exist;
	}
	static void aboutRadar(JTabbedPane tabbedPane){
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
		        					+"<p>Build ID: 20170305-1159 </p>"
		        					+"<p>Version Nunmber: v1.1 </p>"
		        					+"<p>Webpage: https://ucl-badass.github.io/radar/ </p>"
		        					+"<p>Contact email: <a href=\"mailto:{saheed.busari.13, e.letier}@ucl.ac.uk\">{saheed.busari.13, e.letier}@ucl.ac.uk</a></p>"
		        					+"</body>\n";
		        // create a document, set it on the jeditorpane, then add the html
		        Document doc = kit.createDefaultDocument();
		        jEditorPane.setDocument(doc);
		        jEditorPane.setText(htmlString);
		        tabbedPane.addTab("About RADAR", scrollPane);
		        tabbedPane.setSelectedComponent(scrollPane);
	}
	static void viewTutorial ( JTabbedPane tabbedPane){
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
        		+ "<h1>RADAR</h1>"
                + "<p>RADAR is a lightweight modelling language and automated decision analysis tool to support multi-objective decision under uncertainty in requirements engineering and software architecture.</p>"
                +"<h1> How to Use RADAR </h1>"
                +"<p>RADAR has four main panels:"
                +"<ul align =\"justify\">"
                +"<li><strong>Editor</strong> where modellers can write their decision models and also load existing models for editing.</li>"	
                +"<li><strong>Analysis Result</strong> where the results of the optimisation and information value analysis are displayed.</li>"
                +"<li><strong>Analysis Settings</strong> for specifying parameters for model analysis and the path where Graphviz is installed to enable visualisation of the variable AND/OR graph and decision dependency graph. Examples of these parameters needed to be specified for analysis includes: the number of simulation (mandatory), output directory where model analysis results are stored (optional), information value objective for computing the expected value of total and partial perfect information (evtpi and evppi), sub-graph objective for restricting the AND/OR graph to a single specified objective, and some checkboxes used to indicate users' preferences on whether the tool should generate AND/OR dependency graph, decision dependency graph and the Pareto front.  </li>"
                +"<li><strong>Model Decisions</strong> which displays all specified model decisions and their corresponding options, once the model has been parsed successfully.</li>"
                +"</ul>"
                +"<p>To analyse an existing model, the following steps can be followed:</p>"
                +"<ol align =\"justify\">"
                //+"<li>Enable the model board by either clicking <strong>enable model board </strong> under the Radar menu or clicking the <strong>write model</strong> under the Action menu.</li>"
                +"<li>Open the RADAR file (we recommend starting with the first example below i.e. refactoring cost-benefit analysis) by simply clicking on the file menu and then click  <strong>open</strong> to load the existing model on the editor. if successful, you will see the model displayed in the editor. At this point, users can edit the model and save changes by clicking on <strong>save</strong> under the file menu. </li>"
                +"<li>Go to the Action menu and  click  <strong>parse model </strong> to check that the specified model conforms to RADAR syntaxes defined in the paper. If not, an error message is displayed. If successful, you will be prompted to either continue with analysing the model or you could decide to further review the model and later analyse the model by clicking <strong>solve model</strong> under the Action menu. </li>"
                +"<li> If you click continue with model analysis, RADAR analyses the model as described in the icse 2017 paper, and the analysis results, such as the optimisation analysis and information value analysis (if the information value objective is specified), are displayed in a tabular format within another tab. In addition, the model analysis result (in .csv and .out extensions), AND/OR variable dependency graph (DOT format), decision dependency graph (in DOT format) and the Pareto front (in .PNG) are saved in the specified output directory.  </li>"
                +"</ol>"
                +"<p align =\"justify\">To analyse your own model, simply follow the same steps after having edited your model in the tool or using an external text editor.</p>"
                +"</body>\n";
        // create a document, set it on the jeditorpane, then add the html
        Document doc = kit.createDefaultDocument();
        jEditorPane.setDocument(doc);
        jEditorPane.setText(htmlString);
        tabbedPane.addTab("How to Use RADAR", scrollPane);
        tabbedPane.setSelectedComponent(scrollPane);
	}
	static String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	static void createPlot(Model semanticModel, String modelResultPath, AnalysisResult result, JPanel editModel, JCheckBoxMenuItem  chckbxmntmParetoFront){
	    	try {
		    	String imageOutput = modelResultPath + "/";
				String title = ConfigSetting.TABPARETO;
				if (result.getShortListObjectives().get(0).length == 2){
					//TwoDPlotter twoDPlot = new TwoDPlotter();
					TwoDPanelPlotter twoDPlot = new TwoDPanelPlotter();
					twoDPlot.setSize(editModel.getSize());
					twoDPlot.setName(title);
					twoDPlot.setLayout(new BorderLayout());
					//viewPareto(twoDPlot, title);
					twoDPlot.plot(semanticModel,imageOutput, result);
				}else if (result.getShortListObjectives().get(0).length == 3){
					ScatterPlotPanel3D sc3D2= new ScatterPlotPanel3D( );
					sc3D2.setSize(editModel.getSize());
					sc3D2.setName(title);
					sc3D2.setLayout(new BorderLayout());
					//viewPareto(sc3D2, title);
					sc3D2.plot(semanticModel, imageOutput, result);
				}
				chckbxmntmParetoFront.setSelected(true);
	    	} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage() , "", JOptionPane.ERROR_MESSAGE);
				return;
			}
	    }
	static String getModelContent(TableModel model ) throws IOException{
        
		StringBuilder sb = new StringBuilder();
		String columnEntry = "";
		for(int i=0; i < model.getColumnCount(); i++) {
            columnEntry += model.getColumnName(i) + ",";
        }	
		columnEntry += "\n";
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
                columnEntry += model.getValueAt(i,j).toString()+",";
            }
            columnEntry += "\n";
        }
         sb.append(columnEntry);
         return sb.toString();
	}
	static void exportAnalysisResult(JFrame frame, JTabbedPane tabbedPane, JPanel analysisResult, JTable tableOptimisationDetails, JTable tableOptimisationSolutions, JTable tableInfoValueAnalysis ){
		try {
			// only exports results.
			if(tabbedPane.getSelectedComponent() != analysisResult){
				JOptionPane.showMessageDialog(null, "You can only export analysis results." , "", JOptionPane.WARNING_MESSAGE);
				return;
			}
			JFileChooser fileChooser =  new JFileChooser();
			fileChooser.setDialogTitle("Export Result To CSV"); 
			fileChooser.setAcceptAllFileFilterUsed(true);
			int userSelection = fileChooser.showDialog(frame, "Export");
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			    File fileToExport = fileChooser.getSelectedFile();
			    FileOutputStream fis =  new FileOutputStream(fileToExport, true);
		        OutputStreamWriter os = new OutputStreamWriter (fis);
		        BufferedWriter bw = new BufferedWriter(os);
		    	StringBuilder sb = new StringBuilder();
			    if (tableOptimisationDetails != null){
			    	sb.append(getModelContent(tableOptimisationDetails.getModel()));
			    }	
			    if (tableOptimisationSolutions != null){
			    	sb.append(getModelContent(tableOptimisationSolutions.getModel()));
			    }
			    if (tableInfoValueAnalysis != null){
			    	sb.append(getModelContent(tableInfoValueAnalysis.getModel()));
			    }
			    bw.write(sb.toString());
		        bw.close();
			    JOptionPane.showMessageDialog(null, "Analysis Results exported successfully! \nLocation: " + fileToExport , "", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage() , "", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

	}
	static void saveModel(JFrame frame, JTextPane modelTextPane,  String openedFilePath){
		final JFileChooser  fileChooser = new JFileChooser();
		try {
			String savedPath ="";
			if (!StringUtils.isEmpty(modelTextPane.getText())){
				if (openedFilePath != null && !StringUtils.isEmpty(openedFilePath)){
					Helper.writeToAFile(openedFilePath, modelTextPane.getText());
					savedPath = openedFilePath;
					JOptionPane.showMessageDialog(null, "File succesfully saved, check the path below: \n" + savedPath);
	            	return;
				}else{
					fileChooser.setDialogTitle("Save file"); 
					fileChooser.setAcceptAllFileFilterUsed(true);
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int userSelection = fileChooser.showSaveDialog(frame);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
					    File fileToSave = fileChooser.getSelectedFile();
					    String fileExtension = UserInterfaceUtility.getFileExtension(fileToSave);
					    if (!fileExtension.equals("rdr")){
		            	   JOptionPane.showMessageDialog(null, "RADAR files must end with  the (rdr) extensions", "", JOptionPane.ERROR_MESSAGE);
		            	   return;
					    }
					    Helper.writeToAFile(fileToSave.getAbsolutePath(), modelTextPane.getText());
					    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					    savedPath = fileToSave.getAbsolutePath();
					    JOptionPane.showMessageDialog(null, "File succesfully saved. Check the path below: \n" + savedPath);
		            	return;
					}
				}
				
			}else{
				JOptionPane.showMessageDialog(null, "You do not have any model written.");
            	return;
			}
			
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "There was problem saving the file. \nEnsure the path stille exist.");
        	return;
		}
	}
	static boolean infoValueObjectiveExist(Model semanticModel, String infoValueObjective ){
		boolean result = true;
		try{
			if (infoValueObjective != null && !infoValueObjective.isEmpty()){
				InputValidator.objectiveExist(semanticModel, infoValueObjective);
			}
		}catch(Exception e){
			result = false;
			JOptionPane.showMessageDialog(null, "There was a problem during model analysis. \nDetails:" + e.getMessage());

		}
		return result;
	}
	static String validateOutputPath (String outputPath){
		String message ="";
		if (outputPath != null){
			File modelFile = new File (outputPath.trim());
    		if (!modelFile.exists()){
    			message = "The file '"+ outputPath+ "' does not exist."; 
    		}
		}
		return message;
	}
	static void addDirectorySlash(String path, char separator){
		if (path != "" && path.trim().charAt(path.length()-1) != separator){
			path =path.trim() +"/";
			
		}
	}
	static void processJarLocationPath(String  outPutDirectory){
		File f = new File(outPutDirectory);
		char separator = f.separator.toCharArray()[0];
		boolean filebeginningHasSeparator = false;
		if (outPutDirectory != null && outPutDirectory != "" ){
			if (outPutDirectory.toCharArray()[0] == separator){
				filebeginningHasSeparator = true;
			}
			String[] foldersInPath = StringUtils.split(outPutDirectory,f.separator);
			
			// check if the path where the jar is stored contains the jar itself. if yes, remove the jar name to have only folders
			if (foldersInPath != null && foldersInPath[foldersInPath.length-1].contains(".jar")){
				String jarPath = "";
				for (int i =0; i< foldersInPath.length-1; i++){
					jarPath += foldersInPath[i] + f.separator;
				}
				// did this because the splitting of the path removes the separator at the beginning if it exist.
				if(filebeginningHasSeparator){
					jarPath = f.separator + jarPath;
				}
				outPutDirectory = jarPath;
			}
			
		}
		
		addDirectorySlash(outPutDirectory, separator);
	}
}
