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
    PaintView paintView;
    SeekBar seekBar;
    AlertDialog.Builder saveDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);

        saveButton = findViewById(R.id.save);
        clear = findViewById(R.id.clear);
        reDisplay = findViewById(R.id.redisplay);
        paintView = findViewById(R.id.paintView);
        seekBar = findViewById(R.id.seekBar);

        //ペンの太さ変える
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                saveDialog = createSaveAlertBuilder();
                saveDialog.show();
            }
        };
        saveButton.setOnClickListener(listener);

    }

        // セーブする
     private void save() {
         String fileName = UUID.randomUUID().toString() + ".png";
            fileName = "Only_my_font";

         String filePath = Environment.getExternalStorageDirectory().getPath() + "/Pictures/"+fileName;
          fileName = filePath;

          if (paintView.getDrawingCache() == null) {   //save失敗
              Toast.makeText(getApplicationContext(), "何か記入してね！", Toast.LENGTH_SHORT).show();
              return;
          }

           String imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(), paintView.getDrawingCache(), fileName, "drawing");

           printLog(paintView.getDrawingCache().toString());

           if (imgSaved != null) {
                Toast.makeText(getApplicationContext(),"Fontが保存されました！" + fileName, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),"保存に失敗しました", Toast.LENGTH_SHORT).show();
            }
     }


      private void printLog(String message) {
        Log.d(TAG, message);
    }


      private AlertDialog.Builder createSaveAlertBuilder() {

            saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("絵を保存する");
            saveDialog.setMessage("ギャラリーにフォントを保存しますか？");

          saveDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int which) {   // はいが押された時セーブする。
                    save();
                }
         });

         saveDialog.setNegativeButton("いいえ", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int which) {    // いいえが押された時自分自身を閉じる。
                 Toast.makeText(getApplication(), "保存されませんでした。", Toast.LENGTH_SHORT).show();
                  dialog.cancel();
               }
           });
           return saveDialog;
      }
    }
