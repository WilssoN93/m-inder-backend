package movie.database.minder.controllers;

import movie.database.minder.entities.db.Genre;
import movie.database.minder.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("genre")
@RestController
@CrossOrigin
public class GenreController {

    @Autowired
    private GenreService genreService;


    @PostMapping
    public ResponseEntity saveGenre(final @RequestBody List<Genre> genres) {
        genreService.saveAllGenres(genres);
        return ResponseEntity.ok().build();
    }

}
