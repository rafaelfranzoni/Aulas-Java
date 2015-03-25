package br.com.reges.listaexercicios1.CadastroEmpregado;

/**
 * classe que faz o cadastro de funcionarios 
 */
public class CadastroEmpregado {
	/**
	 * atributo que le o numero da matricula
	 */
	void NMatricula(){
		
		System.out.println(
				"Numero da matricula: 123456");
		}
	/**
	 * atributo que le o nome
	 */
	void Nome(){
		
		System.out.println(
				"Nome do Funcionario: Rafael");
		}
	/**
	 * atributo que le a funcao
	 */
	void Funcao(){
		
		System.out.println(
				"Função: Programador");
	}
	/**
	 * atributo que le o salario
	 */
	void Salario(){
		
		System.out.println(
				"Salario: R$ 5.000,00");
	}
	/**
	 * atributo que le a data de admissao
	 */
	void DAdmissao(){
		
		System.out.println("Data de Admissão: 01/03/2013");
	}
	public static void main(String[] args) {
		
		CadastroEmpregado Empregado = new CadastroEmpregado();
		
		Empregado.NMatricula();
		Empregado.Nome();
		Empregado.Funcao();
		Empregado.Salario();
		Empregado.DAdmissao();
	}

}
