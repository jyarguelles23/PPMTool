package kdc.developers.ppmtool.Security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/api/users/**";
    public static final String H2_URL="h2-console/**";
    public static final String SECRET ="SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX="Beare ";//importante dejar un espacio
    public static final String HEADER_STRING ="Authorization";
    public static final long EXPIRATION_TIME=30000;
}
