package movie.database.minder.controllers;

import movie.database.minder.entities.db.Movie;
import movie.database.minder.entities.db.User;
import movie.database.minder.entities.requests.AddMovieRequest;
import movie.database.minder.entities.response.ProfileResponse;
import movie.database.minder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public User getUser(final @PathVariable String id) {
        return userService.getUser(id).orElse(null);
    }

    @PostMapping("{id}")
    public User addNewMovieFromUserId(final @PathVariable String id, @RequestBody final AddMovieRequest request) {
        return userService.addMovieByUserId(id, request);
    }

    @GetMapping("profile/{id}")
    public ResponseEntity<ProfileResponse> fetchProfile(final @PathVariable("id") String id){
        return ResponseEntity.ok(userService.fetchProfile(id));
    }
}
