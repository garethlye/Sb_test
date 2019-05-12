package com.my.sb_test.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.JsonSyntaxException;
import com.my.sb_test.api.Gson.GsonUtils;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.io.Serializable;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
@Gson.TypeAdapters
@Value.Immutable
public abstract class Movie implements Parcelable {

    @Nullable
    public abstract Double vote_count();

    @Nullable
    public abstract Double id();

    @Nullable
    public abstract Double vote_average();

    @Nullable
    public abstract String title();

    @Nullable
    public abstract String poster_path();

    @Nullable
    public abstract String original_language();

    @Nullable
    public abstract String overview();

    @Nullable
    public abstract String release_date();

    @Nullable
    public abstract String backdrop_path();

    // ----- Boilerplate for Parcelable ----- //
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(final Parcel parcel) {
            return fromJsonString(parcel.readString());
        }

        @Override
        public Movie[] newArray(final int i) {
            return new Movie[0];
        }
    };

    @Override
    public void writeToParcel(final Parcel parcel, final int flag) {
        parcel.writeString(toJsonString());
    }

    public String toJsonString() {
        return GsonUtils.getGson().toJson(this, Movie.class);
    }

    public static Movie fromJsonString(final String json) {
        try {
            return GsonUtils.getGson().fromJson(json, Movie.class);
        } catch (final JsonSyntaxException e) {
            return null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
    // ----- End Boilerplate for Parcelable ----- //

}
