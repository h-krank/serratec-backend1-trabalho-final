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
		Boolean finish = false; 
		
		try {
			//Adiciona a primeira linha do bloco como novo funcionário
			while (input.hasNextLine()) {
				String[] person = input.nextLine().split(";");
				
				//Cuida de linhas vazias extras no arquivo
				if(person.length == 1) {
					continue;
				}
				Funcionario funcionario = Parsing.parseFuncionario(person);
				
				//Enquanto não encontrar uma linha vazia, adiciona as linhas subsequentes como dependentes
				while (true) {
						if (input.hasNextLine()) {
							person = input.nextLine().split(";");	
						} else {
							finish = true;
						}

						if (person.length == 1 || finish) {
							funcionario.calcularINSS();
							funcionario.calcularIR();
							
							if (funcionario != null) {
								funcionarios.add(funcionario);
								writer.write(funcionario.toString());
								System.out.println("\nFuncionário cadastrado com sucesso!");
							}
							break;
						}

						funcionario.addDependente(Parsing.parseDependente(person));
				}
			}

		} 
		finally {
			input.close();
			writer.close();
			System.out.println("\n" + outputFile + " atualizado");
		}
	}

}
