package kz.baldogre.learn.ui.video;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.baldogre.learn.App;
import kz.baldogre.learn.R;
import kz.baldogre.learn.common.DeveloperKey;
import kz.baldogre.learn.common.RunOnBackground;
import kz.baldogre.learn.model.LastViewedLesson;
import kz.baldogre.learn.model.Lesson;
import kz.baldogre.learn.model.db.AppDatabase;

public class VideosActivity extends YouTubeBaseActivity {
    @BindView(R.id.youtube_player)
    YouTubePlayerView youTubePlayerView;

    @BindView(R.id.description)
    TextView description;

    YouTubePlayer mYouTubePlayer;

    @BindView(R.id.pause)
    ImageButton pause;

    private List<Lesson> lessons;
    private LastViewedLesson lastViewedLesson;
    private AppDatabase appDatabase;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        ButterKnife.bind(this);


        appDatabase = ((App) getApplicationContext()).getAppDatabase();

        RunOnBackground.runOnBackground(() -> {
            lessons = appDatabase.getLessonDao().getAllLessons();
            lastViewedLesson = appDatabase.getLastViewedDao().getLastViewedLesson();
            index = lastViewedLesson.getLessonId();
            runOnUiThread(() -> {
                youTubePlayerView.initialize(DeveloperKey.KEY, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
                        mYouTubePlayer = youTubePlayer;
                        setDescription();
                        if (!restored) {
                            mYouTubePlayer.loadVideo(lessons.get(index).getLink());
                        }
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            mYouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
                        } else {
                            mYouTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                        }
                        mYouTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                            @Override
                            public void onPlaying() {
//                                onPausePlayClick(pause);
                            }

                            @Override
                            public void onPaused() {
                                saveCurrentMillis();
                            }

                            @Override
                            public void onStopped() {

                            }

                            @Override
                            public void onBuffering(boolean b) {

                            }

                            @Override
                            public void onSeekTo(int i) {

                            }
                        });
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                        youTubeInitializationResult.getErrorDialog(VideosActivity.this, 1).show();
                    }
                });
            });
        });


    }

    private void setDescription() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            description.setText(Html.fromHtml(lessons.get(index).getDescription().replaceAll("\n", "<br />"), Html.FROM_HTML_MODE_COMPACT));
        } else {
            description.setText(Html.fromHtml(lessons.get(index).getDescription()));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (lessons.get(index).getCurrentMillis() < mYouTubePlayer.getCurrentTimeMillis()) {
            lessons.get(index).setCurrentMillis(mYouTubePlayer.getCurrentTimeMillis());
        }
        RunOnBackground.runOnBackground(() -> appDatabase.getLessonDao().insert(lessons.get(index)));
    }

    @OnClick({R.id.pause})
    public void onPausePlayClick(View v) {
        if (mYouTubePlayer.isPlaying()) {
            mYouTubePlayer.pause();
            ((ImageButton) v).setImageResource(R.drawable.ic_play_arrow_black_24dp);
            saveCurrentMillis();
        } else {
            mYouTubePlayer.play();
            ((ImageButton) v).setImageResource(R.drawable.ic_pause_black_24dp);
        }
    }

    private void saveCurrentMillis() {
        lessons.get(index).setCurrentMillis(mYouTubePlayer.getCurrentTimeMillis());
        RunOnBackground.runOnBackground(() -> appDatabase.getLessonDao().insert(lessons.get(index)));
    }

    @OnClick(R.id.previous)
    public void onPreviousClick(View v) {
        if (index > 0) {
            mYouTubePlayer.loadVideo(lessons.get(--index).getLink());
            setDescription();
            pause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }
    }

    @OnClick(R.id.next)
    public void onNextClick(View v) {
        if (index < lessons.size() - 1 &&
                (mYouTubePlayer.getDurationMillis() >= lessons.get(index).getCurrentMillis()
                        || index < lastViewedLesson.getLessonId())) {
            mYouTubePlayer.loadVideo(lessons.get(++index).getLink());
            setDescription();
            pause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
            lastViewedLesson.setLessonId(index);
            RunOnBackground.runOnBackground(() -> {
                appDatabase.getLastViewedDao().insert(lastViewedLesson);
            });
        } else if (index == lessons.size() - 1) {
            Toast.makeText(this, R.string.error_not_enough_lessons, Toast.LENGTH_SHORT).show();
        } else if (mYouTubePlayer.getDurationMillis() > mYouTubePlayer.getCurrentTimeMillis()) {
            Toast.makeText(this, R.string.error_next_lesson, Toast.LENGTH_SHORT).show();
        }
    }

}
