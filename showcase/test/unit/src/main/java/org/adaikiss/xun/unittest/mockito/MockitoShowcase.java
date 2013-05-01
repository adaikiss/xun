/**
 * 
 */
package org.adaikiss.xun.unittest.mockito;

/**
 * @author hlw
 *
 */
public class MockitoShowcase {
	private ValueRepository repository;

	/**
	 * get value from repository and plus 10
	 * @param index
	 * @return
	 */
	public int getValue(int index){
		return repository.get(index) + 10;
	}

	void setRepository(ValueRepository repository) {
		this.repository = repository;
	}
}
