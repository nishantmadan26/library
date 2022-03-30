package com.prisma.library.service.impl;

import com.prisma.library.domain.Borrower;
import com.prisma.library.domain.User;
import com.prisma.library.repository.BookRepository;
import com.prisma.library.repository.BorrowerRepository;
import com.prisma.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTests {

    @InjectMocks
    LibraryServiceImpl libraryService;
    @Mock
    BookRepository bookRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    BorrowerRepository borrowerRepository;

    @Test
    void should_fetch_users() {
        User nishant = User.builder().firstName("nishant").name("madan").build();
        User tom = User.builder().firstName("tom").name("spengler").build();
        Borrower borrower = Borrower.builder().borrower("madan,nishant").build();
        when(userRepository.findAll()).thenReturn(Arrays.asList(nishant, tom));
        when(borrowerRepository.findAll()).thenReturn(Arrays.asList(borrower));
        List<User> users = libraryService.fetchUsersWhoBorrowedBooks();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(1);
        assertThat(users.get(0).getFirstName()).isEqualTo("nishant");
    }

    @Test
    void should_not_fetch_users_if_no_one_borrowed() {
        User nishant = User.builder().firstName("nishant").name("madan").build();
        User tom = User.builder().firstName("tom").name("spengler").build();
        when(userRepository.findAll()).thenReturn(Arrays.asList(nishant, tom));
        when(borrowerRepository.findAll()).thenReturn(new ArrayList<>());
        List<User> users = libraryService.fetchUsersWhoBorrowedBooks();
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(0);
    }
}