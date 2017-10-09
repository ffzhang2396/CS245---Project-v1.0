/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.v1.pkg0.pkg1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;


public class Credits extends JPanel {

    private MainFrame main;
    private JButton backButton = new JButton("Back");
    private JLabel[] names = new JLabel[6];
    private JPanel scores = new JPanel();
    private JPanel buttons = new JPanel(new BorderLayout());

    /*
    Constructor
     */
    public Credits() {

        setLayout(new BorderLayout());
        readcredits();
        showCreditss();
        addBackButtons();

    }

    /*
    This method is used to set this instance of MainFrame to the current
    main frame so that the swapview method can be accessed to change the
    panel back to the main menu once the back button is pressed.
     */
    public void setMain(MainFrame panel) {
        this.main = panel;
    }


    /*
    Displaying the Credits.
     */
    private void showCreditss() {
        scores.setLayout(new BoxLayout(scores, BoxLayout.Y_AXIS));

        for (int i = 0; i < 6; i++) {

            if (names[i] != null) {
                scores.add(names[i]);
            }
        }
        add(scores, BorderLayout.CENTER);
    }

    /*
    Reading the Credits from the array and adding 
    them to the JLabel array.
     */
    private void readcredits() {
        //String[] entry;
        
        String[] entries = {"Credits", "Brandon Nguyen 011499566", "Charly Dang 010924537", "Colin Koo 010291242", "Felix Zhang 010423283", "Gerianna Geminino 010662522"};
        String[] input;
      
        	names[0] = new JLabel(entries[0]);
        	names[0].setFont(new Font("Papyrus", Font.BOLD, 24));
            names[0].setAlignmentX(Component.CENTER_ALIGNMENT);
        	
            for (int i = 1; i < 6; i++) {

                    input = entries[i].split(" ");
                    names[i] = new JLabel(input[0] + " " + input[1] + "........." + input[2]);
                    names[i].setFont(new Font("Papyrus", Font.BOLD, 24));
                    names[i].setAlignmentX(Component.CENTER_ALIGNMENT);
                }
            
      
    }

    /*
    adding the back button.
     */
    private void addBackButtons() {
        buttons.add(backButton, BorderLayout.LINE_START);
        buttons.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(buttons, BorderLayout.PAGE_END);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.startMenuTimer();
                main.swapView("menu");
            }
        });
    }
}
