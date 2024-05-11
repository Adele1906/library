package com.example.bookservice.service;

import com.example.bookservice.model.Book;
import com.example.bookservice.model.BookFormular;
import com.example.bookservice.repository.BookRepository;
import com.example.bookservice.repository.FormularRepository;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    FormularRepository formularRepository;

    public List<Book> getAllBooks(){
       return bookRepository.findAll();
    }
    public void addBook(int code, String name, String description){

        Book newBook=new Book();
        newBook.setCode(code);
        if(name != null && !name.isEmpty()){
            newBook.setName(name);
        }
        if(name != null && !name.isEmpty()){
            newBook.setDescription(description);
        }
            bookRepository.save(newBook);
    }
    public void deleteBook(int code){
        System.out.println(" in service delete code="+code);
        bookRepository.deleteByCode(code);
    }
    //запись о выдаче книги
    public void addReportInFormular(int code, int userId) {
        BookFormular bookFormular=new BookFormular();
        bookFormular.setBook_code(code);
        bookFormular.setUser_id(userId);
        Date date_begin=new Date();
        bookFormular.setDate_begin(date_begin);
        Date date_end= DateUtils.addDays(date_begin, 7);
        bookFormular.setDate_end(date_end);

        formularRepository.save(bookFormular);
    }
    public List <BookFormular> getAllFormulars(){
        return (List<BookFormular>) formularRepository.findAll();
    }
    public void updateStatusReturn(int id){
        Optional<BookFormular> bookFormular= formularRepository.findById(id);
        bookFormular.orElseThrow().setReturn_status(1);
        formularRepository.save(bookFormular.orElseThrow());
    }
}
