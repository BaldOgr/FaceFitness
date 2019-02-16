package kz.baldogre.learn.ui.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.OnClick;
import kz.baldogre.learn.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    @OnClick(R.id.close)
    public void onCloseClick() {
        finish();
    }
}
