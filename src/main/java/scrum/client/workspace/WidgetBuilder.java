/*
 * Copyright 2011 Witoslaw Koczewsi <wi@koczewski.de>
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package scrum.client.workspace;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetBuilder {

	private List<String> styles = new ArrayList<String>();
	private Map<String, String> attrs = new LinkedHashMap<String, String>();

	public WidgetBuilder styles(String... styles) {
		for (String style : styles) {
			this.styles.add(style);
		}
		return this;
	}

	public WidgetBuilder attr(String name, String value) {
		attrs.put(name, value);
		return this;
	}

	public FlowPanel div() {
		return construct(new FlowPanel());
	}

	public DropdownMenu dropdownMenu() {
		DropdownMenu ret = new DropdownMenu();
		styles("dropdown-menu");
		attr("role", "menu");
		return construct(ret);
	}

	public Nav createNav() {
		return construct(new Nav());
	}

	public Button createButton() {
		Button button = new Button();
		button.removeStyleName("gwt-Button");
		return construct(button);
	}

	public Ul ul() {
		return construct(new Ul());
	}

	public Li li() {
		return construct(new Li());
	}

	public Widget a(String href, String text) {
		return construct(new A(href, text));
	}

	private <W extends Widget> W construct(W ret) {
		for (String style : styles) {
			ret.addStyleName(style);
		}
		Element element = ret.getElement();
		for (Map.Entry<String, String> attr : attrs.entrySet()) {
			element.setAttribute(attr.getKey(), attr.getValue());
		}
		return ret;
	}

	public static class DropdownMenu extends FlowPanel {

		public DropdownMenu() {
			super("div");
		}

		public void addItemA(String href, String text) {
			add(new WidgetBuilder().styles("dropdown-item").a(href, text));
		}

		public Li createWrapperLiNavLink(String text) {
			Li li = new WidgetBuilder().styles("nav-item pull-xs-none pull-md-left dropdown").li();
			li.add(
				new WidgetBuilder().styles("nav-link", "dropdown-toggle").attr("data-toggle", "dropdown").a("#", text));

			li.add(this);
			return li;
		}
	}

	public static class Ul extends FlowPanel {

		public Ul() {
			super("ul");
		}

		public void addNavItemA(String href, String text) {
			add(new WidgetBuilder().styles("nav-item pull-xs-none pull-md-left").li().addNavLink(href, text));
		}
	}

	public static class Li extends FlowPanel {

		public Li() {
			super("li");
		}

		public Li addNavLink(String href, String text) {
			add(new WidgetBuilder().styles("nav-link").a(href, text));
			return this;
		}
	}

	public static class Nav extends FlowPanel {

		public Nav() {
			super("nav");
		}
	}

	public static class A extends FlowPanel {

		public A(String href, String text) {
			super("a");
			getElement().setAttribute("href", href);
			getElement().setInnerText(text);
		}
	}

}