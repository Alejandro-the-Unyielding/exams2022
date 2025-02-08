package e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.logic = new LogicImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
                logic.hit(cells.get(button));
                if(logic.isOver() && button.getText().equals("B")){
                    cells.entrySet().forEach(entry -> {
                        entry.getKey().setEnabled(true);
                        entry.getKey().setText(" ");
                        logic.reset();
                    });
                }
                else{
                    redraw();
                }
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Position(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    private void redraw(){
        cells.entrySet().forEach(entry -> {
            if(logic.isBishop(entry.getValue())){
                entry.getKey().setText("B");
            }
            else if(logic.isDisabled(entry.getValue())){
                entry.getKey().setEnabled(false);
            }
        });
    }
}
