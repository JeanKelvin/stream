package br.com.stream.exception;

/**
 * Created by Jean on 11/02/2017.
 * Exception ConsoanteException extends de RuntimeException para não ser uma checked exception
 * Metodo construtor para lançar uma mensagem ao usuário Consoante não encontrada no parâmetro
 */
public class ConsoanteException extends RuntimeException {
    public ConsoanteException(){
        super("Consoante não encontrada no parâmetro");
    }
}
