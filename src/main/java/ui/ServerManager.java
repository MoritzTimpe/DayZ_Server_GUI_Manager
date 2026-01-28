package ui;

import javax.swing.*;

import java.util.HashMap;
import java.util.Properties;

import static ui.Screens.*;

public class ServerManager {
    private JButton modsManagerButton;
    private JPanel mainPanel;
    private JButton preferencesButton;
    private JButton consoleButton;
    private JButton liveMapButton;

    public ServerManager(Navigator navigator, Properties properties){
        consoleButton.addActionListener(e -> {
            navigator.show(CONSOLE);
        });

        liveMapButton.addActionListener(e -> {
            navigator.show(LIVE_MAP);
        });

        modsManagerButton.addActionListener(e -> {
            navigator.show(MOD_MANAGER);
        });

        preferencesButton.addActionListener(e -> {
            navigator.show(PREFERENCES);
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
