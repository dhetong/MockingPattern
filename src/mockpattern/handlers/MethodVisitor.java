package mockpattern.handlers;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.internal.corext.callhierarchy.MethodWrapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class MethodVisitor extends ASTVisitor {
	ProjectAST javaAST;
	List<String> ClassName = new ArrayList<>();
//	HashSet<String> ClassName = new HashSet<String>();
	HashSet<String> MethodName = new HashSet<String>();
	//MethodName format: Class||Method
	
	public MethodVisitor(IJavaProject javaProject){
		javaAST = new ProjectAST(javaProject);
	}
	
	public List<String> GetClassNames() {
		return ClassName;
	}
	
	public List<String> VarFinder(MethodDeclaration node){
		Block body = node.getBody();
		List<String> varnames = new ArrayList<>();
		
		if(body != null) {
			for(Statement s : (List<Statement>) body.statements()) {
				if(s.getNodeType() == Statement.EXPRESSION_STATEMENT) {
					Pattern PatternWhen = Pattern.compile("when\\(([^}]*)\\)\\.");
					Matcher matcher = PatternWhen.matcher(s.toString());
					if(matcher.find()) {
						if(s.toString().contains("doReturn")) {
							System.out.println(node.getName().toString());
							System.out.println(s.toString());
							MethodInvocationVisitorTypeA visitor = new MethodInvocationVisitorTypeA();
							s.accept(visitor);
						}
						if(s.toString().contains("thenReturn")) {
							System.out.println(node.getName().toString());
							System.out.println(s.toString());
							MethodInvocationVisitorTypeB visitor = new MethodInvocationVisitorTypeB();
							s.accept(visitor);
						}
					}
				}
//				else if(s.getNodeType() == Statement.ENHANCED_FOR_STATEMENT) {	
//					for(Statement sfor : (List<Statement>) ((EnhancedForStatement) s).getBody()) {						
//					}
//				}
//				else if(s.getNodeType() == Statement.TRY_STATEMENT) {					
//				}
//				else {
//				}
			}
		}
		
		return varnames;
	}
	
	public List<String> ClassFinder(MethodDeclaration node, List<String> varlist){
		List<String> classlist = new ArrayList<>();
		Block body = node.getBody();
		
		if(body!=null) {
			for(Statement s : (List<Statement>) body.statements()) {
				for(int i = 0; i < varlist.size(); i++) {
					if(s.toString().contains(varlist.get(i))) {
						Pattern PatternMock = Pattern.compile("mock\\(([^}]*)\\.class\\)");
						Matcher matcher = PatternMock.matcher(s.toString());
						if(matcher.find()) {
							String p = matcher.group();
							String cname = p.substring(5, p.length()-7);
							//System.out.println(cname);
							if(!classlist.contains(cname)) {
								classlist.add(cname);
							}
						}
					}
				}
			}
		}
		
		return classlist;
	}
	
//	public boolean visit(ExpressionStatement node) {
//		Pattern PatternWhen = Pattern.compile("when\\(([^}]*)\\)\\.");
//		Matcher matcher = PatternWhen.matcher(node.toString());
//		if(matcher.find()) {
//			String p = matcher.group();
//			String content = p.substring(5, p.length()-2);
//			
//			System.out.println(content);
//			String[] var = content.split("\\.");
//			String vname = var[0];
//		}
////		if(node.toString().contains("when(")) {
////			System.out.println(matcher.find());
////			System.out.println(node.toString());
////		}
//		return super.visit(node);
//	}
	
	public boolean visit(MethodDeclaration node) {
		List<String> varnames = VarFinder(node);
//		if(varnames.size()>0) {
//			List<String> classnames = new ArrayList<>();
//			classnames = ClassFinder(node, varnames);
//			if(classnames.size()>0) {
//				for(int i = 0;i < classnames.size();i++) {
//					String mockinfo = classnames.get(i) + "-" + node.getName().toString();
//					if(!ClassName.contains(mockinfo)) {
//						ClassName.add(mockinfo);
//					}
//				}
//			}
//		}
		return super.visit(node);		
	}

}

