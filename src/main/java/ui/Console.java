package ui;

import javax.swing.*;

public class Console {
    private JTextArea consoleTextArea;
    private JPanel mainPanel;
    private JButton stopButton;
    private JButton backButton;

    public Console(Navigator navigator){
        backButton.addActionListener(e ->{
            navigator.show("ServerManager");
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
