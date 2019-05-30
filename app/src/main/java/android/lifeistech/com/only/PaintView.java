package android.lifeistech.com.only;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by numabesachi on 2018/07/12.
 */
public class PaintView extends View {
    //Bitmap bmp;
    private Paint paint;
    private Path path;
    String pathString = "";

    private List<Path> paths;
    private int pCount = 0;

    float touchX, touchY;

    int lineWidth = 40;

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        path = new Path();

        paths = new ArrayList<>();

        // mipmap にある ic_lancher と言うファイルを読み込んで、(Bitmap と言う形式で読み込む)
        // bmp に画像のデータを代入する。
        //bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.rgb(0, 0, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(lineWidth);
    }

    public void updatePait() {
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paint);
        }


        //canvas.drawBitmap(bmp, touchX - 36, touchY - 36, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        touchX = x;
        touchY = y;

//        path = paths.get(pCount);
//        path = new Path();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                path.moveTo(x, y);
                path = new Path();
                pathString += "M" + x + " " + y;
                // x,y = 296, 317
                // M296 317

                paths.add(path);
                path.moveTo(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                pathString += "L" + x + " " + y;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(x, y);
                pathString += "L" + x + " " + y;
                invalidate();
                pCount++;

                return true;
        }

        paths.set(pCount, path);

        Log.d("PATH", pathString);
        Log.d("PATH", path.toString());
        Log.d("PATH", paths.size() + "");

        return true;
    }

    public void clear() {
        pathString = "";
//        path.reset();
        paths = new ArrayList<>();
        invalidate();

        pCount = 0;
    }
//ペン色変える
    // public void changeColor(int r, int g, int b){
    //     this.paint.setColor(Color.rgb(r, g, b));
    //  }


    /**
     * ピンチイン/アウトのListener
     */
    private ScaleGestureDetector.SimpleOnScaleGestureListener scaleGestureListener = new ScaleGestureDetector.SimpleOnScaleGestureListener() {
        // イベントの開始（２点タッチされたタイミングで発生）
        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
        }

        // イベントの開始（指を離すと発生）
        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);

        }
        // ピンチイン/アウト
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Log.d("MYLOG", "これはピンチインアウト");
            return super.onScale(detector);
        }
    };
}

