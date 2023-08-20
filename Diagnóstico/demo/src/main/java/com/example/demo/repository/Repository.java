package com.example.demo.repository;

import com.example.demo.models.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Tarea, Long> {

}
