/*
 * Copyright 2011 Witoslaw Koczewsi <wi@koczewski.de>, Artjom Kochtchi
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package scrum.client.admin;

import ilarkesto.core.base.Str;
import ilarkesto.core.scope.Scope;
import scrum.client.common.TooltipBuilder;

public class ActivateSystemMessageAction extends GCreateUserAction {

	@Override
	public String getLabel() {
		return "Activate";
	}

	@Override
	protected void updateTooltip(TooltipBuilder tb) {
		tb.setText("Make the system message visible to all users.");
	}

	@Override
	public boolean isExecutable() {
		if (getCurrentUser().isAdmin() == false) return false;
		if (Scope.get().getComponent(SystemMessageManager.class).getSystemMessage().isActive()) return false;
		if (Str.isBlank(Scope.get().getComponent(SystemMessageManager.class).getSystemMessage().getText()))
			return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Scope.get().getComponent(SystemMessageManager.class).activateSystemMessage();
	}

}
