package com.example.javaexamples.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.sun.jndi.ldap.ctl.SortControl;
import com.sun.jndi.ldap.ctl.VirtualListViewControl;
import com.sun.jndi.ldap.ctl.VirtualListViewResponseControl;

public class TryVirtualListViewControl {
	public static void main(String[] args) throws Exception {

		// Set up environment for creating initial context
		Hashtable env = new Hashtable(11);
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ldaphost:389/dc=ambarnath,dc=com");

		// Required for using VLV control against DS 4.1
		env.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
		env.put(Context.SECURITY_CREDENTIALS, "password1");

		int tot = 0;
		int start = 1;
		int large = 0;
		int ps = 9;
		boolean notdone = false;

		// Create initial context with no connection request controls
		LdapContext ctx = new InitialLdapContext(env, null);

		Control[] ctxCtls = new Control[] { 
				new VirtualListViewControl(start, large, 0, ps, Control.CRITICAL),
				new SortControl(new String[]{"cn"}, Control.CRITICAL)
		};

		// Set context's request controls to be ctxCtls
		ctx.setRequestControls(ctxCtls);

		do {
			tot = 0;
			// Perform search, will still sort by cn
			// because context request controls are still in effect
			NamingEnumeration answer = ctx.search("ou=People", "(objectclass=*)", null);

			// Enumerate answers
			while (answer.hasMore()) {
				System.out.println(((SearchResult) answer.next()).getName());
				tot++;
			}
			
			System.out.println("---------------------");
			start += tot;
			
			notdone = parseControls(ctx.getResponseControls());

			ctx.setRequestControls(new Control[] { 
					new VirtualListViewControl(start, large, 0, ps, Control.CRITICAL), 
					new SortControl(new String[]{"cn"}, Control.CRITICAL) 
					});
		} while (notdone);
	}

	public static boolean parseControls(Control[] controls){

		if (controls != null) {

			for (int i = 0; i < controls.length; i++) {
				if (controls[i] instanceof VirtualListViewResponseControl) {
					VirtualListViewResponseControl c = (VirtualListViewResponseControl) controls[i];
					//System.out.println(c.getTargetOffset());
					//System.out.println(c.getListSize());
					if (c.getTargetOffset() == c.getListSize()) {
						return false;
					}
				}
			}
		}

		return true;
	}
}
