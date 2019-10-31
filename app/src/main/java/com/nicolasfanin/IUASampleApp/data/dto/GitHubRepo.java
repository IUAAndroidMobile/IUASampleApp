package com.nicolasfanin.IUASampleApp.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GitHubRepo {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    private Owner ownerDTO;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("description")
    private String description;
    @SerializedName("created_at")
    private Date createdAt;
    @SerializedName("updated_at")
    private Date updatedAt;
    @SerializedName("language")
    private String language;
    @SerializedName("open_issues_count")
    private String openIssues;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwnerDTO() {
        return ownerDTO;
    }

    public void setOwnerDTO(Owner ownerDTO) {
        this.ownerDTO = ownerDTO;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOpenIssues() {
        return openIssues;
    }

    public void setOpenIssues(String openIssues) {
        this.openIssues = openIssues;
    }
}
