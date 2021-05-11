package movie.database.minder.helpers;

import movie.database.minder.entities.db.Genre;
import movie.database.minder.entities.db.Movie;
import movie.database.minder.entities.db.User;
import movie.database.minder.entities.requests.AddMovieRequest;
import movie.database.minder.entities.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class MovieHelper {

    private MovieHelper(){

    }

    public static List<Movie> matchUser(final User user, List<User> users) {
        List<Movie> matchedMovies = new ArrayList<>();
        for (Movie movie : user.getWatchList()) {
            if (!users.isEmpty()) {
                boolean matched = users.stream().allMatch(u -> u.getWatchList().contains(movie));
                if (matched) {
                    matchedMovies.add(movie);
                }
            }
        }
        return matchedMovies;
    }

    public static Movie mapToMovie(final AddMovieRequest request, List<Genre> genres){
        return new Movie(request, genres);
    }

    public static List<MovieResponse> mapToMovieListResponse(final List<Movie> watchList) {
        return watchList.stream()
                .map(MovieHelper::mapToMovieResponse)
                .collect(Collectors.toList());
    }

    public static MovieResponse mapToMovieResponse(final Movie movie) {
        return new MovieResponse(movie);
    }
}
