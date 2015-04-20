/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes.rafael.telas;

import br.com.testes.rafael.GerarPDF;
import br.com.testes.rafael.NomeFuncionarios;
import br.com.testes.rafael.Pagar;
import br.com.testes.rafael.dao.DBConnectionMySql1;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.Box;
import javax.swing.ImageIcon;

/**
 *
 * @author Rafa
 */
public class JifFiltro extends javax.swing.JInternalFrame {

    /**
     * Creates new form JifFiltro
     */    
    public JifFiltro(){
    	setMaximizable(true);
    	setIconifiable(true);
    	setClosable(true);
        initComponents();
			
	    final DefaultTableModel modelo = new 
	       DefaultTableModel();
	    
	    // constrói a tabela
	    JTable tabela = new JTable(modelo);
	    
	    // Cria duas colunas
	 	modelo.addColumn("Data");
	 	modelo.addColumn("Hora");
	 	modelo.addColumn("Matricula");
	 	modelo.addColumn("Nome");
	 	modelo.addColumn("Relação");
	    
	    JButton btn = new JButton("Atualizar");
	    btn.setIcon(new ImageIcon(JifFiltro.class.getResource("/Imagens/icone_atualizar18.png")));
	    btn.addActionListener(
	      new ActionListener(){
	      	public void actionPerformed(ActionEvent e){
	      		
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
	    						   + "ORDER BY funcionarios.`nome`, `funcionarios`.`id_matricula`,cadastros.`data`, cadastros.`hora` ASC ");
	    		   // O metodo next() informa se houve resultados e posiciona o cursor do banco  
	    		   // na proxima linha disponivel para recuperacao  
	    		   // Como esperamos varias linhas utilizamos um laco para recuperar os dados  
	    		   while(rs.next())  
	    		   {  
	    			   cont++; 
	    		      // Os metodos getXXX recuperam os dados de acordo com o tipo SQL do dado:
	    			  // tabela - funcionarios.  
	    			  String data = rs.getString("data");
	    			  String hora = rs.getString("hora");
	    		      int matricula = rs.getInt("id_matricula");
	    		      String nome = rs.getString("nome");
	    		      
	    		      modelo.addRow(new Object[]{data,hora, matricula,nome});
	    		 
	    		   }
	    		}
	    		   
	    		   catch(SQLException e1)  
	    		      {  
	    			   	// se houve algum erro, uma excecao e gerado para informar o erro   
	    			   //vejamos que erro foi gerado e quem o gerou  
	    			   e1.printStackTrace();
	    			   JOptionPane.showMessageDialog(null,
	    					   "Houve um erro durante a conexao com o banco de dados.\nErro: "+e1.getMessage(),"erro de conexao", JOptionPane.ERROR_MESSAGE);
	    			   
	    		      }  
	    		finally {
	    			DBConnectionMySql1.close();
	    			System.out.println("Fechamento da conexao OK");
	    	} // fim do bloco try-catch-finally
	    		
	    		JOptionPane.showMessageDialog(null, "Foi atualizado - "+cont+" registros.");

	        }
	      }	
	    ); 
	    
	    tabela.setPreferredScrollableViewportSize(new 
	        Dimension(550, 250));
		
	    Container c = getContentPane();
	    c.setLayout(new FlowLayout());
				
	    JScrollPane scrollPane = new JScrollPane(tabela);
	    c.add(scrollPane);
	    c.add(btn);
	    
	    JButton btnInserir = new JButton("Calcular");
	    btnInserir.setIcon(new ImageIcon(JifFiltro.class.getResource("/Imagens/calculadora18.png")));
	    btnInserir.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String data = "";
	    		String hora = "";
	    		String nome = "";
	    		int matricula = 0;
	    		String cargahoraria = "";
	    		String salario = "";
	    		
	    		Pagar.PagarFunci(data, hora, nome, matricula, cargahoraria, salario);
	    		
	    	}
	    });
	    getContentPane().add(btnInserir);
	    
	    JButton btnSalvar = new JButton("Salvar");
	    btnSalvar.setIcon(new ImageIcon(JifFiltro.class.getResource("/Imagens/save18x18.png")));
	    btnSalvar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		String nome = JOptionPane.showInputDialog(
			             null, "Informe o nome que deseja para o arquivo:");
	    		
	    		try {
	    			String data = null;
		    		String hora = null;
		    		String nome1 = null;
		    		int matricula = 0;
		    		String cargahoraria = null;
		    		String salario = null;
					GerarPDF.PDF(nome, data, hora, nome1, matricula, cargahoraria, salario);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
	    	}
	    });
	    getContentPane().add(btnSalvar);
	    	
	    setSize(597, 365);
	    setVisible(true);
	  }
		
	  private void initComponents() {
		// TODO Auto-generated method stub
		
	}

}
