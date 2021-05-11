package movie.database.minder.services;

import movie.database.minder.entities.db.Genre;
import movie.database.minder.entities.db.Movie;
import movie.database.minder.entities.db.User;
import movie.database.minder.entities.requests.AddMovieRequest;
import movie.database.minder.entities.response.ProfileResponse;
import movie.database.minder.exceptions.NotFoundException;
import movie.database.minder.helpers.MovieHelper;
import movie.database.minder.repositories.GenreRepository;
import movie.database.minder.repositories.MovieRepository;
import movie.database.minder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    public Optional<User> getUser(final String id) {
        return userRepository.findById(id);
    }

    public User addMovieByUserId(final String id, final AddMovieRequest request) {
        Optional<User> potentialUser = userRepository.findById(id);
        if (potentialUser.isPresent()) {
            Optional<Movie> existingMovie = movieRepository.findById(request.getId());
            List<Genre> genres = genreRepository.findAllById(request.getGenres());
            Movie movie = MovieHelper.mapToMovie(request, genres);
            if (existingMovie.isEmpty()) {
                movieRepository.save(movie);
            }
            User user = potentialUser.get();
            if (user.getWatchList() != null && !user.getWatchList().contains(movie)) {
                user.getWatchList().add(movie);
                userRepository.save(user);
            }
            return user;
        }
        throw new NotFoundException("User with user id of: " + id + " was not found!");
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public ProfileResponse fetchProfile(final String userId) {
        ProfileResponse profile = new ProfileResponse();
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            profile.setFavoriteGenre(genreRepository.getGenreName(userId));
            profile.setWatchList(MovieHelper.mapToMovieListResponse(user.get().getWatchList()));
            profile.setGroupNames(groupService.getGroupsByUserId(userId));
            return profile;
        }
        throw new NotFoundException("No user with id of: " + userId + " was found!");
    }
}
