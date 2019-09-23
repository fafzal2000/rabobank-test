package com.rabobank.assignment.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "person")
@Getter
@Setter
public class PersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	@JsonFormat
			(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dob;

	@Column(nullable = false)
	private Double age;

	private String address;

}
