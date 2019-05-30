package android.lifeistech.com.only;

import android.content.DialogInterface;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.UUID;

public class MakeActivity extends AppCompatActivity {
    private final String TAG = "MakeActivity";
    ImageButton saveButton;
    ImageButton clear;
    ImageButton reDisplay;
    ImageButton black;
    ImageButton white;
    ImageButton red;
    PaintView paintView;

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);

        // インスタンスを取得する
        saveButton = findViewById(R.id.save);
        clear = findViewById(R.id.clear);
        reDisplay = findViewById(R.id.redisplay);
        paintView = findViewById(R.id.paintView);
//        black = findViewById(R.id.black);
//        white = findViewById(R.id.white);
//        red = findViewById(R.id.red);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("BarChanged", "" + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("BarChanged", "start" );
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("BarChanged", "stop: " + seekBar.getProgress());
                paintView.lineWidth = seekBar.getProgress();
                paintView.updatePait();

            }
        });

        paintView.setDrawingCacheEnabled(true);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.clear();
            }
        });
        // 画像を保存するボタン
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ボタンが押されたら、ダイアログを表示する。
                AlertDialog.Builder saveDialog = createSaveAlertBuilder();
                saveDialog.show();
            }
        };
        saveButton.setOnClickListener(listener);

        //ペン色変える
        //black.setOnClickListener(new View.OnClickListener() {
         //   @Override
            //public void onClick(View view) {
                //paintView.changeColor(0, 0, 0);
           // }
       // });


    }

    private void save() {
        // セーブする
        String fileName = UUID.randomUUID().toString() + ".png";
        fileName = "Only_my_font";

        String filePath = Environment.getExternalStorageDirectory().getPath()
                + "/Pictures/"+fileName;
        fileName = filePath;

        String imgSaved = MediaStore.Images.Media.insertImage(
                getContentResolver(), paintView.getDrawingCache(), fileName
                , "drawing");
        printLog(paintView.getDrawingCache().toString());
        if (imgSaved != null) {
            Toast savedToast = Toast.makeText(getApplicationContext(),
                    "Fontが保存されました！" + fileName, Toast.LENGTH_SHORT);
            savedToast.show();
        } else {
            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                    "保存に失敗しました", Toast.LENGTH_SHORT);
            unsavedToast.show();
        }
    }


    private void printLog(String message) {
        Log.d(TAG, message);
    }


    private AlertDialog.Builder createSaveAlertBuilder() {
        AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);

        saveDialog.setTitle("絵を保存する");
        saveDialog.setMessage("ギャラリーにフォントを保存しますか？");
        saveDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // はいが押された時。
                //save drawing
                // セーブする。
                save();
            }
        });
        saveDialog.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // いいえが押された時
                Toast.makeText(getApplication(), "保存されませんでした。", Toast.LENGTH_SHORT).show();
                // 自分自身を閉じる。
                dialog.cancel();
            }
        });
        return saveDialog;
    }
}
