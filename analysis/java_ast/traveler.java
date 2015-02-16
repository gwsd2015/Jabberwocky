import java.util.*;
import javax.tools.*;
import java.io.File;
public class traveler{

    public static void main(String[] args)
    {
        runTree("Test.java");
    }

    //Gets a file from the system
    public static void runTree(String fileName)
    {

        //Save source in .java file
        File sourceFile = new File(fileName);


        //Get instance of java compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        
        //Get new isntance ofr the standard file manager implementation
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
        

        //Create array of file
        File [] files = new File[] {sourceFile};

        //Get the list of java file objects, in this case we have only one file
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files));

        //Create a compilation task
        JavaCompiler.CompilationTask task = compiler.getTask(null,fileManager,null,null,null,compilationUnits);
        
        //perform the compilation task
        task.call();

    }

    public Object visitClass(ClassTree classTree, Tress trees)
    {
        //Storing the details of the visiting class into a model
        JavaClassInfo clazzInfo = new JavaClassInfo();

        //Get the current path of the node
        TreePath path = getCurrentPath();

        //Get the type element corresponding to the class
        TypeElement e = (TypeElement)trees.getElement(path);

        //Set qualified class name into model
        clazzInfo.setName(e.getQualifiedName().toString());

        //set extending class info
        clazzInfo.setNameOfSuperClass(e.getSuperclass().toString());

        //set implementing interface details
        for(TypeMirror mirror: e.getInterfaces()){
            clazzInfo.addNameofInterface(mirror.toString());
        }

        return super.visitClass(classTree, trees);




    }


}









