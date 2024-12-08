package com.example.androidapplication;

import java.util.Random;

public class Game2048Engine {
    private static final int GRID_SIZE = 4;
    private static final int WINNING_TILE = 2048;

    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];
    private Random random = new Random();
    private boolean gameWon = false;

    public Game2048Engine() {
        addRandomTile();
        addRandomTile();
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void moveLeft() {
        boolean moved = false;
        for (int i = 0; i < GRID_SIZE; i++) {
            moved |= slideAndMergeRow(grid[i]);
        }
        if (moved) addRandomTile();
    }

    public void moveRight() {
        boolean moved = false;
        for (int i = 0; i < GRID_SIZE; i++) {
            reverseArray(grid[i]);
            moved |= slideAndMergeRow(grid[i]);
            reverseArray(grid[i]);
        }
        if (moved) addRandomTile();
    }

    public void moveUp() {
        boolean moved = false;
        for (int col = 0; col < GRID_SIZE; col++) {
            int[] column = getColumn(col);
            moved |= slideAndMergeRow(column);
            setColumn(col, column);
        }
        if (moved) addRandomTile();
    }

    public void moveDown() {
        boolean moved = false;
        for (int col = 0; col < GRID_SIZE; col++) {
            int[] column = getColumn(col);
            reverseArray(column);
            moved |= slideAndMergeRow(column);
            reverseArray(column);
            setColumn(col, column);
        }
        if (moved) addRandomTile();
    }

    private boolean slideAndMergeRow(int[] row) {
        boolean moved = false;
        int[] compressed = new int[GRID_SIZE];
        int pos = 0;

        for (int num : row) {
            if (num != 0) compressed[pos++] = num;
        }

        for (int i = 0; i < GRID_SIZE - 1; i++) {
            if (compressed[i] != 0 && compressed[i] == compressed[i + 1]) {
                compressed[i] *= 2;
                if (compressed[i] == WINNING_TILE) gameWon = true;
                compressed[i + 1] = 0;
                moved = true;
            }
        }

        pos = 0;
        int[] merged = new int[GRID_SIZE];
        for (int num : compressed) {
            if (num != 0) merged[pos++] = num;
        }

        for (int i = 0; i < GRID_SIZE; i++) {
            if (row[i] != merged[i]) moved = true;
            row[i] = merged[i];
        }

        return moved;
    }

    private int[] getColumn(int col) {
        int[] column = new int[GRID_SIZE];
        for (int row = 0; row < GRID_SIZE; row++) {
            column[row] = grid[row][col];
        }
        return column;
    }

    private void setColumn(int col, int[] column) {
        for (int row = 0; row < GRID_SIZE; row++) {
            grid[row][col] = column[row];
        }
    }

    private void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    private void addRandomTile() {
        int emptyCount = 0;
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 0) emptyCount++;
            }
        }

        if (emptyCount == 0) return;

        int target = random.nextInt(emptyCount);
        int value = random.nextInt(10) < 9 ? 2 : 4;

        emptyCount = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (grid[row][col] == 0) {
                    if (emptyCount == target) {
                        grid[row][col] = value;
                        return;
                    }
                    emptyCount++;
                }
            }
        }
    }
}

