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

import restrictions.IRestrictionsModel;
import restrictions.IRestrictionsViewListener;
import restrictions.RestrictionsPanel;
import restrictions.RestrictionsPresenter;
import application.Nature;

public class RestrictionsPresenterTest {

	@Mock
	private RestrictionsPanel view;
	@Mock
	private IRestrictionsModel model;
	private IRestrictionsViewListener viewListener;

	private final List<Nature> initialSelectedNatures = Arrays.asList(
			Nature.Possibility.NEUTRAL.getNature(),
			Nature.Possibility.INC_DEF.getNature(),
			Nature.Possibility.INC_SPDEF.getNature(),
			Nature.Possibility.DEC_DEF.getNature(),
			Nature.Possibility.DEC_SPDEF.getNature());

	private RestrictionsPresenter distributionRestrictionsPresenter;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ArgumentCaptor<IRestrictionsViewListener> viewListenerCaptor = ArgumentCaptor
				.forClass(IRestrictionsViewListener.class);
		distributionRestrictionsPresenter = new RestrictionsPresenter(
				view, model);

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
		inOrder.verify(view).setViewListener(
				any(IRestrictionsViewListener.class));
		inOrder.verify(view).setSelectedNatures(initialSelectedNatures);
	}
}
