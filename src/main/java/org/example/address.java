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

class ImagePanel extends JPanel {
    private Image image;

    public ImagePanel() {
        ImageIcon icon = new ImageIcon("address_back.png");
        // 載入圖片
        try {
            image = icon.getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 設定 JPanel 的大小
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        // 設定 JPanel 的背景為透明
       // setBackground(Color.lightGray);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 繪製圖片
        g.drawImage(image, 0, 0, null);
    }
}
public class address extends JFrame {
    public  Pull pull_frame;
    public type_result typeFrame;
    private JPanel contentPane;
    private JTextField textField;
    private String str;
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
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    address frame = new address();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
    public class RoundBorder implements Border{
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

            setBorder(new RoundBorder());
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
    public address() {
        ImageIcon back = new ImageIcon("back.png");
        ImageIcon mouse_back = new ImageIcon("mouse_back.png");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        //
        contentPane.setBackground(new Color(231, 221, 221));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        contentPane.setOpaque(false);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(156, 40, 190, 21);
        textField.setForeground(Color.black);
        textField.setBackground(null);
        textField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        textField.setHorizontalAlignment(JTextField.CENTER);
       textField.setBorder(BorderFactory.createMatteBorder(0, 0, 1,0, Color.gray));
        contentPane.add(textField);
        textField.setColumns(10);
        textField.setText("高雄大學");
        contentPane.setOpaque(true);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                str=textField.getText();
                Pull pull_frame = new Pull(str);
                //frame3.setVisible(false);

                //pull_frame .setBounds(100, 100, 514, 426);
                pull_frame .setLocation(address.this.getLocation().x,address.this.getLocation().y-150);
                pull_frame.setSize(800,660);
                // 創建一個面板
                //JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                //frame4.getContentPane().add(panel);

                // 顯示新的 JFrame
                pull_frame .setVisible(true);
                address.this.setVisible(false);
            }
        });


        JLabel lblNewLabel = new JLabel("請輸入目前位置:");
        lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        lblNewLabel.setBounds(28, 43, 116, 15);
        contentPane.add(lblNewLabel);

        //last page
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
                address.this.setVisible(false);
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
                str=textField.getText();
                pull_frame = new Pull(str);
                //frame3.setVisible(false);

                //pull_frame .setBounds(100, 100, 514, 426);
                pull_frame .setLocation(address.this.getLocation().x,address.this.getLocation().y-150);
                pull_frame.setSize(800,660);
                // 創建一個面板
                //JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                //frame4.getContentPane().add(panel);

                // 顯示新的 JFrame
                pull_frame .setVisible(true);
                address.this.setVisible(false);
            }
        });
        btnNewButton.setBounds(156, 138, 190, 39);
        //

        contentPane.add(btnNewButton);
        contentPane.setBackground(new Color(231, 221, 221));
    }
}
