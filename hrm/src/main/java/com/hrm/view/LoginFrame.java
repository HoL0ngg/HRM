package com.hrm.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hrm.controller.ActionController;
import com.hrm.dao.UserDAO;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {

    private JPanel contentPane;
    private RoundedTextField namefield;
    private RoundedPasswordField pwfield;

    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 450);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 143, 82));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel ProStaffLabel = new JLabel("ProStaff");
        ProStaffLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 36));
        ProStaffLabel.setBounds(186, 35, 151, 59);
        contentPane.add(ProStaffLabel);

        RoundedPanel panel = new RoundedPanel(20);
        panel.setBounds(101, 104, 343, 215);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel DangNhapLabel = new JLabel("Dang nhap");
        DangNhapLabel.setForeground(new Color(0, 128, 255));
        DangNhapLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        DangNhapLabel.setBounds(118, 10, 91, 27);
        panel.add(DangNhapLabel);

        JLabel tenDangNhapLabel = new JLabel("Ten dang nhap");
        tenDangNhapLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        tenDangNhapLabel.setBounds(10, 51, 126, 18);
        panel.add(tenDangNhapLabel);

        namefield = new RoundedTextField(12);
        namefield.setBounds(146, 47, 164, 27);
        namefield.setColumns(10);
        namefield.setText("Nhap ten dang nhap ...");
        namefield.setForeground(Color.GRAY);
        namefield.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(namefield.getText()).equals("Nhap ten dang nhap ...")) {
                    namefield.setText(""); // Xóa placeholder khi focus
                    namefield.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(namefield.getText()).isEmpty()) {
                    namefield.setText("Nhap ten dang nhap ..."); // Hiển thị lại placeholder khi không có dữ liệu
                    namefield.setForeground(Color.GRAY);
                }
            }

        });
        panel.add(namefield);

        JLabel matKhauLabel = new JLabel("Mat khau");
        matKhauLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        matKhauLabel.setBounds(10, 98, 126, 18);
        panel.add(matKhauLabel);

        pwfield = new RoundedPasswordField(12);
        pwfield.setColumns(10);
        pwfield.setBounds(146, 98, 164, 27);
        pwfield.setText("Nhap mat khau ...");
        pwfield.setForeground(Color.GRAY);
        pwfield.setEchoChar((char) 0);
        pwfield.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(pwfield.getPassword()).equals("Nhap mat khau ...")) {
                    pwfield.setText(""); // Xóa placeholder khi focus
                    pwfield.setForeground(Color.BLACK);
                    pwfield.setEchoChar('*'); // Hiển thị ký tự ẩn khi nhập mật khẩu
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(pwfield.getPassword()).isEmpty()) {
                    pwfield.setText("Nhap mat khau ..."); // Hiển thị lại placeholder khi không có dữ liệu
                    pwfield.setForeground(Color.GRAY);
                    pwfield.setEchoChar((char) 0); // Hiển thị văn bản bình thường cho placeholder
                }
            }

        });
        panel.add(pwfield);

        ActionController act = new ActionController(this);

        JLabel lblQuenMatKhau = new JLabel("Quen mat khau ?");
        lblQuenMatKhau.setForeground(new Color(0, 128, 255));
        lblQuenMatKhau.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        lblQuenMatKhau.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Ban da nhan nut quen mat khau", "Thong bao",
                        JOptionPane.PLAIN_MESSAGE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblQuenMatKhau.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblQuenMatKhau.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        });
        lblQuenMatKhau.setBounds(217, 132, 93, 18);
        panel.add(lblQuenMatKhau);

        RoundedButton btnLogin = new RoundedButton("Dang nhap", 12);
        btnLogin.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
        btnLogin.setBackground(new Color(245, 143, 82));
        btnLogin.setBounds(118, 154, 105, 40);
        panel.add(btnLogin);
        btnLogin.addActionListener(act);

        JLabel lblBanCoTai = new JLabel("Ban co tai khoan chua ?");
        lblBanCoTai.setForeground(new Color(128, 128, 128));
        lblBanCoTai.setFont(new Font("Segoe UI Light", Font.PLAIN, 14));
        lblBanCoTai.setBounds(197, 370, 151, 18);
        contentPane.add(lblBanCoTai);

        JLabel lblTaoTaiKhoan = new JLabel("Dang ky ngay");
        lblTaoTaiKhoan.setForeground(new Color(0, 128, 255));
        lblTaoTaiKhoan.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        lblTaoTaiKhoan.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Ban da nhat nut dang ky", "Thong bao",
                        JOptionPane.PLAIN_MESSAGE);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                lblTaoTaiKhoan.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblTaoTaiKhoan.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

        });
        lblTaoTaiKhoan.setBounds(226, 385, 76, 18);
        contentPane.add(lblTaoTaiKhoan);

        setVisible(true);
    }

    public boolean DangNhap() {
        String user = namefield.getText();
        String password = String.valueOf(pwfield.getPassword());

        return UserDAO.getInstance().DangNhap(user, password);
    }
}