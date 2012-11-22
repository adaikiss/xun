/**
 * 
 */
package org.adaikiss.xun.apt;

import java.util.Set;

import javax.annotation.Nonnull;

/**
 * @author hlw
 * 
 */
public class Configuration {
	// ......codegen begin...... //

	protected String packagePrefix;

	// ......codegen end...... //

	private final Set<String> excludedPackages;

	@Nonnull
	private final Set<String> excludedClasses;
}
