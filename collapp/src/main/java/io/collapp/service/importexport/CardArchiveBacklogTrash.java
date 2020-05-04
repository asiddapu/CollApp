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

import io.collapp.model.Event;
import io.collapp.model.EventFull;
import io.collapp.model.ImportContext;
import io.collapp.model.User;
import io.collapp.service.*;

import java.nio.file.Path;
import java.util.Date;

import static java.util.Collections.singletonList;

class CardArchiveBacklogTrash extends AbstractProcessEvent {

	private final CardService cardService;
	private final EventRepository eventRepository;

	CardArchiveBacklogTrash(CardRepository cardRepository, UserRepository userRepository,
			CardDataService cardDataService, CardService cardService, EventRepository eventRepository) {
		super(cardRepository, userRepository, cardDataService);
		this.cardService = cardService;
		this.eventRepository = eventRepository;
	}

	@Override
	void process(EventFull e, Event event, Date time, User user, ImportContext context, Path tempFile) {
		int columnId = context.getColumns().get(e.getEvent().getColumnId());

		if (event.getPreviousColumnId() == null) {
			eventRepository.insertCardEvent(singletonList(cardId(e)), columnId, user.getId(), event.getEvent(), time);
		} else {
			int previousColumnId = context.getColumns().get(event.getPreviousColumnId());
			cardService.moveCardsToColumn(singletonList(cardId(e)), previousColumnId, columnId, user.getId(),
					event.getEvent(), time);
		}

	}
}
