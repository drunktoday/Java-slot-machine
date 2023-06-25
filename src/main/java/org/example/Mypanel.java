package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Mypanel extends JPanel{

        private Image image;

        public Mypanel() {
            ImageIcon icon = new ImageIcon("machine6.png");
            // 載入圖片
            try {
                image = icon.getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 設定 JPanel 的大小
            setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
            // 設定 JPanel 的背景為透明
            setBackground(Color.lightGray);

            //setOpaque(false);

        }
        public Mypanel(ImageIcon img){
            ImageIcon icon =img;
            // 載入圖片
            try {
                image = icon.getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 設定 JPanel 的大小
            setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
            // 設定 JPanel 的背景為透明
            setBackground(Color.lightGray);
            //setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 繪製圖片
            g.drawImage(image, 0, 0, null);
        }

    }
