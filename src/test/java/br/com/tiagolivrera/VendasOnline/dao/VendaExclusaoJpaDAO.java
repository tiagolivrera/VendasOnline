package br.com.tiagolivrera.VendasOnline.dao;

import br.com.tiagolivrera.VendasOnline.dao.generic.jpa.GenericJpaDAO;
import br.com.tiagolivrera.VendasOnline.dao.jpa.IVendaJpaDAO;
import br.com.tiagolivrera.VendasOnline.domain.jpa.VendaJPA;
import br.com.tiagolivrera.VendasOnline.exceptions.DAOException;
import br.com.tiagolivrera.VendasOnline.exceptions.TipoChaveNaoEncontradaException;

public class VendaExclusaoJpaDAO extends GenericJpaDAO<VendaJPA, Long> implements IVendaJpaDAO {

	public VendaExclusaoJpaDAO() {
		super(VendaJPA.class);
	}

	@Override
	public void finalizarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public void cancelarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public VendaJPA consultarComCollection(Long id) {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

}
