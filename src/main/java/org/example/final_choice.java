package org.example;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class final_choice extends JFrame {
    private JPanel contentPane;
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

            setBorder(new final_choice.RoundBorder());
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
    public final_choice(ArrayList<String> list,String choice,String beverage,String dessert) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(231, 221, 221));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("你抽到的餐廳是:");
        lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        lblNewLabel.setBounds(27, 59, 120, 38);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel1 = new JLabel(choice);
        lblNewLabel1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        lblNewLabel1.setBounds(158, 59, 270, 38);
        contentPane.add(lblNewLabel1);

        JLabel lblNewLabel2 = new JLabel("您可能會喜歡:");
        lblNewLabel2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        lblNewLabel2.setBounds(27, 101, 120, 38);
        contentPane.add(lblNewLabel2);

        JLabel lblNewLabel3 = new JLabel(beverage);
        lblNewLabel3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        lblNewLabel3.setBounds(158, 101, 220, 38);
        contentPane.add(lblNewLabel3);

        JLabel lblNewLabel4 = new JLabel(dessert);
        lblNewLabel4.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
        lblNewLabel4.setBounds(158, 159, 220, 38);
        contentPane.add(lblNewLabel4);
/*
        JLabel lblNewLabel_1 = new JLabel(choice);
        lblNewLabel_1.setFont(new Font("新細明體", Font.PLAIN, 15));
        lblNewLabel_1.setBounds(187, 101, 217, 38);
        contentPane.add(lblNewLabel_1);*/


        RoundRectButton btnNewButton = new RoundRectButton("重抽");
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
                //pull_final finalFrame = new pull_final(list,beverage,dessert);
               // finalFrame.setLocation(final_choice.this.getLocation().x,final_choice.this.getLocation().y);

                // 創建一個面板
                //JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                //frame4.getContentPane().add(panel);

                // 顯示新的 JFrame
                //finalFrame.setVisible(true);
                final_choice.this.setVisible(false);
            }
        });
        btnNewButton.setBounds(160, 202, 85, 23);
        contentPane.add(btnNewButton);


    }

}
