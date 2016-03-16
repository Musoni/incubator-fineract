/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.portfolio.accounts.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.accounts.service.ShareAccountWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = "SHAREACCOUNT", action = "CREATE")
public class CreateShareAccountCommandHandler implements NewCommandSourceHandler{


    private final ShareAccountWritePlatformService shareAccountWritePlatformService ;
    
    @Autowired
    public CreateShareAccountCommandHandler(final ShareAccountWritePlatformService shareAccountWritePlatformService) {
        this.shareAccountWritePlatformService = shareAccountWritePlatformService ;
    }
    @Transactional
    @Override
    public CommandProcessingResult processCommand(JsonCommand jsonCommand) {
        return this.shareAccountWritePlatformService.createShareAccount(jsonCommand);
    }

}