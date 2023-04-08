package com.minsait.bancoricardo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.minsait.bancoricardo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByCpf(String cpf);
	
	boolean existsByCpf(String cpf);
	
	void deleteByCpf(String cpf);
}
