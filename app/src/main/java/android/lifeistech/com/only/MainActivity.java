package android.lifeistech.com.only;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        //implements View.OnClickListener
        {  //クリックリスナーを実装

    ImageView makeImageView;
    ImageView useImageView;
    ImageView settingImageView;
    ImageView shareImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeImageView = findViewById(R.id.make);
        useImageView = findViewById(R.id.use);
        settingImageView = findViewById(R.id.setting);
        shareImageView = findViewById(R.id.share);
    }

    public void make(View view) {
        Intent intent = new Intent(this, MakeActivity.class);
        startActivity(intent);
    }
    public void use(View view) {
        Intent intent = new Intent(this, UseActivity.class);
        startActivity(intent);
    }
    public void setting(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }
    public void share(View view) {
        Intent intent = new Intent(this, ShareActivity.class);
        startActivity(intent);
    }
   // @Override
    //public void onClick(View view) {}


}

