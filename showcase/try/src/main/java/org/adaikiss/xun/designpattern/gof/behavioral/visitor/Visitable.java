/**
 * 
 */
package org.adaikiss.xun.designpattern.gof.behavioral.visitor;

/**
 * @author hlw
 *
 */
public interface Visitable {
	void accept(IVisitor visitor);
}
