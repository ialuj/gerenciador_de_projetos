package br.com.beltis.utils;

public class MensagemException extends RuntimeException {

    public MensagemException(String mensagem) {
        super(mensagem);
    }

    public MensagemException(String message, Throwable cause) {
        super(message, cause);
    }
}
