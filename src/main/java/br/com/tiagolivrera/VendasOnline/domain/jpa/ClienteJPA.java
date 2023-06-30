package br.com.tiagolivrera.VendasOnline.domain.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.tiagolivrera.VendasOnline.dao.Persistente;
import br.com.tiagolivrera.VendasOnline.domain.enuns.TipoPessoa;

@Entity
@Table(name = "TB_CLIENTE")
public class ClienteJPA implements Persistente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
	@SequenceGenerator(name = "cliente_seq", sequenceName = "sq_cliente", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "CPFOUCNPJ", nullable = false, unique = true)
	private Long cpfOuCnpj;
	
	@Column(name = "TELEFONE", nullable = false)
	private Long telefone;
	
	@Column(name = "ENDERECO", nullable = false, length = 150)
	private String endereco;
	
	@Column(name = "NUMERO", nullable = false)
	private Integer numero;
	
	@Column(name = "CIDADE", nullable = false)
	private String cidade;
	
	@Column(name = "ESTADO", nullable = false)
	private String estado;
	
	@Column(name = "TIPOPESSOA", nullable = false)
	private Integer tipoPessoa;

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

	public Long getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(Long cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
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

	public TipoPessoa getTipoPessoa() {
		return TipoPessoa.toEnum(this.tipoPessoa);
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa.getCodigo();
	}

}
