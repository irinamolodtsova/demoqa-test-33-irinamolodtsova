package lesson18_api_ui.helpers;


import lesson18_api_ui.api.BookstoreAuth;
import lesson18_api_ui.models.Bookstore.response.LoginResponseModel;
import lesson18_api_ui.models.Session;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

public class LoginExtension implements BeforeEachCallback, ParameterResolver {

    private static final ThreadLocal<Session> session = new ThreadLocal<>();

    public static Session getSession() {
        return session.get();
    }

    public static void clearSession() {
        session.remove();
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Session newSession = new Session();
        LoginResponseModel authResponse = BookstoreAuth.getAuthorization();
        newSession.setUserId(authResponse.getUserId());
        newSession.setToken(authResponse.getToken());
        newSession.setExpires(authResponse.getExpires());
        session.set(newSession);

        BookstoreAuth.buildAuthorizationCookie(session.get());
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType().equals(Session.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return getSession();
    }
}
