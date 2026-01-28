package ui;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

public class ModManager {
    private JScrollPane serverSideModListPane;
    private JScrollPane clientSideModListPane;
    private JList serverSideModList;
    private JList localSideModList;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton installButton;
    private JButton updateButton;
    private JButton removeButton;
    private JButton validateButton;
    private JButton openOnServerButton;
    private JButton openOnClientButton;
    private JButton reloadButton;

    private Path localModDir;
    private Path serverModDir;



    public ModManager(Navigator navigator, Properties properties){
        localModDir = Paths.get(properties.getProperty("GamePath")).resolve("!Workshop");
        serverModDir = Paths.get(properties.getProperty("ServerPath"));
        loadModsFromPath(localModDir, localSideModList, properties);
        loadModsFromPath(serverModDir, serverSideModList, properties);

        backButton.addActionListener(e ->{
            navigator.show("ServerManager");
        });
    }

    private void loadModsFromPath(Path modsPath, JList list, Properties properties) {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        try (Stream<Path> paths = Files.list(modsPath)) {
            paths
                    .filter(Files::isDirectory)
                    .map(path -> path.getFileName().toString())
                    .filter(name -> name.startsWith("@"))
                    .map(name -> name.substring(1)) // remove '@'
                    .forEach(listModel::addElement);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        list.setModel(listModel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
