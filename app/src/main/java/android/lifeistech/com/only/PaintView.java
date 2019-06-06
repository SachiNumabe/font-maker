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
    final String TAG = "PaintView";

    private Paint paint;
    private Path path;
    String pathString = "";
    private List<Path> paths;
    private int pCount = 0;
    float touchX, touchY;
    int lineWidth = 40;
    private static float MIN_ZOOM = 1f;
    private static float MAX_ZOOM = 5f;
    private float scaleFactor = 1.f;
    private ScaleGestureDetector detector;


    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        detector = new ScaleGestureDetector(getContext(), new ScaleListener());

        paths = new ArrayList<>();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
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
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);

        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paint);
        }

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "touched");
        detector.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        touchX = x;
        touchY = y;


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                pathString += "M" + x + " " + y;
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
        paths = new ArrayList<>();
        invalidate();

        pCount = 0;
    }


    //ピンチインアウトのListener
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

        public boolean onScale(ScaleGestureDetector detector) {
            return super.onScale(detector);
        }
    };

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(MIN_ZOOM, Math.min(scaleFactor, MAX_ZOOM));
            invalidate();
            return true;
          }
      }
    }

