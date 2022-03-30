package com.prisma.library.service.impl;

import com.prisma.library.domain.Book;
import com.prisma.library.domain.Borrower;
import com.prisma.library.domain.User;
import com.prisma.library.repository.BookRepository;
import com.prisma.library.repository.BorrowerRepository;
import com.prisma.library.repository.UserRepository;
import com.prisma.library.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Override
    public void save(List<Book> books, List<User> users, List<Borrower> borrowers) {
        bookRepository.saveAll(books);
        userRepository.saveAll(users);
        borrowerRepository.saveAll(borrowers);
    }
}
