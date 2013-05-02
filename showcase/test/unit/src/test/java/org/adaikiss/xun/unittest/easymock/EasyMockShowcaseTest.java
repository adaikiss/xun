package org.adaikiss.xun.unittest.easymock;

import static org.junit.Assert.assertEquals;

import org.adaikiss.xun.unittest.ValueRepository;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class EasyMockShowcaseTest {

	private EasyMockShowcase showcase;
	private ValueRepository valueRepository;

	@Before
	public void setUp() throws Exception {
		showcase = new EasyMockShowcase();
		valueRepository = EasyMock.createMock(ValueRepository.class);
		showcase.setRepository(valueRepository);
	}

	@Test
	public void testGetValue() {
		int param = 10;
		int mockedValue = 25;
		//mock void method
		valueRepository.reset();
		EasyMock.expectLastCall();
		//mock method with returning value
		EasyMock.expect(valueRepository.get(param)).andReturn(mockedValue);
		EasyMock.replay(valueRepository);
		int result = showcase.getValue(param);
		assertEquals(mockedValue + 10, result);
		EasyMock.verify(valueRepository);
	}

}
