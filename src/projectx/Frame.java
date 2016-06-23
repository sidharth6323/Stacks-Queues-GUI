/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

/**
 *
 * @author DELL
 */
public class Frame extends JFrame{
    
    int stackChoice;
    Stack stk_undo;
    boolean stk_isRandom;
    StackDisplay stackDisplay;
    JPanel stackDisplayPanel;
    StackMenu stackMenu;
    JPanel stackMenuPanel;
    JPanel stackPanel;
    JLabel stackMessage;
    JPanel stackMessagePanel;
    
    int qChoice;
    Stack q_undo;
    boolean q_isRandom;
    QDisplay qDisplay;
    JPanel qDisplayPanel;
    QueueMenu qMenu;
    JPanel qMenuPanel;
    JPanel qPanel;
    JLabel qMessage;
    JPanel qMessagePanel;
    
    Frame() {
        super("Demo GUI App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JTabbedPane tab=new JTabbedPane();
        
        stackPanel=new JPanel();
        stackPanel.setLayout(new BorderLayout());
        tab.addTab("Stack",stackPanel);
        tab.setMnemonicAt(0, KeyEvent.VK_1);
        
        JLabel stackTitle=new JLabel("DATA STRUCTURES : DEMONSTRATION OF THE STACK");
        stackTitle.setFont(new Font("Callibri",Font.PLAIN,20));
        stackTitle.setForeground(Color.BLACK);
        stackTitle.setPreferredSize(new Dimension(800,80));
        JPanel stackTitlePanel=new JPanel();
        stackTitlePanel.setLayout(new FlowLayout(FlowLayout.LEADING,450,0));
        stackTitlePanel.setBackground(new Color(138,138,138,100));
        stackTitlePanel.add(stackTitle);
        
        stackPanel.add(stackTitlePanel,BorderLayout.NORTH);
        
        stk_undo=new Stack(3);
        stk_isRandom=false;
        
        stackMenu=new StackMenu();
        stackMenu.setBackground(new Color(201,201,201,100));
        stackMenuPanel=new JPanel();
        stackMenuPanel.setBorder(BorderFactory.createEtchedBorder(Color.BLACK, Color.LIGHT_GRAY));
        stackMenuPanel.setBackground(Color.BLACK);
        stackMenu.setPreferredSize(new Dimension(1350,100));
        stackMenuPanel.add(stackMenu);     
        stackMenuPanel.add(Box.createRigidArea(new Dimension(0,100)));
        
        stackDisplay=new StackDisplay();
        stackDisplayPanel=new JPanel();
        stackDisplayPanel.add(Box.createRigidArea(new Dimension(0,425)));
        stackDisplayPanel.add(stackDisplay);
        stackDisplayPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        
        stackMessage=new JLabel(">>> Welcome to Stack Demo. An empty stack has been made. Stack Top = -1");
        stackMessage.setFont(new Font("Callibri",Font.PLAIN,17));
        stackMessage.setForeground(Color.DARK_GRAY);
        stackMessagePanel=new JPanel();
        stackMessagePanel.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        stackMessagePanel.setBackground(Color.LIGHT_GRAY);
        stackMessagePanel.add(stackMessage);
        
        Box stackDisplayBox=Box.createVerticalBox();
        stackDisplayBox.add(stackDisplayPanel);
        stackDisplayBox.add(stackMessagePanel);
        
        stackPanel.add(stackDisplayBox,BorderLayout.CENTER);
        stackPanel.add(stackMenuPanel,BorderLayout.SOUTH);
        stackPanel.updateUI();
        
        stackDisplay.textSetter = new TextInterface(){
            public void setText(String str) {
                stackMessage.setText(">>> "+str);
            }
        };
        
        addStackActionListeners();
        
        qPanel=new JPanel();
        qPanel.setLayout(new BorderLayout());
        tab.addTab("Queue",qPanel);
        tab.setMnemonicAt(1, KeyEvent.VK_2);
        
        JLabel qTitle=new JLabel("DATA STRUCTURES : DEMONSTRATION OF THE QUEUE");
        qTitle.setFont(new Font("Callibri",Font.PLAIN,20));
        qTitle.setForeground(Color.BLACK);
        qTitle.setPreferredSize(new Dimension(800,80));
        JPanel qTitlePanel=new JPanel();
        qTitlePanel.setLayout(new FlowLayout(FlowLayout.LEADING,450,0));
        qTitlePanel.setBackground(new Color(138,138,138,100));
        qTitlePanel.add(qTitle);
        
        qPanel.add(qTitlePanel,BorderLayout.NORTH);
        
        q_undo=new Stack(3);
        q_isRandom=false;
        
        qMenu=new QueueMenu();
        qMenu.setBackground(new Color(201,201,201,100));
        qMenuPanel=new JPanel();
        qMenuPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        qMenuPanel.setBackground(Color.BLACK);
        qMenu.setPreferredSize(new Dimension(1350,100));
        qMenuPanel.add(qMenu);     
        qMenuPanel.add(Box.createRigidArea(new Dimension(0,100)));
        
        qDisplay=new QDisplay();
        qDisplayPanel=new JPanel();
        qDisplayPanel.add(Box.createRigidArea(new Dimension(0,425)));
        qDisplayPanel.add(qDisplay);
        qDisplayPanel.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.LIGHT_GRAY));
        
        qMessage=new JLabel(">>> Welcome to Queue Demo. An empty queue has been made. Front = 0, Rear = -1");
        qMessage.setForeground(Color.DARK_GRAY);
        qMessage.setFont(new Font("Callibri",Font.PLAIN,17));
        qMessagePanel=new JPanel();
        qMessagePanel.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        qMessagePanel.setBackground(Color.LIGHT_GRAY);
        qMessagePanel.add(qMessage);
        
        Box qDisplayBox=Box.createVerticalBox();
        qDisplayBox.add(qDisplayPanel);
        qDisplayBox.add(qMessagePanel);
        
        qPanel.add(qDisplayBox,BorderLayout.CENTER);
        qPanel.add(qMenuPanel,BorderLayout.SOUTH);
        qPanel.updateUI();
        
        qDisplay.textSetter = new TextInterface(){
            public void setText(String str) {
                qMessage.setText(">>>"+str);
            }
        };
        
        addQueueActionListeners();
        
        add(tab);
        setVisible(true);
    }
    
    private void addStackActionListeners(){
        
        stackMenu.pushButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                String str=stackMenu.text.getText();
                stackMenu.text.setValue(null);
                if(isNum(str)){
                    str=str.replace(",","");
                    int num=Integer.parseInt(str);
                    stackDisplay.update_push(num,stk_undo);
                    stackMenu.resetButton.setEnabled(true);
                    stackMenu.resetEnabled=true;
                }
                else{
                    stackDisplay.textSetter.setText("Invalid number");
                }
                if(stk_undo.top!=-1){
                    stackMenu.undoButton.setEnabled(true);
                    stackMenu.undoEnabled=true;
                }
                stackPanel.updateUI();
            }
        });
        
        stackMenu.popButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                stackDisplay.update_pop(stk_undo);
                if(stk_undo.top!=-1){
                    stackMenu.undoButton.setEnabled(true);
                }
                if(stackDisplay.stack.top==-1 && stk_undo.top==-1) stackMenu.resetButton.setEnabled(false);
                stackPanel.updateUI();
            }
        });
        
        stackMenu.undoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                int choice=stk_undo.pop();
                if(choice==0){
                    stackDisplay.undoPush();
                    if(stackDisplay.stack.top==-1 && stk_undo.top==-1) stackMenu.resetButton.setEnabled(false);
                }
                else{
                    stackDisplay.undoPop();
                    stackMenu.resetButton.setEnabled(true);
                    
                }
                if(stk_undo.top==-1){
                    stackMenu.undoButton.setEnabled(false);
                    stackMenu.undoEnabled=false;
                }
                stackPanel.updateUI();
            }
        });
        
        stackMenu.randomButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(!stk_isRandom){
                    stk_isRandom=true;
                    stackMenu.randomButton.setText("Stop");
                    stackMenu.text.setEnabled(false);
                    stackMenu.pushButton.setEnabled(false);
                    stackMenu.popButton.setEnabled(false);
                    stackMenu.undoButton.setEnabled(false);
                    stackMenu.resetButton.setEnabled(false);
                    startStackRandom();
                }
                else{
                    stk_isRandom=false;
                    stackMenu.randomButton.setText("Random");
                    stackMenu.text.setEnabled(true);
                    stackMenu.pushButton.setEnabled(true);
                    stackMenu.popButton.setEnabled(true);
                    stackMenu.undoButton.setEnabled(stackMenu.undoEnabled);
                    stackMenu.resetButton.setEnabled(stackMenu.resetEnabled);
                }
            }
        });
        
        stackMenu.resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                stackDisplay.reset();
                stk_undo.top=-1;
                stackMenu.undoButton.setEnabled(false);
                stackMenu.undoEnabled=false;
                stackMenu.resetButton.setEnabled(false);
                stackMenu.resetEnabled=false;
                stackPanel.updateUI();
            }
        });
    }
    
    private void startStackRandom(){
        SwingWorker<Void, Void> stk_random = new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                Thread.sleep(100);
                stackDisplay.random(stk_undo);
                if (stk_undo.top != -1) {
                    stackMenu.undoEnabled=true;
                }
                stackMenu.resetEnabled=true;
                stackPanel.updateUI();
                Thread.sleep(500);
                return null;
            }

            protected void done() {
                if (stk_isRandom) {
                    startStackRandom();
                }
            }
        };
        stk_random.execute();
    }
        
    private void addQueueActionListeners(){
        
        qMenu.insertButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                String str=qMenu.text.getText();
                qMenu.text.setValue(null);
                if(isNum(str)){
                    str=str.replace(",","");
                    int num=Integer.parseInt(str);
                    qDisplay.update_insert(num,q_undo);
                    qMenu.resetButton.setEnabled(true);
                    qMenu.resetEnabled=true;
                }
                else{
                    qDisplay.textSetter.setText("Invalid number");
                }
                if(q_undo.top!=-1){
                    qMenu.undoButton.setEnabled(true);
                    qMenu.undoEnabled=true;
                }
                qPanel.updateUI();
            }
        });
        
        qMenu.deleteButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                qDisplay.update_delete(q_undo);
                if(q_undo.top!=-1){
                    qMenu.undoButton.setEnabled(true);
                }
                if(qDisplay.q.rear==-1 && qDisplay.q.front==0 && stk_undo.top==-1) qMenu.resetButton.setEnabled(false);
                qPanel.updateUI();
            }
        });
        
        qMenu.undoButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                int choice=q_undo.pop();
                if(choice==0){
                    qDisplay.undoInsert();
                    if(qDisplay.q.rear==-1 && qDisplay.q.front==0 && stk_undo.top==-1) qMenu.resetButton.setEnabled(false);
                }
                else{
                    qDisplay.undoDelete();
                    qMenu.resetButton.setEnabled(true);
                    
                }
                if(q_undo.top==-1){
                    qMenu.undoButton.setEnabled(false);
                    qMenu.undoEnabled=false;
                }
                qPanel.updateUI();
            }
        });
        
        qMenu.randomButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(!q_isRandom){
                    q_isRandom=true;
                    qMenu.randomButton.setText("Stop");
                    qMenu.text.setEnabled(false);
                    qMenu.insertButton.setEnabled(false);
                    qMenu.deleteButton.setEnabled(false);
                    qMenu.undoButton.setEnabled(false);
                    qMenu.resetButton.setEnabled(false);
                    startQueueRandom();
                }
                else{
                    q_isRandom=false;
                    qMenu.randomButton.setText("Random");
                    qMenu.text.setEnabled(true);
                    qMenu.insertButton.setEnabled(true);
                    qMenu.deleteButton.setEnabled(true);
                    qMenu.undoButton.setEnabled(qMenu.undoEnabled);
                    qMenu.resetButton.setEnabled(qMenu.resetEnabled);
                }
            }
        });
        
        qMenu.resetButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                qDisplay.reset();
                q_undo.top=-1;
                qMenu.undoButton.setEnabled(false);
                qMenu.undoEnabled=false;
                qMenu.resetButton.setEnabled(false);
                qMenu.resetEnabled=false;
                qPanel.updateUI();
            }
        });
    }
    
    private void startQueueRandom(){
        SwingWorker<Void, Void> q_random;
        q_random = new SwingWorker<Void, Void>() {
            protected Void doInBackground() throws Exception {
                if(qDisplay.q.front == qDisplay.q.size){
                    qDisplay.textSetter.setText("Cannot perform any more operations on the queue. Please try using the undo or the reset buttons to continue. Front = "+Integer.toString(qDisplay.q.front)+" Rear = "+Integer.toString(qDisplay.q.rear));
                    qMenu.randomButton.doClick();
                    return null;
                }
                Thread.sleep(100);
                qDisplay.random(q_undo);
                if (q_undo.top != -1) {
                    qMenu.undoEnabled=true;
                }
                qMenu.resetEnabled=true;
                qPanel.updateUI();
                Thread.sleep(500);
                return null;
            }

            protected void done() {
                if (q_isRandom) {
                    startQueueRandom();
                }
            }
        };
        q_random.execute();
    }
    
    private static boolean isNum(String str){
        str=str.replace(",","");
        try{
            Integer x=Integer.parseInt(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
