package org.debski.models;

import static org.junit.Assert.assertEquals;

import org.debski.models.FiveInARowModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

public class FiveInARowModelTest {
	
	private FiveInARowModel model;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void before() {
		model = new FiveInARowModel();
	}
	
	@Test
	public void whenXIsOutOfBoundThenRuntimeException() {
		exception.expect(RuntimeException.class);
		exception.expectMessage("Wspolrzedna przekracza rozmiar pola gry");
		model.play(17,1);
	}
	
	@Test
	public void whenYIsOutOfBoundThenRuntimeException() {
		exception.expect(RuntimeException.class);
		exception.expectMessage("Wspolrzedna przekracza rozmiar pola gry");
		model.play(2, 16);
	}
	
	@Test
	public void whenFieldIsCheckedSecondTimeThenRuntimeException() {
		model.play(3, 3);
		exception.expect(RuntimeException.class);
		exception.expectMessage("Probujesz zaznaczyc pole, ktore jest juz zaznaczone");
		model.play(3, 3);
	}
	
	@Test
	public void givenFirstTurnWhenGetCurrentPlayerThen1() {
		model.play(4, 4);
		assertEquals('X' , model.getCurrentPlayer());
	}
	
	@Test
	public void givenLastTurnWas1WhenGetCurrentPlayerThen2() {
		model.play(4, 4);
		model.play(2, 1);
		model.play(3, 4);
		model.play(1, 1);
		assertEquals('O', model.getCurrentPlayer());
	}
	
	@Test 
	public void whenPlayThenNoWinner() {
		String actual = model.play(4, 4);
		assertEquals("Brak zwyciezcy", actual);
	}
	
	@Test
	public void whenFiveInHorizontalThenWin() {
		model.play(1, 1); //X
		model.play(1, 6); //O
		model.play(1, 2); //X
		model.play(2, 2); //O
		model.play(1, 3); //X
		model.play(2, 3); //O
		model.play(1, 4); //X
		model.play(2, 4); //O
		String actual = model.play(1, 5); //X
		assertEquals("Zwycieza X", actual);
	}
	
	@Test
	public void whenOverlineThenNoWinner() {
		model.play(1, 1); //X
		model.play(2, 1); //O
		model.play(1, 2); //X
		model.play(2, 2); //O
		model.play(1, 3); //X
		model.play(2, 3); //O
		model.play(1, 4); //X
		model.play(2, 4); //O
		model.play(1, 6); //X
		model.play(2, 6); //O
		String actual = model.play(1, 5); //X
		assertEquals("Brak zwyciezcy", actual);
	}
	
	@Test
	public void whenFiveInVerticalThenWin() {
		model.play(3, 1); //X
		model.play(2, 7); //O
		model.play(1, 7); //X
		model.play(3, 7); //O
		model.play(8, 7); //X
		model.play(4, 7); //O
		model.play(7, 7); //X
		model.play(5, 7); //O
		model.play(4, 6); //X
		String actual = model.play(6, 7); //O
		assertEquals("Zwycieza O", actual);
	}
	
	@Test
	public void whenFiveFromLeftBottomToTopRightThenWin() {
		model.play(5, 2); //X
		model.play(2, 7); //O
		model.play(4, 3); //X
		model.play(3, 7); //O
		model.play(3, 4); //X
		model.play(4, 7); //O
		model.play(1, 6); //X
		model.play(2, 14); //O
		String actual = model.play(2, 5); //X
		assertEquals("Zwycieza X", actual);
	}
	
	@Test
	public void whenFiveFromLeftBottomToTopRightThenWin2() {
		model.play(10, 13); //X
		model.play(2, 7); //O
		model.play(11, 12); //X
		model.play(3, 7); //O
		model.play(12, 11); //X
		model.play(4, 7); //O
		model.play(13, 10); //X
		model.play(2, 14); //O
		String actual = model.play(14, 9); //X
		assertEquals("Zwycieza X", actual);
	}
	
	@Test
	public void whenFiveFromTopLeftToBottomRightThenWin1() {
		model.play(11, 2); //X
		model.play(2, 11); //O
		model.play(12, 13); //X
		model.play(3, 12); //O
		model.play(7, 7); //X
		model.play(4, 13); //O
		model.play(1, 1); //X
		model.play(5, 14); //O
		model.play(12, 12); //X
		String actual = model.play(6, 15);
		assertEquals("Zwycieza O", actual);
	}
	
	@Test
	public void whenFiveFromTopLeftToBottomRightThenWin2() {
		model.play(11, 2); //X
		model.play(8, 5); //O
		model.play(4, 13); //X
		model.play(9, 6); //O
		model.play(7, 7); //X
		model.play(10, 7); //O
		model.play(1, 1); //X
		model.play(11, 8); //O
		model.play(12, 12); //X
		String actual = model.play(12, 9);
		assertEquals("Zwycieza O", actual);
	}
	
	@Test
	public void whenPlayAndFilledThenNoWinner() {
		for(int x = 1; x <=14; x++) {
			for(int y = 1; y <= 15; y+=3) {
				model.play(x, y);
			}
		}
		
		for(int x = 1; x <=14; x++) {
			for(int y = 2; y <= 15; y+=3) {
				model.play(x, y);
			}
		}
		for(int x = 1; x <=14; x++) {
			for(int y = 3; y <= 15; y+=3) {
				model.play(x, y);
			}
		}
		//Check last row
		for(int y = 1; y <= 14; y++) {
			model.play(15, y);
		}
		String actual = model.play(15, 15);
		assertEquals("Remis", actual);
	}

}
