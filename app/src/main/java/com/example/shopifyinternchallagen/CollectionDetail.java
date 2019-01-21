package com.example.shopifyinternchallagen;

public class CollectionDetail {
    private String name;
    private int invent;
    private String collectName;
    private String imgProduct;
    private String type;

    public CollectionDetail(String name, int invent, String collectName, String imgProduct, String type) {
        this.name = name;
        this.invent = invent;
        this.collectName = collectName;
        this.imgProduct = imgProduct;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInvent() {
        return invent;
    }

    public void setInvent(int invent) {
        this.invent = invent;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }
}
