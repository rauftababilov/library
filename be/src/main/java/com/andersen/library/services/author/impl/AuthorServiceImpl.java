package com.andersen.library.services.author.impl;

import com.andersen.library.config.CacheConfig;
import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.author.AuthorService;
import com.andersen.library.services.author.AuthorValidatorService;
import com.andersen.library.services.author.model.AuthorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    private final AuthorMapper mapper;

    private final AuthorValidatorService validatorService;

    @Override
    public Page<AuthorDto> getAll(Pageable pageable) {
        return repository.findAllByDeletedIsFalse(pageable).map(mapper::toDto);
    }

    @Override
    public List<AuthorDto> getAllById(Collection<Long> ids) {
        return repository.findAllByIdIn(ids).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Cacheable(value = CacheConfig.AUTHOR_BY_ID_CACHE, key = "#id", condition = "#allowDeleted == true")
    @Override
    public AuthorDto get(Long id, boolean allowDeleted) {
        AuthorDto authorDto = repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);

        if (!allowDeleted && authorDto.isDeleted()) {
            throw ExceptionType.AUTHOR_DELETED.exception();
        }

        return authorDto;
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
        Author author = new Author();

        validatorService.throwIfAuthorAlreadyExists(authorDto.getFullName());

        author.setFullName(authorDto.getFullName());

        author = repository.save(author);

        return mapper.toDto(author);
    }

    @CacheEvict(value = CacheConfig.AUTHOR_BY_ID_CACHE, key = "#id")
    @Override
    public AuthorDto update(Long id, AuthorDto authorDto) {
        Author author = repository.findById(id).orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);

        validateAuthorOnUpdate(authorDto, author);

        author.setFullName(authorDto.getFullName());

        repository.save(author);

        return mapper.toDto(author);
    }

    @CacheEvict(value = CacheConfig.AUTHOR_BY_ID_CACHE, key = "#id")
    @Override
    public void softDelete(Long id) {
        Author author = repository.findById(id).orElseThrow(ExceptionType.AUTHOR_NOT_FOUND::exception);

        validatorService.throwIfAuthorDeleted(author.isDeleted());

        author.setDeleted(true);

        repository.save(author);
    }

    private void validateAuthorOnUpdate(AuthorDto authorDto, Author author) {
        validatorService.throwIfAuthorDeleted(author.isDeleted());
        validatorService.throwIfAuthorNameChangeNotAllowed(author.getFullName(), authorDto.getFullName());
    }

}
