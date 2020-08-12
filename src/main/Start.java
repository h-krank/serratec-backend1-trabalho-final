package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import pessoas.Funcionario;
import scanner.ScanFile;

public class Start {
		protected static List<Funcionario> funcionarios = new ArrayList<Funcionario>();

		public static void main(String[] args) {
		
			try {
				ScanFile.scan();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (NoSuchElementException e) {
				System.out.println("ERROR");
			}

		}

}


