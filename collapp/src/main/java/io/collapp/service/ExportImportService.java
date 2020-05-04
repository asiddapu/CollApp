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

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Handle the export/import part of collapp.
 */
@Service
@Transactional(readOnly = true, timeout = 5000000)
public class ExportImportService {

    private final collappImporter importer;
    private final collappExporter exporter;
    private final AtomicBoolean isImporting = new AtomicBoolean(false);

    public ExportImportService(collappExporter exporter, collappImporter importer) {
        this.importer = importer;
        this.exporter = exporter;
    }

    public void exportData(OutputStream os) throws IOException {
        exporter.exportData(os);
    }

    @Transactional(readOnly = false)
    public void importData(boolean overrideConfiguration, Path tempFile) {
        try {
            if(!isImporting.compareAndSet(false, true)) {
                throw new IllegalStateException("Cannot do more than one import at a time");
            }
            importer.importData(overrideConfiguration, tempFile);
        } finally {
            isImporting.set(false);
        }
    }

    public boolean isImporting() {
        return isImporting.get();
    }
}
