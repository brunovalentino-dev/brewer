package com.algaworks.brewer.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Cerveja {

	@NotBlank(message = "O campo SKU é obrigatório!")
	private String sku;
	
	@NotBlank(message = "O campo Nome é obrigatório!")
	private String nome;
		
	@NotBlank(message = "O campo Descrição é obrigatório!")
	@Size(max = 5, message = "A descrição de uma cerveja não pode ter mais de 5 caracteres.")
	private String descricao;
	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}