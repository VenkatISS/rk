package com.rk.entities;

public class SwimTrainer {
    private String name;
    public SwimTrainer(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SwimTrainer) {
            return this.name.equals(((SwimTrainer) obj).name);
        }
        return false;
    }
}
