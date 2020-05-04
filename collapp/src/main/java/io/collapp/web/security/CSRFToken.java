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
package io.collapp.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class CSRFToken {

    private static final String CSRF_TOKEN = CSRFToken.class.getName() + ".CSRF_TOKEN";

    private CSRFToken() {
    }

    public static String getToken(HttpServletRequest req) {
        return (String) req.getSession().getAttribute(CSRFToken.CSRF_TOKEN);
    }

    public static void setToken(HttpServletRequest req, String token) {
        setToken(req.getSession(), token);
    }

    public static void setToken(HttpSession session, String token) {
        session.setAttribute(CSRFToken.CSRF_TOKEN, token);
    }
}
