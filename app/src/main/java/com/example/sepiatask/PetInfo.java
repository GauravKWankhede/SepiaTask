package com.example.sepiatask;

public class PetInfo {
    private final String image_url;
    private final String title;
    private final String content_url;
    private final String date_added;

    public PetInfo(String image_url, String title, String content_url, String date_added) {
        this.image_url = image_url;
        this.title = title;
        this.content_url = content_url;
        this.date_added = date_added;
    }


    public String getImage_url() {return image_url;}

    public String getTitle() {return title;}

    public String getContent_url() {return content_url;}

    public String getDate_added() {return date_added;}

}
