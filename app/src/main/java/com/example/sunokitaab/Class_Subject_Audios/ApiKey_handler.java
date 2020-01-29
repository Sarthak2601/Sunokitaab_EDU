package com.example.sunokitaab.Class_Subject_Audios;



public class ApiKey_handler {

    private final String _0to4 = "01234";
    private final String _5to9 = "56789";

    String key_0to4 = "rxgdkvedigso5ufz2orgvm99jhmuimybnnfiizf8";
    String key_5to9 = "gzs9evtqu0pn7szwrprfwmbkpqowsemztskfwfde";
    String key_a2z = "hl1uzrzq9uleikssxpjqr5mu6pcpegphuoj3jn3t";

    public void ApiKey_handler(){
    }

    public String route(String L){
        if (_0to4.contains(L)){
            return key_a2z;
        }
        else if (_5to9.contains(L)){
            return key_5to9;
        }
        return key_0to4;    // characters
    }

}
