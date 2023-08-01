package view.ultilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

public class LoadingDialog extends JDialog {

        private String txt;
        public static LoadingDialog o;
        

        public LoadingDialog(JFrame owner) {
            super(owner, false);
            setUndecorated(true);
            setSize(150, 150);
            txt = " loading";
            setLocationRelativeTo(owner);
            setLayout(new BorderLayout());
            getContentPane().setBackground(new Color(0x273c75));
            JLabel lblLoading = new JLabel(txt);
            lblLoading.setForeground(new Color(0xf5f6fa));
            lblLoading.setFont(new Font("Consolas", Font.BOLD, 18));
            add(lblLoading, BorderLayout.CENTER);
            
            setLocationRelativeTo(this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (true) {
                        try {
                            if (cnt < 3) {
                                txt += " .";
                                cnt++;
                            } else {
                                cnt = 0;
                                txt = " loading";
                            }
                            Thread.sleep(500);
                            lblLoading.setText(txt);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        
        public LoadingDialog(JDialog owner) {
            super(owner, false);
            setUndecorated(true);
            setSize(150, 150);
            txt = " loading";
            setLocationRelativeTo(owner);
            setLayout(new BorderLayout());
            getContentPane().setBackground(new Color(0x273c75));
            JLabel lblLoading = new JLabel(txt);
            lblLoading.setForeground(new Color(0xf5f6fa));
            lblLoading.setFont(new Font("Consolas", Font.BOLD, 18));
            add(lblLoading, BorderLayout.CENTER);
            
            setLocationRelativeTo(this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (true) {
                        try {
                            if (cnt < 3) {
                                txt += " .";
                                cnt++;
                            } else {
                                cnt = 0;
                                txt = " loading";
                            }
                            Thread.sleep(500);
                            lblLoading.setText(txt);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        
        public static LoadingDialog init(JFrame owner) {
            o = new LoadingDialog(owner);
            return o;
        }
        
        public static LoadingDialog init(JDialog owner) {
            o = new LoadingDialog(owner);
            return o;
        }

        @Override
        protected JRootPane createRootPane() {
            JRootPane rootPane = new JRootPane();
            rootPane.setWindowDecorationStyle(JRootPane.NONE); // Loại bỏ thanh tiêu đề
            return rootPane;
        }

    }
