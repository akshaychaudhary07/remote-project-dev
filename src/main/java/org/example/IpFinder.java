package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;


public class IpFinder extends JFrame implements ActionListener{

    JButton b;
    JTextField tf;
    JLabel l;
    IpFinder(){
        l = new JLabel("Enter URL:");
        l.setBounds(100,100,100,20);
        tf  = new JTextField();
        tf.setBounds(200,100,100,20);

        b  = new JButton("Get IP");
        b.setBounds(100,150,100,20);

        add(l);
        add(tf);
        add(b);
        b.addActionListener(this);
        setSize(400,400);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String url = tf.getText();
        System.out.println(url);

        InetAddress ia;
        try {
            ia = InetAddress.getByName(url);
            String ip=ia.getHostAddress();
            JOptionPane.showMessageDialog(this,"The IP is: "+ ip);
        } catch (UnknownHostException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(ia.getHostAddress());

    }

    public static void main(String[] args) {
        new IpFinder();
    }
}

