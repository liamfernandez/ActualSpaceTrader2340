package edu.gatech.cs2340.spacetrader.views;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.model.MySQLTalker;

/**
 *
 */
public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = findViewById(R.id.PlayGameBtn);
        Button returningPlayer = findViewById(R.id.returningPlayer);
        final ImageView background1 = findViewById(R.id.appCompatImageView1);
        final ImageView background2 = findViewById(R.id.appCompatImageView2);
        //If animating star background
//        final ImageView background1 = findViewById(R.id.starbackground1);
//        final ImageView background2 = findViewById(R.id.starbackground2);
        final ValueAnimator animate = ValueAnimator.ofFloat(0.0f, 1.0f);
        animate.setRepeatCount(ValueAnimator.INFINITE);
        animate.setInterpolator(new LinearInterpolator());
        //if animating star background animate.setDuration(25000L);
        animate.setDuration(12500L);
        animate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = background1.getWidth();
                final float translationX = width * progress;
                background1.setTranslationX(translationX);
                background2.setTranslationX(translationX - width);
            }
        });
        animate.start();
        //MySQLTalker.initialize();

        start.setOnClickListener(e -> {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
        });
        //startService(new Intent(MainActivity.this, SoundService.class));

        returningPlayer.setOnClickListener(e -> {
            Intent intent = new Intent(MainActivity.this, ReturningPlayerSignInActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater temp = getMenuInflater();
        temp.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
