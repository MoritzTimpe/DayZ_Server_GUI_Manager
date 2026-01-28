import ui.*;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

import static ui.Screens.*;

public class Executor {

    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Executor().startApp());
    }

    public void startApp() {
        CardLayout layout = new CardLayout();
        JPanel window = new JPanel(layout);

        Navigator navigator = screenName -> layout.show(window, screenName);

        // restart callback: dispose current frame + rebuild everything
        Runnable restartApp = () -> {
            if (frame != null) frame.dispose();
            SwingUtilities.invokeLater(() -> new Executor().startApp());
        };

        Preferences preferences = new Preferences(navigator, restartApp);

        Properties properties = preferences.getProperties();

        ModManager modManager = new ModManager(navigator, properties);
        ServerManager serverManager = new ServerManager(navigator, properties);
        Console console = new Console(navigator, properties);
        LiveMap liveMap = new LiveMap(navigator, properties);

        window.add(serverManager.getMainPanel(), SERVER_MANAGER);
        window.add(modManager.getMainPanel(), MOD_MANAGER);
        window.add(preferences.getMainPanel(), PREFERENCES);
        window.add(console.getMainPanel(), CONSOLE);
        window.add(liveMap.getMainPanel(), LIVE_MAP);

        frame = new JFrame("Server Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(window);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        navigator.show(SERVER_MANAGER);
    }
}
