package com.boostinsider.android.data.campaign;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by qiongchen on 6/5/16.
 */
public class Campaign implements Serializable {

    @Expose
    String name;

    @Expose
    String description;

    @Expose
    String instruction;

    @Expose
    String url;

    @Expose
    String status;

    @Expose
    String cpcDisplay;

    @Expose
    String cpiDisplay;

    @SerializedName("budget_left_display")
    @Expose
    String budgetLeftDisplay;

    @SerializedName("image_display")
    @Expose
    String imageDisplay;

    @Expose
    boolean locked;

    @Expose
    String[] countries;

    @SerializedName("type_display")
    @Expose
    String typeDisplay;

    @SerializedName("conversion_name")
    @Expose
    String conversionName;

    @Expose
    ArrayList<Photo> photos;

    @Expose
    int id;

    public Campaign(String name, String description, String instruction, String url, String status, String cpcDisplay, String cpiDisplay, String budgetLeftDisplay, String imageDisplay, boolean locked, String[] countries, String typeDisplay, String conversionName, ArrayList<Photo> photos, int id) {
        this.name = name;
        this.description = description;
        this.instruction = instruction;
        this.url = url;
        this.status = status;
        this.cpcDisplay = cpcDisplay;
        this.cpiDisplay = cpiDisplay;
        this.budgetLeftDisplay = budgetLeftDisplay;
        this.imageDisplay = imageDisplay;
        this.locked = locked;
        this.countries = countries;
        this.typeDisplay = typeDisplay;
        this.conversionName = conversionName;
        this.photos = photos;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCpcDisplay() {
        return cpcDisplay;
    }

    public void setCpcDisplay(String cpcDisplay) {
        this.cpcDisplay = cpcDisplay;
    }

    public String getCpiDisplay() {
        return cpiDisplay;
    }

    public void setCpiDisplay(String cpiDisplay) {
        this.cpiDisplay = cpiDisplay;
    }

    public String getBudgetLeftDisplay() {
        return budgetLeftDisplay;
    }

    public void setBudgetLeftDisplay(String budgetLeftDisplay) {
        this.budgetLeftDisplay = budgetLeftDisplay;
    }

    public String getImageDisplay() {
        return imageDisplay;
    }

    public void setImageDisplay(String imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String getTypeDisplay() {
        return typeDisplay;
    }

    public void setTypeDisplay(String typeDisplay) {
        this.typeDisplay = typeDisplay;
    }

    public String getConversionName() {
        return conversionName;
    }

    public void setConversionName(String conversionName) {
        this.conversionName = conversionName;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}