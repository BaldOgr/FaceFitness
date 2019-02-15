package kz.baldogre.learn;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VideosActivity extends YouTubeBaseActivity {

    public static String[] links = {
            "yctelnfigHc",
            "OhLavbEdqmk",
            "iX4pqNK_0T4",
            "-dH-xU4CSJY"
    };

    public static String[] descriptions = {
            "Лекция 1. Введение в архитектуру клиент-серверных андроид-приложений",
            "Лекция 2 по архитектуре андроид-приложений. Паттерны A/B/C",
            "Лекция 3 по архитектуре андроид приложения. Знакомство с RxJava",
            "Стоит ли учить Scala и Kotlin?"
    };

    @BindView(R.id.youtube_player)
    YouTubePlayerView youTubePlayerView;

    @BindView(R.id.description)
    TextView description;

    YouTubePlayer mYouTubePlayer;

    @BindView(R.id.pause)
    ImageButton pause;

    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        ButterKnife.bind(this);

        youTubePlayerView.initialize(DeveloperKey.KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                mYouTubePlayer = youTubePlayer;
                mYouTubePlayer.loadVideo(links[index]);
                description.setText(descriptions[index]);
                mYouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                youTubeInitializationResult.getErrorDialog(VideosActivity.this, 1).show();
            }
        });
    }

    @OnClick(R.id.pause)
    public void onPausePlayClick(View v) {
        if (mYouTubePlayer.isPlaying()) {
            mYouTubePlayer.pause();
            ((ImageButton) v).setImageResource(R.drawable.ic_pause_black_24dp);
        } else {
            mYouTubePlayer.play();
            ((ImageButton) v).setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
    }

    @OnClick(R.id.previous)
    public void onPreviousClick(View v) {
        if (index > 0) {
            mYouTubePlayer.loadVideo(links[--index]);
            description.setText(descriptions[index]);
            pause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
    }

    @OnClick(R.id.next)
    public void onNextClick(View v) {
        if (index < links.length - 1) {
            mYouTubePlayer.loadVideo(links[++index]);
            description.setText(descriptions[index]);
            pause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
    }

}
