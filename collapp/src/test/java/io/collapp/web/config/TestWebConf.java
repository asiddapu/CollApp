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
package io.collapp.web.config;

import io.collapp.service.UserRepository;
import io.collapp.web.helper.UserSession;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestWebConf {

	public static final MockHttpSession UNAUTH_SESSION;
	public static final MockHttpSession SESSION;

	static {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		UNAUTH_SESSION = new MockHttpSession();
		SESSION = new MockHttpSession();
		Mockito.when(req.getSession()).thenReturn(UNAUTH_SESSION);
		Mockito.when(req.getSession(true)).thenReturn(SESSION);
		UserSession.setUser(0, false, req, resp, Mockito.mock(UserRepository.class));
	}

}
