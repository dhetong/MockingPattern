package mockpattern.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class TestVisitor extends ASTVisitor{
	ProjectAST javaAST;
	List<String> ClassName = new ArrayList<>();
	
	public TestVisitor(IJavaProject javaProject, List<String> ClassList){
		javaAST = new ProjectAST(javaProject);
		ClassName = ClassList;
	}
	
	public boolean visit(TypeDeclaration node) {
		if(node.getName().toString().contains("Test")) {
			for(int i = 0;i < ClassName.size();i++) {
//				System.out.println(ClassName.get(i));
				String[] cname = ClassName.get(i).split("-");
//				for(int j = 0;j < cname.length;j++) {
//					System.out.println(cname[j]);
//				}
				if(node.toString().contains(cname[0])) {
					System.out.println(node.getName().toString() + ":" + ClassName.get(i));
				}
			}
		}
		return super.visit(node);
	}
}
