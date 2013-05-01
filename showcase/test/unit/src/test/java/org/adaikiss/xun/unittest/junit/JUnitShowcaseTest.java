package org.adaikiss.xun.unittest.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class JUnitShowcaseTest {

	private static JUnitShowcase showcase;

	@BeforeClass
	public static void beforeClass() throws Exception {
		showcase = new JUnitShowcase();
	}

	public static class ShowBasic {
		@Test
		public void testGetValue() {
			Assert.assertEquals("should be expected value [2]!", 2,
					showcase.getValue(1));
			assumeThat(showcase.getValue(0), is(1));
			assertThat(showcase.getValue(0), is(1));
		}
	}

	public static class ShowException {
		@Test(expected = IndexOutOfBoundsException.class)
		public void testGetValue() {
			showcase.getValue(3);
		}
	}

	public static class ShowExceptionDetail {
		@Test
		public void testGetValue() {
			try {
				showcase.getValue(3);
			} catch (Exception e) {
				assertThat(e.getMessage(), is("3"));
			}
		}
	}

	public static class ShowExceptionExpected {
		@Rule
		public ExpectedException thrown = ExpectedException.none();

		@Test
		public void testGetValue() {
			thrown.expect(ArrayIndexOutOfBoundsException.class);
			thrown.expectMessage("3");
			showcase.getValue(3);
		}
	}

	@RunWith(Parameterized.class)
	public static class ShowParameterized {
		private int input, expected;

		public ShowParameterized(int input, int expected) {
			this.input = input;
			this.expected = expected;
		}

		/**
		 * each tuple will be passed to the constructor
		 * @return
		 */
		@Parameters
		public static Iterable<Object[]> data() {
			return Arrays.asList(new Object[][] { { 0, 1 }, { 1, 2 } });
		}

		@Test
		public void testGetValue() {
			System.out.println("run!");
			assertEquals(expected, showcase.getValue(input));
		}
	}

	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	public static class ShowFixedOrder{
		private int flag = 0;

		@Test
		public void testB(){
			assertEquals("this test should run after testA!", 1, flag);
		}

		@Test
		public void testA(){
			assertEquals("this test should run first!", 0, flag++);
		}
	}
	
}
