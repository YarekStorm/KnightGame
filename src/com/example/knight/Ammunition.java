package com.example.knight;

import javafx.beans.property.*;

public class Ammunition {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty type = new SimpleStringProperty();
    private final DoubleProperty weight = new SimpleDoubleProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();

    public Ammunition(String name, String type, double weight, double price) {
        this.name.set(name);
        this.type.set(type);
        this.weight.set(weight);
        this.price.set(price);
    }

    public String getName() { return name.get(); }
    public String getType() { return type.get(); }
    public double getWeight() { return weight.get(); }
    public double getPrice() { return price.get(); }

    public StringProperty nameProperty() { return name; }
    public StringProperty typeProperty() { return type; }
    public DoubleProperty weightProperty() { return weight; }
    public DoubleProperty priceProperty() { return price; }
}
