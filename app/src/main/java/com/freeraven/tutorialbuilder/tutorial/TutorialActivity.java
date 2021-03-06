package com.freeraven.tutorialbuilder.tutorial;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.freeraven.tutorialbuilder.data.DataFormat;
import com.freeraven.tutorialbuilder.data.DataSourceType;
import com.freeraven.tutorialbuilder.data.RowDataURI;
import com.freeraven.tutorialbuilder.data.provider.ProviderFactory;
import com.freeraven.tutorialbuilder.R;
import com.freeraven.tutorialbuilder.data.provider.PageListModelProvider;
import com.freeraven.tutorialbuilder.pagemodel.PageListModel;
import com.freeraven.tutorialbuilder.pagemodel.PageModel;

public class TutorialActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private PageListModel pageListModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupPageListModel();

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), pageListModel.size());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // todo remove action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TutorialActivity.this, ImageLoadingSampleActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupPageListModel() {
        RowDataURI dataURI = new RowDataURI();
        dataURI.setDataURI("sep-17-2016.json");
        PageListModelProvider provider = ProviderFactory.getPageListProvider(dataURI, DataSourceType.ASSETS, DataFormat.JSON, this);
        pageListModel = provider.getPageListModel();
    }

    public PageModel getPageModel(int pageIndex) {
        return pageListModel.get(pageIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int index) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, index);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            TutorialActivity activity = (TutorialActivity) getActivity();
            int pageIndex = getArguments().getInt(ARG_SECTION_NUMBER);
            PageModel pageModel = activity.getPageModel(pageIndex);
            VerticalLinearPageViewBuilder verticalLinearPageViewBuilder = new VerticalLinearPageViewBuilder();
            return verticalLinearPageViewBuilder.buildPage(pageModel, container, inflater);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final int count;

        public SectionsPagerAdapter(FragmentManager fm, int numberOfPages) {
            super(fm);
            this.count = numberOfPages;
        }

        @Override
        public Fragment getItem(int index) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(index);
        }

        @Override
        public int getCount() {
            return count;
        }
    }
}
