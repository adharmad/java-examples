package com.example.javaexamples.javaparser;


import java.util.ArrayList;
import java.util.List;

//import com.sun.source.tree.ExpressionTree;
//import com.sun.source.tree.MethodTree;
//import com.sun.source.tree.VariableTree;
//import com.sun.source.util.TreeScanner;

/**
 * @author Amol Dharmadhikari (adharmad@ionate.io)
 */
public class JavaSourceVisitor { //extends TreeScanner {

    /*
    private List methodList = new ArrayList();
    private List varList = new ArrayList();
    private boolean inMethod = false;

    public List getMethodList() {
        return methodList;
    }


    public List getVarList() {
        return varList;
    }

    @Override
    public Object visitMethod(MethodTree mt, Object obj) {

        if(mt!=null)
        {
            JavaMethodDTO javaMethodDto = new JavaMethodDTO();

            String modifier = StringTool.getObjectStringValue(mt.getModifiers());
            System.out.println("mt.getModifiers() : " + modifier);

            String returnType = StringTool.getObjectStringValue(mt.getReturnType());
            System.out.println("mt.getReturnType() : " + returnType);

            String methodName = StringTool.getObjectStringValue(mt.getName());
            System.out.println("mt.getName() : " + methodName);

            List paramStrList = new ArrayList();

            List<? extends VariableTree> paramList = mt.getParameters();
            if(paramList!=null)
            {
                for(VariableTree vt : paramList)
                {
                    String paramStr = StringTool.getObjectStringValue(vt);
                    System.out.println("param : " + paramStr);
                    paramStrList.add(paramStr);
                }
            }

            //System.out.println("mt.getDefaultValue() : " + StringTool.getObjectStringValue(mt.getDefaultValue()));

            //System.out.println("mt.getKind() : " + StringTool.getObjectStringValue(mt.getKind()));

            List throwsStrList = new ArrayList();

            List<? extends ExpressionTree> throwsList = mt.getThrows();
            if(throwsList!=null)
            {
                for(ExpressionTree et : throwsList)
                {
                    String throwsStr = StringTool.getObjectStringValue(et);
                    System.out.println("throws : " + throwsStr);
                    throwsStrList.add(throwsStr);
                }
            }

            String methodBody = StringTool.getObjectStringValue(mt.getBody());

            System.out.println("mt.getBody() : " + methodBody);

            javaMethodDto.setMethodModifier(modifier);
            javaMethodDto.setMethodReturnType(returnType);
            javaMethodDto.setMethodName(methodName);
            javaMethodDto.setMethodParamList(paramStrList);
            javaMethodDto.setMethodThrowsList(throwsStrList);
            javaMethodDto.setMethodBody(methodBody);

            this.methodList.add(javaMethodDto);
        }

        if(obj!=null)
        {
            System.out.println(obj.toString());
        }

//        inMethod = true;
//        Object o = super.visitMethod(mt, obj);
//        if (o != null) {
//            System.out.println(o.getClass().getName());
//        }
        inMethod = false;

        return o;
    }


    @Override
    public Object visitVariable(VariableTree vt, Object arg1) {
        if (inMethod) {
            return super.visitVariable(vt, arg1);
        }

        String varName = vt.getName().toString();
        String varType = vt.getType().toString();
        String modifier = vt.getModifiers().toString();
        String annot = vt.getModifiers().getAnnotations().toString();

        JavaVariableDTO variableDTO = new JavaVariableDTO(varName, varType, modifier, annot);
        varList.add(variableDTO);

        if (arg1 != null) {
            System.out.println(arg1.toString());
        }

        return super.visitVariable(vt, arg1);
    }
    */

}