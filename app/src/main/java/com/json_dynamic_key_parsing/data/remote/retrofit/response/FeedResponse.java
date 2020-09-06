package com.json_dynamic_key_parsing.data.remote.retrofit.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class FeedResponse {
    @SerializedName("Matchdetail")
    private MatchDetail matchdetail;

    @SerializedName("Teams")
    private Map<String, DataValues> teams;

    public MatchDetail getMatchdetail() {
        return matchdetail;
    }

    public Map<String, DataValues> getTeams() {
        return teams;
    }

    @Override
    public String toString() {
        return "FeedResponse{" +
                "matchdetail=" + matchdetail +
                ", teams=" + teams +
                '}';
    }

    public static class MatchDetail{
        @SerializedName("Team_Home")
        private String teamHome;

        @SerializedName("Team_Away")
        private String tamAway;

        public String getTeamHome() {
            return teamHome;
        }

        public String getTamAway() {
            return tamAway;
        }

        @Override
        public String toString() {
            return "MatchDetail{" +
                    "teamHome='" + teamHome + '\'' +
                    ", tamAway='" + tamAway + '\'' +
                    '}';
        }
    }

    public class DataValues{
        @SerializedName("Name_Full")
        private String nameFull;

        @SerializedName("Players")
        private Map<String, PlayersValues> playersValuesMap;

        public String getNameFull() {
            return nameFull;
        }

        public Map<String, PlayersValues> getPlayersValuesMap() {
            return playersValuesMap;
        }

        @Override
        public String toString() {
            return "DataValues{" +
                    "nameFull='" + nameFull + '\'' +
                    ", playersValuesMap=" + playersValuesMap +
                    '}';
        }
    }

    public static class PlayersValues{
        @SerializedName("Position")
        private String position;

        @SerializedName("Name_Full")
        private String namefull;

        @SerializedName("Iscaptain")
        private boolean isCaptain;

        @SerializedName("Iskeeper")
        private boolean isKeeper;

        public String getPosition() {
            return position;
        }

        public String getNamefull() {
            return namefull;
        }

        public boolean isCaptain() {
            return isCaptain;
        }

        public boolean isKeeper() {
            return isKeeper;
        }

        @Override
        public String toString() {
            return "PlayersValues{" +
                    "position='" + position + '\'' +
                    ", namefull='" + namefull + '\'' +
                    ", isCaptain=" + isCaptain +
                    ", isKeeper=" + isKeeper +
                    '}';
        }
    }
}