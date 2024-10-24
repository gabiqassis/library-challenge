package dev.gabiqassis.hering.common.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathConstants {
    private static final String API = "/api";

    public static final String PALINDROME_V1 = API + "/v1/palindrome";
    public static final String NUMBER_REVERSER_V1 = API + "/v1/reverse-number";

    public static final String LITERARY_WORK_V1 = API + "/v1/literarywork";
    public static final String LITERARY_WORK_ID = LITERARY_WORK_V1 + "/{id}";
    public static final String LITERARY_WORK_AUTHORS = LITERARY_WORK_ID + "/authors";

    public static final String AUTHORS_V1 = API + "/v1/authors";
    public static final String AUTHORS_ID = AUTHORS_V1 + "/{id}";
    public static final String AUTHORS_LITERARY_WORK = AUTHORS_ID + "/literarywork";

    public static final String COUNTRY_V1 = API + "/v1/country";
    public static final String SEARCH_COUTRY = COUNTRY_V1 + "/search";
}
