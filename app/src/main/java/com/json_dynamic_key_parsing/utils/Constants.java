package com.json_dynamic_key_parsing.utils;

public interface Constants {
    String BASE_URL = "https://cricket.yahoo.net";

    interface Retrofit {
        String RetrofitBaseUrl = BASE_URL;
    }

    interface URL{
        String FEED_URL = "/sifeeds/cricket/live/json/nzin01312019187360.json";
    }

    interface TeamTab{
        int India = 0;
        int NewZealand = 1;
    }
}
