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

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Version {


    private static final long START_DATE = System.nanoTime();

    public static String version() {
        Properties buildProp = new Properties();
        try (InputStream is = new ClassPathResource("io/collapp/build.properties").getInputStream()) {
            buildProp.load(is);
            String build = buildProp.getProperty("build.version");
            return build.endsWith("SNAPSHOT") ? (build + "-" + START_DATE): build;
        } catch (IOException e) {
            return "dev";
        }
    }
}
