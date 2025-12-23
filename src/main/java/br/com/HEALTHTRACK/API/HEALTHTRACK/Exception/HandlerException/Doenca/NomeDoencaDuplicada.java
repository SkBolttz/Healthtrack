package br.com.HEALTHTRACK.API.HEALTHTRACK.Exception.HandlerException.Doenca;

public class NomeDoencaDuplicada extends RuntimeException {
    public NomeDoencaDuplicada(String message) {
        super(message);
    }
}
