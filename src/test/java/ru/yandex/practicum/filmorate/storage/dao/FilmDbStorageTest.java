package ru.yandex.practicum.filmorate.storage.dao;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmDbStorageTest {
    private final FilmDbStorage filmDbStorage;

    @Test
    void getById() {
//        Film film = new Film();
//        film.
//    user.setLogin("dolore");
//        user.setName("Name dolore");
//        user.setBirthday(LocalDate.of(1997, 11, 29));
//        filmDbStorage.create(user);
//
//        List<Film> films = filmDbStorage.getAll();
//        assertNotNull(films);
//        assertEquals(1, films.size());
//        assertEquals("mail@mail.ru", film.getName());
//
//        Optional<User> userOptional = Optional.of(film);
//
//        assertThat(userOptional)
//                .isPresent()
//                .hasValueSatisfying(u ->
//                        assertThat(u).hasFieldOrPropertyWithValue("id", 1l)
//                );
    }
}