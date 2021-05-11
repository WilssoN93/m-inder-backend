package movie.database.minder.entities.db;



import movie.database.minder.entities.requests.AddMovieRequest;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    private String id;
    private String title;
    private String overview;
    private String posterPath;
    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<Genre> genres;

    public Movie(final AddMovieRequest request,final List<Genre> genres){
        this.setGenres(genres);
        this.setId(request.getId());
        this.setOverview(request.getOverview());
        this.setTitle(request.getTitle());
        this.setPosterPath(request.getPosterPath());

    }

    public Movie(){}

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

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
