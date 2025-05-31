package com.example.knight;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Knight {
    private final String name;
    private final ObservableList<Ammunition> equipment = FXCollections.observableArrayList();

    public Knight(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ObservableList<Ammunition> getEquipment() {
        return equipment;
    }

    public void addAmmunition(Ammunition item) {
        equipment.add(item);
    }

    public void removeAmmunition(Ammunition item) {
        equipment.remove(item);
    }
}
