package skywars;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class SkyWars {
	//set the size of the grid
	private static final int GRID_SIZE = 4;
	private final Random random;
	private MasterShip masterShip;
	private ArrayList<Ship> enemyShips;
	private Scanner scanner;
	private boolean running = true;

	public SkyWars() {

		//new random object
		random = new Random();

		//add the master ship object to a random location
		masterShip = new MasterShip(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE));

		//array list to store enemy ships
		enemyShips = new ArrayList<Ship>();
	}

	//method to start the game
	public void play() {

		//set the turn number to 0
		int turn = 0;

		//print master ship starting location
		System.out.println("MasterShip position: (" + masterShip.xCoordinate + ", " + masterShip.yCoordinate + ")");

		//constant loop
		while (running == true) {

			//print out turn number and add 1 to turn count
			System.out.println("Turn: " + (++turn));

			moveMasterShip();
			spawnEnemyShip();
			moveEnemyShips();
			printGameState();

			//if the check collisions boolean is true end the game
			if (checkCollisions()) {
				break;
			}
		}
	}


	// moving the master ship
	private void moveMasterShip() {
		// input direction
		scanner = new Scanner(System.in);
		String input;

		while (running==true) {

			// ask for input
			System.out.println("Enter move direction (U: Up, D: Down, L: Left, R: Right): ");

			input = scanner.next().toUpperCase();

			//move the master ship in the intended direction 
			if (input.equals("U")) {
				masterShip.move(0, -1, GRID_SIZE);
				break;
			} else if (input.equals("D")) {
				masterShip.move(0, 1, GRID_SIZE);
				break;
			} else if (input.equals("L")) {
				masterShip.move(-1, 0, GRID_SIZE);
				break;
			} else if (input.equals("R")) {
				masterShip.move(1, 0, GRID_SIZE);
				break;
			} else {
				System.out.println("Invalid input! Please enter a valid direction.");
			}
		}
	}

	//spawning enemy ships
	private void spawnEnemyShip() {
		// spawn an enemy ship if there is less than 3 enemy ships currently and the random number is equal to 0 (1/3)
		if (enemyShips.size() < 3 && random.nextInt(3) == 0) {

			//spawn ship with an undefined type
			Ship newEnemy = null;

			//random chance at any ship type being spawned
			switch (random.nextInt(3)) {
			case 0:
				newEnemy = new BattleStar(0, 0);
				break;
			case 1:
				newEnemy = new BattleCruiser(0, 0);
				break;
			case 2:
				newEnemy = new BattleShooter(0, 0);
				break;
			}

			//if that ship type is not already on the grid add it to the game
			if (!containsShip(newEnemy.getClass())) {
				enemyShips.add(newEnemy);
			}
		}
	}

	// check if the inputed ship is already in the enemy ships class
	private boolean containsShip(Class<?> shipClass) {

		for (Ship ship : enemyShips) {
			if (ship.getClass().equals(shipClass)) {
				return true;
			}
		}
		return false;
	}



	private void moveEnemyShips() {
		
		int xInput = 0;
		int yInput = 0;

		//loop through each enemy ship thats currently on the grid
		for (Ship enemy : enemyShips) {

			//randomly generate if the ship will on the x coordinate or y coordinate
			if(random.nextInt(2) == 1){

				//move ship left or right?
				if (random.nextInt(2) == 1){
					xInput = 1;}else{xInput=-1;}

			}else{
				//move ship up or down?
				if (random.nextInt(2) == 1){
					yInput = 1;}else{yInput=-1;}
			}

			//move enemy ship
			enemy.move(xInput, yInput, GRID_SIZE);
		}
	}



	//print the game status 
	private void printGameState() {
		//master ship status
		System.out.println("MasterShip position: (" + masterShip.xCoordinate + ", " + masterShip.yCoordinate + ")");
		//enemy ships status
		for (Ship enemy : enemyShips) {
			System.out.println(enemy.type + " position: (" + enemy.xCoordinate + ", " + enemy.yCoordinate + ")");
		}
	}


	private boolean checkCollisions() {

		//array list of ships to be removed
		ArrayList<Ship> shipsToRemove = new ArrayList<Ship>();

		int collisions = 0;
		String destroyed =""; 

		//loop through enemy ships
		for (Ship enemy : enemyShips) {
			//if the master ship is colliding with an enemy ship add 1 to collisions and add it to the shipsToRemove array list
			if (masterShip.isColliding(enemy)) {
				destroyed=enemy.getType();
				shipsToRemove.add(enemy);
				collisions++;
			}
		}

		//remove the destroyed ships from the grid
		enemyShips.removeAll(shipsToRemove);

		//if the master ship collides with more than 1 ship the game ends
		if (collisions > 1) {
			System.out.println("Game Over! MasterShip collided with two or more enemy ships.");
			return true;

			//print which ship was been destroyed
		} else if (collisions == 1) {
			System.out.println("You destoyed the " + destroyed);
		}

		//if the master ship destroys all 3 ships the game ends
		if (enemyShips.isEmpty() && shipsToRemove.size() > 2) {
			System.out.println("Congratulations! All enemy ships have been destroyed.");
			return true;
		}

		return false;
	}
}

