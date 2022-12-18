package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface FilmGenresStorage {
    List<Genre> getGenresByFilmId(Long filmId);

    void addGenre(long filmId, long genreId);

    void deleteGenre(long filmId, long genreId);

}
