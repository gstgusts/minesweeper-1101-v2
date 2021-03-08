package com.example.minesweeper11011.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class MineGenerator {

    private final int[][] fields;

    @Getter
    private int numberOfMines;

    public void generate() {
        setMineNumber();
        generateMines();
        calculateNumbers();
    }

    private void setMineNumber() {
        if (fields.length >= 5 && fields.length < 10) {
            numberOfMines = 3;
            return;
        }

        if (fields.length >= 10 && fields.length < 15) {
            numberOfMines = 10;
            return;
        }

        if (fields.length >= 15 && fields.length < 20) {
            numberOfMines = 15;
            return;
        }

        numberOfMines = 0;
    }

    private void generateMines() {
        int minesGenerated = 0;
        Random random = new Random();
        while (minesGenerated < numberOfMines) {
            int x = random.nextInt(fields.length);
            int y = random.nextInt(fields[0].length);
            if (fields[x][y] == Board.FIELD_IS_EMPTY) {
                fields[x][y] = Board.MINE_VALUE;
                minesGenerated++;
            }
        }
    }

    private void calculateNumbers() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                if (fields[i][j] == Board.MINE_VALUE) {
                    updateAdjacentFields(i, j);
                }
            }
        }
    }

    private void updateAdjacentFields(int i, int j) {

        int[] iValues = new int[]{1, 1, 1, -1, -1, -1, 0, 0};
        int[] jValues = new int[]{1, 0, -1, 1, 0, -1, 1, -1};

        for (int k = 0; k < 8; k++) {

            var rowIndex = i + iValues[k];
            var columnIndex = j + jValues[k];

            if(rowIndex < 0 || rowIndex >= fields.length || columnIndex < 0 || columnIndex >= fields.length) {
                continue;
            }

            if (fields[rowIndex][columnIndex] != Board.MINE_VALUE) {
                fields[rowIndex][columnIndex] += 1;
            }
        }
    }
}
