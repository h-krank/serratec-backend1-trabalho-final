package scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
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
				String[] person 		= input.nextLine().split(";");
				Funcionario funcionario = Parsing.parseFuncionario(person);
				
				//Enquanto não encontrar uma linha vazia, adiciona as linhas subsequentes como dependentes
				while (true) {
						person = input.nextLine().split(";");
						
						//Ao encontrar uma linha vazia, finaliza os valores do funcionário
						//adiciona na lista de funcionários e quebra o loop
						if (person.length == 1) {
							funcionario.calcularINSS();
							funcionario.calcularIR();
							
							if (funcionario != null) {
								funcionarios.add(funcionario);
								writer.write(funcionario.toString());
								System.out.println("\nFuncionário cadastrado com sucesso!");
							}
							break;
						}

						funcionario.addDependente(person);
						
						//Código um pouco repetido, helper functions aparentemente não funcionam em Java
						if(!input.hasNextLine()) {
							funcionario.calcularINSS();
							funcionario.calcularIR();
							funcionarios.add(funcionario);
							
							if (funcionario != null) {
								writer.write(funcionario.toString());
								System.out.println("\nFuncionário cadastrado com sucesso!");
								return;
							}
						}
				}
			}
			
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
			
		}catch (NoSuchElementException e) {
			writer.close();
			System.out.println("ERROR");
		}catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			writer.close();
			System.out.println("\n" + outputFile + " atualizado");
		}
	}

}
