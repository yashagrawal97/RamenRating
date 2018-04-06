package com.yash.ramenrating;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.*;

class Plot extends JFrame {

    private final int WIDTH = 1000;
    private final int HEIGHT = 1000;
    private Container drawable;
    private JPanel canvas;
    private int points;

    public Plot(int points) {

        super("Plot");
        this.points=points;
        drawable = getContentPane();
        canvas = new GraphCanvas();
        drawable.add(canvas);
        setSize(WIDTH, HEIGHT);
    }

    public class GraphCanvas extends JPanel {
        private String[] data;
        private double[] XData;
        private double[] YData;
        public GraphCanvas() {
            super();
            BufferedReader reader;
            String line;
            XData = new double[points];
            YData = new double[points];
            int loop=0;
            try {
                reader = new BufferedReader(new FileReader("inputdata.csv"));
                line = reader.readLine();
                data = line.split(",");
                line = reader.readLine();
                while (line != null)
                {
                    data = line.split(",");
                    XData[loop]=Double.parseDouble(data[0]);
                    YData[loop]=Double.parseDouble(data[5]);
                    loop=loop+1;
                    line = reader.readLine();
                }
            }
            catch (Exception f)
            {
                System.out.println(f.getMessage());
            }
        }
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
//            System.out.println("dafg"+points);
            for(int i = 0; i < points - 1; i++) {
                int x0 = (int) (XData[i]);
                int x1 = (int) (XData[i + 1]);
                int y0 = (int) (YData[i]*20);
                int y1 = (int) (YData[i + 1]*20);
               // System.out.println(x0+" "+y0);
//                g2.scale(5,5);
                g2.drawLine(0,0,1000,0);
                g2.drawLine(0,0,0,1000);
                g2.drawLine(x0, y0, x1, y1);
                if (i == 0)
                    g2.drawString(("" + x0 + ", " + (double)y0/20), x0 - 20, y0 + 10);
                if (i == points - 2)
                    g2.drawString(("" + x1 + ", " + (double)y1/20), x1, y1);
            }
        }
    }
}
