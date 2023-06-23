package br.com.tiagolivrera.VendasOnline.services;

import br.com.tiagolivrera.VendasOnline.dao.IProdutoDAO;
import br.com.tiagolivrera.VendasOnline.domain.Produto;
import br.com.tiagolivrera.VendasOnline.services.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

	public ProdutoService(IProdutoDAO dao) {
		super(dao);
	}

}
