--
-- This file is part of collapp.
--
-- collapp is free software: you can redistribute it and/or modify
-- it under the terms of the GNU General Public License as published by
-- the Free Software Foundation, either version 3 of the License, or
-- (at your option) any later version.
--
-- collapp is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU General Public License for more details.
--
-- You should have received a copy of the GNU General Public License
-- along with collapp.  If not, see <http://www.gnu.org/licenses/>.
--

ALTER TABLE LA_API_HOOK ADD API_HOOK_ENABLED BOOLEAN NOT NULL;
ALTER TABLE LA_API_HOOK ADD API_HOOK_TYPE VARCHAR(32) NOT NULL;
ALTER TABLE LA_API_HOOK ADD API_HOOK_PROJECTS TEXT;
ALTER TABLE LA_API_HOOK ADD API_HOOK_VERSION INTEGER NOT NULL;