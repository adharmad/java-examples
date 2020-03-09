package com.example.javaexamples.jndi;

import java.security.Provider;
import java.security.Security;
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

public class FindADUser {
	public static void main(String[] args) throws Exception {

		String server = "ldap://ldaphost:389/";
		String rootCtx = "dc=ambarnath,dc=com";
		String admin = "administrator@ambarnath.com";
		String password = "dead_line";

		Provider prov = Security
				.getProvider("com.sun.net.ssl.internal.ssl.Provider");

		if (prov == null) {
			Class moProviderClass = Class
					.forName("com.sun.net.ssl.internal.ssl.Provider");
			Provider moProvider = (Provider) moProviderClass.newInstance();
			Security.addProvider(moProvider);
		}

		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, server + rootCtx);
		env.put(Context.AUTHORITATIVE, "true");
		env.put(Context.SECURITY_PRINCIPAL, admin);
		env.put(Context.SECURITY_CREDENTIALS, password);
		// env.put(Context.SECURITY_PROTOCOL, "ssl");
		DirContext ctx = new InitialDirContext(env);

		System.out.println("Initial context = " + ctx);

		SearchControls searchCtls = new SearchControls();

		NamingEnumeration nenum = ctx.search("",
				"(&(sAMAccountName=gabbar)(objectclass=user))", searchCtls);

		// loop through the results in each page
		while (nenum != null && nenum.hasMoreElements()) {
			SearchResult sr = (SearchResult) nenum.next();

			// print out the name
			//System.out.println("name: " + sr.getName());
			dumpSearchResult(sr);
		}

		ctx.close();
	}
	
	public static void dumpSearchResult(SearchResult sr) throws NamingException {
		Attributes attrs = sr.getAttributes();
		
		NamingEnumeration ne = attrs.getAll();
		
		while (ne != null && ne.hasMoreElements()) {
			Attribute a = (Attribute)ne.next();
			String id = a.getID();
			NamingEnumeration ne1 = a.getAll();
			
			System.out.print(id + " : " );
			
			while (ne1 != null && ne1.hasMoreElements()) {
				System.out.println("\t" + ne1.next());
			}
			
		}
		
		
	}
}
