package com.hrm;

import com.hrm.view.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Đặt encoding toàn cục cho ứng dụng
        System.setProperty("file.encoding", "UTF-8");

        // In kiểm tra encoding hiện tại (đảm bảo là UTF-8)
        System.out.println("Default Charset: " + java.nio.charset.Charset.defaultCharset());

        // Khởi chạy ứng dụng
        new LoginFrame();
    }
}
