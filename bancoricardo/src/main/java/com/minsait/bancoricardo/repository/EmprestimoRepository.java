package com.minsait.bancoricardo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minsait.bancoricardo.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
