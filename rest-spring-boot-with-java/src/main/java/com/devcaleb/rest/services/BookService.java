package com.devcaleb.rest.services;

import com.devcaleb.rest.controllers.BookController;
import com.devcaleb.rest.data.dto.v1.BookDTO;
import com.devcaleb.rest.exceptions.RequiredObjectIsNullException;
import com.devcaleb.rest.exceptions.ResourceNotFoundException;
import com.devcaleb.rest.mapper.ObjectMapper;
import com.devcaleb.rest.model.Book;
import com.devcaleb.rest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookDTO> findAll() {
        var books = ObjectMapper.parseListObjects(repository.findAll(), BookDTO.class);
        books.forEach(b -> addHateoasLinks(b));
        return books;
    }

    public BookDTO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        var dto = ObjectMapper.parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO create(BookDTO book) {

        if(book == null) throw new RequiredObjectIsNullException();
        var entity = ObjectMapper.parseObject(book, Book.class);
        var dto = ObjectMapper.parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public BookDTO update(BookDTO book) {

        if(book == null) throw new RequiredObjectIsNullException();

        Book entity = repository.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = ObjectMapper.parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(entity);
    }

    private static void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).insert(dto)).withRel("insert").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
