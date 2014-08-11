/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.reportmanager;

import com.fahmi.data.PrintedData;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;

/**
 *
 * @author fahmi
 */
public class PDLinaExtractor extends AbstractLinaDataExtractor{

        HSSFFormulaEvaluator evaluator;
    
    public PDLinaExtractor(String folderGambar, String folderLogoPerusahaan, String folderLogoMitra, String titleLaporan) {
        super(folderGambar, folderLogoPerusahaan, folderLogoMitra, titleLaporan);
    }

    @Override
    public Object getData() {
        PrintedData pd = new PrintedData();
        pd.setTitle("PENATAAN TOWER TELEKOMUNIKASI ‐ " + titleLaporan);
        Iterator cellIterator = getRow().cellIterator();
        int i = 0;
        cellIterator.hasNext();
        while(cellIterator.hasNext()){
            this.setDataToPrintedData(pd, i, (HSSFCell) cellIterator.next());
            String folderGambarAndId = folderGambar + slashString + pd.getIdSite() + slashString;
            String imageSatu = getGambar(folderGambarAndId + identitasGambar[0]);
            String imageDua = getGambar(folderGambarAndId + identitasGambar[1]);
            pd.setImageDetail1(imageSatu);
            pd.setImegeDetail2(imageDua);
            pd.setLogoImage(folderLogoPerusahaan);
            pd.setLogoMitra(folderLogoMitra);
            pd.setKeterangan("");
            i++;
        }
        return pd;
    }
    
    private void setDataToPrintedData(PrintedData pd, int columnNumber, HSSFCell cell){
        switch (columnNumber){
            case 0:
                pd.setIdSite(getStringValueOfCell(cell));
                break;
            case 1:
                pd.setPemilik(getStringValueOfCell(cell));
                break;
            case 3:
                pd.setAlamat(getStringValueOfCell(cell));
                break;
            case 4:
                pd.setKelurahan(getStringValueOfCell(cell));
                break;
            case 5:
                pd.setKecamatan(getStringValueOfCell(cell));
                break;
            case 6:
                pd.setLatitude(getStringValueOfCell(cell));
                break;
            case 7:
                pd.setLogitude(getStringValueOfCell(cell));
                break;
            case 8:
                pd.setXl(getStringValueOfCell(cell));
                break;
            case 9:
                pd.setIsatCdma(getStringValueOfCell(cell));
                break;
            case 10:
                pd.setIsatGsm(getStringValueOfCell(cell));
                break;
            case 11:
                pd.setSmartFren(getStringValueOfCell(cell));
                break;
            case 12:
                pd.setTsel(getStringValueOfCell(cell));
                break;
            case 13:
                pd.setHcpt(getStringValueOfCell(cell));
                break;
            case 14:
                pd.setNts(getStringValueOfCell(cell));
                break;
            case 15:
                pd.setFlexi(getStringValueOfCell(cell));
                break;
            case 16:
                pd.setEsia(getStringValueOfCell(cell));
                break;
            case 17:
                pd.setSti(getStringValueOfCell(cell));
                break;
            case 18:
                pd.setPenggunaTidakDiketahui(getValidNumber(getStringValueOfCell(cell)));
                break;
            case 19:
                pd.setOperatorPengguna(getStringValueOfCell(cell));
                break;
            case 20:
                pd.setStatusPenggunaan(getStatusPenggunaan(pd));
                System.out.println( "Status penggunaan " + pd.getStatusPenggunaan());
                break;
            case 21:
                pd.setTipeSite(getStringValueOfCell(cell));
                break;
            case 22:
                pd.setTipeTower(getStringValueOfCell(cell));
                break;
            case 23:
                pd.setTinggi(getValidNumber(getStringValueOfCell(cell)));
                break;
            case 24:
                pd.setShelterIndoor(getValidNumber(getStringValueOfCell(cell)));
                break;
            case 25:
                pd.setShelterOutdoor(getValidNumber(getStringValueOfCell(cell)));
                break;
            case 26:
                pd.setSektoral(getValidNumber(getStringValueOfCell(cell)));
                break;
            case 27:
                pd.setRadioKurang(getValidNumber(getStringValueOfCell(cell)));
                break;
            case 28:
                pd.setRadioLebih(getValidNumber(getStringValueOfCell(cell)));
                break;
            case 29:
                pd.setLuasTanah(getStringValueOfCell(cell));
                break;
            case 30:
                pd.setSiteCoverage(getStringValueOfCell(cell));
                break;
            case 31:
                pd.setEstetikaSekitarSite(getStringValueOfCell(cell));
                break;
            case 32:
                pd.setTanggalSurvey(getStringValueOfCell(cell));
                break;
        }
    }
    
    private String getStringValueOfCell(HSSFCell cell){
        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
             if (HSSFDateUtil.isCellDateFormatted(cell)){
                 SimpleDateFormat dateFormat = new SimpleDateFormat("d / MMM / yyy");
                 return dateFormat.format(cell.getDateCellValue());
             }
            return String.valueOf(cell.getNumericCellValue());
        }
        if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
          int i = (int) cell.getNumericCellValue();
          return String.valueOf(i);
        }
        else {
            return cell.getRichStringCellValue().getString();
        }
    }
    
    private String getValidNumber(String str){
        if (str.equalsIgnoreCase("")) {
            return "0";
        }
        String validStr = "";
        try{
            validStr = String.valueOf( ((int) Double.parseDouble(str)));
        }
        catch(NumberFormatException ex){
            System.out.println("Salah format");
        }
        return validStr;
    }

    public HSSFFormulaEvaluator getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(HSSFFormulaEvaluator evaluator) {
        this.evaluator = evaluator;
    }
    
    public String getStatusPenggunaan(PrintedData pd){
        int jml = 1;
        try{
            jml = Integer.parseInt(pd.getOperatorPengguna());
        }
        catch( NumberFormatException ex){
            System.out.println("ini string");
        }
        return jml > 1 ? "Multi Operator" : "Single Operator";
    }
    
}
