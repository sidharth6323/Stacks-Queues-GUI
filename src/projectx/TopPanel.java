/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectx;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author DELL
 */
public class TopPanel extends JPanel{
    
    public JButton stackButton;
    public JButton qButton;
    
    TopPanel(){
        super();
        
        super.setLayout(new FlowLayout(FlowLayout.LEFT, 400, 3));
        
        Border empty=BorderFactory.createEmptyBorder(5,5,5,5);
        Border btnborder=BorderFactory.createEtchedBorder();
        
        stackButton=new JButton("Stack");
        stackButton.setPreferredSize(new Dimension(100,40));
        stackButton.setBorder(BorderFactory.createCompoundBorder(btnborder, empty));
        
        qButton=new JButton("Queue");
        qButton.setPreferredSize(new Dimension(100,40));
        qButton.setBorder(BorderFactory.createCompoundBorder(btnborder, empty));
        
        super.add(stackButton);
        super.add(qButton);
    }
}
