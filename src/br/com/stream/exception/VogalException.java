package br.com.stream.exception;

/**
 * Created by Jean on 11/02/2017.
 * Exception VogalException extends de RuntimeException para não ser uma checked exception
 * Metodo construtor para lançar uma mensagem ao usuário Vogal não encontrada no parâmetro
 */

public class VogalException extends RuntimeException {
    public VogalException(){
        super("Vogal não encontrada no parâmetro.");
    }
}
