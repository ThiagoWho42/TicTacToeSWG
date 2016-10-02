package app.sobre;

public class Sobre
{
	/**
	 * Retorna informações para o menu ajuda
	 * @return
	 */
	public String ajuda()
	{
		String TextoAjuda = "                      Tic Tac Toe\n" 
				 +"                -------------------------\n"       									 
				 +"\n\n:. Novo... .:"
				 +"\n Inicia uma nova partida mantendo"
				 +"\n a pontuação atual"
				 +"\n\n:. Jogador .:" 
				 +"\nInsere ou altera o nome do jogador"
				 +"\n\n:. Encerrar .:" 
				 +"\n Encerra a partida e zera a pontuação"
				 +"\n\n:. Sair .:"
				 +"\nFinaliza a aplicação                                            \n\n\n";
		
		return TextoAjuda; 
	}// Fim Ajuda
	
	/**
	 * Retorna informações para o menu sobre
	 * @return
	 */
	public String versao()
	{
		String TextoSobre = "\n"
						+"_________________________________________________      \n\n"       									 
			 			+"   Desenvolvidor por.:      Juliano Dorigão  \n"
			 			+"                versão 0.3 Beta            \n"
			 			+"_________________________________________________ \n\n";
		
		return TextoSobre;		
	}// Fim Versao
	
	/**
	 * Retorna String da versão
	 * @return
	 */
	public String VersaoTitulo()
	{
		String TextoVersao = "..:: Tic Tac Toe ::..";
		
		return TextoVersao;
	}
}// Fim Class sobre