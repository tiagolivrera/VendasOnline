package br.com.tiagolivrera.VendasOnline.dao.jpa;

import br.com.tiagolivrera.VendasOnline.dao.generic.jpa.GenericJpaDAO;
import br.com.tiagolivrera.VendasOnline.domain.jpa.ProdutoJPA;

public class ProdutoJpaDAO extends GenericJpaDAO<ProdutoJPA, Long> implements IProdutoJpaDAO {

	public ProdutoJpaDAO() {
		super(ProdutoJPA.class);
	}

}
