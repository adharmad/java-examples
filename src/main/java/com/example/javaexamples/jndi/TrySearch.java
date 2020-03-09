package com.example.javaexamples.jndi;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class TrySearch {
    Hashtable env;

    public static void main(String[] args) {
        TrySearch srch = new TrySearch("com.sun.jndi.ldap.LdapCtxFactory",
                "ldap://host:389/ou=dev,dc=devel", "accounts", "accounts");

        srch.search("ldap://host:389/ou=dev,dc=devel", "ou=TFS Americas");
    }

    public TrySearch(String initialContextFactory, String providerUrl,
            String userDN, String password) {
        env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
        //env.put(Context.PROVIDER_URL, providerUrl);
        env.put("java.naming.authoritative", "true");
        env.put("java.naming.security.principal", userDN);
        env.put("java.naming.security.credentials", password);
        //		env.put("java.naming.security.protocol","ssl");
    }

    public void search(String url, String rolebase) {
        try {
            
            String completePath = url;// + "," + rolebase;
            env.put(Context.PROVIDER_URL, url);
            
            DirContext ctx = new InitialDirContext(env);
            System.out.println(ctx.getNameInNamespace());
            SearchControls ctls = new SearchControls();
            ctls.setReturningObjFlag(true);
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String query = "objectClass=group";
            
            NamingEnumeration list = ctx.search(rolebase, query, ctls);

            while (list.hasMore()) {
                SearchResult searchresult = (SearchResult) list.next();
                Attributes attrs = searchresult.getAttributes();
                System.out.println(attrs);
            }
        } catch (NamingException ne) {
            System.out.println("NAmig Excpetion in LDAPRecon");
            ne.printStackTrace();
        }

    }

}
