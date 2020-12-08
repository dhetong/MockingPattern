package mockpattern.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
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
		List whenarg = whenNode.arguments();
//		System.out.println(whenarg.get(0).toString());
		argsHandler(whenarg);
		System.out.println("returnNode");
		List returnarg = returnNode.arguments();
//		for(int i = 0;i < returnarg.size();i++) {
//			System.out.println(returnarg.get(i).toString());
//		}
		argsHandler(returnarg);
		
		return true;
	}
	
	private boolean ExtractorTypeB(MethodInvocation node) {
		MethodInvocation methodNode = node;
		MethodInvocation whenNode = (MethodInvocation) node.getExpression();
		MethodInvocation returnNode = (MethodInvocation) whenNode.getExpression();
		
		System.out.println("whenNode");
		List whenarg = whenNode.arguments();
//		System.out.println(whenarg.get(0).toString());
		argsHandler(whenarg);
		System.out.println("returnNode");
		List returnarg = returnNode.arguments();
//		for(int i = 0;i < returnarg.size();i++) {
//			System.out.println(returnarg.get(i).toString());
//		}
		argsHandler(returnarg);
		System.out.println("methodNode");
		List methodarg = methodNode.arguments();
		System.out.println(methodNode.getName().toString());
//		if(methodarg.size() > 0) {
//			for(int i = 0;i < methodarg.size();i++) {
//				System.out.println(methodarg.get(i).toString());
//			}
//		}
		argsHandler(methodarg);
		
		return true;
	}
	
	private void methodHandler(List args) {
		if(args.size() > 0) {
			for(int i = 0;i < args.size();i++) {
				if(args.get(i) instanceof MethodInvocation) {
					MethodInvocation node = (MethodInvocation) args.get(i);
					List methodargs = node.arguments();
					
					System.out.println(node.getName().toString());
					
					if(node.getExpression() != null) {
						System.out.println(node.getExpression().toString());
					}
					
					if(methodargs.size() > 0) {
						for(int j = 0;j < methodargs.size();j++) {
							System.out.println(methodargs.get(j).toString());
						}
					}
					else {
						System.out.println("no parameter");
					}
				}
				else {
					System.out.println(args.get(i).toString());
					if(args.get(i) instanceof SimpleName) {
						IBinding binding = ((SimpleName) args.get(i)).resolveBinding();
						bindingCheck(binding);
					}
				}
			}
		}
		else {
			System.out.println("no parameter:");
		}
	}
	
	private void whenHandler(List args) {
		if(args.size() > 0) {
			if(args.get(0) instanceof SimpleName) {
				IBinding binding = ((SimpleName) args.get(0)).resolveBinding();
				bindingCheck(binding);
			}
			else {
				
			}
		}
	}
	
	private void returnHandler(List args) {
		if(args.size() > 0) {
			for(int i = 0;i < args.size();i++) {
				if(args.get(i) instanceof MethodInvocation) {
					MethodInvocation node = (MethodInvocation) args.get(i);
					List methodargs = node.arguments();
					
					System.out.println(node.getName().toString());
					
					if(node.getExpression() != null) {
						System.out.println(node.getExpression().toString());
					}
					
					if(methodargs.size() > 0) {
						for(int j = 0;j < methodargs.size();j++) {
							System.out.println(methodargs.get(j).toString());
						}
					}
					else {
						System.out.println("no parameter");
					}
				}
				else {
					System.out.println(args.get(i).toString());
					if(args.get(i) instanceof SimpleName) {
						IBinding binding = ((SimpleName) args.get(i)).resolveBinding();
						bindingCheck(binding);
					}
				}
			}
		}
		else {
			System.out.println("no parameter:");
		}
	}
	
	private void argsHandler(List args) {
		if(args.size() > 0) {
			for(int i = 0;i < args.size();i++) {
				if(args.get(i) instanceof MethodInvocation) {
					MethodInvocation node = (MethodInvocation) args.get(i);
					List methodargs = node.arguments();
					
					System.out.println(node.getName().toString());
					
					if(node.getExpression() != null) {
						System.out.println(node.getExpression().toString());
					}
					
					if(methodargs.size() > 0) {
						for(int j = 0;j < methodargs.size();j++) {
							System.out.println(methodargs.get(j).toString());
						}
					}
					else {
						System.out.println("no parameter");
					}
				}
				else {
					System.out.println(args.get(i).toString());
					if(args.get(i) instanceof SimpleName) {
						IBinding binding = ((SimpleName) args.get(i)).resolveBinding();
						bindingCheck(binding);
					}
				}
			}
		}
		else {
			System.out.println("no parameter:");
		}
		
	}
	
	private void bindingCheck(IBinding binding) {
		System.out.println("type:");
		if(binding == null) {
			System.out.println("null");
		}
		else {
			System.out.println("not null");
		}
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
