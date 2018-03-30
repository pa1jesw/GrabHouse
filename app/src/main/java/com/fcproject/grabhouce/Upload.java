package com.fcproject.grabhouce;

public class Upload {

    public String title;
    public String price;
    public String location;
    public String number;
    public String url;
    public String selection;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String title,String price,String location,String number,String selection, String url) {
        this.title=title;
        this.number=number;
        this.location=location;
        this.price=price;
        this.selection=selection;
        this.url= url;

    }

    public String getTitle() {
        return title;
    }
    public String getNumber() {
        return number;
    }
    public String getLocation() {
        return location;
    }
    public String getPrice() {
        return price;
    }
    public String getUrl() {
        return url;
    }
}

