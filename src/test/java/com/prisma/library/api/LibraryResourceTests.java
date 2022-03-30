package com.prisma.library.api;

import com.prisma.library.domain.User;
import com.prisma.library.service.LibraryService;
import com.prisma.library.service.UploadService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryResourceTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UploadService uploadService;
    @MockBean
    private LibraryService libraryService;

    @Test
    public void when_file_not_uploaded_then_verify_status() throws Exception {
        Resource bookResource = new ClassPathResource("books.csv");
        MockMultipartFile book = new MockMultipartFile("book", bookResource.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE, bookResource.getInputStream());
        mockMvc.perform(multipart("/api/library/upload")
                        .file(book)
                        .contentType(MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void fetchUsersWhoBorrowedBooks() throws Exception {
        User nishant = User.builder().firstName("nishant").name("madan").build();

        when(libraryService.fetchUsersWhoBorrowedBooks()).thenReturn(Arrays.asList(nishant));
        mockMvc.perform(get("/api/library/fetch/users/borrowed")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Name").value("madan"));
    }
}