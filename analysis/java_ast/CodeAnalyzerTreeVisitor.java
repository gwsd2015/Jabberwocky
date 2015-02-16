import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Modifier;

import java.lang.Object;

public class CodeAnalyzerTreeVisitor extends TreePathScanner<Object, Trees>{
	
	public Object VisitMethod(MethodTree MethodTree, Trees trees){
		return super.visitClass(classTree, trees);
	}



}