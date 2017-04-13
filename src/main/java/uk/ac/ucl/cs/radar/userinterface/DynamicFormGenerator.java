package uk.ac.ucl.cs.radar.userinterface;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class DynamicFormGenerator {

	Map<String, String> formData;
	private JPanel formPanel;
	
	public DynamicFormGenerator(Map<String,String> formLabels){
		formData = new HashMap<String, String>();
		initialiseFormData(formLabels);
		
	}
	public void initialiseFormData(Map<String,String> formLabels){
		
		for (Map.Entry<String, String> entry:formLabels.entrySet()){
		//  formData gets label name and default values.
			formData.put(entry.getKey(), entry.getValue());
		}
		
	}
	public void initialiseFormPanel(Map<String,String> formLabels){
		int nbrOfFields = formLabels.size();
		formPanel = new JPanel ();//new GridLayout(nbrOfFields, 2,6,6)
		formPanel.setLayout(new GridBagLayout());
		formPanel.setBorder(LineBorder.createBlackLineBorder());
		for (Map.Entry<String, String> entry:formLabels.entrySet()){
			JLabel label = new JLabel (entry.getKey());
			label.setHorizontalAlignment(JLabel.TRAILING);
			formPanel.add(label);
			
			JTextField textField = new JTextField(25);
			textField.setText(entry.getValue());
			textField.putClientProperty("Textfield.For.Field", entry.getKey());
			formPanel.add(textField);
		}
	}
	
	public Map<String, String> getDataFromTextField (){
		for (Component component : formPanel.getComponents()){
			if (component instanceof JTextField){
				JTextField textField = (JTextField)component;
				String key = (String)textField.getClientProperty("Textfield.For.Field");
				String value = textField.getText().trim();
				formData.put(key, value);
			}
		}
		return formData;
	}
	public JPanel getFormPanel(){
		return formPanel;
	}
	
	
	
	
	
}
