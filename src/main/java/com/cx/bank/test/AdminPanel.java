package com.cx.bank.test;

import com.cx.bank.manager.ManagerImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * @ClassName AdminPanel
 * @Description 管理员界面
 * @Author Bruce Xu
 * @Date 2021/8/1 16:57
 * @Version 1.0
 */
public class AdminPanel extends JFrame implements ActionListener {
    /**
     * 业务层接口
     */
    ManagerImpl manager = ManagerImpl.getInstance();
    /**
     * 搜索到的用户信息
     */
    HashMap<String, Object> selectedUser;
    /**
     * ID搜索栏
     */
    JTextField idField;
    /**
     * id搜索标签
     */
    JLabel idLabel;
    /**
     * 信息显示栏
     */
    JTextArea infoArea;
    /**
     * 按钮
     */
    JButton confirm, freeze, restore;

    AdminPanel() {
        /*
        初始化面板，新建各个组件
         */
        super("管理员界面");
        this.setSize(496, 405);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        idField = new JTextField();
        idLabel = new JLabel("请输入用户ID：");
        infoArea = new JTextArea();
        confirm = new JButton("搜索");
        freeze = new JButton("冻结");
        restore = new JButton("解冻");


        this.setLayout(null);
        this.add(idField);
        idField.setBounds(160, 70, 200, 25);

        infoArea.setEditable(false);
        infoArea.setBounds(60, 135, 370, 135);
        this.add(infoArea);

        this.add(idLabel);
        idLabel.setBounds(65, 70, 110, 25);


        this.add(confirm);
        confirm.setBounds(370, 70, 60, 25);
        confirm.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            selectedUser = manager.getInfo(id);
            infoArea.setText("用户名：" + selectedUser.get("username") + "\n用户状态：" + selectedUser.get("userFlag"));
        });

        this.add(freeze);
        freeze.setBounds(70, 300, 120, 45);

        this.add(restore);
        restore.setBounds(285, 300, 120, 45);

        freeze.addActionListener(this);
        restore.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(freeze) && "正常".equals(selectedUser.get("userFlag"))) {
            manager.changeStatus(Integer.parseInt(idField.getText()), 0);
            JOptionPane.showMessageDialog(null, "冻结成功！");

        } else if (e.getSource().equals(restore) && "冻结".equals(selectedUser.get("userFlag"))) {
            manager.changeStatus(Integer.parseInt(idField.getText()), 1);
            JOptionPane.showMessageDialog(null, "解冻成功！");
        } else {
            JOptionPane.showMessageDialog(null, "不能重复操作！");
        }
    }
}
