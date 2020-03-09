package com.example.javaexamples.xml;

/*
import com.sun.codemodel.internal.JCodeModel;
import com.sun.tools.internal.xjc.api.S2JJAXBModel;
import com.sun.tools.internal.xjc.api.SchemaCompiler;
import com.sun.tools.internal.xjc.api.XJC;
import org.xml.sax.InputSource;
*/

import java.io.File;
import java.io.InputStream;


public class XSD2Java {
    /*
    public static void main(String[] args) throws Exception {
        InputStream xsdInputStream = XSD2Java.class.getClassLoader().getResourceAsStream("shiporder.xsd");

        String outputDirectory = "/tmp";

        // Setup schema compiler
        SchemaCompiler sc = XJC.createSchemaCompiler();
        sc.forcePackageName("foo");

        // Setup SAX InputSource
        //File schemaFile = new File(schemaPath);
        InputSource is = new InputSource(xsdInputStream);
        is.setSystemId("Erp_NdPkg_Portal_Gndi_Autorizacao_NU46getinfbeneficiariolegadogndi.xsd");

        // Parse & build
        sc.parseSchema(is);
        S2JJAXBModel model = sc.bind();
        JCodeModel jCodeModel = model.generateCode(null, null);
        jCodeModel.build(new File(outputDirectory));
    }
    */
}
