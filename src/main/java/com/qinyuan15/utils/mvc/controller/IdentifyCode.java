package com.qinyuan15.utils.mvc.controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IdentifyCode {
    private final static int CODE_LENGTH = 4;
    private final static int WIDTH = 100;
    private final static int HEIGHT = 40;

    // the chars that can be shown in identify code
    private final static char mapTable[] = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9'};

    private IdentifyCode() {
    }

    /**
     * this method create a identifying code picture, show it to output stream,
     * then return the identifying code string
     *
     * @param response HttpServletResponse instance
     * @return identity code
     * @throws IOException
     */
    public static String getPicture(HttpServletResponse response)
            throws IOException {
        OutputStream os = response.getOutputStream();

        // create BufferedImage and set the graphics
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        // create a string as the identifying code randomly
        Random rand = new Random();
        String codeStr = "";
        for (int i = 0; i < CODE_LENGTH; i++) {
            codeStr += mapTable[rand.nextInt(mapTable.length)];
        }

        // draw the identifying code string
        g.setFont(new Font("Consolas", Font.ITALIC, 28));
        for (int i = 0; i < CODE_LENGTH; i++) {
            String str = codeStr.substring(i, i + 1);
            g.drawString(str, i * 22 + 5, 20 + rand.nextInt(17));
        }

        // create 10 interfering points
        for (int i = 0; i < 20; i++) {
            int x = rand.nextInt(WIDTH);
            int y = rand.nextInt(HEIGHT);
            g.drawOval(x, y, 2, 2);
            x = rand.nextInt(WIDTH);
            y = rand.nextInt(HEIGHT);
            g.draw3DRect(x, y, 2, 2, true);
        }

        g.dispose();
        try {
            ImageIO.write(image, "JPEG", os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeStr;
    }

    private final static Set<String> blackList = new HashSet<>();

    public static void remoteBlackList(String remoteAddress) {
        blackList.remove(remoteAddress);
    }

    public static void addBlackList(String remoteAddress) {
        blackList.add(remoteAddress);
    }

    public static boolean inBlacklist(String remoteAddress) {
        return blackList.contains(remoteAddress);
    }
}
