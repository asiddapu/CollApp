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

import io.collapp.model.BoardColumnDefinition
import io.collapp.model.ColumnDefinition

open class TaskStatistics(tasks: Map<ColumnDefinition, Int>,
                          columnDefinitions: Map<ColumnDefinition, BoardColumnDefinition>) {

    val openTaskColor: Int
    val closedTaskColor: Int
    val backlogTaskColor: Int
    val deferredTaskColor: Int

    val openTaskCount: Int?
    val closedTaskCount: Int?
    val backlogTaskCount: Int?
    val deferredTaskCount: Int?

    init {

        this.openTaskColor = columnDefinitions[ColumnDefinition.OPEN]!!.color
        this.closedTaskColor = columnDefinitions[ColumnDefinition.CLOSED]!!.color
        this.backlogTaskColor = columnDefinitions[ColumnDefinition.BACKLOG]!!.color
        this.deferredTaskColor = columnDefinitions[ColumnDefinition.DEFERRED]!!.color

        this.openTaskCount = tasks[ColumnDefinition.OPEN]
        this.closedTaskCount = tasks[ColumnDefinition.CLOSED]
        this.backlogTaskCount = tasks[ColumnDefinition.BACKLOG]
        this.deferredTaskCount = tasks[ColumnDefinition.DEFERRED]
    }
}
