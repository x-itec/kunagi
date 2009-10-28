package scrum.client.test;

import ilarkesto.gwt.client.AAction;
import ilarkesto.gwt.client.AIntegerViewEditWidget;
import ilarkesto.gwt.client.ButtonWidget;
import ilarkesto.gwt.client.FloatingFlowPanel;
import ilarkesto.gwt.client.Gwt;
import ilarkesto.gwt.client.ImageAnchor;
import ilarkesto.gwt.client.MultiSelectionWidget;
import ilarkesto.gwt.client.NavigatorWidget;
import ilarkesto.gwt.client.ToolbarWidget;
import ilarkesto.gwt.client.editor.ATextEditorModel;
import ilarkesto.gwt.client.editor.RichtextEditorWidget;
import ilarkesto.gwt.client.editor.TextEditorWidget;
import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.AScrumWidget;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockListWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.common.FieldsWidget;
import scrum.client.img.Img;
import scrum.client.workspace.PagePanel;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class WidgetsTesterWidget extends AScrumWidget {

	private PagePanel page;

	@Override
	protected Widget onInitialization() {
		page = new PagePanel();
		page.setStyleName("WidgetsTesterWidget");

		testActions();

		testFloatingFlowPanel();
		testTextConverter();
		testBlockList();
		testFields();
		testMultiSelection();
		testNavigator();
		testToolbar();
		testButtons();
		// testImageAnchor();

		return page;
	}

	private void testActions() {
		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.addButton(new AAction() {

			@Override
			protected void onExecute() {
				cm.getApp().callSelectProject("invalidprojectid");
			}

			@Override
			public String getLabel() {
				return "Produce Server Error";
			}
		});
		addTest("Actions", toolbar);
	}

	private void testFloatingFlowPanel() {
		FloatingFlowPanel panel = new FloatingFlowPanel();
		panel.add(Gwt.createDiv("title", "Item 1"));
		panel.add(Gwt.createDiv("title", "Item 2"));
		panel.add(Gwt.createDiv("title", "Item 3"), true);
		panel.add(Gwt.createDiv("title", "Item 4"), true);
		addTest("FloatingFlowPanel", panel);
	}

	private void testTextConverter() {
		StringBuilder html = new StringBuilder();
		html.append(
			cm.getRichtextConverter().toHtml(
				"[Wiki] r1 aaaa t5 aaaa (r3) aaaa r3. aaaa r3: aaaa [t12] aaar7 aaaa r7x aaaa t9")).append("<hr>");
		html.append(cm.getRichtextConverter().toHtml("<b>html?</b> C&A\nnew line")).append("<hr>");
		addTest("TextConverter", new HTML(html.toString()));
	}

	private void testBlockList() {
		final BlockListWidget<String> list = new BlockListWidget<String>(TestBlock.FACTORY);
		list.setObjects("Element A", "Element B", "Element C", "Element D", "Element E");
		list.update();
		addTest("BlockList", list);
	}

	private static class TestBlock extends ABlockWidget<String> {

		@Override
		protected void onInitializationHeader(BlockHeaderWidget header) {
			header.insertPrefixIcon().setWidget(Img.bundle.action16().createImage());
			header.setDragHandle("dmy666");
			header.setCenter(getObject());
			header.appendCenterSuffix("Suffix");
			header.addToolbarAction(new DummyAction("Function 1"));
			header.addToolbarAction(new DummyAction("Function 2"));
			header.addMenuAction(new DummyAction("Function 3"));
			header.addMenuAction(new DummyAction("Function 4"));
			header.addMenuAction(new DummyAction("Function 5"));
		}

		@Override
		protected void onUpdateHeader(BlockHeaderWidget header) {}

		@Override
		protected Widget onExtendedInitialization() {
			HTML content = new HTML(
					"<h3>"
							+ getObject()
							+ "</h3><p>Das ist der Content. Das ist der Content. Das ist der Content. Das ist der Content. </p>");
			return content;
		}

		public static BlockWidgetFactory<String> FACTORY = new BlockWidgetFactory<String>() {

			public TestBlock createBlock() {
				return new TestBlock();
			}
		};

	}

	private String fieldsText = "test";
	private String fieldsRichText = "test";
	private Integer fieldsInt = 5;

	private void testFields() {
		FieldsWidget fields = new FieldsWidget();
		fields.add("TextPropertyEditorWidget", new TextEditorWidget(new ATextEditorModel() {

			@Override
			public void setValue(String value) {
				fieldsText = value;
			}

			@Override
			public String getValue() {
				return fieldsText;
			}
		}));
		fields.add("RichtextEditorWidget", new RichtextEditorWidget(new ATextEditorModel() {

			@Override
			public void setValue(String value) {
				fieldsRichText = value;
			}

			@Override
			public String getValue() {
				return fieldsRichText;
			}
		}));
		fields.add("AIntegerViewEditWidget", new AIntegerViewEditWidget() {

			@Override
			protected void onIntegerViewerUpdate() {
				setViewerValue(fieldsInt, "times");
			}

			@Override
			protected void onEditorUpdate() {
				setEditorValue(fieldsInt);
			}

			@Override
			protected void onEditorSubmit() {
				fieldsInt = getEditorValue();
			}

			@Override
			protected void onPlusClicked() {
				fieldsInt++;
			}

			@Override
			protected void onMinusClicked() {
				fieldsInt--;
			}

		});
		fields.update();
		addTest("FieldsWidget", fields);
	}

	private void testMultiSelection() {
		MultiSelectionWidget<String> ms = new MultiSelectionWidget<String>();
		ms.add("Item 1");
		ms.add("Item 2");
		ms.add("Item 3");
		ms.update();
		addTest("MultiSelectionWidget", ms);
	}

	private void testNavigator() {
		NavigatorWidget navigator = new NavigatorWidget();
		navigator.addItem(Img.bundle.action16(), "Item 1", "1", null);
		navigator.addItem(Img.bundle.action16(), "Item 2", "2", null);
		navigator.addItem(Img.bundle.action16(), "Item 3", "3", null);
		addTest("NavigatorWidget", navigator);
	}

	private void testToolbar() {
		ToolbarWidget toolbar = new ToolbarWidget();
		toolbar.add(new ButtonWidget(createAction(Img.bundle.action16().createImage(), "icon and text")));
		toolbar.add(new ButtonWidget(createAction("text only")));
		toolbar.add(new ButtonWidget(createAction(Img.bundle.action16().createImage(), null)));
		addTest("ToolbarWidget", toolbar);
	}

	private void testButtons() {
		addTest("ButtonWidget:text-only", new ButtonWidget(createAction("text only")));
		addTest("ButtonWidget:icon-only", new ButtonWidget(createAction(Img.bundle.action16().createImage(), null)));
		addTest("ButtonWidget:icon-text", new ButtonWidget(createAction(Img.bundle.action16().createImage(),
			"icon and text")));
		addTest("ButtonWidget:nonexecutable", new ButtonWidget(createAction(Img.bundle.action16().createImage(),
			"icon and text", false)));

		FlowPanel multipleButtons = new FlowPanel();
		multipleButtons.add(new ButtonWidget(createAction("Button 1")).update());
		multipleButtons.add(new ButtonWidget(createAction("Button 2")).update());
		multipleButtons.add(new ButtonWidget(createAction("Button 3")).update());
		addTest("multiple ButtonWidgets", multipleButtons);
	}

	private void testImageAnchor() {
		ImageAnchor a = new ImageAnchor(Img.bundle.action16().createImage(), "click");
		addTest("ImageAnchor", a);
	}

	@Override
	protected void onUpdate() {

	}

	private void addTest(String title, Widget content) {
		Gwt.update(content);

		SimplePanel sectionContent = new SimplePanel();
		sectionContent.setStyleName("test-content");
		sectionContent.setWidget(content);

		page.addHeader(title);
		page.addSection(sectionContent);
	}

	private AAction createAction(String label) {
		return createAction(null, label);
	}

	private AAction createAction(final Image icon, final String label) {
		return createAction(icon, label, true);
	}

	private AAction createAction(final Image icon, final String label, final boolean executable) {
		return new AAction() {

			@Override
			protected void onExecute() {}

			@Override
			public String getLabel() {
				return label;
			}

			@Override
			public Image getIcon() {
				return icon;
			}

			@Override
			public boolean isExecutable() {
				return executable;
			}

			@Override
			public String getTooltip() {
				return "Tooltip for " + getLabel();
			}
		};
	}

	static class DummyAction extends AScrumAction {

		private String label;

		public DummyAction(String label) {
			this.label = label;
		}

		@Override
		public String getLabel() {
			return label;
		}

		@Override
		public boolean isExecutable() {
			return true;
		}

		@Override
		public String getTooltip() {
			return "Tooltip for " + label;
		}

		@Override
		protected void onExecute() {}

	}

}
