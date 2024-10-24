package dev.gabiqassis.hering.common.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathConstants {
    private static final String API = "/api";

    public static final String PALINDROME_V1 = API + "/v1/palindrome";
    public static final String NUMBER_REVERSER_V1 = API + "/v1/reverse-number";

    public static final String OBRAS_V1 = API + "/v1/obras";
    public static final String OBRAS_ID = OBRAS_V1 + "/{id}";
    public static final String OBRAS_AUTORES = OBRAS_ID + "/autores";

    public static final String AUTORES_V1 = API + "/v1/autores";
    public static final String AUTORES_ID = AUTORES_V1 + "/{id}";
    public static final String AUTORES_OBRAS = AUTORES_ID + "/obras";

    public static final String PAISES_V1 = API + "/v1/paises";
    public static final String PAISES_BUSCAR = PAISES_V1 + "/buscar";
}
