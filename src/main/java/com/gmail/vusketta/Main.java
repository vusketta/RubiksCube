package com.gmail.vusketta;

public class Main {
    public static void main(String[] args) {
        Cube cube1 = CubeFactory.create();
        System.out.println(cube1);
        Cube cube2 = CubeFactory.randomCube();
        System.out.println(cube2);
    }
}