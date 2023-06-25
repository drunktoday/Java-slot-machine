package org.example;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Swing3 extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private ArrayList<String> restaurant = new ArrayList<String>();
    private int index = 0;
    private JCheckBox []jb = new JCheckBox[30];
    private JPanel leftPane;
    private JPanel rightPane;
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

    /**
     * Create the frame.
     */


    public Swing3() {
        ImageIcon back = new ImageIcon("back.png");
        ImageIcon mouse_back = new ImageIcon("mouse_back.png");
        ImageIcon trash = new ImageIcon("trash.png");
        ImageIcon mouse_trash = new ImageIcon("mouse_trash.png");
        ImageIcon send = new ImageIcon("send.png");
        ImageIcon mouse_send = new ImageIcon("mouse_send.png");
        ImageIcon add = new ImageIcon("add.png");
        ImageIcon mouse_add = new ImageIcon("mouse_add.png");
        //JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        //JPanel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(37, 20, 10, 20));
        //contentPane.setLayout(new BorderLayout(0,0));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        contentPane. setBackground(new Color(231, 221, 221));
        leftPane=new JPanel();
        leftPane.setBounds(10,10,350,300);
        /////
        leftPane.setLayout(new BorderLayout());

        rightPane=new JPanel();
        rightPane.setBounds(360,5,100,300);

        //Box

        //JPanel for JLabel and JTextField
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //JLabel
        Font textF = new Font("標楷體", Font.PLAIN, 15);
        Font labelF = new Font("標楷體", Font.PLAIN, 15);
        JLabel lblNewLabel = new JLabel("請輸入你想吃的餐廳:");
        lblNewLabel.setFont(labelF);
        lblNewLabel.setOpaque(false);
        labelPanel.setOpaque(false);
        labelPanel.add(lblNewLabel);
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setBorder(new EmptyBorder(5,30,200,10));
        ArrayList<JCheckBox> choice = new ArrayList<JCheckBox>();

        //TextField
        textField = new JTextField();
        textField.setColumns(28);
        Color fontC = new Color(255,255,255);
        textField.setForeground(fontC);
        Color bgColor = new Color(50, 50, 50);
        textField.setBackground(bgColor);
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setFont(textF);

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.add(textField.getText());
                jb[index] = new JCheckBox(textField.getText());
                jb[index].setBackground(Color.WHITE);
                jb[index].setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
                choice.add(jb[index]);
                box.add(choice.get(index));
                //textArea.append(restaurant.get(index) + "\r\n");
                index++;
                textField.setText("");
                int x = getLocation().x;
                int y = getLocation().y;
                pack();
                setBounds(100, 100, 514, 426);
                setLocation(x,y);
            }
        });

        //LabelPanel 用來放label和textField
        labelPanel.add(textField);
        leftPane.add(labelPanel, BorderLayout.NORTH);

        //上一頁
        Swing2 swing2 = new Swing2();
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
                Swing3.this.setVisible(false);
                swing2.setVisible(true);
            }
        });
        last_page.setBounds(150, 175, 50, 50);
        rightPane.add(last_page);

        //Button1 送出
        JButton btnNewButton_1 = new JButton(send);
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setBorderPainted(false); // 設定邊框不可見
        btnNewButton_1.setOpaque(false);
        btnNewButton_1.setFocusPainted(false);
        //button.setSize(199,430);
        btnNewButton_1.setPreferredSize(new Dimension(back.getIconWidth(), back.getIconHeight()));
        btnNewButton_1.addMouseListener(new MouseListener() {

         public void mouseClicked(MouseEvent e) {
           }

         public void mousePressed(MouseEvent e) {
          }

         public void mouseReleased(MouseEvent e) {
           }

          @Override
          public void mouseEntered(MouseEvent e) {
            btnNewButton_1.setIcon(mouse_send);
          }

           @Override
          public void mouseExited(MouseEvent e) {
             btnNewButton_1.setIcon(send);
           }
       });
        btnNewButton_1.setVerticalTextPosition(AbstractButton.CENTER);
        btnNewButton_1.setHorizontalTextPosition(AbstractButton.CENTER);
        //btnNewButton_1.setSize(85,23);
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pull1 pull1_frame = new Pull1(restaurant);
                //pull1_frame.setBounds(100, 100, 514, 426);
                pull1_frame.setLocation(Swing3.this.getLocation().x,Swing3.this.getLocation().y-150);
                // 創建一個面板
                //JPanel panel = new JPanel();

                // 將面板加到新的 JFrame 上
                //pull1_frame.getContentPane().add(panel);
                pull1_frame.setSize(800,660);
                // 顯示新的 JFrame
                pull1_frame.setVisible(true);

                Swing3.this.setVisible(false);
            }
        });

        //btnNewButton_1.setBounds(347, 145, 85, 23);
        btnNewButton_1.setBounds(150, 200, 85, 23);
        //contentPane.add(btnNewButton_1);
        rightPane.add(btnNewButton_1);

       // Box box = new Box(BoxLayout.Y_AXIS);
        ////////// box.setSize(100,100);
//        box.setBorder(new EmptyBorder(5,30,200,10));
        ////////
        leftPane.add(box, BorderLayout.WEST);

//        ArrayList<JCheckBox> choice = new ArrayList<JCheckBox>();
        //Button2 加入
        JButton btnNewButton = new JButton(add);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false); // 設定邊框不可見
        btnNewButton.setOpaque(false);
        btnNewButton.setFocusPainted(false);
        //button.setSize(199,430);
        btnNewButton.setPreferredSize(new Dimension(back.getIconWidth(), back.getIconHeight()));
        btnNewButton.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnNewButton.setIcon(mouse_add);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnNewButton.setIcon(add);
            }
        });
        btnNewButton.setBounds(150, 225, 85, 23);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.add(textField.getText());
                jb[index] = new JCheckBox(textField.getText());
                //jb[index].setFont(labelF);
                jb[index].setBackground(Color.WHITE);
                jb[index].setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
                choice.add(jb[index]);
                box.add(choice.get(index));
                //textArea.append(restaurant.get(index) + "\r\n");
                index++;
                textField.setText("");
                int x = getLocation().x;
                int y = getLocation().y;
                pack();
                setBounds(100, 100, 514, 426);
                setLocation(x,y);
            }

        });
        //contentPane.add(btnNewButton);
        rightPane.add(btnNewButton);

        //checkbox

        //Button2
        JButton btnClear = new JButton(trash);
        btnClear.setContentAreaFilled(false);
        btnClear.setBorderPainted(false); // 設定邊框不可見
        btnClear.setOpaque(false);
        btnClear.setFocusPainted(false);
        //button.setSize(199,430);
        btnClear.setPreferredSize(new Dimension(back.getIconWidth(), back.getIconHeight()));
        btnClear.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                btnClear.setIcon(mouse_trash);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnClear.setIcon(trash);
            }
        });
        btnClear.setBounds(150,250,85,23);
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               /* JCheckBox []st = new JCheckBox[choice.size()];
                for(JCheckBox a:choice){
                    if(a.isSelected() == true){
                        st[choice.indexOf(a)] = a;
                    }
                    for(JCheckBox i:st){
                        choice.remove(i);
                        box.remove(i);
                    }

                }*/
                Iterator<JCheckBox> iterator = choice.iterator();
                while (iterator.hasNext()) {
                    JCheckBox checkBox = iterator.next();
                    if (checkBox.isSelected()) {
                        iterator.remove();
                        restaurant.remove(checkBox.getText());
                        box.remove(checkBox);
                        index--;
                    }
                }
                box.revalidate();
                box.repaint();
            }
        });

        //contentPane.add(btnClear);
        rightPane.add(btnClear);

        //box.setBounds(23,80,410,200);
        //contentPane.add(box, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(box);

        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());

        scrollPane.setBounds(10, 80, 300, 250);
        //scrollPane.add(box);
        leftPane.add(scrollPane);
        leftPane.setOpaque(false);
        rightPane.setOpaque(false);
        contentPane.add(leftPane);
        contentPane.add(rightPane);
        //contentPane.add(scrollPane, BorderLayout.CENTER);

    }

    static class MyScrollBarUI extends BasicScrollBarUI {
        private final Dimension d = new Dimension();

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return d;
                }
            };
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return new JButton() {
                @Override
                public Dimension getPreferredSize() {
                    return d;
                }
            };
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);
            g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
            g2.dispose();
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height, 10, 10);
            g2.dispose();
        }
    }
}