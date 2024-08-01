/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
  
    Login(){
        setTitle("BANK OF CO-2 GPAN");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagement/icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 70, 100, 100);
        add(l11);
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("bankmanagement/icons/login3.jpg"));
        Image i5 = i4.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l5 = new JLabel(i6);
        l5.setBounds(0, 0,850,800);
        add(l5);
        
        l1 = new JLabel("WELCOME TO GPAN BANK");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(180,100,550,50);
        l5.add(l1);
        
        l2 = new JLabel("CARD NO:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(125,200,375,50);
        l5.add(l2);
        
        tf1 = new JTextField(15);
        tf1.setBounds(300,200,300,50);
        tf1.setFont(new Font("Arial", Font.BOLD,24));
        l5.add(tf1);
        
        l3 = new JLabel("PIN :");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(125,300,375,50);
        l5.add(l3);
        
        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 24));
        pf2.setBounds(300,300,300,50);
        l5.add(pf2);
                
        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("CREATE NEW ACCOUNT");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        setLayout(null);
        
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setBounds(180,400,200,50);
        l5.add(b1);
        
        b2.setFont(new Font("Arial", Font.BOLD, 16));
        b2.setBounds(430,400,200,50);
        l5.add(b2);
        
        b3.setFont(new Font("Arial", Font.BOLD, 16));
        b3.setBounds(250,500,300,50);
        l5.add(b3);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.white);
        
        setSize(850,700);
        setLocation(300,0);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        try{        
            if(ae.getSource()==b1){
                Conn c1 = new Conn();
                String cardno  = tf1.getText();
                String pin  = pf2.getText();
                        String q  = "select * from login where cardno = '"+cardno+"' and pin = '"+pin+"'";

                        ResultSet rs = c1.s.executeQuery(q);
                        if(rs.next()){
                            setVisible(false);
                            new Transactions(pin).setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }
            }else if(ae.getSource()==b2){
                tf1.setText("");
                pf2.setText("");
            }else if(ae.getSource()==b3){
                setVisible(false);
                new Signup().setVisible(true);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error:"+e);
        }
    }
    public static void main(String[] args){
        new Login().setVisible(true);
    }

    
}