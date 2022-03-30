package com.prisma.library.service;

import com.prisma.library.domain.Book;
import com.prisma.library.domain.Borrower;
import com.prisma.library.domain.User;

import java.util.List;

public interface UploadService {

    void save(List<Book> books, List<User> users, List<Borrower> borrowers);
}
