package br.com.tiagolivrera.VendasOnline.dao.jpa;

import br.com.tiagolivrera.VendasOnline.dao.generic.jpa.GenericJpaDB1DAO;
import br.com.tiagolivrera.VendasOnline.domain.jpa.ClienteJPA;

public class ClienteJpaDAO extends GenericJpaDB1DAO<ClienteJPA, Long> implements IClienteJpaDAO<ClienteJPA> {

	public ClienteJpaDAO() {
		super(ClienteJPA.class);
	}

}
