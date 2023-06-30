package br.com.tiagolivrera.VendasOnline.dao.jpa;

import br.com.tiagolivrera.VendasOnline.dao.generic.jpa.GenericJpaDAO;
import br.com.tiagolivrera.VendasOnline.domain.jpa.ClienteJPA;

public class ClienteJpaDAO extends GenericJpaDAO<ClienteJPA, Long> implements IClienteJpaDAO {

	public ClienteJpaDAO() {
		super(ClienteJPA.class);
	}

}
