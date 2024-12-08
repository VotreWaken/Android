package com.example.androidapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GameBoardView extends View {
    private int[][] grid;
    private Paint paint;

    public GameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(64);
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (grid == null) return;

        int cellSize = getWidth() / grid.length;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int value = grid[row][col];
                paint.setColor(value == 0 ? 0xFFDDDDDD : 0xFFFFA500);
                canvas.drawRect(
                        col * cellSize, row * cellSize,
                        (col + 1) * cellSize, (row + 1) * cellSize,
                        paint
                );
                if (value != 0) {
                    paint.setColor(0xFF000000);
                    canvas.drawText(
                            String.valueOf(value),
                            (col + 0.5f) * cellSize,
                            (row + 0.5f) * cellSize + paint.getTextSize() / 3,
                            paint
                    );
                }
            }
        }
    }
}
