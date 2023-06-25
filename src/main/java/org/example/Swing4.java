package org.example;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class shopInfo{
    public String name,Address;

    public float Rating;
    public shopInfo(String n,String A,float R){
        name = n;
        Address = A;
        Rating = R;

    }
    public shopInfo(){
        name = "";
        Address = "";
        Rating = 0;

    }
    public void set(String n,String A,float r){
        name = n;
        Address = A;
        Rating = r;

    }

}
public class Swing4 extends JFrame {

    private static final String API_KEY = ""; // 請填入您自己的Google Maps API金鑰
    private static final String GEOCODE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json?latlng=";

    private JLabel locationLabel;

    private ArrayList<String> selected = new ArrayList<String>();
    private ArrayList<String> Store_name = new ArrayList<>();
    //private ArrayList<String> beverage=new ArrayList<>();
    //private ArrayList<String> dessert=new ArrayList<>();
    private String beverage_result;
    private String dessert_result;

    private JLabel mapImageLabel;
    private JPanel infoTextPane;

    private JPanel contentPane;
    private JPanel leftPane;
    private JPanel rightPane;
    public class RoundBorder implements Border {
        public Insets getBorderInsets(Component c){
            return new Insets(0,0,0,0);
        }
        public boolean isBorderOpaque(){
            return false;
        }

        public void paintBorder(Component c,Graphics g,int x,int y,int width,int height){
            g.setColor((Color.BLACK));
            g.drawRoundRect(0,0,c.getWidth()-1,c.getHeight()-1,30,30);
        }
    }

    public class RoundRectButton extends JButton{
        public RoundRectButton(String s){
            super(s);
            setMargin(new Insets(0,0,0,0));

            setBorder(new Swing4.RoundBorder());
            setContentAreaFilled(false);
            //setBorderPainted(false);
            setFocusPainted(false);
        }

        protected  void paintComponent(Graphics g){
            if(getModel().isArmed()){
                g.setColor(new Color(119, 136, 153));
            }
            else{
                g.setColor(getBackground());
            }
            g.fillRoundRect(0,0,getSize().width-1,getSize().height-1,30,30);
            super.paintComponent(g);
        }
    }

    public Swing4(String type, String address) {
        // 設定視窗標題
        setTitle("Google Maps Example");
        // 設定視窗大小
        setSize(800, 600);
        // 設定視窗位置
        setLocationRelativeTo(null); // 置中
        // 設定視窗關閉時的操作
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 初始化UI元件
        locationLabel = new JLabel("正在獲取現在位置...");
        //mapImageLabel = new JLabel();
        infoTextPane = new JPanel();
        contentPane = new JPanel();
        contentPane.setBackground(new Color(231, 221, 221));
        //contentPane.setOpaque(true);
        leftPane = new JPanel();
        leftPane.setBounds(10, 5, 600, 600);
        //leftPane.setBackground(new Color(231, 221, 221));
        leftPane.setOpaque(true);
        rightPane = new JPanel();
        rightPane.setBounds(610, 5, 200, 600);
        rightPane.setBorder(new EmptyBorder(5, 20, 5, 5));
        rightPane.setBackground(new Color(231, 221, 221));
        rightPane.setOpaque(true);
        //infoTextPane.setEditable(false);
        ArrayList<shopInfo> alist = new ArrayList<shopInfo>();
        ArrayList<shopInfo> beverage_list = new ArrayList<shopInfo>();
        ArrayList<shopInfo> dessert_list = new ArrayList<shopInfo>();
        // String[] type ={"noodle","curry","rice","粥","義式料理","炸物","pizza","鹽水雞","滷味","日式料理","港式料理","中式料理","漢堡","速食店","素食","麵店","便當","火鍋","燒烤","水餃","麵包","鍋燒"};
        //Random R = new Random();
        //int r = 0;
        //r =(int)(Math.random()*(type.length-1));
        locationLabel.setSize(getWidth(), 30);
        locationLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        // 將UI元件加入視窗
        //leftPane.add(locationLabel,BorderLayout.NORTH);

        // add(mapImageLabel, BorderLayout.CENTER);
        //add(new JScrollPane(contentPane), BorderLayout.SOUTH);


        // 獲取現在位置的坐標
        try {

            //String str;
            //str = type[r];

            GeoApiContext context = new GeoApiContext.Builder().apiKey(API_KEY).build();
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
            if (results.length > 0) {
                locationLabel.setText("現在位置：" + results[0].formattedAddress);
                LatLng location = results[0].geometry.location;


                PlacesSearchResponse nearbyPlaces = PlacesApi.nearbySearchQuery(context, location)
                        .radius(250)
                        .type(PlaceType.RESTAURANT)
                        .keyword(type)
                        .await();
                //
                PlacesSearchResponse nearBeverage = PlacesApi.nearbySearchQuery(context, location)
                        .radius(500)
                        // .type(PlaceType.RESTAURANT)
                        .keyword("飲料")
                        .await();

                PlacesSearchResponse nearDessert = PlacesApi.nearbySearchQuery(context, location)
                        .radius(800)
                        //.type(PlaceType.RESTAURANT)
                        .keyword("甜品")
                        .await();
                //

                for (PlacesSearchResult place : nearbyPlaces.results) {
                    String name = place.name;
                    String vicinity = place.vicinity;
                    float rating = place.rating;
                    //String []a = place.openingHours.weekdayText;
                    //peningHours status = place.openingHours;
                    shopInfo info = new shopInfo();
                    info.set(name, vicinity, rating);

                    alist.add(info);
                    //infoTextPane.setText(infoTextPane.getText() + info);
                }
                ArrayList<shopInfo> ra = new ArrayList<shopInfo>();
                //Beverage
                for (PlacesSearchResult place : nearBeverage.results) {
                    String name = place.name;
                    String vicinity = place.vicinity;
                    float rating = place.rating;
                    //String []a = place.openingHours.weekdayText;
                    OpeningHours status = place.openingHours;
                    shopInfo info_B = new shopInfo();
                    info_B.set(name, vicinity, rating);

                    beverage_list.add(info_B);
                    //infoTextPane.setText(infoTextPane.getText() + info);
                }
                ArrayList<shopInfo> rb = new ArrayList<shopInfo>();
                //Dessert
                for (PlacesSearchResult place : nearDessert.results) {
                    String name = place.name;
                    String vicinity = place.vicinity;
                    float rating = place.rating;
                    //String []a = place.openingHours.weekdayText;
                    OpeningHours status = place.openingHours;
                    shopInfo info_D = new shopInfo();
                    info_D.set(name, vicinity, rating);

                    dessert_list.add(info_D);
                    //infoTextPane.setText(infoTextPane.getText() + info);
                }
                ArrayList<shopInfo> rd = new ArrayList<shopInfo>();

                //
                //contentPane.setBorder(new EmptyBorder(37, 20, 10, 20));


                //contentPane.setLayout(new BorderLayout(0,0));
                //contentPane.setLayout(null);
                setContentPane(contentPane);
                while (alist.size()!=0){
                    for (int i = 0; i < alist.size(); i++) {
                        float max = 0;
                        int index = 0;
                        for (shopInfo s : alist) {
                            if (max < s.Rating) {
                                max = s.Rating;
                                index = alist.indexOf(s);
                            }
                        }
                        shopInfo s = alist.get(index);
                        ra.add(s);
                        alist.remove(s);
                    }
                }
                //n
                while(beverage_list.size()!=0){
                    for (int i = 0; i <  beverage_list.size(); i++) {
                        //
                        System.out.print(beverage_list.get(i) + " ");
                        float max = 0;
                        int index = 0;
                        for (shopInfo s : beverage_list) {
                            if (max < s.Rating) {
                                max = s.Rating;
                                index = beverage_list.indexOf(s);
                            }
                        }
                        shopInfo sb = beverage_list.get(index);
                        rb.add(sb);
                        beverage_list.remove(sb);
                    }
                }
                System.out.println();
                while(dessert_list.size()!=0){
                    for (int i = 0; i < dessert_list.size(); i++) {
                        System.out.print(dessert_list.get(i) + " ");
                        float max = 0;
                        int index = 0;
                        for (shopInfo s : dessert_list) {
                            if (max < s.Rating) {
                                max = s.Rating;
                                index = dessert_list.indexOf(s);
                            }
                        }
                        shopInfo sd = dessert_list.get(index);
                        rd.add(sd);
                        dessert_list.remove(sd);
                    }
                }
                //
                String info1 = "";
                //textArea.setText(str);
                //textArea.append(str);
                JButton btnNewButton = new JButton("送出");
                btnNewButton.setBackground(new Color(	176, 196 ,222));
                btnNewButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
                btnNewButton.addMouseListener(new MouseListener() {

                    public void mouseClicked(MouseEvent e) {}
                    public void mousePressed(MouseEvent e) {}
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btnNewButton.setBackground(new Color(	119, 136, 153));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        btnNewButton.setBackground(new Color(	176, 196 ,222));
                    }
                });
                JButton btnNewButton1 = new JButton("全選");
                btnNewButton1.setBackground(new Color(	176, 196 ,222));
                btnNewButton1.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
                btnNewButton1.addMouseListener(new MouseListener() {

                    public void mouseClicked(MouseEvent e) {}
                    public void mousePressed(MouseEvent e) {}
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btnNewButton1.setBackground(new Color(	119, 136, 153));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        btnNewButton1.setBackground(new Color(	176, 196 ,222));
                    }
                });
                JLabel label_space = new JLabel(" ");
                // label_space.setBounds(450,);
                //rightPane.add(btnNewButton1);
                //btnNewButton.setBounds(160, 214, 85, 23);
                Box box = Box.createVerticalBox();
                //box.setSize(10,10);
                //box.setBounds(5,200,100,50);
                box.setBorder(new EmptyBorder(5, 10, 200, 50));
                ////////////////////////////
                Box box2 = Box.createVerticalBox();
                box2.setBounds(250, 10, 200, 100);
                box2.add(btnNewButton);
                box2.add(label_space);
                box2.add(btnNewButton1);
                rightPane.add(box2);


                JScrollPane sp = new JScrollPane(box, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


                JCheckBox[] jcb = new JCheckBox[ra.size()];
                int x = 0, y = 60;
                for (int n = 0; n < ra.size(); n++) {
                    info1 = "<html>Name:" + ra.get(n).name + "<br/>" +
                            "Address: " + ra.get(n).Address + "<br/>" +
                            "Rating: " + ra.get(n).Rating + "<br/><html/>";
                    //infoTextPane.setText(infoTextPane.getText() + info1);
                    Store_name.add(n, ra.get(n).name);
                    jcb[n] = new JCheckBox(info1);
                    //
                    jcb[n].setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
                    jcb[n].setFocusPainted(false);
                    box.add(jcb[n]);

                }
                leftPane.add(sp);
                leftPane.add(box);
                //隨機甜點與飲料


                btnNewButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int k = 0; k < ra.size(); k++) {
                            if (jcb[k].isSelected()) {
                                //  selected.add(jcb[k].getText());
                                selected.add(Store_name.get(k));
                            }
                        }
                        pull_final pull_finalFrame = new pull_final(selected, rb, rd);
                        pull_finalFrame.setLocation(Swing4.this.getLocation().x, Swing4.this.getLocation().y - 40);
                        // 創建一個面板
                        JPanel panel = new JPanel();

                        // 將面板加到新的 JFrame 上
                        pull_finalFrame.getContentPane().add(panel);

                        // 顯示新的 JFrame
                        pull_finalFrame.setVisible(true);

                        Swing4.this.setVisible(false);
                    }
                });

                btnNewButton1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        for (int k = 0; k < ra.size(); k++) {
                            jcb[k].setSelected(true);
                        }
                    }
                });


//box.add(btnNewButton);
//box.add(btnNewButton1);
//Component glue=Box.createHorizontalGlue();
//box.add(glue);
                // Component strut=Box.createHorizontalStrut(20);
                //box.add(strut);

                //contentPane.add(btnNewButton);
                //contentPane.add(btnNewButton1);
                contentPane.add(leftPane);
                contentPane.add(rightPane);


            } else {
                locationLabel.setText("無法獲取現在位置");
            }
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}