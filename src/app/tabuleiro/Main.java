package app.tabuleiro;

import java.awt.Dimension;
import java.awt.Toolkit;
import app.principal.*;

public class Main {

	public static void main(String[] args) {
		
		
		// Declarando Objeto
		final TicTacToe Obj = new TicTacToe();

		// Configuração da janela
		Obj.setSize(290, 410);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - Obj.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - Obj.getHeight()) / 2);
		Obj.setResizable(false);
		Obj.setLocation(x, y);
		Obj.setVisible(true);

	}
}	