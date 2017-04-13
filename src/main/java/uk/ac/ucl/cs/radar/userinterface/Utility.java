package uk.ac.ucl.cs.radar.userinterface;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextPane;

import uk.ac.ucl.cs.radar.model.Model;
import uk.ac.ucl.cs.radar.model.Parser;

public class Utility {

	boolean isModelParsed;
	boolean isModelSolved;
	String theSolvedModel;
	JTextPane modelTextPane;
	Model semanticModel;
	private JMenuItem itemParseModel;
	private void parse(JTextPane modelTextPane){
		try {
			Model semanticModel = loadModel(modelTextPane);
			if (semanticModel != null){
				isModelParsed =true;
			}
		} catch (Exception e) {
			isModelParsed =false;
			String err = e.getMessage();
			JOptionPane.showMessageDialog(null, err);
		}
	}
	static void solve(){
		
	}
	private boolean doesOutputDirectoryExist(){
		boolean result =true;
		try{
			InputValidator.validateOutputPath(""); //textOutputDirectory.getText());
		}catch (Exception e){
			result = false;
			
		}
		return result;
	}
	void loadResultInFrame(){
		
	}
	void populateDecisionTable(){
		
	}
	Model getSemanticModel(){
		return semanticModel;
	}
	String getSolvedModel(){
		return theSolvedModel;
	}
	boolean isModelSolved(){
		return isModelSolved;
	}
	boolean isModelParsed(){
		return isModelParsed;
	}
	public Model loadModel (JTextPane modelTextPane) throws Exception{
		Model model =null;
		try {
			model = new Parser().parseUIModel(modelTextPane.getText(), 10000, "","");
		}catch (RuntimeException re){
			isModelParsed = false;
			String err = re.getMessage();
			JOptionPane.showMessageDialog(null, err);
		}
		semanticModel = model;
		return model;
	}
	public void parseModel (JTextPane modelTextPane, boolean modelParsed, boolean modelSolved, String solvedModel ){
		parse(modelTextPane);
		if (modelParsed ==true){
			populateDecisionTable();
			int selection = JOptionPane.showConfirmDialog( null , "Model has been parsed successfully. Do you want to continue with analysis?" , "Confirmation "
                    , JOptionPane.OK_CANCEL_OPTION , JOptionPane.INFORMATION_MESSAGE);
			if (selection == JOptionPane.OK_OPTION)
            {
				solve();
				if (modelSolved){
					loadResultInFrame();
					solvedModel = modelTextPane.getText();
				}
            }
			modelParsed= true;
		}
		isModelParsed = modelParsed;
		isModelSolved = modelSolved;
		theSolvedModel =solvedModel;
	
	}
	public void solveModel(Model semanticModel, JTextPane modelTextPane, String solvedModel, boolean modelSolved, boolean modelParsed ){
		// also check that there is no changes to the model, otherwise, we parse again.
		if (solvedModel != null){
			if ( !solvedModel.equals(modelTextPane.getText())){
				parse(modelTextPane);
			}
		}else{
			parse(modelTextPane);
		}
		if (semanticModel != null && modelParsed == true) {
			solve();
		}else{
			parseModel(modelTextPane,modelParsed,modelSolved, solvedModel);
			if (modelParsed == true){
				solve();
			}
		}
		if (modelSolved){
			populateDecisionTable();
			loadResultInFrame();
		}
		isModelParsed = modelParsed;
		isModelSolved = modelSolved;
		theSolvedModel =solvedModel;
	}
}
