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

package io.collapp.common;

public class CookieNames {

    private static String SESSION_COOKIE_NAME = "collapp_SESSION_ID";
    private static String REMEMBER_ME_COOKIE = "collapp_REMEMBER_ME";
    
    public static final String PROPERTY_NAME = "cookiePrefix";

    public static void updatePrefix(String prefix) {
        SESSION_COOKIE_NAME = prefix + "-collapp_SESSION_ID";
        REMEMBER_ME_COOKIE = prefix + "-collapp_REMEMBER_ME";
    }
    
    public static String getSessionCookieName() {
        return SESSION_COOKIE_NAME;
    }
    
    public static String getRememberMeCookieName() {
        return REMEMBER_ME_COOKIE;
    }
}
