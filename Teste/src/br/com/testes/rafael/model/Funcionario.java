package br.com.testes.rafael.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Funcionario {

	private String data;
	private String hora;
	private String matricula;
	private String nome;
	private String id;
	
	public String getData() {
		return data;
	}
	public String getHora() {
		return hora;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getNome() {
		return nome;
	}
	public String getId() {
		return id;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Funcionario [data=" + data + ", hora=" + hora + ", matricula="
				+ matricula + ", nome=" + nome + ", id=" + id + "]";
	}
	
public static void nome(List<Funcionario> funcionarios){
		
		File excel = new File("C://RH/Employee.xls");
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("<exemplo>");
  	  	int i=0;
  	  	try{
  	  for(Funcionario funcionario: funcionarios){
  		  i++;
  		  Map<String, Object[]> newData = new HashMap<String, Object[]>();
				newData.put(String.valueOf(i), new Object[] { 
						funcionario.getId() ,funcionario.getData(), funcionario.getHora(), funcionario.getMatricula(), funcionario.getNome() });

				Set<String> newRows = newData.keySet();
				int rownum = sheet.getLastRowNum();
				
				for (String key : newRows) {
					Row row = sheet.createRow(rownum++);
					Object[] objArr = newData.get(key);
					int cellnum = 0;
					for (Object obj : objArr) {
						Cell cell = row.createCell(cellnum++);
						if (obj instanceof String) {
							cell.setCellValue((String) obj);
						} else if (obj instanceof Boolean) {
							cell.setCellValue((Boolean) obj);
						} else if (obj instanceof Date) {
							cell.setCellValue((Date) obj);
						} else if (obj instanceof Double) {
							cell.setCellValue((Double) obj);
						}
					}
				} 
  	  		}
  	  
  	  FileOutputStream os = new FileOutputStream(excel);
  	  book.write(os);
	
  	  JOptionPane.showMessageDialog(null, "Processo finalizado ...");

  	  // Close workbook, OutputStream and Excel file to prevent leak
  	  os.close();
  	  book.close();
  	  
	}catch (FileNotFoundException fe) {
		fe.printStackTrace();

	} catch (IOException ie) {
		ie.printStackTrace();

	}
    }
	
	
}
