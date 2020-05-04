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

import io.collapp.model.Key;
import io.collapp.service.ConfigurationRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expose some configuration variables that must be visible to all users.
 */
@RestController
public class ConfigurationController {

	private final ConfigurationRepository configurationRepository;


	public ConfigurationController(ConfigurationRepository configurationRepository) {
		this.configurationRepository = configurationRepository;
	}

	@RequestMapping(value = "/api/configuration/max-upload-file-size", method = RequestMethod.GET)
	@ResponseBody
	public String getMaxUploadFileSize() {
		return configurationRepository.getValueOrNull(Key.MAX_UPLOAD_FILE_SIZE);
	}

}
