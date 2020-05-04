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
package io.collapp.service.importexport;

import com.google.gson.reflect.TypeToken;
import io.collapp.common.Read;
import io.collapp.model.*;
import io.collapp.service.CardDataService;
import io.collapp.service.CardRepository;
import io.collapp.service.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

class FileUpload extends AbstractProcessEvent {

	FileUpload(CardRepository cardRepository, UserRepository userRepository, CardDataService cardDataService) {
		super(cardRepository, userRepository, cardDataService);
	}

	@Override
	void process(EventFull e, Event event, Date time, User user, ImportContext context, Path tempFile) {
		try {
			Path p = Objects.requireNonNull(Read.readFile("files/" + e.getContent(), tempFile));
			CardDataUploadContentInfo fileData = Read.readObject("files/" + e.getContent() + ".json", tempFile,
					new TypeToken<CardDataUploadContentInfo>() {
					});
			ImmutablePair<Boolean, CardData> res = cardDataService.createFile(event.getValueString(), e.getContent(),
					fileData.getSize(), cardId(e), Files.newInputStream(p), fileData.getContentType(), user, time);
			if (res.getLeft()) {
				context.getFileId().put(event.getDataId(), res.getRight().getId());
			}
			Files.delete(p);
		} catch (IOException ioe) {
			throw new IllegalStateException("error while handling event FILE_UPLOAD for event: " + e, ioe);
		}
	}

}
