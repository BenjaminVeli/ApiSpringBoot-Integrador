package com.integrador.repository;
import com.integrador.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {

}
