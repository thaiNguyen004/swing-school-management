package view.root;

import view.ultilities.TeacherManagement;
import service.EmployeeManagerServices;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import model.beans.Employee;
import model.ultilities.PutStudent;
import model.beans.Student;
import model.beans.SubjectDetail;
import model.beans.TeachingAssignment;
import service.Service;
import service.StudentService;
import service.SubjectService;
import service.TeacherService;
import view.ultilities.Equipment;
import view.ultilities.LoadingDialog;
import view.login.Login;
import static view.login.Login.load;
import view.ultilities.Management;
import view.ultilities.Success;
import view.ultilities.UtilitiesStudentManager;

public class EmployeeView extends JFrame {

    // panel
    JPanel menuBarLeft;
    JPanel panelRight;
    JPanel panelStudentMan;

    //menuBarLeft
    JLabel lblTitle;
    JButton btnStudentManager;
    JButton btnTeacherManager;
    JButton btnEquipManagement;

    // Font
    Font bigestFont;
    Font bigFont;
    Font normalFont;

    // SERVICES
    EmployeeManagerServices employeeServices;

    // Đối tượng Employee
    Employee employee = null;

    private String username = null;
    private JLabel lblUsername;

    // Layout main
    private GridBagConstraints gbcleft;
    private final GridBagConstraints gbcMain;
    private final GridBagLayout gba;
    private GridBagConstraints consOfStudentMan;
    private JLabel lblTitleStudentMan;

    private JButton btnFilter;
    private JButton btnAddStudent;
    private JButton btnUltilities;

    private JTable tblInfoStudent;
    // Table hiển thị sinh viên
    private DefaultTableModel tableModel;

    private JScrollPane scrollPane;
    // 
    private JComboBox cboHowtoDisplay;
    // Mảng các hình thức hiển thị
    String[] ways;

    // List student hiển thị lên table
    List<Student> listStudentFillTable;
    // Combo box định nghĩa cách hiển thị dữ liệu
    private JComboBox<String> cboHowTo;

    //
    int cboHowToDisplaySelected;
    private JLabel cntStudent;
    private List<TeachingAssignment> teachingAssignment;
    private Map<String, Integer> mapClasses;
    private final TeacherService teacherService;
    private JLabel nameTeacher;
    private final Service service;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private SubjectDetail subjectDetail;
    private JButton btnManagement;
    private boolean isManager;
    private boolean isAdmin = false;
    
    
    public EmployeeView(String user, String role) throws HeadlessException {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Login.load.dispose();
            }
        });
        
        service = new Service();
        // Init teacherService object
        teacherService = new TeacherService();
        // Init studentService object
        studentService = new StudentService();
        // Khởi tạo đối tượng employee
        subjectService = new SubjectService();
        // Khởi tạo đối tượng service 
        employeeServices = new EmployeeManagerServices();

        // Xác định user
        if (user != null) {
            this.username = user;
            employee = employeeServices.createEmployeeObj(username);
            System.out.println("Em: " + employee.getEmployeeID());
            if (service.isManager(employee.getEmployeeID())) {
                isManager = true;
            } else {
                isManager = false;
            }
        } else {
            this.username = null;
            employee = null;
            isManager = true;
            isAdmin = true;
        }

        setTitle("View Nhân viên");
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // definefont
        bigestFont = new Font("Consolas", Font.BOLD, 25);
        bigFont = new Font("Consolas", Font.BOLD, 20);
        normalFont = new Font("Consolas", Font.BOLD, 14);

        // set layout cho chương trình chính
        gba = new GridBagLayout();
        gbcMain = new GridBagConstraints();
        setLayout(gba);
        // Bỏ đường viền khi nhấn vào nút
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        // Đinh nghĩa menuBarLeft
        defineLeftFrame();
        // Định nghĩa thành phần bên phải
        defineRightFrame();

        // set up tiêu đề cho khung bên trái
        initTitleOfLeftFrame();
        // set up tiêu đề giáo viên cho khung trái
        initTitleNameEmployee();

        // Code : Quản lý sinh viên
        // set up nút Quản lý sinh viên cho khung trái
        initBtnStudentManagerOfMenuBarLeft();

        // Student manager frame
        defineStudentManagerFrame();

        // Khởi tạo title của khung myClass
        initlblTitleComp();

        // Khởi tạo nút lọc sinh viên
        initBtnFilterStudent();

        // Khởi tạo nút thêm sinh viên
        initBtnAddStudent();

        // Khởi tạo nút xóa sinh viên
        initBtnUltilitiesStudent();

        // Khởi tạo lựa chọn hiển thị sinh viên
        initCboHowToDisplay();

//        cboHowtoDisplay.addItemListener(e -> {
//            int position = cboHowtoDisplay.getSelectedIndex();
//            switch (position) {
//                case 0 -> { // tất cả
//                    cboHowTo.removeAllItems();
//                }
//                case 1 -> {
//                    if (dbServices.getAllClassCode() != null) {
//                        List<String> classesCode = dbServices.getAllClassCode();
//                        cboHowTo.removeAllItems();
////                        for (String str : classesCode) {
////                            cboHowTo.addItem(str);
////                        }
//                        String[] classes = {"IT183001", "SD183002", "SD183003"};
//                        cboValues(classes);
//                    }
//                }
//                case 2 -> {
//                    Map<String, Integer> specificallyMajor = dbServices.getSpecificallyMap();
//                    if (specificallyMajor != null) {
//                        for (Map.Entry<String, Integer> entry : specificallyMajor.entrySet()) {
//                            cboHowTo.addItem(entry.getKey());
//                        }
//                    }
//                }
//                case 3 -> {
//                    cboHowTo.removeAllItems();
//                    cboHowTo.addItem("Nam");
//                    cboHowTo.addItem("Nữ");
//                }
//                default ->
//                    throw new AssertionError();
//            }
//        }); 
        cboValues();
        cboHowtoDisplay.addActionListener(e -> {
            int position = cboHowtoDisplay.getSelectedIndex();

            if (position == 0) {
                cboValues(new ArrayList<>());
            }
            if (position == 1) {
                // Lấy ra các lớp và môn (phân công giảng dạy)
                teachingAssignment = service.getAllTeachingAssignments();
                if (teachingAssignment != null) {
                    mapClasses = new HashMap<>();
                    for (TeachingAssignment teachingAssignment : teachingAssignment) {
                        String classCode = service.getClassname(
                                teachingAssignment.getTeachingAssignmentID());
                        subjectDetail = subjectService.getSubjectBySubjectDetailID(teachingAssignment.getSubjectID());
                        int teacheingAssignmentID = teachingAssignment.getTeachingAssignmentID();
                        mapClasses.put(classCode + " - " + subjectDetail.getSubjectName(), teacheingAssignmentID);
                    }
                    cboValues(mapClasses);

                }
            }
            if (position == 2) {

                Map<String, Integer> specificallyMajor = service.getSpecificallyMajorMap();
                List<String> res = new ArrayList<>();
                if (specificallyMajor != null) {
                    for (Map.Entry<String, Integer> entry : specificallyMajor.entrySet()) {
                        res.add(entry.getKey());
                    }
                    cboValues(res);
                }
            }
            if (position == 3) {
                cboHowTo.removeAllItems();
                cboValues(new ArrayList<>(Arrays.asList("Nam", "Nữ")));
            }

        }
        );

        cboHowTo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cboHowToDisplaySelected = cboHowtoDisplay.getSelectedIndex();
            }

        });

        // Khởi tạo label hiển thị số sinh viên
        initlblCntOfStudent();

        // Khởi tạo label hiển thị tên giáo viên
        initlblNameTeacher();

        // Lắng nghe sự kiện nút Find
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openLoad();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        fillTable(cboHowtoDisplay.getSelectedIndex());
                    }
                }).start();

            }
        });

        // Lắng nghe sự kiện nút Add
        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openLoad();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        showAddStudentDialog("Thêm sinh viên");
                    }
                }).start();

            }
        });

        btnUltilities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openLoad();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openFindDialog();
                    }
                }).start();

            }
        });

        // Khởi tạo bảng thông tin sinh viên
        initTableDisplayStudent();

        btnStudentManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelRight.setVisible(false);
                panelStudentMan.setVisible(true);
            }
        });

        // Code: GRADEMANAGEMENT
        // set up nút Grade Management cho khung trái
        initbtnTeacherManager();

        btnTeacherManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openLoad();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new TeacherManagement().setVisible(true);
                    }
                }).start();

            }
        });

        // Code: Management
        initBtnManager();
        btnManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayManagement();
            }
        });

        // Code : Equipment Management
        initBtnEquipment();
        btnEquipManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        openLoad();
                    }
                }).start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        displayEquipmentView();
                    }
                }).start();
            }
        });

//        try {
//            Thread.sleep(3000);
//            setVisible(true);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        setVisible(true);
    }

    private void openLoad() {
        load = LoadingDialog.init(this);
        load.setVisible(true);
    }

    private void displayManagement() {
        new Management(this, false, isManager, isAdmin).setVisible(true);
    }

    private ImageIcon getMyImageIcon(String path, int width, int height) {
        ImageIcon originImageIcon = new ImageIcon(path);
        return new ImageIcon(originImageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    private void designButton(JButton originButton, int bgHex, int fgHex, int t, int r, int b, int l) {
        originButton.setBackground(new Color(bgHex));
        originButton.setForeground(new Color(fgHex));
        originButton.setBorder(new EmptyBorder(t, l, b, r));
    }

    // định nghĩa khung bên trái
    private void defineLeftFrame() {
        menuBarLeft = new JPanel(new GridBagLayout());
        gbcleft = new GridBagConstraints();
        menuBarLeft.setPreferredSize(new Dimension(220, 200));
        menuBarLeft.setBackground(new Color(0xecf0f1));
        menuBarLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(0x2c3e50)));

        gbcMain.anchor = GridBagConstraints.NORTHWEST;
        gbcMain.fill = GridBagConstraints.BOTH;

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 0;
        gbcMain.weighty = 1;

        // add 
        add(menuBarLeft, gbcMain);
    }

    // Định nghĩa khung bên phải
    private void defineRightFrame() {
        panelRight = new JPanel();
        panelRight.setBackground(new Color(0x95a5a6));

        gbcMain.gridx = 3;
        gbcMain.gridy = 0;
        gbcMain.weighty = 1;
        gbcMain.weightx = 3;

        // add 
        add(panelRight, gbcMain);
    }

    // CODE CỦA MENU BÊN TRÁI
    // set up tiêu đề cho menu bên trái
    public void initTitleOfLeftFrame() {
        lblTitle = new JLabel("PHÒNG ĐÀO TẠO VÀ CÁN BỘ");
        lblTitle.setForeground(new Color(0x2ecc71));
        lblTitle.setFont(normalFont);

        // add vào khung bên trái
        gbcleft.insets = new Insets(10, 20, 10, 20);
        gbcleft.gridx = 0;
        gbcleft.gridy = 0;
        gbcleft.weighty = 1;

        // add 
        menuBarLeft.add(lblTitle, gbcleft);
    }

    // set up tiêu đề tên nhân viên cho khung trái
    private void initTitleNameEmployee() {
        if (employee != null) {
            lblUsername = new JLabel("Nhân viên " + employee.getEmployeeName());
        } else {
            lblUsername = new JLabel("Developer");
        }
        gbcleft.gridx = 0;
        gbcleft.gridy = 1;

        // add 
        menuBarLeft.add(lblUsername, gbcleft);
    }

    // Quản lý sinh viên
    // setup nút My Class cho khung bên trái
    private void initBtnStudentManagerOfMenuBarLeft() {
        btnStudentManager = new JButton("Quản lý sinh viên");
        // design button
        designButton(btnStudentManager, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);
        btnStudentManager.setFont(normalFont);

        gbcleft.gridx = 0;
        gbcleft.gridy = 2;

        // add 
        menuBarLeft.add(btnStudentManager, gbcleft);
    }

    // Student manager frame
    // Định nghĩa khung Student manager thay thế cho khung bên phải panelRight
    private void defineStudentManagerFrame() {
        panelStudentMan = new JPanel(new GridBagLayout());
        consOfStudentMan = new GridBagConstraints();
        panelStudentMan.setBackground(new Color(0x1abc9c));

        // add 
        add(panelStudentMan, gbcMain);
        panelStudentMan.setVisible(false);
    }

    // Khởi tạo tiêu đề cho khung quản lý sinh viên
    private void initlblTitleComp() {
        // Title of panelMyClass
        lblTitleStudentMan = new JLabel("Chào mừng tới hệ thống quản lý sinh viên");
        lblTitleStudentMan.setFont(bigFont);
        lblTitleStudentMan.setForeground(new Color(0xecf0f1));
        // thêm 
        consOfStudentMan.gridx = 0;
        consOfStudentMan.gridy = 0;
        consOfStudentMan.gridwidth = 4;
        consOfStudentMan.weightx = 1;
        consOfStudentMan.anchor = GridBagConstraints.WEST;
        consOfStudentMan.insets = new Insets(10, 10, 10, 10);

        panelStudentMan.add(lblTitleStudentMan, consOfStudentMan);
    }

    // Khởi tạo nút tìm kiếm sinh viên
    private void initBtnFilterStudent() {
        btnFilter = new JButton("Filter");
        btnFilter.setFont(normalFont);
        designButton(btnFilter, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);

        consOfStudentMan.insets = new Insets(10, 10, 10, 10);
        consOfStudentMan.gridx = 0;
        consOfStudentMan.gridy = 1;
        consOfStudentMan.gridwidth = 1;
        consOfStudentMan.fill = GridBagConstraints.HORIZONTAL;
        panelStudentMan.add(btnFilter, consOfStudentMan);
    }

    // Khởi tạo nút thêm sinh viên
    private void initBtnAddStudent() {
        btnAddStudent = new JButton("Add");
        btnAddStudent.setFont(normalFont);
        designButton(btnAddStudent, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);

        consOfStudentMan.insets = new Insets(10, 10, 10, 10);
        consOfStudentMan.gridx = 1;
        consOfStudentMan.gridy = 1;
        panelStudentMan.add(btnAddStudent, consOfStudentMan);
    }

    // Khởi tạo nút xóa sinh viên
    private void initBtnUltilitiesStudent() {
        btnUltilities = new JButton("Ultilities");
        btnUltilities.setFont(normalFont);
        designButton(btnUltilities, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);

        consOfStudentMan.insets = new Insets(10, 10, 10, 10);
        consOfStudentMan.gridx = 2;
        consOfStudentMan.gridy = 1;
        panelStudentMan.add(btnUltilities, consOfStudentMan);
    }

    private void openFindDialog() {
        new UtilitiesStudentManager(this, 1);
    }

    // Khởi tạo lựa chọn hiển thị sinh viên
    private void initCboHowToDisplay() {
        ways = new String[5];
        String[] a = {"Tất cả", "Theo lớp", "Theo ngành", "Theo giới tính"};
        ways = Arrays.copyOf(a, 4);
        cboHowtoDisplay = new JComboBox(ways);
        //add

        consOfStudentMan.insets = new Insets(10, 10, 10, 10);
        consOfStudentMan.gridx = 0;
        consOfStudentMan.gridy = 2;
        panelStudentMan.add(cboHowtoDisplay, consOfStudentMan);
    }

    // Khởi tạo ô label cách hiển thị
    private void cboValues() {

        cboHowTo = new JComboBox<>();

        // add
        consOfStudentMan.insets = new Insets(10, 10, 10, 10);
        consOfStudentMan.gridx = 1;
        consOfStudentMan.gridy = 2;
        consOfStudentMan.gridwidth = 3;
        panelStudentMan.add(cboHowTo, consOfStudentMan);
    }

    private void cboValues(Map<String, Integer> values) {
        cboHowTo.removeAllItems();

        for (Map.Entry<String, Integer> entry : values.entrySet()) {
            cboHowTo.addItem(entry.getKey());
        }

//        cboHowTo = new JComboBox<>(valuesString);
        consOfStudentMan.insets = new Insets(10, 10, 10, 10);
        consOfStudentMan.gridx = 1;
        consOfStudentMan.gridy = 2;
        consOfStudentMan.gridwidth = 3;
        panelStudentMan.add(cboHowTo, consOfStudentMan);
    }

    private void cboValues(List<String> values) {
        cboHowTo.removeAllItems();

        for (String str : values) {
            cboHowTo.addItem(str);
        }

//        cboHowTo = new JComboBox<>(valuesString);
        consOfStudentMan.insets = new Insets(10, 10, 10, 10);
        consOfStudentMan.gridx = 1;
        consOfStudentMan.gridy = 2;
        consOfStudentMan.gridwidth = 3;
        panelStudentMan.add(cboHowTo, consOfStudentMan);
    }

    //Khởi tạo Table hiển thị danh sách sinh viên
    private void initTableDisplayStudent() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("STT");
        tableModel.addColumn("MSSV");
        tableModel.addColumn("Name");
        tableModel.addColumn("Sex");
        tableModel.addColumn("Batch");
        tableModel.addColumn("Dob");
        tableModel.addColumn("Email");
        tableModel.addColumn("PhoneNumber");
        tableModel.addColumn("Provice");
        tableModel.addColumn("Specifically Major");

        tblInfoStudent = new JTable(tableModel);

        JTableHeader header = tblInfoStudent.getTableHeader();
        header.getColumnModel().getColumn(0).setHeaderValue("STT");
        header.getColumnModel().getColumn(1).setHeaderValue("MSSV");
        header.getColumnModel().getColumn(2).setHeaderValue("Name");
        header.getColumnModel().getColumn(3).setHeaderValue("Sex");
        header.getColumnModel().getColumn(4).setHeaderValue("Batch");
        header.getColumnModel().getColumn(5).setHeaderValue("Dob");
        header.getColumnModel().getColumn(6).setHeaderValue("Email");
        header.getColumnModel().getColumn(7).setHeaderValue("PhoneNumber");
        header.getColumnModel().getColumn(8).setHeaderValue("Provice");
        header.getColumnModel().getColumn(9).setHeaderValue("Specifically Major");

        header.setBackground(new Color(0x3498db));
//        tblInfoStudent.setBackground(new Color(0xbdc3c7));
//        tblInfoStudent.setForeground(new Color(0x34495e));

        tblInfoStudent.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tblInfoStudent);

        fillTable(cboHowtoDisplay.getSelectedIndex());

        consOfStudentMan.gridx = 0;
        consOfStudentMan.gridy = 3;
        consOfStudentMan.gridwidth = 4;
        consOfStudentMan.weightx = 0;
        panelStudentMan.add(scrollPane, consOfStudentMan);
    }

    public void fillTable(int selectedIndex) {
        if (selectedIndex == 0) {
            listStudentFillTable = service.getListFullStudents();
            Login.load.dispose();

        } else if (selectedIndex == 1) {
            if (mapClasses.size() != 0) {
                int teachingAssignmentID
                        = mapClasses.get((String) cboHowTo.getSelectedItem()); // value : ID TeachingAssignments

                listStudentFillTable
                        = service.getListStudentsByClassCode(teachingAssignmentID);
                Login.load.dispose();
                if (listStudentFillTable != null) {
                    nameTeacher.setText(teacherService.
                            getTeacherByTeachingAssignmentID(teachingAssignmentID).getTeacherName());

                }
            }

        } else if (selectedIndex == 2) {
            if (service.getSpecificallyMajorMap() != null) {
                System.out.println("địt con mẹ mày");
                int specificallyMajorID = service.getSpecificallyMajorMap()
                        .get((String) cboHowTo.getSelectedItem());
                listStudentFillTable = service
                        .getListStudentsBySpecificallyMajorID(specificallyMajorID, false);
                Login.load.dispose();
            }
        } else {
            int sex;
            if (((String) cboHowTo.getSelectedItem()).equals("Nam")) {
                sex = 1;
            } else {
                sex = 2;
            }
            listStudentFillTable = service.getListStudentsBySex(sex);
            Login.load.dispose();
        }
        int index;
        if (listStudentFillTable != null) {
            cntStudent.setText(listStudentFillTable.size() + " sinh viên");
            tableModel.setRowCount(0);
            index = 0;
            for (Student o : listStudentFillTable) {
                tableModel.addRow(new Object[]{
                    index++,
                    getMSSV(o.getStudentID()),
                    o.getStudentName(),
                    o.getSex(),
                    o.getBatch(),
                    o.getDob(),
                    o.getEmail(),
                    o.getPhoneNumber(),
                    o.getProvice(),
                    o.getSpecificallyMajorName()
                });
            }
        } else {
            cntStudent.setText("Rỗng");
        }
    }

    private String getMSSV(int StudentID) {
        String defaultStr = "00000" + StudentID;
        String res = "PH" + defaultStr.substring(defaultStr.length() - 5, defaultStr.length());
        return res.toUpperCase();
    }

    private void showAddStudentDialog(String title) {
        new AddStudentDialog(this, title).setVisible(true);
    }

    private void initlblCntOfStudent() {
        cntStudent = new JLabel("Số sinh viên");
        consOfStudentMan.gridx = 0;
        consOfStudentMan.gridy = 4;
        consOfStudentMan.weightx = 1;
        consOfStudentMan.anchor = GridBagConstraints.EAST;
        panelStudentMan.add(cntStudent, consOfStudentMan);

    }

    private void initlblNameTeacher() {
        nameTeacher = new JLabel("Giáo viên?");
        consOfStudentMan.gridx = 1;
        consOfStudentMan.gridy = 4;
        consOfStudentMan.weightx = 1;
        consOfStudentMan.anchor = GridBagConstraints.EAST;
        panelStudentMan.add(nameTeacher, consOfStudentMan);

    }

    // Teacher Manager
    // setup nút quản lý giáo viên cho khung trái
    private void initbtnTeacherManager() {
        btnTeacherManager = new JButton("Quản lý giáo viên");
        // design button
        designButton(btnTeacherManager, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);
        btnTeacherManager.setFont(normalFont);

        gbcleft.gridx = 0;
        gbcleft.gridy = 3;

        // add 
        menuBarLeft.add(btnTeacherManager, gbcleft);
    }

    // Management
    // set up nút mở ra khung quản lý cho khung trái
    private void initBtnManager() {
        btnManagement = new JButton("Management");
        // design button
        designButton(btnManagement, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);
        btnManagement.setFont(normalFont);
        gbcleft.gridx = 0;
        gbcleft.gridy = 4;
        menuBarLeft.add(btnManagement, gbcleft);
    }

    private void initBtnEquipment() {
        btnEquipManagement = new JButton("Equipment Management");
        designButton(btnEquipManagement, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);
        btnEquipManagement.setFont(normalFont);

        gbcleft.gridx = 0;
        gbcleft.gridy = 5;
        menuBarLeft.add(btnEquipManagement, gbcleft);
    }

    private void displayEquipmentView() {
        JDialog a = new JDialog();
        new Equipment(a, false).setVisible(true);
    }

    public static void main(String[] args) {

    }

    class AddStudentDialog extends JDialog {

        private Student student = null;
        private final GridBagLayout gbMainDia;
        private final GridBagConstraints gbcMainDia;
        //
        private JLabel lblTitleDia;
        private JTextField txtNameStudent;
        private JComboBox<String> cboSexSelection;
        private JTextField txtDateOfBirth;
        private JTextField txtPhoneNumber;
        private JTextField txtProvice;
        private JComboBox<Object> cboSpecificallyMajor;
        private Component cboMajor;
        private JFileChooser fileChooser;
        private Image photoImage;
        private ImageIcon imgIconFE;
        private JLabel lblPhoto;
        private JButton btnOpenBrowser;
        private JButton btnReportTo;
        private JPanel containerPhoto;
        private JTextArea txtDescriptor;
        private JLabel errorEnterName;
        private JLabel errorchooseSex;
        private JLabel errorEnterDob;
        private JLabel errorEnterPhoneNumber;
        private JLabel errorEnterProvice;
        private JLabel errorEnterMajor;
        private byte[] data;
        private Map<String, Integer> specificallyMajors;

        // Components
        public AddStudentDialog(JFrame owner, String title) {
            super(owner, title, false);
            // Khởi tạo đối tượng
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    Login.load.dispose();
                }
            });
            setResizable(false);

            setSize(new Dimension(650, 700));
            setLocationRelativeTo(owner);
            getContentPane().setBackground(new Color(0xdff9fb));

            gbMainDia = new GridBagLayout();
            gbcMainDia = new GridBagConstraints();
            setLayout(gbMainDia);

            // Khởi tạo tiêu đề
            initlblTitle();
            // Khởi tạo phần nhập tên sinh viên
            initEnterNameStudent();
            // Khởi tạo phần nhập tên sinh viên
            initChooseSexStudent();
            // Khởi tạo phần nhập ngày sinh sinh viên
            initEnterDateOfBirth();
            // Khởi tạo phần nhập số điện thoại sinh viên
            initEnterPhoneNumber();
            // Khởi tạo phần nhập số điện thoại sinh viên
            initEnterProvice();
            // Khởi tạo phần nhập số điện thoại sinh viên
            initChooseSpecificallyMajor();
            // Khởi tạo ô hiển thị ảnh
            byte[] dataEmpty = new byte[]{};
            initPhotoComp(dataEmpty);
            // Khởi tạo nút browser
            initBtnOpenBrowser();
            btnOpenBrowser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Khởi tạo phần mở File explorer
                    data = initFileExplorer();
                    if (data != null) {
                        initPhotoComp(data);
                    }
                }
            });

            // Khởi tạo ô nhập mô tả
            initTextDescriptor();

            txtNameStudent.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    errorEnterName.setText(" ");
                }

            });

            txtDateOfBirth.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    errorEnterDob.setText(" ");
                }

            });

            txtPhoneNumber.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    errorEnterPhoneNumber.setText(" ");
                }

            });

            txtProvice.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    errorEnterProvice.setText(" ");
                }

            });

            // Khởi tạo nút gửi yêu cầu phê duyệt
            initBtnReportTo();

            btnReportTo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createStudent();
                }
            });

        }

        private void createStudent() {
            if (validateForm()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                int sex;
                if (cboSexSelection.getSelectedItem().equals("Nam")) {
                    sex = 1;
                } else {
                    sex = 2;
                }
                PutStudent o = new PutStudent(txtNameStudent.getText(),
                        sex,
                        LocalDate.parse(txtDateOfBirth.getText(), formatter),
                        txtPhoneNumber.getText(),
                        txtProvice.getText(),
                        data,
                        183,
                        specificallyMajors.get(cboSpecificallyMajor.getSelectedItem()),
                        LocalDate.now(),
                        txtDescriptor.getText());

                // thêm 
                boolean isSuccess = studentService.createStudent(o);
                if (isSuccess) {
                    JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
                    fillTable(cboHowtoDisplay.getSelectedIndex());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm sinh viên thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        private boolean validateForm() {
            int cntError = 0;
            if (txtNameStudent.getText().isEmpty()) {
                cntError++;
                errorEnterName.setText("Vui lòng nhập họ và tên");
            } else {
                errorEnterName.setText(" ");
            }
            if (txtDateOfBirth.getText().isEmpty()) {
                cntError++;
                errorEnterDob.setText("Vui lòng nhập ngày sinh");
            } else {
                try {
                    errorEnterDob.setText(" ");
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dob = LocalDate.parse(txtDateOfBirth.getText(), format);
                } catch (Exception e) {
                    cntError++;
                    errorEnterDob.setText("Định dạng Date không hợp lệ");
                }
            }
            if (txtPhoneNumber.getText().isEmpty()) {
                cntError++;
                errorEnterPhoneNumber.setText("Vui lòng nhập số điện thoại");
            } else {
                String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(txtPhoneNumber.getText());
                if (!matcher.matches()) {
                    cntError++;
                    errorEnterPhoneNumber.setText("Số điện thoại không chuẩn");
                } else {
                    errorEnterPhoneNumber.setText(" ");
                }
            }
            if (txtProvice.getText().isEmpty()) {
                cntError++;
                errorEnterProvice.setText("Vui lòng nhập tỉnh thành phố");
            } else {
                errorEnterProvice.setText(" ");
            }

            if (cntError == 0) {
                return true;
            }
            return false;
        }

        // Khởi tạo tiêu đề 
        private void initlblTitle() {
            lblTitleDia = new JLabel("Thêm sinh viên");
            lblTitleDia.setFont(bigFont);
            lblTitleDia.setForeground(new Color(0x6D214F));

            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 0;
            gbcMainDia.gridwidth = 9;
            add(lblTitleDia, gbcMainDia);
            gbcMainDia.gridwidth = 1;
        }

        // Khởi tạo phần nhập tên sinh viên
        private void initEnterNameStudent() {
            JLabel lbl = new JLabel("Họ và tên");
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 1;
            gbcMainDia.weightx = 1;
            gbcMainDia.anchor = GridBagConstraints.WEST;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(lbl, gbcMainDia);

            txtNameStudent = new JTextField(20);
            txtNameStudent.setMargin(new Insets(3, 3, 3, 3));
            txtNameStudent.setBackground(new Color(0x16a085));
            txtNameStudent.setForeground(new Color(0xecf0f1));
            txtNameStudent.setPreferredSize(new Dimension(280, 35));

            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 2;
            add(txtNameStudent, gbcMainDia);

            errorEnterName = new JLabel(" ");
            errorEnterName.setForeground(new Color(0xd63031));
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 3;
            errorEnterName.setFont(new Font("Consolas", Font.PLAIN, 11));
            gbcMainDia.insets = new Insets(0, 20, 5, 0);
            add(errorEnterName, gbcMainDia);
        }

        // Khởi tạo phần nhập tên sinh viên
        private void initChooseSexStudent() {
            JLabel lbl = new JLabel("Giới tính");
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 4;
            gbcMainDia.insets = new Insets(0, 20, 5, 0);
            add(lbl, gbcMainDia);

            String[] sex = {"Nam", "Nữ"};
            cboSexSelection = new JComboBox<>(sex);
            cboSexSelection.setBackground(new Color(0x16a085));
            cboSexSelection.setForeground(new Color(0xecf0f1));

            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 5;
            gbcMainDia.insets = new Insets(0, 20, 5, 0);
            add(cboSexSelection, gbcMainDia);

        }

        // Khởi tạo phần nhập ngày sinh sinh viên
        private void initEnterDateOfBirth() {
            JLabel lbl = new JLabel("Ngày sinh (yyyy-MM-dd)");
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 7;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(lbl, gbcMainDia);

            txtDateOfBirth = new JTextField(20);
            txtDateOfBirth.setPreferredSize(new Dimension(280, 35));
            txtDateOfBirth.setMargin(new Insets(3, 3, 3, 3));
            txtDateOfBirth.setBackground(new Color(0x16a085));
            txtDateOfBirth.setForeground(new Color(0xecf0f1));

            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 8;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(txtDateOfBirth, gbcMainDia);

            errorEnterDob = new JLabel(" ");
            errorEnterDob.setForeground(new Color(0xd63031));
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 9;
            errorEnterDob.setFont(new Font("Consolas", Font.PLAIN, 11));
            gbcMainDia.insets = new Insets(0, 20, 5, 0);
            add(errorEnterDob, gbcMainDia);
        }

        // Khởi tạo phần nhập số điện thoại sinh viên
        private void initEnterPhoneNumber() {
            JLabel lbl = new JLabel("Số điện thoại");
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 10;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(lbl, gbcMainDia);

            txtPhoneNumber = new JTextField(20);
            txtPhoneNumber.setPreferredSize(new Dimension(280, 35));
            txtPhoneNumber.setMargin(new Insets(3, 3, 3, 3));
            txtPhoneNumber.setBackground(new Color(0x16a085));
            txtPhoneNumber.setForeground(new Color(0xecf0f1));

            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 11;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(txtPhoneNumber, gbcMainDia);

            errorEnterPhoneNumber = new JLabel(" ");
            errorEnterPhoneNumber.setForeground(new Color(0xd63031));
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 12;
            errorEnterPhoneNumber.setFont(new Font("Consolas", Font.PLAIN, 11));
            gbcMainDia.insets = new Insets(0, 20, 5, 0);
            add(errorEnterPhoneNumber, gbcMainDia);
        }

        // Khởi tạo phần nhập số điện thoại sinh viên
        private void initEnterProvice() {
            JLabel lbl = new JLabel("Tỉnh / Thành phố");
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 13;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(lbl, gbcMainDia);

            txtProvice = new JTextField(20);
            txtProvice.setPreferredSize(new Dimension(280, 35));
            txtProvice.setMargin(new Insets(3, 3, 3, 3));
            txtProvice.setBackground(new Color(0x16a085));
            txtProvice.setForeground(new Color(0xecf0f1));

            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 14;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(txtProvice, gbcMainDia);

            errorEnterProvice = new JLabel(" ");
            errorEnterProvice.setForeground(new Color(0xd63031));
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 15;
            errorEnterProvice.setFont(new Font("Consolas", Font.PLAIN, 11));
            gbcMainDia.insets = new Insets(0, 20, 5, 0);
            add(errorEnterProvice, gbcMainDia);
        }

        // Khởi tạo phần nhập số điện thoại sinh viên
        private void initChooseSpecificallyMajor() {
            JLabel lbl = new JLabel("Chuyên ngành hẹp");
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 16;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(lbl, gbcMainDia);

            // Chuyên ngành hẹp
            List<String> specificallyMajorName = new ArrayList<>();
            specificallyMajors = service.getSpecificallyMajorMap();
            if (specificallyMajors != null) {
                for (Map.Entry<String, Integer> entry : specificallyMajors.entrySet()) {
                    specificallyMajorName.add(entry.getKey());
                }
            }

            cboSpecificallyMajor = new JComboBox<>(specificallyMajorName.toArray(new String[0]));
            cboSpecificallyMajor.setPreferredSize(new Dimension(280, 35));
            cboSpecificallyMajor.setBackground(new Color(0x16a085));
            cboSpecificallyMajor.setForeground(new Color(0xecf0f1));

            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 17;
            gbcMainDia.insets = new Insets(0, 20, 5, 0);
            add(cboSpecificallyMajor, gbcMainDia);

        }

        // Khởi tạo phần mở File explorer
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
                    new Success(this, 0, "Thêm ảnh thành công", "Thêm ảnh thất bại", "Bạn hãy chọn ảnh có định dạng là .png");
                    System.out.println(e);
                }
            }
            return null;
        }

        // Khởi tạo ô hiển thị ảnh
        private void initPhotoComp(byte[] data) {
            if (data.length != 0) {
                try (ByteArrayInputStream bin = new ByteArrayInputStream(data);) {
                    photoImage = ImageIO.read(bin).getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                imgIconFE = new ImageIcon(photoImage);
                lblPhoto.setText("");
                lblPhoto.setIcon(imgIconFE);
            } else {
                containerPhoto = new JPanel(new BorderLayout());
                lblPhoto = new JLabel("NOTHING", JLabel.CENTER);
                lblPhoto.setForeground(new Color(0xecf0f1));
                containerPhoto.add(lblPhoto);
                containerPhoto.setPreferredSize(new Dimension(120, 120));
                containerPhoto.setBackground(new Color(0x16a085));
            }

            gbcMainDia.gridx = 2;
            gbcMainDia.gridy = 2;
            gbcMainDia.gridheight = 8;
            gbcMainDia.anchor = GridBagConstraints.FIRST_LINE_START;
            add(containerPhoto, gbcMainDia);

        }

        // Khởi tạo nút browser
        private void initBtnOpenBrowser() {
            btnOpenBrowser = new JButton("Browser");
            designButton(btnOpenBrowser, 0xe67e22, 0xecf0f1, 10, 36, 10, 36);
            gbcMainDia.gridx = 2;
            gbcMainDia.gridy = 8;
            gbcMainDia.gridheight = 1;
            add(btnOpenBrowser, gbcMainDia);
        }

        // Khởi tạo ô nhập mô tả
        private void initTextDescriptor() {
            JLabel lbl = new JLabel("Mô tả");
            gbcMainDia.gridx = 2;
            gbcMainDia.gridy = 10;
            gbcMainDia.gridheight = 2;
            gbcMainDia.insets = new Insets(0, 20, 0, 0);
            add(lbl, gbcMainDia);

            txtDescriptor = new JTextArea(10, 35);
            txtDescriptor.setLineWrap(true);
            txtDescriptor.setWrapStyleWord(true);
            txtDescriptor.setBackground(new Color(0x16a085));
            txtDescriptor.setFont(new Font("Consolas", Font.PLAIN, 11));
            txtDescriptor.setForeground(new Color(0xecf0f1));
            txtDescriptor.setMargin(new Insets(3, 3, 3, 3));

            ((AbstractDocument) txtDescriptor.getDocument()).setDocumentFilter(new DocumentFilter() {
                @Override
                public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                        throws BadLocationException {
                    if ((fb.getDocument().getLength() + string.length()) <= 255) {
                        super.insertString(fb, offset, string, attr);
                    }
                }

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    if ((fb.getDocument().getLength() - length + text.length()) <= 255) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            });

            gbcMainDia.gridx = 2;
            gbcMainDia.gridy = 11;
            gbcMainDia.gridheight = 5;
            add(txtDescriptor, gbcMainDia);
        }

        // Khởi tạo nút gửi yêu cầu phê duyệt
        private void initBtnReportTo() {
            btnReportTo = new JButton("Request");
            designButton(btnReportTo, 0x6F1E51, 0xecf0f1, 10, 36, 10, 36);

            gbcMainDia.gridx = 2;
            gbcMainDia.gridy = 17;
            gbcMainDia.gridheight = 1;
            add(btnReportTo, gbcMainDia);
        }

    }

}
