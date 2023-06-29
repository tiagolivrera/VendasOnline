package br.com.tiagolivrera.VendasOnline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.tiagolivrera.VendasOnline.dao.ClienteDAOMock;
import br.com.tiagolivrera.VendasOnline.dao.IClienteDAO;
import br.com.tiagolivrera.VendasOnline.domain.Cliente;
import br.com.tiagolivrera.VendasOnline.domain.enuns.TipoPessoa;
import br.com.tiagolivrera.VendasOnline.exceptions.DAOException;
import br.com.tiagolivrera.VendasOnline.exceptions.TipoChaveNaoEncontradaException;
import br.com.tiagolivrera.VendasOnline.services.ClienteService;
import br.com.tiagolivrera.VendasOnline.services.IClienteService;

public class ClienteServiceTest {

	private IClienteService clienteService;

	private Cliente cliente;

	public ClienteServiceTest() {
		IClienteDAO dao = new ClienteDAOMock();
		clienteService = new ClienteService(dao);
	}

	@Before
	public void init() {
		cliente = new Cliente();
		cliente.setCpfOuCnpj(12312312312L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
		cliente.setTipoPessoa(TipoPessoa.FISICA);

	}

	@Test
	public void pesquisarCliente() throws DAOException {
		Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpfOuCnpj());
		Assert.assertNotNull(clienteConsultado);
	}

	@Test
	public void salvarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		Boolean retorno = clienteService.cadastrar(cliente);

		Assert.assertTrue(retorno);
	}

	@Test
	public void excluirCliente() throws DAOException {
		clienteService.excluir(cliente.getCpfOuCnpj());
	}

	@Test
	public void alterarCliente() throws TipoChaveNaoEncontradaException, DAOException {
		cliente.setNome("Rodrigo Pires");
		clienteService.alterar(cliente);

		Assert.assertEquals("Rodrigo Pires", cliente.getNome());
	}

}
