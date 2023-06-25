package org.example;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Pull1 extends JFrame {
    Image img[] = new Image[10];
    private  Panel panel = new Panel();

    private MediaTracker tracker = null;
    private ArrayList<String> list = new ArrayList<String>();
    private JButton button;
    DigitPanel[] dpanel = new DigitPanel[1];


    public Pull1(ArrayList<String> list1) {
        ImageIcon icon = new ImageIcon("pull_start.png");
        ImageIcon icon1 = new ImageIcon("pull_end.png");
        ImageIcon icon2 = new ImageIcon("machine.png");
        ImageIcon home = new ImageIcon("home.png");
        ImageIcon home_H = new ImageIcon("home_hover.png");
        this.setContentPane(new Mypanel());
        list = list1;
        this.setTitle("DrawMain");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        //this.setLayout(new BorderLayout());
        this.setLayout(null);

        //panel.setSize(600,300);
        panel.setBounds(160,142,450,240);
        panel.setLayout(new GridLayout(1, 3));
        for (int i = 0; i < 1; i++) {
            dpanel[i] = new DigitPanel();
            dpanel[i].runnable = false;
            panel.add(dpanel[i]);
        }


        //go home
        JButton last_page = new JButton(home);
        last_page.setContentAreaFilled(false);
        last_page.setBorderPainted(false); // 設定邊框不可見
        last_page.setOpaque(false);
        last_page.setFocusPainted(false);
        //button.setSize(199,430);
        last_page.setPreferredSize(new Dimension(home.getIconWidth(), home.getIconHeight()));
        last_page.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                last_page.setIcon(home_H);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                last_page.setIcon(home);
            }
        });
        last_page.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pull1.this.setVisible(false);
                Swing2 swing2 = new Swing2();
                swing2.setVisible(true);

            }
        });

        last_page.setBounds(30, 330, 50, 50);
        this.getContentPane().add(last_page);
        //JButton button = new JButton(icon);
        button = new JButton(icon);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false); // 設定邊框不可見
        button.setOpaque(false);
        button.setFocusPainted(false);
        //button.setSize(199,430);
        button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));

        button.addActionListener(new ActionListener() {
            int idx = 0;

            public void actionPerformed(ActionEvent e) {

                dpanel[idx].showNumber = (int) (Math.random() * list.size());
                dpanel[idx].digitString = String.valueOf(dpanel[idx].showNumber);
                dpanel[idx].count = 3;
                dpanel[idx].runnable = true;
                button.setBounds(670,275,80,160);
                button.setIcon(icon1);

                //idx = (idx + 1) % 1;
            }
        });
        this.add(button);

        button.setBounds(670,180,80,160);
        this.add(panel, BorderLayout.CENTER);



        this.setVisible(true);
        pack();
        this.setResizable(true);
        this.setSize(800, 600);
        this.setResizable(false);
    }

    public class DigitPanel extends JPanel {
        int showNumber;
        String digitString;
        int[] X = new int[list.size()];
        int[] Y = new int[list.size()];
        boolean runnable;
        int count;
        Timer timer = new Timer();

        public DigitPanel() {

            for (int i = 0; i < list.size(); i++) {
                int k=0;
                for(int n=0;n<list.get(i).length()-1;n++){
                    if(Character.isDigit(list.get(i).charAt(n))||list.get(i).charAt(0)<='z'){
                        k=1;
                        break;
                    }
                }
                if(list.get(i).length()>8){
                    if(list.get(i).length()>13){
                        if(k==1){
                            X[i] = 225 - (list.get(i).length()*3);
                            if(k>15){
                                X[i] = 225 - (list.get(i).length());
                            }
                        }else{
                            X[i] = 225 - ((list.get(i).length()+4)*8);
                        }
                    }else{
                        if(k==1){
                            X[i] = 225 - (list.get(i).length()*(list.get(i).length()-(list.get(i).length()+2)/2));
                        }else{
                            X[i] = 225 - ((list.get(i).length()+2)*8);

                        }

                    }

                }else{
                    if(k==1){
                        X[i] = 225 - ((list.get(i).length()+2)*8);
                    }else{
                        X[i] = 225 - ((list.get(i).length()+2)*12);
                    }

                }

                Y[i] = (list.size() - i) * 200+100;
            }
            timer.schedule(new Heart(), 1000,10);
            runnable = true;
        }

        public class Heart extends TimerTask {
            public void run() {
                if (runnable == false)
                    return;
                for (int i = 0; i < list.size(); i++) {
                    Y[i] = Y[i] + 25;
                    Y[i] %= 200 * list.size();

                }
                repaint();
            }
        }

        public void setShowNumber(int x) {
            this.showNumber = x;
        }

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon icon = new ImageIcon("pull_start.png");
            super.paintComponent(g);
            Point2D start = new Point2D.Float(this.getWidth(), 0);
            Point2D end = new Point2D.Float(this.getWidth(), this.getHeight());
            float[] dist = { 0.0f, 0.40f, 0.80f, 1.0f };
            //Color c1 = Color.white, c2 = Color.WHITE, c3 = Color.WHITE, c4 = Color.white;
            //Color[] colors = { c1, c2, c3, c4 };
            //LinearGradientPaint p = new LinearGradientPaint(start, end, dist, colors);
            Graphics2D g2d = (Graphics2D) g;
           // g2d.setPaint(p);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            int pcount = count;
            for (int i = 0; i < list.size(); i++) {
                //String digit = String.valueOf(i);
                String digit=list.get(i);
                int x = X[i];
                int y = Y[i];
                g.setColor(Color.yellow);
                if(digit.length()>8){
                    if(digit.length()>11){
                        g.setFont(new Font("微軟正黑體",Font.PLAIN,22));
                        if(digit.length()>15){
                            g.setFont(new Font("微軟正黑體",Font.PLAIN,22));
                        }
                    }else{
                        g.setFont(new Font("微軟正黑體",Font.PLAIN,25));
                    }

                }else{
                    g.setFont(new Font("微軟正黑體",Font.PLAIN,30));
                }
                g.drawString(digit, x, y);
                if (y == 125 && i == showNumber) {
                    count--;
                    if (count == 0) {
                        runnable = false;
                        button.setIcon(icon);
                        button.setBounds(670,180,80,160);
                    }
                }
            }
          //  for()
            if (pcount != count) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new Heart(), 50, 5 * (5 - count));
            }
        }
    }

    public static void main(String[] args) {
       new Pull1(new ArrayList<String>());
    }
}
