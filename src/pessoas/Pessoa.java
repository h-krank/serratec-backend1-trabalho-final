package pessoas;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class Pessoa {
	protected static  Set<String> listaCpf = new HashSet<String>();
	protected String nome;
	protected String cpf;
	protected LocalDate nascimento;
	
	public Pessoa(String nome, String cpf, LocalDate nascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
	}
	
	public static Set<String> getListaCpf() {
		return listaCpf;
	}
	
	public static void addCpf(String cpf) {
		listaCpf.add(cpf);
	}
}
