package uk.ac.ucl.cs.radar.userinterface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TabbedPaneSelection extends JPanel implements ChangeListener {
    public TabbedPaneSelection() {
        initializeUI();
    }

    public static void showFrame() {
        JPanel panel = new TabbedPaneSelection();
        panel.setOpaque(true);

        JFrame frame = new JFrame("JTabbedPane Selection Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TabbedPaneSelection.showFrame();
            }
        });
    }

    private void initializeUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel dashboardPanel = new JPanel();
        tabbedPane.addTab("Dashboard", dashboardPanel);

        JPanel accountPanel = new JPanel();
        tabbedPane.addTab("Account", accountPanel);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 200));
        this.add(tabbedPane, BorderLayout.CENTER);

        //
        // Add ChangeListener to the tabbed pane.
        //
        tabbedPane.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        int selectedIndex = tabbedPane.getSelectedIndex();
        JOptionPane.showMessageDialog(null, "Selected Index: " + selectedIndex);
    }
}