package com.prisma.library.repository;

import com.prisma.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    /*@Query(value = "SELECT book(d.name, e.name, e.email, e.address) "
            + "FROM Department d LEFT JOIN d.employees e")
    List<Book> allAvailableBooks();*/
}
