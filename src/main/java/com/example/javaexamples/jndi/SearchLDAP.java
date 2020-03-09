package com.example.javaexamples.jndi;

import java.util.Hashtable;


import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class SearchLDAP {
	public static void main(String[] args) throws Exception {
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldaphost:5566");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, "cn=admin"); 
		env.put(Context.SECURITY_CREDENTIALS, "welcome1");
		
		DirContext ctx = new InitialDirContext(env);
		SearchControls ctls = new SearchControls();
		String returnedAtts[]={"uid", "cn"};
		ctls.setReturningAttributes(returnedAtts);

		String filter = "(uid=mm1)";
		
		NamingEnumeration<SearchResult> ne = ctx.search("l=users,dc=ambarnath,dc=com", filter, ctls);
		
		while (ne.hasMoreElements()) {
			//System.out.println(ne.next());
			SearchResult sr = ne.next();
			
			Attributes attrs = sr.getAttributes();
			Attribute cn = attrs.get("cn");
			String cnval = (String)cn.get(0);
			System.out.println("cn = " + cnval);
		
			Attribute uid = attrs.get("uid");
			String uidval = (String)uid.get(0);
			System.out.println("uid = " + uidval);
			
		
		}
		
		ctx.close();

	}
}
