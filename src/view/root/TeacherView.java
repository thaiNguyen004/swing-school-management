package view.root;

import service.TeacherService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.beans.Teacher;
import model.beans.Student;
import model.beans.TeachingAssignment;
import service.Service;
import service.StudentService;
import view.ultilities.GradeManagement;
import view.ultilities.LoadingDialog;
import view.login.Login;
import static view.login.Login.load;

public class TeacherView extends JFrame {

    // panel 
    JPanel panelLeft;
    JPanel panelRight;
    JPanel panelMyClass;

    // MenuBar
    private JPanel panelMenuBar;
    JMenuBar menuBar;
    private JMenu jmEdit;
    private JMenu jmSearch;
    private JMenu jmMyProfily;

    //menuBarLeft
    JLabel lblTitle;
    JButton btnMyClasse;
    JButton btnSetGrade;
    JButton btnAttendance;

    // Font
    Font bigestFont;
    Font bigFont;
    Font normalFont;

    // variable của MyClass frame
    private JLabel lblTitleMyClass;
    private JComboBox<Object> cboListClasses;
    private JLabel lblCntClass;
    private JLabel lblClassIsSelected;
    private JTable tblInfoStudent;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    String[] classes = null;
    private JLabel lblCntOfStudent;
    int TeachingAssignmentIDSelected;

    // SERVICES
    TeacherService teacherServices;

    // Đối tượng Employee
    Teacher teacher = null;

    // List danh sách công việc của giáo viên
    private List<TeachingAssignment> listAssignments = null;

    private String username = null;
    private JLabel lblUsername;

    // Layout
    private GridBagConstraints consOfMyClass;
    private GridBagConstraints gbcleft;
    private final GridBagConstraints gbcMain;
    private final GridBagLayout gba;
    private final MyMouseListener listener;
    private final Service service;

    public TeacherView(String user) throws HeadlessException {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Login.load.dispose();
            }
        });

        // Xác định user    
        this.username = user;

        // khởi tạo service
        // Khởi tạo đối tượng teacher
        service = new Service();
        teacherServices = new TeacherService();

        teacher = teacherServices.createTeacherObj(username);

        listAssignments = service.getTeachingAssignments(teacher.getTeacherID());

        setTitle("View Giáo viên");
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

//        initMenuBar();
        // Đinh nghĩa thành phần bên trái
        defineLeftFrame();
        // Định nghĩa thành phần bên phải
        defineRightFrame();

        // set up tiêu đề cho khung bên trái
        initTitleOfLeftFrame();
        // set up tiêu đề giáo viên cho khung trái
        initTitleNameTeacher();

        // Code : MYCLASS
        // set up nút myClass cho khung trái
        initBtnMyClassOfLeftFrame();

        // MY CLASS FRAME
        defineMyClassFrame();
        // Khởi tạo title của khung myClass
        initLabelTitleComp();
        // Khởi tạo JCombobox danh sách lớp học cho khung MyClass
        initCboListClassesComp();

        panelRight.setVisible(false);
        btnMyClasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelRight.setVisible(false);
            }
        });

        // khởi tạo và định nghĩa Jlabel hiển thị số lớp đang dạy vào khung myClass
        initTextCountClass();
        // khởi tạo và định nghĩa Jlabel hiển thị lớp đang chỉ định
        initTextClassIsSelected();

        initAndUpdateTableForMyClassFrame();

        // lắng nghe sự kiện itemevent
        cboListClasses.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                currentSelected();
                int selectedIndex = cboListClasses.getSelectedIndex();
                TeachingAssignmentIDSelected = listAssignments.get(selectedIndex).getTeachingAssignmentID();
                fillTable(TeachingAssignmentIDSelected);
                lblCntOfStudent.setText("Sĩ số: " + tableModel.getRowCount());
            }
        });

        //Khởi tạo và định nghĩa sĩ số học sinh bên dưới JTable
        initCountOfStudent();

        // Lắng nghe sự kiện khi click vào 1 row trong bảng
        listener = new MyMouseListener();
        tblInfoStudent.addMouseListener(listener);

        // Code: GRADEMANAGEMENT
        // set up nút Grade Management cho khung trái
        initbtnGradeManagementOfLeftFrame();

        btnSetGrade.addActionListener(new ActionListener() {
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
                        openDialog();
                    }
                }).start();
                
            }
        });

        // Code: ATTENDANCE
        initBtnAttendanceOfLeftFrame();

        setVisible(true);

//        try {
//            Thread.sleep(3000);
//            setVisible(true);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new StudentDialog(this, 1);
    }

    class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int index = tblInfoStudent.getSelectedRow();
            int selectedIndex = cboListClasses.getSelectedIndex();
            TeachingAssignmentIDSelected = listAssignments.get(selectedIndex).getTeachingAssignmentID();
            int studentID
                    = service
                            .getStudentParticipations(TeachingAssignmentIDSelected, 1)
                            .get(index).getStudentID();

            DisplayDialog(studentID);
        }

    }
    
    private void openLoad() {
        load = LoadingDialog.init(this);
        load.setVisible(true);
    }

    private void openDialog() {
        new GradeManagement(this, false, username).setVisible(true);
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

    private void initMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0x9b59b6));
        jmEdit = new JMenu("Edit");
        jmSearch = new JMenu("Search");
        jmMyProfily = new JMenu("My profile");

        menuBar.add(jmEdit);
        menuBar.add(jmSearch);
        menuBar.add(jmMyProfily);

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
//        gbcMain.fill = GridBagConstraints.BOTH;
        add(menuBar, gbcMain);

    }

    // định nghĩa khung bên trái
    private void defineLeftFrame() {
        panelLeft = new JPanel(new GridBagLayout());
        gbcleft = new GridBagConstraints();
        panelLeft.setPreferredSize(new Dimension(220, 200));
        panelLeft.setBackground(new Color(0xecf0f1));
        panelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(0x2c3e50)));

        gbcMain.anchor = GridBagConstraints.NORTHWEST;
        gbcMain.fill = GridBagConstraints.BOTH;

        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 0;
        gbcMain.weighty = 1;

        // add 
        add(panelLeft, gbcMain);
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
        lblTitle = new JLabel("Quản lý sinh viên");
        lblTitle.setForeground(new Color(0x2ecc71));
        lblTitle.setFont(bigFont);

        // add vào khung bên trái
        gbcleft.insets = new Insets(10, 20, 10, 20);
        gbcleft.gridx = 0;
        gbcleft.gridy = 0;
        gbcleft.weighty = 1;

        // add 
        panelLeft.add(lblTitle, gbcleft);
    }

    // set up tiêu đề tên giáo viên cho khung trái
    private void initTitleNameTeacher() {
        lblUsername = new JLabel("Giáo viên " + teacher.getTeacherName());
        gbcleft.gridx = 0;
        gbcleft.gridy = 1;

        // add 
        panelLeft.add(lblUsername, gbcleft);
    }

    // MY CLASS
    // setup nút My Class cho khung bên trái
    private void initBtnMyClassOfLeftFrame() {
        btnMyClasse = new JButton("My Class");
        // design button
        designButton(btnMyClasse, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);
        btnMyClasse.setFont(normalFont);

        gbcleft.gridx = 0;
        gbcleft.gridy = 2;

        // add 
        panelLeft.add(btnMyClasse, gbcleft);
    }

    // MY CLASS FRAME
    // Định nghĩa khung MyClass thay thế cho khung bên phải panelRight
    private void defineMyClassFrame() {
        panelMyClass = new JPanel(new GridBagLayout());
        consOfMyClass = new GridBagConstraints();
        panelMyClass.setBackground(new Color(0x1abc9c));

        // add 
        add(panelMyClass, gbcMain);
    }

    // Khởi tạo và setup cho tiêu đề của khung MyClass
    public void initLabelTitleComp() {
        // Title of panelMyClass
        lblTitleMyClass = new JLabel("Chào mừng tới danh sách lớp của bạn");
        lblTitleMyClass.setFont(bigFont);
        lblTitleMyClass.setForeground(new Color(0xecf0f1));
        // thêm 
        consOfMyClass.gridx = 0;
        consOfMyClass.gridy = 0;
        consOfMyClass.weightx = 1;
        consOfMyClass.insets = new Insets(10, 10, 10, 10);
        consOfMyClass.fill = GridBagConstraints.HORIZONTAL;

        // add 
        panelMyClass.add(lblTitleMyClass, consOfMyClass);
    }

    // Khởi tạo và định nghĩa cho JCombobox Danh sách các lớp học của khung MyClass
    private void initCboListClassesComp() {
        // list classes
        cboListClasses = new JComboBox<>();

        if (listAssignments.size() != 0) {
            classes = new String[listAssignments.size()];
            for (int i = 0; i < listAssignments.size(); i++) {
                TeachingAssignment o = listAssignments.get(i);
                int teachingAssignmentID = o.getTeachingAssignmentID();
                int subjectDetailID = o.getSubjectID();

                classes[i]
                        = service.getClassname(teachingAssignmentID)
                        + ","
                        + service.getSubjectNameBySubjectDetailID(subjectDetailID);

            }

            for (String classStr : classes) {
                cboListClasses.addItem(new String(classStr.split(",")[0] + " - " + classStr.split(",")[1]));
            }
        }

        cboListClasses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        consOfMyClass.gridx = 0;
        consOfMyClass.gridy = 1;
        consOfMyClass.weightx = 1;
        consOfMyClass.anchor = GridBagConstraints.WEST;

        // add 
        panelMyClass.add(cboListClasses, consOfMyClass);
    }

    // khởi tạo và định nghĩa Jlabel hiển thị số lớp đang dạy vào khung myClass
    private void initTextCountClass() {
        int cnt = cboListClasses.getItemCount();
        lblCntClass = new JLabel(cnt + " lớp đang dạy");
        lblCntClass.setFont(normalFont);
        lblCntClass.setForeground(new Color(0xecf0f1));

        // add
        consOfMyClass.gridx = 1;
        consOfMyClass.gridy = 1;
        consOfMyClass.weightx = 2;
        consOfMyClass.anchor = GridBagConstraints.EAST;
        panelMyClass.add(lblCntClass, consOfMyClass);
    }

    // khởi tạo và định nghĩa Jlabel hiển thị lớp đang chỉ định
    private void initTextClassIsSelected() {
        String ClassSelected = (String) cboListClasses.getSelectedItem();
        if (ClassSelected != null) {
            lblClassIsSelected = new JLabel("Đang chọn lớp " + ClassSelected);
        } else {
            lblClassIsSelected = new JLabel("Không có lớp nào để chọn");
        }

        lblClassIsSelected.setFont(normalFont);
        lblClassIsSelected.setForeground(new Color(0xecf0f1));

        // add
        consOfMyClass.gridx = 0;
        consOfMyClass.gridy = 2;
        consOfMyClass.weightx = 1;
        consOfMyClass.fill = GridBagConstraints.HORIZONTAL;
        panelMyClass.add(lblClassIsSelected, consOfMyClass);
    }

    // phương thức cập nhật lại lớp đang được chọn 
    private void currentSelected() {
        String ClassSelected = (String) cboListClasses.getSelectedItem();
        lblClassIsSelected.setText("Đang chọn lớp " + ClassSelected);
    }

    // Định nghĩa bảng hiển thị thông tin sinh viên ở khung myClass
    private void initAndUpdateTableForMyClassFrame() {
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
        tableModel.addColumn("Email");
        tableModel.addColumn("Specifically Major");

        tblInfoStudent = new JTable(tableModel);

        JTableHeader header = tblInfoStudent.getTableHeader();
        header.getColumnModel().getColumn(0).setHeaderValue("STT");
        header.getColumnModel().getColumn(1).setHeaderValue("MSSV");
        header.getColumnModel().getColumn(2).setHeaderValue("Name");
        header.getColumnModel().getColumn(3).setHeaderValue("Sex");
        header.getColumnModel().getColumn(4).setHeaderValue("Email");
        header.getColumnModel().getColumn(5).setHeaderValue("Specifically Major");
        header.setBackground(new Color(0x3498db));
//        tblInfoStudent.setBackground(new Color(0xbdc3c7));
//        tblInfoStudent.setForeground(new Color(0x34495e));

        tblInfoStudent.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tblInfoStudent);

        if (cboListClasses.getItemCount() != 0) {
            fillTable(listAssignments.get(0).getTeachingAssignmentID());
        }

        consOfMyClass.gridx = 0;
        consOfMyClass.gridy = 3;
        consOfMyClass.gridwidth = 4;
        consOfMyClass.weightx = 0;
        panelMyClass.add(scrollPane, consOfMyClass);

    }

    // Phương thức fill dữ liệu lên bảng dựa vào lớp đang được chọn
    private void fillTable(int teachingAssignmentID) {
        tableModel.setRowCount(0);
        int stt = 1;
        for (Student o : service.getStudentParticipations(teachingAssignmentID, 1)) {
            tableModel.addRow(new Object[]{
                stt++,
                getMSSV(o.getStudentID()),
                getSex(o.getSex()),
                o.getStudentName(),
                o.getEmail(),
                o.getSpecificallyMajorName()});
        }
    }

    private String getMSSV(int StudentID) {
        String defaultStr = "00000" + StudentID;
        String res = "PH" + defaultStr.substring(defaultStr.length() - 5, defaultStr.length());
        return res.toUpperCase();
    }

    private String getSex(int sex) {
        if (sex == 1) {
            return "Nam";
        }
        return "Nữ";
    }

    // Khởi tạo và định nghĩa sĩ số học sinh bên dưới JTable
    private void initCountOfStudent() {
        lblCntOfStudent = new JLabel("Sĩ số : " + tableModel.getRowCount(), JLabel.RIGHT);

        // add
        consOfMyClass.gridx = 3;
        consOfMyClass.gridy = 4;
        consOfMyClass.gridwidth = 1;
        consOfMyClass.weightx = 4;
        consOfMyClass.anchor = GridBagConstraints.EAST;
        panelMyClass.add(lblCntOfStudent, consOfMyClass);
    }

    // Phương thức mở ra Dialog
    private void DisplayDialog(int studentID) {
        new StudentDialog(this, studentID).setVisible(true);
    }

    // GRADE MANAGEMENT
    // setup nút quản lý điểm cho khung trái
    public void initbtnGradeManagementOfLeftFrame() {
        btnSetGrade = new JButton("Grade Management");
        // design button
        designButton(btnSetGrade, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);
        btnSetGrade.setFont(normalFont);

        gbcleft.gridx = 0;
        gbcleft.gridy = 3;

        // add 
        panelLeft.add(btnSetGrade, gbcleft);
    }

    // ATTENDANCE
    // set up nút Attendance cho khung trái
    private void initBtnAttendanceOfLeftFrame() {
        btnAttendance = new JButton("Attendance");
        // design button
        designButton(btnAttendance, 0x27ae60,
                0xecf0f1, 10, 10, 10, 10);
        btnAttendance.setFont(normalFont);

        gbcleft.gridx = 0;
        gbcleft.gridy = 4;

        // add 
        panelLeft.add(btnAttendance, gbcleft);
    }

    class StudentDialog extends JDialog {

        private Student student = null;

        // service student
        private StudentService studentService;

        // Components
        private final GridBagLayout gbMainDia;
        private final GridBagConstraints gbcMainDia;
        private JLabel lblLogo;
        private JLabel lblTitleDia;
        private JLabel lblPhoto;
        private Image photoImage;
        private ImageIcon imgIconOrigin;

        public StudentDialog(JFrame owner, int id) {
            super(owner, false);
            // Khởi tạo đối tượng

            studentService = new StudentService();

            student = studentService.readStudent(id);
            setTitle(student.getStudentName() + " (view)");
            setSize(new Dimension(650, 400));
            setLocationRelativeTo(this);
            getContentPane().setBackground(new Color(0xdff9fb));

            gbMainDia = new GridBagLayout();
            gbcMainDia = new GridBagConstraints();
            setLayout(gbMainDia);

            // Khởi tạo và định nghĩa logo trường cho khung main
            initImageLogoSchool();

            // Khởi tạo tiêu đề cho khung main
            initTitleComp();

            // Khởi tạo khung chứa ảnh
            initPhotoFrame();

            // Thiết lập JLabel tên sinh viên
            initlblNameStudent();

            // Thiết lập JLabel chuyên ngành của sinh viên
            initlblSpecificallyMajor();

            // Thiết lập JLabel MSSV
            initlblMSSV();

            // Thiết lập JLabel ngày vào trường
            initlblLastModified();

        }

        // Khởi tạo và định nghĩa logo trường cho khung main
        private void initImageLogoSchool() {
            String url = "C:\\Users\\nguye\\OneDrive\\Desktop\\asm\\image\\FPT_Polytechnic.png";
            ImageIcon imgIconLogo = new ImageIcon(url);
            Image imgLogo = imgIconLogo.getImage();
            int w = 120;
            int h = 40;
            imgIconLogo = new ImageIcon(imgLogo.getScaledInstance(w, h, Image.SCALE_SMOOTH));
            lblLogo = new JLabel(imgIconLogo);

            // add
            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 0;
            gbcMainDia.weightx = 1;
            gbcMainDia.insets = new Insets(0, 40, 0, 0);
            gbcMainDia.anchor = GridBagConstraints.WEST;
            add(lblLogo, gbcMainDia);

        }

        // Khởi tạo tiêu đề cho khung main
        private void initTitleComp() {
            lblTitleDia = new JLabel("Thông tin sinh viên");
            lblTitleDia.setFont(bigestFont);
            lblTitleDia.setForeground(new Color(0xff6348));

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 0;
            gbcMainDia.weightx = 1;
            gbcMainDia.insets = new Insets(0, 0, 0, 0);
            add(lblTitleDia, gbcMainDia);
        }

        // Khởi tạo khung chứa ảnh
        private void initPhotoFrame() {
            byte[] pixel;
            pixel = service.getPhotoBinary(student.getStudentID());

            try (ByteArrayInputStream bin = new ByteArrayInputStream(pixel);) {
                if (bin != null) {
                    photoImage = ImageIO.read(bin);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoImage != null) {
                imgIconOrigin = new ImageIcon(photoImage.getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                lblPhoto = new JLabel(imgIconOrigin);
            } else {
                lblPhoto = new JLabel();
            }

            gbcMainDia.gridx = 0;
            gbcMainDia.gridy = 1;
            gbcMainDia.gridheight = 9;
            gbcMainDia.insets = new Insets(10, 30, 10, 10);
            add(lblPhoto, gbcMainDia);
//            teacherServices.insertDefault();

        }

        // Thiết lập JLabel tên sinh viên
        private void initlblNameStudent() {
            JLabel lblNameTitleDia = new JLabel("Họ và tên / Name");
            lblNameTitleDia.setFont(normalFont);

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 1;
            gbcMainDia.gridheight = 1;
            gbcMainDia.insets = new Insets(30, 0, 0, 10);

            add(lblNameTitleDia, gbcMainDia);

            JLabel lblNameDia = new JLabel(student.getStudentName().toUpperCase());
            lblNameDia.setFont(new Font("Consolas", Font.BOLD, 20));
            lblNameDia.setForeground(new Color(0xd35400));

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 2;
            gbcMainDia.insets = new Insets(10, 0, 0, 0);
            add(lblNameDia, gbcMainDia);
        }

        // Thiết lập JLabel chuyên ngành của sinh viên
        private void initlblSpecificallyMajor() {
            JLabel lblSpecificallyMajorTitle = new JLabel("Chuyên ngành");
            lblSpecificallyMajorTitle.setFont(normalFont);

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 3;
            gbcMainDia.gridheight = 1;
            gbcMainDia.insets = new Insets(0, 0, 0, 0);
            add(lblSpecificallyMajorTitle, gbcMainDia);

            JLabel lblSpecificallyMajor = new JLabel(student.getSpecificallyMajorName());
            lblSpecificallyMajor.setFont(new Font("Consolas", Font.BOLD, 20));
            lblSpecificallyMajor.setForeground(new Color(0xd35400));

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 4;
            gbcMainDia.gridheight = 1;
            gbcMainDia.insets = new Insets(10, 0, 0, 0);
            add(lblSpecificallyMajor, gbcMainDia);
        }

        // Thiết lập JLabel MSSV
        private void initlblMSSV() {
            JLabel lblMSSVTitle = new JLabel("MSSV / Student ID");
            lblMSSVTitle.setFont(normalFont);

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 5;
            gbcMainDia.gridheight = 1;
            gbcMainDia.insets = new Insets(0, 0, 0, 0);
            add(lblMSSVTitle, gbcMainDia);

            JLabel lblMSSV = new JLabel(getMSSV(student.getStudentID()));
            lblMSSV.setFont(new Font("Consolas", Font.BOLD, 20));
            lblMSSV.setForeground(new Color(0xd35400));

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 6;
            gbcMainDia.gridheight = 1;
            gbcMainDia.insets = new Insets(10, 0, 0, 0);
            add(lblMSSV, gbcMainDia);
        }

        // Thiết lập JLabel ngày vào trường
        private void initlblLastModified() {
            JLabel lblValidToTitle = new JLabel("Ngày gia nhập");
            lblValidToTitle.setFont(normalFont);

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 7;
            gbcMainDia.gridheight = 1;
            gbcMainDia.insets = new Insets(0, 0, 0, 0);
            add(lblValidToTitle, gbcMainDia);

            LocalDate lastModified = student.getLastModified();
            int lastYear = lastModified.getYear();
            int lastMonth = lastModified.getMonthValue();

            JLabel lblValidTo = new JLabel(lastMonth + "/" + lastYear);
            lblValidTo.setFont(new Font("Consolas", Font.BOLD, 20));
            lblValidTo.setForeground(new Color(0xd35400));

            gbcMainDia.gridx = 1;
            gbcMainDia.gridy = 8;
            gbcMainDia.gridheight = 1;
            gbcMainDia.insets = new Insets(10, 0, 0, 0);
            add(lblValidTo, gbcMainDia);
        }

    }

    public static void main(String[] args) {
        new TeacherView("anph002@fpt.edu.vn").setVisible(true);
    }
}
