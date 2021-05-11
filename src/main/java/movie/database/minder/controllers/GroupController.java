package movie.database.minder.controllers;

import movie.database.minder.entities.db.Group;
import movie.database.minder.entities.db.User;
import movie.database.minder.entities.requests.AddUserRequest;
import movie.database.minder.entities.response.GroupResponse;
import movie.database.minder.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("group")
@RestController
@CrossOrigin
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<GroupResponse> createGroup(final @RequestBody Group group) {
        return ResponseEntity.ok(groupService.createGroup(group));
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<GroupResponse>> getById(final @PathVariable String id) {
        return ResponseEntity.ok(groupService.getGroupsByUserId(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<Group> getGroupById(final @PathVariable String id) {
        return ResponseEntity.ok(groupService.getGroup(id));
    }

    @PostMapping("user")
    public ResponseEntity<GroupResponse> addUserToGroup(final @RequestBody AddUserRequest request) {
        return ResponseEntity.ok(groupService.addNewUser(request));
    }

    @GetMapping("users/{id}")
    public ResponseEntity<List<User>> getUsersByGroupId(final @PathVariable String id) {
        return ResponseEntity.ok(groupService.getUsersByGroupId(id));
    }

    @GetMapping("match/{userId}/{movieId}")
    public ResponseEntity<List<GroupResponse>> matchMovieWithGroupIdAndMovieId(final @PathVariable("userId") String userId,
                                                                         final @PathVariable("movieId") String movieId) {
        return ResponseEntity.ok(groupService.matchMovie(userId, movieId));
    }

    @GetMapping("match/{groupId}")
    public ResponseEntity<GroupResponse> matchMovieWithGroupId(final @PathVariable("groupId") String groupId) {
        return ResponseEntity.ok(groupService.matchGroup(groupId));
    }

}
