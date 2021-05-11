package movie.database.minder.services;

import movie.database.minder.entities.db.Genre;
import movie.database.minder.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void saveAllGenres(final List<Genre> genres){
        genreRepository.saveAll(genres);
    }

}
