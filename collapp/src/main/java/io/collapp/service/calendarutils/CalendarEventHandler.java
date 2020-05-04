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

package io.collapp.service.calendarutils;

import io.collapp.model.CardFullWithCounts;
import io.collapp.model.LabelAndValue;
import io.collapp.model.LabelListValueWithMetadata;
import io.collapp.model.SearchResults;

import java.net.URISyntaxException;
import java.util.Date;

public interface CalendarEventHandler {


    void addCardEvent(CardFullWithCounts card, LabelAndValue lav) throws URISyntaxException;

    void addMilestoneEvent(String shortName, Date date, LabelListValueWithMetadata m, SearchResults cards)
        throws URISyntaxException;
}
