package edu.gatech.cs2340.spacetrader.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.viewmodels.SignInViewModel;

public class ReturningPlayerSignInActivity extends AppCompatActivity {

    private Button signIn;
    private EditText userName;
    private SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.returning_main);

        signIn = findViewById(R.id.signInButton);
        userName = findViewById(R.id.playerName);
        viewModel = ViewModelProviders.of(this).get(SignInViewModel.class);

        signIn.setOnClickListener(e-> {
            if (viewModel.doesPlayerNameExist(userName.getText().toString())) {
                viewModel.loadExistingPlayer(userName.getText().toString());
                Intent intent = new Intent(ReturningPlayerSignInActivity.this, PlanetActivity.class);
                startActivity(intent);
            }
        });
    }
}
