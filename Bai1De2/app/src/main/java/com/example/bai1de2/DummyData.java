package com.example.bai1de2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyData {
    public interface FavoriteChangeListener {
        void onFavoriteChange(Song song, boolean isFavorite);
    }

    private static List<FavoriteChangeListener> mListeners = new ArrayList<>();

    public static void registerFavoritesChange(FavoriteChangeListener listener) {
        mListeners.add(listener);
    }

    public static final Song[] allSongs = new Song[]{
            new Song("Savage", "Megan Thee Stallion"),
            new Song("Rain On Me", "Lady Gaga"),
            new Song("Breath Deeper", "Tame Impala"),
            new Song("You Should Be Sad", "Halsey"),
            new Song("No Time To Die", "Billie Eilish"),
            new Song("Delete Forever", "Grimes")
    };

    public static void favoriteSong(Song song, boolean isFavorite) {
        song.isFavorite = isFavorite;
        for (FavoriteChangeListener listener : mListeners) {
            listener.onFavoriteChange(song, isFavorite);
        }
    }

    public static Song[] getFavorites() {
        return Arrays.stream(allSongs)
                .filter(song -> song.isFavorite)
                .toArray(Song[]::new);
    }
}
