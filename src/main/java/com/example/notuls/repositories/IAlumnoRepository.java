package com.example.notuls.repositories;

import com.example.notuls.entities.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query(value = "select alumnos.* from alumnos " + "where alumnos.tutor_id = :tutorId", nativeQuery = true)
    List<Alumno> findAllByTutorId(Long tutorId);

    List<Alumno> findByMateriasId(Long materiasId);

}
