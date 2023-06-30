package br.com.tiagolivrera.VendasOnline.jpa;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import br.com.tiagolivrera.VendasOnline.dao.jpa.ClienteJpaDAO;
import br.com.tiagolivrera.VendasOnline.dao.jpa.IClienteJpaDAO;
import br.com.tiagolivrera.VendasOnline.domain.enuns.TipoPessoa;
import br.com.tiagolivrera.VendasOnline.domain.jpa.ClienteJPA;
import br.com.tiagolivrera.VendasOnline.exceptions.DAOException;
import br.com.tiagolivrera.VendasOnline.exceptions.MaisDeUmRegistroException;
import br.com.tiagolivrera.VendasOnline.exceptions.TableException;
import br.com.tiagolivrera.VendasOnline.exceptions.TipoChaveNaoEncontradaException;

public class ClienteJpaDAOTest {

	private IClienteJpaDAO clienteDao;

	private Random rd;

	public ClienteJpaDAOTest() {
		this.clienteDao = new ClienteJpaDAO();
		rd = new Random();
	}

	@After
	public void end() throws DAOException {
		Collection<ClienteJPA> list = clienteDao.buscarTodos();
		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Test
	public void pesquisarCliente()
			throws TipoChaveNaoEncontradaException, DAOException, MaisDeUmRegistroException, TableException {
		ClienteJPA cliente = criarCliente();
		clienteDao.cadastrar(cliente);

		ClienteJPA clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);

	}

	@Test
	public void salvarCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJPA cliente = criarCliente();
		ClienteJPA retorno = clienteDao.cadastrar(cliente);
		Assert.assertNotNull(retorno);

		ClienteJPA clienteConsultado = clienteDao.consultar(retorno.getId());
		Assert.assertNotNull(clienteConsultado);

		clienteDao.excluir(cliente);

		ClienteJPA clienteConsultado1 = clienteDao.consultar(retorno.getId());
		Assert.assertNull(clienteConsultado1);
	}

	@Test
	public void excluirCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJPA cliente = criarCliente();
		ClienteJPA retorno = clienteDao.cadastrar(cliente);
		Assert.assertNotNull(retorno);

		ClienteJPA clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);

		clienteDao.excluir(cliente);
		clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNull(clienteConsultado);
	}

	@Test
	public void alterarCliente()
			throws TipoChaveNaoEncontradaException, MaisDeUmRegistroException, TableException, DAOException {
		ClienteJPA cliente = criarCliente();
		ClienteJPA retorno = clienteDao.cadastrar(cliente);
		Assert.assertNotNull(retorno);

		ClienteJPA clienteConsultado = clienteDao.consultar(cliente.getId());
		Assert.assertNotNull(clienteConsultado);

		clienteConsultado.setNome("Rodrigo Pires");
		clienteDao.alterar(clienteConsultado);

		ClienteJPA clienteAlterado = clienteDao.consultar(clienteConsultado.getId());
		Assert.assertNotNull(clienteAlterado);
		Assert.assertEquals("Rodrigo Pires", clienteAlterado.getNome());

		clienteDao.excluir(cliente);
		clienteConsultado = clienteDao.consultar(clienteAlterado.getId());
		Assert.assertNull(clienteConsultado);
	}

	@Test
	public void buscarTodos() throws TipoChaveNaoEncontradaException, DAOException {
		ClienteJPA cliente = criarCliente();
		ClienteJPA retorno = clienteDao.cadastrar(cliente);
		Assert.assertNotNull(retorno);

		ClienteJPA cliente1 = criarCliente();
		ClienteJPA retorno1 = clienteDao.cadastrar(cliente1);
		Assert.assertNotNull(retorno1);

		Collection<ClienteJPA> list = clienteDao.buscarTodos();
		assertTrue(list != null);
		assertTrue(list.size() == 2);

		list.forEach(cli -> {
			try {
				clienteDao.excluir(cli);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Collection<ClienteJPA> list1 = clienteDao.buscarTodos();
		assertTrue(list1 != null);
		assertTrue(list1.size() == 0);
	}

	private ClienteJPA criarCliente() {
		ClienteJPA cliente = new ClienteJPA();
		cliente.setCpfOuCnpj(rd.nextLong());
		cliente.setNome("Rodrigo");
		cliente.setCidade("São Paulo");
		cliente.setEndereco("End");
		cliente.setEstado("SP");
		cliente.setNumero(10);
		cliente.setTelefone(1199999999L);
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		return cliente;
	}

}
