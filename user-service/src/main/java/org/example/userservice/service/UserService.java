package org.example.userservice.service;

import org.example.userservice.model.Book;
import org.example.userservice.repository.BookRepo;
import org.example.userservice.repository.UserRepository;
import org.example.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepo bookRepository;
    public Optional<User> findById(int id){

        return userRepository.findById(id);
    }
    public List<User> findAll(){

        return (List<User>) userRepository.findAll();
    }
    public List<Book> getAllBooksByUserId(int user_id){
        return bookRepository.getBooksByUserId(user_id);
    }


}
