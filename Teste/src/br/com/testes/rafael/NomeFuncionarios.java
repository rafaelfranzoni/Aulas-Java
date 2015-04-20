package br.com.testes.rafael;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.testes.rafael.dao.DBConnectionMySql1;

public class NomeFuncionarios {

	
	public static void Funcionarios(String data,String hora,int matricula,String nome) {
		
		Connection connection = DBConnectionMySql1.getConnection();
		
		int cont = 0;
		
		try{
			// Aplicacao estabelecermos a conexcao com o banco de dados  
		   // Utilizamos o metodo createStatement de con para criar o Statement  
		   Statement stm = connection.createStatement();    
		   
		   // Definido o Statement, executamos a query no banco de dados
		   ResultSet rs = stm.executeQuery(
				   "SELECT cadastros.`data`, cadastros.`hora`, funcionarios.`id_matricula`, funcionarios.`nome` FROM cadastros " 
						   + "INNER JOIN funcionarios ON cadastros.`matricula` = funcionarios.`id_matricula` " 
						   + "ORDER BY funcionarios.`nome` ASC");
		   // O metodo next() informa se houve resultados e posiciona o cursor do banco  
		   // na proxima linha disponivel para recuperacao  
		   // Como esperamos varias linhas utilizamos um laco para recuperar os dados  
		   while(rs.next())  
		   {  
			   cont++; 
		      // Os metodos getXXX recuperam os dados de acordo com o tipo SQL do dado:
			  // tabela - funcionarios.  
			  data = rs.getString("data");
		      hora = rs.getString("hora");
		      matricula = rs.getInt("id_matricula");
		      nome = rs.getString("nome");
		 
		   }
		}
		   
		   catch(SQLException e)  
		      {  
			   	// se houve algum erro, uma excecao e gerado para informar o erro   
			   //vejamos que erro foi gerado e quem o gerou  
			   e.printStackTrace();
			   JOptionPane.showMessageDialog(null,"Houve um erro durante a conexao com o banco de dados.\nErro: "+e.getMessage(),"erro de conexao", JOptionPane.ERROR_MESSAGE);
			   
		      }  
		finally {
			DBConnectionMySql1.close();
			System.out.println("Fechamento da conexao OK");
	} // fim do bloco try-catch-finally
		
		JOptionPane.showMessageDialog(null, "Foi atualizado - "+cont+" registros.");

	}

}
