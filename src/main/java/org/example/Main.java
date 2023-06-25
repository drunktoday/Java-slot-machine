package org.example;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Main{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
                    e.printStackTrace();
                }
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            Swing2 frame = new Swing2();
                            frame.setVisible(true);

                        } catch (Exception e) {
                            e.printStackTrace();
                }
            }
        });
    }
}