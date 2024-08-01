/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Pin extends JFrame implements ActionListener{
    
    JPasswordField t1,t2;
    JButton b1,b2;                               
    JLabel l1,l2,l3,l4;
    String pin;
    Pin(String pin){
        this.pin = pin;
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagement/icons/atm.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(1000, 1180, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel l4 = new JLabel(i3);
//        l4.setBounds(0, 0, 960, 1080);
//        add(l4);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bankmanagement/icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l11 = new JLabel(i3);
        l11.setBounds(70, 70, 140, 100);
        add(l11);
        
        l4=new JLabel("BANK OF GPAN");
        l4.setForeground(Color.black);
        l4.setFont(new Font("System", Font.BOLD,48));
        
        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 38));
        l1.setForeground(Color.black);
        
        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 24));
        l2.setForeground(Color.black);
        
        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 24));
        l3.setForeground(Color.black);
        
        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 24));
        
        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 24));
        
        b1 = new JButton("CHANGE");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("BACK");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);   
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        setLayout(null);
        
        l4.setBounds(230,100,700,50);
        add(l4);
        
        l1.setBounds(180,200,500,50);
        add(l1);
        
        l2.setBounds(180,300,300,40);
        add(l2);
        
        l3.setBounds(180,400,300,40);
        add(l3);
        
        t1.setBounds(400,300,300,40);
        add(t1);
        
        t2.setBounds(400,400,300,40);
        add(t2);
        
        b1.setBounds(200,550,150,50);
        add(b1);
        
        b2.setBounds(480,550,150,50);
        add(b2);
        
        getContentPane().setBackground(Color.WHITE);
        setSize(850,800);
        setLocation(300,0);
        setUndecorated(true);
        setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent ae){
        try{        
            String npin = t1.getText();
            String rpin = t2.getText();
            
            if(!npin.equals(rpin)){
                JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                return;
            }
            
            if(ae.getSource()==b1){
                if (t1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                }
                if (t2.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                }
                
                Conn c1 = new Conn();
                String q1 = "update bank set pin = '"+rpin+"' where pin = '"+pin+"' ";
                String q2 = "update login set pin = '"+rpin+"' where pin = '"+pin+"' ";
                String q3 = "update signup3 set pin = '"+rpin+"' where pin = '"+pin+"' ";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);
            
            }else if(ae.getSource()==b2){
                new Transactions(pin).setVisible(true);
                setVisible(false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Pin("").setVisible(true);
    }
}