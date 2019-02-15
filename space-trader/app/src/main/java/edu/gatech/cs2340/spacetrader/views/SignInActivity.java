package edu.gatech.cs2340.spacetrader.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.viewmodels.SignInViewModel;


public class SignInActivity extends AppCompatActivity {

    private SignInViewModel viewModel;

    /*
     WIDGETS FOR BINDING AND GETTING INFORMATION
    */
    private EditText nameFeild;
    private SeekBar difficultySlider;
    private Spinner skill1Spinner;
    private Spinner skill2Spinner;
    private Spinner skill3Spinner;
    private Spinner skill4Spinner;

    // MAKING A PLAYER THAT WE WILL LATER SAVE TO THE APPLICATION
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Grab the dialog widgets so we can get info for later
         */
        nameFeild = (EditText) findViewById(R.id.editText_name);
        difficultySlider = (SeekBar) findViewById(R.id.seekBar_difficulty);
        skill1Spinner = (Spinner) findViewById(R.id.spinner_skill_1);
        skill2Spinner = (Spinner) findViewById(R.id.spinner_skill_2);
        skill3Spinner = (Spinner) findViewById(R.id.spinner_skill_3);
        skill4Spinner = (Spinner) findViewById(R.id.spinner_skill_4);
        Button button = findViewById(R.id.button_submit_signIn);

    }

}
