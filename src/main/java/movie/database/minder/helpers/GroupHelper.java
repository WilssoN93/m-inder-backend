package movie.database.minder.helpers;

import movie.database.minder.entities.db.Group;
import movie.database.minder.entities.db.Movie;
import movie.database.minder.entities.db.User;
import movie.database.minder.entities.response.GroupResponse;

import java.util.ArrayList;
import java.util.List;

public final class GroupHelper {

    private GroupHelper() {

    }

    public static GroupResponse mapToResponse(final Group group) {
        GroupResponse response = new GroupResponse();
        response.setName(group.getGroupName());
        response.setId(group.getId());
        return response;
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

}

