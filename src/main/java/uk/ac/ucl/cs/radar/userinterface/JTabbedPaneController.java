package uk.ac.ucl.cs.radar.userinterface;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JTabbedPaneController {

	JTabbedPane tabbedPane;
	public JTabbedPaneController (JTabbedPane pane){
		tabbedPane = pane;
	}
	
	void addPanelToTab (JPanel panel, String title){
		tabbedPane.addTab(title, panel);
	}
	void removePanelFromTab (int index){
		if (index!=-1){
			tabbedPane.remove(index);
		}
	}
	
}
