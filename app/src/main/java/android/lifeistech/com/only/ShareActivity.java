package android.lifeistech.com.only;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ShareActivity extends AppCompatActivity {

    ImageView giveImageView;
    ImageView takeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        giveImageView = findViewById(R.id.sharegive);
        takeImageView = findViewById(R.id.sharetake);
    }

    public void give(View view) {
        Intent intent = new Intent(this, GiveActivity.class);
        startActivity(intent);
    }
    public void take(View view) {
        Intent intent = new Intent(this, TakeActivity.class);
        startActivity(intent);
    }

}
