package e2;

import javax.swing.*;

import java.util.*;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.logic = new LogicImpl();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
                logic.hit(cells.get(button));
                if(logic.isOver()){
                    System.exit(0);;
                }
                else{
                    redraw();
                }
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton();
                this.cells.put(jb, new Position(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        redraw();
        this.setVisible(true);
    }    

    private void redraw(){
        for (Entry<JButton,Position> entry : this.cells.entrySet()) {
            if(logic.isSelected(entry.getValue())){
                entry.getKey().setText("*");
            }
            else{
                entry.getKey().setText("");
            }
            
        }
    }
}
