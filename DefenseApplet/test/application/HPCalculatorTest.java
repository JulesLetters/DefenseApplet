package application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HPCalculatorTest {

	@Mock
	Nature nature;
	private HPCalculator hpCalculator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		hpCalculator = new HPCalculator();
	}

	@Test
	public void testZeroStatAndZeroEVsGets141() {
		assertEquals(141, hpCalculator.calculate(0, 0, nature));
	}

	@Test
	public void testRaisingBaseStatsRaisesHPByTwo() {
		assertEquals(143, hpCalculator.calculate(1, 0, nature));
		assertEquals(145, hpCalculator.calculate(2, 0, nature));
		assertEquals(651, hpCalculator.calculate(255, 0, nature));
	}

	@Test
	public void testRaisingEVsByFourRaisesHPByOne() {
		assertEquals(141, hpCalculator.calculate(0, 1, nature));
		assertEquals(141, hpCalculator.calculate(0, 2, nature));
		assertEquals(142, hpCalculator.calculate(0, 4, nature));
		assertEquals(204, hpCalculator.calculate(0, 252, nature));
		assertEquals(204, hpCalculator.calculate(0, 255, nature));
	}

}
