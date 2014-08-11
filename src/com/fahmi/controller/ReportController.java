/*
 * This software distributed free for Roselina Febriati.
 * to help make report and to avoid her go home late.
 * please dont copy this software. may be it was useless.
 * but it was mean to me to show how much i miss her.. :$
 */
package com.fahmi.controller;

import com.fahmi.gui.FrameAbout;
import com.fahmi.gui.FrameReport;
import com.fahmi.reportmanager.LinaReportingManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.read.biff.BiffException;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author fahmi
 */
public class ReportController {
    private FrameReport frameReport;
    private LinaReportingManager reportingManager;

    public FrameReport getFrameReport() {
        return frameReport;
    }

    public void setFrameReport(FrameReport frameReport) {
        this.frameReport = frameReport;
    }

    public LinaReportingManager getReportingManager() {
        return reportingManager;
    }

    public void setReportingManager(LinaReportingManager reportingManager) {
        this.reportingManager = reportingManager;
    }

    public ReportController(FrameReport frameReport, LinaReportingManager reportingManager) {
        this.frameReport = frameReport;
        this.reportingManager = reportingManager;
        setActionListenner();
    }
    
    public void setActionListenner(){
        
        frameReport.getTxtExcelSource().getDocument().addDocumentListener(new TextFieldChangeSizeListenner(this));
        frameReport.getTxtHeader().getDocument().addDocumentListener(new TextFieldChangeSizeListenner(this));
        frameReport.getTxtImageMitraSource().getDocument().addDocumentListener(new TextFieldChangeSizeListenner(this));
        frameReport.getTxtImagesSource().getDocument().addDocumentListener(new TextFieldChangeSizeListenner(this));
        
        frameReport.getMnAbout().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                FrameAbout frame = new FrameAbout();
            }
        });
        
        frameReport.getMnItmOpen().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls", "xls");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(frameReport);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    frameReport.getTxtExcelSource().setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        
        frameReport.getMnItmExit().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        frameReport.getBtnExportDestination().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showOpenDialog(frameReport);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    frameReport.getTxtDestination().setText(chooser.getSelectedFile().getAbsolutePath());   
                }
            }
        });
        
        frameReport.getBtnGenerateReport().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (frameReport.getTxtDestination().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frameReport, "Mohon pilih folder tujuan", "Error", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                if(reportingManager.getListData().isEmpty()){
                    JOptionPane.showMessageDialog(frameReport, "Mohon lakukan ekstraksi data excel dulu", "Error", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            frameReport.getBtnGenerateReport().setText("Loading...");
                            frameReport.getBtnGenerateReport().setEnabled(false);
                            reportingManager.setStringPathHasilLaporan(frameReport.getTxtDestination().getText());
                            reportingManager.generateAllReportToDirectory();
                        } catch (JRException ex) {
                            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        JOptionPane.showMessageDialog(frameReport, "Laporan berhasil dibuat", "Pesan", JOptionPane.PLAIN_MESSAGE);
                        frameReport.getBtnGenerateReport().setText("Hasilkan Laporan");
                            frameReport.getBtnGenerateReport().setEnabled(true);
                    }
                }).start();
            }
        });
        
        
        frameReport.getBtnImportImageMitraSource().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
                chooser.setFileFilter(filter);  
                int returnVal = chooser.showOpenDialog(frameReport);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    frameReport.getTxtImageMitraSource().setText(chooser.getSelectedFile().getAbsolutePath());   
                }
            }
        });
        
        frameReport.getBtnImportImageSource().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = chooser.showOpenDialog(frameReport);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    frameReport.getTxtImagesSource().setText(chooser.getSelectedFile().getAbsolutePath());   
                }
            }
        });
        
        frameReport.getBtnShowPreviewReport().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        if (reportingManager.getListData().size() == 0) {
                            JOptionPane.showMessageDialog(frameReport, "Data excel belum diekstrak", "Error", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        
                        
                        frameReport.getBtnShowPreviewReport().setText("Loading...");
                        frameReport.getBtnShowPreviewReport().setEnabled(false);
                        int i = Integer.parseInt(frameReport.getTxtPreviewRowNumber().getText());
                        try {
                            reportingManager.previewReportAtIndex(i);
                        } catch (JRException ex) {
                            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        frameReport.getBtnShowPreviewReport().setText("Lihat");
                        frameReport.getBtnShowPreviewReport().setEnabled(true);
                    }
                }).start();
            }
        });
        
        frameReport.getBtnPreviewAll().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        frameReport.getBtnPreviewAll().setEnabled(false);
                        frameReport.getBtnPreviewAll().setText("Preview All");
                        try {
                            reportingManager.previewAllReport();
                        } catch (JRException ex) {
                            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        frameReport.getBtnPreviewAll().setText("Preview All");
                        frameReport.getBtnPreviewAll().setEnabled(true);
                    }
                });
                
            }
        });
        
        frameReport.getBtnExtractDataExcel().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(frameReport.getTxtExcelSource().getText().isEmpty()){
                    JOptionPane.showMessageDialog(frameReport, "Mohon diisi terlebih dahulu sumber excelnya", "Pesann", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                else if(frameReport.getTxtHeader().getText().isEmpty()){
                    JOptionPane.showMessageDialog(frameReport, "Mohon diisi terlebih dahulu judul headernya", "Pesann", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                else if(frameReport.getTxtImageMitraSource().getText().isEmpty()){
                    JOptionPane.showMessageDialog(frameReport, "Mohon diisi terlebih dahulu sumber gambar mitra", "Pesann", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                else if(frameReport.getTxtImagesSource().getText().isEmpty()){
                    JOptionPane.showMessageDialog(frameReport, "Mohon diisi terlebih dahulu sumber gambar laporannya", "Pesann", JOptionPane.PLAIN_MESSAGE);
                    return;
                }
                
                if (reportingManager.isChangeProperty()) {
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                reportingManager.setStringHeader(frameReport.getTxtHeader().getText());
                                reportingManager.setStringExcelFile(frameReport.getTxtExcelSource().getText());
                                reportingManager.setStringPathGambar(frameReport.getTxtImagesSource().getText());
                                reportingManager.setStringLogoMitra(frameReport.getTxtImageMitraSource().getText());
                                reportingManager.generateWorkbook();
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(frameReport, "Kesalahan IO", "Error", JOptionPane.ERROR_MESSAGE);
                            } catch (BiffException ex) {
                                JOptionPane.showMessageDialog(frameReport, "Kesalahan Biff", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            reportingManager.loadReport();
                            JOptionPane.showMessageDialog(frameReport, "Data berhasil di ekstrak", "Pesan", JOptionPane.PLAIN_MESSAGE);
                            
                            System.out.println("Data ekstrak :" + reportingManager.getListData().size() + "dan " + reportingManager.getListDataDua().size());
                            reportingManager.setChangeProperty(false);
                            checkPropertyChange();
                        }
                        
                    }).start();
                    
                }
            }
        });
        frameReport.getMnSpecial().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showConfirmDialog(frameReport, "  This software distributed free for Roselina Febriati.\n" +
"  to help make report and to avoid her go home late.\n" +
"  please dont copy this software. may be it was useless.\n" +
"  but it was mean to me to show how much i miss her.. :$", "Pesan untuk pengguna", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
    
    public void checkPropertyChange(){
        if (reportingManager.isChangeProperty()) {
            frameReport.getBtnExtractDataExcel().setText("Ekstrak data Excel");
        }
        else{
            frameReport.getBtnExtractDataExcel().setText("File Excel sudah diekstract");
        }
    }
    
    public static class TextFieldChangeSizeListenner implements DocumentListener{
        
        private ReportController reportController;

        public TextFieldChangeSizeListenner(ReportController reportController) {
            this.reportController = reportController;
        }


        @Override
        public void insertUpdate(DocumentEvent e) {
            reportController.reportingManager.setChangeProperty(true);
            reportController.checkPropertyChange();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            reportController.reportingManager.setChangeProperty(true);
            reportController.checkPropertyChange();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            reportController.reportingManager.setChangeProperty(true);
            reportController.checkPropertyChange();
        }
        
    }
    
    
}
