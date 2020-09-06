package com.json_dynamic_key_parsing.json_feed.model;

public class TeamList {
    private String mTeamName;
    private String mPlayerName;
    private String mPlayerPosition;
    private boolean mIsCaptain;
    private boolean mIsKeeper;

    public TeamList(String teamName, String playerName, String playerPosition, boolean isCaptain,
                    boolean isKeeper) {
        mTeamName = teamName;
        mPlayerName = playerName;
        mPlayerPosition = playerPosition;
        mIsCaptain = isCaptain;
        mIsKeeper = isKeeper;
    }

    public String getmTeamName() {
        return mTeamName;
    }

    public String getmPlayerName() {
        return mPlayerName;
    }

    public String getmPlayerPosition() {
        return mPlayerPosition;
    }

    public boolean ismIsCaptain() {
        return mIsCaptain;
    }

    public boolean ismIsKeeper() {
        return mIsKeeper;
    }

    @Override
    public String toString() {
        return "TeamList{" +
                "mTeamName='" + mTeamName + '\'' +
                ", mPlayerName='" + mPlayerName + '\'' +
                ", mPlayerPosition='" + mPlayerPosition + '\'' +
                ", mIsCaptain=" + mIsCaptain +
                ", mIsKeeper=" + mIsKeeper +
                '}';
    }
}
