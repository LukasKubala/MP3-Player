package com.mp3.analukpat.mp3_player;

class Lied {

    private long id;
    private String titel;
    private String interpret;

    public Lied(long liedId, String liedTitel, String liedInterpret){
        id=liedId;
        titel=liedTitel;
        interpret=liedInterpret;
    }

    public long getId() {
        return id;
    }

    public String getInterpret() {
        return interpret;
    }

    public String getTitel() {
        return titel;
    }
}
