package scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exceptions.DependenteException;
import exceptions.FuncionarioException;
import pessoas.Dependente;
import pessoas.Funcionario;
import pessoas.Parentesco;

public class Parsing {
	
	public static Funcionario parseFuncionario(String[] person) {
		
		try {
			System.out.printf("\n\nCadastrando %s...\n", person[0]);
			String nome 		= person[0];
			Double tryCpf 		= Double.parseDouble(person[1]);
			String cpf		= person[1];
			DateTimeFormatter data 	= DateTimeFormatter.ofPattern("yyyMMdd");
			LocalDate nascimento 	= LocalDate.parse(person[2], data);
			Double salario 		= Double.parseDouble(person[3]);
			
			//Verifica se o CPF tem 11 digitos
			if (person[1].length() != 11)
				throw new FuncionarioException("CPF inválido");
			
			//Verifica se o CPF é unico
			int cpfQuant = Funcionario.getCpfFuncionarios().size();
			Funcionario.addCpfFuncionarios(cpf);
			
			if (cpfQuant == Funcionario.getCpfFuncionarios().size()) {
				throw new FuncionarioException("CPF já cadastrado");
			}
			
			return new Funcionario(nome, cpf, nascimento, salario);
			
		}catch(IllegalArgumentException e) {
			System.out.println("\nCadastro de funcionário falhou - Dados inválidos");
		}catch (FuncionarioException e) {
			System.out.println("\nCadastro de funcionário falhou - " + e.getMessage());
		}
		
		Funcionario.setCount(-1);
		return null;
	}
	
	public static Dependente parseDependente(String[] person) {
		try {
			String nome 		= person[0];
			Double tryCpf 		= Double.parseDouble(person[1]);
			String cpf 		= person[1];
			DateTimeFormatter data 	= DateTimeFormatter.ofPattern("yyyMMdd");
			LocalDate nascimento 	= LocalDate.parse(person[2], data);
			Parentesco parentesco 	= Parentesco.valueOf(person[3]);
			
			//Verifica se dependente é menor de 18
			if (LocalDate.now().getYear() - nascimento.getYear() > 18) {
				throw new DependenteException("Dependente inválido - Dependente maior de idade");
			}
			//Verifica se o CPF tem 11 digiots
			if (person[1].length() != 11)
				throw new DependenteException("Cadastro dependente falhou - CPF inválido");
			
			//Verifica se o CPF é unico			
			int cpfQuant = Dependente.getCpfDependentes().size();
			Dependente.addCpfDependentes(cpf);
			
			if (cpfQuant == Dependente.getCpfDependentes().size()) {
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
