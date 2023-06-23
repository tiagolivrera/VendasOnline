package br.com.tiagolivrera.VendasOnline.dao;

import br.com.tiagolivrera.VendasOnline.dao.generic.IGenericDAO;
import br.com.tiagolivrera.VendasOnline.domain.Venda;
import br.com.tiagolivrera.VendasOnline.exceptions.DAOException;
import br.com.tiagolivrera.VendasOnline.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {

	public void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;

	public void cancelarVenda(Venda venda) throws TipoChaveNaoEncontradaException, DAOException;
}
