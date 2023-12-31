package view.ultilities;

import view.login.Login;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import model.beans.Classroom;
import model.beans.Classs;
import model.beans.Major;
import model.beans.SpecificallyMajor;
import model.beans.SubjectDetail;
import model.beans.Teacher;
import model.beans.TeachingAssignment;
import service.Service;


public class ArrangeTeaching extends javax.swing.JDialog {
    private final Service service;
    private List<Major> listMajor;
    private TreeMap<String, Integer> majorMap;
    private DefaultListModel modelListMajor;
    private final DefaultListModel<String> modelListSubjectDetail;
    private TreeMap<String, Integer> specificallyMajorMap;
    private List<SpecificallyMajor> listSpecificallyMajor;
    private final DefaultListModel<String> modelListSpecificallyMajor;
    private TreeMap<String, Integer> subjectDetailMap;
    private List<SubjectDetail> listSubjectDetail;
    private List<Classs> listClass;
    private TreeMap<String, Integer> classMap;
    private final DefaultListModel<String> modelListClass;
    private List<Teacher> listTeacher;
    private TreeMap<String, Integer> teacherMap;
    private final DefaultListModel<String> modelListTeacher;
    private List<Classroom> listClassroom;
    private TreeMap<String, Integer> classroomMap;
    private final DefaultListModel<String> modelListClassroom;

    /**
     * Creates new form ArrangeTeaching
     */
    
    
    public ArrangeTeaching(JDialog parent, boolean modal) {
        super(parent, modal);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Login.load.dispose();
            }
        });
        
        initComponents();
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0x2c3e50));

        // service
        service = new Service();

        // model
        modelListMajor = new DefaultListModel();
        modelListSpecificallyMajor = new DefaultListModel<>();
        modelListSubjectDetail = new DefaultListModel<>();
        modelListClass = new DefaultListModel<>();
        modelListTeacher = new DefaultListModel<>();
        modelListClassroom = new DefaultListModel<>();

        designButton(btnSubmit, 
                0x16a085, 0xecf0f1, 5, 10, 5, 10);
        
        // khởi tạo giá trị cho list major
        initMajor();
        fillMajorToList();
        defineClassroom();
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        lblSubject = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlSubjectDetail = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        lblClass = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlClass = new javax.swing.JList<>();
        jPanel3 = new javax.swing.JPanel();
        lblMajor = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlMajor = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblSpecificallyMajor = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jlSpecificallyMajor = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        lblTeacher = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jlTeacher = new javax.swing.JList<>();
        jPanel6 = new javax.swing.JPanel();
        txtTeacherNameInfo = new javax.swing.JTextField();
        lblTeacherNameInfo = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtSubjectInfo = new javax.swing.JTextField();
        lblSubjectInfo = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txtClassInfo = new javax.swing.JTextField();
        lblClassInfo = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        txtShift = new javax.swing.JTextField();
        lblShift = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtClassroom = new javax.swing.JTextField();
        lblClassroom = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtSpecificallyMajor = new javax.swing.JTextField();
        lblSpecificallyMajorInfo = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        lblClassroomList = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jlClassroom = new javax.swing.JList<>();
        jPanel13 = new javax.swing.JPanel();
        lblShiftInfo = new javax.swing.JLabel();
        cboShift = new javax.swing.JComboBox<>();
        btnSubmit = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtDescriptor = new javax.swing.JTextArea();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        lblSubject.setText("Môn học");
        jPanel1.add(lblSubject, java.awt.BorderLayout.NORTH);

        jlSubjectDetail.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSubjectDetailValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jlSubjectDetail);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.BorderLayout());

        lblClass.setText("Lớp học khả dụng");
        jPanel2.add(lblClass, java.awt.BorderLayout.NORTH);

        jlClass.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlClassValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jlClass);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.BorderLayout());

        lblMajor.setText("Ngành");
        jPanel3.add(lblMajor, java.awt.BorderLayout.NORTH);

        jlMajor.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlMajorValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(jlMajor);

        jPanel3.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel3, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(211, 84, 0));
        jLabel1.setText("QUẢN LÝ LỚP HỌC CHO TRƯỜNG F ĐẶC CẦU");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 9.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jLabel1, gridBagConstraints);

        jPanel4.setLayout(new java.awt.BorderLayout());

        lblSpecificallyMajor.setText("Nganh Hep");
        jPanel4.add(lblSpecificallyMajor, java.awt.BorderLayout.NORTH);

        jlSpecificallyMajor.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSpecificallyMajorValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(jlSpecificallyMajor);

        jPanel4.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.BorderLayout());

        lblTeacher.setText("Giáo viên");
        jPanel5.add(lblTeacher, java.awt.BorderLayout.NORTH);

        jlTeacher.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlTeacher.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlTeacherValueChanged(evt);
            }
        });
        jScrollPane6.setViewportView(jlTeacher);

        jPanel5.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel5, gridBagConstraints);

        jPanel6.setLayout(new java.awt.BorderLayout(5, 0));

        txtTeacherNameInfo.setEditable(false);
        txtTeacherNameInfo.setBackground(new java.awt.Color(39, 174, 96));
        txtTeacherNameInfo.setColumns(20);
        jPanel6.add(txtTeacherNameInfo, java.awt.BorderLayout.EAST);

        lblTeacherNameInfo.setBackground(new java.awt.Color(44, 62, 80));
        lblTeacherNameInfo.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblTeacherNameInfo.setForeground(new java.awt.Color(39, 174, 96));
        lblTeacherNameInfo.setText("Họ và tên giảng viên");
        lblTeacherNameInfo.setMinimumSize(new java.awt.Dimension(138, 15));
        jPanel6.add(lblTeacherNameInfo, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.BorderLayout(5, 0));

        txtSubjectInfo.setEditable(false);
        txtSubjectInfo.setBackground(new java.awt.Color(39, 174, 96));
        txtSubjectInfo.setColumns(20);
        jPanel7.add(txtSubjectInfo, java.awt.BorderLayout.EAST);

        lblSubjectInfo.setBackground(new java.awt.Color(44, 62, 80));
        lblSubjectInfo.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblSubjectInfo.setForeground(new java.awt.Color(39, 174, 96));
        lblSubjectInfo.setText("Môn dạy");
        lblSubjectInfo.setMinimumSize(new java.awt.Dimension(138, 15));
        jPanel7.add(lblSubjectInfo, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel7, gridBagConstraints);

        jPanel8.setLayout(new java.awt.BorderLayout(5, 0));

        txtClassInfo.setEditable(false);
        txtClassInfo.setBackground(new java.awt.Color(39, 174, 96));
        txtClassInfo.setColumns(20);
        jPanel8.add(txtClassInfo, java.awt.BorderLayout.EAST);

        lblClassInfo.setBackground(new java.awt.Color(44, 62, 80));
        lblClassInfo.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblClassInfo.setForeground(new java.awt.Color(39, 174, 96));
        lblClassInfo.setText("Lớp học");
        lblClassInfo.setMinimumSize(new java.awt.Dimension(138, 15));
        jPanel8.add(lblClassInfo, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel8, gridBagConstraints);

        jPanel9.setLayout(new java.awt.BorderLayout(5, 0));

        txtShift.setEditable(false);
        txtShift.setBackground(new java.awt.Color(39, 174, 96));
        txtShift.setColumns(20);
        jPanel9.add(txtShift, java.awt.BorderLayout.EAST);

        lblShift.setBackground(new java.awt.Color(44, 62, 80));
        lblShift.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblShift.setForeground(new java.awt.Color(39, 174, 96));
        lblShift.setText("Ca");
        lblShift.setMinimumSize(new java.awt.Dimension(138, 15));
        jPanel9.add(lblShift, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel9, gridBagConstraints);

        jPanel10.setLayout(new java.awt.BorderLayout(5, 0));

        txtClassroom.setEditable(false);
        txtClassroom.setBackground(new java.awt.Color(39, 174, 96));
        txtClassroom.setColumns(20);
        jPanel10.add(txtClassroom, java.awt.BorderLayout.EAST);

        lblClassroom.setBackground(new java.awt.Color(44, 62, 80));
        lblClassroom.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblClassroom.setForeground(new java.awt.Color(39, 174, 96));
        lblClassroom.setText("Phòng");
        lblClassroom.setMinimumSize(new java.awt.Dimension(138, 15));
        jPanel10.add(lblClassroom, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel10, gridBagConstraints);

        jPanel11.setLayout(new java.awt.BorderLayout(5, 0));

        txtSpecificallyMajor.setEditable(false);
        txtSpecificallyMajor.setBackground(new java.awt.Color(39, 174, 96));
        txtSpecificallyMajor.setColumns(20);
        jPanel11.add(txtSpecificallyMajor, java.awt.BorderLayout.EAST);

        lblSpecificallyMajorInfo.setBackground(new java.awt.Color(44, 62, 80));
        lblSpecificallyMajorInfo.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        lblSpecificallyMajorInfo.setForeground(new java.awt.Color(39, 174, 96));
        lblSpecificallyMajorInfo.setText("Ngành hẹp");
        lblSpecificallyMajorInfo.setMinimumSize(new java.awt.Dimension(138, 15));
        jPanel11.add(lblSpecificallyMajorInfo, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel11, gridBagConstraints);

        jPanel12.setLayout(new java.awt.BorderLayout());

        lblClassroomList.setText("Phòng học");
        jPanel12.add(lblClassroomList, java.awt.BorderLayout.NORTH);

        jlClassroom.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlClassroomValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(jlClassroom);

        jPanel12.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel12, gridBagConstraints);

        jPanel13.setLayout(new java.awt.BorderLayout());

        lblShiftInfo.setText("Ca học");
        jPanel13.add(lblShiftInfo, java.awt.BorderLayout.NORTH);

        cboShift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        cboShift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboShiftActionPerformed(evt);
            }
        });
        jPanel13.add(cboShift, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jPanel13, gridBagConstraints);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(btnSubmit, gridBagConstraints);

        txtDescriptor.setColumns(20);
        txtDescriptor.setRows(5);
        jScrollPane8.setViewportView(txtDescriptor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jScrollPane8, gridBagConstraints);

        setBounds(0, 0, 1005, 705);
    }// </editor-fold>//GEN-END:initComponents

    private void jlMajorValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlMajorValueChanged
        // TODO add your handling code here:
        defineSpecificallyMajor();
        defineTeacher();

    }//GEN-LAST:event_jlMajorValueChanged

    private void jlSpecificallyMajorValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSpecificallyMajorValueChanged
        // TODO add your handling code here:
        defineSubjectDetail();
        txtSpecificallyMajor.setText(jlSpecificallyMajor.getSelectedValue());
    }//GEN-LAST:event_jlSpecificallyMajorValueChanged

    private void jlSubjectDetailValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSubjectDetailValueChanged
        // TODO add your handling code here:
        defineClassAvailabel();
        txtSubjectInfo.setText(jlSubjectDetail.getSelectedValue());
    }//GEN-LAST:event_jlSubjectDetailValueChanged

    private void jlClassValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlClassValueChanged
        // TODO add your handling code here:
        txtClassInfo.setText(jlClass.getSelectedValue());
    }//GEN-LAST:event_jlClassValueChanged

    private void jlTeacherValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlTeacherValueChanged
        // TODO add your handling code here:
        txtTeacherNameInfo.setText(jlTeacher.getSelectedValue());
    }//GEN-LAST:event_jlTeacherValueChanged

    private void jlClassroomValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlClassroomValueChanged
        // TODO add your handling code here:
        txtClassroom.setText(jlClassroom.getSelectedValue());
    }//GEN-LAST:event_jlClassroomValueChanged

    private void cboShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboShiftActionPerformed
        // TODO add your handling code here:
        txtShift.setText(cboShift.getSelectedItem() + "");
        defineClassroom();
    }//GEN-LAST:event_cboShiftActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        String season = "Summer";
//        LocalDate date = LocalDate.now();
//        if(Integer.valueOf(date.getMonth().toString()) >= 10) {
//            season = "winter";
//        } else if(Integer.valueOf(date.getMonth().toString()) >= 7) {
//            season = "fall";
//        } else if(Integer.valueOf(date.getMonth().toString()) >= 4) {
//            season = "summer";
//        } else {
//            season = "spring";
//        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        TeachingAssignment o = new TeachingAssignment(
                subjectDetailMap.get(jlSubjectDetail.getSelectedValue()), 
                classMap.get(jlClass.getSelectedValue()), 
                teacherMap.get(jlTeacher.getSelectedValue()), 
                Integer.valueOf((String)cboShift.getSelectedItem()), 
                classroomMap.get(jlClassroom.getSelectedValue()), 
                2, season, 1, LocalDate.parse(LocalDate.now().toString(), format), 
                txtDescriptor.getText());
        if(service.createTeachingAssignment(o)) {
            JOptionPane.showMessageDialog(this, "Thêm phân công dạy học thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm phân công dạy học thất bại");
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ArrangeTeaching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ArrangeTeaching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ArrangeTeaching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ArrangeTeaching.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ArrangeTeaching dialog = new ArrangeTeaching(new JDialog(), true);
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
    private javax.swing.JButton btnSubmit;
    private javax.swing.JComboBox<String> cboShift;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JList<String> jlClass;
    private javax.swing.JList<String> jlClassroom;
    private javax.swing.JList<String> jlMajor;
    private javax.swing.JList<String> jlSpecificallyMajor;
    private javax.swing.JList<String> jlSubjectDetail;
    private javax.swing.JList<String> jlTeacher;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblClassInfo;
    private javax.swing.JLabel lblClassroom;
    private javax.swing.JLabel lblClassroomList;
    private javax.swing.JLabel lblMajor;
    private javax.swing.JLabel lblShift;
    private javax.swing.JLabel lblShiftInfo;
    private javax.swing.JLabel lblSpecificallyMajor;
    private javax.swing.JLabel lblSpecificallyMajorInfo;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblSubjectInfo;
    private javax.swing.JLabel lblTeacher;
    private javax.swing.JLabel lblTeacherNameInfo;
    private javax.swing.JTextField txtClassInfo;
    private javax.swing.JTextField txtClassroom;
    private javax.swing.JTextArea txtDescriptor;
    private javax.swing.JTextField txtShift;
    private javax.swing.JTextField txtSpecificallyMajor;
    private javax.swing.JTextField txtSubjectInfo;
    private javax.swing.JTextField txtTeacherNameInfo;
    // End of variables declaration//GEN-END:variables

    private void initMajor() {
        listMajor = service.getAllMajors(false);
        majorMap = new TreeMap<>();
        for (Major o : listMajor) {
            majorMap.put(o.getMajorName() + "-" + o.getMajorCode(), o.getMajorID());
        }
    }

    private void fillMajorToList() {
        jlMajor.setModel(modelListMajor);
        modelListMajor.removeAllElements();
        for (Map.Entry<String, Integer> entry : majorMap.entrySet()) {
            modelListMajor.addElement(entry.getKey());
        }
    }

    private void defineSpecificallyMajor() {
        if (jlMajor.getSelectedIndices().length == 1) {
            int majorID = majorMap.get(jlMajor.getSelectedValue());
            specificallyMajorMap = new TreeMap<>();
            listSpecificallyMajor = service.getSpecificallyMajorByMajorID(majorID, false);

            if (listSpecificallyMajor != null) {
                for (SpecificallyMajor o : listSpecificallyMajor) {
                    specificallyMajorMap.put(o.getSpecificallyMajorName() + " - " + o.getSpecificallyMajorCode(), o.getSpecificallyMajorID());
                }
            }

            jlSpecificallyMajor.setModel(modelListSpecificallyMajor);
            modelListSpecificallyMajor.removeAllElements();
            for (Map.Entry<String, Integer> entry : specificallyMajorMap.entrySet()) {
                modelListSpecificallyMajor.addElement(entry.getKey());
            }
        }
    }

    private void defineSubjectDetail() {
        if (jlSpecificallyMajor.getSelectedIndices().length == 1) {
            int specificallyMajorID = specificallyMajorMap.get(jlSpecificallyMajor.getSelectedValue());
            subjectDetailMap = new TreeMap<>();
            listSubjectDetail = service.getSubjectBySpecificallyMajorID(specificallyMajorID, false);

            if (listSubjectDetail != null) {
                for (SubjectDetail o : listSubjectDetail) {
                    subjectDetailMap.put(o.getSubjectName() + " - " + o.getSubjectCode(), o.getSubjectDetailID());
                }

                jlSubjectDetail.setModel(modelListSubjectDetail);
                modelListSubjectDetail.removeAllElements();
                for (Map.Entry<String, Integer> entry : subjectDetailMap.entrySet()) {
                    modelListSubjectDetail.addElement(entry.getKey());
                }
            }
        }

    }

    private void defineClassAvailabel() {
        if (jlSubjectDetail.getSelectedIndices().length == 1) {
            int subjectDetailID = subjectDetailMap.get(jlSubjectDetail.getSelectedValue());
            int specificallyMajorID = specificallyMajorMap.get(jlSpecificallyMajor.getSelectedValue());

            listClass = service.getClassFromSubjectDetailAvailable(subjectDetailID, specificallyMajorID, false);
            classMap = new TreeMap<>();
            for (Classs o : listClass) {
                classMap.put(o.getClassCode(), o.getClassID());
            }

            jlClass.setModel(modelListClass);
            modelListClass.removeAllElements();
            for (Map.Entry<String, Integer> entry : classMap.entrySet()) {
                modelListClass.addElement(entry.getKey());
            }
        }
    }

    private void defineTeacher() {
        if (jlMajor.getSelectedIndices().length == 1) {
            int majorID = majorMap.get(jlMajor.getSelectedValue());
            listTeacher = service.getListTeacherByMajorID(majorID, false);

            if (listTeacher != null) {
                teacherMap = new TreeMap<>();
                for (Teacher o : listTeacher) {
                    teacherMap.put(
                            o.getTeacherName() + " - " + getMSGV(o.getTeacherID()),
                            o.getTeacherID());
                }

                jlTeacher.setModel(modelListTeacher);
                modelListTeacher.removeAllElements();
                for (Map.Entry<String, Integer> entry : teacherMap.entrySet()) {
                    modelListTeacher.addElement(entry.getKey());
                }
            }

        }

    }

    private String getMSGV(int TeacherID) {
        String defaultStr = "00000" + TeacherID;
        String res = "FE" + defaultStr.substring(defaultStr.length() - 5, defaultStr.length());
        return res.toUpperCase();
    }

    private void defineClassroom() {
        int shift = Integer.parseInt((String) cboShift.getSelectedItem());

        listClassroom = service.getClassroomAvailableFromShift(shift);
        if (listClassroom != null) {
            classroomMap = new TreeMap<>();
            for (Classroom o : listClassroom) {
                classroomMap.put(service.getClassroomCodeByClassroomID(o.getClassroomID()), o.getClassroomID());
            }
            
            jlClassroom.setModel(modelListClassroom);
            modelListClassroom.removeAllElements();
            for (Map.Entry<String, Integer> entry : classroomMap.entrySet()) {
                modelListClassroom.addElement(entry.getKey());
            }
        }
        
    }
    

}
