package android.lifeistech.com.only;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        {

    ImageView makeImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeImageView = findViewById(R.id.make);
    }

    public void make(View view) {
        Intent intent = new Intent(this, MakeActivity.class);
        startActivity(intent);
    }

}

