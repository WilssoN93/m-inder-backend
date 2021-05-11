package movie.database.minder.repositories;

import movie.database.minder.entities.db.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Query(value = "select name, COUNT(name) as times\n" +
            "            from genre\n" +
            "            left join movie_genres mg on genre.id = mg.genres_id\n" +
            "            left join movie m on mg.movie_id = m.id\n" +
            "            left join enduser_watch_list ewl on m.id = ewl.watch_list_id\n" +
            "            where user_id = :userId\n" +
            "            group by name\n" +
            "            order by times DESC\n" +
            "            limit 1", nativeQuery = true)
    String getGenreName(final String userId);

}
