/**
 * This file is part of collapp.

 * collapp is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * collapp is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with collapp.  If not, see //www.gnu.org/licenses/>.
 */
package io.collapp.model

import ch.digitalfondue.npjt.ConstructorAnnotationRowMapper.Column
import org.apache.commons.lang3.time.DateUtils
import java.util.*

class EventsCount(@Column("EVENT_DATE") date: Date, @Column("EVENT_COUNT") val count: Long) {

    val date: Long

    init {
        this.date = DateUtils.truncate(date, Calendar.DATE).time
    }
}
