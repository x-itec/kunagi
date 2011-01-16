package scrum.client.project;

import ilarkesto.gwt.client.ADropdownViewEditWidget;
import ilarkesto.gwt.client.AMultiSelectionViewEditWidget;
import ilarkesto.gwt.client.TableBuilder;

import java.util.ArrayList;
import java.util.List;

import scrum.client.ScrumGwt;
import scrum.client.admin.ProjectUserConfig;
import scrum.client.common.AScrumWidget;
import scrum.client.common.ThemesWidget;

import com.google.gwt.user.client.ui.Widget;

public class ProductBacklogFilterWidget extends AScrumWidget {

	private ProjectUserConfig userConfig;

	public List<Requirement> getRequirements() {
		initialize();
		List<Requirement> ret = new ArrayList<Requirement>();
		for (Requirement req : getCurrentProject().getProductBacklogRequirements()) {
			if (matches(req)) ret.add(req);
		}
		return ret;
	}

	private boolean matches(Requirement req) {
		if (!userConfig.getPblFilterThemes().isEmpty()) {
			for (String theme : userConfig.getPblFilterThemes()) {
				if (!req.containsTheme(theme)) return false;
			}
		}
		if (!userConfig.getPblFilterQualitys().isEmpty()) {
			for (Quality quality : userConfig.getPblFilterQualitys()) {
				if (!req.containsQuality(quality)) return false;
			}
		}
		if (userConfig.getPblFilterEstimationFrom() != null) {
			Float estimatedWork = req.getEstimatedWork();
			if (estimatedWork == null || estimatedWork < userConfig.getPblFilterEstimationFrom()) return false;
		}
		if (userConfig.getPblFilterEstimationTo() != null) {
			Float estimatedWork = req.getEstimatedWork();
			if (estimatedWork == null || estimatedWork > userConfig.getPblFilterEstimationTo()) return false;
		}
		// if (userConfig.getPblFilterDateFrom()!=null) {
		// if (!req.getlastM)
		// }
		return true;
	}

	@Override
	protected Widget onInitialization() {
		userConfig = getCurrentProject().getUserConfig(getCurrentUser());

		TableBuilder tb = ScrumGwt.createFieldTable();

		tb.addFieldRow("Themes", new ThemesWidget(userConfig.getPblFilter()), 3);
		tb.addFieldRow("Qualities", new AMultiSelectionViewEditWidget<Quality>() {

			@Override
			protected void onViewerUpdate() {
				setViewerItemsAsHtml(userConfig.getPblFilterQualitys());
			}

			@Override
			protected void onEditorUpdate() {
				setEditorItems(userConfig.getProject().getQualitys());
				setEditorSelectedItems(userConfig.getPblFilterQualitys());
			}

			@Override
			protected void onEditorSubmit() {
				userConfig.setPblFilterQualitys(getEditorSelectedItems());
			}

			@Override
			public boolean isEditable() {
				return true;
			}
		}, 3);
		tb.addField("Estimated from", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(ScrumGwt.getEstimationAsString(userConfig.getPblFilterEstimationFrom(), userConfig
						.getProject().getEffortUnit()));
			}

			@Override
			protected void onEditorUpdate() {
				setOptions(Requirement.WORK_ESTIMATION_VALUES);
				String work = ScrumGwt.getEstimationAsString(userConfig.getPblFilterEstimationFrom());
				setSelectedOption(work == null ? "" : work.toString());
			}

			@Override
			protected void onEditorSubmit() {
				String value = getSelectedOption();
				userConfig.setPblFilterEstimationFrom(value.length() == 0 ? null : Float.parseFloat(value));
			}

			@Override
			public boolean isEditable() {
				return true;
			}

		});
		tb.addFieldRow("Estimated to", new ADropdownViewEditWidget() {

			@Override
			protected void onViewerUpdate() {
				setViewerText(ScrumGwt.getEstimationAsString(userConfig.getPblFilterEstimationTo(), userConfig
						.getProject().getEffortUnit()));
			}

			@Override
			protected void onEditorUpdate() {
				setOptions(Requirement.WORK_ESTIMATION_VALUES);
				String work = ScrumGwt.getEstimationAsString(userConfig.getPblFilterEstimationTo());
				setSelectedOption(work == null ? "" : work.toString());
			}

			@Override
			protected void onEditorSubmit() {
				String value = getSelectedOption();
				userConfig.setPblFilterEstimationTo(value.length() == 0 ? null : Float.parseFloat(value));
			}

			@Override
			public boolean isEditable() {
				return true;
			}

		});

		// tb.addField("Last modified from", userConfig.getPblFilterDateFromModel());
		// tb.addFieldRow("Last modified to", userConfig.getPblFilterDateToModel());

		return tb.createTable();
	}
}
