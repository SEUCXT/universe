package com.seu.universe.config;

public enum RelationType {
    Friend("Friend"),
    Classmate("Classmate"),
    Collegue("Collegue"),
    Relative("Relative");

    public String type;

    RelationType(String type) {
        this.type = type;
    }
}
