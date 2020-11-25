package mockpattern.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Statement;

public class MethodInvocationVisitorTypeA extends ASTVisitor {
	public String varname;
	public String method;
	public String valuereturn;
	
	public MethodInvocationVisitorTypeA() {
	}
	
	public boolean visit(MethodInvocation node) {			
//		if(node.getName().toString().equals("when")) {
//			varname = node.arguments().get(0).toString();
//		}
//		else if(node.getName().toString().equals("doReturn")) {
//			valuereturn = node.arguments().get(0).toString();
//		}
//		else {
//			method = node.getName().toString();
//		}
//		return super.visit(node);
		if(node.getName().toString().equals("when")) {
			ExtractorTypeA(node);
		}
		else {
			ExtractorTypeB(node);
		}
		
		return false;
	}
	
	private boolean ExtractorTypeA(MethodInvocation node) {
		MethodInvocation whenNode = node;
		MethodInvocation returnNode = (MethodInvocation) node.getExpression();
		
		System.out.println("whenNode");
		System.out.println(whenNode.toString());
		System.out.println("returnNode");
		System.out.println(returnNode.toString());
		
		return true;
	}
	
	private boolean ExtractorTypeB(MethodInvocation node) {
		MethodInvocation methodNode = node;
		MethodInvocation whenNode = (MethodInvocation) node.getExpression();
		MethodInvocation returnNode = (MethodInvocation) whenNode.getExpression();
		
		System.out.println("whenNode");
		List whenarg = whenNode.arguments();
		System.out.println(whenarg.get(0).toString());
		System.out.println("returnNode");
		List returnarg = returnNode.arguments();
		for(int i = 0;i < returnarg.size();i++) {
			System.out.println(returnarg.get(i).toString());
		}
		System.out.println("methodNode");
		List methodarg = methodNode.arguments();
		System.out.println(methodNode.getName().toString());
		if(methodarg.size() > 0) {
			for(int i = 0;i < methodarg.size();i++) {
				System.out.println(methodarg.get(i).toString());
			}
		}
		
		return true;
	}
	
	public String getvarname() {
		return varname;
	}
	
	public String getmethod() {
		return method;
	}
	
	public String getvaluereturn() {
		return valuereturn;
	}
}
