package com.veterinaryclinic.ui.fragments.home.model;

/**
 * The data set from pet URL
 */
public class PetData {
    private String imageUrl;
    private String title;
    private String contentUrl;
    private String dateAdded;

    public PetData(String imageUrl, String title, String contentUrl, String dateAdded) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.contentUrl = contentUrl;
        this.dateAdded = dateAdded;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
