package application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StatCalculatorTest {

	private StatCalculator statCalculator;

	@Before
	public void setUp() throws Exception {
		statCalculator = new StatCalculator();
	}

	@Test
	public void testZeroStatAndZeroEVsGets36() {
		assertEquals(36, statCalculator.calculate(0, 0));
	}

	@Test
	public void testRaisingBaseStatRaisesOutputByTwoPoints() {
		assertEquals(38, statCalculator.calculate(1, 0));
		assertEquals(56, statCalculator.calculate(10, 0));
		assertEquals(76, statCalculator.calculate(20, 0));
		assertEquals(546, statCalculator.calculate(255, 0));
	}

	@Test
	public void testRaisingEVsByFourRaisesOutputByOnePoints() {
		assertEquals(36, statCalculator.calculate(0, 1));
		assertEquals(36, statCalculator.calculate(0, 3));
		assertEquals(37, statCalculator.calculate(0, 4));
		assertEquals(37, statCalculator.calculate(0, 4));
		assertEquals(99, statCalculator.calculate(0, 252));
		assertEquals(99, statCalculator.calculate(0, 252));
	}

	@Test
	public void testChangingBaseStatsAndEVsChangesOutput() {
		assertEquals(47, statCalculator.calculate(5, 4));
		assertEquals(609, statCalculator.calculate(255, 252));
	}

}
