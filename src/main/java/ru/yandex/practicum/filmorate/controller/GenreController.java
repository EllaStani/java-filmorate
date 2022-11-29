package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/genres")
public class GenreController {
    private FilmService filmService;

    public GenreController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{genreId}")
    public Genre getGenreById(@PathVariable int genreId) {
        Genre genre = filmService.getGenreById(genreId);
        log.info("Get-запрос: жанр с id = {} : {}", genreId, genre);
        return genre;
    }

    @GetMapping()
    public List<Genre> getAllGenres() {
        List<Genre> genres = filmService.getAllGenres();
        log.info("Get-запрос: всего жанров = {} : {}", genres.size(), genres);
        return genres;
    }
}
