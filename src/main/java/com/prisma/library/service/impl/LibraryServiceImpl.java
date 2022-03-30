package com.prisma.library.service.impl;

import com.prisma.library.domain.Book;
import com.prisma.library.domain.Borrower;
import com.prisma.library.domain.User;
import com.prisma.library.repository.BookRepository;
import com.prisma.library.repository.BorrowerRepository;
import com.prisma.library.repository.UserRepository;
import com.prisma.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BorrowerRepository borrowerRepository;

    public LibraryServiceImpl() {
    }

    @Override
    public List<User> fetchUsersWhoBorrowedBooks() {
        List<User> allUsers = userRepository.findAll();
        List<Borrower> allBorrowers = borrowerRepository.findAll();
        List<String> borrowersName = allBorrowers.stream()
                .map(Borrower::getBorrower)
                .collect(Collectors.toList());
        List<User> usersWhoBorrowedBooks = allUsers.stream()
                .filter(e -> !borrowersName.contains(e.getName() + "," + e.getFirstName()))
                .collect(Collectors.toList());
        return usersWhoBorrowedBooks;
    }

    @Override
    public List<Book> fetchAvailableBooks() {
        List<Book> books = bookRepository.findAll();
        List<Borrower> allBorrowers = borrowerRepository.findAll();
        List<String> borrowersBook = allBorrowers.stream()
                .map(Borrower::getBook)
                .collect(Collectors.toList());
        List<Book> availableBooks = books.stream()
                .filter(e -> !borrowersBook.contains(e.getTitle()))
                .collect(Collectors.toList());
        return availableBooks;
    }

    @Override
    public List<User> fetchNonTerminatedNonBorrowerUsers() {
        List<User> nonTerminatedUsers = userRepository.fetchNonTerminatedUsers();
        List<Borrower> allBorrowers = borrowerRepository.findAll();
        List<String> borrowersName = allBorrowers.stream()
                .map(Borrower::getBorrower)
                .collect(Collectors.toList());
        List<User> nonTerminatedNonBorrowerUsers = nonTerminatedUsers.stream()
                .filter(user -> borrowersName.contains(user.getName() + "," + user.getFirstName()))
                .collect(Collectors.toList());
        return nonTerminatedNonBorrowerUsers;
    }
}
