package pessoas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import scanner.Parsing;

public class Funcionario extends Pessoa implements Impostos{
	private double salarioBruto;
	private double descontoInss;
	private double descontoIr;
	private double salarioLiquido;
	private List<Dependente> dependentes = new ArrayList<Dependente>();
	
	
	public Funcionario(String nome, String cpf, LocalDate nascimento, double salario) {
		super(nome, cpf, nascimento);
		this.salarioBruto = salario;	
	}
	
	@Override
	public void calcularINSS() {
		descontoInss = salarioBruto;
		
		if (salarioBruto < 1751.81) {
			descontoInss *= .08;
		}
		else if(salarioBruto < 2919.72) {
			descontoInss *= .09;
		}
		else if (salarioBruto < 5839.45) {
			descontoInss *= .11;
		}
		else {
			descontoInss = 5839.456 * .11;
		}
		this.descontoInss = Math.round(descontoInss * 100.0) / 100.0;
	}

	@Override
	public void calcularIR() {
		descontoIr = salarioBruto - descontoInss - (dependentes.size() * taxaDependente);
		
		if(descontoIr > 4664.68) {
			descontoIr = descontoIr *.275 - 869.36;
		}
		else if(descontoIr > 3751.05) {
			descontoIr = descontoIr * .225 - 636.13;
		}
		else if(descontoIr > 2826.65) {
			descontoIr = descontoIr * .15 - 354.8;
		}
		else if(descontoIr > 1903.98) {
			descontoIr = descontoIr * .075 - 142.8;
		}
		else {
			descontoIr = 0;
		}
		
		this.descontoIr = Math.round(descontoIr * 100.0) / 100.0;
		this.salarioLiquido = this.salarioBruto - this.descontoInss- this.descontoIr;
	}
	
	public void addDependente(Dependente dependente) {		
		if (dependente != null) {
			this.dependentes.add(dependente);
			System.out.printf("Dependente %d cadastrado com sucesso!\n", dependentes.size());
		}
	}
	
	@Override
	public String toString() {
		return nome + ";" + cpf + ";" + descontoInss + ";" + descontoIr+ ";" + salarioLiquido + "\n";
	}
	
}
