/**
 * 
 */
package org.adaikiss.xun.codegen;


/**
 * @author hlw
 * 
 */
public class ModelFactory {
	public enum ModelType {
		CLASS(ClassModel.class);
		private Class<? extends Model> type;

		ModelType(Class<? extends Model> type) {
			this.type = type;
		}
	}

	public static Model buildModel(ModelType modelType) {
		try {
			Model model = modelType.type.getConstructor().newInstance();
			modelType.type.getDeclaredMethod("collect").invoke(model);
			return model;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
