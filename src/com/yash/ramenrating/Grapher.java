package com.yash.ramenrating;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class Grapher extends Frame implements WindowListener, ActionListener
{
    Button b1,b2,b3,b4;
    TextArea l1;
    TextField t1;
    double mean=0;
    int i=0;
    String[] cols;

    public Grapher()
    {
        initComponents();
    }

    public void initComponents()
    {
        setLayout(new GridBagLayout());
        l1=new TextArea();
        add(l1);
        b1=new Button("Get Data");
        add(b1);
        b2=new Button("Get Mean Star Rating");
        add(b2);
        t1=new TextField("",10);
        t1.setBounds(200,200,50,20);
        add(t1);
        b3=new Button("Plot");
        add(b3);
        b4=new Button("Pie Chart");
        add(b4);
        this.setSize(1200,1000);
        this.setVisible(true);
        addWindowListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b4)
        {
            Pie p= new Pie();

        }
        else if(e.getSource()==b1)
        {
            BufferedReader reader;
            String line;
            try {

                l1.setText("");
                reader = new BufferedReader(new FileReader("inputdata.csv"));
                line = reader.readLine();
                cols = line.split(",");
                l1.append(cols[0] + "\t" + cols[5]);
                l1.append("\n");
                line = reader.readLine();
                while (line != null)
                {
                    cols = line.split(",");
                    mean = mean + Double.parseDouble(cols[5]);
                    i++;
                    l1.append(cols[0] + "\t" + cols[5]);
                    l1.append("\n");
                    line = reader.readLine();
                }
                mean=mean/i;
            } catch (Exception f) {
                l1.append("Exception thrown");
            }
        }

        else if(e.getSource()==b2)
        {
            t1.setText(String.valueOf(mean));
        }
        else if(e.getSource()==b3)
        {
            Frame f = new Plot(i);
            f.setVisible(true);
        }
    }

    public void windowOpened(WindowEvent e){setTitle("Welcome");}
    public void windowClosing(WindowEvent e){System.exit(0);}
    public void windowClosed(WindowEvent e){}
    public void windowIconified(WindowEvent e){setTitle("STOP");}
    public void windowDeiconified(WindowEvent e){setTitle("START");}
    public void windowActivated(WindowEvent e){setBackground(Color.lightGray);}
    public void windowDeactivated(WindowEvent e){setBackground(Color.gray);}
}
