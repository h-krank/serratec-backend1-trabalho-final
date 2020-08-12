package pessoas;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Dependente extends Pessoa {
	private Parentesco parentesco;
	private static Set<String> cpfDependentes = new HashSet<String>();
	
	public Dependente(String nome, String cpf, LocalDate nascimento, Parentesco parentesco) {
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.parentesco = parentesco;
	}
	
	public static Set<String> getCpfDependentes() {
		return cpfDependentes;
	}
	
	public static void addCpfDependentes(String cpf) {
		Dependente.cpfDependentes.add(cpf);
	}
	
}
