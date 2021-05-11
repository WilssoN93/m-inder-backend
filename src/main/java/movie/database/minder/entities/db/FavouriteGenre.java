package movie.database.minder.entities.db;

import javax.persistence.*;

@SqlResultSetMapping(name = "genre mapping",
        classes = @ConstructorResult(
                targetClass = FavouriteGenre.class,
                columns = {@ColumnResult(name = "name"),
                        @ColumnResult(name = "times")}
        )
)
public class FavouriteGenre {

    @Id
    private String name;
    private int times;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
