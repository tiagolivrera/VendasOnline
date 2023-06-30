package br.com.tiagolivrera.VendasOnline.dao.jpa;

import br.com.tiagolivrera.VendasOnline.dao.Persistente;
import br.com.tiagolivrera.VendasOnline.dao.generic.jpa.IGenericJpaDAO;

public interface IClienteJpaDAO<T extends Persistente> extends IGenericJpaDAO<T, Long> {

}
