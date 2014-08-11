/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fahmi.data;

/**
 *
 * @author fahmi
 */
public class PrintedDataDua {
    private String title;
    private String logoCompanny;
    private String logoMitra;
    private String imageTowerSatu;
    private String imageTowerDua;
    private String imagePanoramaSatu;
    private String imagePanoramaDua;
    private String imagePhotoLainSatu;
    private String imagePhotoLainDua;
    

    public PrintedDataDua(String title, String logoCompanny, String logoMitra, String imageTowerSatu, String imageTowerDua) {
        this.title = title;
        this.logoCompanny = logoCompanny;
        this.logoMitra = logoMitra;
        this.imageTowerSatu = imageTowerSatu;
        this.imageTowerDua = imageTowerDua;
    }

    public PrintedDataDua(String title, String logoCompanny, String logoMitra, String imageTowerSatu, String imageTowerDua, String imagePanoramaSatu, String imagePanoramaDua, String imagePhotoLainSatu, String imagePhotoLainDua) {
        this.title = title;
        this.logoCompanny = logoCompanny;
        this.logoMitra = logoMitra;
        this.imageTowerSatu = imageTowerSatu;
        this.imageTowerDua = imageTowerDua;
        this.imagePanoramaSatu = imagePanoramaSatu;
        this.imagePanoramaDua = imagePanoramaDua;
        this.imagePhotoLainSatu = imagePhotoLainSatu;
        this.imagePhotoLainDua = imagePhotoLainDua;
    }

    public PrintedDataDua() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogoCompanny() {
        return logoCompanny;
    }

    public void setLogoCompanny(String logoCompanny) {
        this.logoCompanny = logoCompanny;
    }

    public String getLogoMitra() {
        return logoMitra;
    }

    public void setLogoMitra(String logoMitra) {
        this.logoMitra = logoMitra;
    }

    public String getImageTowerSatu() {
        return imageTowerSatu;
    }

    public void setImageTowerSatu(String imageTowerSatu) {
        this.imageTowerSatu = imageTowerSatu;
    }

    public String getImageTowerDua() {
        return imageTowerDua;
    }

    public void setImageTowerDua(String imageTowerDua) {
        this.imageTowerDua = imageTowerDua;
    }

    public String getImagePanoramaSatu() {
        return imagePanoramaSatu;
    }

    public void setImagePanoramaSatu(String imagePanoramaSatu) {
        this.imagePanoramaSatu = imagePanoramaSatu;
    }

    public String getImagePanoramaDua() {
        return imagePanoramaDua;
    }

    public void setImagePanoramaDua(String imagePanoramaDua) {
        this.imagePanoramaDua = imagePanoramaDua;
    }

    public String getImagePhotoLainSatu() {
        return imagePhotoLainSatu;
    }

    public void setImagePhotoLainSatu(String imagePhotoLainSatu) {
        this.imagePhotoLainSatu = imagePhotoLainSatu;
    }

    public String getImagePhotoLainDua() {
        return imagePhotoLainDua;
    }

    public void setImagePhotoLainDua(String imagePhotoLainDua) {
        this.imagePhotoLainDua = imagePhotoLainDua;
    }
    
}
