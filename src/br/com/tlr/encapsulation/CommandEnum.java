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
public enum CommandEnum {

    RUN(0), ATTACK(1);

    /** Código da animação */
    private final short code;

    /**
     * Construtor padrão
     *
     * @param code Código da animação
     */
    CommandEnum(int code) {
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
        return CommandEnum.class.getEnumConstants()[code].toString();
    }

    /**
     * Retorna todas as entradas no enumerado
     *
     * @return AnimationEnum[]
     */
    public static CommandEnum[] getAll() {
        return CommandEnum.values();
    }

}
