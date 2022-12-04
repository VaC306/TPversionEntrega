package tp1.p2.logic;

import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.gameobjects.GameObject;

public interface GameStatus {


	/**
	 * Get game cycles.
	 * @return the game cycles.
	 */
	int getCycle();

	/**
	 * Get available suncoins.
	 * 
	 * @return the available suncoins.
	 */
	int getSuncoins();

	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns.
	 */
	int getRemainingZombies();

	/**
	 * Draw a cell of the game.
	 * 
	 * @param col Column of the cell to draw.
	 * @param row Row of the cell to draw.
	 * 
	 * @return a string that represents the content of the cell (col, row).
	 */
	String positionToString(int col, int row);

	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns
	 */
	int getGeneratedSuns();

	/**
	 * Get the number of caught suns.
	 * 
	 * @return the number of caught suns.
	 */
	int getCaughtSuns();

	boolean isPlayerQuits(); // comprueba si el juagdor ha decidido salir del game
	
	boolean allZombiesDead(); // comprueba si han muerto todos los zombies
	
	boolean deadPlayer(); //comprueba si el jugador ha muerto, es decir si el zombie ha cruzado
	
	boolean isFinished(); // comprueba si ha terminado el game de cualquier manera


}
