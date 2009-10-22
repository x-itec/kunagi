package scrum.client.impediments;

import scrum.client.common.ABlockWidget;
import scrum.client.common.AScrumAction;
import scrum.client.common.BlockHeaderWidget;
import scrum.client.common.BlockWidgetFactory;
import scrum.client.dnd.TrashSupport;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImpedimentBlock extends ABlockWidget<Impediment> implements TrashSupport {

	private Label dateLabel;

	@Override
	protected void onInitializationHeader(BlockHeaderWidget header) {
		Impediment impediment = getObject();
		dateLabel = header.insertPrefixLabel("80px");
		header.addMenuAction(new CloseImpedimentAction(impediment));
		header.addMenuAction(new DeleteImpedimentAction(impediment));
	}

	@Override
	protected void onUpdateHeader(BlockHeaderWidget header) {
		Impediment impediment = getObject();
		dateLabel.setText(impediment.getDate().toString());
		header.setDragHandle(impediment.getReference());
		header.setCenter(impediment.getLabel());
	}

	@Override
	protected Widget onExtendedInitialization() {
		return new ImpedimentWidget(getObject());
	}

	public AScrumAction getTrashAction() {
		return new DeleteImpedimentAction(getObject());
	}

	public static final BlockWidgetFactory<Impediment> FACTORY = new BlockWidgetFactory<Impediment>() {

		public ImpedimentBlock createBlock() {
			return new ImpedimentBlock();
		}

	};

}
