package com.example.practica.repositories;

import com.example.practica.entities.Primito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimitoRepository extends JpaRepository<Primito, Long> {



}
