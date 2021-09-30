package com.andersen.library.services.author.impl;

import com.andersen.library.services.author.model.AuthorDto;
import com.andersen.library.services.author.AuthorService;
import com.andersen.library.services.author.AuthorUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
class AuthorController {

    private final AuthorService authorService;

    @GetMapping(AuthorUrl.GET)
    public AuthorDto get(@PathVariable Long authorId) {
        return authorService.get(authorId);
    }

    @GetMapping(AuthorUrl.FIND)
    public Page<AuthorDto> getAll(Pageable pageable) {
        return authorService.getAll(pageable);
    }

    @PostMapping(AuthorUrl.CREATE)
    public AuthorDto create(@Valid @RequestBody AuthorDto authorDto) {
        return authorService.create(authorDto);
    }

    @PutMapping(AuthorUrl.UPDATE)
    public AuthorDto update(@PathVariable Long authorId, @Valid @RequestBody AuthorDto authorDto) {
        return authorService.update(authorId, authorDto);
    }

    @DeleteMapping(AuthorUrl.DELETE)
    public void delete(@PathVariable Long authorId) {
        authorService.softDelete(authorId);
    }

}
