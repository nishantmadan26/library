package com.prisma.library.api;

import com.prisma.library.domain.Book;
import com.prisma.library.domain.Borrower;
import com.prisma.library.domain.User;
import com.prisma.library.service.LibraryService;
import com.prisma.library.service.UploadService;
import com.prisma.library.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.prisma.library.utils.CsvUtils.*;

@RestController
@RequestMapping("/api/library")
public class LibraryResource {

    @Autowired
    private UploadService uploadService;
    @Autowired
    private LibraryService libraryService;


    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadCSVs(@RequestParam("book") MultipartFile book,
                                             @RequestParam("user") MultipartFile user,
                                             @RequestParam("borrower") MultipartFile borrower) throws IOException {
        List<Book> books = read(Book.class, book);
        List<User> users = read(User.class, user);
        List<Borrower> borrowers = read(Borrower.class, borrower);
        uploadService.save(books, users, borrowers);
        return ResponseEntity.ok("CSV uploaded successfully");
    }

    @GetMapping(value = "/fetch/users/borrowed")
    public List<User> fetchUsersWhoBorrowedAtLeastOneBook() {
        List<User> users = libraryService.fetchUsersWhoBorrowedBooks();
        return users;
    }

    @GetMapping(value = "/fetch/available/books")
    public List<Book> fetchAvailableBooks() {
        List<Book> books = libraryService.fetchAvailableBooks();
        return books;
    }

    @GetMapping(value = "/fetch/users/nonBorrower")
    public List<User> fetchNonBorrowerUsers() {
        List<User> users = libraryService.fetchNonTerminatedNonBorrowerUsers();
        return users;
    }
}
