package com.example.mahmoud.carsparepartsonlineshopping.models;

/**
 * Created by Elseedy on 10/25/2017.
 */

public class Model {
    String Model_id,Model_Name;

    public Model(String model_id, String model_Name) {
        Model_id = model_id;
        Model_Name = model_Name;
    }

    public String getModel_id() {
        return Model_id;
    }

    public String getModel_Name() {
        return Model_Name;
    }

    @Override
    public String toString() {
        return getModel_Name();
    }
}
