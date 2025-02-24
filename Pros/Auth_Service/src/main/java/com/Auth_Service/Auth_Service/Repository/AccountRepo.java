package com.Auth_Service.Auth_Service.Repository;

import com.Auth_Service.Auth_Service.Entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findByUsername(String s);
}
