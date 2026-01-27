import ui.*;

import javax.swing.*;
import java.awt.*;

import static ui.Screens.*;

public class Executor {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            CardLayout layout = new CardLayout();
            JPanel window = new JPanel(layout);

            Navigator navigator = screenName -> layout.show(window, screenName);

            ModManager modManager = new ModManager(navigator);
            ServerManager serverManager = new ServerManager(navigator);
            Preferences preferences = new Preferences(navigator);
            Console console = new Console(navigator);
            LiveMap liveMap = new LiveMap(navigator);

            window.add(serverManager.getMainPanel(), SERVER_MANAGER);
            window.add(modManager.getMainPanel(), MOD_MANAGER);
            window.add(preferences.getMainPanel(), PREFERENCES);
            window.add(console.getMainPanel(), CONSOLE);
            window.add(liveMap.getMainPanel(), LIVE_MAP);

            JFrame frame = new JFrame("Server Tool");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(window);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            navigator.show(SERVER_MANAGER);
        });
    }
}
