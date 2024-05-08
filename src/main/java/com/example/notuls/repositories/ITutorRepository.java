package com.example.notuls.repositories;

import com.example.notuls.entities.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITutorRepository extends JpaRepository<Tutor, Long> {
}
