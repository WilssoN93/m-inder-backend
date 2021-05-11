package movie.database.minder.services;

import movie.database.minder.entities.db.Genre;
import movie.database.minder.entities.db.Group;
import movie.database.minder.entities.db.Movie;
import movie.database.minder.entities.db.User;
import movie.database.minder.entities.requests.AddUserRequest;
import movie.database.minder.entities.response.GroupResponse;
import movie.database.minder.exceptions.NotFoundException;
import movie.database.minder.helpers.GroupHelper;
import movie.database.minder.helpers.MovieHelper;
import movie.database.minder.repositories.GenreRepository;
import movie.database.minder.repositories.GroupRepository;
import movie.database.minder.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {


    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private UserService userService;

    public GroupResponse createGroup(final Group group) {
        for (User groupUser : group.getUsers()) {
            Optional<User> user = userService.getUser(groupUser.getId());
            if (user.isEmpty()) {
                userService.saveUser(groupUser);
            }
        }
        return GroupHelper.mapToResponse(groupRepository.save(group));
    }

    public List<GroupResponse> getGroupsByUserId(final String id) {
        return groupRepository.getGroupByUserId(id).stream()
                .map(GroupHelper::mapToResponse)
                .collect(Collectors.toList());
    }

    public GroupResponse addNewUser(final AddUserRequest request) {
        Group group = groupRepository.getOne(request.getGroupId());
        String id = request.getUser().getId();
        Optional<User> user = userService.getUser(id);
        List<User> users = group.getUsers();
        if (user.isPresent()) {
            User existingUser = user.get();
            if (!users.contains(existingUser)) {
                users.add(existingUser);
            }
        } else {
            userService.saveUser(request.getUser());
            users.add(request.getUser());
        }
        group.setUsers(users);
        return GroupHelper.mapToResponse(groupRepository.save(group));

    }

    public List<User> getUsersByGroupId(final String id) {
        Optional<Group> group = groupRepository.findById(id);

        if (group.isPresent()) {
            return group.get().getUsers();
        }
        throw new NotFoundException("Group with id of: " + id + ", Was not found!");
    }

    public List<GroupResponse> matchMovie(final String userId, final String movieId) {

        List<Group> groups = groupRepository.getGroupByUserId(userId);

        if (groups.size() == 0){
            throw new NotFoundException("No Groups with with userid of: " + userId + " did exist!");
        }

        for (Group group : groups) {
            List<Movie> moviesMatched = group.getMatchedMovies();
            if (!moviesMatched.contains(movieRepository.getOne(movieId))) {
                List<User> users = group.getUsers();
                List<Boolean> matchedMovies = new ArrayList<>();
                if (users.size() > 1) {
                    for (User user : users) {
                        Optional<Movie> matched = user.getWatchList()
                                .stream()
                                .filter(movie -> movie.getId().equals(movieId))
                                .findAny();
                        if (matched.isPresent()) {
                            matchedMovies.add(true);
                        }
                    }
                    if (matchedMovies.size() == users.size()) {
                        Optional<Movie> movie = movieRepository.findById(movieId);
                        if (movie.isPresent()) {
                            group.getMatchedMovies().add(movie.get());
                            groupRepository.save(group);
                        } else {
                            throw new NotFoundException("Movie with id of: " + movieId + ", Was not found!");
                        }
                    }
                }
            }
        }
        return groups.stream()
                .map(GroupHelper::mapToResponse)
                .collect(Collectors.toList());
    }

    public GroupResponse matchGroup(final String groupId) {

        Optional<Group> group = groupRepository.findById(groupId);
        if (group.isPresent()) {
            List<List<Movie>> listMatchedMovies = group.get().getUsers().stream()
                    .map(user -> MovieHelper.matchUser(user, group.get().getUsers()))
                    .collect(Collectors.toList());
            List<Movie> allMatchedMovies = new ArrayList<>();
            for (List<Movie> movies : listMatchedMovies) {
                allMatchedMovies.addAll(movies);
            }
            group.get().setMatchedMovies(new ArrayList<>());
            group.get().getMatchedMovies().addAll(allMatchedMovies
                    .stream()
                    .distinct()
                    .collect(Collectors.toList()));
            groupRepository.save(group.get());
            return GroupHelper.mapToResponse(group.get());
        }
        throw new NotFoundException("Group with id of: " + groupId + ", Was not found!");
    }

    public Group getGroup(final String id) {

        Optional<Group> group = groupRepository.findById(id);

        if (group.isPresent()) {
            return group.get();
        }
        throw new NotFoundException("Group with id of: " + id + ", Was not found!");
    }

    private Movie saveMovie(final Movie movie){
        Optional<Movie> existingMovie = movieRepository.findById(movie.getId());
        return existingMovie.orElseGet(() -> movieRepository.save(movie));
    }
}
