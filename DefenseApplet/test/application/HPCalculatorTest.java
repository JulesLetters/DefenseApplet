package application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HPCalculatorTest {

	private HPCalculator hpCalculator;

	@Before
	public void setUp() throws Exception {
		hpCalculator = new HPCalculator();
	}

	@Test
	public void testZeroStatAndZeroEVsGets141() {
		assertEquals(141, hpCalculator.calculate(0, 0));
	}

	@Test
	public void testRaisingBaseStatsRaisesHPByTwo() {
		assertEquals(143, hpCalculator.calculate(1, 0));
		assertEquals(145, hpCalculator.calculate(2, 0));
		assertEquals(651, hpCalculator.calculate(255, 0));
	}

	@Test
	public void testRaisingEVsByFourRaisesHPByOne() {
		assertEquals(141, hpCalculator.calculate(0, 1));
		assertEquals(141, hpCalculator.calculate(0, 2));
		assertEquals(142, hpCalculator.calculate(0, 4));
		assertEquals(204, hpCalculator.calculate(0, 252));
		assertEquals(204, hpCalculator.calculate(0, 255));
	}

}
