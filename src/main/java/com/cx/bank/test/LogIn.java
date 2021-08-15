
package com.cx.bank.test;

import com.cx.bank.manager.ManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @ClassName LogIn
 * @Description 登录页面生成
 * @Author Bruce Xu
 * @Date 2021/7/23 21:45
 * @Version 1.9
 */
public class LogIn extends JFrame {
    /**
     * 创造业务层接口
     */
    ManagerImpl manager = ManagerImpl.getInstance();
    /**
     * 设置字体
     */
    Font fo = new Font("宋体", Font.BOLD, 15);
    /**
     * 身份字符串，为0则是用户，1为管理员
     */
    String[] identity = {"USER", "ADMIN", "FROZEN"};

    LogIn() {
        super("登录");

        /*
        设置选项卡式布局
         */
        JTabbedPane jTabbedPane = new JTabbedPane();
        JComponent logInPanel = makeLogInPanel();
        jTabbedPane.add("登录", logInPanel);
        JComponent registerPanel = makeRegisterPanel();
        jTabbedPane.add("注册", registerPanel);
        JComponent adminLoginPanel = makeAdminLogInPanel();
        jTabbedPane.add("管理员登陆", adminLoginPanel);


        /*
          设置参数
         */
        this.setSize(350, 200);
        this.add(jTabbedPane);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new LogIn();
    }

    /**
     * 生成登录面板
     *
     * @return 登录面板
     */
    protected JComponent makeLogInPanel() {
        JPanel logInPanel = new JPanel();
        logInPanel.setLayout(null);


        /*
        用户名模块
         */
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setFont(fo);
        userLabel.setBounds(38, 20, 80, 25);
        logInPanel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        logInPanel.add(userText);

        /*
        密码模块
         */
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(fo);
        passwordLabel.setBounds(55, 60, 80, 25);
        logInPanel.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 60, 165, 25);
        logInPanel.add(passwordText);


        /*
        登录按钮
         */
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", Font.BOLD, 18));
        loginButton.setBounds(130, 110, 80, 25);
        logInPanel.add(loginButton);


        /*
        登录按钮监听器，进行登录操作
         */
        loginButton.addActionListener(e -> {
            if (!"".equals(userText.getText()) && !"".equals(String.valueOf(passwordText.getPassword()))) {
                String userName = userText.getText();
                String password = String.valueOf(passwordText.getPassword());
                if (identity[0].equals(manager.login(userName, password, false))) {
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    dispose();
                    new MainMenu();
                } else if (identity[2].equals(manager.login(userName, password, false))) {
                    JOptionPane.showMessageDialog(null, "账号被冻结！请咨询管理员以获得更多信息。");
                    passwordText.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误，请重试！");
                    passwordText.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "账号密码不能为空！");
            }
        });
        return logInPanel;
    }

    /**
     * 生成注册面板
     *
     * @return 注册面板
     */
    protected JComponent makeRegisterPanel() {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(null);


        /*
        用户名模块
         */
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setFont(fo);
        userLabel.setBounds(38, 20, 80, 25);
        registerPanel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        registerPanel.add(userText);

        /*
        密码模块
         */
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(fo);
        passwordLabel.setBounds(55, 50, 80, 25);
        registerPanel.add(passwordLabel);
        JPasswordField jPasswordField = new JPasswordField(20);
        jPasswordField.setBounds(100, 50, 165, 25);
        registerPanel.add(jPasswordField);


        /*
        设置用户名文本域失去焦点时检测用户名是否重复
         */
        jPasswordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (manager.verifyUserName(userText.getText())) {
                    JOptionPane.showMessageDialog(null, "用户名重复，请更换用户名！");
                    userText.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });


        /*
        确认密码模块
         */
        JLabel passwordConfirmLabel = new JLabel("确认密码:");
        passwordConfirmLabel.setFont(fo);
        passwordConfirmLabel.setBounds(23, 80, 80, 25);
        registerPanel.add(passwordConfirmLabel);
        JPasswordField passwordConfirmField = new JPasswordField(20);
        passwordConfirmField.setBounds(100, 80, 165, 25);
        registerPanel.add(passwordConfirmField);


        /*
        设置确认密码文本域失去焦点时检测两次密码是否一致
         */
        passwordConfirmField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordConfirmField.getPassword() != jPasswordField.getPassword()) {
                    JOptionPane.showMessageDialog(null, "两次密码输入不一致，请重新输入！");
                    passwordConfirmField.setText("");
                }
            }
        });

        // 注册按钮
        JButton loginButton = new JButton("注册");
        loginButton.setFont(new Font("宋体", Font.BOLD, 18));
        loginButton.setBounds(130, 110, 80, 25);
        registerPanel.add(loginButton);


        /*
        注册按钮监听器，进行注册
         */
        loginButton.addActionListener(e -> {
            String userName = userText.getText();
            String password = String.valueOf(jPasswordField.getPassword());
            if (manager.register(userName, password)) {
                JOptionPane.showMessageDialog(null, "注册成功！");
                dispose();
                new MainMenu();
            }
        });
        return registerPanel;
    }

    protected JComponent makeAdminLogInPanel() {
        JPanel adminLogInPanel = new JPanel();
        adminLogInPanel.setLayout(null);


        /*
        用户名模块
         */
        JLabel userLabel = new JLabel("用户名:");
        userLabel.setFont(fo);
        userLabel.setBounds(38, 20, 80, 25);
        adminLogInPanel.add(userLabel);
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        adminLogInPanel.add(userText);

        /*
        密码模块
         */
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setFont(fo);
        passwordLabel.setBounds(55, 60, 80, 25);
        adminLogInPanel.add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 60, 165, 25);
        adminLogInPanel.add(passwordText);


        /*
        登录按钮
         */
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("宋体", Font.BOLD, 18));
        loginButton.setBounds(130, 110, 80, 25);
        adminLogInPanel.add(loginButton);


        /*
        登录按钮监听器，进行登录操作
         */
        loginButton.addActionListener(e -> {
            if (!"".equals(userText.getText()) && !"".equals(String.valueOf(passwordText.getPassword()))) {
                String userName = userText.getText();
                String password = String.valueOf(passwordText.getPassword());
                if (identity[1].equals(manager.login(userName, password, true))) {
                    JOptionPane.showMessageDialog(null, "登录成功！");
                    dispose();
                    new AdminPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误，请重试！");
                    passwordText.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "账号密码不能为空！");
            }
        });
        return adminLogInPanel;
    }
}
