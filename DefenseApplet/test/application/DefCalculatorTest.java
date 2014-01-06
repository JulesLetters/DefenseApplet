package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DefCalculatorTest {

	@Mock
	Nature nature;

	private DefCalculator defCalculator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(nature.getDefAdjustment()).thenReturn(Nature.Adjustment.NEUTRAL);
		defCalculator = new DefCalculator();
	}

	@Test
	public void testZeroStatAndZeroEVsGets36() {
		assertEquals(36, defCalculator.calculate(0, 0, nature));
	}

	@Test
	public void testRaisingBaseStatRaisesOutputByTwoPoints() {
		assertEquals(38, defCalculator.calculate(1, 0, nature));
		assertEquals(56, defCalculator.calculate(10, 0, nature));
		assertEquals(76, defCalculator.calculate(20, 0, nature));
		assertEquals(546, defCalculator.calculate(255, 0, nature));
	}

	@Test
	public void testRaisingEVsByFourRaisesOutputByOnePoints() {
		assertEquals(36, defCalculator.calculate(0, 1, nature));
		assertEquals(36, defCalculator.calculate(0, 3, nature));
		assertEquals(37, defCalculator.calculate(0, 4, nature));
		assertEquals(37, defCalculator.calculate(0, 4, nature));
		assertEquals(99, defCalculator.calculate(0, 252, nature));
		assertEquals(99, defCalculator.calculate(0, 252, nature));
	}

	@Test
	public void testChangingBaseStatsAndEVsChangesOutput() {
		assertEquals(47, defCalculator.calculate(5, 4, nature));
		assertEquals(609, defCalculator.calculate(255, 252, nature));
	}

	@Test
	public void testDefenseBoostingNatureBoostsDefenseTenPercent() {
		when(nature.getDefAdjustment()).thenReturn(Nature.Adjustment.INCREASE);
		assertEquals(108, defCalculator.calculate(0, 252, nature));
		assertEquals(322, defCalculator.calculate(128, 4, nature));
		assertEquals(85, defCalculator.calculate(5, 128, nature));
		assertEquals(669, defCalculator.calculate(255, 252, nature));

	}

	@Test
	public void testDefenseDroppingNatureDropsDefenseTenPercent() {
		when(nature.getDefAdjustment()).thenReturn(Nature.Adjustment.DECREASE);
		assertEquals(89, defCalculator.calculate(0, 252, nature));
		assertEquals(263, defCalculator.calculate(128, 4, nature));
		assertEquals(70, defCalculator.calculate(5, 128, nature));
		assertEquals(548, defCalculator.calculate(255, 252, nature));
	}

}
