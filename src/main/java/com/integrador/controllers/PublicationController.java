package com.integrador.controllers;

import com.integrador.models.PublicationModel;
import com.integrador.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @GetMapping("/publicaciones")
    public ResponseEntity<List<PublicationModel>> getPublicaciones() {
        List<PublicationModel> publicaciones = publicationRepository.findAll();
        return ResponseEntity.ok(publicaciones);
    }

    @PostMapping("/addpublicaciones")
    public ResponseEntity<String> addPublicacion(@RequestBody PublicationModel publication) {
        if (publication.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad Request. Please fill the 'content' field.");
        }

        if (publication.getFecha() == null) {
            publication.setFecha(new Date());
        }

        publicationRepository.save(publication);
        return ResponseEntity.status(HttpStatus.CREATED).body("Publicación creada exitosamente");
    }

    @PutMapping("/publicacion/{id}")
    public ResponseEntity<String> updatePublicacion(@PathVariable Long id, @RequestBody PublicationModel updatedPublication) {
        Optional<PublicationModel> optionalPublication = publicationRepository.findById(id);

        if (optionalPublication.isEmpty() || updatedPublication.getContent() == null) {
            return ResponseEntity.badRequest().body("Bad Request. Please provide a valid publication ID and content.");
        }

        PublicationModel existingPublication = optionalPublication.get();
        existingPublication.setContent(updatedPublication.getContent());

        publicationRepository.save(existingPublication);
        return ResponseEntity.ok("La publicación se actualizó correctamente");
    }

    @DeleteMapping("/publicacion/{id}")
    public ResponseEntity<String> delPublicacion(@PathVariable Long id) {
        Optional<PublicationModel> optionalPublication = publicationRepository.findById(id);

        if (optionalPublication.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publicación no encontrada");
        }

        publicationRepository.deleteById(id);
        return ResponseEntity.ok("Publicación eliminada correctamente");
    }
}

