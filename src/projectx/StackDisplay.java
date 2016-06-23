/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

/**
 *
 * @author DELL
 */
public class StackDisplay extends JPanel{
    public Stack stack;
    private JPanel panel[];
    private JLabel elts[];
    private JLabel top;
    private JPanel topPanel[];
    public TextInterface textSetter;
    public Stack tempPopStorage;
    StackDisplay(){
        super(); 
        super.setLayout(new BorderLayout());
        
        stack=new Stack(8);
        tempPopStorage = new Stack(3);
        
        elts = new JLabel[stack.size+1];
        panel=new JPanel[stack.size+1];
        top=new JLabel("TOP-->");
        top.setFont(new Font("Serif",Font.BOLD,12));
        topPanel=new JPanel[stack.size+1];
        Box eltBox,panelBox[];
        eltBox=Box.createVerticalBox();
        panelBox=new Box[stack.size+1];
        
        Border border=BorderFactory.createLineBorder(Color.GRAY, 1);
        Border empty=BorderFactory.createEmptyBorder(5,5,5,5);
        Border cmpnd=BorderFactory.createCompoundBorder(border,empty);
        
        for(int i=0;i<stack.size;i++){
            elts[i]=new JLabel("");
            topPanel[i]=new JPanel();
            topPanel[i].setPreferredSize(new Dimension(40,30));
            panel[i]=new JPanel();
            panel[i].setBorder(cmpnd);
            panel[i].setPreferredSize(new Dimension(55,45));
            panel[i].add(elts[i]);
            panelBox[i]=Box.createHorizontalBox();
            panelBox[i].add(topPanel[i]);
            panelBox[i].add(panel[i]);
            eltBox.add(panelBox[i]);
        }
        elts[stack.size]=new JLabel("");
        panel[stack.size]=new JPanel();
        panel[stack.size].setPreferredSize(new Dimension(55,45));
        topPanel[stack.size]=new JPanel();
        topPanel[stack.size].add(top);
        topPanel[stack.size].setPreferredSize(new Dimension(40,30));
        panelBox[stack.size]=Box.createHorizontalBox();
        panelBox[stack.size].add(topPanel[stack.size]);
        panelBox[stack.size].add(panel[stack.size]);
        eltBox.add(panelBox[stack.size]);
        
        super.add(eltBox,BorderLayout.CENTER);
    }
    
    public void update_push(int num,Stack undo){
        if (stack.top == stack.size - 1) {
            textSetter.setText("The stack is full! Cannot push more elements. Top = "+Integer.toString(stack.top));
            return;
        }
        if(undo.top==undo.size-1) undo.forgetEarlierChoice();
        undo.push(0);
        topPanel[stack.size-stack.nElts].remove(top);
        stack.push(num);
        topPanel[stack.size-stack.nElts].add(top);
        topPanel[stack.size-stack.nElts].add(top);
        elts[stack.size - stack.nElts].setText(Integer.toString(num));
        textSetter.setText(Integer.toString(num) +" has been pushed on to the stack. Top = "+Integer.toString(stack.top));
    }
    
    public void update_pop(Stack undo){
        if(stack.top==-1){
            textSetter.setText("The stack is empty! Cannot pop. Top = "+Integer.toString(stack.top));
            return;
        }
        if(undo.top==undo.size-1) undo.forgetEarlierChoice();        
        if(tempPopStorage.top==tempPopStorage.size-1) tempPopStorage.forgetEarlierChoice();
        undo.push(1);
        topPanel[stack.size-stack.nElts].remove(top);
        elts[stack.size-stack.top-1].setText("");
        int num=stack.pop();
        tempPopStorage.push(num);
        topPanel[stack.size-stack.nElts].add(top);
        textSetter.setText(Integer.toString(num) +" has been popped from the stack. Top = "+Integer.toString(stack.top));
    }
    
    public void undoPush(){
        elts[stack.size-stack.top-1].setText("");
        topPanel[stack.size-stack.nElts].remove(top);
        stack.pop();
        topPanel[stack.size-stack.nElts].add(top);
        textSetter.setText("Undo successful. Top = "+Integer.toString(stack.top));
    }
    
    public void undoPop(){
        topPanel[stack.size-stack.nElts].remove(top);
        stack.push(tempPopStorage.pop());
        topPanel[stack.size-stack.nElts].add(top);
        elts[stack.size - stack.top - 1].setText(Integer.toString(stack.getVal(stack.top)));
        textSetter.setText("Undo successful. Top = "+Integer.toString(stack.top));
    }
    
    public void random(Stack undo) throws InterruptedException{
        int choice;
        Random getRandom=new Random();
        if(stack.nElts==stack.size) choice=1;
        else if(stack.nElts==0) choice=0;
        else{
            int r=getRandom.nextInt(10+this.stack.nElts);
            if(r<10) choice=0;
            else choice=1; 
        }
        if(choice==1) update_pop(undo);
        else update_push(getRandom.nextInt(100),undo);
    }
    
    public void reset(){
        topPanel[stack.size-stack.nElts].remove(top);
        stack.nElts=0;
        stack.top=-1;
        for(int i=0;i<stack.size;i++){
            elts[i].setText("");
        }
        topPanel[stack.size-stack.nElts].add(top);
        textSetter.setText("Stack has been reset. Top = "+Integer.toString(stack.top));
    } 
}
