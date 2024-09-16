package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameActivity extends AppCompatActivity {

    boolean gameEnd = false;
    int numberOfTurns = 0;

    // Player representation and Game state :
    // 0 = O
    // 1 = X
    //-1 = Null

    int activePlayer = 0;

    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    final int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public void playerTap(View view) {

        if (gameEnd) {
            return;
        }

        numberOfTurns++;

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        img.setTranslationY(-1000f);

        if (gameState[tappedImage] == -1) {

            gameState[tappedImage] = activePlayer;

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.img_o);

                TextView statusBar = findViewById(R.id.textViewStatus);
                statusBar.setText("X's Turn");

                activePlayer = 1;

            } else {
                img.setImageResource(R.drawable.img_x);

                TextView statusBar = findViewById(R.id.textViewStatus);
                statusBar.setText("O's Turn");

                activePlayer = 0;
            }

            img.animate().translationYBy(1000f).setDuration(150);

        }

        // Checking if any one won or not

        for (int[] winPosition : winPositions) {

            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[0]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != -1) {

                gameEnd = true;

                if (gameState[winPosition[0]] == 0) {
                    TextView statusBar = findViewById(R.id.textViewStatus);
                    statusBar.setText("'O' has Won\nTap Here to Play Again\n ↺");
                } else {
                    TextView statusBar = findViewById(R.id.textViewStatus);
                    statusBar.setText("'X' has Won\nTap Here to Play Again\n↺");
                }
            }
        }

        // Checking if all the turns end or not
        if (numberOfTurns == 9 && !gameEnd) {
            gameEnd = true;
            TextView statusBar = findViewById(R.id.textViewStatus);
            statusBar.setText("Tied\nTap Here to Play Again\n↺");

        }


    }


    public void gameReset(View view) {

        if (!gameEnd) {
            return;
        }

        activePlayer = 0;
        numberOfTurns = 0;
        gameEnd = false;

        // Reset gameState
        for (int i = 0; i < 9; ++i) {
            gameState[i] = -1;
        }

        // Clearing the images of Grid. (Setting the resource ID to 0  might be intended to clear the image displayed by the ImageView)
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.textViewStatus);
        status.setText("O's Turn \n Tap to play");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

}