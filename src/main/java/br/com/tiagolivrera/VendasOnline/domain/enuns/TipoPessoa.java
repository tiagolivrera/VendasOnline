package br.com.tiagolivrera.VendasOnline.domain.enuns;

public enum TipoPessoa {

	FISICA(1, "FISICA"), JURIDICA(2, "JURIDICA");

	private int codigo;
	private String descricao;

	private TipoPessoa(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoPessoa toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (TipoPessoa p : TipoPessoa.values()) {
			if (codigo.equals(p.getCodigo())) {
				return p;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

	public static TipoPessoa getByName(String value) {
		for (TipoPessoa pessoa : TipoPessoa.values()) {
			if (pessoa.name().equals(value)) {
				return pessoa;
			}
		}
		return null;
	}

}
