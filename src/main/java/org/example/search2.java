package org.example;

import java.awt.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


public class search2 extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField2;
    private String str_address;
    private String str_type;
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
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    address frame = new address();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
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

            setBorder(new search2.RoundBorder());
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
    public search2() {
        ImageIcon back = new ImageIcon("back.png");
        ImageIcon mouse_back = new ImageIcon("mouse_back.png");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        //
        contentPane.setBackground(new Color(231, 221, 221));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);



        //輸入目前位置
        JLabel lblNewLabel = new JLabel("請輸入目前位置:");
        lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
        lblNewLabel.setBounds(28, 46, 116, 15);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(156, 43, 190, 21);
        textField.setForeground(Color.black);
        textField.setBackground(null);
        textField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1,0, Color.gray));
        contentPane.add(textField);
        //textField.setColumns(10);
        textField.setText("高雄大學");

        //輸入想吃的種類

        JLabel lblNewLabel2 = new JLabel("請輸入想吃的類型:");
        lblNewLabel2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
        lblNewLabel2.setBounds(28, 86, 116, 15);
        contentPane.add(lblNewLabel2);

        textField2 = new JTextField();
        textField2.setBounds(156, 83, 190, 21);
        textField2.setForeground(Color.black);
        textField2.setBackground(null);
        textField2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        textField2.setHorizontalAlignment(JTextField.CENTER);
        textField2.setBorder(BorderFactory.createMatteBorder(0, 0, 1,0, Color.gray));
        contentPane.add(textField2);
        //textField2.setColumns(10);
       // textField.setText("高雄大學");

        JButton last_page = new JButton(back);
        last_page.setContentAreaFilled(false);
        last_page.setBorderPainted(false); // 設定邊框不可見
        last_page.setOpaque(false);
        last_page.setFocusPainted(false);
        //button.setSize(199,430);
        last_page.setPreferredSize(new Dimension(back.getIconWidth(), back.getIconHeight()));
        last_page.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                last_page.setIcon(mouse_back);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                last_page.setIcon(back);
            }
        });

        last_page.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound("上一頁_01.wav");
                search2.this.setVisible(false);
                Swing2 swing2 = new Swing2();
                swing2.setVisible(true);

            }
        });
        last_page.setBounds(410, 10, 55, 55);
        contentPane.add(last_page);


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
                str_address=textField.getText();
                str_type=textField2.getText();
                Swing4 address_type_frame = new Swing4(str_type,str_address);
                //frame3.setVisible(false);

                //pull_frame .setBounds(100, 100, 514, 426);
                address_type_frame .setLocation(search2.this.getLocation().x,search2.this.getLocation().y-150);
                address_type_frame.setSize(800,660);
                // 創建一個面板
                //JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                //frame4.getContentPane().add(panel);

                // 顯示新的 JFrame
                address_type_frame .setVisible(true);
                search2.this.setVisible(false);
            }
        });
        btnNewButton.setBounds(156, 138, 190, 39);
        contentPane.add(btnNewButton);
    }
}
