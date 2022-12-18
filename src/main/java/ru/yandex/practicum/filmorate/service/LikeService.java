package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.LikeStorage;
import ru.yandex.practicum.filmorate.storage.dao.FilmDbStorage;

import java.util.List;

@Service
public class LikeService {
    private LikeStorage likeStorage;

    @Autowired
    public LikeService(LikeStorage likeStorage) {
        this.likeStorage = likeStorage;
    }

    public void addLike(long filmId, long userId) {
        likeStorage.addLike(filmId, userId);
    }

    public void deleteLike(long filmId, long userId) {
        likeStorage.deleteLike(filmId, userId);
    }

    public List<Film> getPopular(String count) {
        return likeStorage.getPopular(count);
    }
}
