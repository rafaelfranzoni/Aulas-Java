package br.com.testes.rafael;

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.testes.rafael.dao.*;;

public class TesteLerArquivoTxt {
	
	public static void LerArquivo(String nome,String dia,String mes,String ano,String hora,String matricula,String data,int cont) throws SQLException{
		cont = 0;
		

		try { 
			FileReader arq = new FileReader(nome); 
			
			BufferedReader lerArq = new BufferedReader(arq); 
			
			String linha = lerArq.readLine(); 
			/* 
			 * 	lê a primeira linha 
			 * a variável "linha" recebe o valor "null" quando o processo 
			 * de repetição atingir o final do arquivo texto 
			 */
			
		while (linha != null) { 
			
			dia = String.copyValueOf(linha.toCharArray(), 0, 2);
			mes = String.copyValueOf(linha.toCharArray(), 3, 2);
			ano = String.copyValueOf(linha.toCharArray(), 6, 4);
			hora = String.copyValueOf(linha.toCharArray(), 11, 5);
			matricula = String.copyValueOf(linha.toCharArray(), 29, 2);
			data = ano + "-" + mes + "-" + dia;
			linha = lerArq.readLine(); // lê da segunda até a última linha
			cont++;
			
			int result = 0;
			
			TesteLerArquivoTxt.insert(ano, hora, matricula, data, result);
			
			}
			
		 
		arq.close(); 
		
		} catch (IOException e) { 
			
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage()); 
			
		} System.out.println(); 
			
		JOptionPane.showMessageDialog(null, "Foram inseridos - "+cont);
		
	}
	
	public static void insert(String ano,String hora,String matricula,String data,int result) throws SQLException{
		Connection connection = DBConnectionMySql1.getConnection();
		
		PreparedStatement pstn = connection.prepareStatement("INSERT INTO cadastros(data, hora, matricula) VALUES (?,?,?);");
		pstn.setString(1, data);
		pstn.setString(2, hora);
		pstn.setString(3, matricula);
		
		result = pstn.executeUpdate();
				
		
		if (result == 1){ 
			 
			System.out.println("Os dados foram inseridos com sucesso ");
		}
	}

}
