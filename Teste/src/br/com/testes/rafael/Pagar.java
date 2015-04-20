package br.com.testes.rafael;

import java.io.BufferedReader; 
import java.io.FileReader; 
import java.io.IOException; 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.testes.rafael.dao.*;;

public class Pagar {

	public static void PagarFunci(String data,String hora,String nome,int matricula,String cargahoraria,String salario) {
		
		Connection connection = DBConnectionMySql1.getConnection();
		
		int cont = 0;
		
		try{
			// Aplicacao estabelecermos a conexcao com o banco de dados  
		   // Utilizamos o metodo createStatement de con para criar o Statement  
		   Statement stm = connection.createStatement();    
		   
		   // Definido o Statement, executamos a query no banco de dados
		   ResultSet rs = stm.executeQuery(
				   "SELECT cadastros.`data`, cadastros.`hora`, funcionarios.`id_matricula`, funcionarios.`nome`,`funcionarios`.`carga_horaria`,`funcionarios`.`salario` FROM cadastros " 
						   + "INNER JOIN funcionarios ON cadastros.`matricula` = funcionarios.`id_matricula` " 
						   + "ORDER BY funcionarios.`nome`, `funcionarios`.`id_matricula`,cadastros.`data`, cadastros.`hora`,`funcionarios`.`carga_horaria`,`funcionarios`.`salario` ASC");
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
		      cargahoraria = rs.getString("carga_horaria");
		      salario = rs.getString("salario");
		      int i = 0;
		      if (data.equals(data) && nome.equals(nome) && i <=3){
		    	  System.out.println("Aheeeeeee!!!!!");
		      }else{
		    	  System.out.println("Funcionou perfeitamente!!!!!");
		    	  System.out.println(data + ""+ nome);
		      }
		      
//		      for (int i = 0; i == 0 && hora.equals(hora) && matricula == matricula;i++){
//		    	  System.out.println(data+" "+nome+" "+salario+" "+hora+" ");
//		    	  cont++;
//		    	  
//		      }
		 
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
