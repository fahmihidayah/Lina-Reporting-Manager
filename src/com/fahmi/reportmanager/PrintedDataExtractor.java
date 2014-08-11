/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.reportmanager;

import com.fahmi.data.PrintedData;
import com.fahmi.data.PrintedDataDua;
import com.fahmi.os_utils.OsUtils;
import com.fahmi.report_helper.ReportExtractor;
import java.io.File;
import jxl.Sheet;

/**
 *
 * @author fahmi
 */
public class PrintedDataExtractor implements ReportExtractor<PrintedData>{

    private String titleLaporan;
    private String folderGambar;
    private String folderLogoPerusahaan;
    private String folderLogoMitra;
    public String slashString;
    
    public final String [] identitasGambar = {"site1", "site2","tower1", 
                                                "tower2", "panorama1", "panorama2", 
                                                "lain1","lain2"};

    public String tempKecamatan;
    
    public PrintedDataExtractor(String folderGambar, String folderLogoPerusahaan, String folderLogoMitra, String titleLaporan) {
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
    
    
    
    @Override
    public PrintedData getData(Sheet sheet, int iRow) {
        if(getContentCell(sheet, 1, iRow).equalsIgnoreCase("")){
            return null;
        }
        PrintedData pd = new PrintedData();
        pd.setTitle("PENATAAN TOWER TELEKOMUNIKASI ‐ " + titleLaporan);
        pd.setIdSite(getContentCell(sheet, 1, iRow));
        pd.setPemilik(getContentCell(sheet, 1, iRow));
        pd.setAlamat(getContentCell(sheet, 3, iRow));
        pd.setKelurahan(getContentCell(sheet, 4, iRow));
        pd.setKecamatan(getContentCell(sheet, 5, iRow));
        pd.setLatitude(getContentCell(sheet, 6, iRow));
        pd.setLogitude(getContentCell(sheet, 7, iRow));
        pd.setXl(getContentCell(sheet, 8, iRow));
        pd.setIsatCdma(getContentCell(sheet, 9, iRow));
        pd.setIsatGsm(getContentCell(sheet, 10, iRow));
        pd.setSmartFren(getContentCell(sheet, 11, iRow));
        pd.setTsel(getContentCell(sheet, 12, iRow));
        pd.setHcpt(getContentCell(sheet, 13, iRow));
        pd.setNts(getContentCell(sheet, 14, iRow));
        pd.setSti(getContentCell(sheet, 17, iRow));
        pd.setFlexi(getContentCell(sheet, 15, iRow));
        pd.setEsia(getContentCell(sheet, 16, iRow));
        pd.setPenggunaTidakDiketahui(getContentCell(sheet, 18, iRow));
        pd.setOperatorPengguna(getContentCell(sheet, 19, iRow));        
        pd.setTipeSite(getContentCell(sheet, 21, iRow));
        pd.setTipeTower(getContentCell(sheet, 22, iRow));
        pd.setTinggi(getContentCell(sheet, 23, iRow));
        pd.setShelterIndoor(getContentCell(sheet, 24, iRow));
        pd.setShelterOutdoor(getContentCell(sheet, 25, iRow));
        pd.setSektoral(getContentCell(sheet, 26, iRow));
        pd.setRadioKurang(getContentCell(sheet, 27, iRow));
        pd.setRadioLebih(getContentCell(sheet, 28, iRow));
        pd.setLuasTanah(getContentCell(sheet, 29, iRow));
        //pd.setStatusPenggunaan("");
        System.out.println(pd.getOperatorPengguna());
        //pd.setStatusPenggunaan(getContentCell(sheet, 20, iRow));
        try{
            
            int jumlah = Integer.parseInt(pd.getOperatorPengguna());
            String statusPenggunaan = (jumlah > 1 ? "Multi Operator":"Single Operator");
            pd.setStatusPenggunaan(statusPenggunaan);
        }catch(NumberFormatException ex){
            pd.setStatusPenggunaan("");
        }
       
        
        System.out.println(pd.getStatusPenggunaan());
        pd.setTanggalSurvey(getContentCell(sheet, 32, iRow));//pd.setTanggalSurvey(getContentCell(sheet, 34, iRow));
        pd.setKeterangan("");//pd.setKeterangan(getContentCell(sheet, 33, iRow));
        pd.setSiteCoverage(getContentCell(sheet, 30, iRow));
        pd.setEstetikaSekitarSite(getContentCell(sheet, 31, iRow));
        String folderGambarAndId = folderGambar + slashString + pd.getIdSite() + slashString;
        String imageSatu = getGambar(folderGambarAndId + identitasGambar[0]);
        String imageDua = getGambar(folderGambarAndId + identitasGambar[1]);
        pd.setImageDetail1(imageSatu);
        pd.setImegeDetail2(imageDua);
        pd.setLogoImage(folderLogoPerusahaan);
        pd.setLogoMitra(folderLogoMitra);
        return pd;
    }
    
    public PrintedDataDua getPrintedDataDua(String idSite){
        PrintedDataDua pdd = new PrintedDataDua();
        pdd.setTitle("PENATAAN TOWER TELEKOMUNIKASI ‐ "+titleLaporan);
        String pathImg = folderGambar + slashString + idSite + slashString;
        pdd.setImageTowerSatu(getGambar(pathImg + identitasGambar[2]));
        pdd.setImageTowerDua(getGambar(pathImg + identitasGambar[3]));
        pdd.setImagePanoramaSatu(getGambar(pathImg + identitasGambar[4]));
        pdd.setImagePanoramaDua(getGambar(pathImg + identitasGambar[5]));
        pdd.setImagePhotoLainSatu(getGambar(pathImg + identitasGambar[6]));
        pdd.setImagePhotoLainDua(getGambar(pathImg + identitasGambar[7]));        
        pdd.setLogoCompanny(folderLogoPerusahaan);
        pdd.setLogoMitra(folderLogoMitra);
        return pdd;
    }
    
    public String getContentCell(Sheet sheet, int iCol, int iRow){
        return sheet.getCell(iCol, iRow).getContents();
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
           // System.out.println("Gambar tidak ada. path : " + gambar + ".jpg");
            return "blank.JPG";
        }
    }
    
    
}
