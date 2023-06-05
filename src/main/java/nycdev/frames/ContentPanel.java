package nycdev.frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ContentPanel extends JPanel {
    private final Image background;

    ContentPanel()  {
        try {
            background = ImageIO.read(new File("src/main/resources/assets/BackGroundPersonalLibrary.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background,0,0, this);
    }
}
