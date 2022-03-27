package com.algaworks.brewer.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.algaworks.brewer.validation.SKU;

@Entity
@Table(name = "cerveja")
public class Cerveja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
		
	@SKU
	@NotBlank(message = "O campo SKU é obrigatório!")
	private String sku;
	
	@NotBlank(message = "O campo Nome é obrigatório!")
	@Size(min = 10, max = 50, message = "O nome de uma cerveja deve ter entre 10 e 50 caracteres.")
	private String nome;
		
	@NotBlank(message = "O campo Descrição é obrigatório!")
	@Size(min = 10, max = 150, message = "A descrição de uma cerveja deve ter entre 10 e 150 caracteres.")
	private String descricao;
	
	@NotNull(message = "O campo Valor é obrigatório!")
	@DecimalMin(value = "0.01", message = "O valor da cerveja deve ser maior que R$0,01.")
	@DecimalMax(value = "9999999.99", message = "O valor da cerveja deve ser menor que R$9.999.999,99.")
	private BigDecimal valor;
	
	@NotNull(message = "O campo Teor alcóolico é obrigatório!")
	@DecimalMax(value = "100.0", message = "O teor alcóolico da cerveja deve ser menor que 100.0%.")
	@Column(name = "teor_alcoolico")
	private BigDecimal teorAlcoolico;
	
	@DecimalMax(value = "100.0", message = "A comissão de venda da cerveja deve ser menor ou igual a 100.0%.")
	private BigDecimal comissao;
	
	@Max(value = 9999, message = "O estoque máximo permitido é de 9999 itens.")
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;
	
	@NotNull(message = "O campo Origem é obrigatório!")
	@Enumerated(value = EnumType.STRING)
	private Origem origem;
	
	@NotNull(message = "O campo Sabor é obrigatório!")
	@Enumerated(value = EnumType.STRING)
	private Sabor sabor;
	
	@NotNull(message = "O campo Estilo é obrigatório!")
	@ManyToOne
	@JoinColumn(name = "codigo_estilo")
	private Estilo estilo;
	
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

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		return Objects.equals(codigo, other.codigo);
	}
			
}