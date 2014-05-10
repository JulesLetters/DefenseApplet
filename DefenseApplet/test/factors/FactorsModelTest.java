package factors;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class FactorsModelTest {

	private FactorsModel factorsModel;

	@Before
	public void setUp() throws Exception {
		factorsModel = new FactorsModel();
	}

	@Test
	public void testInitialValues() {
		assertEquals(1, factorsModel.getHPNumerator());
		assertEquals(1, factorsModel.getHPDenominator());
		assertEquals(1, factorsModel.getDefNumerator());
		assertEquals(1, factorsModel.getDefDenominator());
		assertEquals(1, factorsModel.getSpDefNumerator());
		assertEquals(1, factorsModel.getSpDefDenominator());
	}

}
