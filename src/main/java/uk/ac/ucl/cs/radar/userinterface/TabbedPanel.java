package uk.ac.ucl.cs.radar.userinterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TabbedPanel extends JFrame {
	private ClosableTabbedPane tabbedPane;

	public TabbedPanel() {
		addTabbedPane();
		addMenu();
		setVisible(true);
		//setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void addMenu() {
		// Create menu for adding tabs
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Add Tab");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.addTab("TAB " + (tabbedPane.getTabCount() + 1),
						new JPanel());
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}

	private void addTabbedPane() {
		// Create ClosableTabbedPane and override the tabAboutToClose
		// to be notified when certain tab is going to close.
		tabbedPane = new ClosableTabbedPane() {

			public boolean tabAboutToClose(int tabIndex) {
				String tab = tabbedPane.getTabTitleAt(tabIndex);
				int choice = JOptionPane.showConfirmDialog(null,
						"You are about to close '" + tab
								+ "'\nDo you want to proceed ?",
						"Confirmation Dialog", JOptionPane.INFORMATION_MESSAGE);
				return choice == 0; // if returned false tab closing will be
									// canceled
			}
		};
		//tabbedPane = new ClosableTabbedPane();
		getContentPane().add(tabbedPane);
	}

	public static void main(String[] args) {
		new TabbedPanel();
	}

}
