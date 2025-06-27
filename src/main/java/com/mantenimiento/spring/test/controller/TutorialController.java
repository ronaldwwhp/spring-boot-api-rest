package com.mantenimiento.spring.test.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mantenimiento.spring.test.model.Tutorial;
import com.mantenimiento.spring.test.repository.TutorialRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Tutorial", description = "Tutorial administracion de APIs")
@Slf4j
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;
	
    @Operation(summary = "Crear un nuevo Tutorial", tags = { "tutorials", "post" })
    @ApiResponses({
      @ApiResponse(responseCode = "201", content = {
          @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    	log.info("request tutorial", tutorial);
		try {
			Tutorial createdTutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), tutorial.getPublished()));
			log.info("tutorial creado");
			return new ResponseEntity<>(createdTutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("error al crear tutorial", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @Operation(
	      summary = "Recuperar un Tutorial por Id",
	      description = "Obtener un Tutorial object por el id. El response es Tutorial object con id, title, description y published status.",
	      tags = { "tutorials", "get" })
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
	        @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
	        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    	log.info("tutorial id", id);
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			log.info("tutorial obtenido", tutorialData);
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			log.info("tutorial no encontrado ", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
