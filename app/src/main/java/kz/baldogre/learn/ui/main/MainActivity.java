package kz.baldogre.learn.ui.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.baldogre.learn.R;
import kz.baldogre.learn.ui.video.VideosActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_lessons)
    public void onStartLessonsClick() {
        startActivity(new Intent(this, VideosActivity.class));
    }
}
