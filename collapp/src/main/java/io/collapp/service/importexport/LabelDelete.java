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

import io.collapp.model.*;
import io.collapp.model.Event.EventType;
import io.collapp.service.*;

import java.nio.file.Path;
import java.util.Date;

class LabelDelete extends AbstractProcessLabelEvent {

	LabelDelete(CardRepository cardRepository, UserRepository userRepository, CardDataService cardDataService,
			LabelService labelService, CardLabelRepository cardLabelRepository, BoardRepository boardRepository,
			EventRepository eventRepository) {
		super(cardRepository, userRepository, cardDataService, labelService, cardLabelRepository, boardRepository,
				eventRepository);
	}

	@Override
	void process(EventFull e, Event event, Date time, User user, ImportContext context, Path tempFile) {
		CardLabelValue clv = findCardLabelValueBy(e);
		if (clv != null) {
			labelService.removeLabelValue(clv, user, time);
		} else {
			insertLabelEvent(e, event, time, EventType.LABEL_DELETE);
		}
	}
}
