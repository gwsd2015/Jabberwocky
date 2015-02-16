package antlr4;


import antlr4.JavaParser.MyMethodNameContext;


import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;



public class MyVisitor extends JavaBaseVisitor<Void>{
    
    public static void main(String[] args) throws IOException{
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream("MyVisitor.java"));

        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit();

        MyVisitor visitor = new MyVisitor();

        visitor.visit(tree);


    }


    private String someAttribute;

    @Override
    public void visitMyMethodName(MyMethodNameContext ctx){
        System.out.println("Method name: "+ ctx.getText());
        return super.visitMyMethodName(ctx);
    }


}