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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import kz.baldogre.learn.App;
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
        int section = 0;
        if (getArguments() != null) {
            section = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        View rootView;
        if (section == 0) {
            rootView = inflater.inflate(R.layout.fragment_main2, container, false);
        } else {
            rootView = inflater.inflate(R.layout.fragment_main_lessons, container, false);
        }

        ButterKnife.bind(this, rootView);

        if (mLessonText != null) {
            int finalSection = section;
            RunOnBackground.runOnBackground(new Runnable() {
                @Override
                public void run() {
                    course = ((App) getActivity().getApplication()).getAppDatabase().getCourseDao().getCourseById(finalSection - 1);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                mLessonText.setText(Html.fromHtml(course.getDescription().replaceAll("\n", "<br />"), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                mLessonText.setText(Html.fromHtml(course.getDescription().replaceAll("\n", "<br />")));
                            }
                            mStartLessons.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    startActivity(new Intent(getContext(), VideosActivity.class).putExtra(VideosActivity.COURSE_ID, course.getId()));
                                }
                            });
                        }
                    });
                }
            });
        }

        return rootView;
    }
}
