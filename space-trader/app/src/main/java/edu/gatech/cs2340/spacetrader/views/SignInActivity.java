package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Player;
import edu.gatech.cs2340.spacetrader.viewmodels.SignInViewModel;


public class SignInActivity extends AppCompatActivity {

    private SignInViewModel viewModel;

    /*
     WIDGETS FOR BINDING AND GETTING INFORMATION
    */
    private EditText nameField;
    private Spinner difficultySpinner;
    private Spinner skill1Spinner;
    private Spinner skill2Spinner;
    private Spinner skill3Spinner;
    private Spinner skill4Spinner;


    // MAKING A PLAYER THAT WE WILL LATER SAVE TO THE APPLICATION
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        /**
         * Grab the dialog widgets so we can get info for later
         */
        nameField =  findViewById(R.id.editText_name);
        difficultySpinner = findViewById(R.id.difficultySpinner);
        skill1Spinner = (Spinner) findViewById(R.id.spinner_skill_1);
        skill2Spinner = findViewById(R.id.spinner_skill_2);
        skill3Spinner = findViewById(R.id.spinner_skill_3);
        skill4Spinner = findViewById(R.id.spinner_skill_4);
        Button button = findViewById(R.id.button_submit_signIn);


        Integer[] skillPoints = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        String[] difficulties = {"Beginner", "Easy", "Normal", "Hard", "Impossible"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficulties);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        difficultySpinner.setAdapter(adapter1);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillPoints);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        skill1Spinner.setAdapter(adapter);
        skill2Spinner.setAdapter(adapter);
        skill3Spinner.setAdapter(adapter);
        skill4Spinner.setAdapter(adapter);

        player = new Player("name", 0, 0, 0, 0);
        viewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
    }

    /**
     * Button handler for the submit a player button
     *
     * @param view the button that was pressed
     */
    public void createPlayer(View view) {

        player.setName(nameField.getText().toString());
        player.setSkill1(Integer.parseInt(skill1Spinner.getSelectedItem().toString()));
        player.setSkill2(Integer.parseInt(skill2Spinner.getSelectedItem().toString()));
        player.setSkill3(Integer.parseInt(skill3Spinner.getSelectedItem().toString()));
        player.setSkill4(Integer.parseInt(skill4Spinner.getSelectedItem().toString()));

        String response = viewModel.addPlayer(player);
        Toast.makeText(this, "Result: " + response, Toast.LENGTH_LONG).show();
        if (response.equals("Player Created")) {
            Intent intent = new Intent(SignInActivity.this, PlanetActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Button handler for the exit button
     *
     * @param view the button that was pressed
     */
    public void onExit(View view) {
        finish();
    }
}
