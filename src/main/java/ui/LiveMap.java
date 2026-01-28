package ui;

import javax.swing.*;

import java.util.HashMap;
import java.util.Properties;

import static ui.Screens.*;

public class LiveMap {
    private JPanel mainPanel;
    private JButton backButton;

    public LiveMap(Navigator navigator, Properties properties){
        backButton.addActionListener(e->{
            navigator.show(SERVER_MANAGER);
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
