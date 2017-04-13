package uk.ac.ucl.cs.radar.userinterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ModelResultFrame extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel resultTableModel;
	private JTable solutionTable;
	private JTable optimisationAnalysisTable;
	private JTable infoValueTable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModelResultFrame frame = new ModelResultFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModelResultFrame() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane optimisationResultScrollPane = new JScrollPane();
		
		JPanel SolutionsPanel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
						.addComponent(optimisationResultScrollPane, GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
					.addContainerGap())
				.addComponent(SolutionsPanel, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(optimisationResultScrollPane, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(SolutionsPanel, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
		);
		
		infoValueTable = new JTable();
		scrollPane.setViewportView(infoValueTable);
		
		optimisationAnalysisTable = new JTable();
		optimisationResultScrollPane.setViewportView(optimisationAnalysisTable);
		
		JScrollPane scrollPaneSolutionTable = new JScrollPane();
		GroupLayout gl_SolutionsPanel = new GroupLayout(SolutionsPanel);
		gl_SolutionsPanel.setHorizontalGroup(
			gl_SolutionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SolutionsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneSolutionTable, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_SolutionsPanel.setVerticalGroup(
			gl_SolutionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_SolutionsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneSolutionTable, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		solutionTable = new JTable(){
			private static final long serialVersionUID = 1L;
	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
		};
		scrollPaneSolutionTable.setViewportView(solutionTable);
		SolutionsPanel.setLayout(gl_SolutionsPanel);
		
		resultTableModel = new DefaultTableModel();
		contentPane.setLayout(gl_contentPane);
	}
	public JTable getSolutionTable (){
		
		return solutionTable;
	}
	public JTable getInfoValueTable (){
		return infoValueTable;
	}
	public JTable getOptimisationAnalysisTable (){
		return optimisationAnalysisTable;
	}
}
