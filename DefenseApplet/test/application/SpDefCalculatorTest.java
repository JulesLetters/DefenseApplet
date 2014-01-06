package application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SpDefCalculatorTest {

	@Mock
	Nature nature;

	private SpDefCalculator spDefCalculator;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(nature.getSpDefAdjustment()).thenReturn(Nature.Adjustment.NEUTRAL);
		spDefCalculator = new SpDefCalculator();
	}

	@Test
	public void testZeroStatAndZeroEVsGets36() {
		assertEquals(36, spDefCalculator.calculate(0, 0, nature));
	}

	@Test
	public void testRaisingBaseStatRaisesOutputByTwoPoints() {
		assertEquals(38, spDefCalculator.calculate(1, 0, nature));
		assertEquals(56, spDefCalculator.calculate(10, 0, nature));
		assertEquals(76, spDefCalculator.calculate(20, 0, nature));
		assertEquals(546, spDefCalculator.calculate(255, 0, nature));
	}

	@Test
	public void testRaisingEVsByFourRaisesOutputByOnePoints() {
		assertEquals(36, spDefCalculator.calculate(0, 1, nature));
		assertEquals(36, spDefCalculator.calculate(0, 3, nature));
		assertEquals(37, spDefCalculator.calculate(0, 4, nature));
		assertEquals(37, spDefCalculator.calculate(0, 4, nature));
		assertEquals(99, spDefCalculator.calculate(0, 252, nature));
		assertEquals(99, spDefCalculator.calculate(0, 252, nature));
	}

	@Test
	public void testChangingBaseStatsAndEVsChangesOutput() {
		assertEquals(47, spDefCalculator.calculate(5, 4, nature));
		assertEquals(609, spDefCalculator.calculate(255, 252, nature));
	}

	@Test
	public void testDefenseBoostingNatureBoostsDefenseTenPercent() {
		when(nature.getSpDefAdjustment())
				.thenReturn(Nature.Adjustment.INCREASE);
		assertEquals(108, spDefCalculator.calculate(0, 252, nature));
		assertEquals(322, spDefCalculator.calculate(128, 4, nature));
		assertEquals(85, spDefCalculator.calculate(5, 128, nature));
		assertEquals(669, spDefCalculator.calculate(255, 252, nature));

	}

	@Test
	public void testDefenseDroppingNatureDropsDefenseTenPercent() {
		when(nature.getSpDefAdjustment())
				.thenReturn(Nature.Adjustment.DECREASE);
		assertEquals(89, spDefCalculator.calculate(0, 252, nature));
		assertEquals(263, spDefCalculator.calculate(128, 4, nature));
		assertEquals(70, spDefCalculator.calculate(5, 128, nature));
		assertEquals(548, spDefCalculator.calculate(255, 252, nature));
	}

}
