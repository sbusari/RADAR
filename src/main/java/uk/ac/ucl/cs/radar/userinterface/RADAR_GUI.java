package uk.ac.ucl.cs.radar.userinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.JTable;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.JTextArea;

import uk.ac.ucl.cs.radar.model.AnalysisResult;
import uk.ac.ucl.cs.radar.model.Decision;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.ModelSolver;
import uk.ac.ucl.cs.radar.model.Parser;
import uk.ac.ucl.cs.radar.model.ScatterPlot3D;
import uk.ac.ucl.cs.radar.model.TwoDPlotter;
import uk.ac.ucl.cs.radar.utilities.Helper;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import org.apache.commons.lang3.StringUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class RADAR_GUI {

	private JFrame frame;
	private Model semanticModel;
	private AnalysisResult result;
	private boolean modelParsed;
	private boolean modelSolved;
	private int nbr_Simulation;
	private String infoValueObjective;
	private String subGraphObjective;
	private boolean isBoardEnabled;
	private boolean parsed;
	private String openedFilePath;
	private String solvedModel;
	private DefaultTableModel decisionTableModel;
	private ModelResultFrame modelResultFrame;
	private TutorialFrame tutorialFrame;
	private JPanel modelBoard;
	private JMenuItem itemWriteModel;
	private JMenuItem itemParseModel;
	private JMenuItem itemSolveModel;
	private JMenuItem itemAbout;
	private JMenuItem itemExit;
	private JMenuItem itemOpen;
	private JMenuItem itemSave;
	private JMenuItem itemPrint;
	private JTextArea textModelArea;
	private JScrollPane textAreaScroll;
	private JPanel analysisPanel;
	private JPanel decisionPanel;
	private JScrollPane decisionTableScrollPane;
	private JTextField textNbrSimulation;
	private JTextField textInfoValueObj;
	private JTextField textSubgraphObj;
	private JTextField textOutputDirectory;
	private JButton btnFindDirectory;
	private JCheckBox chckbxVariable;
	private JCheckBox chckbxDecision;
	private JCheckBox chckbxPareto;
	private JTable decisionsTable;
	private JMenuItem itemEnableBoard;
	private JSeparator separator_8;
	private JMenuItem itemTutorial;
	private JSeparator separator_2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RADAR_GUI window = new RADAR_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RADAR_GUI() {
		initialize();
		createEvents();
	}
	
	private void createEvents (){
		visualiseModelBoard();
		openExistingModel ();
		saveFile();
		parseModel();
		findOutPutDirectory();
		solveModel();
		enableDiableBoard();
		exitRadar();
		printModel();
		viewTutorial();
		aboutRadar();
		
	}
	private void  enableDiableBoard (){
		itemEnableBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBoardEnabled != true){
					activateModelWriting();
					itemEnableBoard.setText("Disable Model Board");
					
				}else{
					deactivateModelWriting();
					itemEnableBoard.setText("Enable Model Board");
				}
				openedFilePath = "";
			}
		});
	}
	private void viewTutorial (){
		itemTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TutorialFrame();
			}
		});
	}
	private void aboutRadar(){
		itemAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AboutRadar();
			}
		});
	}
	private void exitRadar(){
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selection = JOptionPane.showConfirmDialog( null , "Are you sure you want to close this window?" , "Confirmation "
	                    , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
				if (selection == JOptionPane.OK_OPTION)
                {
					frame.dispose();
                }
				
			}
		});
	}
	private String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	void activateModelWriting(){
		textModelArea.setEnabled(true);
		textAreaScroll.setEnabled(true);
		analysisPanel.setEnabled(true);
		decisionPanel.setEnabled(true);
		decisionTableScrollPane.setEnabled(true);
		textNbrSimulation.setEnabled(true);
		textInfoValueObj.setEnabled(true);
		textSubgraphObj.setEnabled(true);
		textOutputDirectory.setEnabled(true);
		btnFindDirectory.setEnabled(true);
		chckbxVariable.setEnabled(true);
		chckbxDecision.setEnabled(true);
		chckbxPareto.setEnabled(true);
		decisionsTable.setEnabled(true);
		isBoardEnabled = true;
	}
	void deactivateModelWriting(){
		textModelArea.setEnabled(false);
		textAreaScroll.setEnabled(false);
		analysisPanel.setEnabled(false);
		decisionPanel.setEnabled(false);
		decisionTableScrollPane.setEnabled(false);
		textNbrSimulation.setEnabled(false);
		textInfoValueObj.setEnabled(false);
		textSubgraphObj.setEnabled(false);
		textOutputDirectory.setEnabled(false);
		btnFindDirectory.setEnabled(false);
		chckbxVariable.setEnabled(false);
		chckbxDecision.setEnabled(false);
		chckbxPareto.setEnabled(false);
		decisionsTable.setEnabled(false);
		isBoardEnabled = false;
	}
	private void printModel(){
		itemPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (!StringUtils.isEmpty(textModelArea.getText())){
						boolean complete = textModelArea.print();
			            if(complete){
			                JOptionPane.showMessageDialog(null,  "Print Completed!", "Model",JOptionPane.INFORMATION_MESSAGE);
			            }else{
			                JOptionPane.showMessageDialog(null, "Printing", "Printer", JOptionPane.ERROR_MESSAGE);
			            }
					}
		            
		        }
				catch(PrinterException ex){
					JOptionPane.showMessageDialog(null, ex);
					return;
		        }
		        
			}
		});
	}
	private void saveFile (){
		final JFileChooser  fileChooser = new JFileChooser();
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String savedPath ="";
					if (!StringUtils.isEmpty(textModelArea.getText())){
						if (openedFilePath != null && !StringUtils.isEmpty(openedFilePath)){
							Helper.writeToAFile(openedFilePath, textModelArea.getText());
							savedPath = openedFilePath;
							JOptionPane.showMessageDialog(null, "File succesfully saved in the path: " + savedPath);
			            	return;
						}else{
							fileChooser.setDialogTitle("Save file"); 
							fileChooser.setAcceptAllFileFilterUsed(true);
							int userSelection = fileChooser.showSaveDialog(frame);
							if (userSelection == JFileChooser.APPROVE_OPTION) {
							    File fileToSave = fileChooser.getSelectedFile();
							    String fileExtension = getFileExtension(fileToSave);
							    if (!fileExtension.equals("rdr")){
				            	   JOptionPane.showMessageDialog(null, "Radar files must end with  the (rdr) extensions");
				            	   return;
							    }
							    Helper.writeToAFile(fileToSave.getAbsolutePath(), textModelArea.getText());
							    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
							    savedPath = fileToSave.getAbsolutePath();
							    JOptionPane.showMessageDialog(null, "File succesfully saved in the path: " + savedPath);
				            	return;
							}
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "You cannot save an empty board.");
		            	return;
					}
					
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "There was problem saving the file. Ensure the path stille exist.");
	            	return;
				}
				
			}
		});
	}
	void openExistingModel (){
		final JFileChooser  fileDialog = new JFileChooser();
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBoardEnabled == false){
					JOptionPane.showMessageDialog(null, "Model board must be enabled before any action.");
	            	   return;
				}
				
				int returnVal = fileDialog.showOpenDialog(frame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               java.io.File file = fileDialog.getSelectedFile();
	               String fileExtension = getFileExtension(file);
	               if (!fileExtension.equals("rdr")){
	            	   JOptionPane.showMessageDialog(null, "Radar files must end with  the (rdr) extensions");
	            	   return;
	               }
	               openedFilePath = file.getPath();
	               loadExistingModel(file.getPath());
	               if (modelBoard.isVisible() == false){
	            	   modelBoard.setEnabled(true);
	            	   decisionPanel.setEnabled(true);
	   					analysisPanel.setEnabled(true);
	               }
	               
	            }
			}
		});
	}
	void visualiseModelBoard(){
		
		itemWriteModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textModelArea.getText().isEmpty()){
					int selection = JOptionPane.showConfirmDialog( null , "The content in the model board will be removed. Do you want to continue?" , "Confirmation "
		                    , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
					if (selection == JOptionPane.OK_OPTION)
	                {
						textModelArea.setText("");
						openedFilePath = "";
	                }
				}
				modelBoard.setEnabled(true);
				decisionPanel.setEnabled(true);
				analysisPanel.setEnabled(true);
				activateModelWriting();
				isBoardEnabled = true;
				itemEnableBoard.setText("Disable Model Board");
				
			}
		});
	}
	void parseModel (){
		itemParseModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBoardEnabled == false){
					JOptionPane.showMessageDialog(null, "Model board must be enabled before any action.");
					return;
				}
				if (textModelArea.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "You need to write a new decision model or select from existing decision models.");
					return;
				}
				String analysisMsg = allAnalysisSettingValid();
				if (!analysisMsg.isEmpty()){
					JOptionPane.showMessageDialog(null, analysisMsg);
					return;
				}
				if (!doesOutputDirectoryExist()){
					JOptionPane.showMessageDialog(null, "Specified output directory does not exist.");
					return;
				}
				
				parse();
				if (parsed ==true){
					populateDecisionTable();
					int selection = JOptionPane.showConfirmDialog( null , "Model has been parsed successfully. Do you want to continue with analysis?" , "Confirmation "
		                    , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
					if (selection == JOptionPane.OK_OPTION)
	                {
						solve();
						if (modelSolved){
							loadResultInFrame();
							solvedModel = textModelArea.getText();
						}
						
						
	                }
					modelParsed= true;
				}
				
			}
		});

	}
	private void solveModel (){
		itemSolveModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isBoardEnabled == false){
					JOptionPane.showMessageDialog(null, "Model board must be enabled before any action.");
	            	   return;
				}
				if (textModelArea.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "You need to write a new decision model or select from existing decision models.");
					return;
				}
				// also check that there is no changes to the model, otherwise, we parse again.
				if (solvedModel != null){
					if ( !solvedModel.equals(textModelArea.getText())){
						parse();
					}
				}else{
					parse();
				}
				if (semanticModel != null && modelParsed == true) {
					solve();
				}else{
					String analysisMsg = allAnalysisSettingValid();
					if (!analysisMsg.isEmpty()){
						JOptionPane.showMessageDialog(null, analysisMsg);
						return;
					}
					if (!doesOutputDirectoryExist()){
						JOptionPane.showMessageDialog(null, "Specified output director does not exist.");
						return;
					}
					parseModel();
					if (modelParsed == true){
						solve();
					}
					
				}
				if (modelSolved){
					populateDecisionTable();
					loadResultInFrame();
				}
			}
		});
	}
	private String allAnalysisSettingValid (){
		String validationMessage = inputValidation();
		return validationMessage;

	}
	private boolean doesOutputDirectoryExist(){
		boolean result =true;
		try{
			InputValidator.validateOutputPath(textOutputDirectory.getText());
		}catch (Exception e){
			result = false;
			
		}
		return result;
	}
	private String inputValidation (){
		String message = "";
		message += InputValidator.verifyEmptyField (textNbrSimulation, "number of simulation", "Integer");
		message += InputValidator.verifyEmptyField (textOutputDirectory, "output directory", "String");
		message += InputValidator.verifyFieldDataType (textNbrSimulation.getText(), "Nbr. Simulation", "Integer");
		message += InputValidator.verifyFieldNonNegativeValue(textNbrSimulation.getText(), "Nbr. Simulation", "Integer");
		//message += validateOutputDirectoryPath();
		return message;
	}
	private String validateOutputDirectoryPath (){
		String message ="";
		if(!textOutputDirectory.getText().isEmpty()){
			if (textOutputDirectory.getText().equals("--Select--")){
				message += "You must specify the directory to store results.";
				return message;
			}else{
				Pattern pattern = Pattern.compile("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_-]+)+\\\\?");
				Matcher matcher = pattern.matcher(textOutputDirectory.getText());
				if (!matcher.matches()){
					message += "The path specified for the output directory is not valid.";
				}
			}
		}else{
			message+= "You must specify the directory to store results.";
		}
		return message;
	}
	private void populateDecisionTable (){
		if (semanticModel != null){
			decisionTableModel.setNumRows(0);
			List<Decision> decisions = semanticModel.getDecisions();
			setDecisionTableHeader(decisionTableModel,decisionsTable);
			populateDecisionOptionTable(decisionTableModel, decisions);
		}
	}
	private void setDecisionTableHeader(DefaultTableModel decisionTableModel, JTable tableDecisionOptions){
		ArrayList<String> decisionOption = new ArrayList<String>();
		decisionOption.add("Decision");
		decisionOption.add("Option");
		String[]header =  decisionOption.toArray(new String[decisionOption.size()]);
		decisionTableModel.setColumnIdentifiers(header);
		tableDecisionOptions.setModel(decisionTableModel);
	}
	private void fillDecisionTable (Map<String, List<String>> decisionsEntry){
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
	private void populateDecisionOptionTable (DefaultTableModel decisionTableModel,List<Decision> decisions){
		
		Map<String, List<String>> decisionsEntry = new LinkedHashMap<String, List<String>>();
		for (Decision d  : decisions){
			decisionsEntry.put(d.getDecisionLabel(), d.getOptions());
		}
		fillDecisionTable(decisionsEntry);		
	}
	void parse (){
		try {
			semanticModel = loadModel();
			if (semanticModel != null){
				parsed =true;
				modelParsed = true;
			}
		} catch (Exception e) {
			parsed =false;
			String err = e.getMessage();
			JOptionPane.showMessageDialog(null, err);
		}
	}
	
	void loadSolutionTable (DefaultTableModel solutionTableModel){
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
	void loadOptimisationTable (DefaultTableModel optimiastionTableModel){
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
	void loadInfoValueTable (DefaultTableModel infoValueTableModel){
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
	void populateTable (JTable table, String detail){
		DefaultTableModel tableModel =  new DefaultTableModel( );
		if (detail.equals("solution")){
			tableModel.setColumnIdentifiers(result.getSolutionTableColumnIdentifier().split(","));
			loadSolutionTable(tableModel);
		}else if (detail.equals("optimisation")){
			tableModel.setColumnIdentifiers(new String[]{"Optimisation Analysis", " "});
			loadOptimisationTable(tableModel);
		}else if (detail.equals("infoValue")){
			tableModel.setColumnIdentifiers(new String[]{"Information Value Analysis", " "});
			loadInfoValueTable(tableModel);
		}
		table.setModel(tableModel);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	
	}
	void loadResultInFrame (){
		if (result == null  || result.getShortListObjectives().size() <= 0){
			JOptionPane.showMessageDialog(null, "No results found!");
			return;
		}
		if (semanticModel == null){
			JOptionPane.showMessageDialog(null, "Model needs to be parsed and analysed for results to show!");
			return;
		}
		modelResultFrame  = new ModelResultFrame();
		
		JTable solutionTable = modelResultFrame.getSolutionTable();
		populateTable(solutionTable, "solution");
		JTable optimisationTable = modelResultFrame.getOptimisationAnalysisTable();
		populateTable(optimisationTable, "optimisation");
		JTable infoValueTable = modelResultFrame.getInfoValueTable();
		populateTable(infoValueTable, "infoValue");
		
		modelResultFrame.setVisible(true);
		modelResultFrame.setTitle("Analysis Results" );
		modelResultFrame.pack();
		modelResultFrame.setLocationRelativeTo(null);
		modelResultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	void solve (){
		
		try {
			// analyse model
			result = ModelSolver.solve(semanticModel);
			String analysisResult = result.analysisToString();
			String analysisResultToCSV = result.analysisResultToCSV();
			String modelResultPath = textOutputDirectory.getText() +"/" + semanticModel.getModelName() + "/ICSE/AnalysisResult/" ;
			
			Helper.printResults (modelResultPath , analysisResult, semanticModel.getModelName() +".out", false);
			Helper.printResults (modelResultPath , analysisResultToCSV, semanticModel.getModelName() +".csv", false);
			
			// generate graphs
			if (chckbxVariable.isSelected()){
				String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, result.getSubGraphObjective());
				Helper.printResults (modelResultPath + "graph/", variableGraph, semanticModel.getModelName() +"vgraph.dot", false);
				
			}
			if (chckbxDecision.isSelected()){
				String decisionGraph = semanticModel.generateDecisionDiagram(result.getAllSolutions());
				Helper.printResults (modelResultPath + "graph/", decisionGraph, semanticModel.getModelName() + "dgraph.dot", false);
				
			}
			if (chckbxPareto.isSelected()){
				String imageOutput = modelResultPath + "/";
				if (result.getShortListObjectives().get(0).length == 2){
					TwoDPlotter twoDPlot = new TwoDPlotter();
					twoDPlot.plot(semanticModel,imageOutput, result);
				}else if (result.getShortListObjectives().get(0).length == 3){
					ScatterPlot3D sc3D2= new ScatterPlot3D( );
					sc3D2.plot(semanticModel, imageOutput, result);
				}
			}
			System.out.println("Finished!");
			modelSolved =true;
			solvedModel = textModelArea.getText();
		
		} 
		catch (IOException e) {
			modelSolved = false;
			JOptionPane.showMessageDialog(null, "There was a problem writing the results to directory. Check that the path exist: ");
			return;
		}
		catch (NullPointerException e){
			modelSolved = false;
			JOptionPane.showMessageDialog(null,"There was a problem during model analysis. Details:" + e.getMessage());
			return;
		}
		catch ( RuntimeException e){
			modelSolved = false;
			JOptionPane.showMessageDialog(null, "There was a problem during model analysis. Details:" + e.getMessage());
			return;
		}
		
	}
	private boolean infoValueObjectiveExist(){
		boolean result = true;
		try{
			if (infoValueObjective != null){
				InputValidator.objectiveExist(semanticModel, infoValueObjective);
			}
		}catch(Exception e){
			result = false;
		}
		return result;
	}
	private boolean subGraphObjectiveExist(){
		boolean result = true;
		try{
			if(subGraphObjective != null){
				InputValidator.objectiveExist(semanticModel, subGraphObjective);
			}
		}catch (Exception e){
			result = false;
		}
		return result;
	}
	private Model loadModel () throws Exception{
		Model semanticModel =null;
		try {
			nbr_Simulation = Integer.parseInt(textNbrSimulation.getText());
			subGraphObjective = textSubgraphObj.getText();
			infoValueObjective = textInfoValueObj.getText();
			semanticModel = new Parser().parseUIModel(textModelArea.getText(), nbr_Simulation, infoValueObjective,subGraphObjective);
		}catch (RuntimeException re){
			parsed = false;
			String err = re.getMessage();
			JOptionPane.showMessageDialog(null, err);
		}
		return semanticModel;
	}
	private boolean loadExistingModel ( String modelPath){
		boolean done = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(modelPath)))) {
			textModelArea.read(reader, "File");
        	done = true;
        } catch (IOException exp) {
        	String err = exp.getMessage();
			JOptionPane.showMessageDialog(null, err);
        }
		return done;
	}

	private void findOutPutDirectory(){
		final JFileChooser  fileDialog = new JFileChooser();
		btnFindDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileDialog.setCurrentDirectory(new java.io.File("."));
				fileDialog.setDialogTitle("Output Directory");
				fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileDialog.setAcceptAllFileFilterUsed(false);
				int returnVal = fileDialog.showOpenDialog(frame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               textOutputDirectory.setText(fileDialog.getSelectedFile().getAbsolutePath().toString());
	            }
	            else{
	            	textOutputDirectory.setText("--Select--" );           
	            } 
			}
		});
	}
	/*private void populateDecisionTable(){
		decisionTableModel = new DefaultTableModel();
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("RADAR- Requirements engineering And Architecture Decisions Analyser");
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu radarMenu = new JMenu("Radar");
		menuBar.add(radarMenu);
		
		itemEnableBoard = new JMenuItem("Enable Model Board");
		
		radarMenu.add(itemEnableBoard);
		
		separator_8 = new JSeparator();
		radarMenu.add(separator_8);
		
		itemAbout = new JMenuItem("About Radar");
	
		radarMenu.add(itemAbout);
		
		separator_2 = new JSeparator();
		radarMenu.add(separator_2);
		
		itemTutorial = new JMenuItem("How to Use");
	
		radarMenu.add(itemTutorial);
		
		JSeparator separator_3 = new JSeparator();
		radarMenu.add(separator_3);
		
		itemExit = new JMenuItem("Exit");
		
		radarMenu.add(itemExit);
		
		JMenu mnNewMenu_2 = new JMenu("File");
		menuBar.add(mnNewMenu_2);
		
		itemOpen = new JMenuItem("Open");
		
		mnNewMenu_2.add(itemOpen);
		
		JSeparator separator_4 = new JSeparator();
		mnNewMenu_2.add(separator_4);
		
		itemSave = new JMenuItem("Save");
		
		mnNewMenu_2.add(itemSave);
		
		JSeparator separator_6 = new JSeparator();
		mnNewMenu_2.add(separator_6);
		
		itemPrint = new JMenuItem("Print");
		
		mnNewMenu_2.add(itemPrint);
		
		JMenu fileMenu = new JMenu("Action");
		menuBar.add(fileMenu);
		
		itemWriteModel = new JMenuItem("Write Model");
		
		
		
		fileMenu.add(itemWriteModel);
		
		JSeparator separator = new JSeparator();
		fileMenu.add(separator);
		
		itemParseModel = new JMenuItem("Parse Model");
		
		
		fileMenu.add(itemParseModel);
		
		JSeparator separator_1 = new JSeparator();
		fileMenu.add(separator_1);
		
		itemSolveModel = new JMenuItem("Solve Model");
		
		fileMenu.add(itemSolveModel);
		
		modelBoard = new JPanel();
		modelBoard.setBorder(new TitledBorder(null, "Model Board", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		analysisPanel = new JPanel();
		analysisPanel.setBorder(new TitledBorder(null, "Analysis Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		decisionPanel = new JPanel();
		decisionPanel.setBorder(new TitledBorder(null, "Decisions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//populateDecisionTable();
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(modelBoard, GroupLayout.PREFERRED_SIZE, 715, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(analysisPanel, GroupLayout.PREFERRED_SIZE, 450, GroupLayout.PREFERRED_SIZE)
						.addComponent(decisionPanel, GroupLayout.PREFERRED_SIZE, 446, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(analysisPanel, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(decisionPanel, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(modelBoard, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		decisionTableScrollPane = new JScrollPane();
		decisionTableScrollPane.setEnabled(false);
		decisionTableScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_decisionPanel = new GroupLayout(decisionPanel);
		gl_decisionPanel.setHorizontalGroup(
			gl_decisionPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_decisionPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(decisionTableScrollPane, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_decisionPanel.setVerticalGroup(
			gl_decisionPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_decisionPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(decisionTableScrollPane, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		decisionTableModel = new DefaultTableModel();
		decisionsTable = new JTable (){
			private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		decisionsTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		decisionTableScrollPane.setViewportView(decisionsTable);
		
		
		decisionPanel.setLayout(gl_decisionPanel);
		setDecisionTableHeader(decisionTableModel,decisionsTable);
		
		JLabel lblNbrSimulation = new JLabel("Nbr. Simulation:");
		
		textNbrSimulation = new JTextField();
		textNbrSimulation.setEnabled(false);
		textNbrSimulation.setText("10000");
		textNbrSimulation.setColumns(10);
		
		JLabel lblInfoValueObj = new JLabel("Info. Value Objective:");
		
		textInfoValueObj = new JTextField();
		textInfoValueObj.setEnabled(false);
		textInfoValueObj.setColumns(10);
		
		JLabel lblSUbGraphObj = new JLabel("SubGraph Objective:");
		
		textSubgraphObj = new JTextField();
		textSubgraphObj.setEnabled(false);
		textSubgraphObj.setColumns(10);
		
		JLabel lblOutputDir = new JLabel("Output Directory:");
		
		textOutputDirectory = new JTextField();
		textOutputDirectory.setEnabled(false);
		textOutputDirectory.setText("--Select--");
		textOutputDirectory.setColumns(10);
		
		JLabel lblDOTGraph = new JLabel("DOT Graph:");
		
		chckbxVariable = new JCheckBox("Variable");
		chckbxVariable.setEnabled(false);
		chckbxVariable.setSelected(true);
		
		chckbxDecision = new JCheckBox("Decision");
		chckbxDecision.setEnabled(false);
		chckbxDecision.setSelected(true);
		
		JLabel lblPlot = new JLabel("Plot:");
		
		chckbxPareto = new JCheckBox("Pareto Optimal");
		chckbxPareto.setEnabled(false);
		
		btnFindDirectory = new JButton("Find");
		btnFindDirectory.setEnabled(false);
		
		GroupLayout gl_analysisPanel = new GroupLayout(analysisPanel);
		gl_analysisPanel.setHorizontalGroup(
			gl_analysisPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_analysisPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblNbrSimulation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(32))
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblSUbGraphObj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblOutputDir, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(24))
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblDOTGraph, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(61))
						.addComponent(lblInfoValueObj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addComponent(lblPlot, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addGap(51)))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addGroup(gl_analysisPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(chckbxPareto)
								.addGroup(gl_analysisPanel.createSequentialGroup()
									.addComponent(chckbxVariable, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(chckbxDecision))
								.addComponent(textOutputDirectory))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnFindDirectory)
							.addContainerGap())
						.addGroup(gl_analysisPanel.createSequentialGroup()
							.addGroup(gl_analysisPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(textNbrSimulation, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(textSubgraphObj, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
								.addComponent(textInfoValueObj, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
							.addGap(87))))
		);
		gl_analysisPanel.setVerticalGroup(
			gl_analysisPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_analysisPanel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNbrSimulation)
						.addComponent(textNbrSimulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInfoValueObj)
						.addComponent(textInfoValueObj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSUbGraphObj)
						.addComponent(textSubgraphObj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOutputDir)
						.addComponent(textOutputDirectory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFindDirectory))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxVariable)
						.addComponent(chckbxDecision)
						.addComponent(lblDOTGraph))
					.addGap(18)
					.addGroup(gl_analysisPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxPareto)
						.addComponent(lblPlot))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		analysisPanel.setLayout(gl_analysisPanel);
		
		textAreaScroll = new JScrollPane();
		textAreaScroll.setEnabled(false);
		GroupLayout gl_modelBoard = new GroupLayout(modelBoard);
		gl_modelBoard.setHorizontalGroup(
			gl_modelBoard.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_modelBoard.createSequentialGroup()
					.addContainerGap()
					.addComponent(textAreaScroll, GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_modelBoard.setVerticalGroup(
			gl_modelBoard.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_modelBoard.createSequentialGroup()
					.addComponent(textAreaScroll, GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		textModelArea = new JTextArea();
		textModelArea.setEnabled(false);
		textAreaScroll.setViewportView(textModelArea);
		modelBoard.setLayout(gl_modelBoard);
		frame.getContentPane().setLayout(groupLayout);
	}
}
