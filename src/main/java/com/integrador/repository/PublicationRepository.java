package com.integrador.repository;
import com.integrador.models.PublicationModel;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PublicationRepository extends JpaRepository<PublicationModel, Long> {

}
