/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.reportmanager;

import com.fahmi.data.PrintedData;
import com.fahmi.data.PrintedDataDua;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author fahmi
 */
public class LinaReportingManager {
    private ArrayList<PrintedData> listData = new ArrayList<>();
    private ArrayList<PrintedDataDua> listDataDua = new ArrayList<>();
    private String stringExcelFile;
    private String stringPathGambar;
    private String stringLogoPerusahaan = "logo_perusahaan.jpeg";
    private String stringLogoMitra;
    private String stringPathHasilLaporan;
    private String stringHeader;
    private Workbook workBook;
    // new attribute
    private HSSFWorkbook hssfworkBook;
    private boolean changeProperty;

    public ArrayList<PrintedData> getListData() {
        return listData;
    }

    public void setListData(ArrayList<PrintedData> listData) {
        this.listData = listData;
    }

    public String getStringExcelFile() {
        return stringExcelFile;
    }

    public void setStringExcelFile(String stringExcelFile) {
        this.stringExcelFile = stringExcelFile;
    }

    public String getStringPathGambar() {
        return stringPathGambar;
    }

    public void setStringPathGambar(String stringPathGambar) {
        this.stringPathGambar = stringPathGambar;
    }

    public String getStringLogoPerusahaan() {
        return stringLogoPerusahaan;
    }

    public void setStringLogoPerusahaan(String stringLogoPerusahaan) {
        this.stringLogoPerusahaan = stringLogoPerusahaan;
    }

    public String getStringLogoMitra() {
        return stringLogoMitra;
    }

    public void setStringLogoMitra(String stringLogoMitra) {
        this.stringLogoMitra = stringLogoMitra;
    }

    public String getStringPathHasilLaporan() {
        return stringPathHasilLaporan;
    }

    public void setStringPathHasilLaporan(String stringPathHasilLaporan) {
        this.stringPathHasilLaporan = stringPathHasilLaporan;
    }

    public Workbook getWorkBook() {
        return workBook;
    }

    public void setWorkBook(Workbook workBook) {
        this.workBook = workBook;
    }

    public HSSFWorkbook getHssfworkBook() {
        return hssfworkBook;
    }

    public void setHssfworkBook(HSSFWorkbook hssfworkBook) {
        this.hssfworkBook = hssfworkBook;
    }

    public boolean isChangeProperty() {
        return changeProperty;
    }

    public void setChangeProperty(boolean changeProperty) {
        this.changeProperty = changeProperty;
    }

    public String getStringHeader() {
        return stringHeader;
    }

    public void setStringHeader(String stringHeader) {
        this.stringHeader = stringHeader;
    }

    public ArrayList<PrintedDataDua> getListDataDua() {
        return listDataDua;
    }

    public void setListDataDua(ArrayList<PrintedDataDua> listDataDua) {
        this.listDataDua = listDataDua;
    }
    
    public void generateWorkbook() throws IOException, BiffException{
        workBook = Workbook.getWorkbook(new File(stringExcelFile));
        hssfworkBook = new HSSFWorkbook(new FileInputStream(stringExcelFile));
    }
    
    public void loadReport(){
        if(listData.size() > 0){
            listData.clear();
            listDataDua.clear();
        }        
//        int sheetNumber = workBook.getNumberOfSheets();
//        for (int i = 0; i < sheetNumber; i++) {
//            Sheet sheet = workBook.getSheet(i);
//            extractSheet(sheet);
//       }
        
       int sheetNumberHss = hssfworkBook.getNumberOfSheets();
        HSSFSheet hsfSheet = hssfworkBook.getSheetAt(0);
        extractHssfSheet(hsfSheet);
    }
    
    public void extractHssfSheet(HSSFSheet hssfSheet){
        Iterator rowIter = hssfSheet.rowIterator();
        PDLinaExtractor pdExtractor = new PDLinaExtractor(stringPathGambar, stringLogoPerusahaan, stringLogoMitra, stringHeader);
        PDDLinaExtractor pddExtractor = new PDDLinaExtractor(stringPathGambar, stringLogoPerusahaan, stringLogoMitra, stringHeader);
        pdExtractor.setHssfWorkBook(hssfworkBook);
        
        while(rowIter.hasNext()){
            HSSFRow hssfrow = (HSSFRow) rowIter.next();
            pdExtractor.setRow(hssfrow);
            PrintedData pd = (PrintedData) pdExtractor.getData();
            pddExtractor.setPd(pd);
            PrintedDataDua pdd = (PrintedDataDua) pddExtractor.getData();
            listData.add(pd);
            listDataDua.add(pdd);
        }
        listData.remove(0);
        listDataDua.remove(0);
    }
    
    public void extractSheet(Sheet sheet){
        int row = sheet.getRows();
        PrintedDataExtractor reportEx = new PrintedDataExtractor(stringPathGambar, stringLogoPerusahaan, stringLogoMitra, stringHeader);
        for (int i = 1; i < row; i++) {
            PrintedData pd = reportEx.getData(sheet, i);
            if(pd == null){
                return;
            }
            PrintedDataDua pdd = reportEx.getPrintedDataDua(pd.getIdSite());
            listData.add(pd);
            listDataDua.add(pdd);
        }
    }
    
    public void previewAllReport() throws JRException{
        JasperPrint report = margeJasperPrint(listData, listDataDua, "site_report.jasper", "report_site_dua.jasper");
        JasperViewer jv = new JasperViewer(report, false);
        jv.setVisible(true);
    }
    
    public void previewReportAtIndex(int i) throws JRException{
        ArrayList<PrintedData> listPd = new ArrayList<>();
        ArrayList<PrintedDataDua> listPdd = new ArrayList<>();
        listPd.add(this.listData.get(i));
        listPdd.add(this.listDataDua.get(i));
        JasperPrint report = margeJasperPrint(listPd, listPdd, "site_report.jasper", "report_site_dua.jasper");
        JasperViewer jv = new JasperViewer(report, false);
        jv.setVisible(true);
    }
    
    private JasperPrint getJasperPrint(ArrayList list, String jasperFile) throws JRException{
        InputStream streamRep = JRLoader.getFileInputStream(jasperFile);
        JRDataSource jRDataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> map = new HashMap<>();
        map.put(JRParameter.REPORT_DATA_SOURCE, jRDataSource);
        JasperPrint report = JasperFillManager.fillReport(streamRep, map);
        return report;
    }
    
    private JasperPrint margeJasperPrint(ArrayList list1, ArrayList list2, String jasperFile1, String jasperFile2) throws JRException{
        JasperPrint jpMain;
        ArrayList listMain = new ArrayList();
        listMain.add(list1.get(0));
        
        jpMain = getJasperPrint(listMain,jasperFile1);
        JasperPrint jp1 = getJasperPrint(list1, jasperFile1);
        JasperPrint jp2 = getJasperPrint(list2, jasperFile2);
        
        List pagesOne = jp1.getPages();
        List pagesTwo = jp2.getPages();
        for(int i = 1; i < pagesOne.size(); i++){
            jpMain.addPage((JRPrintPage) pagesTwo.get(i-1));
            jpMain.addPage((JRPrintPage) pagesOne.get(i));
        }
        
        jpMain.addPage((JRPrintPage) pagesTwo.get(pagesTwo.size()-1));
        return jpMain;
    }
    
    
    public void generateAllReportToDirectory() throws JRException, FileNotFoundException{
        JasperPrint report = margeJasperPrint(listData, listDataDua,"site_report.jasper", "report_site_dua.jasper");
        if (!stringPathHasilLaporan.equals("")) {
            OutputStream os = new FileOutputStream(new File(stringPathHasilLaporan+"/laporan1.pdf"));
            JasperExportManager.exportReportToPdfStream(report, os);
            //JasperExportManager.exportReportToPdfStream(print, output);
        }
        
    }
    
}
