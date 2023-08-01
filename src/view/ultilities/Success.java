package view.ultilities;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Success extends JDialog {

    private JLabel msg;
    private JButton btnCancel;
    private JTextPane txtHelp;

    public Success(int status, String contentSuccess, String contentFail, String help) {
        setUndecorated(true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        getContentPane().setBackground(new Color(0xd1ccc0));
        
        msg = new JLabel("", JLabel.CENTER);
        msg.setFont(new Font("Consolas", Font.BOLD, 22));
        msg.setForeground(new Color(0xdcdde1));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(msg, gbc);
        
        txtHelp = new JTextPane();
        txtHelp.setForeground(new Color(0x575fcf));
        txtHelp.setPreferredSize(new Dimension(250, 50));
        txtHelp.setEditable(false);
        txtHelp.setBackground(new Color(0xd1ccc0));
        
        JScrollPane jsp = new JScrollPane(txtHelp);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 2;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        add(jsp, gbc);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setMargin(new Insets(5, 5, 5, 5));
        designButton(btnCancel, 0x192a56, 0xf5f6fa, 10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(btnCancel, gbc);
        
        
//        btnCancel.setOpaque(false);
//        btnCancel.setContentAreaFilled(false);
////        btnCancel.setBorderPainted(false);
//        btnCancel.setBorder(null);


        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        
        
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
        });
        
        if (status == 0) {
            msg.setText(contentFail);
            msg.setForeground(new Color(0xff3f34));
            txtHelp.setText(help);
        } else {
            msg.setText(contentSuccess);
            msg.setForeground(new Color(0x05c46b));
        }
        
        
        setVisible(true);

    }

    public Success(JFrame owner, int status, String msgSuccess, String msgFail, String help) {
        super(owner, true);
        setSize(400, 200);
        setLocationRelativeTo(owner);
        new Success(status, msgSuccess, msgFail, help);
    }

    public Success(JDialog owner, int status, String msgSuccess, String msgFail, String help) {
        super(owner, true);
        setSize(400, 200);
        setLocationRelativeTo(owner);
        new Success(status, msgSuccess, msgFail, help);
    }

    @Override
    protected JRootPane createRootPane() {
        JRootPane rootPane = new JRootPane();
        rootPane.setWindowDecorationStyle(JRootPane.NONE); // Loại bỏ thanh tiêu đề
        return rootPane;
    }

    private void designButton(JButton originButton, int bgHex, int fgHex, int t, int r, int b, int l) {
        originButton.setBackground(new Color(bgHex));
        originButton.setForeground(new Color(fgHex));
        originButton.setBorder(new EmptyBorder(t, l, b, r));
    }
    
}
