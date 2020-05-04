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
package io.collapp.service;

import io.collapp.model.ConfigurationKeyValue;
import io.collapp.model.UserToCreate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SetupService {

	private final ConfigurationRepository configurationRepository;
	private final UserService userService;


	public SetupService(ConfigurationRepository configurationRepository, UserService userService) {
		this.configurationRepository = configurationRepository;
		this.userService = userService;
	}

	public void updateConfiguration(List<ConfigurationKeyValue> toUpdateOrCreate, UserToCreate user) {
		configurationRepository.updateOrCreate(toUpdateOrCreate);
		userService.createUser(user);
	}
}
