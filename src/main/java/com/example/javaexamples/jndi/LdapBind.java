package com.example.javaexamples.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LdapBind {
	public static void main(String[] args) throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldaphost:3061");
		//env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=testuser,cn=Users,dc=ambarnath,dc=com"); 
		env.put(Context.SECURITY_CREDENTIALS, "password123");
		
		DirContext ctx = new InitialDirContext(env);
		
		System.out.println("bound!!");
		
		ctx.close();
		
		System.out.println("un  bound!!");
		
		
	}
}
