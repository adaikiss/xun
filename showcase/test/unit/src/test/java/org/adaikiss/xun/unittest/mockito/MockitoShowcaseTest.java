package org.adaikiss.xun.unittest.mockito;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MockitoShowcaseTest {

	private MockitoShowcase showcase;
	private ValueRepository valueRepository;

	@Before
	public void setUp() throws Exception {
		showcase = new MockitoShowcase();
		valueRepository = Mockito.mock(ValueRepository.class);
		showcase.setRepository(valueRepository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetValue() {
		int param = 10;
		int mockedValue = 25;
		Mockito.when(valueRepository.get(param)).thenReturn(mockedValue);
		int result = showcase.getValue(param);
		assertEquals(mockedValue + 10, result);
		Mockito.verify(valueRepository.get(param));
	}

}
