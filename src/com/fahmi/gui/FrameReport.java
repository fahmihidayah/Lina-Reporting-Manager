/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.gui;

import com.oracle_source.layout.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

/**
 *
 * @author fahmi
 */
public class FrameReport extends JFrame{
    private JMenuItem mnItmOpen;
    private JMenuItem mnItmExit;
    private JMenuItem mnSpecial;
    
    private JMenuItem mnAbout;
    
    
    private JTextField txtExcelSource;
    private JTextField txtImagesSource;
    private JTextField txtImageMitraSource;
    private JTextField txtDestination;
    private JTextField txtPreviewRowNumber;
    
    private JTextField txtHeader;
    
    private JButton btnImportImageSource;
    private JButton btnImportImageCompanySource;
    private JButton btnImportImageMitraSource;
    private JButton btnExportDestination;
    private JButton btnShowPreviewReport;
    private JButton btnGenerateReport;
    private JButton btnExtractDataExcel;
    private JButton btnPreviewAll;
    
    public FrameReport() {
        setTitle("Lina Reporting Manager");
        
        instanceAllComponent();
        
        setJMenuBar(generateMenuBar());
        
        add(generateMainPanel());
        
        setResizable(false);
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void instanceAllComponent(){
        mnItmOpen = new JMenuItem("Buka File Excel");
        mnItmExit = new JMenuItem("Keluar");
        mnAbout = new JMenuItem("Tentang");
        mnSpecial = new JMenuItem("Special");
        
        txtExcelSource = new JTextField();
        txtImagesSource =  new JTextField();
        txtImageMitraSource = new JTextField();
        txtDestination = new JTextField();
        txtPreviewRowNumber = new JTextField();
    
        txtHeader = new JTextField(10);
        
        btnImportImageSource = new JButton("Impor");
        btnImportImageCompanySource = new JButton("Impor");
        btnImportImageMitraSource = new JButton("Impor");
        btnExportDestination = new JButton("Impor");
        btnShowPreviewReport = new JButton("Lihat");
        btnGenerateReport = new JButton("Hasilkan Laporan");
        btnExtractDataExcel = new  JButton("Ekstrak data Excel");
        btnPreviewAll = new JButton("Preview All");
    }
    
    public JMenuBar generateMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menu = new JMenu("Berkas");
        menu.add(mnItmOpen);
        menu.add(mnItmExit);
        
        JMenu menuHelp = new JMenu("Bantuan");
        menuHelp.add(mnAbout);
      //  menuHelp.add(mnSpecial);
        
        menuBar.add(menu);
        menuBar.add(menuHelp);
        
        return menuBar;
    }
    
    public JPanel generateMainPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(generateNorthPanel(), BorderLayout.NORTH);
        
        panel.add(generatePanelImageSource(),BorderLayout.CENTER);
        
        panel.add(generateSouthPanel(), BorderLayout.SOUTH);
        
        return panel;
    }
    
    public JPanel generateNorthPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.add(generatePanelReportHeader(), BorderLayout.CENTER);
        
        panel.add(generatePanelExcelSourceInfo() ,BorderLayout.SOUTH);
        
        return panel;
    }
    
    public JPanel generatePanelReportHeader(){
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.setBorder(new TitledBorder("Nama kota"));
        
        panel.add(txtHeader, BorderLayout.CENTER);
        
        return panel;
    }
    
    public JPanel generatePanelExcelSourceInfo(){
        JPanel panel = new JPanel(new SpringLayout());
        
        panel.setBorder(new TitledBorder("Sumber Excel"));
        
        panel.add(txtExcelSource);
        
        SpringUtilities.makeCompactGrid(panel, 1, 1, 2, 2, 2, 2);
        
        return panel;
    }
    
    public JPanel generatePanelImageSource(){
        JPanel panel = new JPanel(new SpringLayout());
        
        panel.setBorder(new TitledBorder("Sumber Gambar"));
        
        panel.add(new JLabel("Folder gambar                     "));
        panel.add(txtImagesSource);
        panel.add(btnImportImageSource);
        panel.add(new JLabel("Gambar logo mitra             "));
        panel.add(txtImageMitraSource);
        panel.add(btnImportImageMitraSource);
        
        SpringUtilities.makeCompactGrid(panel, 2, 3, 2, 2, 1, 1);
        
        return panel;
    }
    
    public JPanel generateSouthPanel(){
        JPanel panel = new JPanel(new GridLayout(4, 1));
        
        JPanel panelButtonExtract = new JPanel();
        
        panelButtonExtract.add(btnExtractDataExcel);
        
        panel.add(panelButtonExtract);
        
        panel.add(generatePanelDestinationResult());
        
        panel.add(generatePreviewPanel());
        
        panel.add(generateButtonGeneratePanel());
        
        return panel;
    }
    
    public JPanel generatePanelDestinationResult(){
        JPanel panel = new JPanel(new SpringLayout());
        panel.setBorder(new TitledBorder("Folder laporan"));
        
        panel.add(new JLabel("Folder hasil laporan                     "));
        
        panel.add(txtDestination);
        
        panel.add(btnExportDestination);
        
        SpringUtilities.makeCompactGrid(panel, 1, 3, 2, 2, 1, 1);
        return panel;
    }
    
    public JPanel generatePreviewPanel(){
        JPanel panel = new JPanel(new SpringLayout());
        
        panel.setBorder(new TitledBorder("Preview laporan"));
        
        panel.add(new JLabel("Lihat laporan di index excel ke - "));
        
        panel.add(txtPreviewRowNumber);
        
        panel.add(btnShowPreviewReport);
        
        SpringUtilities.makeCompactGrid(panel, 1, 3, 2, 2, 1, 1);
        
        return panel;
    }
    
    public JPanel generateButtonGeneratePanel(){
        JPanel panel = new JPanel(new FlowLayout());
        
        panel.add(btnGenerateReport);
        
        panel.add(btnPreviewAll);
        
        return panel;
    }

    public JMenuItem getMnItmOpen() {
        return mnItmOpen;
    }

    public void setMnItmOpen(JMenuItem mnItmOpen) {
        this.mnItmOpen = mnItmOpen;
    }

    public JMenuItem getMnItmExit() {
        return mnItmExit;
    }

    public void setMnItmExit(JMenuItem mnItmExit) {
        this.mnItmExit = mnItmExit;
    }

    public JMenuItem getMnAbout() {
        return mnAbout;
    }

    public void setMnAbout(JMenuItem mnAbout) {
        this.mnAbout = mnAbout;
    }
    
    public JTextField getTxtExcelSource() {
        return txtExcelSource;
    }

    public void setTxtExcelSource(JTextField txtExcelSource) {
        this.txtExcelSource = txtExcelSource;
    }

    public JTextField getTxtImagesSource() {
        return txtImagesSource;
    }

    public void setTxtImagesSource(JTextField txtImagesSource) {
        this.txtImagesSource = txtImagesSource;
    }

    public JTextField getTxtImageMitraSource() {
        return txtImageMitraSource;
    }

    public void setTxtImageMitraSource(JTextField txtImageMitraSource) {
        this.txtImageMitraSource = txtImageMitraSource;
    }

    public JTextField getTxtDestination() {
        return txtDestination;
    }

    public void setTxtDestination(JTextField txtDestination) {
        this.txtDestination = txtDestination;
    }

    public JTextField getTxtPreviewRowNumber() {
        return txtPreviewRowNumber;
    }

    public void setTxtPreviewRowNumber(JTextField txtPreviewRowNumber) {
        this.txtPreviewRowNumber = txtPreviewRowNumber;
    }

    public JButton getBtnImportImageSource() {
        return btnImportImageSource;
    }

    public void setBtnImportImageSource(JButton btnImportImageSource) {
        this.btnImportImageSource = btnImportImageSource;
    }

    public JButton getBtnImportImageCompanySource() {
        return btnImportImageCompanySource;
    }

    public void setBtnImportImageCompanySource(JButton btnImportImageCompanySource) {
        this.btnImportImageCompanySource = btnImportImageCompanySource;
    }

    public JButton getBtnImportImageMitraSource() {
        return btnImportImageMitraSource;
    }

    public void setBtnImportImageMitraSource(JButton btnImportImageMitraSource) {
        this.btnImportImageMitraSource = btnImportImageMitraSource;
    }

    public JButton getBtnExportDestination() {
        return btnExportDestination;
    }

    public void setBtnExportDestination(JButton btnExportDestination) {
        this.btnExportDestination = btnExportDestination;
    }

    public JButton getBtnShowPreviewReport() {
        return btnShowPreviewReport;
    }

    public void setBtnShowPreviewReport(JButton btnShowPreviewReport) {
        this.btnShowPreviewReport = btnShowPreviewReport;
    }

    public JButton getBtnGenerateReport() {
        return btnGenerateReport;
    }

    public void setBtnGenerateReport(JButton btnGenerateReport) {
        this.btnGenerateReport = btnGenerateReport;
    }

    public JButton getBtnExtractDataExcel() {
        return btnExtractDataExcel;
    }

    public void setBtnExtractDataExcel(JButton btnExtractDataExcel) {
        this.btnExtractDataExcel = btnExtractDataExcel;
    }

    public JTextField getTxtHeader() {
        return txtHeader;
    }

    public void setTxtHeader(JTextField txtHeader) {
        this.txtHeader = txtHeader;
    }

    public JButton getBtnPreviewAll() {
        return btnPreviewAll;
    }

    public void setBtnPreviewAll(JButton btnPreviewAll) {
        this.btnPreviewAll = btnPreviewAll;
    }

    public JMenuItem getMnSpecial() {
        return mnSpecial;
    }

    public void setMnSpecial(JMenuItem mnSpecial) {
        this.mnSpecial = mnSpecial;
    }
    
    
}
