package skywars;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {


	@Test
	public void testMove() {
		Ship master = new MasterShip(1,1);
		int gridsize=4;
		master.move(1,1,gridsize);
		assertTrue(master.xCoordinate==2 && master.yCoordinate==2 && master.xCoordinate >= 0 && master.xCoordinate < gridsize && master.yCoordinate >= 0 && master.yCoordinate < gridsize);
	}

	@Test
	public void testIsColliding() {
		Ship enemy = new BattleShooter(1,1);
		Ship master = new MasterShip(1,1);
		assertTrue(master.isColliding(enemy));	
	}

	@Test
	public void testGetType() {
		Ship enemy = new BattleShooter(1,1);
		String expected = "BattleShooter";
		String actual = enemy.getType();
		assertTrue(actual == expected);
	}

}
