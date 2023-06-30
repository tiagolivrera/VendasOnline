package br.com.tiagolivrera.VendasOnline.domain.jpa;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.tiagolivrera.VendasOnline.dao.Persistente;
import br.com.tiagolivrera.VendasOnline.domain.enuns.Status;

@Entity
@Table(name = "TB_VENDA")
public class VendaJPA implements Persistente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
	@SequenceGenerator(name = "venda_seq", sequenceName = "sq_venda", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "CODIGO", nullable = false, unique = true, length = 10)
	private String codigo;

	@ManyToOne
	@JoinColumn(name = "id_cliente_fk", foreignKey = @ForeignKey(name = "fk_venda_cliente"), referencedColumnName = "id", nullable = false)
	private ClienteJPA cliente;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
	private Set<ProdutoQuantidadeJPA> produtos;

	@Column(name = "VALOR_TOTAL", nullable = false)
	private BigDecimal valorTotal;

	@Column(name = "DATA_VENDA", nullable = false)
	private Instant dataVenda;

	@Column(name = "STATUS_VENDA", nullable = false)
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public ClienteJPA getCliente() {
		return cliente;
	}

	public void setCliente(ClienteJPA cliente) {
		this.cliente = cliente;
	}

	public Set<ProdutoQuantidadeJPA> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<ProdutoQuantidadeJPA> produtos) {
		this.produtos = produtos;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Instant getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Instant dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private void validarStatus() {
		if (this.status == Status.CONCLUIDA.getCodigo()) {
			throw new UnsupportedOperationException("IMPOSSIVEL ALTERAR VENDA FINALIZADA");
		}
	}

	public void adicionarProduto(ProdutoJPA produto, Integer quantidade) {
		validarStatus();
		Optional<ProdutoQuantidadeJPA> op = produtos.stream()
				.filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();
		if (op.isPresent()) {
			ProdutoQuantidadeJPA produtpQtd = op.get();
			produtpQtd.adicionar(quantidade);
		} else {
			// Criar fabrica para criar ProdutoQuantidade
			ProdutoQuantidadeJPA prod = new ProdutoQuantidadeJPA();
			prod.setProduto(produto);
			prod.adicionar(quantidade);
			produtos.add(prod);
		}
		recalcularValorTotalVenda();
	}

	public void removerProduto(ProdutoJPA produto, Integer quantidade) {
		validarStatus();
		Optional<ProdutoQuantidadeJPA> op = produtos.stream()
				.filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();

		if (op.isPresent()) {
			ProdutoQuantidadeJPA produtpQtd = op.get();
			if (produtpQtd.getQuantidade() > quantidade) {
				produtpQtd.remover(quantidade);
				recalcularValorTotalVenda();
			} else {
				produtos.remove(op.get());
				recalcularValorTotalVenda();
			}

		}
	}

	public void removerTodosProdutos() {
		validarStatus();
		produtos.clear();
		valorTotal = BigDecimal.ZERO;
	}

	public Integer getQuantidadeTotalProdutos() {
		// Soma a quantidade getQuantidade() de todos os objetos ProdutoQuantidade
		int result = produtos.stream().reduce(0,
				(partialCountResult, prod) -> partialCountResult + prod.getQuantidade(), Integer::sum);
		return result;
	}

	public void recalcularValorTotalVenda() {
		// validarStatus();
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (ProdutoQuantidadeJPA prod : this.produtos) {
			valorTotal = valorTotal.add(prod.getValorTotal());
		}
		this.valorTotal = valorTotal;
	}

}
