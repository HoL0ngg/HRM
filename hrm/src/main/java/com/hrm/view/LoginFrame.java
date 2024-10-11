package com.hrm.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hrm.controller.LoginController;
import com.hrm.dao.UserDAO;
import com.hrm.model.Employee;

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
        setBounds(100, 100, 680, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 143, 82));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel ProStaffLabel = new JLabel("ProStaff");
        ProStaffLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 36));
        ProStaffLabel.setBounds(270, 35, 151, 59);
        contentPane.add(ProStaffLabel);

        RoundedPanel panel = new RoundedPanel(20);
        panel.setBounds(106, 106, 480, 300);
        panel.setLayout(null);

        JLabel DangNhapLabel = new JLabel("Dang nhap");
        DangNhapLabel.setForeground(new Color(0, 128, 255));
        DangNhapLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
        DangNhapLabel.setBounds(186, 10, 120, 27);
        panel.add(DangNhapLabel);

        JLabel tenDangNhapLabel = new JLabel("Ten dang nhap");
        tenDangNhapLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        tenDangNhapLabel.setBounds(30, 60, 126, 18);
        panel.add(tenDangNhapLabel);

        namefield = new RoundedTextField(12);
        namefield.setBounds(168, 55, 240, 30);
        namefield.setText("Nhap ten dang nhap ...");
        namefield.setForeground(Color.GRAY);

        JLabel nameErrorLabel = new JLabel("Ban phai nhap ten dang nhap");
        nameErrorLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
        nameErrorLabel.setForeground(Color.red);
        nameErrorLabel.setBounds(170, 86, 200, 18);
        nameErrorLabel.setVisible(false);
        panel.add(nameErrorLabel);

        namefield.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(namefield.getText()).equals("Nhap ten dang nhap ...")) {
                    namefield.setText(""); // Xóa placeholder khi focus
                    namefield.setForeground(Color.BLACK);
                }
                nameErrorLabel.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(namefield.getText()).isEmpty()) {
                    namefield.setText("Nhap ten dang nhap ..."); // Hiển thị lại placeholder khi không có dữ liệu
                    namefield.setForeground(Color.GRAY);
                    nameErrorLabel.setVisible(true);
                }
            }

        });
        panel.add(namefield);

        JLabel matKhauLabel = new JLabel("Mat khau");
        matKhauLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        matKhauLabel.setBounds(31, 130, 126, 18);
        panel.add(matKhauLabel);

        pwfield = new RoundedPasswordField(12);
        pwfield.setBounds(168, 123, 240, 30);
        pwfield.setText("Nhap mat khau ...");
        pwfield.setForeground(Color.GRAY);
        pwfield.setEchoChar((char) 0);

        JLabel pwErrorLabel = new JLabel("Ban phai nhap mat khau");
        pwErrorLabel.setBounds(170, 148, 140, 30);
        pwErrorLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
        pwErrorLabel.setForeground(Color.red);
        pwErrorLabel.setVisible(false);
        panel.add(pwErrorLabel);

        pwfield.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (String.valueOf(pwfield.getPassword()).equals("Nhap mat khau ...")) {
                    pwfield.setText(""); // Xóa placeholder khi focus
                    pwfield.setForeground(Color.BLACK);
                    pwfield.setEchoChar('*'); // Hiển thị ký tự ẩn khi nhập mật khẩu
                }
                pwErrorLabel.setVisible(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(pwfield.getPassword()).isEmpty()) {
                    pwfield.setText("Nhap mat khau ..."); // Hiển thị lại placeholder khi không có dữ liệu
                    pwfield.setForeground(Color.GRAY);
                    pwfield.setEchoChar((char) 0); // Hiển thị văn bản bình thường cho placeholder
                    pwErrorLabel.setVisible(true);
                }
            }

        });
        panel.add(pwfield);

        LoginController act = new LoginController(this);

        JLabel lblQuenMatKhau = new JLabel("Quen mat khau ?");
        lblQuenMatKhau.setForeground(new Color(0, 128, 255));
        lblQuenMatKhau.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        lblQuenMatKhau.setBounds(295, 175, 120, 20);
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
        panel.add(lblQuenMatKhau);

        RoundedButton btnLogin = new RoundedButton("Dang nhap", 12);
        btnLogin.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
        btnLogin.setBackground(new Color(245, 143, 82));
        btnLogin.setBounds(165, 210, 150, 50);
        panel.add(btnLogin);
        btnLogin.addActionListener(act);

        JLabel lblBanCoTai = new JLabel("Ban co tai khoan chua ?");
        lblBanCoTai.setBounds(270, 460, 150, 20);
        lblBanCoTai.setForeground(Color.gray);
        lblBanCoTai.setFont(new Font("Segoe UI Light", Font.PLAIN, 15));
        contentPane.add(lblBanCoTai);

        JLabel lblTaoTaiKhoan = new JLabel("Dang ky ngay");
        lblTaoTaiKhoan.setBounds(290, 480, 120, 20);
        lblTaoTaiKhoan.setForeground(new Color(0, 128, 255));
        lblTaoTaiKhoan.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
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
        contentPane.add(panel);
        contentPane.add(lblTaoTaiKhoan);

        // Tranh focus vao JTextfield tu ban dau
        JPanel emptyJPanel = new JPanel();
        emptyJPanel.setBounds(0, 0, 0, 0);
        contentPane.add(emptyJPanel);
        setVisible(true);
        emptyJPanel.requestFocusInWindow();
    }

    public Employee DangNhap() {
        String user = namefield.getText();
        String password = String.valueOf(pwfield.getPassword());

        return UserDAO.getInstance().DangNhap(user, password);
    }
}