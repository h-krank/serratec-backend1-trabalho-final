package scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import pessoas.Funcionario;

public class ScanFile extends GetSources{
	
	public static void scan() throws IOException {
		File inputFile = inputFile();
		String outputFile = outputFile();
		Scanner input = new Scanner(inputFile);
		FileWriter writer = new FileWriter(outputFile);
		
		try {
			//Adiciona a primeira linha do bloco como novo funcionário
			while (input.hasNextLine()) {
				String[] person = input.nextLine().split(";");
				
				//Cuida de linhas vazias extras no arquivo
				if(person.length == 1) {
					continue;
				}
				Funcionario funcionario = Parsing.parseFuncionario(person);
				
				if (funcionario == null) {
					while(!input.nextLine().isBlank()) {
						//Pula dependentes após falha de cadastro de funcionário
					}
					continue;
				}
				
				//Enquanto não encontrar uma linha vazia, adiciona as linhas subsequentes como dependentes
				while (input.hasNextLine()) {
					person = input.nextLine().split(";");

					if (person.length == 1) {				
						break;
					}

					funcionario.addDependente(Parsing.parseDependente(person));
				}
				
				//Finaliza cadastro
				funcionario.calcularINSS();
				funcionario.calcularIR();
				
				funcionarios.add(funcionario);
				writer.write(funcionario.toString());
				System.out.println("\nFuncionário cadastrado com sucesso!");
			}

		} 
		finally {
			input.close();
			writer.close();
			System.out.println("\n" + outputFile + " atualizado");
		}
	}

}
