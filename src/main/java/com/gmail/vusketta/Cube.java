package com.gmail.vusketta;

import java.util.Arrays;
import java.util.Random;

public class Cube {
    private final int FACES_NUMBER = 6;
    private final int CUBE_SIZE = 3;
    private final Cell[][][] cells = new Cell[FACES_NUMBER][CUBE_SIZE][CUBE_SIZE];

    public Cube() {
        for (int i = 0; i < FACES_NUMBER; i++) {
            final int finalI = i;
            Arrays.stream(cells[i]).forEach(a -> Arrays.fill(a, Cell.valueOf(finalI)));
        }
    }

    public void move(final Direction direction, final int shift, final int index) {
        switch (direction) {
            case UP -> {
                for (int i = 0; i < shift; i++) up(index);
            }
            case DOWN -> {
                for (int i = 0; i < shift; i++) down(index);
            }
            case RIGHT -> {
                for (int i = 0; i < shift; i++) right(index);
            }
            case LEFT -> {
                for (int i = 0; i < shift; i++) left(index);
            }
        }
    }

    public Cell[][] getFace(final int face) {
        return cells[face];
    }

    private void up(final int col) {
        swapCols(col, true);
        if (col == 0) rotateCounterСlockwise(1);
        if (col == CUBE_SIZE - 1) rotateСlockwise(3);
    }

    private void down(final int col) {
        swapCols(col, false);
        if (col == 0) rotateСlockwise(1);
        if (col == CUBE_SIZE - 1) rotateCounterСlockwise(3);
    }

    private void right(final int row) {
        swapRows(row, true);
        if (row == 0) rotateCounterСlockwise(1);
        if (row == CUBE_SIZE - 1) rotateСlockwise(3);
    }

    private void left(final int row) {
        swapRows(row, false);
        if (row == 0) rotateСlockwise(1);
        if (row == CUBE_SIZE - 1) rotateCounterСlockwise(3);
    }

    private void swapRows(final int row, final boolean isRight) {
        final Cell[] t1 = cells[1][row].clone();
        final Cell[] t2 = cells[2][row].clone();
        final Cell[] t3 = cells[3][row].clone();
        final Cell[] t4 = cells[4][row].clone();
        cells[1][row] = isRight ? t4 : t2;
        cells[2][row] = isRight ? t1 : t3;
        cells[3][row] = isRight ? t2 : t4;
        cells[4][row] = isRight ? t3 : t1;
    }

    private void swapCols(final int col, final boolean isUp) {
        final Cell[][] t1 = new Cell[CUBE_SIZE][CUBE_SIZE];
        final Cell[][] t2 = new Cell[CUBE_SIZE][CUBE_SIZE];
        final Cell[][] t3 = new Cell[CUBE_SIZE][CUBE_SIZE];
        final Cell[][] t4 = new Cell[CUBE_SIZE][CUBE_SIZE];
        for (int i = 0; i < CUBE_SIZE; i++) {
            t1[i] = cells[0][i].clone();
            t2[i] = cells[2][i].clone();
            t3[i] = cells[5][i].clone();
            t4[i] = cells[4][i].clone();
        }
        for (int row = 0; row < CUBE_SIZE; row++) {
            t1[row][col] = cells[isUp ? 2 : 4][row][col];
            t2[row][col] = cells[isUp ? 5 : 0][row][col];
            t3[row][col] = cells[isUp ? 4 : 2][row][col];
            t4[row][col] = cells[isUp ? 0 : 5][row][col];
        }
        cells[0] = t1;
        cells[2] = t2;
        cells[5] = t3;
        cells[4] = t4;
    }

    private void rotateСlockwise(final int face) {
        final Cell[][] transposed = transpose(face);
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE / 2; j++) {
                final Cell temp = transposed[i][j];
                transposed[i][j] = cells[face][i][CUBE_SIZE - j - 1];
                cells[face][i][CUBE_SIZE - j - 1] = temp;
            }
        }
        cells[face] = transposed;
    }

    private void rotateCounterСlockwise(final int face) {
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE / 2; j++) {
                final Cell temp = cells[face][i][j];
                cells[face][i][j] = cells[face][i][CUBE_SIZE - j - 1];
                cells[face][i][CUBE_SIZE - j - 1] = temp;
            }
        }
        cells[face] = transpose(face);
    }

    private Cell[][] transpose(final int face) {
        final Cell[][] temp = new Cell[CUBE_SIZE][CUBE_SIZE];
        for (int i = 0; i < CUBE_SIZE; i++) {
            for (int j = 0; j < CUBE_SIZE; j++) {
                temp[i][j] = cells[face][j][i];
            }
        }
        return temp;
    }

    public boolean isSolved() {
        return equals(new Cube());
    }

    public int getSize() {
        return CUBE_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cube that) return Arrays.equals(cells, that.cells);
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < CUBE_SIZE; i++) {
            sb.append('\n' );
            for (int j = 0; j < 2 * CUBE_SIZE; j++) sb.append(' ' );
            for (int j = 0; j < CUBE_SIZE; j++) sb.append(cells[0][i][j]).append(' ' );
        }
        for (int i = 0; i < CUBE_SIZE; i++) {
            sb.append('\n' );
            for (int k = 1; k < FACES_NUMBER - 1; k++) {
                for (int j = 0; j < CUBE_SIZE; j++) sb.append(cells[k][i][j]).append(' ' );
            }
        }
        for (int i = 0; i < CUBE_SIZE; i++) {
            sb.append('\n' );
            for (int j = 0; j < 2 * CUBE_SIZE; j++) sb.append(' ' );
            for (int j = 0; j < CUBE_SIZE; j++) sb.append(cells[FACES_NUMBER - 1][i][j]).append(' ' );
        }
        return sb.toString();
    }
}
