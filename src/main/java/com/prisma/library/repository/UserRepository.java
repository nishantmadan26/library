package com.prisma.library.repository;

import com.prisma.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "SELECT * FROM user u where u.member_till IS NULL", nativeQuery = true)
    List<User> fetchNonTerminatedUsers();
}
