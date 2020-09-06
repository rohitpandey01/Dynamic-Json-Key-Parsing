package com.json_dynamic_key_parsing.json_feed.views.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.si_test.R;
import com.json_dynamic_key_parsing.base.views.activity.BaseActivity;
import com.json_dynamic_key_parsing.data.remote.retrofit.response.FeedResponse;
import com.json_dynamic_key_parsing.json_feed.model.TeamList;
import com.json_dynamic_key_parsing.json_feed.view_model.JsonFeedViewModel;
import com.json_dynamic_key_parsing.json_feed.views.fragments.IndiaFragment;
import com.json_dynamic_key_parsing.json_feed.views.fragments.NewZealandFragment;
import com.json_dynamic_key_parsing.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {
    TabLayout teamTabView;
    ViewPager teamViewPager;

    TextView indianTeamTabText, nzTeamTabText;

    private JsonFeedViewModel mJsonFeedViewModel;
    private boolean isFirstTime = true;
    private ArrayList<TeamList> indianTeamList = new ArrayList<>();
    private ArrayList<TeamList> nzTeamList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamTabView = findViewById(R.id.team_tab_view);
        teamViewPager = findViewById(R.id.team_view_pager);

        mJsonFeedViewModel = ViewModelProviders.of(this).get(JsonFeedViewModel.class);

        getJsonFeedsData();
    }

    private void getJsonFeedsData(){
        mJsonFeedViewModel.getJsonFeed().observe(this, apiResponse -> {
            if (apiResponse != null){
                switch (apiResponse.currentState){
                    case LOADING:
                        setProgressBar(true);
                        break;
                    case SUCCESS:
                        setProgressBar(false);
                        for (Map.Entry<String, FeedResponse.DataValues> entry : apiResponse.data.getTeams().entrySet()){
                            if (isFirstTime){
                                isFirstTime = false;
                                for (Map.Entry<String, FeedResponse.PlayersValues> playersEntry : entry.getValue().getPlayersValuesMap().entrySet()){
                                    indianTeamList.add(new TeamList(entry.getValue().getNameFull(),
                                            playersEntry.getValue().getNamefull(), playersEntry.getValue().getPosition(),
                                            playersEntry.getValue().isCaptain(), playersEntry.getValue().isKeeper()));
                                }
                            }else{
                                for (Map.Entry<String, FeedResponse.PlayersValues> playersEntry : entry.getValue().getPlayersValuesMap().entrySet()){
                                    nzTeamList.add(new TeamList(entry.getValue().getNameFull(), playersEntry.getValue().getNamefull(),
                                            playersEntry.getValue().getPosition(), playersEntry.getValue().isCaptain(),
                                            playersEntry.getValue().isKeeper()));
                                }
                            }
                        }
                        createViewPagerTab();
                        break;
                    case FAILURE:
                    case ERROR:
                        setProgressBar(false);
                        break;
                }
            }
        });
    }

    private void createViewPagerTab(){
        setToolbarTitleName(indianTeamList.get(0).getmTeamName(), nzTeamList.get(0).getmTeamName());
        if (teamViewPager.getAdapter() == null) {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFrag(new IndiaFragment(indianTeamList),"");
            adapter.addFrag(new NewZealandFragment(nzTeamList),"");
            teamViewPager.setAdapter(adapter);
            teamTabView.setupWithViewPager(teamViewPager);
            setViewPagerTitles();
        }
    }

    private final List<Fragment> mFragmentList = new ArrayList<>();

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<String> mTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return  mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFrag(Fragment fragment , String titleName) {
            mFragmentList.add(fragment);
            mTitleList.add(titleName);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setViewPagerTitles() {
        //Indian Team
        View indiaView = LayoutInflater.from(this).inflate(R.layout.tab_layout_row_text, null);
        indianTeamTabText = indiaView.findViewById(R.id.tab_txt);
        indianTeamTabText.setText(indianTeamList.get(0).getmTeamName());

        (teamTabView.getTabAt(Constants.TeamTab.India)).setCustomView(indiaView);

        //NewZealand Team
        View newZealandView = LayoutInflater.from(this).inflate(R.layout.tab_layout_row_text, null);
        nzTeamTabText = newZealandView.findViewById(R.id.tab_txt);
        nzTeamTabText.setText(nzTeamList.get(0).getmTeamName());
        (teamTabView.getTabAt(Constants.TeamTab.NewZealand)).setCustomView(newZealandView);
    }
}
