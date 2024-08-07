package com.libraries;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagementApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) context.getBean("bookService");
        
        
//        For exercise 1 
//        bookService.displayBooks();  
        
//        exercise 5 
        System.out.println("BookService bean: " + bookService);
    }
}
