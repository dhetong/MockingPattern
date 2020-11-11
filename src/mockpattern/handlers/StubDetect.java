package mockpattern.handlers;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class StubDetect extends AbstractHandler {
	List<String> ClassNameMock = new ArrayList<>();

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
//		System.out.println(workspace.toString());
		IWorkspaceRoot root = workspace.getRoot();
//		System.out.println(root.getLocation().toString());
		IProject[] projects = root.getProjects();
//		System.out.println(projects.length);
		
		DetectProjects(projects);
		
		System.out.println("DONE DETECTING");
		return null;
	}
	
//	private void DetectTest(IProject[] projects) {
//		for(IProject project : projects) {
//			System.out.println("DETECTING IN: " + project.getName());
//			try {
//				CheckTest(project);
//			} catch (CoreException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	private void CheckTest(IProject project) throws CoreException{
//		IJavaProject javaProject = JavaCore.create(project);
//		IPackageFragment[] packages = javaProject.getPackageFragments();
//		System.out.println(packages.toString());
//				
//		for (IPackageFragment mypackage : packages){
//			
//			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE)
//			{
//				for (ICompilationUnit unit : mypackage.getCompilationUnits())
//				{
//					CompilationUnit cunit = ASTBuilder(unit);
//				    TestVisitor visitor = new TestVisitor(javaProject, ClassNameMock);
//				    cunit.accept(visitor);
//				}
//			}
//		}
//	}
	
	private void DetectProjects(IProject[] projects) {
		for(IProject project : projects) {
			System.out.println("DETECTING IN: " + project.getName());
			try {
				ProcessProject(project);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void ProcessProject(IProject project) throws CoreException{
		IJavaProject javaProject = JavaCore.create(project);
		IPackageFragment[] packages = javaProject.getPackageFragments();
		System.out.println(packages.toString());
				
		for (IPackageFragment mypackage : packages){
			
			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE)
			{
				for (ICompilationUnit unit : mypackage.getCompilationUnits())
				{
					CompilationUnit cunit = ASTBuilder(unit);
				    MethodVisitor visitor = new MethodVisitor(javaProject);
				    cunit.accept(visitor);
				}
			}
		}
	}
	
	private CompilationUnit ASTBuilder(ICompilationUnit unit) throws CoreException {
		ASTParser astParser = ASTParser.newParser(AST.JLS3);
		astParser.setResolveBindings(true);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);
		astParser.setBindingsRecovery(true);
		@SuppressWarnings("rawtypes")
		Map options = JavaCore.getOptions();
		astParser.setCompilerOptions(options);
		astParser.setSource(unit);
		CompilationUnit result = (CompilationUnit) (astParser.createAST(null));
        return result;
	}
}