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

import io.collapp.common.collappEnvironment;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;

import javax.sql.DataSource;

public class DatabaseMigrator {

	public DatabaseMigrator(collappEnvironment env, DataSource dataSource, MigrationVersion target) {
		if (canMigrate(env)) {
			doMigration(env, dataSource, target);
		}
	}

	private void doMigration(collappEnvironment env, DataSource dataSource, MigrationVersion version) {
		String sqlDialect = env.getRequiredProperty("datasource.dialect");
		Flyway migration = new Flyway();
		migration.setDataSource(dataSource);
		// FIXME remove the validation = false when the schemas will be stable
		migration.setValidateOnMigrate(false);
		//

		migration.setTarget(version);

		migration.setLocations("io/collapp/db/" + sqlDialect + "/");
		migration.migrate();
	}

	private boolean canMigrate(collappEnvironment env) {
		return !"true".equals(env.getProperty("datasource.disable.migration"));
	}
}
