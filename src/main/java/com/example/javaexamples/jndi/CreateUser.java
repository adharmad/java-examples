package com.example.javaexamples.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class CreateUser {
	public static void main(String[] args) throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldaphost:5566");
		//env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin"); 
		env.put(Context.SECURITY_CREDENTIALS, "welcome1");
		
		DirContext ctx = new InitialDirContext(env);
		
		String prefix = "RAVAN3";

		Attribute objClasses = new BasicAttribute("objectClass");
        objClasses.add("orclIDXPerson");		
		
		Attributes attrs = new BasicAttributes();
		attrs.put(new BasicAttribute("cn", prefix));
		//attrs.put(new BasicAttribute("uid", prefix));
		attrs.put(new BasicAttribute("sn", prefix + "sn"));
		//attrs.put(new BasicAttribute("givenName", prefix + "givenName"));
		attrs.put(new BasicAttribute("userPassword", "password12"));
		attrs.put(objClasses);
		
		ctx.createSubcontext("uid=" + prefix + ",l=users,dc=ambarnath,dc=com", attrs);
		ctx.close();
		
	}
}
