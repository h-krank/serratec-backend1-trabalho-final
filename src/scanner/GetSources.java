package scanner;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import main.Start;


public class GetSources extends Start{
	protected static Scanner read = new Scanner(System.in);
	
	public static File inputFile() throws IOException {
		
		while(true) {
			System.out.println("\nInsira o caminho do arquivo para a inserção de dados");
			String filePath = read.nextLine();
						
			if(new File (filePath).isFile()) {
				int len = filePath.length();
				if(filePath.substring(len-4, len).equals(".csv")) {
					return new File(filePath);
				}
				
				System.out.printf("O arquivo %s não é do tipo .csv \nTente novamente\n", filePath);
			} 
			else {
				System.out.println("---Caminho inválido, tente novamente---");
			}
		}
	}
	
	public static String outputFile() throws IOException {
			System.out.println("\nInsira o caminho do arquivo para o armazenamento de dados");
			String filePath = read.next();
			
			if(new File (filePath).isFile()) {
				int len = filePath.length();
				if(filePath.substring(len-4, len).equals(".csv")) {
					return filePath;
				}
			} 
			else {
			
				System.out.println("O arquivo especificado não é do tipo .csv ou não existe");
				System.out.println("Deseja criar um novo arquivo? (Y/n)");
				
				if (!read.next().toLowerCase().equals("n")) {
					System.out.println("Insira o caminho do diretório");
					filePath = read.next();
					
					if(new File(filePath).isDirectory()) {
						return filePath + "output.csv";
					}
					else {
						System.out.println("--Destino inválido--");
						outputFile();
					}
				}
				else {
					System.out.println("Deseja sair? (Y/n)");
					
					if(read.next().equals("n")) {
						return outputFile();
					}
					else {
						System.out.println("Saindo...");
						System.exit(1);
					}
				}
			}
			return null;
			
	}
	
	
}
