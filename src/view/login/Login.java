package view.login;

import view.root.TeacherView;
import view.root.EmployeeView;
import service.UserService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.MemoryImageSource;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import model.beans.User;
import view.root.StudentView;
import view.ultilities.LoadingDialog;

public class Login extends JFrame {

    private final JPanel logoPanel;
    private final JLabel title;
    private final JButton btnHelp;
    private final JPanel panelLogin;
    private final JLabel lblCopyright;
    private final JButton submit;
    private final JButton forget;
    private JCheckBox remember;
    private final JTextField txtUser;
    private final JPasswordField txtPassword;
    private final JLabel errorUser;
    private final JLabel errorPass;

    private final UserService userService;
    public static LoadingDialog load;
    
    public static EmployeeView employeeView;

    public Login() throws HeadlessException {

        // init service object
        userService = new UserService();

        // Set layout
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(gridbag);
        Font font = new Font("Consolas", Font.PLAIN, 12);
        setFont(font);
        getContentPane().setBackground(new Color(0xecf0f1));
        // Bỏ đường viền khi nhấn vào nút
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                setSize(30, 30);

                int w = getWidth();
                int h = getHeight();
                // Tạo một đối tượng ellipse == hình tròn
                Ellipse2D circle = new Ellipse2D.Double(0, 0, w, h);
                g2d.setClip(circle);
                g2d.drawImage(createPicture(w, h), 0, 0, null);
            }

        };

        // title
        title = new JLabel("FPT Education", JLabel.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 20));
        title.setForeground(new Color(0x08509f));

        // register
        btnHelp = new JButton("Help ?");
        btnHelp.setBorderPainted(false);
        btnHelp.setBackground(new Color(0xecf0f1));
        btnHelp.setFont(new Font("Consolas", Font.BOLD, 14));
        btnHelp.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                btnHelp.setBackground(new Color(0xf27123));
                btnHelp.setForeground(new Color(0xffffff));
            }

        });
        btnHelp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                btnHelp.setBackground(new Color(0xecf0f1));
                btnHelp.setForeground(new Color(0x000000));
            }

        });
        btnHelp.addActionListener(e -> new HelpDialog(this));

        panelLogin = new JPanel(new GridLayout(7, 1));
        JLabel lblLoginTitle = new JLabel("Login to begin the advanture");
        lblLoginTitle.setFont(new Font("Consolas", Font.BOLD, 18));
        lblLoginTitle.setForeground(new Color(0xf27123));
        panelLogin.add(lblLoginTitle);
        //user
        JLabel lblUser = new JLabel("Enter your username");
        lblUser.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtUser = new JTextField();
        errorUser = new JLabel(" ");
        errorUser.setFont(font);
        errorUser.setForeground(Color.red);
        panelLogin.add(lblUser);
        panelLogin.add(txtUser);
        panelLogin.add(errorUser);
        // password
        txtPassword = new JPasswordField();
        JLabel lblPass = new JLabel("Enter your password");
        lblPass.setFont(new Font("Consolas", Font.PLAIN, 13));
        errorPass = new JLabel(" ");
        errorPass.setFont(font);
        errorPass.setForeground(Color.red);
        panelLogin.add(lblPass);
        panelLogin.add(txtPassword);
        panelLogin.add(errorPass);

        panelLogin.setBackground(new Color(0xecf0f1));

        // add event cho txtUser và txtPassword
        txtUser.addActionListener(e -> {
            txtPassword.requestFocus();
        });

        txtUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                errorUser.setText(" ");
            }

        });

        txtPassword.addActionListener(e -> {
            login();
        });

        txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                errorPass.setText(" ");
            }
        });

        // Submit
        submit = new JButton("Submit...");
//        submit.setBorderPainted(false);
        submit.setBorder(BorderFactory.createEtchedBorder());
        submit.setBackground(new Color(0xecf0f1));
        submit.setFont(new Font("Consolas", Font.BOLD, 14));

        submit.addActionListener(e -> {
            login();
        });

        // Submit
        forget = new JButton("Forget...");
//        forget.setBorderPainted(false);
        forget.setBorder(BorderFactory.createEtchedBorder());
        forget.setBackground(new Color(0xecf0f1));
        forget.setFont(new Font("Consolas", Font.BOLD, 14));

        lblCopyright = new JLabel(
                "Created by Nguyen Nguyen © Copyright 2022",
                JLabel.CENTER);
        lblCopyright.setFont(new Font("Consolas", Font.PLAIN, 14));
        lblCopyright.setForeground(new Color(0x51b748));

        // set chung
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.anchor = GridBagConstraints.NORTH;

        // set riêng
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 80;
        add(logoPanel, gbc);

        gbc.ipadx = 0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(title, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(btnHelp, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(panelLogin, gbc);

        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.gridx = 2;
        gbc.gridy = 1;
        add(submit, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(forget, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weighty = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        add(lblCopyright, gbc);

        setVisible(true);
    }

    private void openLoad() {
        load = LoadingDialog.init(this);
        load.setVisible(true);
    }

    public Image createPicture(int w, int h) {
        Image img;
        int[] pixel = new int[w * h];

        int i = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int b = (x ^ y) & 0xff;
                int g = (x * 4 ^ y * 4) & 0xff;
                int r = (x * 2 ^ y * 2) & 0xff;
                pixel[i++] = (255 << 24) | (r << 16) | (g << 8) | b;
            }
        }

        return createImage(new MemoryImageSource(w, h, pixel, 0, w));
    }

    private boolean validateForm() {
        int cntErr = 0;
        if (txtUser.getText().isEmpty()) {
            errorUser.setText("Không được để trống");
            cntErr++;
        }
        if (txtPassword.getText().isEmpty()) {
            errorPass.setText("Không được để trống");
        }
        if (cntErr == 0) {
            errorUser.setText(" ");
            errorPass.setText(" ");
            return true;
        } else {
            return false;
        }
    }
    
    public static void disposeFrame() {
        employeeView.dispose();
    }

    private void login() {
//        String user = txtUser.getText();
//        String pass = new String(txtPassword.getPassword());
        if (validateForm()) {
            String stateLogin = userService.login(new User(txtUser.getText(),
                     new String(txtPassword.getPassword())));

            if (stateLogin.equals("0")) {
                errorUser.setText("Thông tin username không chính xác");
            } else if (stateLogin.equals("1")) {
                errorUser.setText(" ");
                errorPass.setText("Mật khẩu không chính xác");
            } else {
                String[] states = stateLogin.split(",");
                errorUser.setText(" ");
                errorPass.setText("");
                System.out.println(states[1]);
                switch (states[1]) {
                    case "1" -> {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                openLoad();
                            }
                        }).start();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new EmployeeView(null, states[1]);
                            }
                        }).start();
                        break;
                    }
                    case "2" -> {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                openLoad();
                            }
                        }).start();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                               employeeView = new EmployeeView(txtUser.getText(), states[1]);
                            }
                        }).start();
                        break;
                    }
                    case "3" -> {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                openLoad();
                            }
                        }).start();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new TeacherView(txtUser.getText()).setVisible(true);
                            }
                        }).start();
                        break;
                    }
                    case "4" -> {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                openLoad();
                            }
                        }).start();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                new StudentView(txtUser.getText()).setVisible(true);
                            }
                        }).start();
                        break;
                    }
                }

                dispose();
            }
        }
    }

    public static void main(String[] args) {
        Login appwin = new Login();
        appwin.setSize(600, 400);
        appwin.setLocationRelativeTo(null);
        appwin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appwin.setResizable(false);

//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            ex.printStackTrace();
//        }
        appwin.setVisible(true);
    }

}

class HelpDialog extends JDialog {

    JLabel ask1;
    JLabel ask2;
    JLabel ask3;
    JLabel ask4;
    JTextArea ans;

    public HelpDialog(JFrame f) {
        super(f, "Help", true);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(0xecf0f1));
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel lblTitle = new JLabel("Chung tâm hỗ trợ");
        lblTitle.setFont(new Font("Consolas", Font.BOLD, 18));
        lblTitle.setForeground(new Color(0x2c3e50));

        JLabel content = new JLabel("Một vài câu hỏi NGU VCL hay gặp của hệ thống "
                + "FPT (FPT Education) FE.");
        content.setFont(new Font("Consolas", Font.BOLD, 12));
        content.setBackground(new Color(0xecf0f1));

        ask1 = new JLabel("1. Làm sao không học mà vẫn giỏi");
        ask1.setFont(new Font("Consolas", Font.ITALIC, 12));
        ask2 = new JLabel("2. Làm sao để đăng nhập mà không có tài khoản");
        ask2.setFont(new Font("Consolas", Font.ITALIC, 12));
        ask3 = new JLabel("3. Cách hack free fire");
        ask3.setFont(new Font("Consolas", Font.ITALIC, 12));
        ask4 = new JLabel("4. Cách hack trường");
        ask4.setFont(new Font("Consolas", Font.ITALIC, 12));

        MyMouseListener mouseListener = new MyMouseListener();
        ask1.addMouseListener(mouseListener);
        ask2.addMouseListener(mouseListener);
        ask3.addMouseListener(mouseListener);
        ask4.addMouseListener(mouseListener);

        JPanel ansPanel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Câu trả lời");
        ans = new JTextArea();
        ans.setEditable(false);
        ans.setBackground(new Color(0xecf0f1));
        ans.setForeground(new Color(0x27ae60));
        ans.setFont(new Font("Consolas", Font.ITALIC, 12));

        ansPanel.add(title, BorderLayout.NORTH);
        ansPanel.add(ans, BorderLayout.SOUTH);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(10, 5, 0, 0);
        add(lblTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(content, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(ask1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(ask2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(ask3, gbc);

        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(ask4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(ansPanel, gbc);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

        });
        setLocationRelativeTo(f);
        setSize(480, 300);
        setVisible(true);

    }

    class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel ansClicked = (JLabel) e.getSource();
            if (ansClicked == ask1) {
                ans.setText("Hết cứu");
            } else if (ansClicked == ask2) {
                ans.setText("Mày không phải sinh viên của trường đúng không? \n"
                        + "Thế thì không có cách nào đâu =)");
            } else if (ansClicked == ask3) {
                ans.setText("Free fire là môn gì =)");
            } else if (ansClicked == ask4) {
                ans.setText("Bạn bị một phiếu tố cáo");
            }
        }

    }

}
