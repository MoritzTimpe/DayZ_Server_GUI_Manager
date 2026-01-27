package ui;

import javax.swing.*;

public class ModManager {
    private JScrollPane serverSideModListPane;
    private JScrollPane clientSideModListPane;
    private JList serverSideModList;
    private JList clientSideModList;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton installButton;
    private JButton updateButton;
    private JButton removeButton;
    private JButton validateButton;
    private JButton openOnServerButton;
    private JButton openOnClientButton;

    public ModManager(Navigator navigator){
        backButton.addActionListener(e ->{
            navigator.show("ServerManager");
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
