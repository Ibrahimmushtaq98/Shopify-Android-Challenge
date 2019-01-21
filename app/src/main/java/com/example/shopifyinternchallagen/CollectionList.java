package com.example.shopifyinternchallagen;

public class CollectionList {
    private String body;
    private String id;
    private String handle;
    private String title;
    private String image;

    CollectionList(String id, String handle, String title, String body,String image){
        this.id = id;
        this.handle = handle;
        this.title = title;
        this.image = image;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
