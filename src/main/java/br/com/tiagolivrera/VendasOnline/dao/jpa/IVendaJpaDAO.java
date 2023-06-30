package br.com.tiagolivrera.VendasOnline.dao.jpa;

import br.com.tiagolivrera.VendasOnline.dao.generic.jpa.IGenericJpaDAO;
import br.com.tiagolivrera.VendasOnline.domain.jpa.VendaJPA;
import br.com.tiagolivrera.VendasOnline.exceptions.DAOException;
import br.com.tiagolivrera.VendasOnline.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaJpaDAO extends IGenericJpaDAO<VendaJPA, Long> {

	public void finalizarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException;

	public void cancelarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException;

	/**
	 * Usando este método para evitar a exception
	 * org.hibernate.LazyInitializationException Ele busca todos os dados de objetos
	 * que tenham colletion pois a mesma por default é lazy Mas você pode configurar
	 * a propriedade da collection como fetch = FetchType.EAGER na
	 * anotação @OneToMany e usar o consultar genérico normal
	 * 
	 * OBS: Não é uma boa prática utiliar FetchType.EAGER pois ele sempre irá trazer
	 * todos os objetos da collection mesmo sem precisar utilizar.
	 * 
	 * 
	 * @see VendaJpa produtos
	 * 
	 * @param id
	 * @return
	 */
	public VendaJPA consultarComCollection(Long id);

}
