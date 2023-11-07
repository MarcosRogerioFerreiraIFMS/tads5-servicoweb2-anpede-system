package br.com.anpede.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.anpede.entities.Associado;
import br.com.anpede.entities.Role;
import br.com.anpede.services.validation.AssociadoUpdateValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class AssociadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@Size(min = 5, max = 100, message = "Deve ter entre 5 e 100 caracteres")
	@NotBlank(message = "Campo obrigatório")
	private String nome;
	private String CPF;
	@PastOrPresent(message = "A data não deve ser futura")
	private LocalDate dataNascimento;
	private String telefone;
	private String endereco;
	@Email(message = "Entrar com um email válido")
	private String email;
	
	private Set<RoleDTO> roles = new HashSet<>();
	
	
	public AssociadoDTO() {
		// TODO Auto-generated constructor stub
	}

	public AssociadoDTO(Long id, String nome, String CPF, LocalDate dataNascimento, String telefone, String email,
			String endereco) {
		this.id = id;
		this.nome = nome;
		this.CPF = CPF;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
	}
	
	public AssociadoDTO(Associado entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.CPF = entity.getCPF();
		this.dataNascimento = entity.getDataNascimento();
		this.telefone = entity.getTelefone();
		this.email = entity.getEmail();
		this.endereco = entity.getEndereco();
		entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}	
}
