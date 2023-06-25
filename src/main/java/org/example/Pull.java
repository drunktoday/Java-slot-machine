package org.example;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;

public class Pull extends JFrame {
    Image img[] = new Image[22];
    private ArrayList<String> imgNames = new ArrayList<>(Arrays.asList("拉麵1.jpg", "咖哩.jpg", "丼飯.jpg", "粥.jpg", "義大利麵.jpg", "炸雞.jpg", "pizza(new).jpg", "鹹水雞.jpg", "滷味.jpg", "壽司3.jpg", "湯包1.jpg", "中式料理.jpg", "漢堡.jpg", "素食.jpg", "便當.jpg", "壽喜燒.jpg", "燒肉1.jpg", "水餃.jpg", "麵包1.jpg", "鍋燒.jpg"));
    private ArrayList<String> indexNames = new ArrayList<>(Arrays.asList("拉麵", "咖哩", "飯", "粥", "義式料理", "炸物", "pizza", "鹽水雞", "滷味", "日式料理", "港式料理", "中式料理", "速食店", "素食",  "便當", "火鍋", "燒烤", "水餃", "麵包", "鍋燒"));
    private String type_result;
    private String address_result;
    private MediaTracker tracker = null;
    private JButton button;
    private  JPanel panel;

    public int check;

    DigitPanel[] dpanel = new DigitPanel[3];
    public String get_type(){
        return type_result;
    }
    public String get_address(){
        return address_result;

    }
    public ArrayList<String> get_image(){
        return imgNames;
    }
    public ArrayList<String> get_index(){
        return indexNames;
    }
    public int getCheck(){
        return check;
    }
    public void loadImages() {
        tracker = new MediaTracker(this);
        try {
            for (int i = 0; i < imgNames.size(); i++) {
                img[i] = java.awt.Toolkit.getDefaultToolkit().getImage(
                        imgNames.get(i));
                tracker.addImage(img[i], i);
            }
        } catch (Exception e) {
            System.err.println("Error loading image");
        }

        try {
            tracker.waitForAll();
        } catch (Exception e) {
            System.err.println("Unknown error while loading images");
        }
    }
    public Pull(){}
    public Pull(String address) {
        ImageIcon icon = new ImageIcon("pull_start.png");
        ImageIcon icon1 = new ImageIcon("pull_end.png");
        address_result=address;
        loadImages();
        this.setContentPane(new Mypanel());
        this.setTitle("DrawMain");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
       // this.setResizable(false);
       // this.setLayout(new BorderLayout());
        this.setLayout(null);

        //panel
        panel = new JPanel();
        panel.setBounds(160,142,450,240);
        panel.setLayout(new GridLayout(1, 3));

        //dpanel
        for (int i = 0; i < 3; i++) {
            dpanel[i] = new DigitPanel();
            dpanel[i].runnable = false;
            panel.add(dpanel[i]);
        }

        //button1
        button = new JButton(icon);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false); // 設定邊框不可見
        button.setOpaque(false);
        button.setFocusPainted(false);
        //button.setSize(199,430);
        button.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        //btn1 action
        button.addActionListener(new ActionListener() {
            //int idx = 0;
            //int Shownumber=0;

            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < 3; i++) {
                    dpanel[i].runnable = false;
                }
                int number=(int) (Math.random() * indexNames.size());
                for(int idx=0;idx<3;idx++){
                    dpanel[idx].showNumber = number;
                    type_result=indexNames.get(dpanel[idx].showNumber);
                    dpanel[idx].digitString = String.valueOf(dpanel[idx].showNumber);
                    dpanel[idx].count = 2+idx;
                    dpanel[idx].runnable = true;
                    button.setBounds(670,275,80,160);
                    button.setIcon(icon1);
                }
                //idx = (idx + 1) % 3;
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
        int stopCount=0;
        String digitString;
        int[] X = new int[indexNames.size()];
        int[] Y = new int[indexNames.size()];
        boolean runnable;
        int count;
        Timer timer = new Timer();

        public DigitPanel() {
            for (int i = 0; i < indexNames.size(); i++) {
                X[i] = 0;
                Y[i] = (indexNames.size()-1 - i) * 200 + 50;
                //Y[i] = (indexNames.size()-1 - i) * 200 + 100;
            }
            timer.schedule(new Heart(), 1000, 10);
            runnable = true;
        }

        public class Heart extends TimerTask {
            public void run() {
                if (runnable == false)
                    return;
                for (int i = 0; i < indexNames.size(); i++) {
                    Y[i] = Y[i] + 25;
                    Y[i] %= 200 * indexNames.size();
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
            Color c1 = Color.gray, c2 = Color.lightGray, c3 = Color.lightGray, c4 = Color.lightGray;
            Color[] colors = { c1, c2, c3, c4 };
            LinearGradientPaint p = new LinearGradientPaint(start, end, dist, colors);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(p);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            int pcount = count;
            for (int i = 0; i < indexNames.size(); i++) {
                //String digit = String.valueOf(i);
                String digit=imgNames.get(i);
                int x = X[i];
                int y = Y[i];
                g.setColor(Color.yellow);
                g.setFont(new Font("Serif",Font.ITALIC,30));
                //g.drawString(digit, x, y);
                g.drawImage(img[i],X[i],Y[i],150,150,null);
                if (y == 50 && i == showNumber) {
                    count--;
                    stopCount++;

                    if (count == 0) {

                        runnable = false;


                        if(stopCount==4){

                            /*for(int k=0;k<3;k++)
                            panel.remove(dpanel[k]);*/
                            pack();
                            setSize(800, 600);
                            int xx = getLocation().x;
                            int yy = getLocation().y;
                            setLocation(xx,yy);

                            type_result typeFrame = new type_result(type_result,address_result,imgNames,indexNames);
                            typeFrame.setBounds(100, 100, 514, 426);
                            typeFrame.setLocation(Pull.this.getLocation().x+380,Pull.this.getLocation().y+100);
                            button.setIcon(icon);
                            button.setBounds(670,180,80,160);
                            typeFrame.setAlwaysOnTop(true);
                            typeFrame.setVisible(true);
                            Pull.this.setVisible(true);
                            stopCount = -1;
                            count = 0;
                           // dpanel = new DigitPanel[3];
                            /*for (int n = 0; n < 3; n++) {
                                dpanel[n] = new DigitPanel();
                                dpanel[n].runnable = false;
                                panel.add(dpanel[n]);
                            }*/


                        }

                    }

                }
            }
            if (pcount != count) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new Heart(), 50, 5 * (5 - count));
            }
        }
    }
/*
    public static void main(String[] args) {
        new pull(String );
    }*/
}