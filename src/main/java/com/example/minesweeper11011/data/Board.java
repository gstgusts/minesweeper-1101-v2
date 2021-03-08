package com.example.minesweeper11011.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Board {

    public static final int MINE_VALUE = -1;
    public static final int FIELD_IS_EMPTY = 0;
    public static final int FIELD_CLOSED = -2;
    public static final int FIELD_FLAGGED = -3;

    private int[][] fields;
    private FieldStatus[][] overlay;

    private final int size;
    private MineGenerator mineGenerator;

    public void initBoard() {
        fields = new int[size][size];
        overlay = new FieldStatus[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields[i][j] = FIELD_IS_EMPTY;
                overlay[i][j] = FieldStatus.CLOSED;
            }
        }

        mineGenerator = new MineGenerator(fields);
        mineGenerator.generate();
    }

    public boolean checkField(int row, int column) {
        if (row > size - 1 || row < 0 || column > size - 1 || column < 0) {
            throw new IllegalArgumentException("Values are outside of bounds");
        }

        overlay[row][column] = FieldStatus.OPENED;

        return fields[row][column] != MINE_VALUE;
    }

    public boolean isOpen() {
        var mines = mineGenerator.getNumberOfMines();

        var closedFields = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (overlay[i][j] == FieldStatus.CLOSED) {
                    ++closedFields;
                }
            }
        }

        return mines == closedFields;
    }

    public int getFieldInfo(int row, int column) {
        if (overlay[row][column] == FieldStatus.CLOSED) {
            return FIELD_CLOSED;
        }

        if(overlay[row][column] == FieldStatus.FLAGGED) {
            return FIELD_FLAGGED;
        }

        return fields[row][column];
    }
}
