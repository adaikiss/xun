/**
 * 
 */
package org.adaikiss.xun.unittest.mockito;

import org.adaikiss.xun.unittest.ValueRepository;

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
		repository.reset();
		return repository.get(index) + 10;
	}

	void setRepository(ValueRepository repository) {
		this.repository = repository;
	}
}
