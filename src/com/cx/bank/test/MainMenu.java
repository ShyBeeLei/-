
package com.cx.bank.test;

import com.cx.bank.manager.ManagerImpl;
import com.cx.bank.util.AccountOverDrawnException;
import com.cx.bank.util.InvalidDepositException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ClassName MainMenu
 * @Description 主界面
 * @Author Bruce Xu
 * @Date 2021/7/23 22:25
 * @Version 1.9
 */
public class MainMenu extends JFrame implements ActionListener {
    /**
     * 业务层接口
     */
    ManagerImpl manager = ManagerImpl.getInstance();
    /**
     * 用户名
     */
    String userName;
    /**
     * 字体设置
     */
    Font fo = new Font("宋体", Font.BOLD, 20);
    /**
     * 按钮
     */
    JButton inquiry, deposit, withdrawals, transfer, exit;
    /**
     * 标签
     */
    JLabel jLabel1, jLabel2;

    MainMenu() {
        /*
        参数设置
         */
        super("银行管理系统");
        this.setSize(630, 290);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);

        /*
        新建按钮对象
         */
        inquiry = new JButton("查询");
        inquiry.setFont(fo);
        deposit = new JButton("存款");
        deposit.setFont(fo);
        withdrawals = new JButton("取款");
        withdrawals.setFont(fo);
        transfer = new JButton("转账");
        transfer.setFont(fo);
        exit = new JButton("退出");
        exit.setFont(fo);

        /*
        新建标签对象
         */
        jLabel1 = new JLabel("枝江银行欢迎您！");
        jLabel1.setFont(fo);
        jLabel2 = new JLabel("请选择服务");
        jLabel2.setFont(fo);

        /*
        给按钮添加监听器
         */
        inquiry.addActionListener(this);
        deposit.addActionListener(this);
        withdrawals.addActionListener(this);
        transfer.addActionListener(this);
        exit.addActionListener(this);


        /*
        设置按钮的位置和大小
         */
        inquiry.setBounds(35, 180, 90, 50);
        deposit.setBounds(155, 180, 90, 50);
        withdrawals.setBounds(270, 180, 90, 50);
        transfer.setBounds(385, 180, 90, 50);
        exit.setBounds(505, 180, 90, 50);

        /*
        设置标签的位置和大小
         */
        jLabel1.setBounds(230, 0, 185, 85);
        jLabel2.setBounds(260, 95, 130, 60);

        this.add(inquiry);
        this.add(deposit);
        this.add(withdrawals);
        this.add(transfer);
        this.add(exit);
        this.add(jLabel1);
        this.add(jLabel2);
    }


    /**
     * 监听器中事件触发方法
     *
     * @param e 事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (inquiry.equals(e.getSource())) {
            inquiry();
        }
        if (deposit.equals(e.getSource())) {
            deposit();
        }
        if (withdrawals.equals(e.getSource())) {
            withdrawals();
        }
        if (transfer.equals(e.getSource())) {
            transfer();
        }
        if (exit.equals(e.getSource())) {
            manager.exitSystem();
        }
    }


    /**
     * 查询余额面板
     */
    private void inquiry() {
        /*
        设置默认参数并创建各个组件
         */
        JDialog inquiry = new JDialog(this, "查询余额");
        inquiry.setSize(400, 305);
        inquiry.setLocationRelativeTo(this);
        inquiry.setResizable(false);
        JLabel passwordLabel = new JLabel();
        JButton inquiryButton = new JButton();
        JPasswordField passwordField1 = new JPasswordField();
        JTextField showMoneyField = new JTextField();
        inquiry.setLayout(null);
        inquiry.setVisible(true);

        /*
        密码模块
         */
        passwordLabel.setText("请输入密码：");
        passwordLabel.setFont(new Font("宋体", Font.PLAIN, 13));
        inquiry.add(passwordLabel);
        passwordLabel.setBounds(55, 55, 80, 25);

        /*
        查询按钮及其监听器
         */
        inquiryButton.setText("查询");
        inquiry.add(inquiryButton);
        inquiryButton.setBounds(135, 110, 130, 43);
        inquiryButton.addActionListener(e -> {
            if (manager.login(userName, String.valueOf(passwordField1.getPassword())) != null) {
                showMoneyField.setText("您的余额为：" + manager.inquiry() + "元。");
            } else {
                JOptionPane.showMessageDialog(null, "密码错误！请重试");
                passwordField1.setText("");
            }
        });
        inquiry.add(passwordField1);
        passwordField1.setBounds(155, 55, 200, 26);

        /*
        余额展示区
         */
        inquiry.add(showMoneyField);
        showMoneyField.setBounds(45, 175, 315, 65);
        showMoneyField.setBackground(Color.WHITE);
        showMoneyField.setEditable(false);
    }


    /**
     * 存款面板
     */
    private void deposit() {
        /*
        设置默认参数并新建各个组件
         */
        JDialog deposit = new JDialog(this, "存款");
        deposit.setLayout(null);
        deposit.setSize(400, 255);
        deposit.setLocationRelativeTo(this);
        deposit.setResizable(false);
        JLabel moneyLabel = new JLabel();
        JTextField moneyField = new JTextField();
        JButton summit = new JButton();
        deposit.setVisible(true);

        /*
        金额模块
         */
        moneyLabel.setText("请输入金额：");
        deposit.add(moneyLabel);
        moneyLabel.setBounds(40, 45, 125, 50);
        deposit.add(moneyField);
        moneyField.setBounds(170, 55, 205, 30);

        /*
        提交按钮及其监听器
         */
        summit.setText("提交");
        summit.addActionListener(e -> {
            try {
                if ("".equals(moneyField.getText())) {
                    throw new InvalidDepositException("请输入正确的金额！");
                } else {
                    manager.deposit(Double.parseDouble(moneyField.getText()));
                    JOptionPane.showMessageDialog(null, "存款成功！");
                    deposit.dispose();
                }
            } catch (InvalidDepositException invalidDepositException) {
                JOptionPane.showMessageDialog(null, invalidDepositException.getMessage());
            }
        });
        deposit.add(summit);
        summit.setBounds(135, 135, 125, 55);
    }

    /**
     * 取款面板
     */
    private void withdrawals() {
        /*
        设置默认参数以及新建各个组件
         */
        JDialog withdrawals = new JDialog(this, "取款");
        withdrawals.setLayout(null);
        withdrawals.setSize(400, 300);
        withdrawals.setLocationRelativeTo(this);
        withdrawals.setResizable(false);
        withdrawals.setVisible(true);
        JLabel passwordLabel = new JLabel();
        JPasswordField passwordField = new JPasswordField();
        JButton summit = new JButton();
        JLabel moneyLabel = new JLabel();
        JTextField moneyField = new JTextField();


        /*
        密码模块
         */
        passwordLabel.setText("请输入密码：");
        withdrawals.add(passwordLabel);
        passwordLabel.setBounds(50, 45, 125, 50);
        withdrawals.add(passwordField);
        passwordField.setBounds(170, 55, 205, 30);

        /*
        提交按钮及其监听器
         */
        summit.setText("提交");
        summit.addActionListener(e -> {
            String password = String.valueOf(passwordField.getPassword());
            try {
                if (manager.login(userName, password) != null) {
                    manager.withdrawals(Double.parseDouble(moneyField.getText()));
                    JOptionPane.showMessageDialog(null, "取款成功！");
                    withdrawals.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "账号密码错误！");
                }
            } catch (AccountOverDrawnException | InvalidDepositException accountOverDrawnException) {
                JOptionPane.showMessageDialog(null, accountOverDrawnException.getMessage());
            }
        });
        withdrawals.add(summit);
        summit.setBounds(120, 175, 125, 55);

        /*
        取款金额模块
         */
        moneyLabel.setText("请输入取款金额：");
        withdrawals.add(moneyLabel);
        moneyLabel.setBounds(25, 105, 125, 50);
        withdrawals.add(moneyField);
        moneyField.setBounds(170, 120, 205, 30);
    }

    /**
     * 转账面板
     */
    private void transfer() {
        /*
        设置默认参数并新建用到的组件
         */
        JDialog transfer = new JDialog(this, "转账");
        transfer.setLayout(null);
        transfer.setSize(400, 285);
        transfer.setLocationRelativeTo(this);
        transfer.setResizable(false);
        transfer.setVisible(true);
        JLabel nameLabel = new JLabel();
        JTextField nameField = new JTextField();
        JButton summit = new JButton();
        JLabel moneyLabel = new JLabel();
        JTextField moneyField = new JTextField();
        JLabel passwordLabel = new JLabel();
        JPasswordField passwordField = new JPasswordField();
        JButton reset = new JButton();

        /*
        收款人模块
         */
        nameLabel.setText("请输入收款人：");
        transfer.add(nameLabel);
        nameLabel.setBounds(40, 20, 125, 50);
        transfer.add(nameField);
        nameField.setBounds(170, 30, 205, 30);

        /*
        提交按钮以及监听器
         */
        summit.setText("提交");
        summit.addActionListener(e -> {
            String password = String.valueOf(passwordField.getPassword());
            String targetName = nameField.getText();
            try {
                if (manager.login(userName, password) != null) {
                    if (manager.verifyUserName(targetName)) {
                        manager.transfer(targetName, Double.parseDouble(moneyField.getText()));
                        JOptionPane.showMessageDialog(null, "转账成功！");
                        transfer.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "收款人不存在！");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误！");
                }
            } catch (AccountOverDrawnException | InvalidDepositException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
            }
        });
        transfer.add(summit);
        summit.setBounds(50, 175, 125, 55);

        /*
        金额模块
         */
        moneyLabel.setText("请输入转账金额：");
        transfer.add(moneyLabel);
        moneyLabel.setBounds(25, 70, 125, 50);
        transfer.add(moneyField);
        moneyField.setBounds(170, 80, 205, 30);


        /*
        密码模块
         */
        passwordLabel.setText("请输入密码：");
        transfer.add(passwordLabel);
        passwordLabel.setBounds(50, 115, 95, 50);
        transfer.add(passwordField);
        passwordField.setBounds(170, 130, 205, 30);

        /*
        重置按钮以及监听器
         */
        reset.setText("重置");
        reset.addActionListener(e -> {
            passwordField.setText("");
            nameField.setText("");
            moneyField.setText("");
        });
        transfer.add(reset);
        reset.setBounds(230, 175, 125, 55);
    }
}


