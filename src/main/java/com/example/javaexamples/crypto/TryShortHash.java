package com.example.javaexamples.crypto;

import java.util.UUID;

public class TryShortHash {
    public static void main(String[] args) {
        String s = "mylongstringtohash";

//        for (int i=0 ; i<10 ; i++) {
//            s = s+i;
//            UUID uuid = UUID.nameUUIDFromBytes(s.getBytes());
//            String shortHash = uuid.toString().substring(0, 7);
//            System.out.println(uuid.toString() + " " + shortHash);
//        }

        String packageName = "PQBD_COL_WIZGESCOB";
        String procedureName = "PBD_COL_INFO_PRODUCTOS";
        String recordTypeName =  "REG_LISTADO_PRODUCTOS";


        // PQBD_COL_WIZGESCOBREG_LISTADO_PRODUCTOS
        //Unique name :
        // SIG_<>[6charhash]
        // SIG_[19 chars from recordTypeName][abcdefg]
        // SIG_[2 chars from package]_[2 chars from proc]_[12 chars from recordTypeName][abcdefg]

        String fqName = packageName + procedureName + recordTypeName;
        UUID uuid = UUID.nameUUIDFromBytes(fqName.getBytes());
        String shortHash = uuid.toString().substring(0, 7).toUpperCase();
        String uniqueName = "ZION_" + packageName.substring(0, 2) + "_" + procedureName.substring(0, 2) + "_"
                + recordTypeName.substring(0, 11) + "_" + shortHash;
        String uniqueName1 = "ZION_" + recordTypeName.substring(0, 17) + "_" + shortHash;
        String uniqueName2 = packageName.substring(0, 3) + "_" + procedureName.substring(0, 3) + "_"
                + recordTypeName.substring(0, 14) + "_" + shortHash;

        System.out.println(uniqueName + " " + uniqueName.length());
        System.out.println(uniqueName1  + " " + uniqueName1.length());
        System.out.println(uniqueName2  + " " + uniqueName2.length());
    }
}
