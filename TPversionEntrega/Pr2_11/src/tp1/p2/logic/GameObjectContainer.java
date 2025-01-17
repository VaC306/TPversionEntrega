package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}
	
	
	public List<GameObject> getGameObjects()
	{
		return gameObjects;
	}
	/**
	 * A帽ade un objeto y ejecuta su acci贸n de entrada
	 * 
	 * @param gameObject El GameObject que va a ser a帽adido
	 */
	public void add(GameObject gameObject)
	{
		//A馻de el  objeto a la lista
		gameObjects.add(gameObject);
		//El objeto ejecuta su accion de entrada
		gameObject.onEnter();
	}
	/**
	 * Comprueba si la posici贸n est谩 vac铆a
	 * 
	 * @param col Posici贸n de la columna introducida.
	 * @param row Posici贸n de la fila introducida.
	 * 
	 * @return <code>true</code> Si ls posici贸n est谩 vac铆a<code>false</code>
	 *         otherwise.
	 */
	public boolean isPositionEmpty(int col, int row) 
	{
		for(GameObject g: gameObjects)
		{
			if(g.isInPosition(col, row))
			{
				return false;
			}
		}
			return true;
	}
	/**
	 * Elimina a los muertos de la lista
	 * 
	 * @return <code>true</code> Si se ha eliminado a un muerto<code>false</code>
	 *         otherwise.
	 */
	public boolean removeDead() 
	{
		boolean dead = false;
		for(int i = 0; i < gameObjects.size();i++) 
		{
			if(!gameObjects.get(i).isAlive()) 
			{
				gameObjects.get(i).onExit();
				gameObjects.remove(i);
				dead = true;
			}
		}

		return dead;
	}
	/**
	 * Retorna el objeto de esa posicion.
	 * @param col columna.
	 * @param row fila.
	 * @return = null if no se ha encontrado el objeto.
	 */
	
	//busca si hay un objeto en esa posicion y devuelve el tipo.
	public GameObject getObjectInPosition(int col, int row) 
	{
		for(GameObject g: gameObjects) 
		{
			if(g.isInPosition(col,row) && (g.receivePlantAttack(0)||g.receiveZombieAttack(0)))
				return g;
		}
		return null;
	}
	/**
	 * Update de todos los objetos existentes.
	 */
	public void update() 
	{
		for(int i = 0; i < gameObjects.size();i++) 
		{
			gameObjects.get(i).update();
		}
		removeDead(); //elimina los objetos ya muertos todavia existentes
	}
	/**
	 * Retorna STATUS de objeto.
	 * @param col columna.
	 * @param row fila.
	 * @return = "" if no se ha encontrado el objeto en la posicion.
	 */
	public String positionToString(int col, int row) 
	{
		GameItem g = getObjectInPosition(col,row);
		GameItem s = getSunInPosition(col, row);
		String string = ""; 
		
		if(s != null || g != null ) 
		{
			if(s == null) 
			{
				string = g.toString();
			}
			else if(g == null) 
			{
				string = s.toString();
			}
			else
			{
				string = (s.toString()  +  g.toString());
			}
		}
		return string;
	}
	/**
	 *Comprueba si hay un sol en la posici贸n introducida y si es as铆 lo devuelve
	 * 
	 * @param col Posici贸n de la columna introducida.
	 * @param row Posici贸n de la fila introducida.
	 * 
	 * @return El GameItem en la posici贸n introducida
	 */
	public GameItem getGameItemInPosition(int col, int row) {
		
		
		for(GameObject g: gameObjects) {
			if(g.isInPosition(col, row))
			{	
				return g;	
			}
		}

		return null;
	}
	public GameItem getSunInPosition(int col, int row) {
		for(GameObject g: gameObjects) {	
			if(g.isInPosition(col, row) && !g.receivePlantAttack(0) && !g.receiveZombieAttack(0) ){
				return g;
			}
			
		}
		return null;
	}
	
	public boolean catchObjects(int col, int row) 
	{
		int i = 0;
		boolean ok = false;
		while(!ok && i < gameObjects.size()) {
			//si gameobject = sol && esta en col, row
			if(gameObjects.get(i).isInPosition(col, row) && !gameObjects.get(i).receivePlantAttack(0) && !gameObjects.get(i).receiveZombieAttack(0)) 
			{
				ok = gameObjects.get(i).catchObject();
			}
			++i;
		}
		return ok;
	}

}
