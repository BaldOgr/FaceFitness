package kz.baldogre.learn.ui.menu;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.baldogre.learn.R;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.about_author_text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.close)
    public void onCloseClick() {
        finish();
    }

    @OnClick(R.id.instagram)
    public void onInstagramClick() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.instagram_title)
                .setMessage(R.string.instagram_message)
                .setPositiveButton(R.string.ok, (dialog, which) -> {
                    Uri uri = Uri.parse("http://instagram.com/facefitness_universe");
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                    likeIng.setPackage("com.instagram.android");

                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://instagram.com/facefitness_universe")));
                    }
                })
                .setNegativeButton(R.string.no, (dialog, which) -> {

                }).show();
    }
}
