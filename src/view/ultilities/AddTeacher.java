package view.ultilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.beans.Teacher;
import service.Service;
import service.TeacherService;

/**
 *
 * @author nguye
 */
public class AddTeacher extends javax.swing.JDialog {

    private JFileChooser fileChooser;
    private Image photoImage;
    private ImageIcon imgIconFE;
    private byte[] data;
    private Service service;
    private Map<String, Integer> mapMajor;
    private List<Teacher> listTeacherManager;
    private HashMap<String, Integer> teacherManagerMap;
    private final TeacherService teacherService;

    /**
     * Creates new form AddTeacher
     */
    public AddTeacher(JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);

        // Khởi tạo service 
        service = new Service();
        teacherService = new TeacherService();

        getContentPane().setBackground(new Color(0x55efc4));
        panelForm.setBackground(new Color(0x55efc4));
        panelForm1.setBackground(new Color(0x55efc4));
        panelForm2.setBackground(new Color(0x55efc4));
        panelForm3.setBackground(new Color(0x55efc4));
        panelForm4.setBackground(new Color(0x55efc4));
        panelForm5.setBackground(new Color(0x55efc4));
        panelForm7.setBackground(new Color(0x55efc4));
        panelForm9.setBackground(new Color(0x55efc4));
        designButton(btnBrowser, 0xe67e22, 0xecf0f1, 10, 36, 10, 36);
        designButton(btnAddTeacher, 0xe67e22, 0xecf0f1, 10, 36, 10, 36);

        btnBrowser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data = initFileExplorer();
                if (data != null) {
                    initPhotoComp(data);
                }
            }
        });

        // Định nghĩa cboMajor
        defineCboMajor();
        // Định nghĩa trưởng môn
        defineCboTeacherManager();

        cboMajor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defineCboTeacherManager();
            }
        });

        btnAddTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTeacher();
            }
        });
    }

    private void createTeacher() {
        if (validateForm()) {
            int teacherManager = 0;
            String str = (String) cboMyBoss.getSelectedItem();
            if (str != null) {
                if (chkTeacherManager.isSelected()) {
                    teacherManager = 0;
                } else {
                    teacherManager = teacherManagerMap.get(str);
                }
            } else {
                teacherManager = 0;
                // Khi giá trị này = 0 <=> sẽ insert giá trị teacherManagerID = null cho nó
                // và nó chính là trưởng môn luôn
            }

            int sex = (cboSex.getSelectedIndex() == 0 ? 1 : 2);
            Teacher teacher
                    = new Teacher(
                            txtFullname.getText(),
                            sex,
                            LocalDate.parse(txtDob.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            txtPhoneNumber.getText(),
                            txtProvice.getText(),
                            mapMajor.get((String) cboMajor.getSelectedItem()),
                            teacherManager,
                            data,
                            LocalDate.parse(LocalDate.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            txtDescriptor.getText());

            String[] options = {"Xác nhận", "Hủy"};
            int indexOptions = JOptionPane.showOptionDialog(this, "Bạn có muốn thay đổi giáo viên này là trưởng môn không?",
                    "Xác nhận trưởng môn", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
            if (indexOptions == 0) {
                if (teacherService.createTeacher(teacher)) {
                    JOptionPane.showMessageDialog(this, "Tạo giáo viên thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm giáo viên thất bại");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Hủy thành công");
            }

        }
    }


    private void designButton(JButton originButton, int bgHex, int fgHex, int t, int r, int b, int l) {
        originButton.setBackground(new Color(bgHex));
        originButton.setForeground(new Color(fgHex));
        originButton.setBorder(new EmptyBorder(t, l, b, r));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        panelForm = new javax.swing.JPanel();
        lblFullname = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();
        lblErrorFullName = new javax.swing.JLabel();
        panelForm1 = new javax.swing.JPanel();
        lblSex = new javax.swing.JLabel();
        cboSex = new javax.swing.JComboBox<>();
        panelForm2 = new javax.swing.JPanel();
        lblDob = new javax.swing.JLabel();
        txtDob = new javax.swing.JTextField();
        lblErrorDob = new javax.swing.JLabel();
        panelForm3 = new javax.swing.JPanel();
        lblPhonenumber = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        lblErrorPhoneNumber = new javax.swing.JLabel();
        panelForm4 = new javax.swing.JPanel();
        lblProvice = new javax.swing.JLabel();
        txtProvice = new javax.swing.JTextField();
        lblErrorProvice = new javax.swing.JLabel();
        panelForm5 = new javax.swing.JPanel();
        lblMajor = new javax.swing.JLabel();
        cboMajor = new javax.swing.JComboBox<>();
        panelForm6 = new javax.swing.JPanel();
        lblPhoto = new javax.swing.JLabel();
        btnBrowser = new javax.swing.JButton();
        panelForm7 = new javax.swing.JPanel();
        lblMyBoss = new javax.swing.JLabel();
        cboMyBoss = new javax.swing.JComboBox<>();
        panelForm9 = new javax.swing.JPanel();
        lblDescriptor = new javax.swing.JLabel();
        txtDescriptor = new javax.swing.JTextArea();
        btnAddTeacher = new javax.swing.JButton();
        chkTeacherManager = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        getContentPane().setLayout(layout);

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(142, 68, 173));
        jLabel1.setText("Thêm giảng viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        getContentPane().add(jLabel1, gridBagConstraints);

        panelForm.setLayout(new java.awt.GridLayout(3, 1));

        lblFullname.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblFullname.setText("Họ và tên");
        lblFullname.setToolTipText("");
        panelForm.add(lblFullname);

        txtFullname.setBackground(new java.awt.Color(22, 160, 133));
        txtFullname.setColumns(18);
        txtFullname.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtFullname.setForeground(new java.awt.Color(255, 255, 255));
        txtFullname.setMargin(new java.awt.Insets(5, 5, 5, 5));
        txtFullname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFullnameActionPerformed(evt);
            }
        });
        panelForm.add(txtFullname);

        lblErrorFullName.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        panelForm.add(lblErrorFullName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(panelForm, gridBagConstraints);

        panelForm1.setLayout(new java.awt.GridLayout(2, 1));

        lblSex.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblSex.setText("Giới tính");
        lblSex.setToolTipText("");
        panelForm1.add(lblSex);

        cboSex.setBackground(new java.awt.Color(22, 160, 133));
        cboSex.setForeground(new java.awt.Color(255, 255, 255));
        cboSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        panelForm1.add(cboSex);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(panelForm1, gridBagConstraints);

        panelForm2.setLayout(new java.awt.GridLayout(3, 1));

        lblDob.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblDob.setText("Ngày sinh");
        lblDob.setToolTipText("");
        panelForm2.add(lblDob);

        txtDob.setBackground(new java.awt.Color(22, 160, 133));
        txtDob.setColumns(18);
        txtDob.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtDob.setForeground(new java.awt.Color(255, 255, 255));
        txtDob.setMargin(new java.awt.Insets(5, 5, 5, 5));
        panelForm2.add(txtDob);

        lblErrorDob.setText(" ");
        panelForm2.add(lblErrorDob);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(panelForm2, gridBagConstraints);

        panelForm3.setLayout(new java.awt.GridLayout(3, 1));

        lblPhonenumber.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblPhonenumber.setText("Số điện thoại");
        lblPhonenumber.setToolTipText("");
        panelForm3.add(lblPhonenumber);

        txtPhoneNumber.setBackground(new java.awt.Color(22, 160, 133));
        txtPhoneNumber.setColumns(18);
        txtPhoneNumber.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtPhoneNumber.setForeground(new java.awt.Color(255, 255, 255));
        txtPhoneNumber.setMargin(new java.awt.Insets(5, 5, 5, 5));
        panelForm3.add(txtPhoneNumber);

        lblErrorPhoneNumber.setText(" ");
        panelForm3.add(lblErrorPhoneNumber);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(panelForm3, gridBagConstraints);

        panelForm4.setLayout(new java.awt.GridLayout(3, 1));

        lblProvice.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblProvice.setText("Tỉnh / Thành Phố");
        lblProvice.setToolTipText("");
        panelForm4.add(lblProvice);

        txtProvice.setBackground(new java.awt.Color(22, 160, 133));
        txtProvice.setColumns(18);
        txtProvice.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        txtProvice.setForeground(new java.awt.Color(255, 255, 255));
        txtProvice.setMargin(new java.awt.Insets(5, 5, 5, 5));
        panelForm4.add(txtProvice);

        lblErrorProvice.setText(" ");
        panelForm4.add(lblErrorProvice);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(panelForm4, gridBagConstraints);

        panelForm5.setLayout(new java.awt.GridLayout(2, 1));

        lblMajor.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblMajor.setText("Chuyên ngành");
        lblMajor.setToolTipText("");
        panelForm5.add(lblMajor);

        cboMajor.setBackground(new java.awt.Color(22, 160, 133));
        cboMajor.setForeground(new java.awt.Color(255, 255, 255));
        panelForm5.add(cboMajor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(panelForm5, gridBagConstraints);

        panelForm6.setBackground(new java.awt.Color(255, 102, 102));
        panelForm6.setAutoscrolls(true);
        panelForm6.setMinimumSize(new java.awt.Dimension(120, 120));
        panelForm6.setPreferredSize(new java.awt.Dimension(120, 120));
        panelForm6.setLayout(new java.awt.BorderLayout());

        lblPhoto.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblPhoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhoto.setText("NOTHING");
        lblPhoto.setToolTipText("");
        panelForm6.add(lblPhoto, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(panelForm6, gridBagConstraints);

        btnBrowser.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnBrowser.setText("Browser");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1.5;
        getContentPane().add(btnBrowser, gridBagConstraints);

        panelForm7.setLayout(new java.awt.GridLayout(2, 1));

        lblMyBoss.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblMyBoss.setText("Quản lý");
        lblMyBoss.setToolTipText("");
        panelForm7.add(lblMyBoss);

        cboMyBoss.setBackground(new java.awt.Color(22, 160, 133));
        cboMyBoss.setForeground(new java.awt.Color(255, 255, 255));
        panelForm7.add(cboMyBoss);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(panelForm7, gridBagConstraints);

        panelForm9.setLayout(new java.awt.GridLayout(3, 1));

        lblDescriptor.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblDescriptor.setText("Mô tả");
        lblDescriptor.setToolTipText("");
        panelForm9.add(lblDescriptor);

        txtDescriptor.setBackground(new java.awt.Color(22, 160, 133));
        txtDescriptor.setColumns(20);
        txtDescriptor.setRows(10);
        txtDescriptor.setMargin(new java.awt.Insets(5, 5, 5, 5));
        panelForm9.add(txtDescriptor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        getContentPane().add(panelForm9, gridBagConstraints);

        btnAddTeacher.setText("Thêm giáo viên");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 14, 0, 14);
        getContentPane().add(btnAddTeacher, gridBagConstraints);

        chkTeacherManager.setBackground(new java.awt.Color(85, 239, 196));
        chkTeacherManager.setText("Tao là trưởng môn mới chứ?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 15);
        getContentPane().add(chkTeacherManager, gridBagConstraints);

        setBounds(0, 0, 646, 682);
    }// </editor-fold>//GEN-END:initComponents

    private void txtFullnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFullnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFullnameActionPerformed

    /**
     * @param args the command line arguments
     */
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
                photoImage = ImageIO.read(bin).
                        getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            } catch (IOException e) {
                e.printStackTrace();
            }

            imgIconFE = new ImageIcon(photoImage);
            lblPhoto.setText("");
            lblPhoto.setIcon(imgIconFE);
        }
    }

    private boolean validateForm() {
        int cntError = 0;
        if (txtFullname.getText().isEmpty()) {
            cntError++;
            lblErrorFullName.setText("Vui lòng nhập họ và tên");
        } else {
            lblErrorFullName.setText(" ");
        }
        if (txtDob.getText().isEmpty()) {
            cntError++;
            lblErrorDob.setText("Vui lòng nhập ngày sinh");
        } else {
            try {
                lblErrorDob.setText(" ");
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dob = LocalDate.parse(txtDob.getText(), format);
            } catch (Exception e) {
                cntError++;
                lblErrorDob.setText("Định dạng Date không hợp lệ");
            }
        }
        if (txtPhoneNumber.getText().isEmpty()) {
            cntError++;
            lblErrorPhoneNumber.setText("Vui lòng nhập số điện thoại");
        } else {
            String regex = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(txtPhoneNumber.getText());
            if (!matcher.matches()) {
                cntError++;
                lblErrorPhoneNumber.setText("Số điện thoại không chuẩn");
            } else {
                lblErrorPhoneNumber.setText(" ");
            }
        }
        if (txtProvice.getText().isEmpty()) {
            cntError++;
            lblErrorProvice.setText("Vui lòng nhập tỉnh thành phố");
        } else {
            lblErrorProvice.setText(" ");
        }

        if (cntError == 0) {
            return true;
        }
        return false;
    }

    // Khởi tạo chuyên ngành
    private void defineCboMajor() {
        cboMajor.removeAllItems();
        mapMajor = service.getMajorMap();

        for (Map.Entry<String, Integer> entry : mapMajor.entrySet()) {
            cboMajor.addItem(entry.getKey());
        }
    }

    // Định nghĩa trưởng môn
    private void defineCboTeacherManager() {
        cboMyBoss.removeAllItems();
        int majorID = mapMajor.get((String) cboMajor.getSelectedItem());
        listTeacherManager = service.getListTeacherManager(majorID);
        teacherManagerMap = new HashMap<>();

        if (listTeacherManager != null) {
            for (Teacher o : listTeacherManager) {
                teacherManagerMap.put(o.getTeacherName() + "-" + getMSGV(o.getTeacherID()), o.getTeacherID());
            }
        }

        for (Map.Entry<String, Integer> entry : teacherManagerMap.entrySet()) {
            cboMyBoss.addItem(entry.getKey());
        }
    }

    private String getMSGV(int TeacherID) {
        String defaultStr = "00000" + TeacherID;
        String res = "FE" + defaultStr.substring(defaultStr.length() - 5, defaultStr.length());
        return res.toUpperCase();
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddTeacher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddTeacher dialog = new AddTeacher(new JDialog(), false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddTeacher;
    private javax.swing.JButton btnBrowser;
    private javax.swing.JComboBox<String> cboMajor;
    private javax.swing.JComboBox<String> cboMyBoss;
    private javax.swing.JComboBox<String> cboSex;
    private javax.swing.JCheckBox chkTeacherManager;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDescriptor;
    private javax.swing.JLabel lblDob;
    private javax.swing.JLabel lblErrorDob;
    private javax.swing.JLabel lblErrorFullName;
    private javax.swing.JLabel lblErrorPhoneNumber;
    private javax.swing.JLabel lblErrorProvice;
    private javax.swing.JLabel lblFullname;
    private javax.swing.JLabel lblMajor;
    private javax.swing.JLabel lblMyBoss;
    private javax.swing.JLabel lblPhonenumber;
    private javax.swing.JLabel lblPhoto;
    private javax.swing.JLabel lblProvice;
    private javax.swing.JLabel lblSex;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panelForm1;
    private javax.swing.JPanel panelForm2;
    private javax.swing.JPanel panelForm3;
    private javax.swing.JPanel panelForm4;
    private javax.swing.JPanel panelForm5;
    private javax.swing.JPanel panelForm6;
    private javax.swing.JPanel panelForm7;
    private javax.swing.JPanel panelForm9;
    private javax.swing.JTextArea txtDescriptor;
    private javax.swing.JTextField txtDob;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtProvice;
    // End of variables declaration//GEN-END:variables
}
