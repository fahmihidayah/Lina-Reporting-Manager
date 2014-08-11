/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.reportmanager;

import com.fahmi.os_utils.OsUtils;
import com.fahmi.report_helper.DataExtractor;
import java.io.File;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author fahmi
 */
public abstract class AbstractLinaDataExtractor implements DataExtractor{

    protected String titleLaporan;
    protected String folderGambar;
    protected String folderLogoPerusahaan;
    protected String folderLogoMitra;
    protected String slashString;
    protected String tempKecamatan;
    protected HSSFRow row;
    protected HSSFWorkbook hssfWorkBook;
    
    
    public final String [] identitasGambar = {"site1", "site2","tower1", 
                                                "tower2", "panorama1", "panorama2", 
                                                "lain1","lain2"};
    
    public AbstractLinaDataExtractor(String folderGambar, String folderLogoPerusahaan, String folderLogoMitra, String titleLaporan) {
        this.folderGambar = folderGambar;
        this.folderLogoPerusahaan = folderLogoPerusahaan;
        this.folderLogoMitra = folderLogoMitra;
        this.titleLaporan = titleLaporan;
        if (OsUtils.isWindows()) {
             slashString = "\\";
        }
        else{
            slashString = "/";
        }
        folderGambar = folderGambar + slashString;
        System.out.println(folderGambar);
    }
    
    public String getGambar(String gambar){
        File file = new File(gambar + ".jpg");
        if (file.exists()) {
            System.out.println(gambar + ".jpg");
            return gambar + ".jpg";
        }
        file =  new File(gambar + ".JPG");
        if (file.exists()) {
            System.out.println(gambar + ".JPG");
            return gambar + ".JPG";
        }
        else {   
            return "blank.JPG";
        }
    }

    public HSSFRow getRow() {
        return row;
    }

    public void setRow(HSSFRow row) {
        this.row = row;
    }

    public HSSFWorkbook getHssfWorkBook() {
        return hssfWorkBook;
    }

    public void setHssfWorkBook(HSSFWorkbook hssfWorkBook) {
        this.hssfWorkBook = hssfWorkBook;
    }
    
}
