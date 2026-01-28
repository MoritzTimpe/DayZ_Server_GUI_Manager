package ui;

import com.sun.tools.javac.Main;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ui.Screens.*;

public class Preferences {
    private final Path workingDirectory;
    private File configFile;
    private JPanel mainPanel;
    private JTextField gamePathTextField;
    private JButton backButton;
    private JButton browseGamePathButton;
    private JButton browseServerPathButton;
    private JTextField serverPathTextField;
    private JButton saveButton;

    private Properties properties = new Properties();

    public Preferences(Navigator navigator, Runnable restartApp){

        this.workingDirectory = Paths.get(System.getProperty("user.dir"));
        this.configFile = this.workingDirectory.resolve( "server.properties").toFile();
        this.properties = loadConfig();

        gamePathTextField.setText(this.properties.getProperty("GamePath"));
        serverPathTextField.setText(this.properties.getProperty("ServerPath"));

        backButton.addActionListener(e -> {
            navigator.show(SERVER_MANAGER);
        });

        saveButton.addActionListener(e -> {
            if (Files.notExists(configFile.toPath())) {
                try {
                    Files.createFile(configFile.toPath());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            properties = saveConfig();

            System.out.println("Restarting....");

            restartApp.run();

        });

        browseGamePathButton.addActionListener(this::openDialogPathSelector);
        browseServerPathButton.addActionListener(this::openDialogPathSelector);
    }

    private Properties loadConfig() {
        Properties props = new Properties();

        Path path = configFile.toPath();
        if (!Files.exists(path)) {
            return props; // empty properties if file doesn't exist
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            props.load(reader);
        } catch (IOException ex) {
            throw new UncheckedIOException("Failed to load config file", ex);
        }

        return props;
    }

    private Properties saveConfig() {
        Properties props = new Properties();

        props.setProperty("GamePath", gamePathTextField.getText().trim());
        props.setProperty("ServerPath", serverPathTextField.getText().trim());

        try (BufferedWriter writer = Files.newBufferedWriter(
                configFile.toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        )) {
            props.store(writer, "CONFIG");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    mainPanel,
                    "Failed to save configuration:\n" + ex.getMessage(),
                    "Save Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return props;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Properties getProperties() {
        return properties;
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
