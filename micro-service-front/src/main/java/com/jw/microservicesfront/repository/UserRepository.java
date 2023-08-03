package com.jw.microservicesfront.repository;


import org.springframework.data.jpa.repository.EntityGraph;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);
}
