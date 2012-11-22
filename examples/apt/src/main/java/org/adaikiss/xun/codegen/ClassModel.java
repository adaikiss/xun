/**
 * 
 */
package org.adaikiss.xun.codegen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.lang.model.element.Modifier;

/**
 * @author hlw
 *
 */
public class ClassModel extends Model{

	private String licence;

	private String packageName;

	private List<String> imports;


	@Nonnull
	private Set<Modifier> modifiers;

	private List<String> annotations;

	private List<Field> fields;
	private List<Method> methods;
	private boolean fullConstructure;

	public ClassModel(){
		this.modifiers = new HashSet<Modifier>(2);
		this.methods = new ArrayList<Method>();
		this.fields = new ArrayList<Field>();
	}

	public String getLicence() {
		return licence;
	}


	public void setLicence(String licence) {
		this.licence = licence;
	}


	public String getPackageName() {
		return packageName;
	}


	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}


	public boolean isFullConstructure() {
		return fullConstructure;
	}


	public void setFullConstructure(boolean fullConstructure) {
		this.fullConstructure = fullConstructure;
	}

	public void addModifier(Modifier...modifiers){
		for(Modifier modifier : modifiers){			
			this.modifiers.add(modifier);
		}
	}

	public void addModifiers(Collection<Modifier> modifiers){
		this.modifiers.addAll(modifiers);
	}

	@Override
	public ClassModel collect(){
		ClassModel model = new ClassModel();
		model.licence = "";
		model.packageName = "org.adaikiss.xun.repository";
		model.imports = new ArrayList<String>();
		model.imports.add("");
		return model;
	}
}
