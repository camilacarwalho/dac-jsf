package br.edu.ifpb.infra.util;

import java.util.UUID;

public class GeradorDeUUID {

    public static String gerarUUID() {
        return UUID.randomUUID().toString();
    }

}
