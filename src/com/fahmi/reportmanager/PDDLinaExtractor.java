/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.reportmanager;

import com.fahmi.data.PrintedData;
import com.fahmi.data.PrintedDataDua;
import java.util.Iterator;

/**
 *
 * @author fahmi
 */
public class PDDLinaExtractor extends AbstractLinaDataExtractor{

    private PrintedData pd;
    
    public PDDLinaExtractor(String folderGambar, String folderLogoPerusahaan, String folderLogoMitra, String titleLaporan) {
        super(folderGambar, folderLogoPerusahaan, folderLogoMitra, titleLaporan);
    }

    public PDDLinaExtractor(PrintedData pd, String folderGambar, String folderLogoPerusahaan, String folderLogoMitra, String titleLaporan) {
        super(folderGambar, folderLogoPerusahaan, folderLogoMitra, titleLaporan);
        this.pd = pd;
    }
   
    @Override
    public Object getData() {
        PrintedDataDua pdd = new PrintedDataDua();
        pdd.setTitle("PENATAAN TOWER TELEKOMUNIKASI ‐ "+titleLaporan);
        String pathImg = folderGambar + slashString + pd.getIdSite() + slashString;
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

    public PrintedData getPd() {
        return pd;
    }

    public void setPd(PrintedData pd) {
        this.pd = pd;
    }
    
    
    
}
