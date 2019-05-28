package com.intuit.cardgame.util;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;

/**<p>
 * Simple ASCII Art Generator
 * <br> For example to display cool titles !
 *</p>
 * @author mnajar
 */
@Component
public class ASCIIArt {

    public void drawString(String text){
        Settings settings = new Settings(new Font("SansSerif", Font.BOLD, 12), text.length() * 15, 15); // 30 pixel width per character
        drawString(text, "*", settings);
    }

    public void drawString(String text, String artChar, Settings settings) {
        BufferedImage image = getImageIntegerMode(settings.width, settings.height);

        Graphics2D graphics2D = getGraphics2D(image.getGraphics(), settings);
        graphics2D.drawString(text, 6, ((int) (settings.height * 0.67)));

        for (int y = 0; y < settings.height; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < settings.width; x++) {
                stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : artChar);
            }

            if (stringBuilder.toString()
                    .trim()
                    .isEmpty()) {
                continue;
            }

            System.out.println(stringBuilder);
        }

    }

    private BufferedImage getImageIntegerMode(int width, int height) {
        return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private Graphics2D getGraphics2D(Graphics graphics, Settings settings) {
        graphics.setFont(settings.font);

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return graphics2D;
    }

    public class Settings {
        public Font font;
        public int width;
        public int height;

        public Settings(Font font, int width, int height) {
            this.font = font;
            this.width = width;
            this.height = height;
        }
    }

}
