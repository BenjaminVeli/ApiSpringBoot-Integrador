package com.integrador.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "comentario")
public class CommentModel {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private Long id;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "fecha")
	 private Date fecha;

	 @Column(name = "contenido")
	 private String contenido;
	 
	 @Column(name = "id_publicacion")
	 private Long id_publicacion;
	 
	
	 public Long getId() {
	     return id;
	 }
	 
	 public void setId(Long id) {
	    this.id = id;
	 }
	 
	 public Date getFecha() {
		 return fecha;
	 }
	 
	 public void setFecha(Date fecha) {
		 this.fecha = fecha;
	 }
	 
	 public String getContenido() {
		 return contenido;
	 }
	 
	 public void setContenido(String contenido) {
		 this.contenido = contenido;
	 }
	 
	 public Long getIdPublicacion() {
		 return id_publicacion;
	 }

	 public void setIdPublicacion(Long idPublicacion) {
	     this.id_publicacion = idPublicacion;
	 }
	 
}