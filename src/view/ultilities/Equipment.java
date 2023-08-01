package view.ultilities;

import view.login.Login;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import model.beans.Building;
import model.beans.Classroom;
import model.beans.Classs;
import model.beans.SpecificallyMajor;
import model.beans.SubjectDetail;
import service.Service;

/**
 *
 * @author nguye
 */
public class Equipment extends javax.swing.JDialog {

    private final CardLayout cardLayout;
    private final Service service;
    private Map<String, Integer> mapMajorClass;
    private final DefaultListModel<String> modelMajorClass;
    private List<SpecificallyMajor> listSpecificallyMajor;
    private TreeMap<String, Integer> mapSpecificallyMajor;
    private final DefaultListModel<String> modelSpecificallyMajorClass;
    private List<Classs> listClassClass;
    private TreeMap<String, Integer> mapClassClass;
    private final DefaultListModel<String> modelClassClass;
    private Map<String, Integer> mapMajorSubject;
    private TreeMap<String, Integer> mapSpecificallyMajorSubject;
    private final DefaultListModel<String> modelSpecificallyMajorSubject;
    private List<SpecificallyMajor> listSpecificallyMajorSubject;
    private List<SubjectDetail> listSubject;
    private TreeMap<String, Integer> mapSubject;
    private final DefaultListModel<String> modelSubject;
    private final CardLayout cardLayout1;
    private Map<String, Integer> mapMajorCreateClass;
    private final DefaultListModel<String> modelMajorCC;
    private List<SpecificallyMajor> listSpecificallyMajorCC;
    private TreeMap<String, Integer> mapSpecificallyMajorCC;
    private final DefaultListModel<String> modelSpecificallyMajorCC;
    private Map<String, Integer> mapMajorCreateSubject;
    private final DefaultListModel<String> modelMajorCS;
    private List<SpecificallyMajor> listSpecificallyMajorCS;
    private TreeMap<String, Integer> mapSpecificallyMajorCS;
    private final DefaultListModel<String> modelSpecificallyMajorCS;
    private List<SubjectDetail> listSubjectHaveNotDone;
    private TreeMap<String, Integer> mapSubjectHaveNotDone;
    private final DefaultListModel<String> modelSubjectHaveNotDone;
    private int check;
    private SubjectDetail subjectDetailHaveNotDone;
    private Map<String, Integer> mapMajorCreateSpecificallyMajor;
    private final DefaultListModel<String> modelMajorCSM;
    private List<Building> listBuilding;
    private final DefaultListModel<String> modelListBuilding;
    private TreeMap<String, Integer> mapBuilding;
    private Map<String, Integer> mapSpecificallyMajorBySM;
    private final DefaultListModel<String> modelM;
    private List<SpecificallyMajor> listSM;
    private TreeMap<String, SpecificallyMajor> mapSM;
    private final DefaultListModel<String> modelSM;
    private SpecificallyMajor sm;
    private List<Building> listBuildingForClass;
    private TreeMap<String, Integer> mapBuildingForClass;
    private final DefaultListModel<String> modelBuildingName;
    private List<Classroom> listClass;
    private TreeMap<String, Classroom> mapClassForClass;
    private final DefaultListModel<String> modelRoom;

    /**
     * Creates new form Equipment
     */
    public Equipment(JDialog parent, boolean modal) {
        super(parent, modal);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                Login.load.dispose();
            }
        });
        initComponents();

        designButton(btnClassmanagement, 0x1e3799, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnSubjectManagement, 0xd35400, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnOtherManagement, 0xd05c46b, 0xecf0f1, 5, 10, 5, 10);

        cardLayout = (CardLayout) jPanel2.getLayout();
        getContentPane().setBackground(new Color(0x0c2461));

        cardLayout1 = (CardLayout) panelCreate.getLayout();
        setLocationRelativeTo(null);

        designButton(btnUpdateClass, 0x6F1E51, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnDeleteClass, 0xEA2027, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnUpdateSubject, 0x6F1E51, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnDeleteSubject, 0xEA2027, 0xecf0f1, 5, 10, 5, 10);

        designButton(btnCreateClass, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnCreateSubject, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnCreateSpecificallyMajor, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnCreateClassroom, 0x009432, 0xecf0f1, 5, 10, 5, 10);

        designButton(btnConfirmCC, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnClassmanagement, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnCreateClassroom, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnUpdateSpecificallyMajor, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnSubmit, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnCS, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnConfirmCC, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnSubmitCreateClassroom, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnSpecificallyMajor, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnClassroom, 0x009432, 0xecf0f1, 5, 10, 5, 10);

        designButton(btnDeleteClassroom, 0x009432, 0xecf0f1, 5, 10, 5, 10);
        designButton(btnUpdateClassroom, 0x009432, 0xecf0f1, 5, 10, 5, 10);

        btnCreateClass.setVisible(false);
        btnCreateSubject.setVisible(false);
        btnCreateClassroom.setVisible(false);
        btnCreateSpecificallyMajor.setVisible(false);

        // service 
        service = new Service();

        // model
        modelMajorClass = new DefaultListModel<>();
        modelSpecificallyMajorClass = new DefaultListModel<>();
        modelClassClass = new DefaultListModel<>();
        modelSpecificallyMajorSubject = new DefaultListModel<>();
        modelSubject = new DefaultListModel<>();

        modelMajorCC = new DefaultListModel<>();
        modelSpecificallyMajorCC = new DefaultListModel<>();
        modelSpecificallyMajorCS = new DefaultListModel<>();
        modelMajorCSM = new DefaultListModel<>();
        modelListBuilding = new DefaultListModel<>();
        modelM = new DefaultListModel<>();
        modelSM = new DefaultListModel<>();
        modelBuildingName = new DefaultListModel<>();
        modelRoom = new DefaultListModel<String>();

        modelMajorCS = new DefaultListModel<>();
        modelSubjectHaveNotDone = new DefaultListModel<>();

//        modelMajorSubject = new DefaultListModel<String>();
//        modelSpecificallyMajorSubject = new DefaultListModel<String>();
//        modelMajorSubject = new DefaultListModel<String>();
        initMajorClass();
        initMajorSpecificallyMajor();
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

        jPanel1 = new javax.swing.JPanel();
        btnClassmanagement = new javax.swing.JButton();
        btnSubjectManagement = new javax.swing.JButton();
        btnSpecificallyMajor = new javax.swing.JButton();
        btnClassroom = new javax.swing.JButton();
        btnOtherManagement = new javax.swing.JButton();
        btnCreateClass = new javax.swing.JButton();
        btnCreateSubject = new javax.swing.JButton();
        btnCreateSpecificallyMajor = new javax.swing.JButton();
        btnCreateClassroom = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelClass = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblMajor = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlMajorClass = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        lblClass = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jlClassClass = new javax.swing.JList<>();
        jPanel8 = new javax.swing.JPanel();
        lblClassInfo = new javax.swing.JLabel();
        pnClassCode = new javax.swing.JPanel();
        lblClasscode = new javax.swing.JLabel();
        txtClasscode = new javax.swing.JTextField();
        pnMajor = new javax.swing.JPanel();
        lblMajorClassInfo = new javax.swing.JLabel();
        txtMajorClassInfo = new javax.swing.JTextField();
        pnSpecificallyMajor = new javax.swing.JPanel();
        lblSpecificallyMajorClassInfo = new javax.swing.JLabel();
        txtSpecificallyMajorClassInfo = new javax.swing.JTextField();
        pnBatch = new javax.swing.JPanel();
        lblBatch = new javax.swing.JLabel();
        txtBatch = new javax.swing.JTextField();
        pnLastModified = new javax.swing.JPanel();
        lblLastModified = new javax.swing.JLabel();
        txtLastModified = new javax.swing.JTextField();
        pnLastModified1 = new javax.swing.JPanel();
        lblDescriptor = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescriptor = new javax.swing.JTextArea();
        btnUpdateClass = new javax.swing.JButton();
        btnDeleteClass = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        lblSpecificallyMajorClass = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jlSpecificallyMajorClass = new javax.swing.JList<>();
        panelSubject = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblMajor1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jlMajorSubject = new javax.swing.JList<>();
        jPanel11 = new javax.swing.JPanel();
        lblSubject = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jlSubject = new javax.swing.JList<>();
        jPanel12 = new javax.swing.JPanel();
        lblClassInfo1 = new javax.swing.JLabel();
        pnSubject = new javax.swing.JPanel();
        lblSubjectName = new javax.swing.JLabel();
        txtSubjectName = new javax.swing.JTextField();
        pnSubjectCode = new javax.swing.JPanel();
        lblSujectCode = new javax.swing.JLabel();
        txtSubjectCode = new javax.swing.JTextField();
        pnSpecificallyMajor1 = new javax.swing.JPanel();
        lblSpecificallyMajorClassInfo1 = new javax.swing.JLabel();
        txtSpecificallyMajorClassInfo1 = new javax.swing.JTextField();
        pnFurlough = new javax.swing.JPanel();
        lblFurlough = new javax.swing.JLabel();
        txtFurlough = new javax.swing.JTextField();
        pnLastModified2 = new javax.swing.JPanel();
        lblLastModified1 = new javax.swing.JLabel();
        txtLastModified1 = new javax.swing.JTextField();
        pnLastModified3 = new javax.swing.JPanel();
        lblDescriptor1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtDescriptor1 = new javax.swing.JTextArea();
        btnUpdateSubject = new javax.swing.JButton();
        btnDeleteSubject = new javax.swing.JButton();
        pnSpecificallyMajor3 = new javax.swing.JPanel();
        lblCredit = new javax.swing.JLabel();
        txtCredit = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        lblSpecificallyMajor = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jlSpecificallyMajorSubject = new javax.swing.JList<>();
        panelCreate = new javax.swing.JPanel();
        panelCreateClass = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelMajorSubject = new javax.swing.JPanel();
        lblMajor2 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jlMajorCreateClass = new javax.swing.JList<>();
        panelSpecificallyMajorSubject = new javax.swing.JPanel();
        lblSpecificallyMajor1 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jlSpecificallyMajorCreateClass = new javax.swing.JList<>();
        panelSubjectInfo = new javax.swing.JPanel();
        lblClassInfo2 = new javax.swing.JLabel();
        pnSpecificallyMajor2 = new javax.swing.JPanel();
        lblSpecificallyMajorClassInfo2 = new javax.swing.JLabel();
        txtSpecificallyMajorClassInfoCC = new javax.swing.JTextField();
        pnLastModified4 = new javax.swing.JPanel();
        lblSpecificallyMajorCreate = new javax.swing.JLabel();
        txtMajorCC = new javax.swing.JTextField();
        pnLastModified5 = new javax.swing.JPanel();
        lblDescriptor2 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtDescriptorCC = new javax.swing.JTextArea();
        btnConfirmCC = new javax.swing.JButton();
        pnSpecificallyMajor4 = new javax.swing.JPanel();
        lblBatchCC = new javax.swing.JLabel();
        txtBatchCC = new javax.swing.JTextField();
        panelCreateSubject = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panelMajorSubject1 = new javax.swing.JPanel();
        lblMajor3 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jlMajorCS = new javax.swing.JList<>();
        panelSpecificallyMajorSubject1 = new javax.swing.JPanel();
        lblSpecificallyMajor2 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jlSpecificallyMajorCS = new javax.swing.JList<>();
        panelSubjectSubject1 = new javax.swing.JPanel();
        lblSubject2 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jlTypeOfSubject = new javax.swing.JList<>();
        panelSubjectInfo1 = new javax.swing.JPanel();
        lblClassInfo3 = new javax.swing.JLabel();
        pnSubject2 = new javax.swing.JPanel();
        lblSubjectName2 = new javax.swing.JLabel();
        txtSubjectNameCS = new javax.swing.JTextField();
        pnSubjectCode2 = new javax.swing.JPanel();
        lblSujectCode2 = new javax.swing.JLabel();
        txtSubjectCodeCS = new javax.swing.JTextField();
        pnSpecificallyMajor5 = new javax.swing.JPanel();
        lblSpecificallyMajorClassInfo3 = new javax.swing.JLabel();
        txtSpecificallyMajorCS = new javax.swing.JTextField();
        pnFurlough2 = new javax.swing.JPanel();
        lblFurlough2 = new javax.swing.JLabel();
        txtFurloughCS = new javax.swing.JTextField();
        pnLastModified6 = new javax.swing.JPanel();
        lblMajorCS = new javax.swing.JLabel();
        txtMajorCS = new javax.swing.JTextField();
        pnLastModified7 = new javax.swing.JPanel();
        lblDescriptor3 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtDescriptorCS = new javax.swing.JTextArea();
        btnCS = new javax.swing.JButton();
        btnUpdateSpecificallyMajor = new javax.swing.JButton();
        pnSpecificallyMajor6 = new javax.swing.JPanel();
        lblCredit2 = new javax.swing.JLabel();
        txtCreditCS = new javax.swing.JTextField();
        panelSubjectHaveNotDone = new javax.swing.JPanel();
        lblSpecificallyMajor3 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jlSubjectHaveNotDone = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        panelCreateSpecificallyMajor = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panelMajorSubject2 = new javax.swing.JPanel();
        lblMajor4 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jlMajorCreateSpecificallyMajor = new javax.swing.JList<>();
        panelSubjectInfo2 = new javax.swing.JPanel();
        lblClassInfo4 = new javax.swing.JLabel();
        pnSpecificallyMajorName = new javax.swing.JPanel();
        lblSpecificallyMajorName = new javax.swing.JLabel();
        txtSpecificallyMajorName = new javax.swing.JTextField();
        pnSpecificallyMajorCode = new javax.swing.JPanel();
        lblSpecificallyMajorCode = new javax.swing.JLabel();
        txtSpecificallyMajorCode = new javax.swing.JTextField();
        pnLastModified9 = new javax.swing.JPanel();
        lblDescriptor4 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        txtDescriptorCSM = new javax.swing.JTextArea();
        btnSubmit = new javax.swing.JButton();
        pnMajorName = new javax.swing.JPanel();
        lblMajorName = new javax.swing.JLabel();
        txtMajorName = new javax.swing.JTextField();
        panelCreateClassroom = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        panelClassroomInfo = new javax.swing.JPanel();
        lblClassInfo5 = new javax.swing.JLabel();
        pnSpecificallyMajorName1 = new javax.swing.JPanel();
        lblNumOfFloor = new javax.swing.JLabel();
        cboFloor = new javax.swing.JComboBox<>();
        pnSpecificallyMajorCode1 = new javax.swing.JPanel();
        lblSpecificallyMajorCode1 = new javax.swing.JLabel();
        cboRoom = new javax.swing.JComboBox<>();
        pnLastModified10 = new javax.swing.JPanel();
        lblDescriptor5 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtDescriptorCreateBuilding = new javax.swing.JTextArea();
        btnSubmitCreateClassroom = new javax.swing.JButton();
        panelMajorSubject3 = new javax.swing.JPanel();
        lblBuilding = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jlBuilding = new javax.swing.JList<>();
        panelSpecificallyMajor = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        lblMajor5 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jlMajorForSpecificallyMajor = new javax.swing.JList<>();
        jPanel15 = new javax.swing.JPanel();
        lblClassInfo6 = new javax.swing.JLabel();
        pnSubject1 = new javax.swing.JPanel();
        lblSubjectName1 = new javax.swing.JLabel();
        txtSMName = new javax.swing.JTextField();
        pnSubjectCode1 = new javax.swing.JPanel();
        lblSujectCode1 = new javax.swing.JLabel();
        txtSMCode = new javax.swing.JTextField();
        pnFurlough1 = new javax.swing.JPanel();
        lblFurlough1 = new javax.swing.JLabel();
        txtMajorNameForSM = new javax.swing.JTextField();
        pnLastModified8 = new javax.swing.JPanel();
        lblLastModified2 = new javax.swing.JLabel();
        txtLastModifiedForSM = new javax.swing.JTextField();
        pnLastModified11 = new javax.swing.JPanel();
        lblDescriptor6 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        txtDescriptorForSM = new javax.swing.JTextArea();
        btnUpdateSM = new javax.swing.JButton();
        btnDeleteSM = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        lblSpecificallyMajor4 = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jlSpecificallyMajorForMajor = new javax.swing.JList<>();
        panelClassroom = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lblMajor6 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jlBuildingName = new javax.swing.JList<>();
        jPanel20 = new javax.swing.JPanel();
        lblSpecificallyMajor6 = new javax.swing.JLabel();
        jScrollPane27 = new javax.swing.JScrollPane();
        jlRoom = new javax.swing.JList<>();
        jPanel16 = new javax.swing.JPanel();
        lblClassInfo7 = new javax.swing.JLabel();
        pnSubject3 = new javax.swing.JPanel();
        lblSubjectName3 = new javax.swing.JLabel();
        txtClassName = new javax.swing.JTextField();
        pnFurlough3 = new javax.swing.JPanel();
        lblFurlough3 = new javax.swing.JLabel();
        cboStatus = new javax.swing.JComboBox<>();
        pnLastModified12 = new javax.swing.JPanel();
        lblLastModified3 = new javax.swing.JLabel();
        txtLastModifiedForClass = new javax.swing.JTextField();
        pnLastModified13 = new javax.swing.JPanel();
        lblDescriptor7 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        txtDescriptorForClass = new javax.swing.JTextArea();
        btnUpdateClassroom = new javax.swing.JButton();
        btnDeleteClassroom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        btnClassmanagement.setText("Class");
        btnClassmanagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClassmanagementActionPerformed(evt);
            }
        });
        jPanel1.add(btnClassmanagement);

        btnSubjectManagement.setText("Subject");
        btnSubjectManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubjectManagementActionPerformed(evt);
            }
        });
        jPanel1.add(btnSubjectManagement);

        btnSpecificallyMajor.setText("SpecificallyMajor");
        btnSpecificallyMajor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSpecificallyMajorActionPerformed(evt);
            }
        });
        jPanel1.add(btnSpecificallyMajor);

        btnClassroom.setText("Classroom");
        btnClassroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClassroomActionPerformed(evt);
            }
        });
        jPanel1.add(btnClassroom);

        btnOtherManagement.setText("Other");
        btnOtherManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOtherManagementActionPerformed(evt);
            }
        });
        jPanel1.add(btnOtherManagement);

        btnCreateClass.setText("Create Class");
        btnCreateClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateClassActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreateClass);

        btnCreateSubject.setText("Create Subject");
        btnCreateSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateSubjectActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreateSubject);

        btnCreateSpecificallyMajor.setText("Create SpecificallyMajor");
        btnCreateSpecificallyMajor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateSpecificallyMajorActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreateSpecificallyMajor);

        btnCreateClassroom.setText("Create Classroom");
        btnCreateClassroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateClassroomActionPerformed(evt);
            }
        });
        jPanel1.add(btnCreateClassroom);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 5, 6);
        getContentPane().add(jPanel1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Equipment Management");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        getContentPane().add(jLabel1, gridBagConstraints);

        jPanel2.setLayout(new java.awt.CardLayout());

        panelClass.setBackground(new java.awt.Color(12, 36, 97));
        panelClass.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Class Management");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        panelClass.add(jLabel2, gridBagConstraints);

        jPanel6.setLayout(new java.awt.BorderLayout());

        lblMajor.setText("Ngành");
        jPanel6.add(lblMajor, java.awt.BorderLayout.NORTH);

        jlMajorClass.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlMajorClassValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jlMajorClass);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClass.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.BorderLayout());

        lblClass.setText("Lớp");
        jPanel7.add(lblClass, java.awt.BorderLayout.NORTH);

        jlClassClass.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlClassClassValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jlClassClass);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClass.add(jPanel7, gridBagConstraints);

        jPanel8.setBackground(new java.awt.Color(12, 36, 97));
        jPanel8.setLayout(new java.awt.GridBagLayout());

        lblClassInfo.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblClassInfo.setForeground(new java.awt.Color(255, 255, 255));
        lblClassInfo.setText("Thông tin lớp học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(lblClassInfo, gridBagConstraints);

        pnClassCode.setLayout(new java.awt.GridLayout(2, 1));

        lblClasscode.setText("Class code");
        pnClassCode.add(lblClasscode);

        txtClasscode.setEditable(false);
        txtClasscode.setColumns(10);
        pnClassCode.add(txtClasscode);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(pnClassCode, gridBagConstraints);

        pnMajor.setLayout(new java.awt.GridLayout(2, 1));

        lblMajorClassInfo.setText("Ngành");
        pnMajor.add(lblMajorClassInfo);

        txtMajorClassInfo.setEditable(false);
        txtMajorClassInfo.setColumns(10);
        pnMajor.add(txtMajorClassInfo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(pnMajor, gridBagConstraints);

        pnSpecificallyMajor.setLayout(new java.awt.GridLayout(2, 1));

        lblSpecificallyMajorClassInfo.setText("Ngành hẹp");
        pnSpecificallyMajor.add(lblSpecificallyMajorClassInfo);

        txtSpecificallyMajorClassInfo.setEditable(false);
        txtSpecificallyMajorClassInfo.setColumns(10);
        pnSpecificallyMajor.add(txtSpecificallyMajorClassInfo);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(pnSpecificallyMajor, gridBagConstraints);

        pnBatch.setLayout(new java.awt.GridLayout(2, 1));

        lblBatch.setText("Khóa");
        pnBatch.add(lblBatch);

        txtBatch.setColumns(20);
        pnBatch.add(txtBatch);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(pnBatch, gridBagConstraints);

        pnLastModified.setLayout(new java.awt.GridLayout(2, 1));

        lblLastModified.setText("Cập nhật cuối");
        pnLastModified.add(lblLastModified);

        txtLastModified.setEditable(false);
        txtLastModified.setColumns(20);
        pnLastModified.add(txtLastModified);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(pnLastModified, gridBagConstraints);

        pnLastModified1.setLayout(new java.awt.BorderLayout());

        lblDescriptor.setText("Descriptor");
        pnLastModified1.add(lblDescriptor, java.awt.BorderLayout.NORTH);

        txtDescriptor.setColumns(20);
        txtDescriptor.setRows(5);
        jScrollPane3.setViewportView(txtDescriptor);

        pnLastModified1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(pnLastModified1, gridBagConstraints);

        btnUpdateClass.setText("Update");
        btnUpdateClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateClassActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(btnUpdateClass, gridBagConstraints);

        btnDeleteClass.setText("Delete");
        btnDeleteClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClassActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel8.add(btnDeleteClass, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelClass.add(jPanel8, gridBagConstraints);

        jPanel9.setLayout(new java.awt.BorderLayout());

        lblSpecificallyMajorClass.setText("Ngành hẹp");
        jPanel9.add(lblSpecificallyMajorClass, java.awt.BorderLayout.NORTH);

        jlSpecificallyMajorClass.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSpecificallyMajorClassValueChanged(evt);
            }
        });
        jScrollPane7.setViewportView(jlSpecificallyMajorClass);

        jPanel9.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClass.add(jPanel9, gridBagConstraints);

        jPanel2.add(panelClass, "classCard");

        panelSubject.setBackground(new java.awt.Color(211, 84, 0));
        panelSubject.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Subject Management");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        panelSubject.add(jLabel3, gridBagConstraints);

        jPanel10.setLayout(new java.awt.BorderLayout());

        lblMajor1.setText("Ngành");
        jPanel10.add(lblMajor1, java.awt.BorderLayout.NORTH);

        jlMajorSubject.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlMajorSubjectValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(jlMajorSubject);

        jPanel10.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubject.add(jPanel10, gridBagConstraints);

        jPanel11.setLayout(new java.awt.BorderLayout());

        lblSubject.setText("Môn học");
        jPanel11.add(lblSubject, java.awt.BorderLayout.NORTH);

        jlSubject.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSubjectValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(jlSubject);

        jPanel11.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubject.add(jPanel11, gridBagConstraints);

        jPanel12.setBackground(new java.awt.Color(211, 84, 0));
        jPanel12.setLayout(new java.awt.GridBagLayout());

        lblClassInfo1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblClassInfo1.setForeground(new java.awt.Color(255, 255, 255));
        lblClassInfo1.setText("Thông tin môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(lblClassInfo1, gridBagConstraints);

        pnSubject.setLayout(new java.awt.GridLayout(2, 1));

        lblSubjectName.setText("Tên môn học");
        pnSubject.add(lblSubjectName);

        txtSubjectName.setEditable(false);
        txtSubjectName.setColumns(10);
        pnSubject.add(txtSubjectName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(pnSubject, gridBagConstraints);

        pnSubjectCode.setLayout(new java.awt.GridLayout(2, 1));

        lblSujectCode.setText("Mã môn học");
        pnSubjectCode.add(lblSujectCode);

        txtSubjectCode.setEditable(false);
        txtSubjectCode.setColumns(10);
        pnSubjectCode.add(txtSubjectCode);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(pnSubjectCode, gridBagConstraints);

        pnSpecificallyMajor1.setLayout(new java.awt.GridLayout(2, 1));

        lblSpecificallyMajorClassInfo1.setText("Ngành hẹp");
        pnSpecificallyMajor1.add(lblSpecificallyMajorClassInfo1);

        txtSpecificallyMajorClassInfo1.setEditable(false);
        txtSpecificallyMajorClassInfo1.setColumns(10);
        pnSpecificallyMajor1.add(txtSpecificallyMajorClassInfo1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(pnSpecificallyMajor1, gridBagConstraints);

        pnFurlough.setLayout(new java.awt.GridLayout(2, 1));

        lblFurlough.setText("Số ngày nghỉ được phép");
        pnFurlough.add(lblFurlough);

        txtFurlough.setColumns(20);
        pnFurlough.add(txtFurlough);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(pnFurlough, gridBagConstraints);

        pnLastModified2.setLayout(new java.awt.GridLayout(2, 1));

        lblLastModified1.setText("Cập nhật cuối");
        pnLastModified2.add(lblLastModified1);

        txtLastModified1.setEditable(false);
        txtLastModified1.setColumns(20);
        pnLastModified2.add(txtLastModified1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(pnLastModified2, gridBagConstraints);

        pnLastModified3.setLayout(new java.awt.BorderLayout());

        lblDescriptor1.setText("Mô tả");
        pnLastModified3.add(lblDescriptor1, java.awt.BorderLayout.NORTH);

        txtDescriptor1.setColumns(20);
        txtDescriptor1.setRows(5);
        jScrollPane6.setViewportView(txtDescriptor1);

        pnLastModified3.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(pnLastModified3, gridBagConstraints);

        btnUpdateSubject.setText("Update");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(btnUpdateSubject, gridBagConstraints);

        btnDeleteSubject.setText("Delete");
        btnDeleteSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSubjectActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(btnDeleteSubject, gridBagConstraints);

        pnSpecificallyMajor3.setLayout(new java.awt.GridLayout(2, 1));

        lblCredit.setText("Số chứng chỉ");
        pnSpecificallyMajor3.add(lblCredit);

        txtCredit.setColumns(10);
        pnSpecificallyMajor3.add(txtCredit);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel12.add(pnSpecificallyMajor3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelSubject.add(jPanel12, gridBagConstraints);

        jPanel17.setLayout(new java.awt.BorderLayout());

        lblSpecificallyMajor.setText("Ngành hẹp");
        jPanel17.add(lblSpecificallyMajor, java.awt.BorderLayout.NORTH);

        jlSpecificallyMajorSubject.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSpecificallyMajorSubjectValueChanged(evt);
            }
        });
        jScrollPane10.setViewportView(jlSpecificallyMajorSubject);

        jPanel17.add(jScrollPane10, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubject.add(jPanel17, gridBagConstraints);

        jPanel2.add(panelSubject, "subjectCard");

        panelCreate.setBackground(new java.awt.Color(211, 84, 0));
        panelCreate.setLayout(new java.awt.CardLayout());

        panelCreateClass.setBackground(new java.awt.Color(39, 174, 96));
        panelCreateClass.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Create class");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        panelCreateClass.add(jLabel4, gridBagConstraints);

        panelMajorSubject.setLayout(new java.awt.BorderLayout());

        lblMajor2.setText("Ngành");
        panelMajorSubject.add(lblMajor2, java.awt.BorderLayout.NORTH);

        jlMajorCreateClass.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlMajorCreateClassValueChanged(evt);
            }
        });
        jScrollPane8.setViewportView(jlMajorCreateClass);

        panelMajorSubject.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCreateClass.add(panelMajorSubject, gridBagConstraints);

        panelSpecificallyMajorSubject.setLayout(new java.awt.BorderLayout());

        lblSpecificallyMajor1.setText("Ngành hẹp");
        panelSpecificallyMajorSubject.add(lblSpecificallyMajor1, java.awt.BorderLayout.NORTH);

        jlSpecificallyMajorCreateClass.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSpecificallyMajorCreateClassValueChanged(evt);
            }
        });
        jScrollPane12.setViewportView(jlSpecificallyMajorCreateClass);

        panelSpecificallyMajorSubject.add(jScrollPane12, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCreateClass.add(panelSpecificallyMajorSubject, gridBagConstraints);

        panelSubjectInfo.setBackground(new java.awt.Color(39, 174, 96));
        panelSubjectInfo.setLayout(new java.awt.GridBagLayout());

        lblClassInfo2.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblClassInfo2.setForeground(new java.awt.Color(255, 255, 255));
        lblClassInfo2.setText("Thông tin môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo.add(lblClassInfo2, gridBagConstraints);

        pnSpecificallyMajor2.setLayout(new java.awt.GridLayout(2, 1));

        lblSpecificallyMajorClassInfo2.setText("Ngành hẹp");
        pnSpecificallyMajor2.add(lblSpecificallyMajorClassInfo2);

        txtSpecificallyMajorClassInfoCC.setEditable(false);
        txtSpecificallyMajorClassInfoCC.setColumns(10);
        pnSpecificallyMajor2.add(txtSpecificallyMajorClassInfoCC);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo.add(pnSpecificallyMajor2, gridBagConstraints);

        pnLastModified4.setLayout(new java.awt.GridLayout(2, 1));

        lblSpecificallyMajorCreate.setText("Ngành");
        pnLastModified4.add(lblSpecificallyMajorCreate);

        txtMajorCC.setEditable(false);
        txtMajorCC.setColumns(20);
        pnLastModified4.add(txtMajorCC);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo.add(pnLastModified4, gridBagConstraints);

        pnLastModified5.setLayout(new java.awt.BorderLayout());

        lblDescriptor2.setText("Mô tả");
        pnLastModified5.add(lblDescriptor2, java.awt.BorderLayout.NORTH);

        txtDescriptorCC.setColumns(20);
        txtDescriptorCC.setRows(5);
        jScrollPane11.setViewportView(txtDescriptorCC);

        pnLastModified5.add(jScrollPane11, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo.add(pnLastModified5, gridBagConstraints);

        btnConfirmCC.setText("Create");
        btnConfirmCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmCCActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo.add(btnConfirmCC, gridBagConstraints);

        pnSpecificallyMajor4.setLayout(new java.awt.GridLayout(2, 1));

        lblBatchCC.setText("Khóa");
        pnSpecificallyMajor4.add(lblBatchCC);

        txtBatchCC.setColumns(10);
        pnSpecificallyMajor4.add(txtBatchCC);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo.add(pnSpecificallyMajor4, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelCreateClass.add(panelSubjectInfo, gridBagConstraints);

        panelCreate.add(panelCreateClass, "createClass");

        panelCreateSubject.setBackground(new java.awt.Color(39, 174, 96));
        panelCreateSubject.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(214, 48, 49));
        jLabel5.setText("Xác minh môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 6.0;
        panelCreateSubject.add(jLabel5, gridBagConstraints);

        panelMajorSubject1.setLayout(new java.awt.BorderLayout());

        lblMajor3.setText("Ngành");
        panelMajorSubject1.add(lblMajor3, java.awt.BorderLayout.NORTH);

        jlMajorCS.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlMajorCSValueChanged(evt);
            }
        });
        jScrollPane13.setViewportView(jlMajorCS);

        panelMajorSubject1.add(jScrollPane13, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCreateSubject.add(panelMajorSubject1, gridBagConstraints);

        panelSpecificallyMajorSubject1.setLayout(new java.awt.BorderLayout());

        lblSpecificallyMajor2.setForeground(new java.awt.Color(214, 48, 49));
        lblSpecificallyMajor2.setText("Ngành hẹp");
        panelSpecificallyMajorSubject1.add(lblSpecificallyMajor2, java.awt.BorderLayout.NORTH);

        jlSpecificallyMajorCS.setForeground(new java.awt.Color(214, 48, 49));
        jlSpecificallyMajorCS.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSpecificallyMajorCSValueChanged(evt);
            }
        });
        jScrollPane14.setViewportView(jlSpecificallyMajorCS);

        panelSpecificallyMajorSubject1.add(jScrollPane14, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCreateSubject.add(panelSpecificallyMajorSubject1, gridBagConstraints);

        panelSubjectSubject1.setLayout(new java.awt.BorderLayout());

        lblSubject2.setText("Loại môn học");
        panelSubjectSubject1.add(lblSubject2, java.awt.BorderLayout.NORTH);

        jlTypeOfSubject.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "1", "2", "3" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jlTypeOfSubject.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlTypeOfSubjectValueChanged(evt);
            }
        });
        jScrollPane15.setViewportView(jlTypeOfSubject);

        panelSubjectSubject1.add(jScrollPane15, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCreateSubject.add(panelSubjectSubject1, gridBagConstraints);

        panelSubjectInfo1.setBackground(new java.awt.Color(39, 174, 96));
        panelSubjectInfo1.setLayout(new java.awt.GridBagLayout());

        lblClassInfo3.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblClassInfo3.setForeground(new java.awt.Color(255, 255, 255));
        lblClassInfo3.setText("Thông tin môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(lblClassInfo3, gridBagConstraints);

        pnSubject2.setLayout(new java.awt.GridLayout(2, 1));

        lblSubjectName2.setText("Tên môn học");
        pnSubject2.add(lblSubjectName2);

        txtSubjectNameCS.setColumns(10);
        pnSubject2.add(txtSubjectNameCS);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(pnSubject2, gridBagConstraints);

        pnSubjectCode2.setLayout(new java.awt.GridLayout(2, 1));

        lblSujectCode2.setText("Mã môn học");
        pnSubjectCode2.add(lblSujectCode2);

        txtSubjectCodeCS.setColumns(10);
        pnSubjectCode2.add(txtSubjectCodeCS);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(pnSubjectCode2, gridBagConstraints);

        pnSpecificallyMajor5.setLayout(new java.awt.GridLayout(2, 1));

        lblSpecificallyMajorClassInfo3.setForeground(new java.awt.Color(214, 48, 49));
        lblSpecificallyMajorClassInfo3.setText("Ngành hẹp");
        pnSpecificallyMajor5.add(lblSpecificallyMajorClassInfo3);

        txtSpecificallyMajorCS.setEditable(false);
        txtSpecificallyMajorCS.setColumns(10);
        txtSpecificallyMajorCS.setForeground(new java.awt.Color(214, 48, 49));
        pnSpecificallyMajor5.add(txtSpecificallyMajorCS);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(pnSpecificallyMajor5, gridBagConstraints);

        pnFurlough2.setLayout(new java.awt.GridLayout(2, 1));

        lblFurlough2.setText("Số ngày nghỉ được phép");
        pnFurlough2.add(lblFurlough2);

        txtFurloughCS.setColumns(20);
        pnFurlough2.add(txtFurloughCS);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(pnFurlough2, gridBagConstraints);

        pnLastModified6.setLayout(new java.awt.GridLayout(2, 1));

        lblMajorCS.setText("Ngành");
        pnLastModified6.add(lblMajorCS);

        txtMajorCS.setEditable(false);
        txtMajorCS.setColumns(20);
        pnLastModified6.add(txtMajorCS);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(pnLastModified6, gridBagConstraints);

        pnLastModified7.setLayout(new java.awt.BorderLayout());

        lblDescriptor3.setText("Mô tả");
        pnLastModified7.add(lblDescriptor3, java.awt.BorderLayout.NORTH);

        txtDescriptorCS.setColumns(20);
        txtDescriptorCS.setRows(5);
        jScrollPane16.setViewportView(txtDescriptorCS);

        pnLastModified7.add(jScrollPane16, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(pnLastModified7, gridBagConstraints);

        btnCS.setText("Create");
        btnCS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCSActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(btnCS, gridBagConstraints);

        btnUpdateSpecificallyMajor.setText("Update SpecificallyMajor");
        btnUpdateSpecificallyMajor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSpecificallyMajorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(btnUpdateSpecificallyMajor, gridBagConstraints);

        pnSpecificallyMajor6.setLayout(new java.awt.GridLayout(2, 1));

        lblCredit2.setText("Số chứng chỉ");
        pnSpecificallyMajor6.add(lblCredit2);

        txtCreditCS.setColumns(10);
        pnSpecificallyMajor6.add(txtCreditCS);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo1.add(pnSpecificallyMajor6, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelCreateSubject.add(panelSubjectInfo1, gridBagConstraints);

        panelSubjectHaveNotDone.setLayout(new java.awt.BorderLayout());

        lblSpecificallyMajor3.setForeground(new java.awt.Color(214, 48, 49));
        lblSpecificallyMajor3.setText("Danh sách môn học chưa xác minh ngành học");
        panelSubjectHaveNotDone.add(lblSpecificallyMajor3, java.awt.BorderLayout.NORTH);

        jlSubjectHaveNotDone.setForeground(new java.awt.Color(214, 48, 49));
        jlSubjectHaveNotDone.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSubjectHaveNotDoneValueChanged(evt);
            }
        });
        jScrollPane17.setViewportView(jlSubjectHaveNotDone);

        panelSubjectHaveNotDone.add(jScrollPane17, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCreateSubject.add(panelSubjectHaveNotDone, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tạo môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 6.0;
        panelCreateSubject.add(jLabel6, gridBagConstraints);

        panelCreate.add(panelCreateSubject, "createSubject");

        panelCreateSpecificallyMajor.setBackground(new java.awt.Color(39, 174, 96));
        panelCreateSpecificallyMajor.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Create specifically major");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        panelCreateSpecificallyMajor.add(jLabel7, gridBagConstraints);

        panelMajorSubject2.setLayout(new java.awt.BorderLayout());

        lblMajor4.setText("Ngành");
        panelMajorSubject2.add(lblMajor4, java.awt.BorderLayout.NORTH);

        jlMajorCreateSpecificallyMajor.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlMajorCreateSpecificallyMajorValueChanged(evt);
            }
        });
        jScrollPane9.setViewportView(jlMajorCreateSpecificallyMajor);

        panelMajorSubject2.add(jScrollPane9, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelCreateSpecificallyMajor.add(panelMajorSubject2, gridBagConstraints);

        panelSubjectInfo2.setBackground(new java.awt.Color(39, 174, 96));
        panelSubjectInfo2.setLayout(new java.awt.GridBagLayout());

        lblClassInfo4.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblClassInfo4.setForeground(new java.awt.Color(255, 255, 255));
        lblClassInfo4.setText("Thông tin môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo2.add(lblClassInfo4, gridBagConstraints);

        pnSpecificallyMajorName.setLayout(new java.awt.GridLayout(2, 1));

        lblSpecificallyMajorName.setText("Tên Ngành hẹp");
        pnSpecificallyMajorName.add(lblSpecificallyMajorName);

        txtSpecificallyMajorName.setColumns(10);
        pnSpecificallyMajorName.add(txtSpecificallyMajorName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo2.add(pnSpecificallyMajorName, gridBagConstraints);

        pnSpecificallyMajorCode.setLayout(new java.awt.GridLayout(2, 1));

        lblSpecificallyMajorCode.setText("Mã ngành hẹp");
        pnSpecificallyMajorCode.add(lblSpecificallyMajorCode);

        txtSpecificallyMajorCode.setColumns(20);
        pnSpecificallyMajorCode.add(txtSpecificallyMajorCode);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo2.add(pnSpecificallyMajorCode, gridBagConstraints);

        pnLastModified9.setLayout(new java.awt.BorderLayout());

        lblDescriptor4.setText("Mô tả");
        pnLastModified9.add(lblDescriptor4, java.awt.BorderLayout.NORTH);

        txtDescriptorCSM.setColumns(20);
        txtDescriptorCSM.setRows(5);
        jScrollPane19.setViewportView(txtDescriptorCSM);

        pnLastModified9.add(jScrollPane19, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo2.add(pnLastModified9, gridBagConstraints);

        btnSubmit.setText("Create");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo2.add(btnSubmit, gridBagConstraints);

        pnMajorName.setLayout(new java.awt.GridLayout(2, 1));

        lblMajorName.setText("Tên ngành");
        pnMajorName.add(lblMajorName);

        txtMajorName.setEditable(false);
        txtMajorName.setColumns(10);
        pnMajorName.add(txtMajorName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSubjectInfo2.add(pnMajorName, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelCreateSpecificallyMajor.add(panelSubjectInfo2, gridBagConstraints);

        panelCreate.add(panelCreateSpecificallyMajor, "createSpecificallyMajor");

        panelCreateClassroom.setBackground(new java.awt.Color(39, 174, 96));
        panelCreateClassroom.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Create Classroom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        panelCreateClassroom.add(jLabel8, gridBagConstraints);

        panelClassroomInfo.setBackground(new java.awt.Color(39, 174, 96));
        panelClassroomInfo.setLayout(new java.awt.GridBagLayout());

        lblClassInfo5.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblClassInfo5.setForeground(new java.awt.Color(255, 255, 255));
        lblClassInfo5.setText("Thông tin môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClassroomInfo.add(lblClassInfo5, gridBagConstraints);

        pnSpecificallyMajorName1.setLayout(new java.awt.GridLayout(2, 1));

        lblNumOfFloor.setText("Tầng");
        pnSpecificallyMajorName1.add(lblNumOfFloor);

        cboFloor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tầng 1", "Tầng 2", "Tầng 3", "Tầng 4" }));
        pnSpecificallyMajorName1.add(cboFloor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClassroomInfo.add(pnSpecificallyMajorName1, gridBagConstraints);

        pnSpecificallyMajorCode1.setLayout(new java.awt.GridLayout(2, 1));

        lblSpecificallyMajorCode1.setText("Phòng");
        pnSpecificallyMajorCode1.add(lblSpecificallyMajorCode1);

        cboRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng 1", "Phòng 2", "Phòng 3", "Phòng 4", "Phòng 5", "Phòng 6", "Phòng 7", "Phòng 8", "Phòng 9", "Phòng 10" }));
        pnSpecificallyMajorCode1.add(cboRoom);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClassroomInfo.add(pnSpecificallyMajorCode1, gridBagConstraints);

        pnLastModified10.setLayout(new java.awt.BorderLayout());

        lblDescriptor5.setText("Mô tả");
        pnLastModified10.add(lblDescriptor5, java.awt.BorderLayout.NORTH);

        txtDescriptorCreateBuilding.setColumns(20);
        txtDescriptorCreateBuilding.setRows(5);
        jScrollPane20.setViewportView(txtDescriptorCreateBuilding);

        pnLastModified10.add(jScrollPane20, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClassroomInfo.add(pnLastModified10, gridBagConstraints);

        btnSubmitCreateClassroom.setText("Create");
        btnSubmitCreateClassroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitCreateClassroomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClassroomInfo.add(btnSubmitCreateClassroom, gridBagConstraints);

        panelMajorSubject3.setLayout(new java.awt.BorderLayout());

        lblBuilding.setText("Tòa");
        panelMajorSubject3.add(lblBuilding, java.awt.BorderLayout.NORTH);

        jlBuilding.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlBuildingValueChanged(evt);
            }
        });
        jScrollPane18.setViewportView(jlBuilding);

        panelMajorSubject3.add(jScrollPane18, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClassroomInfo.add(panelMajorSubject3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelCreateClassroom.add(panelClassroomInfo, gridBagConstraints);

        panelCreate.add(panelCreateClassroom, "createClassroom");

        jPanel2.add(panelCreate, "CreateCard");

        panelSpecificallyMajor.setBackground(new java.awt.Color(211, 84, 0));
        panelSpecificallyMajor.setLayout(new java.awt.GridBagLayout());

        jLabel9.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Specifically Major");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        panelSpecificallyMajor.add(jLabel9, gridBagConstraints);

        jPanel13.setLayout(new java.awt.BorderLayout());

        lblMajor5.setText("Ngành");
        jPanel13.add(lblMajor5, java.awt.BorderLayout.NORTH);

        jlMajorForSpecificallyMajor.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlMajorForSpecificallyMajorValueChanged(evt);
            }
        });
        jScrollPane21.setViewportView(jlMajorForSpecificallyMajor);

        jPanel13.add(jScrollPane21, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSpecificallyMajor.add(jPanel13, gridBagConstraints);

        jPanel15.setBackground(new java.awt.Color(211, 84, 0));
        jPanel15.setLayout(new java.awt.GridBagLayout());

        lblClassInfo6.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblClassInfo6.setForeground(new java.awt.Color(255, 255, 255));
        lblClassInfo6.setText("Thông tin môn học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(lblClassInfo6, gridBagConstraints);

        pnSubject1.setLayout(new java.awt.GridLayout(2, 1));

        lblSubjectName1.setText("Tên ngành hẹp");
        pnSubject1.add(lblSubjectName1);

        txtSMName.setColumns(10);
        pnSubject1.add(txtSMName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(pnSubject1, gridBagConstraints);

        pnSubjectCode1.setLayout(new java.awt.GridLayout(2, 1));

        lblSujectCode1.setText("Mã ngành hẹp");
        pnSubjectCode1.add(lblSujectCode1);

        txtSMCode.setColumns(10);
        pnSubjectCode1.add(txtSMCode);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(pnSubjectCode1, gridBagConstraints);

        pnFurlough1.setLayout(new java.awt.GridLayout(2, 1));

        lblFurlough1.setText("Tên ngành");
        pnFurlough1.add(lblFurlough1);

        txtMajorNameForSM.setEditable(false);
        txtMajorNameForSM.setColumns(20);
        pnFurlough1.add(txtMajorNameForSM);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(pnFurlough1, gridBagConstraints);

        pnLastModified8.setLayout(new java.awt.GridLayout(2, 1));

        lblLastModified2.setText("Cập nhật cuối");
        pnLastModified8.add(lblLastModified2);

        txtLastModifiedForSM.setEditable(false);
        txtLastModifiedForSM.setColumns(20);
        pnLastModified8.add(txtLastModifiedForSM);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(pnLastModified8, gridBagConstraints);

        pnLastModified11.setLayout(new java.awt.BorderLayout());

        lblDescriptor6.setText("Mô tả");
        pnLastModified11.add(lblDescriptor6, java.awt.BorderLayout.NORTH);

        txtDescriptorForSM.setColumns(20);
        txtDescriptorForSM.setRows(5);
        jScrollPane23.setViewportView(txtDescriptorForSM);

        pnLastModified11.add(jScrollPane23, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(pnLastModified11, gridBagConstraints);

        btnUpdateSM.setText("Update");
        btnUpdateSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateSMActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(btnUpdateSM, gridBagConstraints);

        btnDeleteSM.setText("Delete");
        btnDeleteSM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteSMActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel15.add(btnDeleteSM, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelSpecificallyMajor.add(jPanel15, gridBagConstraints);

        jPanel18.setLayout(new java.awt.BorderLayout());

        lblSpecificallyMajor4.setText("Ngành hẹp");
        jPanel18.add(lblSpecificallyMajor4, java.awt.BorderLayout.NORTH);

        jlSpecificallyMajorForMajor.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlSpecificallyMajorForMajorValueChanged(evt);
            }
        });
        jScrollPane24.setViewportView(jlSpecificallyMajorForMajor);

        jPanel18.add(jScrollPane24, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelSpecificallyMajor.add(jPanel18, gridBagConstraints);

        jPanel2.add(panelSpecificallyMajor, "specificallyMajor");

        panelClassroom.setBackground(new java.awt.Color(211, 84, 0));
        panelClassroom.setLayout(new java.awt.GridBagLayout());

        jLabel10.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Classroom");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 3.0;
        panelClassroom.add(jLabel10, gridBagConstraints);

        jPanel14.setLayout(new java.awt.BorderLayout());

        lblMajor6.setText("Tòa");
        jPanel14.add(lblMajor6, java.awt.BorderLayout.NORTH);

        jlBuildingName.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlBuildingNameValueChanged(evt);
            }
        });
        jScrollPane22.setViewportView(jlBuildingName);

        jPanel14.add(jScrollPane22, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClassroom.add(jPanel14, gridBagConstraints);

        jPanel20.setLayout(new java.awt.BorderLayout());

        lblSpecificallyMajor6.setText("Phòng");
        jPanel20.add(lblSpecificallyMajor6, java.awt.BorderLayout.NORTH);

        jlRoom.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlRoomValueChanged(evt);
            }
        });
        jScrollPane27.setViewportView(jlRoom);

        jPanel20.add(jScrollPane27, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        panelClassroom.add(jPanel20, gridBagConstraints);

        jPanel16.setBackground(new java.awt.Color(211, 84, 0));
        jPanel16.setLayout(new java.awt.GridBagLayout());

        lblClassInfo7.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblClassInfo7.setForeground(new java.awt.Color(255, 255, 255));
        lblClassInfo7.setText("Thông tin phòng học");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel16.add(lblClassInfo7, gridBagConstraints);

        pnSubject3.setLayout(new java.awt.GridLayout(2, 1));

        lblSubjectName3.setText("Tên phòng");
        pnSubject3.add(lblSubjectName3);

        txtClassName.setEditable(false);
        txtClassName.setColumns(10);
        pnSubject3.add(txtClassName);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel16.add(pnSubject3, gridBagConstraints);

        pnFurlough3.setLayout(new java.awt.GridLayout(2, 1));

        lblFurlough3.setText("Trạng thái");
        pnFurlough3.add(lblFurlough3);

        cboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        pnFurlough3.add(cboStatus);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel16.add(pnFurlough3, gridBagConstraints);

        pnLastModified12.setLayout(new java.awt.GridLayout(2, 1));

        lblLastModified3.setText("Cập nhật cuối");
        pnLastModified12.add(lblLastModified3);

        txtLastModifiedForClass.setEditable(false);
        txtLastModifiedForClass.setColumns(20);
        pnLastModified12.add(txtLastModifiedForClass);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel16.add(pnLastModified12, gridBagConstraints);

        pnLastModified13.setLayout(new java.awt.BorderLayout());

        lblDescriptor7.setText("Mô tả");
        pnLastModified13.add(lblDescriptor7, java.awt.BorderLayout.NORTH);

        txtDescriptorForClass.setColumns(20);
        txtDescriptorForClass.setRows(5);
        jScrollPane25.setViewportView(txtDescriptorForClass);

        pnLastModified13.add(jScrollPane25, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel16.add(pnLastModified13, gridBagConstraints);

        btnUpdateClassroom.setText("Update");
        btnUpdateClassroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateClassroomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel16.add(btnUpdateClassroom, gridBagConstraints);

        btnDeleteClassroom.setText("Delete");
        btnDeleteClassroom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteClassroomActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel16.add(btnDeleteClassroom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 2.0;
        panelClassroom.add(jPanel16, gridBagConstraints);

        jPanel2.add(panelClassroom, "Classroom");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel2, gridBagConstraints);

        setBounds(0, 0, 995, 716);
    }// </editor-fold>//GEN-END:initComponents

    private void btnClassmanagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClassmanagementActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "classCard");
        getContentPane().setBackground(new Color(0x0c2461));

        btnCreateClass.setVisible(false);
        btnCreateSubject.setVisible(false);
        btnCreateSpecificallyMajor.setVisible(false);
        btnCreateClassroom.setVisible(false);
    }//GEN-LAST:event_btnClassmanagementActionPerformed

    private void btnSubjectManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubjectManagementActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "subjectCard");
        getContentPane().setBackground(new Color(0xd35400));

        btnCreateClass.setVisible(false);
        btnCreateSubject.setVisible(false);
        btnCreateSpecificallyMajor.setVisible(false);
        btnCreateClassroom.setVisible(false);
        initMajorSubject();
    }//GEN-LAST:event_btnSubjectManagementActionPerformed

    private void btnOtherManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOtherManagementActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "CreateCard");

        btnCreateClass.setVisible(true);
        btnCreateSubject.setVisible(true);
        btnCreateSpecificallyMajor.setVisible(true);
        btnCreateClassroom.setVisible(true);
        initMajorCreateClass();
    }//GEN-LAST:event_btnOtherManagementActionPerformed

    private void jlMajorClassValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlMajorClassValueChanged
        // TODO add your handling code here:
        modelSpecificallyMajorCC.removeAllElements();
        initSpecificallyMajorClass();

    }//GEN-LAST:event_jlMajorClassValueChanged

    private void jlSpecificallyMajorClassValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSpecificallyMajorClassValueChanged
        // TODO add your handling code here:
        initClass();
    }//GEN-LAST:event_jlSpecificallyMajorClassValueChanged

    private void jlClassClassValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlClassClassValueChanged
        // TODO add your handling code here:
        displayInfoClass();
    }//GEN-LAST:event_jlClassClassValueChanged

    private void btnUpdateClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateClassActionPerformed
        // TODO add your handling code here:
        try {
            boolean isUpdate = service.updateClass(new Classs(mapClassClass.get(
                    jlClassClass.getSelectedValue()),
                    Integer.valueOf(txtBatch.getText()),
                    txtDescriptor.getText()));
            if (isUpdate) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Batch không hợp lệ");
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpdateClassActionPerformed

    private void jlMajorSubjectValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlMajorSubjectValueChanged
        // TODO add your handling code here:
        initSpecificallyMajorSubject();
    }//GEN-LAST:event_jlMajorSubjectValueChanged

    private void jlSpecificallyMajorSubjectValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSpecificallyMajorSubjectValueChanged
        // TODO add your handling code here:
        initSubject();
    }//GEN-LAST:event_jlSpecificallyMajorSubjectValueChanged

    private void jlSubjectValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSubjectValueChanged
        // TODO add your handling code here:
        displayInfoSubject();
    }//GEN-LAST:event_jlSubjectValueChanged

    private void jlTypeOfSubjectValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlTypeOfSubjectValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jlTypeOfSubjectValueChanged

    private void jlSpecificallyMajorCSValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSpecificallyMajorCSValueChanged
        // TODO add your handling code here:
        txtSpecificallyMajorCS.setText(jlSpecificallyMajorCS.getSelectedValue());
    }//GEN-LAST:event_jlSpecificallyMajorCSValueChanged

    private void jlMajorCSValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlMajorCSValueChanged
        // TODO add your handling code here:
        initSpecificallyMajorCS();
        txtMajorCS.setText(jlMajorCS.getSelectedValue());
    }//GEN-LAST:event_jlMajorCSValueChanged

    private void jlSpecificallyMajorCreateClassValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSpecificallyMajorCreateClassValueChanged
        // TODO add your handling code here:
        getSpecificallyMajorName();
    }//GEN-LAST:event_jlSpecificallyMajorCreateClassValueChanged

    private void jlMajorCreateClassValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlMajorCreateClassValueChanged
        // TODO add your handling code here:
        initSpecificallyMajorCC();
        txtMajorCC.setText(jlMajorCreateClass.getSelectedValue());
    }//GEN-LAST:event_jlMajorCreateClassValueChanged

    private void btnCreateClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateClassActionPerformed
        // TODO add your handling code here:
        cardLayout1.show(panelCreate, "createClass");

    }//GEN-LAST:event_btnCreateClassActionPerformed

    private void btnCreateSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateSubjectActionPerformed
        // TODO add your handling code here:
        cardLayout1.show(panelCreate, "createSubject");
        initMajorCreateSubject();
        initListSubjectHaveNotDone();
    }//GEN-LAST:event_btnCreateSubjectActionPerformed

    private void jlSubjectHaveNotDoneValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSubjectHaveNotDoneValueChanged
        // TODO add your handling code here:

        txtSubjectNameCS.setEnabled(false);
        txtSubjectCodeCS.setEnabled(false);
        txtFurloughCS.setEnabled(false);
        txtCreditCS.setEnabled(false);
        txtMajorCS.setEnabled(false); // fasle
        txtDescriptorCS.setEnabled(false);
        txtSpecificallyMajorCS.setEnabled(false); // false

        displaySubjectHaveNotDone();
        check = 1;
    }//GEN-LAST:event_jlSubjectHaveNotDoneValueChanged

    private void btnCSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCSActionPerformed
        // TODO add your handling code here:
        if (check != 1) {
            createSubject();
            initListSubjectHaveNotDone();
        } else {
            int a = JOptionPane.showConfirmDialog(this, "Bạn không thể tạo môn học chưa xác minh vui lòng xác minh hoặc hủy");
            if (a == 1) {
                check = 2;
                txtSubjectNameCS.setEnabled(true);
                txtSubjectCodeCS.setEnabled(true);
                txtFurloughCS.setEnabled(true);
                txtCreditCS.setEnabled(true);
                txtMajorCS.setEnabled(false); // fasle
                txtDescriptorCS.setEnabled(true);
                txtSpecificallyMajorCS.setEnabled(false); // false
            }
        }

    }//GEN-LAST:event_btnCSActionPerformed

    private void btnUpdateSpecificallyMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSpecificallyMajorActionPerformed
        // TODO add your handling code here:
        updateSubjectHaveNotDone();
    }//GEN-LAST:event_btnUpdateSpecificallyMajorActionPerformed

    private void btnConfirmCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmCCActionPerformed
        // TODO add your handling code here:
        createClass();
    }//GEN-LAST:event_btnConfirmCCActionPerformed

    private void btnDeleteSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSubjectActionPerformed
        // TODO add your handling code here:
        deleteSubject();
    }//GEN-LAST:event_btnDeleteSubjectActionPerformed

    private void btnDeleteClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClassActionPerformed
        // TODO add your handling code here:
        deleteClass();
    }//GEN-LAST:event_btnDeleteClassActionPerformed

    private void jlMajorCreateSpecificallyMajorValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlMajorCreateSpecificallyMajorValueChanged
        // TODO add your handling code here:
        if (jlMajorCreateSpecificallyMajor.getSelectedIndices().length == 1) {
            txtMajorName.setText(jlMajorCreateSpecificallyMajor.getSelectedValue());
        }
    }//GEN-LAST:event_jlMajorCreateSpecificallyMajorValueChanged

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:
        createSpecificallyMajor();
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnCreateSpecificallyMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateSpecificallyMajorActionPerformed
        // TODO add your handling code here:
        cardLayout1.show(panelCreate, "createSpecificallyMajor");
        initMajorCreateSpecificallyMajor();
    }//GEN-LAST:event_btnCreateSpecificallyMajorActionPerformed

    private void btnSubmitCreateClassroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitCreateClassroomActionPerformed
        // TODO add your handling code here:
        createClassRoom();
    }//GEN-LAST:event_btnSubmitCreateClassroomActionPerformed

    private void jlBuildingValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlBuildingValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jlBuildingValueChanged

    private void btnCreateClassroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateClassroomActionPerformed
        // TODO add your handling code here:
        cardLayout1.show(panelCreate, "createClassroom");
        initBuilding();
    }//GEN-LAST:event_btnCreateClassroomActionPerformed

    private void jlMajorForSpecificallyMajorValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlMajorForSpecificallyMajorValueChanged
        // TODO add your handling code here:
        initSM();
    }//GEN-LAST:event_jlMajorForSpecificallyMajorValueChanged

    private void btnDeleteSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteSMActionPerformed
        // TODO add your handling code here:
        deleteSM();
    }//GEN-LAST:event_btnDeleteSMActionPerformed

    private void jlSpecificallyMajorForMajorValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlSpecificallyMajorForMajorValueChanged
        // TODO add your handling code here:
        displaySM();
    }//GEN-LAST:event_jlSpecificallyMajorForMajorValueChanged

    private void btnSpecificallyMajorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSpecificallyMajorActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "specificallyMajor");
        getContentPane().setBackground(new Color(0x0c2461));

        btnCreateClass.setVisible(false);
        btnCreateSubject.setVisible(false);
        btnCreateSpecificallyMajor.setVisible(false);
        btnCreateClassroom.setVisible(false);
    }//GEN-LAST:event_btnSpecificallyMajorActionPerformed

    private void btnUpdateSMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateSMActionPerformed
        // TODO add your handling code here:
        updateSM();
    }//GEN-LAST:event_btnUpdateSMActionPerformed

    private void btnClassroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClassroomActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "Classroom");
        getContentPane().setBackground(new Color(0x0c2461));

        btnCreateClass.setVisible(false);
        btnCreateSubject.setVisible(false);
        btnCreateSpecificallyMajor.setVisible(false);
        btnCreateClassroom.setVisible(false);

        initBuildingForClass();
    }//GEN-LAST:event_btnClassroomActionPerformed

    private void jlBuildingNameValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlBuildingNameValueChanged
        // TODO add your handling code here:
        initClassroomByBuildingID();
    }//GEN-LAST:event_jlBuildingNameValueChanged

    private void btnUpdateClassroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateClassroomActionPerformed
        // TODO add your handling code here:
        int classroomID = mapClassForClass.get(jlRoom.getSelectedValue()).getClassroomID();
        updateClassroom(classroomID);
    }//GEN-LAST:event_btnUpdateClassroomActionPerformed

    private void btnDeleteClassroomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteClassroomActionPerformed
        // TODO add your handling code here:
        int classroomID = mapClassForClass.get(jlRoom.getSelectedValue()).getClassroomID();
        deleteClassroom(classroomID);
    }//GEN-LAST:event_btnDeleteClassroomActionPerformed

    private void jlRoomValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlRoomValueChanged
        // TODO add your handling code here:
        displayClassForClass();
    }//GEN-LAST:event_jlRoomValueChanged

    /**
     * @param args the command line arguments
     */
    private void designButton(JButton originButton, int bgHex, int fgHex, int t, int r, int b, int l) {
        originButton.setBackground(new Color(bgHex));
        originButton.setForeground(new Color(fgHex));
        originButton.setBorder(new EmptyBorder(t, l, b, r));
    }

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
//            java.util.logging.Logger.getLogger(Equipment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Equipment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Equipment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Equipment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Equipment dialog = new Equipment(new JDialog(), true);
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
    private javax.swing.JButton btnCS;
    private javax.swing.JButton btnClassmanagement;
    private javax.swing.JButton btnClassroom;
    private javax.swing.JButton btnConfirmCC;
    private javax.swing.JButton btnCreateClass;
    private javax.swing.JButton btnCreateClassroom;
    private javax.swing.JButton btnCreateSpecificallyMajor;
    private javax.swing.JButton btnCreateSubject;
    private javax.swing.JButton btnDeleteClass;
    private javax.swing.JButton btnDeleteClassroom;
    private javax.swing.JButton btnDeleteSM;
    private javax.swing.JButton btnDeleteSubject;
    private javax.swing.JButton btnOtherManagement;
    private javax.swing.JButton btnSpecificallyMajor;
    private javax.swing.JButton btnSubjectManagement;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JButton btnSubmitCreateClassroom;
    private javax.swing.JButton btnUpdateClass;
    private javax.swing.JButton btnUpdateClassroom;
    private javax.swing.JButton btnUpdateSM;
    private javax.swing.JButton btnUpdateSpecificallyMajor;
    private javax.swing.JButton btnUpdateSubject;
    private javax.swing.JComboBox<String> cboFloor;
    private javax.swing.JComboBox<String> cboRoom;
    private javax.swing.JComboBox<String> cboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JList<String> jlBuilding;
    private javax.swing.JList<String> jlBuildingName;
    private javax.swing.JList<String> jlClassClass;
    private javax.swing.JList<String> jlMajorCS;
    private javax.swing.JList<String> jlMajorClass;
    private javax.swing.JList<String> jlMajorCreateClass;
    private javax.swing.JList<String> jlMajorCreateSpecificallyMajor;
    private javax.swing.JList<String> jlMajorForSpecificallyMajor;
    private javax.swing.JList<String> jlMajorSubject;
    private javax.swing.JList<String> jlRoom;
    private javax.swing.JList<String> jlSpecificallyMajorCS;
    private javax.swing.JList<String> jlSpecificallyMajorClass;
    private javax.swing.JList<String> jlSpecificallyMajorCreateClass;
    private javax.swing.JList<String> jlSpecificallyMajorForMajor;
    private javax.swing.JList<String> jlSpecificallyMajorSubject;
    private javax.swing.JList<String> jlSubject;
    private javax.swing.JList<String> jlSubjectHaveNotDone;
    private javax.swing.JList<String> jlTypeOfSubject;
    private javax.swing.JLabel lblBatch;
    private javax.swing.JLabel lblBatchCC;
    private javax.swing.JLabel lblBuilding;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblClassInfo;
    private javax.swing.JLabel lblClassInfo1;
    private javax.swing.JLabel lblClassInfo2;
    private javax.swing.JLabel lblClassInfo3;
    private javax.swing.JLabel lblClassInfo4;
    private javax.swing.JLabel lblClassInfo5;
    private javax.swing.JLabel lblClassInfo6;
    private javax.swing.JLabel lblClassInfo7;
    private javax.swing.JLabel lblClasscode;
    private javax.swing.JLabel lblCredit;
    private javax.swing.JLabel lblCredit2;
    private javax.swing.JLabel lblDescriptor;
    private javax.swing.JLabel lblDescriptor1;
    private javax.swing.JLabel lblDescriptor2;
    private javax.swing.JLabel lblDescriptor3;
    private javax.swing.JLabel lblDescriptor4;
    private javax.swing.JLabel lblDescriptor5;
    private javax.swing.JLabel lblDescriptor6;
    private javax.swing.JLabel lblDescriptor7;
    private javax.swing.JLabel lblFurlough;
    private javax.swing.JLabel lblFurlough1;
    private javax.swing.JLabel lblFurlough2;
    private javax.swing.JLabel lblFurlough3;
    private javax.swing.JLabel lblLastModified;
    private javax.swing.JLabel lblLastModified1;
    private javax.swing.JLabel lblLastModified2;
    private javax.swing.JLabel lblLastModified3;
    private javax.swing.JLabel lblMajor;
    private javax.swing.JLabel lblMajor1;
    private javax.swing.JLabel lblMajor2;
    private javax.swing.JLabel lblMajor3;
    private javax.swing.JLabel lblMajor4;
    private javax.swing.JLabel lblMajor5;
    private javax.swing.JLabel lblMajor6;
    private javax.swing.JLabel lblMajorCS;
    private javax.swing.JLabel lblMajorClassInfo;
    private javax.swing.JLabel lblMajorName;
    private javax.swing.JLabel lblNumOfFloor;
    private javax.swing.JLabel lblSpecificallyMajor;
    private javax.swing.JLabel lblSpecificallyMajor1;
    private javax.swing.JLabel lblSpecificallyMajor2;
    private javax.swing.JLabel lblSpecificallyMajor3;
    private javax.swing.JLabel lblSpecificallyMajor4;
    private javax.swing.JLabel lblSpecificallyMajor6;
    private javax.swing.JLabel lblSpecificallyMajorClass;
    private javax.swing.JLabel lblSpecificallyMajorClassInfo;
    private javax.swing.JLabel lblSpecificallyMajorClassInfo1;
    private javax.swing.JLabel lblSpecificallyMajorClassInfo2;
    private javax.swing.JLabel lblSpecificallyMajorClassInfo3;
    private javax.swing.JLabel lblSpecificallyMajorCode;
    private javax.swing.JLabel lblSpecificallyMajorCode1;
    private javax.swing.JLabel lblSpecificallyMajorCreate;
    private javax.swing.JLabel lblSpecificallyMajorName;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblSubject2;
    private javax.swing.JLabel lblSubjectName;
    private javax.swing.JLabel lblSubjectName1;
    private javax.swing.JLabel lblSubjectName2;
    private javax.swing.JLabel lblSubjectName3;
    private javax.swing.JLabel lblSujectCode;
    private javax.swing.JLabel lblSujectCode1;
    private javax.swing.JLabel lblSujectCode2;
    private javax.swing.JPanel panelClass;
    private javax.swing.JPanel panelClassroom;
    private javax.swing.JPanel panelClassroomInfo;
    private javax.swing.JPanel panelCreate;
    private javax.swing.JPanel panelCreateClass;
    private javax.swing.JPanel panelCreateClassroom;
    private javax.swing.JPanel panelCreateSpecificallyMajor;
    private javax.swing.JPanel panelCreateSubject;
    private javax.swing.JPanel panelMajorSubject;
    private javax.swing.JPanel panelMajorSubject1;
    private javax.swing.JPanel panelMajorSubject2;
    private javax.swing.JPanel panelMajorSubject3;
    private javax.swing.JPanel panelSpecificallyMajor;
    private javax.swing.JPanel panelSpecificallyMajorSubject;
    private javax.swing.JPanel panelSpecificallyMajorSubject1;
    private javax.swing.JPanel panelSubject;
    private javax.swing.JPanel panelSubjectHaveNotDone;
    private javax.swing.JPanel panelSubjectInfo;
    private javax.swing.JPanel panelSubjectInfo1;
    private javax.swing.JPanel panelSubjectInfo2;
    private javax.swing.JPanel panelSubjectSubject1;
    private javax.swing.JPanel pnBatch;
    private javax.swing.JPanel pnClassCode;
    private javax.swing.JPanel pnFurlough;
    private javax.swing.JPanel pnFurlough1;
    private javax.swing.JPanel pnFurlough2;
    private javax.swing.JPanel pnFurlough3;
    private javax.swing.JPanel pnLastModified;
    private javax.swing.JPanel pnLastModified1;
    private javax.swing.JPanel pnLastModified10;
    private javax.swing.JPanel pnLastModified11;
    private javax.swing.JPanel pnLastModified12;
    private javax.swing.JPanel pnLastModified13;
    private javax.swing.JPanel pnLastModified2;
    private javax.swing.JPanel pnLastModified3;
    private javax.swing.JPanel pnLastModified4;
    private javax.swing.JPanel pnLastModified5;
    private javax.swing.JPanel pnLastModified6;
    private javax.swing.JPanel pnLastModified7;
    private javax.swing.JPanel pnLastModified8;
    private javax.swing.JPanel pnLastModified9;
    private javax.swing.JPanel pnMajor;
    private javax.swing.JPanel pnMajorName;
    private javax.swing.JPanel pnSpecificallyMajor;
    private javax.swing.JPanel pnSpecificallyMajor1;
    private javax.swing.JPanel pnSpecificallyMajor2;
    private javax.swing.JPanel pnSpecificallyMajor3;
    private javax.swing.JPanel pnSpecificallyMajor4;
    private javax.swing.JPanel pnSpecificallyMajor5;
    private javax.swing.JPanel pnSpecificallyMajor6;
    private javax.swing.JPanel pnSpecificallyMajorCode;
    private javax.swing.JPanel pnSpecificallyMajorCode1;
    private javax.swing.JPanel pnSpecificallyMajorName;
    private javax.swing.JPanel pnSpecificallyMajorName1;
    private javax.swing.JPanel pnSubject;
    private javax.swing.JPanel pnSubject1;
    private javax.swing.JPanel pnSubject2;
    private javax.swing.JPanel pnSubject3;
    private javax.swing.JPanel pnSubjectCode;
    private javax.swing.JPanel pnSubjectCode1;
    private javax.swing.JPanel pnSubjectCode2;
    private javax.swing.JTextField txtBatch;
    private javax.swing.JTextField txtBatchCC;
    private javax.swing.JTextField txtClassName;
    private javax.swing.JTextField txtClasscode;
    private javax.swing.JTextField txtCredit;
    private javax.swing.JTextField txtCreditCS;
    private javax.swing.JTextArea txtDescriptor;
    private javax.swing.JTextArea txtDescriptor1;
    private javax.swing.JTextArea txtDescriptorCC;
    private javax.swing.JTextArea txtDescriptorCS;
    private javax.swing.JTextArea txtDescriptorCSM;
    private javax.swing.JTextArea txtDescriptorCreateBuilding;
    private javax.swing.JTextArea txtDescriptorForClass;
    private javax.swing.JTextArea txtDescriptorForSM;
    private javax.swing.JTextField txtFurlough;
    private javax.swing.JTextField txtFurloughCS;
    private javax.swing.JTextField txtLastModified;
    private javax.swing.JTextField txtLastModified1;
    private javax.swing.JTextField txtLastModifiedForClass;
    private javax.swing.JTextField txtLastModifiedForSM;
    private javax.swing.JTextField txtMajorCC;
    private javax.swing.JTextField txtMajorCS;
    private javax.swing.JTextField txtMajorClassInfo;
    private javax.swing.JTextField txtMajorName;
    private javax.swing.JTextField txtMajorNameForSM;
    private javax.swing.JTextField txtSMCode;
    private javax.swing.JTextField txtSMName;
    private javax.swing.JTextField txtSpecificallyMajorCS;
    private javax.swing.JTextField txtSpecificallyMajorClassInfo;
    private javax.swing.JTextField txtSpecificallyMajorClassInfo1;
    private javax.swing.JTextField txtSpecificallyMajorClassInfoCC;
    private javax.swing.JTextField txtSpecificallyMajorCode;
    private javax.swing.JTextField txtSpecificallyMajorName;
    private javax.swing.JTextField txtSubjectCode;
    private javax.swing.JTextField txtSubjectCodeCS;
    private javax.swing.JTextField txtSubjectName;
    private javax.swing.JTextField txtSubjectNameCS;
    // End of variables declaration//GEN-END:variables

    private void initMajorClass() {
        mapMajorClass = service.getMajorMap();

        jlMajorClass.setModel(modelMajorClass);
        modelMajorClass.removeAllElements();
        for (Map.Entry<String, Integer> entry : mapMajorClass.entrySet()) {
            modelMajorClass.addElement(entry.getKey());
        }

    }

    private void initSpecificallyMajorClass() {
        if (jlMajorClass.getSelectedIndices().length == 1) {
            String majorValue = jlMajorClass.getSelectedValue();
            if (majorValue != null) {
                int majorID = mapMajorClass.get(majorValue);

                listSpecificallyMajor = service.getSpecificallyMajorByMajorID(majorID, false);
                mapSpecificallyMajor = new TreeMap<>();

                for (SpecificallyMajor o : listSpecificallyMajor) {
                    mapSpecificallyMajor.put(o.getSpecificallyMajorName() + " - " + o.getSpecificallyMajorCode(), o.getSpecificallyMajorID());
                }

                jlSpecificallyMajorClass.setModel(modelSpecificallyMajorClass);
                modelSpecificallyMajorClass.removeAllElements();
                for (Map.Entry<String, Integer> entry : mapSpecificallyMajor.entrySet()) {
                    modelSpecificallyMajorClass.addElement(entry.getKey());
                }

            }
        }
    }

    private void initClass() {
        if (jlSpecificallyMajorClass.getSelectedIndices().length == 1) {
            String value = jlSpecificallyMajorClass.getSelectedValue();

            int specificallyMajorID = mapSpecificallyMajor.get(value);

            listClassClass = service.getClassBySpecificallyMajorID(specificallyMajorID, false);

            mapClassClass = new TreeMap<>();
            for (Classs o : listClassClass) {
                mapClassClass.put(o.getClassCode(), o.getClassID());
            }
            jlClassClass.setModel(modelClassClass);
            modelClassClass.removeAllElements();
            for (Map.Entry<String, Integer> entry : mapClassClass.entrySet()) {
                modelClassClass.addElement(entry.getKey());
            }

        }
    }

    private void displayInfoClass() {
        if (jlClassClass.getSelectedIndices().length == 1) {
            String value = jlClassClass.getSelectedValue();
            if (value != null) {
                int classID = mapClassClass.get(value);
                Classs ob = null;
                for (Classs o : listClassClass) {
                    if (o.getClassID() == classID) {
                        ob = o;
                    }
                }

                if (ob != null) {
                    txtClasscode.setText(ob.getClassCode());
                    txtBatch.setText(ob.getBatch() + "");
                    txtMajorClassInfo.setText(service.getMajorNameByMajorID(ob.getMajorID()));
                    txtSpecificallyMajorClassInfo.setText(service.getSpecificallyMajorNameByID(ob.getSpecificallyMajorID()));

                    txtLastModified.setText(ob.getLastModified().toString());
                    txtDescriptor.setText(ob.getDescriptor());
                }

            }
        }
    }

    private void initMajorSubject() {
        mapMajorSubject = mapMajorClass;

        jlMajorSubject.setModel(modelMajorClass);
        modelMajorClass.removeAllElements();
        for (Map.Entry<String, Integer> entry : mapMajorClass.entrySet()) {
            modelMajorClass.addElement(entry.getKey());
        }
    }

    private void initSpecificallyMajorSubject() {
        if (jlMajorSubject.getSelectedIndices().length == 1) {
            String value = jlMajorSubject.getSelectedValue();
            if (value != null) {
                int majorID = mapMajorSubject.get(value);
                listSpecificallyMajorSubject = service.getSpecificallyMajorByMajorID(majorID, false);

                mapSpecificallyMajorSubject = new TreeMap<String, Integer>();
                for (SpecificallyMajor o : listSpecificallyMajorSubject) {
                    mapSpecificallyMajorSubject.put(o.getSpecificallyMajorName(), o.getSpecificallyMajorID());
                }

                modelSpecificallyMajorSubject.removeAllElements();
                jlSpecificallyMajorSubject.setModel(modelSpecificallyMajorSubject);
                for (Map.Entry<String, Integer> entry : mapSpecificallyMajorSubject.entrySet()) {
                    modelSpecificallyMajorSubject.addElement(entry.getKey());
                }
            }
        }
    }

    private void initSubject() {
        if (jlSpecificallyMajorSubject.getSelectedIndices().length == 1) {
            String value = jlSpecificallyMajorSubject.getSelectedValue();
            if (value != null) {
                int specificallyMajorID = mapSpecificallyMajorSubject.get(value);

                listSubject = service.getSubjectBySpecificallyMajorID(specificallyMajorID, false);
                if (listSubject != null) {
                    mapSubject = new TreeMap<>();
                    for (SubjectDetail o : listSubject) {
                        mapSubject.put(o.getSubjectName(), o.getSubjectDetailID());
                    }

                    jlSubject.setModel(modelSubject);
                    modelSubject.removeAllElements();
                    for (Map.Entry<String, Integer> entry : mapSubject.entrySet()) {
                        modelSubject.addElement(entry.getKey());
                    }
                }
            }
        }
    }

    private void displayInfoSubject() {
        if (jlSubject.getSelectedIndices().length == 1) {
            String value = jlSubject.getSelectedValue();
            if (value != null) {
                int subjectDetailID = mapSubject.get(value);
                SubjectDetail ob = null;
                for (SubjectDetail o : listSubject) {
                    if (o.getSubjectDetailID() == subjectDetailID) {
                        ob = o;
                    }
                }

                display(ob);
            }
        }
    }

    private void display(SubjectDetail o) {
        if (o != null) {
            txtSubjectCode.setText(o.getSubjectCode());
            txtSubjectName.setText(o.getSubjectName());
            txtSpecificallyMajorClassInfo1.setText(jlSpecificallyMajorSubject.getSelectedValue());
            txtCredit.setText(o.getCredit() + "");
            txtFurlough.setText(o.getFurLough() + "");
            txtLastModified1.setText(o.getLastModified().toString());
            txtDescriptor.setText(o.getDescriptor());
        }
    }

    private void initMajorCreateClass() {
        mapMajorCreateClass = mapMajorClass;

        jlMajorCreateClass.setModel(modelMajorCC);
        modelMajorCC.removeAllElements();
        for (Map.Entry<String, Integer> entry : mapMajorCreateClass.entrySet()) {
            modelMajorCC.addElement(entry.getKey());
        }
    }

    private void initMajorCreateSpecificallyMajor() {
        mapMajorCreateSpecificallyMajor = mapMajorClass;
        jlMajorCreateSpecificallyMajor.setModel(modelMajorCSM);
        modelMajorCSM.removeAllElements();
        for (Map.Entry<String, Integer> entry : mapMajorCreateSpecificallyMajor.entrySet()) {
            modelMajorCSM.addElement(entry.getKey());
        }
    }

    private void initSpecificallyMajorCC() {
        if (jlMajorCreateClass.getSelectedIndices().length == 1) {
            String value = jlMajorCreateClass.getSelectedValue();
            if (value != null) {
                int majorID = mapMajorCreateClass.get(value);
                listSpecificallyMajorCC = service.getSpecificallyMajorByMajorID(majorID, false);

                mapSpecificallyMajorCC = new TreeMap<>();
                if (listSpecificallyMajorCC != null) {
                    for (SpecificallyMajor o : listSpecificallyMajorCC) {
                        mapSpecificallyMajorCC.put(o.getSpecificallyMajorName(), o.getSpecificallyMajorID());
                    }

                    jlSpecificallyMajorCreateClass.setModel(modelSpecificallyMajorCC);
                    modelSpecificallyMajorCC.removeAllElements();

                    for (Map.Entry<String, Integer> entry : mapSpecificallyMajorCC.entrySet()) {
                        modelSpecificallyMajorCC.addElement(entry.getKey());
                    }
                }
            }
        }
    }

    private void getSpecificallyMajorName() {
        txtSpecificallyMajorClassInfoCC.setText(jlSpecificallyMajorCreateClass.getSelectedValue());
    }

    private void initMajorCreateSubject() {
        mapMajorCreateSubject = mapMajorClass;

        jlMajorCS.setModel(modelMajorCS);
        modelMajorCS.removeAllElements();
        for (Map.Entry<String, Integer> entry : mapMajorCreateSubject.entrySet()) {
            modelMajorCS.addElement(entry.getKey());
        }
    }

    private void initSpecificallyMajorCS() {
        if (jlMajorCS.getSelectedIndices().length == 1) {
            String value = jlMajorCS.getSelectedValue();
            if (value != null) {
                int majorID = mapMajorCreateSubject.get(value);
                listSpecificallyMajorCS = service.getSpecificallyMajorByMajorID(majorID, false);

                mapSpecificallyMajorCS = new TreeMap<>();
                if (listSpecificallyMajorCS != null) {
                    for (SpecificallyMajor o : listSpecificallyMajorCS) {
                        mapSpecificallyMajorCS.put(o.getSpecificallyMajorName(), o.getSpecificallyMajorID());
                    }

                    jlSpecificallyMajorCS.setModel(modelSpecificallyMajorCS);
                    modelSpecificallyMajorCS.removeAllElements();

                    for (Map.Entry<String, Integer> entry : mapSpecificallyMajorCS.entrySet()) {
                        modelSpecificallyMajorCS.addElement(entry.getKey());
                    }
                }
            }
        }
    }

    private void createSubject() {
        int check = 1;
        try {
            Integer.valueOf(txtCreditCS.getText());
            Integer.valueOf(txtFurloughCS.getText());
        } catch (NumberFormatException e) {
            check++;
            System.out.println(e);
        }
        if (jlMajorCS.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đúng 1 giá trị ngành");
        } else if (check == 2) {
            JOptionPane.showMessageDialog(this, "Vui lòng viết số vào các trường hợp lệ");
        } else if (jlTypeOfSubject.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 giá trị cho trường loại môn");
        } else {
            SubjectDetail o = new SubjectDetail(
                    txtSubjectNameCS.getText(),
                    txtSubjectCodeCS.getText(),
                    Integer.valueOf(txtCreditCS.getText()),
                    Integer.valueOf(jlTypeOfSubject.getSelectedValue()),
                    Integer.valueOf(txtFurloughCS.getText()),
                    LocalDate.now(),
                    mapMajorCreateSubject.get(jlMajorCS.getSelectedValue()),
                    txtDescriptorCS.getText());

            if (service.createSubject(o)) {
                JOptionPane.showMessageDialog(this, "Tạo môn học thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Tạo môn học thất bại");
            }
        }
    }

    private void initListSubjectHaveNotDone() {
        listSubjectHaveNotDone = service.getSubjectHaveNotDone();

        if (listSubjectHaveNotDone != null) {
            mapSubjectHaveNotDone = new TreeMap<>();
            for (SubjectDetail o : listSubjectHaveNotDone) {
                mapSubjectHaveNotDone.put(o.getSubjectName() + " " + o.getSubjectDetailID(), o.getSubjectDetailID());
            }

            jlSubjectHaveNotDone.setModel(modelSubjectHaveNotDone);
            modelSubjectHaveNotDone.removeAllElements();
            for (Map.Entry<String, Integer> entry : mapSubjectHaveNotDone.entrySet()) {
                modelSubjectHaveNotDone.addElement(entry.getKey());
            }
        }

    }

    private void displaySubjectHaveNotDone() {
        if (jlSubjectHaveNotDone.getSelectedIndices().length == 1) {
            String value = jlSubjectHaveNotDone.getSelectedValue();
            if (value != null) {
                int subjectDetailID = mapSubjectHaveNotDone.get(value);

                subjectDetailHaveNotDone = null;
                for (SubjectDetail o : listSubjectHaveNotDone) {
                    if (o.getSubjectDetailID() == subjectDetailID) {
                        subjectDetailHaveNotDone = o;
                    }
                }
                displaySubjecHaveNotDone(subjectDetailHaveNotDone);
            }

        }
    }

    private void displaySubjecHaveNotDone(SubjectDetail ob) {
        if (ob != null) {
            txtSubjectNameCS.setText(ob.getSubjectName());
            txtSubjectCodeCS.setText(ob.getSubjectCode());
            txtFurloughCS.setText(ob.getFurLough() + "");
            txtCreditCS.setText(ob.getCredit() + "");
            txtMajorCS.setText(service.getMajorNameByMajorID(ob.getMajorID()));
            txtDescriptorCS.setText(ob.getDescriptor());

        }
    }

    private void updateSubjectHaveNotDone() {
        if (jlSpecificallyMajorCS.getSelectedIndices().length == 1) {
            int specificallyMajorID
                    = mapSpecificallyMajorCS.get(jlSpecificallyMajorCS.getSelectedValue());

            if (service.updateSubjectHaveNotDone(
                    subjectDetailHaveNotDone.getSubjectDetailID(),
                    specificallyMajorID)) {
                JOptionPane.showMessageDialog(this, "Xác minh thành công");
                check = 2;
                txtSubjectNameCS.setEnabled(true);
                txtSubjectCodeCS.setEnabled(true);
                txtFurloughCS.setEnabled(true);
                txtCreditCS.setEnabled(true);
                txtMajorCS.setEnabled(false); // fasle
                txtDescriptorCS.setEnabled(true);
                txtSpecificallyMajorCS.setEnabled(false); // false
                initListSubjectHaveNotDone();
            } else {
                JOptionPane.showMessageDialog(this, "Xác minh thất bại");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một giá trị ngành hẹp để xác minh");
        }
    }

    private void createClass() {
        if (validateCC()) {
            if (service.createClass(new Classs(
                    mapMajorCreateClass.get(jlMajorCreateClass.getSelectedValue()),
                    mapSpecificallyMajorCC.get(jlSpecificallyMajorCreateClass.getSelectedValue()),
                    Integer.valueOf(txtBatchCC.getText()),
                    LocalDate.now(),
                    txtDescriptorCC.getText()))) {
                JOptionPane.showMessageDialog(this, "Tạo lớp thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Tạo lớp thất bại");
            }
        }
    }

    private boolean validateCC() {
        if (jlMajorCreateClass.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 giá trị ngành duy nhất");
            return false;
        }
        if (jlSpecificallyMajorCreateClass.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 giá trị ngành hẹp duy nhất");
            return false;
        }

        try {
            Integer.valueOf(txtBatchCC.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá trị batch phải là số");
            return false;
        }

        return true;
    }

    private void deleteSubject() {
        if (validateSubject()) {
            String value = jlSubject.getSelectedValue();
            if (value != null) {
                int subjectDetailID = mapSubject.get(value);
                SubjectDetail ob = null;
                for (SubjectDetail o : listSubject) {
                    if (o.getSubjectDetailID() == subjectDetailID) {
                        ob = o;
                    }
                }
                if (service.deleteSubject(ob.getSubjectID(), subjectDetailID)) {
                    JOptionPane.showMessageDialog(this, "Xóa môn học thành công");
                    initSubject();
                    clearFormSubject();
                } else {
                    JOptionPane.showMessageDialog(this, "Xóa môn học thất bại");
                }
            }
        }

    }

    private void clearFormSubject() {
        txtSubjectName.setText("");
        txtFurlough.setText("");
        txtSubjectCode.setText("");
        txtSpecificallyMajorClassInfo1.setText("");
        txtCredit.setText("");
        txtDescriptor1.setText("");
        txtLastModified1.setText("");

    }

    private boolean validateSubject() {
        if (jlMajorSubject.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một giá trị ngành");
            return false;
        }
        if (jlSpecificallyMajorSubject.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một giá trị ngành hẹp");
            return false;
        }
        if (jlSubject.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một giá trị môn học");
            return false;
        }
        return true;
    }

    private void deleteClass() {
        if (validateClass()) {
            int classID = mapClassClass.get(jlClassClass.getSelectedValue());
            if (service.deleteClass(classID)) {
                JOptionPane.showMessageDialog(this, "Xóa lớp học thành công");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa lớp học thất bại");
            }
        }
    }

    private boolean validateClass() {
        if (jlMajorClass.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một giá trị ngành học");
            return false;
        }

        if (jlSpecificallyMajorClass.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một giá trị ngành hẹp");
            return false;
        }

        if (jlClassClass.getSelectedIndices().length != 1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một giá trị lớp học");
            return false;
        }

        return true;
    }

    private void createSpecificallyMajor() {
        if (jlMajorCreateSpecificallyMajor.getSelectedIndices().length == 1) {
            String value = jlMajorCreateSpecificallyMajor.getSelectedValue();
            int majorID = mapMajorCreateSpecificallyMajor.get(value);

            if (service.createSpecificallyMajor(majorID, new SpecificallyMajor(txtSpecificallyMajorName.getText(), txtSpecificallyMajorCode.getText(), txtDescriptorCSM.getText()))) {
                JOptionPane.showMessageDialog(this, "Tạo ngành thành công ");
            } else {
                JOptionPane.showMessageDialog(this, "Tạo ngành thất bại");
            }
        }
    }

    private void initBuilding() {
        listBuilding = service.getAllBuilding();
        if (listBuilding != null) {

            mapBuilding = new TreeMap<>();
            for (Building o : listBuilding) {
                mapBuilding.put(o.getBuidlingCode(), o.getBuildingID());
            }

            jlBuilding.setModel(modelListBuilding);
            modelListBuilding.removeAllElements();

            for (Map.Entry<String, Integer> entry : mapBuilding.entrySet()) {
                modelListBuilding.addElement(entry.getKey());
            }

        }
    }

    private void createClassRoom() {
        if (jlBuilding.getSelectedIndices().length == 1) {
            int buildingID = mapBuilding.get(jlBuilding.getSelectedValue());
            int numberOfFloor = cboFloor.getSelectedIndex() + 1;
            int numberOfRoom = cboRoom.getSelectedIndex() + 1;
            String descriptor = txtDescriptorCreateBuilding.getText();
            if (service.createClassroom(new Classroom(numberOfFloor, numberOfRoom, buildingID, descriptor))) {
                JOptionPane.showMessageDialog(this, "Tạo phòng học thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Tạo phòng học thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void initMajorSpecificallyMajor() {
        mapSpecificallyMajorBySM = mapMajorClass;

        jlMajorForSpecificallyMajor.setModel(modelM);
        modelM.removeAllElements();
        for (Map.Entry<String, Integer> entry : mapSpecificallyMajorBySM.entrySet()) {
            modelM.addElement(entry.getKey());
        }
    }

    private void initSM() {
        if (jlMajorForSpecificallyMajor.getSelectedIndices().length == 1) {
            String value = jlMajorForSpecificallyMajor.getSelectedValue();
            int majorID = mapSpecificallyMajorBySM.get(value);

            listSM = service.getSpecificallyMajorByMajorID(majorID, false);
            mapSM = new TreeMap<>();
            if (listSM != null) {
                for (SpecificallyMajor o : listSM) {
                    mapSM.put(o.getSpecificallyMajorName(), o);
                }
            }

            jlSpecificallyMajorForMajor.setModel(modelSM);
            modelSM.removeAllElements();
            for (Map.Entry<String, SpecificallyMajor> entry : mapSM.entrySet()) {
                modelSM.addElement(entry.getKey());
            }

        }
    }

    private void displaySM() {
        if (jlSpecificallyMajorForMajor.getSelectedIndices().length == 1) {
            sm = mapSM.get(jlSpecificallyMajorForMajor.getSelectedValue());
            txtSMName.setText(sm.getSpecificallyMajorName());
            txtSMCode.setText(sm.getSpecificallyMajorCode());
            txtMajorNameForSM.setText(jlMajorForSpecificallyMajor.getSelectedValue());
            txtLastModifiedForSM.setText(sm.getLastModified().toString());
            txtDescriptorForSM.setText(sm.getDescriptor());
        }
    }

    private void deleteSM() {
        if (validateSM()) {
            if (service.deleteSM(sm.getSpecificallyMajorID())) {
                JOptionPane.showMessageDialog(this, "Xóa ngành hẹp thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Xóa ngành hẹp thất bại", "Thông báo", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void updateSM() {
        if (validateSM()) {
            if (service.updateSM(new SpecificallyMajor(txtSMName.getText(), txtSMCode.getText(), txtDescriptorForSM.getText()))) {
                JOptionPane.showMessageDialog(this, "Cập nhật ngành hẹp thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật ngành hẹp thất bại", "Thông báo", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private boolean validateSM() {
        if (txtSMName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống tên ngành hẹp", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (txtSMCode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống mã ngành hẹp", "Thông báo", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void initBuildingForClass() {
        listBuildingForClass = service.getAllBuilding();
        if (listBuildingForClass != null) {

            mapBuildingForClass = new TreeMap<>();
            for (Building o : listBuildingForClass) {
                mapBuildingForClass.put(o.getBuidlingCode(), o.getBuildingID());
            }

            jlBuildingName.setModel(modelBuildingName);
            modelBuildingName.removeAllElements();

            for (Map.Entry<String, Integer> entry : mapBuildingForClass.entrySet()) {
                modelBuildingName.addElement(entry.getKey());
            }

        }
    }

    private void initClassroomByBuildingID() {
        if (jlBuildingName.getSelectedIndices().length == 1) {
            int buildingID = mapBuildingForClass.get(jlBuildingName.getSelectedValue());
            listClass = service.getClassroomsByBuildingID(buildingID, false);
            if (listClass != null) {
                mapClassForClass = new TreeMap<>();
                for (Classroom o : listClass) {
                    mapClassForClass.put(service.getClassroomCodeByClassroomID(o.getClassroomID()), o);
                }

                jlRoom.setModel(modelRoom);
                modelRoom.removeAllElements();
                for (Map.Entry<String, Classroom> entry : mapClassForClass.entrySet()) {
                    modelRoom.addElement(entry.getKey());
                }
            } else {
                modelRoom.removeAllElements();
            }
        }
    }

    private void displayClassForClass() {
        if (jlRoom.getSelectedIndices().length == 1) {
            Classroom classroomObj = mapClassForClass.get(jlRoom.getSelectedValue());

            txtClassName.setText(jlRoom.getSelectedValue());
            int status = classroomObj.getStatus();
            cboStatus.setSelectedIndex(status - 1);
            txtDescriptorForClass.setText(classroomObj.getDescriptor());
            txtLastModifiedForClass.setText(classroomObj.getLastModified().toString());
        }
    }

    private void updateClassroom(int classroomID) {
        System.out.println(classroomID);
        if (service.updateClassroom(new Classroom(classroomID, cboStatus.getSelectedIndex() + 1,
                txtDescriptorForClass.getText()))) {
            JOptionPane.showMessageDialog(this, "Cập nhật phòng học thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật phòng học thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteClassroom(int classroomID) {
        if(service.deleteClassroom(classroomID)) {
            JOptionPane.showMessageDialog(this, "Xóa phòng học thành công", "Thông báo", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Xóa phòng học thất bại", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

}
