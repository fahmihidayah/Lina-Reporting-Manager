/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.gui;

import com.oracle_source.layout.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 *
 * @author fahmi
 */
public class FrameAbout extends JFrame{
    private JTextArea textAbout;

    public FrameAbout(){
        setTitle("Tentang");
        setResizable(false);
        setSize(300,100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initialComponent();
        add(generateMainPanel());
        setVisible(true);
    }

    public void initialComponent(){
        textAbout = new JTextArea();
        textAbout.append("Dibuat oleh    : Muhammad Fahmi Hidayah\n");
        textAbout.append("Email              : vambex.system@mgmail.com\n");
        textAbout.append("Perusahaan   : PT.MoeslimInformatics\n");
        textAbout.setEditable(false);
    }
    
    public JPanel generateMainPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(textAbout);

        return panel;
    }
      
}
