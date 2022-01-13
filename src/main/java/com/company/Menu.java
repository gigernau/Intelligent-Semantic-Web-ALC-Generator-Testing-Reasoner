package com.company;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.company.Utility.*;


public class Menu extends JPanel {

    JButton button;
    JFrame frame;
    static void createAndShowGui() throws Utility.myException
    {
            JFrame f = new JFrame("Menu");
            f.setLocation(800,300);
            JPanel panel = new JPanel();
            panel.setBounds(25, 50, 250, 250);


            JButton b1 = new JButton("Test Singolo");
            b1.setBounds(50, 100, 80, 30);
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        InputForm.createAndShowGui();
                    } catch (myException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            JButton b2 = new JButton("Batteria di test");
            b2.setBounds(100, 100, 80, 30);
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        BatteryTest.battery();
                    } catch (myException ex) {
                        ex.printStackTrace();
                    } catch (OWLOntologyCreationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });


            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> f.dispose());
            exit.setBounds(100,150,80,30);
            panel.add(b1);
            panel.add(b2);

            f.add(exit);
            f.add(panel);
            f.setSize(300, 250);
            f.setLayout(null);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


}




