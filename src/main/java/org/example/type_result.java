package org.example;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.border.Border;

public class type_result extends Pull {

    private Pull closePull;     //
    private JPanel contentPane;
    public void playSound(String soundFile) {
        try {
            // 載入音效檔案
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            // 創建音效播放器
            Clip clip = AudioSystem.getClip();
            // 開始播放音效
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };


    /**
     * Launch the application.
     */
    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    type_result frame = new type_result();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/
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

            setBorder(new type_result.RoundBorder());
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
    /**
     * Create the frame.
     */
    public type_result(){}
    public type_result(String type, String address, ArrayList<String> image, ArrayList<String> indexName) {
        this.setBounds(100, 100, 514, 426);
        this.setLocation(type_result.super.getLocation().x + 380, type_result.super.getLocation().y + 100);
        this.setAlwaysOnTop(true);
        this.setVisible(true);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(231, 221, 221));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("你抽到的種類是:");
        lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        lblNewLabel.setBounds(68, 101, 120, 38);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(type);
        lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        lblNewLabel_1.setBounds(187, 101, 217, 38);
        contentPane.add(lblNewLabel_1);

        //Pull pullFrame = new Pull(address);
        //pullFrame.setVisible(false);
        //pullFrame.setBounds(100, 100, 514, 426);
        //pullFrame.setLocation(type_result.this.getLocation().x,type_result.this.getLocation().y);

        RoundRectButton btnNewButton = new RoundRectButton("送出");
        btnNewButton.setBackground(new Color(	176, 196 ,222));
        btnNewButton.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
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
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                type_result.super.dispose();
                Swing4 swing4_frame = new Swing4(type, address);
                swing4_frame.setLocation(type_result.this.getLocation().x, type_result.this.getLocation().y);

                // 創建一個面板
                //JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                //frame4.getContentPane().add(panel);

                // 顯示新的 JFrame
                swing4_frame.setVisible(true);
                type_result.this.setVisible(false);
                return;
            }
        });
        btnNewButton.setBounds(160, 202, 85, 23);
        contentPane.add(btnNewButton);


        RoundRectButton btnNewButton_1 = new RoundRectButton("上一頁");
        btnNewButton_1.setBackground(new Color(	176, 196 ,222));
        btnNewButton_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
        btnNewButton_1.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_1.setBackground(new Color(	119, 136, 153));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_1.setBackground(new Color(	176, 196 ,222));
            }
        });
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound("上一頁.wav");
                type_result.this.setVisible(false);
                //pullFrame.repaint();
                //pullFrame.setLocation(type_result.this.getLocation().x,type_result.this.getLocation().y);
                //pullFrame.setSize( 514, 426);
                //pullFrame.setVisible(true);
            }
        });
        btnNewButton_1.setBounds(343, 21, 85, 23);
        contentPane.add(btnNewButton_1);
    }
}
