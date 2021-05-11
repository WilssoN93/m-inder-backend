package movie.database.minder.entities.requests;

import movie.database.minder.entities.db.User;

public class AddUserRequest {

    private String groupId;
    private User user;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
