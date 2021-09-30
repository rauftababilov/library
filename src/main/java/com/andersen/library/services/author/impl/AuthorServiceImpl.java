package com.andersen.library.services.author.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.author.AuthorDto;
import com.andersen.library.services.author.AuthorFilterDto;
import com.andersen.library.services.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorValidatorService authorValidatorService;

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        Author author = new Author();
        authorValidatorService.throwIfBooksIncorrect(authorDto.getBookIds());
        author.setFullName(authorDto.getFullName());
        author.setBookIds(authorDto.getBookIds());
        authorRepository.save(author);

        return authorMapper.toDto(author);
    }

    @Override
    public Page<AuthorDto> findAllByFilter(AuthorFilterDto authorFilterDto, Pageable pageable) {
        return authorRepository.findAllByFilter(authorFilterDto.getName(), pageable)
                .map(authorMapper::toDto);
    }

    @Override
    public AuthorDto findById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDto)
                .orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);
    }

    @Override
    public AuthorDto update(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id).orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);

        authorValidatorService.throwIfFullNameIncorrect(authorDto.getFullName());
        authorValidatorService.throwIfBooksIncorrect(authorDto.getBookIds());

        author.setFullName(authorDto.getFullName());
        author.setBookIds(authorDto.getBookIds());

        authorRepository.save(author);

        return authorMapper.toDto(author);
    }

    @Override
    public void delete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);

        authorRepository.delete(author);
    }
}





