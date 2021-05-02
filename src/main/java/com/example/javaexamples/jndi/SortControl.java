package com.example.javaexamples.jndi;

//import java.util.Enumeration;
//import java.util.Iterator;
//import com.sun.jndi.ldap.*;
//import java.io.UnsupportedEncodingException;

public class SortControl {

    public static void main( String[] args ) {     

        /*
        if (args.length != 4) {
		System.err.println("Coming Here");
            System.err.println("Usage:   java SortControl <host name> " 
                                       + "<login dn> <password> <container>");
            System.err.println("Example: java SortControl Acme.com \"cn=admin," 
                               +"o=Acme\" secret \"ou=Sales,o=Acme\"");
            System.exit(1);
        }
           
        // Read command line arguments  
        String ldapHost    = args[0];       
        String loginDN     = args[1];
        String password    = args[2];
        String searchBase  = args[3];
        int    ldapPort    = LDAPConnection.DEFAULT_PORT;
        int    ldapVersion = LDAPConnection.LDAP_V3;
        String searchFilter= "(objectclass=*)";
        LDAPConnection lc  = new LDAPConnection();

        try {                     
            // connect to the server
            lc.connect( ldapHost, ldapPort );
            // bind to the server
            lc.bind( ldapVersion, loginDN, password.getBytes("UTF8") );
            
            //  Results of the search only include sn
            String[] attrs = new String[1];            
            attrs[0] = "changes";

            // The results should be sorted using the cn attribute
            LDAPSortKey[] keys = new LDAPSortKey[1];
            keys[0] = new LDAPSortKey( "changes" );
          
            // Create a LDAPSortControl object - Fail if cannot sort
            LDAPSortControl sort = new LDAPSortControl( keys, true );
            
            // Set the Sort control to be sent as part of search request
            LDAPSearchConstraints cons = lc.getSearchConstraints();
            cons.setControls( sort );
            lc.setConstraints(cons);

            // Perform the search - SYNCHRONOUS SEARCH USED HERE
            System.out.println( "\nCalling search request...");
            LDAPSearchResults res = lc.search( 
                                            searchBase,
                                            LDAPConnection.SCOPE_SUB,
                                            searchFilter,
                                            attrs, // only return sn
                                            false, // and it's value
                                            (LDAPSearchConstraints) null );
  
            // Loop on results until finished
            while ( res.hasMore() ) {

                // Get next directory entry - this next object can 
                // be an LDAPException if something went wrong
                LDAPEntry nextEntry;
                try {                
                    nextEntry = res.next();
                }
                catch(LDAPException e) {             
                    if ( e instanceof LDAPReferralException) 
                        continue;
                    else
                        break;
                }
                                                                
                // Print out the entry DN
                System.out.println();
                System.out.println( "Entry dn: " + nextEntry.getDN() );

                // Get the attributes of the entry
                LDAPAttributeSet findAttrs = nextEntry.getAttributeSet();
                  
                Iterator enumAttrs = findAttrs.iterator();
                System.out.println( "Attribute(s):" );

                // Loop on attributes
                while ( enumAttrs.hasNext() ) {
                    LDAPAttribute anAttr = (
                                        LDAPAttribute)enumAttrs.next();
                    
                    // get attribute name
                    String attrName = anAttr.getName();                   
                    
                    // get attribute value
                    Enumeration enumVals = anAttr.getStringValues();
                    while ( enumVals.hasMoreElements() ) {
                      String aVal = ( String )enumVals.nextElement();
                      // print out attribute name and value 
                      System.out.println( "    " + attrName + ": " + aVal );
                    }
                }
            }

            // Server should send back a control irrespective 
            // of the status of the search request
            LDAPControl[] controls = res.getResponseControls();
            if ( controls != null ) {
                
                // Theoritically we could have multiple controls returned
                for( int i = 0; i < controls.length; i++ ) {
                    
                    // We are looking for the LDAPSortResponse Control class - 
                    // the control sent back in response to LDAPSortControl
                    if ( controls[i] instanceof LDAPSortResponse ) {
                        
                        System.out.println("Received LDAP Sort Control from " 
                                                                  + "server");
                        
                        // We must have an error code and maybe a string 
                        // identifying erring attribute in the response control.
                        String bad = 
                           ((LDAPSortResponse)controls[i]).getFailedAttribute();
                        int result = 
                                ((LDAPSortResponse)controls[i]).getResultCode();
                      
                        // Print out error ccode (0 if no error) and any 
                        // returned attribute
                        System.out.println( "Error code: " + result );
                        if ( bad != null )
                            System.out.println( "Offending " + "attribute: " 
                                                                      + bad );
                        else
                            System.out.println( "No offending " + "attribute " 
                                                                + "returned" );
                    }
                }
            }
           
        // All done - disconnect
        if ( lc.isConnected() )
            lc.disconnect();

        } catch( LDAPException e ) {
            System.out.println( e.toString() );
        }
        catch( UnsupportedEncodingException e ) {
            System.out.println( "Error: " + e.toString() );
        }
        */
    }
}

