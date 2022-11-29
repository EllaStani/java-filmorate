package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.*;

import java.util.List;

@Service
public class FilmService {
    private FilmStorage filmStorage;
    private UserStorage userStorage;
    private LikeStorage likeStorage;
    private GenreStorage genreStorage;
    private MpaStorage mpaStorage;

    public FilmService(FilmStorage filmStorage, UserStorage userStorage, LikeStorage likeStorage,
                       GenreStorage genreStorage, MpaStorage mpaStorage) {
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
        this.likeStorage = likeStorage;
        this.genreStorage = genreStorage;
        this.mpaStorage = mpaStorage;
    }

    public Film create(Film film) {
        if (filmStorage.getAll().contains(film)) {
            throw new ValidationException(String.format("Фильм {} - уже есть в фильмотеке", film));
        }
        return filmStorage.create(film);
    }

    public Film update(Film film) {
        getFilmNotNull(film.getId());
        return filmStorage.update(film);
    }

    public Film getById(Long filmId) {
        final Film film = getFilmNotNull(filmId);
        return film;
    }

    public List<Film> getAll() {
        return filmStorage.getAll();
    }

    public Genre getGenreById(int genreId) {
        return genreStorage.getById(genreId);
    }

    public List<Genre> getAllGenres() {
        return genreStorage.getAll();
    }

    public Mpa getMpaById(int mpaId) {
        return mpaStorage.getById(mpaId);
    }

    public List<Mpa> getAllMpa() {
        return mpaStorage.getAll();
    }

    public Film deleteById(Long filmId) {
        final Film film = getFilmNotNull(filmId);
        return filmStorage.deleteById(filmId);
    }

    public void addLike(long filmId, long userId) {
        Film film = getFilmNotNull(filmId);
        User user = userStorage.getById(userId);
        filmStorage.addLike(film, user);

    }

    public void deleteLike(long filmId, long userId) {
        getFilmNotNull(filmId);
        userStorage.getById(userId);
        likeStorage.deleteLike(filmId, userId);
    }

    public List<Film> getPopular(String count) {
        return likeStorage.getPopular(count);
    }

    private Film getFilmNotNull(long filmId) {
        Film film = filmStorage.getById(filmId);
        if (film == null) {
            throw new NotFoundException(String.format("Фильм с id=%s не найден", filmId));
        }
        return film;
    }
}
