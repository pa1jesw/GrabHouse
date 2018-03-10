package com.fcproject.grabhouce;

/**
 * Created by MUKESH on 09-03-2018.
 */

public class ImageUpload {
    public String name;
    public String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    public ImageUpload(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
