package mockpattern.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MethodInvocationVisitorTypeA extends ASTVisitor {
	public String varname;
	public String method;
	public String valuereturn;
	
	public MethodInvocationVisitorTypeA() {
	}
	
	public boolean visit(MethodInvocation node) {
		if(node.getName().toString().equals("when")) {
			varname = node.arguments().get(0).toString();
		}
		else if(node.getName().toString().equals("doReturn")) {
			valuereturn = node.arguments().get(0).toString();
		}
		else {
			method = node.getName().toString();
		}
		return super.visit(node);		
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
