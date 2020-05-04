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

import java.util.*

class Role(name: String) {

    val name: String

    init {
        this.name = name.toUpperCase(Locale.ENGLISH)
    }

    override fun equals(obj: Any?): Boolean {
        return if (obj != null && obj is Role) name == obj.name else false
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

    companion object {

        val ADMIN_ROLE = Role("ADMIN")
        val DEFAULT_ROLE = Role("DEFAULT")
    }
}
