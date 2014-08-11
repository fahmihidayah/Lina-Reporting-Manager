/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.gui;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author fahmi
 */
public class StandardInputGUIBuilder {
    public static JPanel generateSimpleInputGUI(JComponent component, JTextField text){
        JPanel panel = new JPanel(new BorderLayout());
        
        if (text == null) {
            text = new JTextField();
        }
        
        panel.add(component, BorderLayout.WEST);
        panel.add(text, BorderLayout.CENTER);
        
        return panel;
    }
}
