/**
 * This file is part of collapp.
 *
 * collapp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * collapp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with collapp.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.collapp.web.security.login.oauth;

import io.collapp.web.security.SecurityConfiguration.SessionHandler;
import io.collapp.web.security.SecurityConfiguration.Users;
import io.collapp.web.security.login.oauth.OAuthResultHandler.OAuthRequestBuilder;

public interface OAuthResultHandlerFactory {

    OAuthResultHandler build(OAuthServiceBuilder serviceBuilder,
            OAuthRequestBuilder reqBuilder,
            OAuthProvider oauthProvider,
            String callback,
            Users users,
            SessionHandler sessionHandler,
            String errorPage);

    /**
     * Can be used as a configurable base
     * */
    boolean hasConfigurableBaseUrl();

    /**
     * _IS_ a configured instance
     * */
    boolean isConfigurableInstance();

    public abstract class Adapter implements OAuthResultHandlerFactory {
        @Override
        public boolean hasConfigurableBaseUrl() {
            return false;
        }

        @Override
        public boolean isConfigurableInstance() {
            return false;
        }
    }
}
