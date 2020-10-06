package com.hmeng.calculateforme.Data;

public class ServiceModel {
    private String name;
    private String description;
    private String iconURL;

    public ServiceModel() {
    }

    public ServiceModel(String name, String description, String iconURL) {
        this.name = name;
        this.description = description;
        this.iconURL = iconURL;
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

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
}
