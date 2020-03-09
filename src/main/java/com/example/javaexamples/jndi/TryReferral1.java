package com.example.javaexamples.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.ReferralException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class TryReferral1 {
    Hashtable env;

    public static void main(String[] args) {
        TryReferral1 xxx = new TryReferral1(
                "com.sun.jndi.ldap.LdapCtxFactory",
                "ldap://host:389/dc=exch552004,dc=com",
                "cn=Administrator,cn=Users,dc=exch552004,dc=com", "password");

        xxx.getobject("");
    }

    public TryReferral1(String initialContextFactory, String providerUrl,
            String userDN, String password) {

        env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
        env.put(Context.PROVIDER_URL, providerUrl);
        env.put("java.naming.authoritative", "true");
        env.put("java.naming.security.principal", userDN);
        env.put("java.naming.security.credentials", password);
        //env.put(Context.REFERRAL, "follow");
        //env.put(Context.REFERRAL, "throw");
        env.put(Context.REFERRAL, "ignore");
    }

    public void getobject(String rolebase) {
        try {
            DirContext ctx = new InitialDirContext(env);
            //System.out.println(ctx.getNameInNamespace());
            SearchControls ctls = new SearchControls();
            //ctls.setReturningObjFlag(true);
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String o = "2fe4a8a080ed684197f8a0e5a4a6b235";

            //String query = "(objectGUID=" + toByteArray(o) + ")";
            //System.out.println(query);
            
            String query = "(objectGUID=";

            for (int i = 0; i < o.length(); i += 2) {
                query += "\\\\" + o.substring(i, i + 2);
            }
            query += ")";
            
            System.out.println(query);
            
            NamingEnumeration list = ctx.search("", query, ctls);
            
            while (list.hasMoreElements()) {
                SearchResult searchresult = (SearchResult) list.nextElement();
                Attributes attrs = searchresult.getAttributes();
                System.out.println(attrs);

            }
        } catch (ReferralException re) {
            System.out.println("Caught Referal Exception");
        } catch (NamingException ne) {
            System.out.println("NAmig Excpetion in LDAPRecon");
            ne.printStackTrace();
        }

    }
    

}
