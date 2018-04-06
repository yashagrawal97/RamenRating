package com.yash.ramenrating;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Pie
{
    public Pie()
    {
        JFrame frame = new JFrame();
        frame.setTitle("Ramen Styles Pie Chart");
        frame.getContentPane().add(new MyComponent());
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}

class Slice
{
    double value;
    Color color;
    String name;
    public Slice(double value, Color color, String name)
    {
        this.value = value;
        this.color = color;
        this.name=name;
    }
}
class MyComponent extends JComponent
{

    public void paint(Graphics g)
    {
        String[] data;
        ArrayList <Slice> slices=new ArrayList<>();
        BufferedReader reader;
        String line;
        int loop=0,flag=0;
        float h=0.1F,s=0.3F,b=0.6F;
        try {
            reader = new BufferedReader(new FileReader("inputdata.csv"));
            line = reader.readLine();
            data = line.split(",");
            line = reader.readLine();
            while (line != null)
            {
                data = line.split(",");
                for(Slice x:slices)
                {
                    if(x==null) break;
                    if(data[3].equals(x.name))
                    {
                        x.value++;
                        flag=1;
                        break;
                    }
                }
                if(flag==0)
                {
                    slices.add(loop,new Slice(1, Color.getHSBColor(h,s,b), data[3]));
                    h+=0.11;
                    s+=0.08;
                    b+=0.07;
                    loop = loop + 1;
                }
                line = reader.readLine();
                flag=0;
            }
        }
        catch (Exception f)
        {
            System.out.println(f.toString());
        }
        drawPie((Graphics2D) g, getBounds(), slices);
    }

    void drawPie(Graphics2D g, Rectangle area, ArrayList<Slice> slices)
    {
        double total = 0.0D;

        for (Slice x:slices)
        {
            if(x==null) break;
            total += x.value;
        }
        double curValue = 0.0D;
        int startAngle = 0;
        int pos=0;
        g.setFont(new Font("TimesRoman", Font.BOLD,20));
        for(Slice x:slices)
        {
            if(x==null) break;
            if(x.name.equals("")) break;
            startAngle = (int) (curValue * 360 / total);
            int arcAngle = (int) (x.value * 360 / total);
            g.setColor(x.color);
            g.fillArc(area.x+120, area.y, area.width-120, area.height, startAngle, arcAngle);
            if(arcAngle<1)
                g.drawString(x.name + "(Nominal)",10,100+pos);
            else
                g.drawString(x.name,10,100+pos);

            curValue += x.value;
            pos+=30;
        }
    }
}