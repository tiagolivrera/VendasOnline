package br.com.tiagolivrera.VendasOnline;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.tiagolivrera.VendasOnline.dao.ClienteDAO;
import br.com.tiagolivrera.VendasOnline.dao.IClienteDAO;
import br.com.tiagolivrera.VendasOnline.domain.Cliente;
import br.com.tiagolivrera.VendasOnline.domain.enuns.TipoPessoa;
import br.com.tiagolivrera.VendasOnline.exceptions.DAOException;
import br.com.tiagolivrera.VendasOnline.exceptions.MaisDeUmRegistroException;
import br.com.tiagolivrera.VendasOnline.exceptions.TableException;
import br.com.tiagolivrera.VendasOnline.exceptions.TipoChaveNaoEncontradaException;

public class ClienteDAOTest {

	private IClienteDAO clienteDao;

	public ClienteDAOTest() {
		clienteDao = new ClienteDAO();
	}

	@After
	public void end() throws DAOException {
		Collection<Cliente> list = clienteDao.buscarTodos();
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli.getCpfOuCnpj());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Test
	public void pesquisarCliente()
			throws MaisDeUmRegistroException, TableException, TipoChaveNaoEncontradaException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpfOuCnpj(12312312312L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		clienteDao.cadastrar(cliente);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpfOuCnpj());
		Assert.assertNotNull(clienteConsultado);

		clienteDao.excluir(cliente.getCpfOuCnpj());
	}

	@Test
	public void salvarCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpfOuCnpj(56565656565L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpfOuCnpj());
		Assert.assertNotNull(clienteConsultado);

		clienteDao.excluir(cliente.getCpfOuCnpj());
	}

	@Test
	public void excluirCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpfOuCnpj(56565656565L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpfOuCnpj());
		Assert.assertNotNull(clienteConsultado);

		clienteDao.excluir(cliente.getCpfOuCnpj());
		clienteConsultado = clienteDao.consultar(cliente.getCpfOuCnpj());
		Assert.assertNull(clienteConsultado);
	}

	@Test
	public void alterarCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpfOuCnpj(56565656565L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);

		Cliente clienteConsultado = clienteDao.consultar(cliente.getCpfOuCnpj());
		Assert.assertNotNull(clienteConsultado);

		clienteConsultado.setNome("Rodrigo Pires");
		clienteDao.alterar(clienteConsultado);

		Cliente clienteAlterado = clienteDao.consultar(clienteConsultado.getCpfOuCnpj());
		Assert.assertNotNull(clienteAlterado);
		Assert.assertEquals("Rodrigo Pires", clienteAlterado.getNome());

		clienteDao.excluir(cliente.getCpfOuCnpj());
		clienteConsultado = clienteDao.consultar(cliente.getCpfOuCnpj());
		Assert.assertNull(clienteConsultado);
	}

	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
		Cliente cliente = new Cliente();
		cliente.setCpfOuCnpj(56565656565L);
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		Boolean retorno = clienteDao.cadastrar(cliente);
		Assert.assertTrue(retorno);

		Cliente cliente1 = new Cliente();
		cliente1.setCpfOuCnpj(56565656569L);
		cliente1.setNome("Rodrigo");
		cliente1.setCidade("São Paulo");
		cliente1.setEndereco("End");
		cliente1.setEstado("SP");
		cliente1.setNumero(10);
		cliente1.setTelefone(1199999999L);
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		Boolean retorno1 = clienteDao.cadastrar(cliente1);
		Assert.assertTrue(retorno1);

		Collection<Cliente> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);

		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli.getCpfOuCnpj());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Collection<Cliente> list1 = clienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}

}
