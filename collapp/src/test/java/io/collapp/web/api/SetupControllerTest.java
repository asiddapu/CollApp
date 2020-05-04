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
package io.collapp.web.api;

import io.collapp.service.ExportImportService;
import io.collapp.service.Ldap;
import io.collapp.service.SetupService;
import io.collapp.web.api.SetupController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Map;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SetupControllerTest {

	@Mock
	private Ldap ldap;
	@Mock
	private Map<String, String> ldapParams;
	@Mock
	private ExportImportService exportImportService2;
	@Mock
	private SetupService setupService;

	private SetupController setupController;

	@Before
	public void prepare() {
		setupController = new SetupController(setupService, ldap, exportImportService2);
	}

	@Test
	public void checkLdap() {
		setupController.checkLdap(ldapParams);
		for (String s : Arrays.asList("serverUrl", "managerDn", "managerPassword", "userSearchBase",
				"userSearchFilter", "username", "password")) {
			verify(ldapParams).get(s);
		}
	}
}
