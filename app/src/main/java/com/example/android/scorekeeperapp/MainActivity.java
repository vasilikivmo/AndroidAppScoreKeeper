package com.example.android.scorekeeperapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.scorekeeperapp.R;

public class MainActivity extends AppCompatActivity {

    String type_set = "set";
    String type_point = "point";
    String team_a = "A";
    String team_b = "B";
    int score_a_points = 0;
    int score_a_sets = 0;
    int score_b_points = 0;
    int score_b_sets = 0;
    int add_value = 1;
    int max_points = 21;
    int max_points_tie = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addPointForTeamA(View v){
        calculateScore(team_a, type_point);
    }

    public void addSetForTeamA(View v){
        calculateScore(team_a, type_set);
    }

    public void addPointForTeamB(View v){
        calculateScore(team_b, type_point);
    }

    public void addSetForTeamB(View v){
        calculateScore(team_b, type_set);
    }

    public void resetPoints() {
        score_a_points = 0;
        score_b_points = 0;
    }

    public void checkWinTeam(int maxpoints) {
        if (score_a_points + score_b_points >= maxpoints && (Math.abs(score_a_points - score_b_points) != 1)) {
            if (score_a_points > score_b_points) {
                resetPoints();
                calculateScore(team_a, type_set);
            } else if (score_a_points < score_b_points){
                resetPoints();
                calculateScore(team_b, type_set);
            }
        }
    }

    /**
     * Calculates the score for each team (per point or set)
     */
    public void calculateScore(String team, String type) {
        TextView scoreView;

        if (type == type_set) {
            if (team == team_a && score_b_sets <= 1) {
                if (score_a_sets < 2) {
                    score_a_sets++;
                    if (score_a_sets == 2) {
                        displayWinner(team);
                    }
                }
                scoreView = (TextView) findViewById(R.id.team_a_sets);
                scoreView.setText(String.valueOf(score_a_sets));
            } else if (team == team_b && score_a_sets <= 1){
                if (score_b_sets < 2) {
                    score_b_sets++;
                    if (score_b_sets == 2) {
                        displayWinner(team);
                    }
                }
                scoreView = (TextView) findViewById(R.id.team_b_sets);
                scoreView.setText(String.valueOf(score_b_sets));
            }
        } else if (type == type_point) {
            if (team == team_a && score_b_sets <= 1) {
                if (score_a_sets == 1 && score_b_sets == 1 && score_a_points < max_points_tie) {
                    score_a_points++;
                    if (score_a_points == max_points_tie) {
                        resetPoints();
                        calculateScore(team_a, type_set);
                    } else {
                        checkWinTeam(max_points_tie);
                    }
                } else if (score_a_points < max_points && score_a_sets < 2) {
                    score_a_points++;
                    if (score_a_points == max_points) {
                        resetPoints();
                        calculateScore(team_a, type_set);
                    } else {
                        checkWinTeam(max_points);
                    }
                }
                scoreView = (TextView) findViewById(R.id.team_a_point);
                scoreView.setText(score_a_points + " Points");
            } else if (team == team_b && score_a_sets <= 1) {
                if (score_b_sets == 1 && score_a_sets == 1 && score_b_points < max_points_tie) {
                    score_b_points++;
                    if (score_b_points == max_points_tie) {
                        resetPoints();
                        calculateScore(team_b, type_set);
                    } else {
                        checkWinTeam(max_points_tie);
                    }
                } else if (score_b_points < max_points && score_b_sets < 2) {
                    score_b_points++;
                    if (score_b_points == max_points) {
                        resetPoints();
                        calculateScore(team_b, type_set);
                    } else {
                        checkWinTeam(max_points);
                    }
                }

            }
            TextView scoreViewA = (TextView) findViewById(R.id.team_a_point);
            TextView scoreViewB = (TextView) findViewById(R.id.team_b_point);
            scoreViewA.setText(score_a_points + " Points");
            scoreViewB.setText(score_b_points + " Points");
        }
    }

    /**
     * reset all scores from reset button
     */
    public void resetAll(View v) {
        resetData();
    }

    /**
     * reset all scores
     */
    public void resetData() {
        TextView pointsA = (TextView) findViewById(R.id.team_a_point);
        TextView setsA = (TextView) findViewById(R.id.team_a_sets);
        TextView pointsB = (TextView) findViewById(R.id.team_b_point);
        TextView setsB = (TextView) findViewById(R.id.team_b_sets);
        TextView winnerText = (TextView) findViewById(R.id.winner);

        score_a_points = score_a_sets = score_b_points = score_b_sets = 0;

        pointsA.setText(String.valueOf(score_a_points + " Points"));
        setsA.setText(String.valueOf(score_a_sets));
        pointsB.setText(score_b_points + " Points");
        setsB.setText(String.valueOf(score_b_sets));
        winnerText.setText("");
    }

    /**
     * display winner
     */
    public void displayWinner(String team) {
        TextView winner = (TextView) findViewById(R.id.winner);
        String winnerText = "Team "+team+" wins!";;
        winner.setText(winnerText);
    }


}
