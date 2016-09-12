/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tlr.encapsulation;

/**
 * Enumerado das animações default
 *
 * @author 0199831
 */
public enum AnimationEnum {

    UP(0), DOWN(1), LEFT(2), RIGHT(3), NOP(-1);

    /** Código da animação */
    private final short code;

    /**
     * Construtor padrão
     *
     * @param code Código da animação
     */
    AnimationEnum(int code) {
        this.code = (short) code;
    }

    /**
     * Retorna o código da animação
     *
     * @return short
     */
    public short getCode() {
        return code;
    }

    /**
     * Retorna o nome da animação
     *
     * @return String
     */
    public String getName() {
        return "";
    }
    
    /**
     * Retorna todas as entradas no enumerado
     * 
     * @return AnimationEnum[]
     */
    public static AnimationEnum[] getAll(){
        return AnimationEnum.values();
    }

}
