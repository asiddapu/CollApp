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
package io.collapp.web.api;

import io.collapp.model.User;
import io.collapp.service.ExportImportService;
import io.collapp.service.ImportService;
import io.collapp.web.api.ExportImportController;
import io.collapp.web.api.model.TrelloImportRequest;
import io.collapp.web.api.model.TrelloImportRequest.BoardIdAndShortName;
import io.collapp.web.api.model.TrelloRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ExportImportControllerTest {

	@Mock
	private ImportService importService;

	@Mock
	private ExportImportService exportImportService2;

	@Mock
	private User user;

	private ExportImportController importController;

	@Before
	public void prepare() {
		importController = new ExportImportController(exportImportService2, importService);
	}

	@Test
	public void getAvailableTrelloBoardsTest() {
		TrelloRequest request = new TrelloRequest();
		request.setApiKey("KEY");
		request.setSecret("SECRET");
		importController.getAvailableTrelloBoards(request);
		verify(importService).getAvailableTrelloBoards(request);
	}

	@Test
	public void importFromTrelloTest() {
		TrelloImportRequest importRequest = new TrelloImportRequest();
		importRequest.setApiKey("KEY");
		importRequest.setSecret("SECRET");
		importRequest.setImportId("ID");
		BoardIdAndShortName boardIdAndShortName = new BoardIdAndShortName();
		boardIdAndShortName.setId("BOARD_ID");
		boardIdAndShortName.setShortName("SHORT_NAME");
		importRequest.setBoards(Arrays.asList(boardIdAndShortName));
		importController.importFromTrello(importRequest, user);
		verify(importService).importFromTrello(importRequest, user);
	}

	@Test
	public void saveTrelloBoardsToDbTest() {
		TrelloImportRequest importRequest = new TrelloImportRequest();
		importRequest.setProjectShortName("Dummy");
		importRequest.setApiKey("KEY");
		importRequest.setSecret("SECRET");
		importController.importFromTrello(importRequest, user);
		verify(importService).saveTrelloBoardsToDb(eq(importRequest.getProjectShortName()),
				isNull(ImportService.TrelloImportResponse.class), eq(user));
	}
}
