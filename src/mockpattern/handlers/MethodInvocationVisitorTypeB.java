package mockpattern.handlers;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MethodInvocationVisitorTypeB extends ASTVisitor {
	public String varname;
	public String method;
	public String valuereturn;
	
	public MethodInvocationVisitorTypeB() {
	}
	
	public boolean visit(MethodInvocation node) {
//		if(node.getName().toString().equals("when")) {
//		}
//		else if(node.getName().toString().equals("thenReturn")) {
////			valuereturn = node.arguments().get(0).toString();
////			System.out.println(node.arguments().get(0).toString());
//		}
//		else {
////			method = node.getName().toString();
//			if(node.getExpression() != null) {
//				System.out.println(node.getExpression().toString());
//			}
////			System.out.println(node.getName().toString());
//		}
//		return super.visit(node);	
		while(true) {
			if(node.getName().toString().equals("when")) {
				List whenarg = node.arguments();
				System.out.println("WhenNode");
				printList(whenarg);
				break;
			}
			else {
				List returnarg = node.arguments();
				System.out.println("ReturnNode");
				printList(returnarg);
			}
			node = (MethodInvocation) node.getExpression();
		}
		
		return false;
	}

	private void printList(List arg) {
		if(arg.size() > 0) {
			for(int i = 0;i < arg.size();i++) {
				System.out.println(arg.get(i).toString());
			}
		}
	}
}
