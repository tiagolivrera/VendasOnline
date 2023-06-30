package br.com.tiagolivrera.VendasOnline.dao.jpa;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.tiagolivrera.VendasOnline.dao.generic.jpa.GenericJpaDAO;
import br.com.tiagolivrera.VendasOnline.domain.jpa.ClienteJPA;
import br.com.tiagolivrera.VendasOnline.domain.jpa.ProdutoJPA;
import br.com.tiagolivrera.VendasOnline.domain.jpa.VendaJPA;
import br.com.tiagolivrera.VendasOnline.exceptions.DAOException;
import br.com.tiagolivrera.VendasOnline.exceptions.TipoChaveNaoEncontradaException;

public class VendaJpaDAO extends GenericJpaDAO<VendaJPA, Long> implements IVendaJpaDAO {

	public VendaJpaDAO() {
		super(VendaJPA.class);
	}

	@Override
	public void finalizarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException {
		super.alterar(venda);
	}

	@Override
	public void cancelarVenda(VendaJPA venda) throws TipoChaveNaoEncontradaException, DAOException {
		super.alterar(venda);
	}

	@Override
	public void excluir(VendaJPA entity) throws DAOException {
		throw new UnsupportedOperationException("OPERAÇÃO NÃO PERMITIDA");
	}

	@Override
	public VendaJPA cadastrar(VendaJPA entity) throws TipoChaveNaoEncontradaException, DAOException {
		try {
			openConnection();
			entity.getProdutos().forEach(prod -> {
				ProdutoJPA prodJpa = entityManager.merge(prod.getProduto());
				prod.setProduto(prodJpa);
			});
			ClienteJPA cliente = entityManager.merge(entity.getCliente());
			entity.setCliente(cliente);
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			closeConnection();
			return entity;
		} catch (Exception e) {
			throw new DAOException("ERRO SALVANDO VENDA ", e);
		}

	}

	@Override
	public VendaJPA consultarComCollection(Long id) {
		openConnection();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<VendaJPA> query = builder.createQuery(VendaJPA.class);
		Root<VendaJPA> root = query.from(VendaJPA.class);
		root.fetch("cliente");
		root.fetch("produtos");
		query.select(root).where(builder.equal(root.get("id"), id));
		TypedQuery<VendaJPA> tpQuery = entityManager.createQuery(query);
		VendaJPA venda = tpQuery.getSingleResult();
		closeConnection();
		return venda;
	}

}
