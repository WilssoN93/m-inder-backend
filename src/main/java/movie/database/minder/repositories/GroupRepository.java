package movie.database.minder.repositories;

import movie.database.minder.entities.db.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, String> {

    @Query(value = "SELECT usergroup.id, group_name, admin FROM usergroup\n" +
            "LEFT JOIN usergroup_users uu on usergroup.id = uu.group_id\n" +
            "LEFT JOIN enduser e on uu.users_id = e.id\n" +
            "where e.id = :id", nativeQuery = true)
    List<Group> getGroupByUserId(final String id);

}
