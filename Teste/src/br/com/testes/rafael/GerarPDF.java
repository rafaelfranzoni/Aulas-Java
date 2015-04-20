package br.com.testes.rafael;

import java.io.FileOutputStream; 
import java.io.IOException; 
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.com.testes.rafael.dao.DBConnectionMySql1;

import com.itextpdf.text.Document; 
import com.itextpdf.text.DocumentException; 
import com.itextpdf.text.Paragraph; 
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 

public class GerarPDF { 
	
	public static void PDF(String nome,String data,String hora,String nome1,int matricula,String cargahoraria,String salario) throws Exception {
		
		Connection connection = DBConnectionMySql1.getConnection();
		
		OutputStream os = null;
        Document document = new Document();

		int cont = 0;
		boolean result = false;
		
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
			   nome1 = rs.getString("nome");
			   cargahoraria = rs.getString("carga_horaria");
			   salario = rs.getString("salario");
		      
		      //cria a stream de saída		
	            os = new FileOutputStream("c:\\RH"+"\\"+nome+".pdf");
	            
	            //associa a stream de saída ao		
	            PdfWriter.getInstance(document, os);
	            
	            //abre o documento
	            document.open();
	            
	            result = true;
	            
	            if (result == true){
	            //adiciona o texto ao PDF 
	            Paragraph p = new Paragraph("Meu primeiro arquivo PDF!");
	            document.add(p);
		    	  
		    	  PdfPTable table = new PdfPTable(6);
		    	  PdfPCell header = new PdfPCell(new Paragraph("Algumas Palavaras Reservadas do Java"));
		    	  header.setColspan(6);
		    	  table.addCell(header);	
		    	  table.addCell("DATA: "+data);
		    	  table.addCell("HORA: "+hora);
		    	  table.addCell("MATRICULA: "+matricula);
		    	  table.addCell("NOME: "+nome1);
		    	  table.addCell("CARGA HORARIA: "+cargahoraria);
		    	  table.addCell("SALARIO: "+salario);
		    	  table.addCell("DATA: "+data);
		    	  table.addCell("HORA: "+hora);
		    	  table.addCell("MATRICULA: "+matricula);
		    	  table.addCell("NOME: "+nome1);
		    	  table.addCell("CARGA HORARIA: "+cargahoraria);
		    	  table.addCell("SALARIO: "+salario);
		    	  table.addCell(header);	
		    	  document.add(table);
		    	  cont++;
		    	  result = false;
	            }
		     		 
		   }
		   
		} catch (Exception e) {
			
		}finally{
		
		            if (document != null) {
		            	JOptionPane.showMessageDialog(null, "Documento pdf, salvo com sucesso !!!");
		            	JOptionPane.showMessageDialog(null,cont);
		                //fechamento do documento
		            	document.close();
		
		          }
		
		          if (os != null) {
		
		             //fechamento da stream de saída	
		             os.close();
		
		    }
		        
		
		}
	
	}
}

