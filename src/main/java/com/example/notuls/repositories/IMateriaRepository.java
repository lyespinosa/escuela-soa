package com.example.notuls.repositories;

import com.example.notuls.entities.Alumno;
import com.example.notuls.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMateriaRepository extends JpaRepository<Materia, Long> {


    List<Materia> findMateriaByAlumnosId(Long id);
}
