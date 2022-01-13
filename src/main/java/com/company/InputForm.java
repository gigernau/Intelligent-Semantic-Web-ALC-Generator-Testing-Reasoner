package com.company;



import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.company.Utility.*;


public class InputForm extends JPanel {



    private static final int COLUMNS = 10;
    private static final int GAP = 3;
    private static final Insets LABEL_INSETS = new Insets(GAP, GAP, GAP, 15);
    private static final Insets TEXTFIELD_INSETS = new Insets(GAP, GAP, GAP, GAP);
    private String[] labelTexts;
    private Map<String, JTextField> fieldMap = new HashMap<String, JTextField>();
    public static int count =0;
    public InputForm(String[] labelTexts) {
        this.labelTexts = labelTexts;
        setLayout(new GridBagLayout());
        for (int i = 0; i < labelTexts.length; i++) {
            if(i<=4) {
                String text = labelTexts[i];
                JTextField field = new JTextField(COLUMNS);
                fieldMap.put(text, field);
                if (i == 4) {
                    addLabel("",i);
                    addLabel("Somma somma delle seguenti deve essere 100:", i+1);
                    addLabel(text, i+2);
                    addTextField(field, i+2);
                } else {
                    addLabel(text, i);
                    addTextField(field, i);
                }
            }else{

                String text = labelTexts[i];
                JTextField field = new JTextField(COLUMNS);
                fieldMap.put(text, field);

                addLabel(text, i+2);
                addTextField(field, i+2);


            }
        }
    }

    public String[] getLabelTexts() {
        return labelTexts;
    }

    private void addTextField(JTextField field, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = TEXTFIELD_INSETS;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(field, gbc);
    }

    private void addLabel(String text, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = LABEL_INSETS;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(new JLabel(text), gbc);
    }

    public String getFieldText(String key) {
        String text = "";
        JTextField field = fieldMap.get(key);
        if (field != null) {
            text = field.getText();
        }
        return text;
    }

    private static void open(File f) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(f);
            } catch (IOException e) { }
        } else { }
    }
    static void createAndShowGui() throws myException, URISyntaxException {
        String[] labelTexts = new String[] { "Depth Concept Tree",
                "Unsatisfacible (%)","Num. Litteral","Num. Role", "Litteral (%)", "Union (%)", "Intersection (%)", "For All (%)","Exist (%)"};
        InputForm inputForm = new InputForm(labelTexts);


        int result = JOptionPane.showConfirmDialog(null, inputForm, "ALC Concept Generator",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {

            if( !(inputForm.getFieldText("Depth Concept Tree").equals(""))){
                depth = new Integer(inputForm.getFieldText("Depth Concept Tree"));
                insod = new Integer(inputForm.getFieldText("Unsatisfacible (%)"));
                atomic = new Integer(inputForm.getFieldText("Litteral (%)"));
                unione = new Integer(inputForm.getFieldText("Union (%)"));
                intersezione = new Integer(inputForm.getFieldText("Intersection (%)"));
                perOgni = new Integer(inputForm.getFieldText("For All (%)"));
                esiste = new Integer(inputForm.getFieldText("Exist (%)"));

                numAtomic = new Integer(inputForm.getFieldText("Num. Litteral"));
                numRole = new Integer(inputForm.getFieldText("Num. Role"));

                SingleTest.singletest();
            }else{
                if(count < 1) {
                    JOptionPane.showMessageDialog(null, "I campi non possono essere vuoti!", "Warning ", JOptionPane.WARNING_MESSAGE);
                    count++;
                }else{
                    count=0;
                    JOptionPane.showMessageDialog(null, "Non hai capito?!", "Warning ", JOptionPane.WARNING_MESSAGE);
                    open(f);
                }
            }

        }
    }



}
