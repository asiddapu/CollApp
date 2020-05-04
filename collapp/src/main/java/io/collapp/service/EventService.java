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

import io.collapp.model.CardLabel;
import io.collapp.model.CardLabelValue;
import io.collapp.model.Event;
import io.collapp.model.LabelListValue;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
public class EventService {

	private final EventRepository eventRepository;
	private final CardLabelRepository labelRepository;

	public EventService(EventRepository eventRepository, CardLabelRepository labelRepository) {
		this.eventRepository = eventRepository;
		this.labelRepository = labelRepository;
	}

	@Transactional(readOnly = false)
	public Event insertLabelEvent(String labelName, int cardId, int userId, Event.EventType event,
			CardLabelValue.LabelValue value, CardLabel.LabelType labelType, Date time) {

		if (labelType == CardLabel.LabelType.LIST) {
			labelType = CardLabel.LabelType.STRING;
			LabelListValue llv = labelRepository.findListValueById(value.getValueList());
			value = new CardLabelValue.LabelValue(llv.getValue(), value.getValueTimestamp(), value.getValueInt(),
					value.getValueCard(), value.getValueUser(), null);
		}

		return eventRepository.insertLabelEvent(labelName, cardId, userId, event, value, labelType, time);
	}
}
