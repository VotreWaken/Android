package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;

public class Game2048Activity extends AppCompatActivity {
    private Game2048Engine gameEngine;
    private GameBoardView gameBoardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game2048);

        gameEngine = new Game2048Engine();
        gameBoardView = findViewById(R.id.game_board);
        updateBoard();

        findViewById(R.id.game_board).setOnTouchListener(new OnSwipeListener(this) {
            @Override
            public void onSwipeBottom() {
                gameEngine.moveDown();
                updateBoard();
            }

            @Override
            public void onSwipeLeft() {
                gameEngine.moveLeft();
                updateBoard();
            }

            @Override
            public void onSwipeRight() {
                gameEngine.moveRight();
                updateBoard();
            }

            @Override
            public void onSwipeTop() {
                gameEngine.moveUp();
                updateBoard();
            }
        });
    }

    private void updateBoard() {
        gameBoardView.setGrid(gameEngine.getGrid());
        if (gameEngine.isGameWon()) {
            Toast.makeText(this, "You Win!", Toast.LENGTH_LONG).show();
        }
    }
}
