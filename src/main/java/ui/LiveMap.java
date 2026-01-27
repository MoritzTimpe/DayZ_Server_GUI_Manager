package ui;

import javax.swing.*;

import static ui.Screens.*;

public class LiveMap {
    private JPanel mainPanel;
    private JButton backButton;

    public LiveMap(Navigator navigator){
        backButton.addActionListener(e->{
            navigator.show(SERVER_MANAGER);
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
