package br.com.tiagolivrera.VendasOnline.domain.enuns;

public enum Status {

	INICIADA(1, "Iniciada"), CONCLUIDA(2, "Concluída"), CANCELADA(3, "Cancelada");

	private int codigo;
	private String descricao;

	private Status(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Status toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}

		for (Status s : Status.values()) {
			if (codigo.equals(s.getCodigo())) {
				return s;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

}
