package ui;

import javax.swing.*;
import java.util.HashMap;
import java.util.Properties;

public class Console {
    private JTextArea consoleTextArea;
    private JPanel mainPanel;
    private JButton stopButton;
    private JButton backButton;

    public Console(Navigator navigator, Properties properties){
        backButton.addActionListener(e ->{
            navigator.show("ServerManager");
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
