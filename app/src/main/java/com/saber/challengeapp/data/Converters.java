package com.saber.challengeapp.data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static GitHubUser fromString(String value) {
        Type listType = new TypeToken<String>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromGitHubUser(GitHubUser user) {
        Gson gson = new Gson();
        String json = gson.toJson(user);
        return json;
    }
}
