package factors;

import static org.mockito.Mockito.verify;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.eventbus.EventBus;

import factors.events.DefDenominatorChanged;
import factors.events.DefNumeratorChanged;
import factors.events.HPDenominatorChanged;
import factors.events.HPNumeratorChanged;
import factors.events.SpDefDenominatorChanged;
import factors.events.SpDefNumeratorChanged;

public class FactorsPresenterTest {

	@Mock
	private IFactorsModel model;

	private Random random = new Random();
	private EventBus eventBus;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		FactorsPresenter factorsPresenter = new FactorsPresenter(model);
		eventBus = new EventBus();
		eventBus.register(factorsPresenter);
	}

	@Test
	public void testHpNumeratorChangeIsGivenToModel() {
		int number = random.nextInt();
		eventBus.post(new HPNumeratorChanged(number));
		verify(model).setHPNumerator(number);
	}

	@Test
	public void testHpDenominatorChangeIsGivenToModel() {
		int number = random.nextInt();
		eventBus.post(new HPDenominatorChanged(number));
		verify(model).setHPDenominator(number);
	}

	@Test
	public void testDefNumeratorChangeIsGivenToModel() {
		int number = random.nextInt();
		eventBus.post(new DefNumeratorChanged(number));
		verify(model).setDefNumerator(number);
	}

	@Test
	public void testDefDenominatorChangeIsGivenToModel() {
		int number = random.nextInt();
		eventBus.post(new DefDenominatorChanged(number));
		verify(model).setDefDenominator(number);
	}

	@Test
	public void testSpDefNumeratorChangeIsGivenToModel() {
		int number = random.nextInt();
		eventBus.post(new SpDefNumeratorChanged(number));
		verify(model).setSpDefNumerator(number);
	}

	@Test
	public void testSpDefDenominatorChangeIsGivenToModel() {
		int number = random.nextInt();
		eventBus.post(new SpDefDenominatorChanged(number));
		verify(model).setSpDefDenominator(number);
	}
}
