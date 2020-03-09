package com.example.javaexamples.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class UpdateUser {
	public static void main(String[] args) throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldaphost:3061");
		//env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin"); 
		env.put(Context.SECURITY_CREDENTIALS, "welcome1");

		
		DirContext ctx = new InitialDirContext(env);
		/*
		SearchControls ctls = new SearchControls();
		String returnedAtts[]={"uid", "cn"};
		ctls.setReturningAttributes(returnedAtts);

		String filter = "(uid=TEST)";
		
		NamingEnumeration<SearchResult> ne = ctx.search("l=users,dc=oracle,dc=com", filter, ctls);
		SearchResult sr = ne.nextElement();
		*/
		
		ModificationItem[] mods = new ModificationItem[1];
		mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
			    new BasicAttribute("userPassword", "password123"));
		
		ctx.modifyAttributes("cn=testuser,cn=Users,dc=ambarnath,dc=com" , mods);
		
		ctx.close();
	}
}
