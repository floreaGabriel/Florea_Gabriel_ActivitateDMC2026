package com.florea_gabriel.labs;

import android.graphics.Bitmap;

public class MagazinImagine {
    private String imageUrl;
    private String descriere;
    private String webUrl;
    private Bitmap bitmap;

    public MagazinImagine(String imageUrl, String descriere, String webUrl) {
        this.imageUrl = imageUrl;
        this.descriere = descriere;
        this.webUrl = webUrl;
        this.bitmap = null;
    }

    public String getImageUrl() { return imageUrl; }
    public String getDescriere() { return descriere; }
    public String getWebUrl() { return webUrl; }
    public Bitmap getBitmap() { return bitmap; }
    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }
}
