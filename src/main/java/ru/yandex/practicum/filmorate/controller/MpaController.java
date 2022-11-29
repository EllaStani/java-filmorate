package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mpa")
public class MpaController {
    private FilmService filmService;

    public MpaController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{mpaId}")
    public Mpa getMpaById(@PathVariable int mpaId) {
        Mpa mpa = filmService.getMpaById(mpaId);
        log.info("Get-запрос: MPA с id = {} : {}", mpaId, mpa);
        return mpa;
    }

    @GetMapping()
    public List<Mpa> getAllMpa() {
        List<Mpa> mpas = filmService.getAllMpa();
        log.info("Get-запрос: всего MPA = {} : {}", mpas.size(), mpas);
        return mpas;
    }
}
