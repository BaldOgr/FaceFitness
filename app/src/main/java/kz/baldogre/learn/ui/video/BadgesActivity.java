package kz.baldogre.learn.ui.video;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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
        ((App) getApplication()).getAppDatabase().getBadgeDao().getBadge()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Badge>() {
                    @Override
                    public void accept(Badge badge) throws Exception {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mBadges.setText("Badges:" + badge.getCount());
                            }
                        });
                    }
                });
    }
}
