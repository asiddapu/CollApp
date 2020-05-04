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

package io.collapp.model.apihook;

import io.collapp.model.BoardColumn;
import io.collapp.model.CardDataHistory;
import io.collapp.model.CardFull;
import io.collapp.model.CardType;
import org.apache.commons.lang3.StringUtils;

public class From {

    public static Card from(CardFull cf, String baseUrl) {
        String url = new StringBuilder(StringUtils.appendIfMissing(baseUrl, "/"))
            .append(cf.getProjectShortName())
            .append('/')
            .append(cf.getBoardShortName())
            .append('-')
            .append(cf.getSequence()).toString();
        return new Card(cf.getBoardShortName(), cf.getSequence(), cf.getName(), cf.getProjectShortName(), url);
    }


    public static User from(io.collapp.model.User user) {
        return new User(user.getProvider(), user.getUsername(), user.getEmail(), user.getDisplayName());
    }

    public static Column from(BoardColumn column) {
        return new Column(column.getName(), column.getLocation().toString(), column.getStatus().toString(), column.getColor());
    }

    public static CardData from(io.collapp.model.CardData data) {
        return new CardData(data.getType().toString(), StringUtils.trimToEmpty(data.getContent()));
    }

    public static CardData from(CardType type, CardDataHistory data) {
        return new CardData(type.toString(), StringUtils.trimToEmpty(data.getContent()));
    }
}
