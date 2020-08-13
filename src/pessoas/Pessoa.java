package pessoas;

import java.time.LocalDate;

public abstract class Pessoa {
	protected String nome;
	protected String cpf;
	protected LocalDate nascimento;
	
	public Pessoa(String nome, String cpf, LocalDate nascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
	}
	
	
}
