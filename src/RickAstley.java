import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class RickAstley extends AnAction {
    public RickAstley() {
        super();
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {
            displayGif();
        } catch (IOException e) {
            e.printStackTrace();
        }

        playSound();
    }

    public static synchronized void playSound() {
        new Thread(new Runnable() {
            public void run() {
                InputStream fis = this.getClass().getClassLoader().getResourceAsStream("Rick Astley - Never Gonna Give You Up.mp3");
                Player playMP3 = null;
                try {
                    assert fis != null;
                    playMP3 = new Player(fis);
                    playMP3.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void displayGif() throws IOException {
        URL url = getClass().getResource("rickastley.gif");
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);


        JFrame f = new JFrame("Animation");
        f.getContentPane().add(label);
        f.setTitle("Never Gonna Give You Up");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
