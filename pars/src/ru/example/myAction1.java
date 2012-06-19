package ru.example;
import XmlLayout.XmlLayout;
import XmlParser.XmlParser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class myAction1 implements ActionListener{

    public void actionPerformed(ActionEvent e){
       JTextField login, pass, cpass;
            login = (JTextField) XmlLayout.im.items.get("textfield1");
            pass = (JTextField) XmlLayout.im.items.get("textfield2");
            cpass = (JTextField) XmlLayout.im.items.get("textfield3");
            System.out.println();
            System.out.println(login.getText());
            login.setText("");
            System.out.println(pass.getText());
            pass.setText("");
            System.out.println(cpass.getText());
            cpass.setText("");
    }
}
