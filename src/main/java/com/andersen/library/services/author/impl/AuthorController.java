package com.andersen.library.services.author.impl;

import com.andersen.library.services.author.AuthorDto;
import com.andersen.library.services.author.AuthorFilterDto;
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
    public AuthorDto findById(@PathVariable Long authorId) {
        return authorService.findById(authorId);
    }

    @GetMapping(AuthorUrl.FIND)
    public Page<AuthorDto> findAllByFilter(AuthorFilterDto authorFilterDto, Pageable pageable) {
        return authorService.findAllByFilter(authorFilterDto, pageable);
    }

    @PostMapping(AuthorUrl.CREATE)
    public AuthorDto save(@Valid @RequestBody AuthorDto authorDto) {
        return authorService.save(authorDto);
    }

    @PatchMapping(AuthorUrl.UPDATE)
    public AuthorDto update(@PathVariable Long authorId, @Valid @RequestBody AuthorDto authorDto) {
        return authorService.update(authorId, authorDto);
    }

    @DeleteMapping(AuthorUrl.DELETE)
    public void delete(@PathVariable Long authorId) {
        authorService.delete(authorId);
    }
}
