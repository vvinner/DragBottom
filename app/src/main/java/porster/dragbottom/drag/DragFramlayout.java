package porster.dragbottom.drag;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * DragBack
 * Created by Porster on 17/6/9.
 */

public class DragFramlayout extends FrameLayout{
    public static final String TAG="DragFramlayout";

    private ViewDragHelper mDragHelper;

    //#00000000
    public int DEF_BG_COLOR=0xff000000;


    public DragFramlayout(Context context,AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        if(getContext() instanceof Activity){
            ((Activity) getContext()).getWindow().getDecorView().setBackgroundColor(DEF_BG_COLOR);
        }

        mDragHelper=ViewDragHelper.create(this,1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);

                if(mNeedRelease){
                    if(getContext() instanceof Activity){
                        ((Activity) getContext()).onBackPressed();
                    }
                }else{
                    needDrag =false;
                    mDragHelper.settleCapturedViewAt(finalLeft,finalTop);
                    releasedChild.setScaleX(1.0f);
                    releasedChild.setScaleY(1.0f);
                    invalidate();
                }
            }

            boolean mNeedRelease;
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);

                mNeedRelease=top>getHeight()*0.25;//Release

                //ChangeBg
                float present=1-(top*1.0f)/(getHeight());
                if(getContext() instanceof Activity){
                    int alpah=Math.min((int) (255*present),255);
                    ((Activity) getContext()).getWindow().getDecorView().setBackgroundColor(Color.argb(alpah,0,0,0));
                }
                //ChangeScale
                float maxScale=Math.min(present,1.0f);//Max,1.0f
                float minScale=Math.max(0.5f,maxScale);//Min,5.0f;

//                if(present>1.0f){
//                    present=1.0f;
//                }else if(present<0.5f){
//                    present=0.5f;
//                }
                changedView.setScaleX(minScale);
                changedView.setScaleY(minScale);

                Log.i(TAG,"Top="+top+"Release="+getHeight()*0.25+"Present="+present);
            }

            boolean needDrag;
            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                if(needDrag){
                    return top;
                }
                if(top<0){
                    top=0;
                }else if(top>100){//释放允许任何方向拖拽
                    needDrag =true;
                }
                return top;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return needDrag?left:0;
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getHeight()/2;
            }
        });
    }

    int finalLeft;
    int finalTop;


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        finalLeft=getChildAt(0).getLeft();
        finalTop=getChildAt(0).getTop();
    }
    @Override
    public void computeScroll(){
        if(mDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            mDragHelper.processTouchEvent(event);
        return true;
    }
}
