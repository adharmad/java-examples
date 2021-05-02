package com.example.javaexamples.javaparser;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;

//import com.sun.source.tree.CompilationUnitTree;
//import com.sun.source.util.JavacTask;
//import com.sun.tools.javac.api.JavacTool;
//import com.sun.tools.javac.file.JavacFileManager;
//import com.sun.tools.javac.util.Context;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class JavaFileParser {
    /*

    private JavacFileManager jcFileManager;

    private JavacTool jcTool;

    public JavaFileParser() {
        Context context = new Context();
        jcFileManager = new JavacFileManager(context, true, Charset.defaultCharset());
        jcTool = new JavacTool();
    }

    public static void main(String[] args) {
        String javaFilePath = "/Users/adharmad/Dropbox/dev/ionate/re-engineering/wsdl-to-rest/src/main/java/com/ionate/wsdl2rest/model/mapping/MappingDesignator.java";

        JavaFileParser jfp = new JavaFileParser();

        List varList = jfp.parseJavaSourceFile(javaFilePath);
        System.out.println(varList);
    }

    public List parseJavaSourceFile(String filePath) {
        //List retMethodList = new ArrayList();
        List varList = new ArrayList();

        // Create a Java Source Visitor object.
        JavaSourceVisitor jsv = new JavaSourceVisitor();

        // Get files object list from the java file path.
        Iterable<? extends JavaFileObject> javaFiles = jcFileManager.getJavaFileObjects(filePath);

        // Get the java compiler task object.
        JavaCompiler.CompilationTask cTask = jcTool.getTask(null, jcFileManager, null, null, null, javaFiles);
        JavacTask jcTask = (JavacTask) cTask;

//        try {
//            // Iterate the java compiler parse out task.
//            Iterable<? extends CompilationUnitTree> codeResult = jcTask.parse();
//            for (CompilationUnitTree codeTree : codeResult) {
//                // Parse out one java file source code.
//                codeTree.accept(jsv, null);
//            }
//
//            // Get the parsed out method list.
//            //retMethodList = jsv.getMethodList();
//            varList = jsv.getVarList();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        return varList;
    }
    */
}