package com.andersen.library.services.author.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.author.AuthorService;
import com.andersen.library.services.author.AuthorValidatorService;
import com.andersen.library.services.author.model.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    private final AuthorValidatorService validatorService;

    @Override
    public Page<AuthorDto> getAll(Pageable pageable) {
        return authorRepository.findAllByDeletedIsFalse(pageable).map(authorMapper::toDto);
    }

    @Override
    public AuthorDto get(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDto)
                .orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author author = new Author();

        author.setFullName(authorDto.getFullName());

        author = authorRepository.save(author);

        return authorMapper.toDto(author);
    }

    @Override
    public AuthorDto update(Long id, AuthorDto authorDto) {
        Author author = authorRepository.findById(id).orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);

        validatorService.throwIfAuthorDeleted(author.isDeleted());

        author.setFullName(authorDto.getFullName());

        authorRepository.save(author);

        return authorMapper.toDto(author);
    }

    @Override
    public void softDelete(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);

        validatorService.throwIfAuthorDeleted(author.isDeleted());

        author.setDeleted(true);

        authorRepository.save(author);
    }

}
