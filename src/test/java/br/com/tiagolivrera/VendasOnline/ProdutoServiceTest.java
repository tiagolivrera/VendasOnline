package br.com.tiagolivrera.VendasOnline;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.tiagolivrera.VendasOnline.dao.IProdutoDAO;
import br.com.tiagolivrera.VendasOnline.dao.ProdutoDAOMock;
import br.com.tiagolivrera.VendasOnline.domain.Produto;
import br.com.tiagolivrera.VendasOnline.exceptions.DAOException;
import br.com.tiagolivrera.VendasOnline.exceptions.TipoChaveNaoEncontradaException;
import br.com.tiagolivrera.VendasOnline.services.IProdutoService;
import br.com.tiagolivrera.VendasOnline.services.ProdutoService;

public class ProdutoServiceTest {

	private IProdutoService produtoService;

	private Produto produto;

	public ProdutoServiceTest() {
		IProdutoDAO dao = new ProdutoDAOMock();
		produtoService = new ProdutoService(dao);
	}

	@Before
	public void init() {
		produto = new Produto();
		produto.setCodigo("A1");
		produto.setDescricao("Produto 1");
		produto.setNome("Produto 1");
		produto.setValor(BigDecimal.TEN);
	}

	@Test
	public void pesquisar() throws DAOException {
		Produto produtor = this.produtoService.consultar(produto.getCodigo());
		Assert.assertNotNull(produtor);
	}

	@Test
	public void salvar() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = produtoService.cadastrar(produto);
		Assert.assertTrue(retorno);
	}

	@Test
	public void excluir() throws DAOException {
		produtoService.excluir(produto.getCodigo());
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		produto.setNome("Rodrigo Pires");
		produtoService.alterar(produto);

		Assert.assertEquals("Rodrigo Pires", produto.getNome());
	}

}
