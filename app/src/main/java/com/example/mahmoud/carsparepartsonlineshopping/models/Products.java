package com.example.mahmoud.carsparepartsonlineshopping.models;

import java.io.Serializable;

/**
 * Created by osman on 6/1/2018.
 */

public class Products extends NodeId {

    String category, image, manu, model, name, price, subCategory, subModel, year;

    public Products() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getSubModel() {
        return subModel;
    }

    public void setSubModel(String subModel) {
        this.subModel = subModel;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Products(String category, String image, String manu, String model, String name, String price, String subCategory, String subModel, String year) {
        this.category = category;
        this.image = image;
        this.manu = manu;
        this.model = model;
        this.name = name;
        this.price = price;
        this.subCategory = subCategory;
        this.subModel = subModel;
        this.year = year;
    }
}

class NodeId implements Serializable {
    public String id;

    public <T extends NodeId> T withId(String id) {
        this.id = id;
        return (T) this;
    }
}

