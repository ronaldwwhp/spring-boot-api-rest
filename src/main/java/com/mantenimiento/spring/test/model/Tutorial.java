package com.mantenimiento.spring.test.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Tutorial Model Informacion")
@Entity
@Table(name = "tutorials")
public class Tutorial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Tutorial Id", example = "123")
	private long id;

	@Column(name = "title")
	@NonNull
	@Schema(description = "Titulo del Tutorial", example = "Swagger Tutorial")
	private String title;

	@Column(name = "description")
	@NonNull
	@Schema(description = "Descripcion del Tutorial", example = "Document REST API with Swagger 3")
	private String description;

	@Column(name = "published")
	@NonNull
	@Schema(description = "Estado del Tutorial si se publico o no", example = "true")
	private Boolean published;

}
