package scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import exceptions.DependenteException;
import exceptions.FuncionarioException;
import pessoas.Dependente;
import pessoas.Funcionario;
import pessoas.Parentesco;
import pessoas.Pessoa;

public class Parsing {
	
	public static Funcionario parseFuncionario(String[] person) {
		
		try {
			System.out.printf("\n\nCadastrando %s...\n", person[0]);
			String nome 		= person[0];
			Double tryCpf 		= Double.parseDouble(person[1]);
			String cpf		= person[1];
			DateTimeFormatter data 	= DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate nascimento 	= LocalDate.parse(person[2], data);
			Double salario 		= Double.parseDouble(person[3]);
			
			//Verifica se o CPF tem 11 digitos
			if (person[1].length() != 11)
				throw new FuncionarioException("CPF inválido");
			
			//Verifica se o CPF é unico
			int cpfQuant = Pessoa.getListaCpf().size();
			Pessoa.addCpf(cpf);
			
			if (cpfQuant == Pessoa.getListaCpf().size()) {
				throw new FuncionarioException("CPF já cadastrado");
			}
			
			return new Funcionario(nome, cpf, nascimento, salario);
			
		}catch(IllegalArgumentException e) {
			System.out.println("\nCadastro de funcionário falhou - Dados inválidos");
		}catch (FuncionarioException e) {
			System.out.println("\nCadastro de funcionário falhou - " + e.getMessage());
		}

		return null;
	}
	
	public static Dependente parseDependente(String[] person) {
		try {
			String nome 		= person[0];
			Double tryCpf 		= Double.parseDouble(person[1]);
			String cpf 		= person[1];
			DateTimeFormatter data 	= DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate nascimento 	= LocalDate.parse(person[2], data);
			Parentesco parentesco 	= Parentesco.valueOf(person[3]);
			
			//Verifica se dependente é menor de 18
			if (-ChronoUnit.DAYS.between(LocalDate.now(), nascimento) > 18 * 365) {
				throw new DependenteException("Dependente inválido - Dependente maior de idade");
			}
			//Verifica se o CPF tem 11 digiots
			if (person[1].length() != 11)
				throw new DependenteException("Cadastro dependente falhou - CPF inválido");
			
			//Verifica se o CPF é unico			
			int cpfQuant = Pessoa.getListaCpf().size();
			Pessoa.addCpf(cpf);
			
			if (cpfQuant == Pessoa.getListaCpf().size()) {
				throw new DependenteException("Cadastro dependente falhou - CPF já cadastrado");
			}
			
			return (new Dependente(nome, cpf, nascimento, parentesco));
			
		} catch (IllegalArgumentException e) {
			System.out.printf("Cadastro de dependente falhou - Dados inválidos");
		} catch (DependenteException e) {
			System.out.println(e.getMessage());
		}
		return null;
		
	}
	
}
