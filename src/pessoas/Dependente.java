package pessoas;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Dependente extends Pessoa {
	private Parentesco parentesco;
	
	public Dependente(String nome, String cpf, LocalDate nascimento, Parentesco parentesco) {
		super(nome, cpf, nascimento);
		this.parentesco = parentesco;
	}
	
}
