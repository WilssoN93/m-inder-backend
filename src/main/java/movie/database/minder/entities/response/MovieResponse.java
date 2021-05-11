package movie.database.minder.entities.response;

import movie.database.minder.entities.db.Movie;

public class MovieResponse {

    private String id;
    private String title;
    private String overview;
    private String posterPath;

    public MovieResponse(final Movie movie){
        this.setId(movie.getId());
        this.setOverview(movie.getOverview());
        this.setTitle(movie.getTitle());
        this.setPosterPath(movie.getPosterPath());
    }

    public MovieResponse(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
