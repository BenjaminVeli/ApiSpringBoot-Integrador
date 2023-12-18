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
@Table(name = "publicacion")
public class PublicationModel {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private Long id;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "fecha")
	 private Date fecha;

	 
	 @Column(name = "content")
	 private String content;
	 
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
	 
	 public String getContent() {
		 return content;
	 }
	 
	 public void setContent(String content) {
		 this.content = content;
	 }
	 

	 
}