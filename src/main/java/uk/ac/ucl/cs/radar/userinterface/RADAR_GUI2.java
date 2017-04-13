package uk.ac.ucl.cs.radar.userinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JComponent;
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
import javax.swing.JTextPane;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.JTextArea;

import uk.ac.ucl.cs.radar.model.AnalysisResult;
import uk.ac.ucl.cs.radar.model.Decision;
import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.ModelSolver;
import uk.ac.ucl.cs.radar.model.Parser;
import uk.ac.ucl.cs.radar.model.ScatterPlot3D;
import uk.ac.ucl.cs.radar.model.ScatterPlotPanel3D;
import uk.ac.ucl.cs.radar.model.TwoDPanelPlotter;
import uk.ac.ucl.cs.radar.model.TwoDPlotter;
import uk.ac.ucl.cs.radar.userinterface.ProgressMonitorDemo.Task;
import uk.ac.ucl.cs.radar.utilities.ConfigSetting;
import uk.ac.ucl.cs.radar.utilities.Helper;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import java.awt.image.BufferedImage;

import javax.swing.UIManager;

import java.awt.Dimension;

import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTabbedPane;

import java.awt.Color;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.github.jabbalaci.graphviz.GraphViz;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.FlowLayout;

import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;


public class RADAR_GUI2 implements PropertyChangeListener {

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
	private JMenuItem itemParseModel;
	private JMenuItem itemSolveModel;
	private JMenuItem itemOpen;
	private JMenuItem itemSave;
	private JMenuItem itemPrint;
	private JMenuItem newMenu;
	private JSeparator separator_5;
	private JMenuItem mntmNewMenuItem;
	private JSeparator separator_9;
	private JMenuItem itemSaveAs;
	private JPanel editModel;
	private JPanel analysisResult; 
	//private JTabbedPane tabbedPane;
	private ClosableTabbedPane tabbedPane;
	private JPanel console;
	private JScrollPane scrollPaneEditModel;
	private JScrollPane scrollPaneInfoValueAnalysis;
	private ModelTextPane modelTextPane;
	private Utility radarUtility;
	private JTable tableOptimisationDetails;
	private JTable tableOptimisationSolutions;
	private JTable tableInfoValueAnalysis;
	private JScrollPane scrollPaneOptimisationDetails;
	private JScrollPane scrollPaneOptimisationSolutions;
	private JPanel variableGraphPanel;
	private JPanel decisionDependencyGraphPanel;
	private String outPutDirectory;
	private JButton btnNewFile;
	private JButton btnOpenFile;
	private JButton btnSaveFile;
	private JButton btnExportFile;
	private UndoManager undoManager;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnPaste;
	private JButton btnCopy;
	private JButton btnCut;
	private JMenuItem mntmUndo;
	private JMenuItem mntmRedo;
	private StringSelection ss;
	private JButton btnParse;
	private JButton btnSolve;
	private JComboBox comboBox;
	private JButton btnOptimisationAnalysis;
	private JButton btnInfoValueAnalysis;
	private JButton btnParetoFront;
	private ProgressMonitor progressMonitor;
	private Task task;
	private JScrollPane scrollPaneConsole;
	private JTextArea consoleTextArea;
	private String modelExample ="";
	String executable = "/usr/local/bin/dot";
	int decimalPrecision = 2;
	private ImageLabel decisionDependencyGraphImageLabel;
	private ImageLabel variableGraphImageLabel;
	private ImageIcon decisionDependencyGraphIcon ;
	private ImageIcon variableGraphIcon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
		        	RADAR_GUI2 window = new RADAR_GUI2();
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
	public RADAR_GUI2() {
    	initialize();
		createEvents();
	}
	
	private void createEvents (){
		visualiseModelBoard();
		openExistingModel ();
		parseModelToolBar();
		parseModelMenuBar();
		findOutPutDirectory();
		exitRadar();
		printModel();
		viewTutorial();
		aboutRadar();
		newFileMenuBar();
		newFileToolBar();
		openFileToolBar();
		saveFileToolBar();
		saveFileMenuBar();
		saveAsFileMenuBar();
		exportFileToolBar();
		exportFileMenuBar();
		undoRedo();
		solveModelToolBar();
		solveModelMenuBar();
		displayOptimisationAnalysisToolBar();
		displayOptimisationAnalysisMenuBar();
		displayInformationValueAnalysisToolBar();
		displayInformationValueAnalysisMenuBar();
		displayParetoFrontMenuBar();
		displayParetoFrontToolBar();
		displayANDORGraphMenuBar();
		displayDecisionDependencyGraphMenuBar();
		displayModelDecisionsToolBar();
		analysisSettings();
		findOutputDirectory();
		findGraphVizExecutablePath();
		viewCBAModel();
		viewBSPDMModel();
		viewFDMModel();
		viewSASModel();
		viewECSModel();
		graphvizPathForMAC();
		graphvizForWindows();
		graphvizForLinux();
		graphvizForOtherSystemType();
		minimiseViewToolBar();
		minimiseViewMenuBar();
		MaximiseViewToolBar();
		MaximiseViewMenuBar();
	}
	void graphvizPathForMAC(){
		rdbtnMAC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnMAC.isSelected()){
					executable = ConfigSetting.MACEXE;
					textGraphvizExecutablePath.setText(executable);
					btnBrowseExecutablePath.setVisible(false);
				}
			}
		});
	}
	void graphvizForWindows(){
		rdbtnWindows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnWindows.isSelected()){
					executable = ConfigSetting.WINEXE;
					textGraphvizExecutablePath.setText(executable);
					btnBrowseExecutablePath.setVisible(false);
				}
			}
		});
	}
	void graphvizForLinux (){
		rdbtnLinux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnLinux.isSelected()){
					executable = ConfigSetting.LINUXEXE;
					textGraphvizExecutablePath.setText(executable);
					btnBrowseExecutablePath.setVisible(false);
				}
				
			}
		});
		
	}
	void graphvizForOtherSystemType (){
		rdbtnOthers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnOthers.isSelected()){
					executable = "";
					btnBrowseExecutablePath.setVisible(true);
					textGraphvizExecutablePath.setText(executable);
				}
			}
		});
	}
	void viewModelExample (String model_example){
		modelExample = model_example;
		if (modelTextPane != null && !modelTextPane.getText().equals("")){
			int choice = JOptionPane.showConfirmDialog(frame,
					"The current model will be cleared'" + ""
							+ "'\nDo you want to proceed ?",
					"Confirmation Dialog", JOptionPane.INFORMATION_MESSAGE);
			if(choice == JOptionPane.OK_OPTION){
				
				clearEditModel();
				clearAnalysisResult();
				clearConsole();
				Component [] tabbedComponent = tabbedPane.getComponents();
				if (tabbedComponent != null){
					for (Component comp: tabbedComponent){
						if (comp instanceof JPanel && comp.getName() != null && !comp.getName().equals("Editor") && !comp.getName().equals("Analysis Result") && !comp.getName().equals("Console") ){
							tabbedPane.remove(comp);
						}
					}
				}
			}
		}
		openModelExamples();
		initialiseAnalysisSettings();
		tabbedPane.addTab(ConfigSetting.EDITORTAB,editModel);
		tabbedPane.addTab(ConfigSetting.TABANALYSISRESULT,analysisResult);
		tabbedPane.addTab(ConfigSetting.CONSOLETAB,console);
		
		tabbedPane.setSelectedComponent(editModel);
		
		chckbxmntmOptimisationAnalysis.setSelected(false);
		chckbxmntmInformationValueAnalysis.setSelected(false);
		chckbxmntmVariableAndorGraph.setSelected(false);
		chckbxmntmDecisionDependencyGraph.setSelected(false);
		chckbxmntmParetoFront.setSelected(false);
		chckbxmntmModelDecisions.setSelected(false);
	}
	void viewCBAModel(){
		mntmRefactoringrdr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelExample = ConfigSetting.CBA;
				viewModelExample(modelExample);
			}
		});
	}
	void viewBSPDMModel(){
		mntmBuildingSecurityPolicy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelExample = ConfigSetting.BSPDM;
				initialiseModelSubgraphAndInfoValueObjective();
				viewModelExample(modelExample);
			}
		});
	}
	void viewFDMModel(){
		mntmFinancialFraudDetection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelExample = ConfigSetting.FDM;
				initialiseModelSubgraphAndInfoValueObjective();
				viewModelExample(modelExample);
			}
		});
	}
	void viewSASModel(){
		mntmSituationAwarenessSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelExample = ConfigSetting.SAS;
				initialiseModelSubgraphAndInfoValueObjective();
				viewModelExample(modelExample);
			}
		});
	}
	void viewECSModel(){
		mntmSatelliteImageProcessing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelExample = ConfigSetting.ECM;
				initialiseModelSubgraphAndInfoValueObjective();
				viewModelExample(modelExample);
			}
		});
	}

	void findGraphVizExecutablePath(){
		final JFileChooser  fileDialog = new JFileChooser();
		btnBrowseExecutablePath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileDialog.setCurrentDirectory(new java.io.File(""));
				fileDialog.setDialogTitle("GraphViz Executable Path");
				fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileDialog.setAcceptAllFileFilterUsed(false);
				int returnVal = fileDialog.showOpenDialog(frame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	textGraphvizExecutablePath.setText("");
	            	textGraphvizExecutablePath.setText(fileDialog.getSelectedFile().getAbsolutePath().toString());
	               if (!doesDirectoryExist(textGraphvizExecutablePath.getText())){
	            	   textGraphvizExecutablePath.setText("");  
	            	   JOptionPane.showMessageDialog(null, "Specified directory '"+fileDialog.getSelectedFile().getAbsolutePath().toString()+ "' does not exist." , "", JOptionPane.INFORMATION_MESSAGE);
	       				return;
	               }
	               
	            }
	            else{
	            	textOutputDirectory.setText("");           
	            } 
			}
		});
	}
	void findOutputDirectory (){
		final JFileChooser  fileDialog = new JFileChooser();
		btnBrowseOutputPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				fileDialog.setCurrentDirectory(new java.io.File(""));
				fileDialog.setDialogTitle("Output Directory");
				fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileDialog.setAcceptAllFileFilterUsed(false);
				int returnVal = fileDialog.showOpenDialog(frame);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	            	textOutputDirectory.setText("");
	               textOutputDirectory.setText(fileDialog.getSelectedFile().getAbsolutePath().toString());
	               if (!doesDirectoryExist(textOutputDirectory.getText())){
	            	   textOutputDirectory.setText(outPutDirectory);  
	            	   JOptionPane.showMessageDialog(null, "Specified directory '"+fileDialog.getSelectedFile().getAbsolutePath().toString()+ "' does not exist." , "", JOptionPane.INFORMATION_MESSAGE);
	       				return;
	               }
	               
	            }
	            else{
	            	textOutputDirectory.setText(outPutDirectory);           
	            } 
			}
		});
	}
	void initialiseModelSubgraphAndInfoValueObjective(){
		// dependencding on the selected examples
		// populate the info value objective and subgraph objective 
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
	void initialiseAnalysisSettings(){
		textNbrSimulation.setText(String.valueOf(ConfigSetting.NUMBER_OF_SIMULATION));
		textOutputDirectory.setText(outPutDirectory);
		initialiseModelSubgraphAndInfoValueObjective();
		
	}
	void analysisSettings(){
		mntmAnalysisSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = ConfigSetting.TABANALYSISSETTINGS;
				initialiseAnalysisSettings();
				tabbedPane.addTab(title, null, analysisSettingsPanel, null);
				tabbedPane.setSelectedComponent(analysisSettingsPanel);
				
				
			}
		});
	}
	void displayModeldDecision(){
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
			
			populateDecisionTable(decisionTable);
			
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
			return;
		}
		
	}
	void displayModelDecisionsToolBar (){
		chckbxmntmModelDecisions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tabName = ConfigSetting.TABDECISIONS;
				AbstractButton aButton = (AbstractButton)e.getSource();
				boolean selected = aButton.getModel().isSelected();
				if (selected){
					if (!tabbedComponentExits(tabName)){
						displayModeldDecision();
					}
					
				}
				else{ // commented this becuause there is no way to sync the close of a tab with jcheckboxitem when selected or not
					// remove it from tabbed bar
					Component [] allTabbedComponent = tabbedPane.getComponents();
					for (Component comp: allTabbedComponent){
						if (tabName.equals(comp.getName())){
							tabbedPane.remove(comp);
						}
					}
					chckbxmntmModelDecisions.setSelected(false);	
				}
			}
		});
	}
	void displayDecisionDependencyGraph(){
		if (semanticModel != null){
			String decisionGraph = semanticModel.generateDecisionDiagram(result.getAllSolutions());
			decisionDependencyGraphPanel = new JPanel(new BorderLayout());
			String graphType = ConfigSetting.TABDECISIONGRAPH;
			decisionDependencyGraphPanel.setName(graphType);
			decisionDependencyGraphImageLabel = new ImageLabel ("");
			decisionDependencyGraphIcon  = viewDotGraph(decisionGraph,"png", graphType, decisionDependencyGraphPanel,decisionDependencyGraphImageLabel);
			chckbxmntmDecisionDependencyGraph.setSelected(true);
		}else{
			JOptionPane.showMessageDialog(null, "You need to write a new model." , "", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
	}
	void displayDecisionDependencyGraphMenuBar(){
		chckbxmntmDecisionDependencyGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tabName = ConfigSetting.TABDECISIONGRAPH;;
				AbstractButton aButton = (AbstractButton)e.getSource();
				boolean selected = aButton.getModel().isSelected();
				if (selected){
					if (!tabbedComponentExits(tabName)){
						displayDecisionDependencyGraph();
					}
					
				}else{
					// remove it from tabbed bar
					Component [] allTabbedComponent = tabbedPane.getComponents();
					for (Component comp: allTabbedComponent){
						if (tabName.equals(comp.getName())){
							tabbedPane.remove(comp);
						}
					}
					chckbxmntmDecisionDependencyGraph.setSelected(false);	
				}
			}
		});
	}
	void displayANDORGraph (){
		if (semanticModel != null){
			String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, result.getSubGraphObjective());
			variableGraphPanel = new JPanel(new BorderLayout());
			variableGraphImageLabel = new ImageLabel ("");
			String graphType = ConfigSetting.TABGOALGRAPH;
			variableGraphPanel.setName(graphType);
			variableGraphIcon = viewDotGraph(variableGraph,"png", graphType, variableGraphPanel,variableGraphImageLabel);
			chckbxmntmVariableAndorGraph.setSelected(true);
		}else{
			JOptionPane.showMessageDialog(null, "You need to write a new model." , "", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
	}
	void displayANDORGraphMenuBar(){
		chckbxmntmVariableAndorGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tabName =ConfigSetting.TABGOALGRAPH;
				AbstractButton aButton = (AbstractButton)e.getSource();
				boolean selected = aButton.getModel().isSelected();
				if (selected){
					if (!tabbedComponentExits(tabName)){
						displayANDORGraph();
					}
					
				}else{
					// remove it from tabbed bar
					Component [] allTabbedComponent = tabbedPane.getComponents();
					for (Component comp: allTabbedComponent){
						if (tabName.equals(comp.getName())){
							tabbedPane.remove(comp);
						}
					}
					chckbxmntmVariableAndorGraph.setSelected(false);	
				}
			}
		});
	}
	void displayParetoFront(){
		if (result != null){
			String modelResultPath = outPutDirectory + semanticModel.getModelName() + "/ICSE/AnalysisResult/" ;
			String imageOutput = modelResultPath + "/";
			String title = ConfigSetting.TABPARETO;
			if (result.getShortListObjectives().get(0).length == 2){
				TwoDPanelPlotter twoDPlot = new TwoDPanelPlotter();
				twoDPlot.setSize(editModel.getSize());
				twoDPlot.setName(title);
				twoDPlot.setLayout(new BorderLayout());
				viewPareto(twoDPlot, title);
				twoDPlot.plot(semanticModel,imageOutput, result);
			}else if (result.getShortListObjectives().get(0).length == 3){
				ScatterPlotPanel3D sc3D2= new ScatterPlotPanel3D( );
				sc3D2.setSize(editModel.getSize());
				sc3D2.setName(title);
				sc3D2.setLayout(new BorderLayout());
				viewPareto(sc3D2, title);
				try {
					sc3D2.plot(semanticModel, imageOutput, result);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			chckbxmntmParetoFront.setSelected(true);
		}else{
			JOptionPane.showMessageDialog(null, "No analysis result to disolay." , "", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	boolean tabbedComponentExits(String componentName){
		boolean exist =false;
		Component [] allTabbedComponent = tabbedPane.getComponents();
		for (Component comp: allTabbedComponent){
			if (componentName.equals(comp.getName())){
				exist = true;
			}
		}
		return exist;
	}
	void displayParetoFrontMenuBar(){
		chckbxmntmParetoFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tabName = ConfigSetting.TABPARETO;
				AbstractButton aButton = (AbstractButton)e.getSource();
				boolean selected = aButton.getModel().isSelected();
				// synonymous to when the chkbox is clicked
				if (selected){
					if (!tabbedComponentExits(tabName)){
						displayParetoFront();
					}
					
				}else{
					// remove it from tabbed bar
					Component [] allTabbedComponent = tabbedPane.getComponents();
					for (Component comp: allTabbedComponent){
						if (tabName.equals(comp.getName())){
							tabbedPane.remove(comp);
						}
					}
					chckbxmntmParetoFront.setSelected(false);	
				}
			}
		});
	}
	void displayParetoFrontToolBar(){
		btnParetoFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayParetoFront();
				chckbxmntmParetoFront.setSelected(true);
			}
		});
	}
	void displayInformationValueAnalysisToolBar(){
		btnInfoValueAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayInformationValueAnalysis();
				chckbxmntmInformationValueAnalysis.setSelected(true);
			}
		});
	}
	void displayInformationValueAnalysisMenuBar(){
		//https://books.google.co.uk/books?id=hoUnCgAAQBAJ&pg=PA205&lpg=PA205&dq=JCHECKBOXMENUITEM+selected+property+does+not+work&source=bl&ots=3-ZgJqY5SM&sig=8Hkuss_flecOG0eQsK6tfxLalE4&hl=en&sa=X&ved=0ahUKEwjo1IDX37LSAhWLAMAKHVPaACgQ6AEIPTAG#v=onepage&q=JCHECKBOXMENUITEM%20selected%20property%20does%20not%20work&f=false
		chckbxmntmInformationValueAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbstractButton aButton = (AbstractButton)e.getSource();
				boolean selected = aButton.getModel().isSelected();
				String tabName = ConfigSetting.TABINFOVALUEANALYSIS;
				if (selected){
					if (!tabbedComponentExits(tabName)){
						displayInformationValueAnalysis();
					}
				}else{
					// remove it from tabbed bar
					Component [] allTabbedComponent = tabbedPane.getComponents();
					for (Component comp: allTabbedComponent){
						if (tabName.equals(comp.getName())){
							tabbedPane.remove(comp);
						}
					}
					chckbxmntmInformationValueAnalysis.setSelected(false);
				}
			}
		});
		
	}
	void displayInformationValueAnalysis(){
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
			populateTable(infoValueAnalysisTable, "infoValue");
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
			return;
		}
	}
	
	void displayOptimisationAnalysis(){
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
			populateTable(optimisationTable, "optimisation");
			scrollPaneOptimisationDetails.setViewportView(optimisationTable);
			
			JScrollPane scrollPaneOptimisationSolution = new JScrollPane();
			scrollPaneOptimisationSolution.setPreferredSize(new Dimension(850, 440));
			optimisationAnalysis.add(scrollPaneOptimisationSolution);
			
			JTable solutionTable = new JTable();
			populateTable(solutionTable, "solution");
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
	void displayOptimisationAnalysisToolBar(){
    	btnOptimisationAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayOptimisationAnalysis();
				chckbxmntmOptimisationAnalysis.setSelected(true);
			}
		});
    }
	void displayOptimisationAnalysisMenuBar(){
		chckbxmntmOptimisationAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tabName = ConfigSetting.TABOPTIMISATIONANALYSIS;
				AbstractButton aButton = (AbstractButton)e.getSource();
				boolean selected = aButton.getModel().isSelected();
				// synonymous to wgen the chkbox is clicked
				if (selected){
					if (!tabbedComponentExits(tabName)){
						displayOptimisationAnalysis();
					}
					
				}
				else{
					// remove it from tabbed bar
					Component [] allTabbedComponent = tabbedPane.getComponents();
					for (Component comp: allTabbedComponent){
						if (tabName.equals(comp.getName())){
							tabbedPane.remove(comp);
						}
					}
					chckbxmntmOptimisationAnalysis.setSelected(false);	
				}
				
			}
		});
	}
	int progress;
	boolean progressBarCancelled =false;
	String progressMessage = "";
	private JToolBar toolBar_1;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JMenu mnNewMenu;
	private JCheckBoxMenuItem chckbxmntmOptimisationAnalysis;
	private JCheckBoxMenuItem chckbxmntmInformationValueAnalysis;
	private JCheckBoxMenuItem chckbxmntmVariableAndorGraph;
	private JCheckBoxMenuItem chckbxmntmDecisionDependencyGraph;
	private JCheckBoxMenuItem chckbxmntmParetoFront;
	private JSeparator separator;
	private JCheckBoxMenuItem chckbxmntmModelDecisions;
	private JMenuItem mntmAnalysisSettings;
	private JSeparator separator_23;
	private JMenuItem mntmAlgorithmSettings;
	private JPanel analysisSettingsPanel;
	private JLabel lblNbrSimulation;
	private JTextField textNbrSimulation;
	private JTextField textInformationValueObjective;
	private JTextField textSubgraphObjective;
	private JLabel lblOutputDirectory;
	private JTextField textOutputDirectory;
	private JButton btnBrowseOutputPath;
	private JMenu mnExamples;
	private JSeparator separator_3;
	private JMenuItem mntmRefactoringrdr;
	private JMenuItem mntmFinancialFraudDetection;
	private JMenuItem mntmSituationAwarenessSystem;
	private JMenuItem mntmSatelliteImageProcessing;
	private JMenuItem mntmBuildingSecurityPolicy;
	private JSeparator separator_8;
	private JSeparator separator_20;
	private JSeparator separator_24;
	private JSeparator separator_25;
	private JMenu mnRadar;
	private JMenuItem mntmAboutRadar;
	private JMenuItem mntmHowToUseRADAR;
	private JSeparator separator_26;
	private JMenuItem mntmQuitRADAR;
	private JSeparator separator_27;
	private JTextField textFieldDecimalPrecision;
	private JTextField textGraphvizExecutablePath;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnMAC;
	private JRadioButton rdbtnWindows;
	private JRadioButton rdbtnLinux;
	private JRadioButton rdbtnOthers;
	private JButton btnBrowseExecutablePath;
	private JMenu mnView;
	private JMenuItem mntmMaximise;
	/*
	 * http://www.javacreed.com/swing-worker-example/
	 *  http://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html#ProgressMonitorDemo
	 */
    class Task extends SwingWorker<Void, Void> {
    	
        @Override
        public Void doInBackground() {
        	tabbedPane.setSelectedComponent(console);
            AnalysisResult tempResult = null;
            progress = 0;
            setProgress(0);
            try {
                //Thread.sleep(1000);
                int analysisIndex = -1;
                //progress += 20;
                setProgress(Math.min(progress, 100));
                // perform analysis
                while (progress < 100 && !isCancelled()) {
                	if (analysisIndex == -1){
                		consoleTextArea.append("Checking cyclic dependencies in the model ");
                	}
                    if (analysisIndex == 0){
                    	consoleTextArea.append("Analysis Step "+ (analysisIndex+1) +": Generating design space ");
                    }
                    if (analysisIndex == 1){
                    	consoleTextArea.append("Analysis Step "+ (analysisIndex+1) + ": Simulating the design space ");
                    }
                    if (analysisIndex == 2){
                    	consoleTextArea.append("Analysis Step "+ (analysisIndex+1) + ": Shortlisting Pareto optimal solutions ");
                    }
                    if (analysisIndex == 3){
                    	consoleTextArea.append("Analysis Step "+ (analysisIndex+1) + ": Computing expected value of information ");
                    }
                	setProgress(Math.min(progress, 100));
                	Thread.sleep(1000);
                    tempResult = ModelSolver.solve(semanticModel, tempResult, analysisIndex);
                    consoleTextArea.append(tempResult.getConsoleMessage()+"");
                    progress += 20;
                    setProgress(Math.min(progress, 100));
                    analysisIndex++;
                    
                }
                Thread.sleep(100);
                consoleTextArea.append("Generating Pareto fronts, Goal Graph and Decsion Graph"+" \n \n");
            	result = tempResult;
            	if(textFieldDecimalPrecision.getText() != ""){
            		result.addDecimalPrecision(Integer.parseInt(textFieldDecimalPrecision.getText()));
            	}
            	setProgress(Math.min(90, 100));
            } catch (InterruptedException ignore) {}
            return null;
        }

        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            btnParse.setEnabled(true);
    		btnSolve.setEnabled(true);
    		itemParseModel.setEnabled(true);
    		itemSolveModel.setEnabled(true);
            //progressMonitor.setProgress(0);
    		consoleTextArea.append("\n" + result.analysisToString());
            // add these here to avoid the exception thrown when in inside the do in background method.
            loadResultInFrame();
			generateAnalysisGraphs();
			//chckbxmntmInformationValueAnalysis.setSelected(true);
			modelSolved = true;
            
        }
    }
    /**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName() ) {
            int progress = (Integer) evt.getNewValue();
            //progressMonitor.setProgress(progress);
            String message =String.format("Completed %d%% of analysis\n", progress);
            //progressMonitor.setNote(progressMessage);
            //progressMonitor.setNote(message);
            //consoleTextArea.append(message);
            //if (progressMonitor.isCanceled() || task.isDone()) {
            if (task.isDone()) {
                Toolkit.getDefaultToolkit().beep();
                /*if (progressMonitor.isCanceled()) {
                    task.cancel(true);
                    progressBarCancelled = true;
                    Thread.currentThread().stop();
                    consoleTextArea.append("Analysis canceled.\n");
                } else {
                	consoleTextArea.append("Analysis completed.\n");
                }*/
                consoleTextArea.append("Analysis Summary.\n");
                consoleTextArea.setEnabled(true);
            }
        }

    }
  
    void createPlot(String modelResultPath){
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
    void creatANDORGraph (String modelResultPath){
    	try {
    		String variableGraph = semanticModel.generateDOTRefinementGraph(semanticModel, result.getSubGraphObjective());
			Helper.printResults (modelResultPath + "graph/", variableGraph, semanticModel.getModelName() +"vgraph.dot", false);
			variableGraphPanel = new JPanel(new BorderLayout());
			variableGraphImageLabel = new ImageLabel ("");
			String graphType = ConfigSetting.TABGOALGRAPH;
			variableGraphPanel.setName(graphType);
			variableGraphIcon = viewDotGraph(variableGraph,"png", graphType, variableGraphPanel,variableGraphImageLabel);
			chckbxmntmVariableAndorGraph.setSelected(true);
	
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage() , "", JOptionPane.ERROR_MESSAGE);
			return;
		}
    }
    void createDecisionGraph(String modelResultPath){
    	try {
	    	String decisionGraph = semanticModel.generateDecisionDiagram(result.getAllSolutions());
			Helper.printResults (modelResultPath + "graph/", decisionGraph, semanticModel.getModelName() + "dgraph.dot", false);
			decisionDependencyGraphPanel = new JPanel(new BorderLayout());
			decisionDependencyGraphImageLabel = new ImageLabel("");
			String graphType = ConfigSetting.TABDECISIONGRAPH;
			decisionDependencyGraphPanel.setName(graphType);
			decisionDependencyGraphIcon  = viewDotGraph(decisionGraph,"png", graphType, decisionDependencyGraphPanel,decisionDependencyGraphImageLabel);
			chckbxmntmDecisionDependencyGraph.setSelected(true);
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage() , "", JOptionPane.ERROR_MESSAGE);
			return;
		}

    }
    void generateAnalysisGraphs(){
    	try {
	    	String analysisResult = result.analysisToString();
			String analysisResultToCSV = result.analysisResultToCSV();
			String modelResultPath = outPutDirectory + semanticModel.getModelName() + "/ICSE/AnalysisResult/" ;
			
			Helper.printResults (modelResultPath , analysisResult, semanticModel.getModelName() +".out", false);
			Helper.printResults (modelResultPath , analysisResultToCSV, semanticModel.getModelName() +".csv", false);
			
			if (true){
				createPlot(modelResultPath);
			}
			// generate graphs
			if (true){
				creatANDORGraph(modelResultPath);
			}
			if (true){
				createDecisionGraph(modelResultPath);
			}
			System.out.println("Finished!");
			modelSolved =true;
			solvedModel = ""; // textModelArea.getText();
    	} catch (IOException e) {
    		JOptionPane.showMessageDialog(null, e.getMessage() , "", JOptionPane.ERROR_MESSAGE);
			return;
		}
    }
	void asynchronousSolve (){
		
		/*progressMonitor = new ProgressMonitor(frame,
                "Analysing the  " + semanticModel.getModelName().toUpperCase() + " model.",
                "", 0, 100);
		progressMonitor.setProgress(0);*/
		task = new Task();
		task.addPropertyChangeListener(RADAR_GUI2.this);
		task.execute();
		btnParse.setEnabled(false);
		btnSolve.setEnabled(false);
		itemParseModel.setEnabled(false);
		itemSolveModel.setEnabled(false);
		
		
	}
	void openModelExamples(){
		switch(modelExample){
			case "CBA": openedFilePath = outPutDirectory + ConfigSetting.CBA + ".rdr"; break;
			case "FDM": openedFilePath = outPutDirectory + ConfigSetting.FDM + ".rdr"; break;
			case "ECS": openedFilePath = outPutDirectory + ConfigSetting.ECM + ".rdr"; break;
			case "BSPDM": openedFilePath = outPutDirectory + ConfigSetting.BSPDM + ".rdr";  break;
			case "SAS" : openedFilePath = outPutDirectory + ConfigSetting.SAS + ".rdr"; break;
		}
        loadExistingModel(openedFilePath);
	}
	void openModel(){
		final JFileChooser  fileDialog = new JFileChooser();
		int returnVal = fileDialog.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
           java.io.File file = fileDialog.getSelectedFile();
           String fileExtension = getFileExtension(file);
           if (!fileExtension.equals("rdr")){
        	   JOptionPane.showMessageDialog(null, "Radar files must end with the (.rdr) extensions");
        	   return;
           }
           openedFilePath = file.getPath();
           loadExistingModel(file.getPath());
        }
	}
	void newModel(){
		boolean analysisComponentExist =false;
		Component [] allTabbedComponent = tabbedPane.getComponents();
		if (allTabbedComponent != null && allTabbedComponent.length >0){
			for (Component comp: allTabbedComponent){
				if (comp.getName() != null && comp.getName().equals(ConfigSetting.EDITORTAB)){
					analysisComponentExist = true;
				}
			}
		}
		if (!analysisComponentExist){
			tabbedPane.addTab(ConfigSetting.EDITORTAB, editModel);
			tabbedPane.setSelectedComponent(editModel);
		}else{
			if (modelTextPane != null && !modelTextPane.getText().equals("")){
				int choice = JOptionPane.showConfirmDialog(frame,
						"The current model will be cleared'" + ""
								+ "'\nDo you want to proceed ?",
						"Confirmation Dialog", JOptionPane.INFORMATION_MESSAGE);
				if(choice == JOptionPane.OK_OPTION){
					//clear all tabbedPane
					Component [] tabbedComponent = tabbedPane.getComponents();
					if (tabbedComponent != null){
						for (Component comp: tabbedComponent){
							if (comp instanceof JPanel && comp.getName() != null && !comp.getName().equals("Editor") && !comp.getName().equals("Analysis Result") && !comp.getName().equals("Console") ){
								tabbedPane.remove(comp);
							}
						}
					}
					// load new edit model board.
					tabbedPane.addTab(ConfigSetting.EDITORTAB,editModel);
					tabbedPane.setSelectedComponent(editModel);
					tabbedPane.addTab(ConfigSetting.TABANALYSISRESULT,analysisResult);
					tabbedPane.addTab(ConfigSetting.CONSOLETAB,console);
					clearEditModel();
					clearAnalysisResult();
					clearConsole();
				}
			}else{
				tabbedPane.setSelectedComponent(editModel);
			}
			
		}
		chckbxmntmOptimisationAnalysis.setSelected(false);
		chckbxmntmInformationValueAnalysis.setSelected(false);
		chckbxmntmVariableAndorGraph.setSelected(false);
		chckbxmntmDecisionDependencyGraph.setSelected(false);
		chckbxmntmParetoFront.setSelected(false);
		chckbxmntmModelDecisions.setSelected(false);
	}
	void clearEditModel(){
		if(modelTextPane != null) modelTextPane.setText("");
	}
	void clearAnalysisResult(){
		
		DefaultTableModel dtm = new DefaultTableModel();
		if(tableOptimisationSolutions != null)tableOptimisationSolutions.setModel(dtm);
		if(tableOptimisationDetails != null) tableOptimisationDetails.setModel(dtm);
		if(tableInfoValueAnalysis != null) tableInfoValueAnalysis.setModel(dtm);
	}
	void clearConsole(){
		consoleTextArea.setText("");
		consoleTextArea.setForeground(Color.BLACK);
	}
	void writeModelContent(TableModel model, File fileToExport ) throws IOException{
        FileWriter out;
		out = new FileWriter(fileToExport);
		for(int i=0; i < model.getColumnCount(); i++) {
            out.write(model.getColumnName(i) + ",");
        }
        out.write("\n");
        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
                out.write(model.getValueAt(i,j).toString()+",");
            }
            out.write("\n");
        }
        out.close();
	}
	void exportAnalysisResult(){
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
			    writeModelContent(tableOptimisationDetails.getModel(), fileToExport);
			    writeModelContent(tableOptimisationSolutions.getModel(), fileToExport);
			    writeModelContent(tableInfoValueAnalysis.getModel(), fileToExport);
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
	private void exportFileToolBar(){
		btnExportFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportAnalysisResult();
			}
		});
	}
	private void exportFileMenuBar(){
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportAnalysisResult();
			}
		});
	}
	private void openFileToolBar(){
		btnOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openModel();
			}
		});
	}
	/*
	 * //http://www.java2s.com/Code/Java/Swing-JFC/Undoredotextarea.htm
	 * http://stackoverflow.com/questions/9123358/what-is-the-best-way-to-cut-copy-and-paste-in-java
	 */
	void undoRedo (){
		modelTextPane.getDocument().addUndoableEditListener(
		        new UndoableEditListener() {
		          public void undoableEditHappened(UndoableEditEvent e) {
		            undoManager.addEdit(e.getEdit());
		            updateButtons();
		          }
		        });
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			          undoManager.undo();
			        } catch (CannotUndoException cre) {
			          cre.printStackTrace();
			        }
			        updateButtons();
			}
		});
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			          undoManager.redo();
			        } catch (CannotRedoException cre) {
			          cre.printStackTrace();
			        }
			        updateButtons();
			}
		});
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			          undoManager.undo();
			        } catch (CannotUndoException cre) {
			          cre.printStackTrace();
			        }
			        updateButtons();
			}
		});
		mntmRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
			          undoManager.redo();
			        } catch (CannotRedoException cre) {
			          cre.printStackTrace();
			        }
			        updateButtons();
			}
		});
	}
	
	public void updateButtons() {
	    btnUndo.setEnabled(undoManager.canUndo());
	    btnRedo.setEnabled(undoManager.canRedo());
	    mntmUndo.setEnabled(undoManager.canUndo());
	    mntmRedo.setEnabled(undoManager.canRedo());
	  }
	void openExistingModel (){
		itemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openModel();
			}
		});
	}
	private void newFileMenuBar(){
		btnNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newModel();
			}
		});
	}
	private void newFileToolBar(){
		newMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newModel();
			}
		});
	}
	private void viewTutorial (){
		mntmHowToUseRADAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new TutorialFrame();
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
		});
	}

	private void aboutRadar(){
		mntmAboutRadar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
	}
	private void exitRadar(){
		mntmQuitRADAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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
	private void printModel(){
		itemPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (!StringUtils.isEmpty(modelTextPane.getText())){
						boolean complete = modelTextPane.print();
			            if(complete){
			                JOptionPane.showMessageDialog(null,  "Print Completed!", "Model",JOptionPane.INFORMATION_MESSAGE);
			            }else{
			                JOptionPane.showMessageDialog(null, "Nothing was printed.", "Printer", JOptionPane.ERROR_MESSAGE);
			            }
			            return;
					}else{
						JOptionPane.showMessageDialog(null, "Nothing to print.", "Printer", JOptionPane.ERROR_MESSAGE);
					}
		            
		        }
				catch(PrinterException ex){
					JOptionPane.showMessageDialog(null, ex);
					return;
		        }
		        
			}
		});
	}
	void saveModel(){
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
					    String fileExtension = getFileExtension(fileToSave);
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
	private void saveFileToolBar(){
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveModel();
			}
		});
	}
	private void saveFileMenuBar (){
		
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveModel();
			}
		});
	}
	private void saveAsFileMenuBar(){
		itemSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveModel();
			}
		});
	}
	
	void visualiseModelBoard(){
	}
	void parseModel(){
		if (modelTextPane.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "You need to write a new decision model, \nor select from existing decision models.");
			return;
		}
		String analysisMsg = allAnalysisSettingValid();
		if (!analysisMsg.isEmpty()){
			JOptionPane.showMessageDialog(null, analysisMsg);
			return;
		}
		
		parse();
		if (parsed ==true){
			//check that info value objective exist
			boolean infoValueObjExist = infoValueObjectiveExist();
			if (!infoValueObjExist) return;
			
			
			
			//populateDecisionTable();
			int selection = JOptionPane.showConfirmDialog( frame , "Model has been parsed successfully. \nDo you want to continue with analysis?" , "Confirmation "
                    , JOptionPane.YES_NO_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
			if (selection == JOptionPane.YES_OPTION)
            {
				//solve();
				clearConsole();
				clearAnalysisResult();
				asynchronousSolve();
				//assign new value incase it changes
				executable = textGraphvizExecutablePath.getText();
				if (modelSolved){
					solvedModel = modelTextPane.getText();
				}
            }
			modelParsed= true;
		}
	}
	void parseModelToolBar(){
		btnParse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parseModel();
			}
		});
	}
	void parseModelMenuBar (){
		itemParseModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parseModel();
			}
		});
		
	}
	void solveModel(){
		tabbedPane.setSelectedComponent(console);
		if (modelTextPane.getText().isEmpty()){
			JOptionPane.showMessageDialog(null, "You need to write a new decision model,\nor select from existing decision models.");
			return;
		}
		// also check that there is no changes to the model, otherwise, we parse again.
		if (solvedModel != null){
			if ( !solvedModel.equals(modelTextPane.getText())){
				parse();
			}
		}else{
			parse();
		}
		if (semanticModel != null && modelParsed == true) {
			//solve();
			asynchronousSolve();
		}else{
			String analysisMsg = allAnalysisSettingValid();
			if (!analysisMsg.isEmpty()){
				JOptionPane.showMessageDialog(null, analysisMsg);
				return;
			}
			parseModel();
			if (modelParsed == true){
				//solve();
				asynchronousSolve();
			}
			
		}
	}
	
	void solveModelToolBar(){
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAnalysisResult();
				clearConsole();
				solveModel();
			}
		});
	}
	private void solveModelMenuBar(){
		itemSolveModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAnalysisResult();
				clearConsole();
				solveModel();
			}
		});
	}
	private String allAnalysisSettingValid (){
		String validationMessage = inputValidation();
		return validationMessage;

	}
	private String getOutputDirectory (){
		return RADAR_GUI2.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	}
	private boolean doesDirectoryExist(String path){
		boolean result =true;
		try{
			InputValidator.validateOutputPath(path);
		}catch (Exception e){
			result = false;
			
		}
		return result;
	}
	private String inputValidation (){
		String message = "";
		message += InputValidator.verifyEmptyField (textNbrSimulation, "Number of simulation", "Integer");
		//message += InputValidator.verifyEmptyField (textOutputDirectory, "output directory", "String");
		message += InputValidator.verifyFieldDataType (textSubgraphObjective.getText(), "Subgraph Objective", "String");
		message += InputValidator.verifyFieldDataType (textInformationValueObjective.getText(), "Information Value Objective", "String");
		message += InputValidator.verifyFieldDataType (textNbrSimulation.getText(), "Nbr. Simulation", "Integer");
		message += InputValidator.verifyFieldDataType (textFieldDecimalPrecision.getText(), "Decimal Precision", "Integer");
		message += InputValidator.verifyFieldNonNegativeValue(textNbrSimulation.getText(), "Nbr. Simulation", "Integer");
		message += InputValidator.verifyFieldNonNegativeValue(textFieldDecimalPrecision.getText(), "Decimal Precision", "Integer");
		message += InputValidator.verifyEmptyField(textFieldDecimalPrecision, "Decimal Precision", "Integer");
		if (!message.isEmpty()){
			message += "Check the 'analysis settings' page to make corrections.\n";
		}
		
		//message += validateOutputDirectoryPath();
		return message;
	}
	private String validateOutputDirectoryPath (){
		String message ="";
		if(!textOutputDirectory.getText().isEmpty()){
			Pattern pattern = Pattern.compile("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_-]+)+\\\\?");
			Matcher matcher = pattern.matcher(textOutputDirectory.getText());
			if (!matcher.matches()){
				message += "The path specified for the output directory is not valid.";
			}
		}else{
			textOutputDirectory.setText(outPutDirectory); 
			//message+= "You must specify the directory to store results.";
		}
		return message;
	}
	private void populateDecisionTable (JTable  decisionsTable){
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
	String validateOutputPath (String outputPath){
		String message ="";
		if (outputPath != null){
			File modelFile = new File (outputPath.trim());
    		if (!modelFile.exists()){
    			message = "The file '"+ outputPath+ "' does not exist."; 
    		}
		}
		return message;
	}
	void parse (){
		try {
			if (textOutputDirectory.getText() != "" && !textOutputDirectory.getText().isEmpty()){
				String message = validateOutputPath(textOutputDirectory.getText());
				if (message != "") {
					JOptionPane.showMessageDialog(null, message);
					parsed =false;
					consoleTextArea.append(message);
					tabbedPane.setSelectedComponent(console);
					return;
				}
			}
			semanticModel = loadModel();
			if (semanticModel != null){
				parsed =true;
				modelParsed = true;
			}
		} catch (Exception e) {
			parsed =false;
			String err = e.getMessage();
			consoleTextArea.append(err);
			tabbedPane.setSelectedComponent(console);
			//JOptionPane.showMessageDialog(null, err);
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
			JOptionPane.showMessageDialog(null, "Model needs to be parsed and analysed to show for results!");
			return;
		}
		modelResultFrame  = new ModelResultFrame();
		
		JTable solutionTable = tableOptimisationSolutions;
		populateTable(solutionTable, "solution");
		JTable optimisationTable = tableOptimisationDetails;
		populateTable(optimisationTable, "optimisation");
		JTable infoValueTable = tableInfoValueAnalysis;
		populateTable(infoValueTable, "infoValue");
		
		//tabbedPane.setSelectedComponent(analysisResult);
		boolean analysisComponentExist =false;
		Component [] allTabbedComponent = tabbedPane.getComponents();
		if (allTabbedComponent != null && allTabbedComponent.length >0){
			for (Component comp: allTabbedComponent){
				if (comp.getName() != null && comp.getName().equals(ConfigSetting.TABANALYSISRESULT)){
					analysisComponentExist = true;
				}
			}
		}
		if (!analysisComponentExist){
			tabbedPane.addTab(ConfigSetting.TABANALYSISRESULT, analysisResult);
		}
		tabbedPane.setSelectedComponent(console);
	}
	void viewPareto (JPanel panel, String title){
		Component [] allTabbedComponent = tabbedPane.getComponents();
		for (Component comp: allTabbedComponent){
			if (panel.getName().equals(comp.getName())){
				tabbedPane.remove(comp);
			}
		}
		tabbedPane.addTab(title,panel);
		tabbedPane.setSelectedComponent(panel); // added this part to ensure the pareto front is focussed
		
	}
	void resizeGraphOnPanel(String graphType, ImageIcon imageIcon, ImageLabel imageLabel, String resizeType){
		//imageLabel.setHorizontalAlignment(JLabel.NORTH); already set
		imageLabel.setIcon(imageIcon);
		if(resizeType.equals("increase")){
			imageLabel.increaseScale(0.1);
		}else{
			imageLabel.decreaseScale(0.1);
		}
		imageLabel.repaint();
		JPanel  panel = new JPanel(new BorderLayout());
		panel.setForeground(Color.LIGHT_GRAY);
		JScrollPane scrollVariableImageLabel = new JScrollPane(imageLabel);
		panel.setName(graphType);
		scrollVariableImageLabel.setPreferredSize(new Dimension(850, 450));
		panel.add(scrollVariableImageLabel, BorderLayout.CENTER);
		Component [] allTabbedComponent = tabbedPane.getComponents();
		int i=-1;
		for (Component comp: allTabbedComponent){
			if (panel.getName().equals(comp.getName())){
				tabbedPane.remove(comp); 
				i++;
				break;
			}else{
				i++;
			}
		}
		tabbedPane.addTab( graphType, panel);
		tabbedPane.setSelectedComponent(panel);
		tabbedPane.repaint();
	}
	void minimiseViewToolBar(){
		buttonMinimise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minimiseView();
			}
		});
	}
	void minimiseViewMenuBar(){
		mntmMinimise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				minimiseView();
			}
		});
	}
	void minimiseView(){
		String graphType ="";
		Component currentComponent = tabbedPane.getSelectedComponent();
		if (currentComponent.getName() == ConfigSetting.TABGOALGRAPH){
			graphType = ConfigSetting.TABGOALGRAPH;
			resizeGraphOnPanel(graphType,variableGraphIcon,variableGraphImageLabel, "decrease");
		}
		else if (currentComponent.getName() == ConfigSetting.TABDECISIONGRAPH){
			graphType = ConfigSetting.TABDECISIONGRAPH;
			resizeGraphOnPanel(graphType,decisionDependencyGraphIcon,decisionDependencyGraphImageLabel, "decrease");
		}
	}
	void MaximiseViewToolBar(){
		buttonMaximise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maximiseView();
			}
		});
	}
	void MaximiseViewMenuBar(){
		mntmMaximise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maximiseView();
			}
		});
	}
	void maximiseView (){
		String graphType ="";
		Component currentComponent = tabbedPane.getSelectedComponent();
		if (currentComponent.getName() == ConfigSetting.TABGOALGRAPH){
			graphType = ConfigSetting.TABGOALGRAPH;
			resizeGraphOnPanel(graphType,variableGraphIcon,variableGraphImageLabel, "increase");
		}
		else if (currentComponent.getName() == ConfigSetting.TABDECISIONGRAPH){
			graphType = ConfigSetting.TABDECISIONGRAPH;
			resizeGraphOnPanel(graphType,decisionDependencyGraphIcon,decisionDependencyGraphImageLabel, "increase");
		}
	}
	
	private JMenuItem mntmMinimise;
	private JButton buttonMaximise;
	private JButton buttonMinimise;
	
	ImageIcon viewDotGraph(String dotGraph, String imageType, String graphType, JPanel graphPanel, JLabel imageLabel){
		
		GraphViz gv = new GraphViz(executable, outPutDirectory, dotGraph);
		String repesentationType= "dot";
		File out = new File(outPutDirectory+ "out"+gv.getImageDpi()+"."+ imageType); // Mac
		byte[] image = gv.getGraph(gv.getDotSource(), imageType, repesentationType);
		gv.writeGraphToFile( image, out );
		ImageIcon imageIcon = new ImageIcon(image);
		imageLabel.setIcon(imageIcon);
		JScrollPane scrollVariableImageLabel = new JScrollPane(imageLabel);
		scrollVariableImageLabel.setPreferredSize(new Dimension(850, 450));
		graphPanel.add(scrollVariableImageLabel, BorderLayout.CENTER);
		Component [] allTabbedComponent = tabbedPane.getComponents();
		for (Component comp: allTabbedComponent){
			if (graphPanel.getName().equals(comp.getName())){
				tabbedPane.remove(comp);
			}
		}
		tabbedPane.addTab(graphType,graphPanel);
		return imageIcon;
	}

	void solve (){
		try {
			// analyse model
			result = ModelSolver.solve(semanticModel);
			String analysisResult = result.analysisToString();
			String analysisResultToCSV = result.analysisResultToCSV();
			String modelResultPath = outPutDirectory + semanticModel.getModelName() + "/ICSE/AnalysisResult/" ;
			
			Helper.printResults (modelResultPath , analysisResult, semanticModel.getModelName() +".out", false);
			Helper.printResults (modelResultPath , analysisResultToCSV, semanticModel.getModelName() +".csv", false);
			
			// generate graphs
			if (true){
				createPlot(modelResultPath);
			}
			// generate graphs
			if (true){
				creatANDORGraph(modelResultPath);
			}
			if (true){
				createDecisionGraph(modelResultPath);
			}
			System.out.println("Finished!");
			modelSolved =true;
			solvedModel = ""; // textModelArea.getText();
		
		} 
		catch (IOException e) {
			modelSolved = false;
			JOptionPane.showMessageDialog(null, "There was a problem writing the results to directory. \nCheck that the path exist: ");
			return;
		}
		catch (NullPointerException e){
			modelSolved = false;
			JOptionPane.showMessageDialog(null,"There was a problem during model analysis. \nDetails:" + e.getMessage());
			return;
		}
		catch ( RuntimeException e){
			modelSolved = false;
			JOptionPane.showMessageDialog(null, "There was a problem during model analysis. \nDetails:" + e.getMessage());
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
			JOptionPane.showMessageDialog(null, "There was a problem during model analysis. \nDetails:" + e.getMessage());

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
			JOptionPane.showMessageDialog(null, "There was a problem during model analysis. \nDetails:" + e.getMessage());

		}
		return result;
	}
	private Model loadModel () throws Exception{
		Model semanticModel =null;
		try {
			nbr_Simulation = Integer.parseInt(textNbrSimulation.getText());
			subGraphObjective = textSubgraphObjective.getText();
			infoValueObjective = textInformationValueObjective.getText();
			semanticModel = new Parser().parseUIModel(modelTextPane.getText(), nbr_Simulation, infoValueObjective,subGraphObjective);
			
		}catch (RuntimeException re){
			parsed = false;
			String err = re.getMessage();
			consoleTextArea.append(err);
			consoleTextArea.setForeground(Color.RED);
			tabbedPane.setSelectedComponent(console);
			//JOptionPane.showMessageDialog(null, err);
		}
		return semanticModel;
	}
	private boolean loadExistingModel ( String modelPath){
		boolean done = false;
		File toRead = new File(modelPath);
		if(!toRead.exists()){
			JOptionPane.showMessageDialog(null, "You can not view this model example.\nThis is because the file that contains this model exist no more.\nEnsure example model files are in the same location as the downloaded 'jar'. ");
			return false;
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(toRead))) {
			modelTextPane.read(reader, "File");
        	done = true;
        } catch (IOException exp) {
        	String err = exp.getMessage();
        	consoleTextArea.append(err);
			JOptionPane.showMessageDialog(null, err);
			
        }
		return done;
	}

	private void findOutPutDirectory(){
		final JFileChooser  fileDialog = new JFileChooser();
	}
	void updateWindowItemsOnTabClosed(String clossedTab ){
		switch (clossedTab){
			case "Decisions": chckbxmntmModelDecisions.setSelected(false); break;
			case "Optimisation Analysis": chckbxmntmOptimisationAnalysis.setSelected(false); break;
			case "Information Value Analysis": chckbxmntmInformationValueAnalysis.setSelected(false); break;
			case "Goal Graph": chckbxmntmVariableAndorGraph.setSelected(false); break;
			case "Decision Graph": chckbxmntmDecisionDependencyGraph.setSelected(false); break;
			case "Pareto Front": chckbxmntmParetoFront.setSelected(false); break;
		}
	}
	void addDirectorySlash(String path, char separator){
		if (path != "" && path.trim().charAt(path.length()-1) != separator){
			path =path.trim() +"/";
			
		}
	}
	void processJarLocationPath(){
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
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		radarUtility = new Utility();
		frame = new JFrame();
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(900, 600));
		frame.setBounds(100, 100, 900, 600);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("RADAR- Requirements engineering And Architecture Decisions Analyser");
		//frame.pack();

		
		
		outPutDirectory = RADAR_GUI2.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		processJarLocationPath();
	
		undoManager = new UndoManager();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnRadar = new JMenu("RADAR");
		menuBar.add(mnRadar);
		
		mntmAboutRadar = new JMenuItem("About RADAR");
		
		mnRadar.add(mntmAboutRadar);
		
		separator_26 = new JSeparator();
		mnRadar.add(separator_26);
		
		mntmHowToUseRADAR = new JMenuItem("Manual");
		
		mnRadar.add(mntmHowToUseRADAR);
		
		separator_27 = new JSeparator();
		mnRadar.add(separator_27);
		
		mntmQuitRADAR = new JMenuItem("Quit RADAR");
		
		mnRadar.add(mntmQuitRADAR);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		newMenu = new JMenuItem("New");
		
		newMenu.setPreferredSize(new Dimension(55, 19));
		fileMenu.add(newMenu);
		
		separator_5 = new JSeparator();
		fileMenu.add(separator_5);
		
		itemOpen = new JMenuItem("Open");
		
		fileMenu.add(itemOpen);
		
		JSeparator separator_4 = new JSeparator();
		fileMenu.add(separator_4);
		
		itemSave = new JMenuItem("Save");
		
		fileMenu.add(itemSave);
		
		JSeparator separator_6 = new JSeparator();
		fileMenu.add(separator_6);
		
		itemSaveAs = new JMenuItem("Save As");
		
		fileMenu.add(itemSaveAs);
		
		JSeparator separator_10 = new JSeparator();
		fileMenu.add(separator_10);
		
		itemPrint = new JMenuItem("Print");
		
		fileMenu.add(itemPrint);
		
		separator_9 = new JSeparator();
		fileMenu.add(separator_9);
		
		mntmNewMenuItem = new JMenuItem("Export");

		fileMenu.add(mntmNewMenuItem);
		
		separator_3 = new JSeparator();
		fileMenu.add(separator_3);
		
		mnExamples = new JMenu("Examples");
		fileMenu.add(mnExamples);
		
		mntmRefactoringrdr = new JMenuItem("Cost Benefit Analysis");
		
		mnExamples.add(mntmRefactoringrdr);
		
		separator_8 = new JSeparator();
		mnExamples.add(separator_8);
		
		mntmBuildingSecurityPolicy = new JMenuItem("Building Security Policy");
		
		mnExamples.add(mntmBuildingSecurityPolicy);
		
		separator_20 = new JSeparator();
		mnExamples.add(separator_20);
		
		mntmFinancialFraudDetection = new JMenuItem("Fraud Detection System");
		
		mnExamples.add(mntmFinancialFraudDetection);
		
		separator_24 = new JSeparator();
		mnExamples.add(separator_24);
		
		mntmSituationAwarenessSystem = new JMenuItem("Situation Awareness System");
		
		mnExamples.add(mntmSituationAwarenessSystem);
		
		separator_25 = new JSeparator();
		mnExamples.add(separator_25);
		
		mntmSatelliteImageProcessing = new JMenuItem("Satellite Image Processing");
		
		mnExamples.add(mntmSatelliteImageProcessing);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
		mntmCut.setText("Cut");
		mntmCut.setMnemonic(KeyEvent.VK_CUT);
		mnEdit.add(mntmCut);
		
		JSeparator separator_15 = new JSeparator();
		mnEdit.add(separator_15);
		
		JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		mntmCopy.setText("Copy");
		mntmCopy.setMnemonic(KeyEvent.VK_C);
		mnEdit.add(mntmCopy);
		
		JSeparator separator_16 = new JSeparator();
		mnEdit.add(separator_16);
		
		JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mntmPaste.setText("Paste");
		mntmPaste.setMnemonic(KeyEvent.VK_PASTE);
		mnEdit.add(mntmPaste);
		
		JSeparator separator_17 = new JSeparator();
		mnEdit.add(separator_17);
		
		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setEnabled(false);
		
		mnEdit.add(mntmUndo);
		
		JSeparator separator_18 = new JSeparator();
		mnEdit.add(separator_18);
		
		mntmRedo = new JMenuItem("Redo");
	
		mntmRedo.setEnabled(false);
		mnEdit.add(mntmRedo);
		
		mnView = new JMenu("View");
		menuBar.add(mnView);
		
		mntmMaximise = new JMenuItem("Maximise");
		
		mnView.add(mntmMaximise);
		
		JSeparator separator_2 = new JSeparator();
		mnView.add(separator_2);
		
		mntmMinimise = new JMenuItem("Minimise");
		
		mnView.add(mntmMinimise);
		
		JMenu actionMenu = new JMenu("Action");
		menuBar.add(actionMenu);
		
		itemParseModel = new JMenuItem("Parse");
		
		
		actionMenu.add(itemParseModel);
		
		JSeparator separator_1 = new JSeparator();
		actionMenu.add(separator_1);
		
		itemSolveModel = new JMenuItem("Solve");
		
		actionMenu.add(itemSolveModel);
		
		JMenu mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);
		
		chckbxmntmOptimisationAnalysis = new JCheckBoxMenuItem("Optimisation Analysis");
		
		mnWindow.add(chckbxmntmOptimisationAnalysis);
		
		JSeparator separator_11 = new JSeparator();
		mnWindow.add(separator_11);
		
		chckbxmntmInformationValueAnalysis = new JCheckBoxMenuItem("Information Value Analysis");
		
		mnWindow.add(chckbxmntmInformationValueAnalysis);
		
		JSeparator separator_12 = new JSeparator();
		mnWindow.add(separator_12);
		
		chckbxmntmVariableAndorGraph = new JCheckBoxMenuItem("Goal Graph");
		
		mnWindow.add(chckbxmntmVariableAndorGraph);
		
		JSeparator separator_13 = new JSeparator();
		mnWindow.add(separator_13);
		
		chckbxmntmDecisionDependencyGraph = new JCheckBoxMenuItem("Decision Graph");
		
		mnWindow.add(chckbxmntmDecisionDependencyGraph);
		
		JSeparator separator_14 = new JSeparator();
		mnWindow.add(separator_14);
		
		chckbxmntmParetoFront = new JCheckBoxMenuItem("Pareto Front");
		
		mnWindow.add(chckbxmntmParetoFront);
		
		separator = new JSeparator();
		mnWindow.add(separator);
		
		chckbxmntmModelDecisions = new JCheckBoxMenuItem("Model Decisions");
		
		mnWindow.add(chckbxmntmModelDecisions);
		
		mnNewMenu = new JMenu("Optimiser Algorithm");
		menuBar.add(mnNewMenu);
		
		JCheckBoxMenuItem chckbxmntmExhaustiveSearch = new JCheckBoxMenuItem("Exhaustive Search");
		chckbxmntmExhaustiveSearch.setSelected(true);
		mnNewMenu.add(chckbxmntmExhaustiveSearch);
		
		JCheckBoxMenuItem chckbxmntmNsgaii = new JCheckBoxMenuItem("NSGAII");
		mnNewMenu.add(chckbxmntmNsgaii);
		
		JCheckBoxMenuItem chckbxmntmSpeaii = new JCheckBoxMenuItem("SPEAII");
		mnNewMenu.add(chckbxmntmSpeaii);
		
		JCheckBoxMenuItem chckbxmntmMoga = new JCheckBoxMenuItem("MOGA");
		mnNewMenu.add(chckbxmntmMoga);
		
		JCheckBoxMenuItem chckbxmntmIbea = new JCheckBoxMenuItem("IBEA");
		mnNewMenu.add(chckbxmntmIbea);
		
		JMenu mnAnalysisSettings = new JMenu("Settings");
		menuBar.add(mnAnalysisSettings);
		
		mntmAnalysisSettings = new JMenuItem("Analysis Settings");
		
		mnAnalysisSettings.add(mntmAnalysisSettings);
		
		separator_23 = new JSeparator();
		mnAnalysisSettings.add(separator_23);
		
		mntmAlgorithmSettings = new JMenuItem("Evolutionary Algorithm Settings");
		mnAnalysisSettings.add(mntmAlgorithmSettings);
		
		/*btnCut = new JButton();
		btnCut.setToolTipText("Cut");
		btnCut.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/CutFileIcon.png"));
		toolBar_1.add(btnCut);
		
		btnCopy = new JButton("");
		btnCopy.setToolTipText("Copy");
		btnCopy.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/CopyFileIcon.png"));
		toolBar_1.add(btnCopy);
		
		btnPaste = new JButton("");
		btnPaste.setToolTipText("Paste");
		btnPaste.setIcon(new ImageIcon("/Users/INTEGRALSABIOLA/Documents/JavaProject/ICSE/uk.ac.ucl.cs.icons/PasteFileIcon.png"));
		toolBar_1.add(btnPaste);*/
		
		
		decisionTableModel = new DefaultTableModel();
		
		//tabbedPane = new JTabbedPane(JTabbedPane.TOP); 
		//tabbedPane = new ClosableTabbedPane();
		tabbedPane = new ClosableTabbedPane() {

			public boolean tabAboutToClose(int tabIndex) {
				
				String tab = tabbedPane.getTabTitleAt(tabIndex);
				int choice = JOptionPane.showConfirmDialog(frame,
						"You are about to close '" + tab
								+ "'\nDo you want to proceed ?",
						"Confirmation Dialog", JOptionPane.INFORMATION_MESSAGE);
				if (choice == JOptionPane.YES_OPTION){
					updateWindowItemsOnTabClosed(tab);
				}
				return choice == 0; // if returned false tab closing will be
									// canceled
			}
		};
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		
		analysisResult = new JPanel();
		analysisResult.setName("Analysis Result");
		analysisResult.setForeground(Color.LIGHT_GRAY);
		
		analysisSettingsPanel = new JPanel();
		//tabbedPane.addTab(ConfigSetting.TABANALYSISSETTINGS, null, analysisSettingsPanel, null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), ConfigSetting.TABANALYSISSETTINGS, TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		
		JPanel panelGraphvizSettings = new JPanel();
		panelGraphvizSettings.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "GraphViz Settings", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		GroupLayout gl_analysisSettingsPanel = new GroupLayout(analysisSettingsPanel);
		gl_analysisSettingsPanel.setHorizontalGroup(
			gl_analysisSettingsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_analysisSettingsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_analysisSettingsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelGraphvizSettings, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 859, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_analysisSettingsPanel.setVerticalGroup(
			gl_analysisSettingsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_analysisSettingsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelGraphvizSettings, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel = new JLabel("System Type");
		
		rdbtnMAC = new JRadioButton("Mackintosh");
		
		rdbtnMAC.setSelected(true);
		buttonGroup.add(rdbtnMAC);
		
		rdbtnWindows = new JRadioButton("Windows");
		
		buttonGroup.add(rdbtnWindows);
		
		rdbtnLinux = new JRadioButton("Linux");
	
		buttonGroup.add(rdbtnLinux);
		
		JLabel labelGraphvizInstallationPath = new JLabel("GraphViz Executable Path");
		
		textGraphvizExecutablePath = new JTextField();
		textGraphvizExecutablePath.setToolTipText("A mandatory field for the path where the Graphviz executable is located .\n\n");
		textGraphvizExecutablePath.setText("/usr/local/bin/dot");
		textGraphvizExecutablePath.setColumns(10);
		
		rdbtnOthers = new JRadioButton("Other");
		
		buttonGroup.add(rdbtnOthers);
		
		btnBrowseExecutablePath = new JButton("Browse Executable Path");
		
		btnBrowseExecutablePath.setVisible(false);
		GroupLayout gl_panelGraphvizSettings = new GroupLayout(panelGraphvizSettings);
		gl_panelGraphvizSettings.setHorizontalGroup(
			gl_panelGraphvizSettings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGraphvizSettings.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelGraphvizSettings.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelGraphvizSettings.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(119)
							.addComponent(rdbtnMAC)
							.addGap(50)
							.addComponent(rdbtnWindows)
							.addGap(66)
							.addComponent(rdbtnLinux)
							.addGap(50)
							.addComponent(rdbtnOthers, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelGraphvizSettings.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnBrowseExecutablePath)
							.addGroup(gl_panelGraphvizSettings.createSequentialGroup()
								.addComponent(labelGraphvizInstallationPath, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textGraphvizExecutablePath, GroupLayout.PREFERRED_SIZE, 610, GroupLayout.PREFERRED_SIZE))))
					.addGap(42))
		);
		gl_panelGraphvizSettings.setVerticalGroup(
			gl_panelGraphvizSettings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGraphvizSettings.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_panelGraphvizSettings.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(rdbtnMAC)
						.addComponent(rdbtnWindows)
						.addComponent(rdbtnLinux)
						.addComponent(rdbtnOthers))
					.addGap(26)
					.addGroup(gl_panelGraphvizSettings.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelGraphvizInstallationPath)
						.addComponent(textGraphvizExecutablePath, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBrowseExecutablePath)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panelGraphvizSettings.setLayout(gl_panelGraphvizSettings);
		
		lblNbrSimulation = new JLabel("Nbr. Simulation");
		
		textNbrSimulation = new JTextField();
		textNbrSimulation.setToolTipText("A mandatory field for the number of scenarios in Monte Carlo simulation");
		textNbrSimulation.setColumns(10);
		
		JLabel lblInformationValueObjective = new JLabel("Information Value Objective");
		
		textInformationValueObjective = new JTextField();
		textInformationValueObjective.setToolTipText("An optional field that takes the objective on which the expected value of total and partial perfect information is computed.");
		textInformationValueObjective.setColumns(10);
		
		JLabel lblVariableAndorObjective = new JLabel("Variable Subgraph");
		
		textSubgraphObjective = new JTextField();
		textSubgraphObjective.setToolTipText("An optional field that takes a variable for  which AND/OR sub-graph is generated.\n");
		textSubgraphObjective.setColumns(10);
		
		lblOutputDirectory = new JLabel("Output Directory");
		
		textOutputDirectory = new JTextField();
		textOutputDirectory.setToolTipText("An optional field that takes the output directory where the optimisation results are stored. By default, it uses the path where the 'jar' file is located.\n");
		textOutputDirectory.setColumns(10);
		
		btnBrowseOutputPath = new JButton("Browse");
		
		JLabel labelDecimalPrecision = new JLabel("Decimal Precision");
		
		textFieldDecimalPrecision = new JTextField();
		textFieldDecimalPrecision.setToolTipText("An optional field that takes the number of decimal places for rounding the computed values.");
		textFieldDecimalPrecision.setText("2");
		textFieldDecimalPrecision.setColumns(10);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNbrSimulation)
									.addComponent(lblInformationValueObjective, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblVariableAndorObjective, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblOutputDirectory, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textSubgraphObjective, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(textOutputDirectory, GroupLayout.PREFERRED_SIZE, 527, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnBrowseOutputPath, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
								.addComponent(textInformationValueObjective, GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
								.addComponent(textNbrSimulation)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(labelDecimalPrecision, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldDecimalPrecision)))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textNbrSimulation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNbrSimulation))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textInformationValueObjective, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInformationValueObjective))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textSubgraphObjective, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVariableAndorObjective))
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textOutputDirectory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOutputDirectory)
						.addComponent(btnBrowseOutputPath))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelDecimalPrecision)
						.addComponent(textFieldDecimalPrecision, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		analysisSettingsPanel.setLayout(gl_analysisSettingsPanel);
		
		
		editModel = new JPanel();
		editModel.setForeground(Color.LIGHT_GRAY);
		editModel.setName(ConfigSetting.EDITORTAB);
		tabbedPane.addTab(ConfigSetting.EDITORTAB,editModel);
		//tabbedPane.setBackgroundAt(1, Color.GRAY);
		
		modelTextPane = new ModelTextPane();
		
		scrollPaneEditModel = new JScrollPane(modelTextPane);
		scrollPaneEditModel.setPreferredSize(new Dimension(850, 450));
		editModel.add(scrollPaneEditModel);
		
		console = new JPanel();
		console.setName(ConfigSetting.CONSOLETAB);
		tabbedPane.addTab(ConfigSetting.CONSOLETAB, console);
		console.setForeground(Color.LIGHT_GRAY);
		
		scrollPaneConsole = new JScrollPane();
		scrollPaneConsole.setPreferredSize(new Dimension(850, 450));
		console.add(scrollPaneConsole);
		
		consoleTextArea = new JTextArea();
		consoleTextArea.setEditable(false);
		scrollPaneConsole.setViewportView(consoleTextArea);
		console.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{scrollPaneConsole}));
		
		
		tabbedPane.addTab(ConfigSetting.TABANALYSISRESULT,analysisResult);
		
		scrollPaneOptimisationDetails = new JScrollPane();
		scrollPaneOptimisationDetails.setPreferredSize(new Dimension(850, 100));
		analysisResult.add(scrollPaneOptimisationDetails);
		
		tableOptimisationDetails = new JTable(){
			private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		scrollPaneOptimisationDetails.setViewportView(tableOptimisationDetails);
		
		scrollPaneOptimisationSolutions = new JScrollPane();
		scrollPaneOptimisationSolutions.setPreferredSize(new Dimension(850, 200));
		analysisResult.add(scrollPaneOptimisationSolutions);
		
		tableOptimisationSolutions = new JTable(){
			private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		scrollPaneOptimisationSolutions.setViewportView(tableOptimisationSolutions);
		
		scrollPaneInfoValueAnalysis = new JScrollPane();
		scrollPaneInfoValueAnalysis.setPreferredSize(new Dimension(850, 140));
		analysisResult.add(scrollPaneInfoValueAnalysis);
		
		tableInfoValueAnalysis = new JTable(){
			private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		scrollPaneInfoValueAnalysis.setViewportView(tableInfoValueAnalysis);
		tabbedPane.setBackgroundAt(2, Color.GRAY);
		
		
		JToolBar toolBar = new JToolBar();
		//http://examples.oreilly.com/jswing2/code/ch23/SimpleEditor.java
		Action a ;
		Action b ;
		Action c;
		
		a = modelTextPane.getActionMap().get(DefaultEditorKit.cutAction);
		b = modelTextPane.getActionMap().get(DefaultEditorKit.copyAction);
		c = modelTextPane.getActionMap().get(DefaultEditorKit.pasteAction);
		
		//a.putValue(Action.SMALL_ICON, new ImageIcon(RADAR_GUI2.class.getResource("/src/main/resources/uk.ac.ucl.cs.icons/CutFileIcon.png")));
		//Using this approach due to maven.
		a.putValue(Action.SMALL_ICON, new ImageIcon(getImage("uk.ac.ucl.cs.icons/CutFileIcon.png")));
		a.putValue(Action.NAME, "Cut");
		
		
		//b.putValue(Action.SMALL_ICON, new ImageIcon(RADAR_GUI2.class.getResource("/resources/CopyFileIcon.png")));
		b.putValue(Action.SMALL_ICON, new ImageIcon(getImage("uk.ac.ucl.cs.icons/CopyFileIcon.png")));
		b.putValue(Action.NAME, "Copy");
	
		
		//c.putValue(Action.SMALL_ICON, new ImageIcon(RADAR_GUI2.class.getResource("/resources/PasteFileIcon.png")));
		c.putValue(Action.SMALL_ICON, new ImageIcon(getImage("uk.ac.ucl.cs.icons/PasteFileIcon.png")));
		c.putValue(Action.NAME, "Paste");
				
		
		
		btnNewFile = new JButton("");
		btnNewFile.setToolTipText("New Model");
		//btnNewFile.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/NewFileIcon.png")));
		btnNewFile.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/NewFileIcon.png")));
		toolBar.add(btnNewFile);
		
		btnOpenFile = new JButton("");
		
		btnOpenFile.setToolTipText("Open Model");
		//btnOpenFile.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/OpenFileIcon.png")));
		btnOpenFile.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/OpenFileIcon.png")));
		toolBar.add(btnOpenFile);
		
		btnSaveFile = new JButton("");
		
		btnSaveFile.setToolTipText("Save Model");
		//btnSaveFile.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/SaveFileIcon.png")));
		btnSaveFile.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/SaveFileIcon.png")));
		toolBar.add(btnSaveFile);
		
		btnExportFile = new JButton("");
		
		btnExportFile.setToolTipText("Export Result");
		//btnExportFile.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/ExportIcon.png")));
		btnExportFile.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/ExportIcon.png")));
		toolBar.add(btnExportFile);
		
		toolBar_1 = new JToolBar();
		toolBar.add(toolBar_1);
			
			button = toolBar_1.add((Action) a);
			button.setText("");
			button.setToolTipText("Cut");
			
			button_1 = toolBar_1.add((Action) b);
			button_1.setText("");
			button_1.setToolTipText("Copy");
			
			button_2 = toolBar_1.add((Action) c);
			button_2.setText("");
			button_2.setToolTipText("Paste");
		
			
			btnUndo = new JButton("");
			btnUndo.setEnabled(false);
			
			btnUndo.setToolTipText("Undo");
			//btnUndo.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/UndoIcon.png")));
			btnUndo.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/UndoIcon.png")));
			toolBar_1.add(btnUndo);
			
			btnRedo = new JButton("");
			btnRedo.setEnabled(false);
			
			btnRedo.setToolTipText("Redo");
			//btnRedo.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/RedoIcon.png")));
			btnRedo.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/RedoIcon.png")));
			toolBar_1.add(btnRedo);
			
			buttonMaximise = new JButton("");
			buttonMaximise.setToolTipText("Maximise Image");
			
			//buttonMaximise.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/MaximiseIcon.png")));
			//buttonMaximise.setSelectedIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/MaximiseIcon.png")));
			buttonMaximise.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/MaximiseIcon.png")));
			buttonMaximise.setSelectedIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/MaximiseIcon.png")));
			
			toolBar_1.add(buttonMaximise);
			
			buttonMinimise = new JButton("");
			
			buttonMinimise.setToolTipText("Minimise Image");
			//buttonMinimise.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/MinimiseIcon.png")));
			buttonMinimise.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/MinimiseIcon.png")));
			toolBar_1.add(buttonMinimise);
			
			JToolBar toolBar_2 = new JToolBar();
			toolBar_1.add(toolBar_2);
			
			btnParse = new JButton("");
			
			btnParse.setToolTipText("Parse Model");
			//btnParse.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/ParseIcon.png")));
			btnParse.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/ParseIcon.png")));
			toolBar_2.add(btnParse);
			
			btnSolve = new JButton("");
			
			btnSolve.setToolTipText("Solve Model");
			//btnSolve.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/SolveIcon.png")));
			btnSolve.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/SolveIcon.png")));
			toolBar_2.add(btnSolve);
			
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"DEFAULT-Exhaustive Search", "NSGA-II", "SPEA-II", "MOGA", "IBEA"}));

			toolBar_2.add(comboBox);
			
			JToolBar toolBar_3 = new JToolBar();
			toolBar_2.add(toolBar_3);
			
			btnOptimisationAnalysis = new JButton("");
			
			btnOptimisationAnalysis.setToolTipText("Optimisation Analysis");
			//btnOptimisationAnalysis.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/OptimisationIcon.png")));
			btnOptimisationAnalysis.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/OptimisationIcon.png")));
			toolBar_3.add(btnOptimisationAnalysis);
			
			btnInfoValueAnalysis = new JButton("");
			
			btnInfoValueAnalysis.setToolTipText("Information value Analysis");
			//btnInfoValueAnalysis.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/InfoValueIcon.png")));
			btnInfoValueAnalysis.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/InfoValueIcon.png")));
			toolBar_3.add(btnInfoValueAnalysis);
			
			btnParetoFront = new JButton("");
			
			btnParetoFront.setToolTipText("Pareto Front");
			//btnParetoFront.setIcon(new ImageIcon(RADAR_GUI2.class.getResource("/resources/ParetoFrontIcon.png")));
			btnParetoFront.setIcon(new ImageIcon(getImage("uk.ac.ucl.cs.icons/ParetoFrontIcon.png")));
			
			toolBar_3.add(btnParetoFront);
			
			//toolBar_1.add(modelTextPane.getActionMap().get(DefaultEditorKit.cutAction)).setText("");
			//toolBar_1.add(modelTextPane.getActionMap().get(DefaultEditorKit.copyAction)).setText("");
			//toolBar_1.add(modelTextPane.getActionMap().get(DefaultEditorKit.pasteAction)).setText("");
		
		JToolBar toolFile = new JToolBar();
		toolFile.setFloatable(false);
		toolFile.setToolTipText("New File");
		
		JSeparator separator_21 = new JSeparator();
		toolFile.add(separator_21);
		
		JSeparator separator_22 = new JSeparator();
		toolFile.add(separator_22);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(toolBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(toolFile, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(toolFile, GroupLayout.PREFERRED_SIZE, 5, GroupLayout.PREFERRED_SIZE)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE)))
		);
		initialiseAnalysisSettings();
		frame.getContentPane().setLayout(groupLayout);
	}
	public static Image getImage(final String pathAndFileName) {
	    final URL url = Thread.currentThread().getContextClassLoader().getResource(pathAndFileName);
	    return Toolkit.getDefaultToolkit().getImage(url);
	}
}
