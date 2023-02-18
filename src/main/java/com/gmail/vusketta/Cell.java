package com.gmail.vusketta;

public enum Cell {
    WHITE("W"), GREEN("G"), RED("R"), BLUE("B"), ORANGE("O"), YELLOW("Y");
    private final String str;

    Cell(String str) {
        this.str = str;
    }

    public static Cell valueOf(int i) {
        return values()[i];
    }

    @Override
    public String toString() {
        return str;
    }
}
