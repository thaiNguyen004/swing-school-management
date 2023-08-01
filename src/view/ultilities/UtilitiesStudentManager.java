package view.ultilities;

import view.login.Login;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ultilities.PutStudent;
import model.beans.Student;
import service.EmployeeManagerServices;
import service.Service;
import service.StudentService;

public class UtilitiesStudentManager extends JDialog {

    private GridBagConstraints gbcServiceDia;
    private GridBagLayout gbgServiceDia;
    private int type;
    private JTextField txtEnterMssv;
    private JButton btnSubmit;
    private List<JLabel> listLabel;
    private Service service;
    private List<Student> listStudent;
    private JLabel[] txtRange;
    private JLabel lblTitle;
    private JPanel panelPhoto;
    private JLabel lblPhoto;
    private JLabel lbltitle01;
    private JLabel lblStudentCode;
    private JLabel lbltitle02;
    private JTextField lblStudentName;
    private JLabel lbltitle03;
    private JTextField lblSex;
    private JLabel lbltitle04;
    private JTextField lblBatch;
    private JLabel lbltitle05;
    private JTextField lblDob;
    private JLabel lbltitle06;
    private JTextPane lblDescriptor;
    private JLabel lbltitle07;
    private JLabel lblEmail;
    private JLabel lbltitle08;
    private JTextField lblPhoneNumer;
    private JLabel lbltitle09;
    private JTextField lblProvice;
    private JComboBox lblMajor;
    private ImageIcon img;
    private JButton btnUpdateStudent;
    private JButton btnDeleteStudent;
    private JScrollBar jsc;
    private JScrollPane jsp;
    private JPanel panelTxtRange;
    private JFileChooser fileChooser;
    private JButton btnBrowser;
    private byte[] data;
    private ArrayList<Object> majorName;
    private JComboBox<Object> cboSex;
    private StudentService studentService;

    public UtilitiesStudentManager(JDialog owner, int type) {
        super(owner, false);
        setLocationRelativeTo(null);
        new UtilitiesStudentManager(type);
    }

    public UtilitiesStudentManager(JFrame owner, int type) {
        super(owner, false);
        setLocationRelativeTo(null);
        new UtilitiesStudentManager(type);
    }

    public UtilitiesStudentManager(int type) {
        setSize(new Dimension(700, 800));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Login.load.dispose();
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        this.type = type;
        setTitle("Ultilities");
        try {
            setIconImage(new ImageIcon(ImageIO.read(new File("E:\\SwingIcon\\breakPoint.png"))).getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        service = new Service();
        studentService = new StudentService();
        getContentPane().setBackground(new Color(0x2980b9));
        setLocationRelativeTo(null);
        gbgServiceDia = new GridBagLayout();
        gbcServiceDia = new GridBagConstraints();
        setLayout(gbgServiceDia);

        // Khởi tạo nút nhập mã số sinh viên
        initTextEnterStudentID();

        // Khởi tạo nút submit
        initbtnSubmit();

        // Khởi tạo vùng chứa chữ
        intTextRange();

        // Lắng nghe sự kiện nhập liệu ô tìm kiếm
        txtEnterMssv.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                listStudent = service.searchStudent(txtEnterMssv.getText());
                if (listStudent != null && listStudent.size() != 0) {
                    txtRange[0].setText("");
                    txtRange[1].setText("");
                    txtRange[2].setText("");
                    txtRange[3].setText("");
                    txtRange[4].setText("");
                    generateListStudentID(listStudent);

                    txtRange[0].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                            txtRange[0].setForeground(new Color(0xd35400));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            txtRange[0].setForeground(new Color(0x2c3e50));
                        }

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (!txtRange[0].getText().isEmpty()) {
                                txtRange[0].setText("");
                                txtRange[1].setText("");
                                txtRange[2].setText("");
                                txtRange[3].setText("");
                                txtRange[4].setText("");
                                display(listStudent.get(0));
                            }
                        }
                    });
                } else {
                    txtRange[0].setText("Not Found");
                    txtRange[1].setText("");
                    txtRange[2].setText("");
                    txtRange[3].setText("");
                    txtRange[4].setText("");

                }
            }
        });

        txtRange[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtRange[1].setForeground(new Color(0xd35400));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtRange[1].setForeground(new Color(0x2c3e50));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!txtRange[1].getText().isEmpty()) {
                    txtRange[0].setText("");
                    txtRange[1].setText("");
                    txtRange[2].setText("");
                    txtRange[3].setText("");
                    txtRange[4].setText("");
                    display(listStudent.get(1));
                }
            }

        });

        txtRange[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtRange[2].setForeground(new Color(0xd35400));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtRange[2].setForeground(new Color(0x2c3e50));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!txtRange[2].getText().isEmpty()) {
                    txtRange[0].setText("");
                    txtRange[1].setText("");
                    txtRange[2].setText("");
                    txtRange[3].setText("");
                    txtRange[4].setText("");
                    display(listStudent.get(2));
                }
            }
        });

        txtRange[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtRange[3].setForeground(new Color(0xd35400));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtRange[3].setForeground(new Color(0x2c3e50));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!txtRange[3].getText().isEmpty()) {
                    txtRange[0].setText("");
                    txtRange[1].setText("");
                    txtRange[2].setText("");
                    txtRange[3].setText("");
                    txtRange[4].setText("");
                    display(listStudent.get(3));
                }
            }
        });

        txtRange[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                txtRange[4].setForeground(new Color(0xd35400));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                txtRange[4].setForeground(new Color(0x2c3e50));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!txtRange[4].getText().isEmpty()) {
                    txtRange[0].setText("");
                    txtRange[1].setText("");
                    txtRange[2].setText("");
                    txtRange[3].setText("");
                    txtRange[4].setText("");
                    display(listStudent.get(4));
                }
            }
        });

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listStudent != null && listStudent.size() != 0) {
                    txtRange[0].setText("");
                    txtRange[1].setText("");
                    txtRange[2].setText("");
                    txtRange[3].setText("");
                    txtRange[4].setText("");
                    display(listStudent.get(0));
                }
            }
        });

        display();

        btnBrowser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                data = initFileExplorer();
                if (data != null) {
                    System.out.println("Có đa ta");
                    initPhotoComp(data);
                }
            }
        });

        // Khởi tạo nút sửa sinh viên
        initUpdateStudent();
        // Khởi tạo nút xóa sinh viên
        initDeleteStudent();

        btnUpdateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        btnDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentCode = lblStudentCode.getText();
                int id = Integer.valueOf(studentCode.substring(2, studentCode.length()));
                studentService.deleleStudent(id);
                showSuccess();
            }
        });

        setVisible(true);
    }

    private void updateStudent() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String studentCode = lblStudentCode.getText();
        int id = Integer.valueOf(studentCode.substring(2, studentCode.length()));
        int sex = 0;
        if (cboSex.getSelectedIndex() == 0) {
            sex = 1;
        } else {
            sex = 2;
        }
        PutStudent o = new PutStudent(
                id,
                lblStudentName.getText(),
                sex,
                LocalDate.parse(lblDob.getText(), format),
                lblPhoneNumer.getText(),
                lblProvice.getText(),
                data,
                183,
                service.getSpecificallyMajorMap().get(lblMajor.getSelectedItem()),
                LocalDate.parse(LocalDate.now().toString(), format),
                lblDescriptor.getText());
        if (studentService.updateStudent(o)) {
            JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thất bại");
        }
    }

    private void showSuccess() {
        new Success(this, 1, "Xóa thành công", "Xóa thất bại", "Hết cứu");
    }

    private String getMSSV(int StudentID) {
        String defaultStr = "00000" + StudentID;
        String res = "PH" + defaultStr.substring(defaultStr.length() - 5, defaultStr.length());
        return res.toUpperCase();
    }

    private void initTextEnterStudentID() {
        JLabel lbl = new JLabel("MSSV / Student ID");
        lbl.setForeground(new Color(0xecf0f1));
        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 0;
        gbcServiceDia.weightx = 1;
        gbcServiceDia.anchor = GridBagConstraints.NORTHWEST;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);

        add(lbl, gbcServiceDia);

        txtEnterMssv = new JTextField();
        txtEnterMssv.setPreferredSize(new Dimension(400, 25));
        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 1;
        gbcServiceDia.gridwidth = 2;
        gbcServiceDia.weightx = 0;
        add(txtEnterMssv, gbcServiceDia);
        gbcServiceDia.gridwidth = 1;
    }

    private void initbtnSubmit() {
        btnSubmit = new JButton("Submit");
        designButton(btnSubmit, 0x2c3e50, 0xf5f6fa, 5, 5, 5, 5);
        gbcServiceDia.gridx = 2;
        gbcServiceDia.gridy = 1;
        gbcServiceDia.weightx = 1;
        gbcServiceDia.insets = new Insets(10, 10, 0, 10);
        gbcServiceDia.fill = GridBagConstraints.HORIZONTAL;
        add(btnSubmit, gbcServiceDia);
    }

    private void intTextRange() {
        panelTxtRange = new JPanel(new GridLayout(5, 1));
        txtRange = new JLabel[5];
        txtRange[0] = new JLabel("Not Found");
        txtRange[0].setForeground(new Color(0x2c3e50));

        for (int i = 1; i < 5; i++) {
            txtRange[i] = new JLabel();
            txtRange[i].setForeground(new Color(0x2c3e50));
        }

        panelTxtRange.add(txtRange[0]);
        panelTxtRange.add(txtRange[1]);
        panelTxtRange.add(txtRange[2]);
        panelTxtRange.add(txtRange[3]);
        panelTxtRange.add(txtRange[4]);

        jsp = new JScrollPane(panelTxtRange);
        jsp.setPreferredSize(new Dimension(200, 80));
        jsp.setBackground(new Color(0x34495e));

        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 2;
        gbcServiceDia.weightx = 1;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.fill = GridBagConstraints.NONE;
        add(jsp, gbcServiceDia);
//
//        gbcServiceDia.gridx = 0;
//        gbcServiceDia.gridy = 3;
//        gbcServiceDia.weightx = 1;
//        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
//        gbcServiceDia.fill = GridBagConstraints.NONE;
//        add(txtRange[1], gbcServiceDia);
//
//        gbcServiceDia.gridx = 0;
//        gbcServiceDia.gridy = 4;
//        gbcServiceDia.weightx = 1;
//        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
//        gbcServiceDia.fill = GridBagConstraints.NONE;
//        add(txtRange[2], gbcServiceDia);
//
//        gbcServiceDia.gridx = 0;
//        gbcServiceDia.gridy = 5;
//        gbcServiceDia.weightx = 1;
//        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
//        gbcServiceDia.fill = GridBagConstraints.NONE;
//        add(txtRange[3], gbcServiceDia);
//
//        gbcServiceDia.gridx = 0;
//        gbcServiceDia.gridy = 6;
//        gbcServiceDia.weightx = 1;
//        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
//        gbcServiceDia.fill = GridBagConstraints.NONE;
//        add(txtRange[4], gbcServiceDia);
    }

    private void generateListStudentID(List<Student> list) {
        Iterator<Student> it = list.iterator();
        listLabel = new ArrayList<>();
        while (it.hasNext()) {
            Student student = it.next();
            listLabel.add(new JLabel(getMSSV(student.getStudentID())));
        }

        for (int i = 0; i < listLabel.size() && i < 5; i++) {
            txtRange[i].setText(listLabel.get(i).getText());
        }
    }

    private void designButton(JButton originButton, int bgHex, int fgHex, int t, int r, int b, int l) {
        originButton.setBackground(new Color(bgHex));
        originButton.setForeground(new Color(fgHex));
        originButton.setBorder(new EmptyBorder(t, l, b, r));
    }

    private void display(Student o) {
        lblStudentCode.setText(getMSSV(o.getStudentID()));
        lblStudentName.setText(o.getStudentName());
        if (o.getSex() == 1) {
            cboSex.setSelectedIndex(0);
        } else if (o.getSex() == 2) {
            cboSex.setSelectedIndex(1);
        }
        String batch = o.getBatch() + "";
        lblBatch.setText("Khóa " + batch.substring(0, batch.length() - 1)
                + "." + batch.substring(batch.length() - 1));
        lblDob.setText(o.getDob().toString());
        lblEmail.setText(o.getEmail());
        lblPhoneNumer.setText(o.getPhoneNumber());
        lblProvice.setText(o.getProvice());
        lblMajor.setSelectedItem(o.getSpecificallyMajorName());
        if (o.getDescriptor() == null) {
            lblDescriptor.setText("Nah");
        } else {
            if (o.getDescriptor().isEmpty()) {
                lblDescriptor.setText("Nah");
            } else {
                lblDescriptor.setText(o.getDescriptor());
            }
        }
        if (o.getPhoto() != null) {
            data = o.getPhoto();
            try (ByteArrayInputStream bin = new ByteArrayInputStream(data)) {
                Image image = ImageIO.read(bin);
                if (image != null) {
                    lblPhoto.setIcon(new ImageIcon(image.getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
                } else {
                    lblPhoto.setIcon(new ImageIcon());
                }

            } catch (IOException e) {
                System.out.println("Lỗi UtilitiesDialog" + e.getMessage());
            }

        }
    }

    private void display() {
        lblTitle = new JLabel("Thông tin sinh viên");
        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 6;
        gbcServiceDia.weightx = 0;
        gbcServiceDia.insets = new Insets(50, 10, 0, 0);
        lblTitle.setForeground(new Color(0xf5f6fa));
        lblTitle.setFont(new Font("Consolas", Font.BOLD, 18));
        add(lblTitle, gbcServiceDia);

        panelPhoto = new JPanel(new BorderLayout());
        panelPhoto.setPreferredSize(new Dimension(120, 120));
        panelPhoto.setBackground(new Color(0x718093));

        lblPhoto = new JLabel();
        panelPhoto.add(lblPhoto, BorderLayout.CENTER);

        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 7;
        gbcServiceDia.gridheight = 5;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        add(panelPhoto, gbcServiceDia);

        btnBrowser = new JButton("Browser");
        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 12;
        gbcServiceDia.gridheight = 1;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        add(btnBrowser, gbcServiceDia);

        lbltitle06 = new JLabel("Descriptor");
        lbltitle06.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle06.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 13;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle06, gbcServiceDia);

        lblDescriptor = new JTextPane();
        lblDescriptor.setText("Nah");
        lblDescriptor.setBackground(new Color(0x2980b9));
        lblDescriptor.setPreferredSize(new Dimension(200, 80));
        lblDescriptor.setFont(new Font("Consolas", Font.BOLD, 10));
        lblDescriptor.setBackground(new Color(0x34495e));
        lblDescriptor.setForeground(new Color(0x2ecc71));
        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 14;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.gridheight = 7;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        add(lblDescriptor, gbcServiceDia);

        lbltitle01 = new JLabel("MSSV / StudentID");
        lbltitle01.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle01.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 7;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle01, gbcServiceDia);

        lblStudentCode = new JLabel("Nah");
        lblStudentCode.setFont(new Font("Consolas", Font.BOLD, 14));
        lblStudentCode.setForeground(new Color(0x2ecc71));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 8;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lblStudentCode, gbcServiceDia);

        lbltitle02 = new JLabel("Họ và tên");
        lbltitle02.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle02.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 9;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle02, gbcServiceDia);

        lblStudentName = new JTextField("Nah");
        lblStudentName.setPreferredSize(new Dimension(250, 20));
        lblStudentName.setBackground(new Color(0x2980b9));
        lblStudentName.setFont(new Font("Consolas", Font.BOLD, 14));
        lblStudentName.setForeground(new Color(0x2ecc71));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 10;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lblStudentName, gbcServiceDia);

        lbltitle03 = new JLabel("Sex");
        lbltitle03.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle03.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 11;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle03, gbcServiceDia);

        cboSex = new JComboBox<>();
        cboSex.addItem("Nam");
        cboSex.addItem("Nữ");
        cboSex.setPreferredSize(new Dimension(250, 20));
        cboSex.setFont(new Font("Consolas", Font.BOLD, 14));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 12;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(cboSex, gbcServiceDia);

        lbltitle04 = new JLabel("Batch");
        lbltitle04.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle04.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 13;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle04, gbcServiceDia);

        lblBatch = new JTextField("Nah");
        lblBatch.setPreferredSize(new Dimension(250, 20));
        lblBatch.setEditable(false);
        lblBatch.setBackground(new Color(0x2980b9));
        lblBatch.setFont(new Font("Consolas", Font.BOLD, 14));
        lblBatch.setForeground(new Color(0x2ecc71));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 14;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lblBatch, gbcServiceDia);

        lbltitle05 = new JLabel("Date of Birth");
        lbltitle05.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle05.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 15;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle05, gbcServiceDia);

        lblDob = new JTextField("Nah");
        lblDob.setPreferredSize(new Dimension(250, 20));
        lblDob.setEditable(false);
        lblDob.setBackground(new Color(0x2980b9));
        lblDob.setFont(new Font("Consolas", Font.BOLD, 14));
        lblDob.setForeground(new Color(0x2ecc71));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 16;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lblDob, gbcServiceDia);

        lbltitle07 = new JLabel("Email");
        lbltitle07.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle07.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 17;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle07, gbcServiceDia);

        lblEmail = new JLabel("Nah");
        lblEmail.setPreferredSize(new Dimension(250, 20));
        lblEmail.setBackground(new Color(0x2980b9));
        lblEmail.setFont(new Font("Consolas", Font.BOLD, 14));
        lblEmail.setForeground(new Color(0x2ecc71));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 18;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lblEmail, gbcServiceDia);

        lbltitle08 = new JLabel("PhoneNumber");
        lbltitle08.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle08.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 19;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle08, gbcServiceDia);

        lblPhoneNumer = new JTextField("Nah");
        lblPhoneNumer.setPreferredSize(new Dimension(250, 20));
        lblPhoneNumer.setBackground(new Color(0x2980b9));
        lblPhoneNumer.setFont(new Font("Consolas", Font.BOLD, 14));
        lblPhoneNumer.setForeground(new Color(0x2ecc71));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 20;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lblPhoneNumer, gbcServiceDia);

        lbltitle09 = new JLabel("Provice");
        lbltitle09.setFont(new Font("Consolas", Font.BOLD, 14));
        lbltitle09.setForeground(new Color(0xf1c40f));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 21;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lbltitle09, gbcServiceDia);

        lblProvice = new JTextField("Nah");
        lblProvice.setPreferredSize(new Dimension(250, 20));
        lblProvice.setBackground(new Color(0x2980b9));
        lblProvice.setFont(new Font("Consolas", Font.BOLD, 14));
        lblProvice.setForeground(new Color(0x2ecc71));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 22;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(5, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lblProvice, gbcServiceDia);

        majorName = new ArrayList<>();
        if (service.getSpecificallyMajorMap() != null) {
            for (Map.Entry<String, Integer> entry : service.getSpecificallyMajorMap().entrySet()) {
                majorName.add(entry.getKey());
            }
        }

        lblMajor = new JComboBox<>();
        for (Object str : majorName) {
            lblMajor.addItem((String) str);
        }
        lblMajor.setFont(new Font("Consolas", Font.BOLD, 14));
        gbcServiceDia.gridx = 1;
        gbcServiceDia.gridy = 23;
        gbcServiceDia.weightx = 2;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.anchor = GridBagConstraints.WEST;
        gbcServiceDia.gridheight = 1;
        add(lblMajor, gbcServiceDia);

    }

    // Khởi tạo nút sửa sinh viên
    private void initUpdateStudent() {
        btnUpdateStudent = new JButton("Update");
        designButton(btnUpdateStudent, 0xd35400, 0xecf0f1, 5, 5, 5, 5);
        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 20;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.gridheight = 1;
        add(btnUpdateStudent, gbcServiceDia);
    }

//    private void initUpdateStudent() {
//        btnUpdateStudent = new JButton("Update");
//        designButton(btnUpdateStudent, 0xd35400, 0xecf0f1, 5, 5, 5, 5);
//        gbcServiceDia.gridx = 0;
//        gbcServiceDia.gridy = 20;
//        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
//        gbcServiceDia.gridheight = 1;
//        add(btnUpdateStudent, gbcServiceDia);
//    }
    // Khởi tạo nút xóa sinh viên
    private void initDeleteStudent() {
        btnDeleteStudent = new JButton("Delete");
        designButton(btnDeleteStudent, 0xd35400, 0xecf0f1, 5, 5, 5, 5);
        gbcServiceDia.gridx = 0;
        gbcServiceDia.gridy = 21;
        gbcServiceDia.insets = new Insets(10, 10, 0, 0);
        gbcServiceDia.gridheight = 1;
        add(btnDeleteStudent, gbcServiceDia);

    }

    private byte[] initFileExplorer() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter
                = new FileNameExtensionFilter("Images",
                        "jpg",
                        "jpeg",
                        "png",
                        "gif");
        fileChooser.setFileFilter(filter);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Đọc dữ liệu từ tệp ảnh và chuyển thành mảng byte
                FileInputStream in = new FileInputStream(selectedFile);
                Image img = ImageIO.read(in);

                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                ImageIO.write((RenderedImage) img, "png", bout);
                return bout.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void initPhotoComp(byte[] data) {
        try (ByteArrayInputStream bin = new ByteArrayInputStream(data)) {
            Image img = ImageIO.read(bin);
            if (img != null) {
                lblPhoto.setIcon(
                        new ImageIcon(img.getScaledInstance(120, 120, Image.SCALE_SMOOTH)));
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        JFrame a = new JFrame();
        a.setLocationRelativeTo(null);
        new UtilitiesStudentManager(a, 1);
    }

}
