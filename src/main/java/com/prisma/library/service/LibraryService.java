package com.prisma.library.service;

import com.prisma.library.domain.Book;
import com.prisma.library.domain.User;

import java.util.List;

public interface LibraryService {
    List<User> fetchUsersWhoBorrowedBooks();

    List<Book> fetchAvailableBooks();

    List<User> fetchNonTerminatedNonBorrowerUsers();
}
