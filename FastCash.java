/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5, b6, b7, b8;
    JTextField t1;
    String pin;

    FastCash(String pin) {
        this.pin = pin;
        setTitle("BANK OF GPAN");
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagement/icons/atm.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l3 = new JLabel(i3);
//        l3.setBounds(0, 0, 960, 1080);
//        add(l3);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagement/icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 70, 140, 100);
        add(l11);
        
        l2=new JLabel("BANK OF GPAN");
        l2.setForeground(Color.black);
        l2.setFont(new Font("System", Font.BOLD,50));
        
        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setForeground(Color.black);
        l1.setFont(new Font("System", Font.BOLD,38));

        b1 = new JButton("Rs 100");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("System", Font.BOLD,16));
        
        b2 = new JButton("Rs 500");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("System", Font.BOLD,16));
        
        b3 = new JButton("Rs 1000");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setFont(new Font("System", Font.BOLD,16));
        
        b4 = new JButton("Rs 2000");
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        b4.setFont(new Font("System", Font.BOLD,16));
        
        b5 = new JButton("Rs 5000");
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        b5.setFont(new Font("System", Font.BOLD,16));
        
        b6 = new JButton("Rs 10000");
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        b6.setFont(new Font("System", Font.BOLD,16));
        
        b7 = new JButton("BACK");
        b7.setBackground(Color.BLACK);
        b7.setForeground(Color.WHITE);
        b7.setFont(new Font("System", Font.BOLD,16));

        setLayout(null);
        
        l2.setBounds(200,100,800,50);
        add(l2);
        
        l1.setBounds(120,200,700,50);
        add(l1);

        b1.setBounds(180,300,200,50);
        add(b1);

        b2.setBounds(450,300,200,50);
        add(b2);

        b3.setBounds(180,400,200,50);
        add(b3);

        b4.setBounds(450,400,200,50);
        add(b4);

        b5.setBounds(180,500,200,50);
        add(b5);

        b6.setBounds(450,500,200,50);
        add(b6);

        b7.setBounds(250,600,300,50);
        add(b7);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        setSize(850,800);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = ((JButton)ae.getSource()).getText().substring(3); //k
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '"+pin+"'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("mode").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            } String num = "17";
            if (ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "Insuffient Balance");
                return;
            }

            if (ae.getSource() == b7) {
                this.setVisible(false);
                new Transactions(pin).setVisible(true);
            }else{
                Date date = new Date();
                c.s.executeUpdate("insert into bank values('"+pin+"', '"+date+"', 'Withdrawl', '"+amount+"')");
                JOptionPane.showMessageDialog(null, "Rs. "+amount+" Debited Successfully");
                    
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}
