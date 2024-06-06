package com.example.gateway_service.testauth.tst;

import com.example.gateway_service.testauth.tst.BookRepository;
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
        newBook.setOn_hands("0");
            bookRepository.save(newBook);
    }
    public void deleteBook(int code){
        System.out.println(" in service delete code="+code);
        bookRepository.deleteByCode(code);
    }
    //запись о выдаче книги
    public void addReportInFormular(int code, String userId) {
        BookFormular bookFormular=new BookFormular();
        bookFormular.setBook_code(code);
        bookFormular.setUser_id(userId);
        Date date_begin=new Date();
        bookFormular.setDate_begin(date_begin);
        Date date_end= DateUtils.addDays(date_begin, 7);
        bookFormular.setDate_end(date_end);
        bookFormular.setReturn_status(0);

        formularRepository.save(bookFormular);
    }
    public void addReturnBookInFormular(int id_formular) {
        Optional<BookFormular> bookFormular=formularRepository.findById(id_formular);
        BookFormular newForm=new BookFormular();
        if(bookFormular.isPresent()){
            newForm=bookFormular.get();
            newForm.setReturn_status(1);
            newForm.setDate_end(new Date());
            formularRepository.save(newForm);
        }
    }
    public List <BookFormular> getAllFormulars(){
        return  formularRepository.findAll();
    }
    public void updateStatusOnHands(int id){
        Optional<Book> book= bookRepository.findByCode(id);
        book.orElseThrow().setOn_hands("1");
        bookRepository.save(book.orElseThrow());
    }
}
