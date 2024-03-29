package kz.baldogre.learn.ui.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.baldogre.learn.App;
import kz.baldogre.learn.BuildConfig;
import kz.baldogre.learn.R;
import kz.baldogre.learn.common.RunOnBackground;
import kz.baldogre.learn.model.Course;
import kz.baldogre.learn.ui.video.VideosActivity;

public class PlaceholderFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";

    @BindView(R.id.lesson_text)
    @Nullable
    TextView mLessonText;

    @BindView(R.id.start_lessons)
    @Nullable
    Button mStartLessons;

    private Course course;
    private int section;

    public PlaceholderFragment() {
    }

    public static Fragment newInstance(int sectionNumber) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        section = 0;
        if (getArguments() != null) {
            section = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        View rootView;
        if (section == 0) {
            rootView = inflater.inflate(R.layout.fragment_main2, container, false);
            LinearLayout content = rootView.findViewById(R.id.content);
            int[] ids = {R.drawable._0, R.drawable._1, R.drawable._2, R.drawable._3, R.drawable.foto1_2};
            for (int id : ids) {
                ImageView imageView = new ImageView(rootView.getContext());
                imageView.setImageResource(id);
                content.addView(imageView);
            }
        } else {
            rootView = inflater.inflate(R.layout.fragment_main_lessons, container, false);
        }

        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mLessonText != null) {
            RunOnBackground.runOnBackground(new Runnable() {
                @Override
                public void run() {
                    course = ((App) getActivity().getApplication()).getAppDatabase().getCourseDao().getCourseById(section);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                mLessonText.setText(Html.fromHtml(course.getDescription().replaceAll("\n", "<br />"), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                mLessonText.setText(Html.fromHtml(course.getDescription().replaceAll("\n", "<br />")));
                            }
                            if (course.isOpen()) {
                                mStartLessons.setOnClickListener(v -> startActivity(new Intent(getContext(), VideosActivity.class).putExtra(VideosActivity.COURSE_ID, course.getId())));
                            } else {
                                mStartLessons.setOnClickListener(v -> Toast.makeText(getContext(), R.string.error_closed_course, Toast.LENGTH_SHORT).show());

                            }
                        }
                    });
                }
            });
        }

    }
}
