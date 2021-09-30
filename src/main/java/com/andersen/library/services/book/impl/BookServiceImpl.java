package com.andersen.library.services.book.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.book.BookDto;
import com.andersen.library.services.book.BookFilterDto;
import com.andersen.library.services.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class BookServiceImpl implements BookService {

    private final BookRepository repository;

    private final BookMapper mapper;

    private final BookValidatorService validatorService;

    @Override
    public Page<BookDto> getAll(BookFilterDto filterDto, Pageable pageable) {
        return repository.findAllWithFilter(filterDto.getAuthorId(), filterDto.getPublishingHouseId(), pageable)
                .map(mapper::toDto);
    }

    @Override
    public BookDto get(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.BOOK_NOT_FOUND::exception);
    }

    @Override
    public BookDto create(BookDto dto) {
        Book book = new Book();

        validatorService.throwIfAuthorsIncorrect(dto.getAuthorIds());
        validatorService.throwIfPublishingHouseIncorrect(dto.getPublishingHouseId());
        validatorService.throwIfPublishYearIncorrect(dto.getPublishYear());

        book.setPublishYear(dto.getPublishYear());
        book.setAuthorIds(dto.getAuthorIds());
        book.setTitle(dto.getTitle());
        book.setPublishingHouseId(dto.getPublishingHouseId());

        book = repository.save(book);

        return mapper.toDto(book);
    }

    @Override
    public BookDto fullUpdate(Long id, BookDto dto) {
        Book book = repository.findById(id).orElseThrow(ExceptionType.BOOK_NOT_FOUND::exception);

        validatorService.throwIfAuthorsIncorrect(dto.getAuthorIds());
        validatorService.throwIfPublishingHouseIncorrect(dto.getPublishingHouseId());
        validatorService.throwIfPublishYearIncorrect(dto.getPublishYear());

        book.setPublishYear(dto.getPublishYear());
        book.getAuthorIds().clear();
        book.getAuthorIds().addAll(dto.getAuthorIds());
        book.setTitle(dto.getTitle());
        book.setPublishingHouseId(dto.getPublishingHouseId());

        book = repository.save(book);

        return mapper.toDto(book);
    }

    @Override
    public void delete(Long id) {
        Book book = repository.findById(id).orElseThrow(ExceptionType.BOOK_NOT_FOUND::exception);

        repository.delete(book);
    }

}
