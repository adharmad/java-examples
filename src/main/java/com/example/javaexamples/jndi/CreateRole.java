package com.example.javaexamples.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class CreateRole {

	public static void main(String[] args) throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldaphost:5566");
		//env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin"); 
		env.put(Context.SECURITY_CREDENTIALS, "welcome1");
		
		DirContext ctx = new InitialDirContext(env);
		
		String prefix = "test1009";

		Attribute objClasses = new BasicAttribute("objectClass");
        objClasses.add("orclIDXGroup");		
        objClasses.add("groupOfUniqueNames");
        objClasses.add("top");
		
		Attributes attrs = new BasicAttributes();
		attrs.put(new BasicAttribute("cn", prefix));
		attrs.put(new BasicAttribute("businessCategory", "rambc"));
		attrs.put(objClasses);
		
		ctx.createSubcontext("cn=" + prefix + ",l=roles,dc=ambarnath,dc=com", attrs);
		ctx.close();
		
	}

}
