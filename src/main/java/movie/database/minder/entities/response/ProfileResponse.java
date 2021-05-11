package movie.database.minder.entities.response;

import movie.database.minder.entities.db.Genre;
import movie.database.minder.entities.db.Movie;

import java.util.List;

public class ProfileResponse {

    private String favoriteGenre;
    private List<MovieResponse> watchList;
    private List<GroupResponse> groups;


    public String getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(String favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public List<MovieResponse> getWatchList() {
        return watchList;
    }

    public void setWatchList(List<MovieResponse> watchList) {
        this.watchList = watchList;
    }

    public List<GroupResponse> getGroupNames() {
        return groups;
    }

    public void setGroupNames(List<GroupResponse> groupNames) {
        this.groups = groupNames;
    }
}
