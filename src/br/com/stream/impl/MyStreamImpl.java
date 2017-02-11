package br.com.stream.impl;

import br.com.stream.MyStream;
import br.com.stream.exception.ConsoanteException;
import br.com.stream.exception.VogalException;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Jean on 11/02/2017.
 * Classe responsável por regras de negócio do exercício
 */
public class MyStreamImpl implements MyStream {

    private int vogalIndex;
    private int index = 0;
    private String input;
    /**
     * Metacaractere ^ (inicia) e $ (finaliza)
     */
    private static final String VOGAL = "^A|E|I|O|U$";

    /**
     *
     * @param input String a ser passada pelo usuário
     *
     * Construtor MyStreamImpl verifica se a string passada como parâmetro é o primeiro caracter Vogal, após uma consoante,
     *              onde a mesma é antecessora a uma vogal e que não se repita no resto da stream.
     */
    public MyStreamImpl(String input){
        this.input = input;

        List<String> chars = Arrays.asList(this.input.split("")).stream()
                .map(String::toUpperCase).collect(Collectors.toList());

        String consoante = chars.stream().filter(s -> !s.matches(VOGAL))
                .findFirst().orElseThrow(ConsoanteException::new);

        int consoanteIndex = chars.indexOf(consoante);

        String vogal = chars.subList(consoanteIndex, chars.size()).stream()
                .filter(filtraVogalUnica(chars))
                .findFirst().orElseThrow(VogalException::new);

        int index = input.toUpperCase().indexOf(vogal);
        String mod = input.substring(index - 2, index);
        boolean teste = verificarAntecessores(mod);

        if(teste) {
            System.out.println(vogal.toLowerCase());

        }else{
            throw new VogalException();
        }
    }

    /**
     *
     * @param chars
     * @return uma Vogal que não se repete no parâmetro passado
     */
    private Predicate<? super String> filtraVogalUnica(List<String> chars) {
        return s -> s.matches(VOGAL) && chars.stream().filter(v -> v.equals(s)).count() == 1;
    }

    /**
     *
     * @param modificada
     * @return true caso a primeira letra seja vogal e a segunda letra seja consoante, se não retorna false
     */
    private boolean verificarAntecessores(String modificada){
        List<String> string = Arrays.asList(modificada.split("")).stream()
                .map(String::toUpperCase).collect(Collectors.toList());

        String vogalStr = Arrays.asList(string.get(0)).stream().filter(s -> s.matches(VOGAL)).findFirst().orElse("Consoante").toString();
        String consoanteStr = Arrays.asList(string.get(1)).stream().filter(s -> !s.matches(VOGAL)).findFirst().orElse("Vogal").toString();

        if(vogalStr.equals("Consoante")){
            return false;
        }else if(consoanteStr.equals("Vogal")){
            return false;
        }else{
            return true;
        }
    }

    /**
     *
     * @return próximo caracter do input
     */
    @Override
    public char getNext() {
        return this.input.charAt(this.index++);
    }

    /**
     *
     * @return true caso existe um proximo caracter
     */
    @Override
    public boolean hasNext() {
        return this.index <= this.input.length() && this.vogalIndex == -1 || this.index <= this.vogalIndex;
    }
}
