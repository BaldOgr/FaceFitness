package kz.baldogre.learn.ui.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.baldogre.learn.App;
import kz.baldogre.learn.R;
import kz.baldogre.learn.model.Badge;

public class BadgesActivity extends AppCompatActivity {

    @BindView(R.id.badges)
    TextView mBadges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Badge badge = ((App) getApplication()).getAppDatabase().getBadgeDao().getBadge();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mBadges.setText("Badges:" + badge.getCount());
                    }
                });
            }
        }).start();


    }
}
