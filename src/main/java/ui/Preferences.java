package ui;

import javax.swing.*;

import java.awt.event.ActionEvent;

import static ui.Screens.*;

public class Preferences {
    private JPanel mainPanel;
    private JTextField gamePathTextField;
    private JButton backButton;
    private JButton browseGamePathButton;
    private JButton browseServerPathButton;
    private JTextField serverPathTextField;

    public Preferences(Navigator navigator){
        backButton.addActionListener(e -> {
            navigator.show(SERVER_MANAGER);
        });

        browseGamePathButton.addActionListener(this::openDialogPathSelector);
        browseServerPathButton.addActionListener(this::openDialogPathSelector);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void openDialogPathSelector(ActionEvent e) {
        JFrame parent = new JFrame("");
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        int result = chooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            if(e.getSource() == browseGamePathButton){
                gamePathTextField.setText(chooser.getSelectedFile().toString());
            } else if (e.getSource() == browseServerPathButton) {
                serverPathTextField.setText(chooser.getSelectedFile().toString());
            }
        }
    }
}
