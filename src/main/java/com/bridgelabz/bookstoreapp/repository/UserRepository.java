package com.bridgelabz.bookstoreapp.repository;

import com.bridgelabz.bookstoreapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
//    In this MySQL query, "u" is an alias for the "users" table.
//    It allows us to refer to the columns of the "users" table using the "u" alias, such as "u.id," "u.name," "u.email," and "u.otp."

    // Find a user by email
    @Query(value = "SELECT * FROM user_data WHERE email_id =:emailId", nativeQuery = true)
    UserModel findByEmail(String emailId);
}
