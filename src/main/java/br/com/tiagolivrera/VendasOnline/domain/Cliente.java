package br.com.tiagolivrera.VendasOnline.domain;

import br.com.tiagolivrera.VendasOnline.annotations.ColunaTabela;
import br.com.tiagolivrera.VendasOnline.annotations.Tabela;
import br.com.tiagolivrera.VendasOnline.annotations.TipoChave;
import br.com.tiagolivrera.VendasOnline.dao.Persistente;

@Tabela("TB_CLIENTE")
public class Cliente implements Persistente {

	@ColunaTabela(dbName = "id", setJavaName = "setId")
	private Long id;

	@ColunaTabela(dbName = "nome", setJavaName = "setNome")
	private String nome;

	@TipoChave("getCpf")
	@ColunaTabela(dbName = "cpf", setJavaName = "setCpf")
	private Long cpf;

	@ColunaTabela(dbName = "telefone", setJavaName = "setTelefone")
	private Long telefone;

	@ColunaTabela(dbName = "endereco", setJavaName = "setEndereco")
	private String endereco;

	@ColunaTabela(dbName = "numero", setJavaName = "setNumero")
	private Integer numero;

	@ColunaTabela(dbName = "cidade", setJavaName = "setCidade")
	private String cidade;

	@ColunaTabela(dbName = "estado", setJavaName = "setEstado")
	private String estado;

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

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
