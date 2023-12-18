package com.integrador.controllers;

import com.integrador.models.CommentModel;
import com.integrador.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comentarios")
    public ResponseEntity<List<CommentModel>> getPublicaciones() {
        List<CommentModel> publicaciones = commentRepository.findAll();
        return ResponseEntity.ok(publicaciones);
    }

    @PostMapping("/addcomentario")
	public ResponseEntity<String> addComment(@RequestBody CommentModel comment) {
		if (comment.getContenido() == null || comment.getIdPublicacion() == null) {
			return ResponseEntity.badRequest().body("Bad Request. Please fill the fields.");
		}

		if (comment.getFecha() == null) {
			comment.setFecha(new Date());
		}

		commentRepository.save(comment);
		return ResponseEntity.status(HttpStatus.CREATED).body("Comentario creado exitosamente");
	}

    @PutMapping("/comentario/{id}")
    public ResponseEntity<String> updateComment(@PathVariable Long id, @RequestBody CommentModel updatedComment) {
        Optional<CommentModel> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty() || updatedComment.getContenido() == null || updatedComment.getIdPublicacion() == null) {
            return ResponseEntity.badRequest().body("Bad Request. Please provide a valid comment ID and content.");
        }

        CommentModel existingComment = optionalComment.get();
        existingComment.setContenido(updatedComment.getContenido());

        commentRepository.save(existingComment);
        return ResponseEntity.ok("El comentario se actualizó correctamente");
    }

    @DeleteMapping("/comentario/{id}")
    public ResponseEntity<String> delComment(@PathVariable Long id) {
        Optional<CommentModel> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comentario no encontrado");
        }

        commentRepository.deleteById(id);
        return ResponseEntity.ok("Comentario eliminado correctamente");
    }
}