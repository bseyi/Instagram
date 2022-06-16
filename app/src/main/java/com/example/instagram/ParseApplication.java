package com.example.instagram;


import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("6ZqICZUidMk4Foz6ClmT9bGVhZXis0Ma8zJOLaOQ")
                .clientKey("DYGjNYvrgJswMxcU6XJq26rAFu8K9Qnl2R9UHMy4")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
