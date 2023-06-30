package br.com.tiagolivrera.VendasOnline.dao.jpa;

import br.com.tiagolivrera.VendasOnline.dao.generic.jpa.GenericJpaDB2DAO;
import br.com.tiagolivrera.VendasOnline.domain.jpa.ClienteJPA;

public class ClienteJpaDB2DAO extends GenericJpaDB2DAO<ClienteJPA, Long> implements IClienteJpaDAO<ClienteJPA> {

	public ClienteJpaDB2DAO() {
		super(ClienteJPA.class);
	}

}
