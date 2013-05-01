package org.adaikiss.xun.unittest.powermock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * PowerMock with Mockito
 * @author hlw
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( {ValueRepository.class} )
public class PowerMockShowcaseTest {

	private PowerMockShowcase showcase;

	@Before
	public void setUp() throws Exception {
		showcase = new PowerMockShowcase();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetValue() {
		int param = 10;
		int mockedValue = 25;
		PowerMockito.mockStatic(ValueRepository.class);
		Mockito.when(ValueRepository.get(param)).thenReturn(mockedValue);
		int result = showcase.getValue(param);
		Assert.assertEquals(35, result);
		PowerMockito.verifyStatic();
	}

}
