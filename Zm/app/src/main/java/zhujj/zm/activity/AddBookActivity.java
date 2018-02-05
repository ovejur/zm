package zhujj.zm.activity;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import zhujj.baselibrary.activity.BaseActivity;
import zhujj.zm.R;

/**
 * 作者：朱建晶 on 2018/2/5 16:31
 * 邮箱：344951059@qq.com
 */

public class AddBookActivity extends BaseActivity {

    protected static final float FLIP_DISTANCE = 50;
    GestureDetector mDetector;
    private int inputType = 0;

    @Override
    protected void findViews() {

        setContentView(R.layout.activity_add_book);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    @Override
    protected void addAction() {
        mDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // TODO Auto-generated method stub

            }

            /**
             *
             * e1 The first down motion event that started the fling. e2 The
             * move motion event that triggered the current onFling.
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getX() - e2.getX() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向左滑...");
                    return true;
                }
                if (e2.getX() - e1.getX() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向右滑...");
                    return true;
                }
                if (e1.getY() - e2.getY() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向上滑...");
                    return true;
                }
                if (e2.getY() - e1.getY() > FLIP_DISTANCE) {
                    Log.i("MYTAG", "向下滑...");
                    return true;
                }

                Log.d("TAG", e2.getX() + " " + e2.getY());

                return false;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void upDo() {
        if (inputType == 0) {

        } else {
            inputType = inputType - 1;
        }
    }

    private void downDo() {
        if (inputType >= 2) {
            return;
        }
        inputType = inputType + 1;
        reflushView();
    }

    private void reflushView() {
        switch (inputType) {
            case 0:

                break;
            case 1:

                break;
            case  2:

                break;
            default:

                break;
        }
    }
}
