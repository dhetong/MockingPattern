package mockpattern.handlers;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MethodInvocationVisitorTypeB extends ASTVisitor {
	public String varname;
	public String method;
	public String valuereturn;
	
	public MethodInvocationVisitorTypeB() {
	}
	
	public boolean visit(MethodInvocation node) {
		if(node.getName().toString().equals("when")) {
		}
		else if(node.getName().toString().equals("thenReturn")) {
//			valuereturn = node.arguments().get(0).toString();
//			System.out.println(node.arguments().get(0).toString());
		}
		else {
//			method = node.getName().toString();
			if(node.getExpression() != null) {
				System.out.println(node.getExpression().toString());
			}
//			System.out.println(node.getName().toString());
		}
		return super.visit(node);		
	}
}
