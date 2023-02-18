package com.gmail.vusketta;

import java.util.Random;

public class CubeFactory {
    public static Cube create() {
        return new Cube();
    }

    public static Cube randomCube() {
        final Cube random = new Cube();
        final Random randomEngine = new Random();
        Direction direction;
        for (int i = 0; i < 100; i++) {
            direction = switch (randomEngine.nextInt(4)) {
                case 0 -> Direction.UP;
                case 1 -> Direction.DOWN;
                case 2 -> Direction.RIGHT;
                case 3 -> Direction.LEFT;
                default -> throw new IllegalStateException("Unexpected value: " + randomEngine.nextInt(3));
            };
            random.move(direction, randomEngine.nextInt(4), randomEngine.nextInt(random.getSize()));
        }
        return random;
    }
}
