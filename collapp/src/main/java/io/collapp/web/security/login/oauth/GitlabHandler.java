
package io.collapp.web.security.login.oauth;

import io.collapp.web.security.SecurityConfiguration.SessionHandler;
import io.collapp.web.security.SecurityConfiguration.Users;
import io.collapp.web.security.login.oauth.OAuthResultHandler.OAuthResultHandlerAdapter;
import org.apache.commons.lang3.StringUtils;
import org.scribe.builder.ServiceBuilder;

public class GitlabHandler extends OAuthResultHandlerAdapter {

    private GitlabHandler(OAuthServiceBuilder serviceBuilder, OAuthRequestBuilder reqBuilder, OAuthProvider provider, String callback, Users users, SessionHandler sessionHandler, String errorPage) {

        super("oauth." + provider.getProvider(),//
                getProfileUrl(provider),//
                UserInfo.class,//
                "code",//
                users,//
                sessionHandler,//
                errorPage,//
                serviceBuilder
                    .build(new Gitlab20Api(provider.baseUrlOrDefault("https://gitlab.com")),
                        provider.getApiKey(),
                        provider.getApiSecret(),
                        callback),
                reqBuilder);
    }

    //
    //
    // https://gitlab.com/api/v3/user
    private static String getProfileUrl(OAuthProvider provider) {
        return provider.profileUrlOrDefault(StringUtils.removeEnd(provider.baseUrlOrDefault("https://gitlab.com"), "/") + "/api/v3/user");
    }

    public static class UserInfo implements RemoteUserProfile {

        // http://doc.gitlab.com/ce/api/users.html#current-user
        String username;

        @Override
        public boolean valid(Users users, String provider) {
            return users.userExistsAndEnabled(provider, username);
        }

        @Override
        public String username() {
            return username;
        }
    }

    public static final OAuthResultHandlerFactory FACTORY = new OAuthResultHandlerFactory.Adapter() {

        @Override
        public OAuthResultHandler build(OAuthServiceBuilder serviceBuilder,
                OAuthRequestBuilder reqBuilder, OAuthProvider provider,
                String callback, Users users, SessionHandler sessionHandler,
                String errorPage) {
            return new GitlabHandler(serviceBuilder, reqBuilder, provider, callback, users, sessionHandler, errorPage);
        }

        @Override
        public boolean hasConfigurableBaseUrl() {
            return true;
        }
    };
}
