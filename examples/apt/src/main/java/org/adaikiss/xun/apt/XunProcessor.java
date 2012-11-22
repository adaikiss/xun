/**
 * 
 */
package org.adaikiss.xun.apt;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import org.adaikiss.xun.codegen.ClassModel;
import org.adaikiss.xun.codegen.JpaRepositorySerializer;
import org.adaikiss.xun.codegen.ModelFactory;
import org.adaikiss.xun.codegen.ModelFactory.ModelType;


/**
 * @author hlw
 * 
 */
@SupportedAnnotationTypes({"org.adaikiss.xun.apt.Xun"})
public class XunProcessor extends AbstractProcessor {

	boolean f = true;
	
	//@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Running " + getClass().getSimpleName());
		if (roundEnv.processingOver() || annotations.size() == 0) {
			return Boolean.FALSE;
		}
		Elements elementUtils = processingEnv.getElementUtils();
		ClassModel classModel = new ClassModel();
		System.out.println(roundEnv.getRootElements());
		Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Xun.class);
		for(Element element : elements){
			switch(element.getKind()){
			case CLASS : 
				System.out.println();
				classModel.setName(element.getSimpleName().toString());
				classModel.setPackageName(elementUtils.getPackageOf(element).getQualifiedName().toString());
				classModel.addModifiers(element.getModifiers());
				System.out.println(classModel.getName());
				break;
			}
			System.out.println(element.getModifiers());
			List<? extends AnnotationMirror> mirrors = element.getAnnotationMirrors();
			for(AnnotationMirror mirror : mirrors){
				DeclaredType declaredType = mirror.getAnnotationType();
				System.out.println(mirror.getElementValues());
				System.out.println(declaredType);
				System.out.println(declaredType.getTypeArguments());
			}
		}
		if(f){
			return true;
		}
		String packageName = "org.adaikiss.xun.apt";
		String className = "XunGenerated";
		try {
			JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(packageName + "." + className, elements.toArray(new Element[elements.size()]));
			Writer writer = fileObject.openWriter();
			processJpaRepository(writer);
			StringBuilder sb = new StringBuilder();
			line(sb, "package ", packageName, ";");
			line(sb, "import org.adaikiss.xun.apt.Xun;");
			line(sb, "import javax.annotation.Generated;");
			line(sb, "@Generated(\"", getClass().getName(), "\")");
			line(sb, "public class ", className, " {");
			line(sb, "	@Generated(\"", getClass().getName(), "\")");
			line(sb, "	public Class<?> clazz = Xun.class;");
			line(sb, "}");
			try {
				writer.append(sb.toString());
			} finally {
				if(writer != null){
					writer.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			processingEnv.getMessager().printMessage(Kind.ERROR, e.getMessage());
		}
		return false;
	}

	private void processJpaRepository(Writer writer){
		new JpaRepositorySerializer().init().serialize(ModelFactory.buildModel(ModelType.CLASS), writer);
	}

	private void processField(){
		
	}

	private StringBuilder line(StringBuilder sb, Object...params){
		for(Object o : params){			
			sb.append(o);
		}
		sb.append("\n");
		return sb;
	}
}
