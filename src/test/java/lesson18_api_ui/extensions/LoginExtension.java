package lesson18_api_ui.extensions;


import lesson18_api_ui.api.AuthApiSteps;
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

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Session newSession = new Session();
        LoginResponseModel authResponse = AuthApiSteps.getAuthorization();
        newSession.setUserId(authResponse.getUserId());
        newSession.setToken(authResponse.getToken());
        newSession.setExpires(authResponse.getExpires());
        session.set(newSession);

        AuthApiSteps.buildAuthorizationCookie(session.get());
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
