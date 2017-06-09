package porster.dragbottom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.k012).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewCompat.setTransitionName(view,"k012");


                ActivityOptionsCompat compat=ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,view,ViewCompat.getTransitionName(view));
                ActivityCompat.startActivity(view.getContext(),new Intent(view.getContext(),DragActivity.class),compat.toBundle());
            }
        });
        findViewById(R.id.k24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewCompat.setTransitionName(view,"k24");


                ActivityOptionsCompat compat=ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,view,ViewCompat.getTransitionName(view));
                ActivityCompat.startActivity(view.getContext(),new Intent(view.getContext(),DragViewPagerActivity.class),compat.toBundle());
            }
        });
    }
}
