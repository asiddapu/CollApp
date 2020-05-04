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
package io.collapp.web.api.model

import io.collapp.model.Event
import io.collapp.model.EventsCount
import io.collapp.model.ProjectWithEventCounts
import io.collapp.model.User

class UserPublicProfile(user: User, val dailyActivity: List<EventsCount>,
                        val activeProjects: List<ProjectWithEventCounts>, val latestActivityByPage: List<Event>) {
    val user: User

    init {
        // we remove the email
        this.user = User(user.id, user.provider, user.username, null, user.displayName,
            user.enabled, user.emailNotification, user.memberSince, user.skipOwnNotifications,
            user.userMetadataRaw)
    }
}
