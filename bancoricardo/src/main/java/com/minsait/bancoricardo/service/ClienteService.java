package com.minsait.bancoricardo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.bancoricardo.dto.ClienteDTO;
import com.minsait.bancoricardo.entity.Cliente;
import com.minsait.bancoricardo.exception.ClienteNaoEncontradoException;
import com.minsait.bancoricardo.exception.CpfJaCadastradoException;
import com.minsait.bancoricardo.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) throws CpfJaCadastradoException {
		if (this.clienteRepository.existsByCpf(clienteDTO.getCpf()) == false) {
			Cliente clienteParaCadastrar = ClienteDTO.retornaCliente(clienteDTO);
			Cliente clienteCadastrado = this.clienteRepository.save(clienteParaCadastrar);
			ClienteDTO clienteDTOCadastrado = ClienteDTO.retornaCliente(clienteCadastrado);
			return clienteDTOCadastrado;
		}
		
		throw new CpfJaCadastradoException(clienteDTO.getCpf());
	}
	
	public List<ClienteDTO> retornarTodosOsClientes() {
		List<Cliente> clientes = this.clienteRepository.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			ClienteDTO clienteDTO = ClienteDTO.retornaCliente(cliente);
			clientesDTO.add(clienteDTO);
		}
		return clientesDTO;
	}
	
	public ClienteDTO retornarCliente(String cpf) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpf)) {
			Cliente cliente = this.clienteRepository.findByCpf(cpf).get();
			ClienteDTO clienteDTO = ClienteDTO.retornaCliente(cliente);
			return clienteDTO;
		}
		throw new ClienteNaoEncontradoException(cpf);
	}	

	@Transactional
	public MensagemDeSucesso deletarCliente(String cpf) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpf)) {
			this.clienteRepository.deleteByCpf(cpf);
			MensagemDeSucesso mensagem = new MensagemDeSucesso("Deletado com sucesso");
			return mensagem;
		}		
		throw new ClienteNaoEncontradoException(cpf);
	}
	
	public ClienteDTO atualizarCliente(String cpfCadastrado, ClienteDTO clienteDTO) throws ClienteNaoEncontradoException {
		if (this.clienteRepository.existsByCpf(cpfCadastrado)) {
			Cliente clienteCadastrado = this.clienteRepository.findByCpf(cpfCadastrado).get();
			ClienteDTO clienteDTOCadastrado = ClienteDTO.retornaCliente(clienteCadastrado);
//			if (clienteDTO.getNome() == null) {
//				clienteDTO.setNome(clienteCadastrado.getNome());
//			}
//			if (clienteDTO.getTelefone() == null) {
//				clienteDTO.setTelefone(clienteCadastrado.getTelefone());
//			}
//			if (clienteDTO.getRua() == null) {
//				clienteDTO.setRua(clienteCadastrado.getEndereco().getRua());
//			}
//			if (clienteDTO.getNumero() == null) {
//				clienteDTO.setNumero(clienteCadastrado.getEndereco().getNumero());
//			}
//
//			if (clienteDTO.getCep() == null) {
//				clienteDTO.setCep(clienteCadastrado.getEndereco().getCep());
//			}
//			if (clienteDTO.getRendimentoMensal() == null) {
//				clienteDTO.setRendimentoMensal(clienteCadastrado.getRendimentoMensal());
//			}
//			if (clienteDTO.getCpf() == null || clienteDTO.getCpf().equals("")) {
//				clienteDTO.setCpf(cpfCadastrado);
//			}
//			
			if (clienteDTO.getNome() == null) {
				clienteDTO.setNome(clienteDTOCadastrado.getNome());
			}
			if (clienteDTO.getTelefone() == null) {
				clienteDTO.setTelefone(clienteDTOCadastrado.getTelefone());
			}
			if (clienteDTO.getRua() == null) {
				clienteDTO.setRua(clienteDTOCadastrado.getRua());
			}
			if (clienteDTO.getNumero() == null) {
				clienteDTO.setNumero(clienteDTOCadastrado.getNumero());
			}

			if (clienteDTO.getCep() == null) {
				clienteDTO.setCep(clienteDTOCadastrado.getCep());
			}
			if (clienteDTO.getRendimentoMensal() == null) {
				clienteDTO.setRendimentoMensal(clienteDTOCadastrado.getRendimentoMensal());
			}
			if (clienteDTO.getCpf() == null || clienteDTO.getCpf().equals("") || !clienteDTO.getCpf().equals(cpfCadastrado)) {
				clienteDTO.setCpf(cpfCadastrado);
			}
				
			Cliente clienteParaAtualizar = ClienteDTO.retornaCliente(clienteDTO);
			Cliente clienteAtualizado = this.clienteRepository.save(clienteParaAtualizar);
			ClienteDTO clienteDTOAtualizado = ClienteDTO.retornaCliente(clienteAtualizado);
			
			return clienteDTOAtualizado;
			
			
		}
		throw new ClienteNaoEncontradoException(cpfCadastrado);
	}
}
