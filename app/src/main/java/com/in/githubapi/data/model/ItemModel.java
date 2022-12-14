package com.in.githubapi.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemModel implements Serializable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("html_url")
    @Expose
    private String html_url;
    @SerializedName("clone_url")
    @Expose
    private String clone_url;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("owner")
    @Expose
    private OwnerModel owners;
    @SerializedName("stargazers_count")
    @Expose
    private int star_count;

    private boolean isClicked;




    public ItemModel(String name, String html_url, String clone_url, String description, String language, OwnerModel owners, int star_count ) {
        this.name = name;
        this.html_url = html_url;
        this.clone_url = clone_url;
        this.description = description;
        this.language = language;
        this.owners = owners;
        this.star_count = star_count;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public int getStar_count() {
        return star_count;
    }

    public void setStar_count(int star_count) {
        this.star_count = star_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public OwnerModel getOwners() {
        return owners;
    }

    public void setOwners(OwnerModel owners) {
        this.owners = owners;
    }
}

