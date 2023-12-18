package com.integrador.repository;
import com.integrador.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserModel, Long> {

}
