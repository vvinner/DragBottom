package porster.dragbottom;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Porster on 17/6/9.
 */

public class DragViewPagerActivity extends Activity{

    private List<View> viewList=new ArrayList<>();

    private ViewPager viewpager;

    private int nowPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_viewpager);

        nowPosition=1;

        viewpager= (ViewPager) findViewById(R.id.viewpager);



        ImageView k012=new ImageView(this);
        k012.setImageResource(R.mipmap.k012);
        ImageView k24=new ImageView(this);
        k24.setImageResource(R.mipmap.k24);

        k012.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        k24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        viewList.add(k012);
        viewList.add(k24);
        ViewCompat.setTransitionName(k24,"k24");

        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }
            @Override
            public int getCount() {
                return viewList.size();
            }
            @Override
            public void destroyItem(ViewGroup container, int position,Object object) {
                container.removeView(viewList.get(position));
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));
                return viewList.get(position);
            }
        });
        viewpager.setCurrentItem(nowPosition,false);
    }

    @Override
    public void onBackPressed() {
        if(viewpager.getCurrentItem()!=nowPosition){
            finish();
            overridePendingTransition(R.anim.x_push_activity,R.anim.zoom_exit);
        }else{
            ActivityCompat.finishAfterTransition(this);
        }
    }
}
