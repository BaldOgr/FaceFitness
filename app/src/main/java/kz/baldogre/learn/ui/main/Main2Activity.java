package kz.baldogre.learn.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rd.PageIndicatorView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.baldogre.learn.App;
import kz.baldogre.learn.R;
import kz.baldogre.learn.common.RunOnBackground;
import kz.baldogre.learn.model.Course;
import kz.baldogre.learn.ui.info.InfoActivity;
import kz.baldogre.learn.ui.menu.MenuActivity;

public class Main2Activity extends AppCompatActivity {

    private List<Course> courses;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        ButterKnife.bind(this);

        RunOnBackground.runOnBackground(() -> {
            courses = ((App) getApplication()).getAppDatabase().getCourseDao().getAllCourses();
            initPager();
        });
    }

    private void initPager() {
        runOnUiThread(() -> {
            PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
            pageIndicatorView.setCount(courses.size() + 1); // specify total count of indicators
            pageIndicatorView.setSelection(1);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            mViewPager.setCurrentItem(1);
        });
    }

    @OnClick(R.id.menu)
    public void onMenuClick() {
        startActivity(new Intent(this, MenuActivity.class));
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return courses.size() + 1;
        }
    }
}
