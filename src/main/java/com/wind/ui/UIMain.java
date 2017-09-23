package com.wind.ui;

import javax.swing.*;

/**
 * 程序入口
 */
public class UIMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UIWindow();
            }
        });
    }
}
