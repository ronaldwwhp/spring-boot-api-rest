package com.mantenimiento.spring.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mantenimiento.spring.test.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}
