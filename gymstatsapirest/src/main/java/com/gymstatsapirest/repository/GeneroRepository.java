package com.gymstatsapirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gymstatsapirest.model.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Short>
{

}
