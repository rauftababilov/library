package com.andersen.library.services.book.impl;

import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.services.book.BookService;
import com.andersen.library.services.book.BookValidatorService;
import com.andersen.library.services.book.model.BookDto;
import com.andersen.library.services.book.model.BookFilterDto;
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
        return repository.findAllByFilter(filterDto.getAuthorId(), filterDto.getPublishingHouseId(), pageable)
                .map(mapper::toDto);
    }

    @Override
    public BookDto get(Long id, boolean allowDeleted) {
        BookDto bookDto = repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(ExceptionType.BOOK_NOT_FOUND::exception);

        if (!allowDeleted && bookDto.isDeleted()) {
            throw ExceptionType.BOOK_DELETED.exception();
        }

        return bookDto;
    }

    @Override
    public BookDto create(BookDto dto) {
        Book book = new Book();

        validateBookOnCreate(dto);

        mapper.populateEmptyBook(book, dto);

        book = repository.save(book);

        return mapper.toDto(book);
    }

    @Override
    public BookDto update(Long id, BookDto dto) {
        Book book = repository.findById(id).orElseThrow(ExceptionType.BOOK_NOT_FOUND::exception);

        validateBookOnUpdate(dto);

        mapper.updateBookData(book, dto);

        book = repository.save(book);

        return mapper.toDto(book);
    }

    @Override
    public void softDelete(Long id) {
        Book book = repository.findById(id).orElseThrow(ExceptionType.BOOK_NOT_FOUND::exception);

        validateOnBookDelete(id, book);

        book.setDeleted(true);

        repository.save(book);
    }

    private void validateOnBookDelete(Long id, Book book) {
        validatorService.throwIfBookGiven(id);
        validatorService.throwIfBookDeleted(book.isDeleted());
    }

    private void validateBookOnCreate(BookDto dto) {
        validatorService.throwIfAuthorsIncorrect(dto.getAuthorIds());
        validatorService.throwIfPublishingHouseIncorrect(dto.getPublishingHouseId());
        validatorService.throwIfPublishYearIncorrect(dto.getPublishYear());
    }

    private void validateBookOnUpdate(BookDto dto) {
        validateBookOnCreate(dto);
        validatorService.throwIfBookDeleted(dto.isDeleted());
    }

}
