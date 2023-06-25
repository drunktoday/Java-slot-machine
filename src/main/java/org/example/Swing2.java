package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Swing2 extends JFrame  {

    private Mypanel contentPane;
    private final Action action = new SwingAction();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
       /* GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontFamilies = ge.getAvailableFontFamilyNames();
        Font font = new Font(fontFamilies[13], Font.PLAIN, 15);
        UIManager.put("OptionPane.messageFont", font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("TextField.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);*/

    }

    /**
     * Create the frame.
     */
    int a =0;
    private JTextField textField;
    public Swing2() {
        ImageIcon randem = new ImageIcon("驚嘆號.png");
        ImageIcon noIdea = new ImageIcon("問號.png");
        ImageIcon icon = new ImageIcon("first5.png");
        ImageIcon search = new ImageIcon("放大鏡.png");
        ImageIcon mouse_random = new ImageIcon("驚嘆號mouse_hover.png");
        ImageIcon mouse_noIdea = new ImageIcon("問號mouse_hover.png");
        ImageIcon mouse_search = new ImageIcon("放大鏡mouse_hover.png");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 514, 450);
        setLocation(350,250);
       contentPane = new Mypanel(icon);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);


        //add(lblNewLabel);
        JButton btnNewButton_2 = new JButton(randem);
        btnNewButton_2.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
            btnNewButton_2.setIcon(mouse_random);
                btnNewButton_2.setBounds(260, 330, 55, 49);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_2.setIcon(randem);
                btnNewButton_2.setBounds(260, 330, 55, 55);
            }
        });
        btnNewButton_2.setBounds(260, 330, 55, 55);
        btnNewButton_2.setContentAreaFilled(false);
        btnNewButton_2.setBorderPainted(false); // 設定邊框不可見
        btnNewButton_2.setOpaque(false);
        btnNewButton_2.setFocusPainted(false);
        //button.setSize(199,430);
        btnNewButton_2.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Swing3 frame3 = new Swing3();
                //frame3.setVisible(false);

                frame3.setBounds(100, 100, 514, 426);
                frame3.setLocation(Swing2.this.getLocation().x,Swing2.this.getLocation().y);

                // 創建一個面板
                JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                frame3.getContentPane().add(panel);

                // 顯示新的 JFrame
                frame3.setVisible(true);
                a=1;
                Swing2.this.setVisible(false);
            }
        });

        contentPane.setLayout(null);
        contentPane.add(btnNewButton_2);

        //btn no idea
        JButton btnNewButton_2_1 = new JButton(noIdea);
        btnNewButton_2_1.setContentAreaFilled(false);
        btnNewButton_2_1.setBorderPainted(false); // 設定邊框不可見
        btnNewButton_2_1.setOpaque(false);
        btnNewButton_2_1.setFocusPainted(false);
        //button.setSize(199,430);
        btnNewButton_2_1.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        btnNewButton_2_1.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton_2_1.setIcon(mouse_noIdea);
                btnNewButton_2_1.setBounds(200, 330, 55, 49);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton_2_1.setIcon(noIdea);
                btnNewButton_2_1.setBounds(200, 330, 55, 55);
            }
        });
        btnNewButton_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                address address_frame = new address();
                //Swing4 frame4 = new Swing4();
                //frame3.setVisible(false);
                address_frame.setBounds(100, 100, 514, 426);
                address_frame.setLocation(Swing2.this.getLocation().x,Swing2.this.getLocation().y);


                // 創建一個面板
                JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                //frame4.getContentPane().add(panel);

                // 顯示新的 JFrame
                address_frame.getContentPane().add(panel);
                address_frame.setVisible(true);
                a=1;
                Swing2.this.setVisible(false);
            }
        });
        btnNewButton_2_1.setBounds(200, 330, 55, 55);
        btnNewButton_2_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定箭頭游標
        btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定箭頭游標
        contentPane.add(btnNewButton_2_1);

        //有想吃的type
        JButton Search_btn = new JButton(search);
        Search_btn.setContentAreaFilled(false);
        Search_btn.setBorderPainted(false); // 設定邊框不可見
        Search_btn.setOpaque(false);
        Search_btn.setFocusPainted(false);
        //button.setSize(199,430);
        Search_btn.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        Search_btn.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                Search_btn.setIcon(mouse_search);
                Search_btn.setBounds(140, 330, 55, 49);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Search_btn.setIcon(search);
                Search_btn.setBounds(140, 330, 55, 55);
            }
        });
        Search_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search2 s = new search2();
                //Swing4 frame4 = new Swing4();
                //frame3.setVisible(false);
                s.setBounds(100, 100, 514, 426);
                s.setLocation(Swing2.this.getLocation().x,Swing2.this.getLocation().y);


                // 創建一個面板
                //JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                //frame4.getContentPane().add(panel);

                // 顯示新的 JFrame
                s.setVisible(true);
                a=1;
                Swing2.this.setVisible(false);
            }
        });
        Search_btn.setBounds(140, 330, 55, 55);
        Search_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定箭頭游標
        Search_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 設定箭頭游標
        contentPane.add(Search_btn);

    }

    private class SwingAction extends AbstractAction {
        public SwingAction() {
            putValue(NAME, "SwingAction");
            putValue(SHORT_DESCRIPTION, "Some short description");
        }
        public void actionPerformed(ActionEvent e) {
        }
    }
}