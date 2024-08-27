package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final int PLAY_CODE = 1;
    public final int VIEW_CODE = 2;

    Game game = new Game();

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i;
            switch (v.getId()) {
                case R.id.play:
                    i = new Intent(MainActivity.this, PlayMenu.class);
                    startActivityForResult(i, PLAY_CODE);
                    startActivity(i);
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(
        int requestCode,
        int resultCode,
        Intent data
    ) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PLAY_CODE:
                    int Play[] = new int[game.action];
                    Play = data.getIntArrayExtra("play");
                    for (int j = 0; j < game.action; j++) {
                        if (Play[j] != 0) {
                            if (
                                Player1.hand[Play[j]].spell == 3 ||
                                Player1.hand[Play[j]].spell == 4
                            ) {
                                Intent i = new Intent(this, ViewMenu.class);
                                i.putExtra("type", Play[j]);
                                startActivity(i);
                            } else {
                                Player1.hand[Play[j]].PlayCard(1);
                            }
                            Player1.hand[Play[j]] = null;
                        }
                    }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView round = (TextView) findViewById(R.id.round);
        TextView[] cards = new TextView[3];
        Button play = (Button) findViewById(R.id.play);
        cards[0] = (TextView) findViewById(R.id.card1);
        cards[1] = (TextView) findViewById(R.id.card2);
        cards[2] = (TextView) findViewById(R.id.card3);
        TextView hp1 = (TextView) findViewById(R.id.your_hp);
        TextView hp2 = (TextView) findViewById(R.id.enemy_hp);
        TextView armor1 = (TextView) findViewById(R.id.your_armor);
        TextView armor2 = (TextView) findViewById(R.id.enemy_armor);

        game.StartGame();
        while (game.EndGame() == false) {
            hp1.setText(Player1.hp);
            hp2.setText(Player2.hp);
            armor1.setText(Player1.armor);
            armor2.setText(Player2.armor);
            round.setText(game.round);

            for (int i = 0; i <= game.action; i++) {
                cards[i].setText(Player1.hand[i].id);
            }
            play.setOnClickListener(listener);

            for (int i = 0; i < game.action; i++) {
                Player2.hand[i].PlayCard(2);
            }

            game.StartNewTurn();
        }
    }
}
