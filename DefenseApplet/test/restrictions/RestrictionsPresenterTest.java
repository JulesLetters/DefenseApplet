package restrictions;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import application.Nature;

public class RestrictionsPresenterTest {

	@Mock
	private RestrictionsPanel view;
	@Mock
	private IRestrictionsModel model;
	private IRestrictionsViewListener viewListener;

	private final List<Nature> initialSelectedNatures = Arrays.asList(Nature.Possibility.NEUTRAL.getNature(),
			Nature.Possibility.INC_DEF.getNature(), Nature.Possibility.INC_SPDEF.getNature());

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ArgumentCaptor<IRestrictionsViewListener> viewListenerCaptor = ArgumentCaptor
				.forClass(IRestrictionsViewListener.class);
		@SuppressWarnings("unused")
		RestrictionsPresenter distributionRestrictionsPresenter = new RestrictionsPresenter(view, model);

		verify(view).setViewListener(viewListenerCaptor.capture());
		viewListener = viewListenerCaptor.getValue();
	}

	@Test
	public void testWhenViewAddsNatureItIsAddedToModel() {
		Nature nature = mock(Nature.class);

		viewListener.natureAdded(nature);

		verify(model).addNature(nature);
	}

	@Test
	public void testWhenViewRemovesNatureItIsRemovedFromModel() {
		Nature nature = mock(Nature.class);

		viewListener.natureRemoved(nature);

		verify(model).removeNature(nature);
	}

	@Test
	public void testViewReceivesInitialCollection() {
		verify(view).setSelectedNatures(initialSelectedNatures);
	}

	@Test
	public void testViewReceivesInitialCollectionAfterListenerSet() {
		InOrder inOrder = inOrder(view);
		inOrder.verify(view).setViewListener(any(IRestrictionsViewListener.class));
		inOrder.verify(view).setSelectedNatures(initialSelectedNatures);
	}

	@Test
	public void testModelUpdatedWhenMaxEVsChange() {
		int value = 1;

		viewListener.maxEVsChanged(value);

		verify(model).setMaxEVs(value);
	}

	@Test
	public void testModelReallyUpdatedWhenMaxEVsChange() {
		int value = 2;

		viewListener.maxEVsChanged(value);

		verify(model).setMaxEVs(value);
	}

	@Test
	public void testModelUpdatedWhenMinEVsChange() {
		int value = 1;

		viewListener.minEVsChanged(value);

		verify(model).setMinEVs(value);
	}

	@Test
	public void testModelReallyUpdatedWhenMinEVsChange() {
		int value = 2;

		viewListener.minEVsChanged(value);

		verify(model).setMinEVs(value);
	}
}
