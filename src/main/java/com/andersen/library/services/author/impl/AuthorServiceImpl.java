package com.andersen.library.services.author.impl;

import com.andersen.library.services.author.AuthorService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author save(@NonNull Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public List<Author> findAllByFirstName(String firstName) {
        return authorRepository.findAll();
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public void delete(Long id) {
         authorRepository.deleteById(id);
    }
}
