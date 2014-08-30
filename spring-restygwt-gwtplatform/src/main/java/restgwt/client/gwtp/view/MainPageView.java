package restgwt.client.gwtp.view;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import restgwt.client.restygwt.RestHelloService;
import restgwt.client.restygwt.UserData;
import restgwt.client.restygwt.UserElaboratedData;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class MainPageView extends ViewImpl
implements MainPagePresenter.MyView {

	HTMLPanel panel = new HTMLPanel("");

	@Inject
	public MainPageView() {
		final TextBox name = new TextBox();
		final IntegerBox year = new IntegerBox();
		Button button = new Button("Submit Async");
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RestHelloService.New.get().getInfoAsync( new UserData(year.getValue(), name.getValue()) , new MethodCallback<UserElaboratedData>() {
					@Override
					public void onSuccess(Method method, UserElaboratedData response) {
						RootPanel.get().add(new Label("name: " + response.name + " age: " +response.age + " (received at " + response.ready_time + ")"));
					}

					@Override
					public void onFailure(Method method, Throwable exception) {
						GWT.log("Error");
					}
				});
			}
		});

		panel.add(new Label("Your name: "));
		panel.add(name);
		panel.add(new Label("Year of born: "));
		panel.add(year);
		panel.add(button);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}
}
