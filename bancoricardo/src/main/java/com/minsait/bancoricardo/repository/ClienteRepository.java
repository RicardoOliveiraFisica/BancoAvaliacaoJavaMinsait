package com.minsait.bancoricardo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.bancoricardo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
