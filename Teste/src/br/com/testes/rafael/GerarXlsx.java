package br.com.testes.rafael;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.testes.rafael.dao.DBConnectionMySql1;
import br.com.testes.rafael.model.Funcionario;
 
public class GerarXlsx {
	
	public static void main(String[] args) throws IOException {
		
		Connection connection = DBConnectionMySql1.getConnection();
			
			try{
				// Aplicacao estabelecermos a conexcao com o banco de dados  
			   // Utilizamos o metodo createStatement de con para criar o Statement  
			   Statement stm = connection.createStatement();    
			   
			   // Definido o Statement, executamos a query no banco de dados
			   ResultSet rs = stm.executeQuery(
					   "SELECT cadastros.`data`, cadastros.`hora`, funcionarios.`id_matricula`, funcionarios.`nome`,`cadastros`.`id_ponto` FROM cadastros " 
							   + "INNER JOIN funcionarios ON cadastros.`matricula` = funcionarios.`id_matricula` " 
							   + "ORDER BY cadastros.`data`, cadastros.`hora`, funcionarios.`id_matricula`, funcionarios.`nome`,`cadastros`.`id_ponto` ASC");
			   // O metodo next() informa se houve resultados e posiciona o cursor do banco  
			   // na proxima linha disponivel para recuperacao  
			   // Como esperamos varias linhas utilizamos um laco para recuperar os dados  
			   List<Funcionario> funcionarios = new ArrayList<Funcionario>();
			   //List<String> nome1 = new ArrayList<String>();
			   while(rs.next())  
			   {  
				  Funcionario funcionario = new Funcionario();
			      // Os metodos getXXX recuperam os dados de acordo com o tipo SQL do dado:
				  // tabela - funcionarios.  
				  funcionario.setId(rs.getString("id_ponto"));
				  funcionario.setData(rs.getString("data"));
				  funcionario.setHora(rs.getString("hora"));
				  funcionario.setMatricula(rs.getString("id_matricula"));
				  funcionario.setNome(rs.getString("nome"));
			      
			      funcionarios.add(funcionario);
			      
			      Funcionario.nome(funcionarios);
				
			}
			
			
			
			}catch(SQLException e){  
				   	// se houve algum erro, uma excecao e gerado para informar o erro   
				   //vejamos que erro foi gerado e quem o gerou  
				   e.printStackTrace();
				   JOptionPane.showMessageDialog(null,"Houve um erro durante a conexao com o banco de dados.\nErro: "+e.getMessage(),"erro de conexao", JOptionPane.ERROR_MESSAGE);
				   
			}finally {
				DBConnectionMySql1.close();
				System.out.println("Fechamento da conexao OK");
			} // fim do bloco try-catch-finally
		}
}
