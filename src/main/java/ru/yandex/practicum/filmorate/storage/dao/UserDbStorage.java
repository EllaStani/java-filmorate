package ru.yandex.practicum.filmorate.storage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.sql.*;
import java.util.List;

@Primary
@Component
public class UserDbStorage implements UserStorage {
    @Autowired
    FriendDbStorage friendDbStorage;
    private JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("user_id");
        long userId = simpleJdbcInsert.executeAndReturnKey(user.toMap()).longValue();
        user.setId(userId);
        return user;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE users SET email= ?, login = ?, user_name = ?, birthday = ?";
        jdbcTemplate.update(sql, user.getEmail(), user.getLogin(), user.getName(),
                user.getBirthday());
        return user;
    }

    @Override
    public User getById(long userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        List<User> users = jdbcTemplate.query(sql, (rs, rowNum) -> makeUser(rs, rowNum), userId);

        if (users.size() != 1) {
            throw new NotFoundException(String.format("Пользователь с id=%s не найден", userId));
        }
        return users.get(0);
    }

    @Override
    public List<User> getAll() {
        final String sql = "SELECT * FROM users";
        final List<User> users = jdbcTemplate.query(sql, UserDbStorage::makeUser);
        return users;
    }

    @Override
    public User deleteById(long userId) {
        final String sql = "DELETE FROM users WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
        return getById(userId);
    }

    @Override
    public void addFriend(User user, User film) {
        friendDbStorage.addFriend(user.getId(), film.getId());
    }

    @Override
    public void deleteFriend(User user, User film) {
        friendDbStorage.deleteFriend(user.getId(), film.getId());
    }

    static User makeUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong("user_id"),
                rs.getString("email"),
                rs.getString("login"),
                rs.getString("user_name"),
                rs.getDate("birthday").toLocalDate());
    }
}
