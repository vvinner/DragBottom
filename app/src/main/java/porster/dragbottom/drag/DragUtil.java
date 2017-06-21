package porster.dragbottom.drag;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by Porster on 17/6/20.
 */

public class DragUtil {

    /**
     * 当不支持共享元素时，采用属性动画来平滑过渡退出效果
     * @param target                    View
     * @param animatorListenerAdapter   AnimatorListener
     */
    public static void closeNotSupportTransition(View target, AnimatorListenerAdapter animatorListenerAdapter){

        Drawable draw=target.getBackground();
        int nowBgColor=0;

        if(draw instanceof ColorDrawable){
            ColorDrawable colorDrawable= (ColorDrawable) draw;
            nowBgColor=colorDrawable.getColor();
        }

        ObjectAnimator t=ObjectAnimator.ofInt(target,"backgroundColor",nowBgColor, Color.TRANSPARENT);

        t.setEvaluator(ArgbEvaluator.getInstance());

        t.start();

        ObjectAnimator move=ObjectAnimator.ofFloat(target,"translationY",target.getTop(),target.getHeight());
        move.setDuration(600);
        if (animatorListenerAdapter != null) {
            move.addListener(animatorListenerAdapter);
        }
        move.start();
        ObjectAnimator.ofFloat(target,"alpha",1.0f,0f).start();

    }
}
