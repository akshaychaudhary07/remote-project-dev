package org.example.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OnlineExam extends JFrame implements ActionListener {
    JLabel ques;
    JButton next;
    ButtonGroup bg;
    JRadioButton[] rb = new JRadioButton[5];
    int index = 0,count=0;
    OnlineExam(String s){
        super(s);
        bg = new ButtonGroup();
        ques = new JLabel();
        add(ques);
        for(int i=0;i<rb.length;i++){
            rb[i] = new JRadioButton();
            add(rb[i]);
            bg.add(rb[i]);
        }

        next = new JButton("Next");

        next.addActionListener(this);

        ques.setBounds(60,80,200,20);
        rb[0].setBounds(60,100,200,20);
        rb[1].setBounds(60,120,200,20);
        rb[2].setBounds(60,140,200,20);
        rb[3].setBounds(60,160,200,20);

        next.setBounds(100,200,100,20);
        add(next);
        setQues();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLayout(null);
        setVisible(true);
    }

    void setQues(){

        if(index==0){
            ques.setText("Que1: What is Java?");
            rb[0].setText("programming Language");
            rb[1].setText("Framework");
            rb[2].setText("toy");
            rb[3].setText("operating system");
        }else if(index==1){
            ques.setText("Que2: Which class is available to all the class automatically?");
            rb[0].setText("Swing");
            rb[1].setText("Applet");
            rb[2].setText("Object");
            rb[3].setText("ActionEvent");
        }else if(index==2){
            ques.setText("Que3: Which package is directly available to our class without importing it?");
            rb[0].setText("swing");
            rb[1].setText("applet");
            rb[2].setText("net");
            rb[3].setText("lang");
        }else if(index==3){
            ques.setText("Que4: String class is defined in which package?");
            rb[0].setText("lang");
            rb[1].setText("Swing");
            rb[2].setText("Applet");
            rb[3].setText("awt");
        }else if(index==4){
            ques.setText("Que5: Which institute is best for java coaching?");
            rb[0].setText("Utek");
            rb[1].setText("Aptech");
            rb[2].setText("SSS IT");
            rb[3].setText("jtek");
        }

        ques.setBounds(60,80,200,20);
        for(int i=0,j=0;i<=60;i+=20,j++){
            rb[j].setBounds(60,100+i,200,20);
        }
    }

    boolean check(){
        if(index==0){
            return rb[0].isSelected();
        }
        if(index==1){
            return rb[2].isSelected();
        }
        if(index==2){
            return rb[3].isSelected();
        }
        if(index==3){
            return rb[0].isSelected();
        }
        if(index==4){
            return rb[2].isSelected();
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==next){
            if(check()){
                count+=1;
            }
            index++;
            rb[4].setSelected(true);
            setQues();
            if(index==4){
                next.setText("Submit");
            }
        }
        if(e.getActionCommand().equals("Submit")){
            if(check()){
                count+=1;
            }
            index++;
            JOptionPane.showMessageDialog(this,"Final Score: " + count + "/5");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new OnlineExam("Online Exam");
    }
}
