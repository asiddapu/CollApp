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

import io.collapp.config.PersistenceAndServiceConfig;
import io.collapp.service.ExportImportService;
import io.collapp.service.config.TestServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestServiceConfig.class, PersistenceAndServiceConfig.class })
@Transactional
public class ExportImportServiceTest {

	@Autowired
	private ExportImportService exportImportService;

	@Test
	public void testImportAndExport() throws IOException {
		Path tmp = Files.createTempFile(null, null);
		try (InputStream is = new ClassPathResource("io/collapp/export2.zip").getInputStream()) {
			Files.copy(is, tmp, StandardCopyOption.REPLACE_EXISTING);
			exportImportService.importData(false, tmp);

			// TODO additional checks

			exportImportService.exportData(new ByteArrayOutputStream());
		} finally {
			if (tmp != null) {
				Files.deleteIfExists(tmp);
			}
		}

	}

}
