package com.example.gateway_service.testauth.tst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    public User findById(String id){

        return userRepository.findById(id);
    }
    public List<User> findAll(){

        return (List<User>) userRepository.findAll();
    }
    /*
    public List<Book> getAllBooksByUserId(String user_id){
        return bookRepository.getBooksByUserId(user_id);
    }

     */


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
