package restrictions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import restrictions.RestrictionsModel;
import application.Nature;

public class RestrictionsModelTest {

	private RestrictionsModel restrictionsModel;

	@Before
	public void setUp() throws Exception {
		restrictionsModel = new RestrictionsModel();
	}

	@Test
	public void testAllowedNaturesIsInitialiallyEmptySet() {
		int size = restrictionsModel.getAllowedNatures().size();

		assertEquals(0, size);
	}

	@Test
	public void testAddedNatureShowsInAllowedNatures() {
		Nature nature = mock(Nature.class);

		restrictionsModel.addNature(nature);

		Set<Nature> allowedNatures = restrictionsModel.getAllowedNatures();

		assertEquals(1, allowedNatures.size());
		assertEquals(nature, allowedNatures.iterator().next());
	}

	@Test
	public void testMultipleAddedNaturesShowsInAllowedNatures() {
		Nature nature1 = mock(Nature.class);
		Nature nature2 = mock(Nature.class);

		restrictionsModel.addNature(nature1);
		restrictionsModel.addNature(nature2);

		Set<Nature> allowedNatures = restrictionsModel.getAllowedNatures();

		assertEquals(2, allowedNatures.size());
		assertTrue(allowedNatures.contains(nature1));
		assertTrue(allowedNatures.contains(nature2));
	}

	@Test
	public void testRemoveNatureRemovesItFromAllowedNatures() {
		Nature nature1 = mock(Nature.class);
		Nature nature2 = mock(Nature.class);

		restrictionsModel.addNature(nature1);
		restrictionsModel.addNature(nature2);
		restrictionsModel.removeNature(nature1);

		Set<Nature> allowedNatures = restrictionsModel.getAllowedNatures();

		assertEquals(1, allowedNatures.size());
		assertTrue(allowedNatures.contains(nature2));
	}

	@Test
	public void testSettingAllowedNaturesSetsAllowedNatures() {
		Nature nature1 = mock(Nature.class);
		Nature nature2 = mock(Nature.class);

		List<Nature> initialSelectedNatures = Arrays.asList(nature1, nature2);
		restrictionsModel.setAllowedNatures(initialSelectedNatures);

		Set<Nature> allowedNatures = restrictionsModel.getAllowedNatures();

		assertEquals(2, allowedNatures.size());
		assertTrue(allowedNatures.contains(nature1));
		assertTrue(allowedNatures.contains(nature2));
	}

	@Test
	public void testSettingAllowedNaturesClearsOldNatures() {
		Nature nature1 = mock(Nature.class);
		Nature nature2 = mock(Nature.class);

		restrictionsModel.setAllowedNatures(Arrays.asList(nature1));
		restrictionsModel.setAllowedNatures(Arrays.asList(nature2));

		Set<Nature> allowedNatures = restrictionsModel.getAllowedNatures();

		assertEquals(1, allowedNatures.size());
		assertTrue(allowedNatures.contains(nature2));
	}

	@Test
	public void testGetAllowedNaturesReturnsCopyOfCollection() {
		Set<Nature> allowedNatures1 = restrictionsModel.getAllowedNatures();
		Set<Nature> allowedNatures2 = restrictionsModel.getAllowedNatures();
		assertNotSame(allowedNatures1, allowedNatures2);
	}

}
