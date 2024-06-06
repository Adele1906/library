package org.example.userservice.service;

import org.example.userservice.model.Book;
import org.example.userservice.model.User;
import org.example.userservice.repository.BookRepo;
import org.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private BookRepo bookRepository;
    @Autowired
    UserRepository userRepository;


    public User findById(String id){

        return userRepository.findById(id);
    }
    public List<User> findAll(){

        return (List<User>) userRepository.findAll();
    }
    public List<Book> getAllBooksByUserId(int user_id){
        return bookRepository.getBooksByUserId(user_id);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
