package hello.gwt.client

import com.google.gwt.core.client.EntryPoint
import com.google.gwt.core.client.GWT
import com.google.gwt.event.dom.client.KeyCodes
import com.google.gwt.user.client.ui.Button
import com.google.gwt.user.client.ui.DialogBox
import com.google.gwt.user.client.ui.HTML
import com.google.gwt.user.client.ui.Label
import com.google.gwt.user.client.ui.TextBox
import com.google.gwt.user.client.ui.VerticalPanel

import static hello.gwt.client.HelloGWT.*
import static hello.gwt.util.ConfigurableAsyncCallback.*

import static extension hello.gwt.shared.FieldVerifier.*
import static extension hello.gwt.util.ClientUi.*
import static extension hello.gwt.util.DocumentUtil.*

class HelloGWT implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again."

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private GreetingServiceAsync greetingService = GWT::create(typeof(GreetingService))
	
	/**
	 * This is the entry point method.
	 */
	override void onModuleLoad() {
		val sendButton = new Button("Send").with [
			addStyleName("sendButton")
			"sendButtonContainer" -> it
		]
		
		val errorLabel = "errorLabelContainer" -> new Label
		
		val nameField = new TextBox().with [
			text = "GWT User"
			"nameFieldContainer" -> it
			focus = true
			selectAll
		]
		

		// declare referenced elements
		val textToServerLabel = new Label
		val serverResponseLabel = new HTML
		val closeButton = new Button("Close")
		// Create the popup dialog box
		val dialogBox = new DialogBox().with [ box |
			box.text = "Remote Procedure Call"
			box.animationEnabled = true
			
			box.widget = new VerticalPanel().with [
				addStyleName("dialogVPanel")
				it += new HTML("<b>Sending name to the server:</b>")
				it += textToServerLabel
				it += new HTML("<br><b>Server replies:</b>")
				it += serverResponseLabel
				horizontalAlignment = VerticalPanel::ALIGN_RIGHT
				it += closeButton.with [
					// We can set the id of a widget by accessing its Element
					element.id = "closeButton"
					// Add a handler to close the DialogBox
					addClickHandler [
						box.hide
						sendButton.enabled = true
						sendButton.focus = true
					]
				]
			]
		]
		
		val Runnable sendNameToServer = [|
			// First, we validate the input.
			if (!nameField.text.isValidName) {
				errorLabel.text = "Please enter at least four characters"
				return
			}
			errorLabel.text = ''
	
			// Then, we send the input to the server.
			sendButton.enabled = false
			textToServerLabel.text = nameField.text
			serverResponseLabel.text = ''
			greetingService.greetServer(nameField.text,
				callback [
					onSuccessDo [
						dialogBox.text = "Remote Procedure Call"
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.HTML = it
						dialogBox.center
						closeButton.focus = true
					]
					onFailureDo [
						// Show the RPC error message to the user
						dialogBox.text = "Remote Procedure Call - Failure"
						serverResponseLabel.addStyleName("serverResponseLabelError")
						serverResponseLabel.HTML = SERVER_ERROR
						dialogBox.center
						closeButton.focus = true
					]
				])
		]


		// Add a handler to send the name to the server
		sendButton.addClickHandler [ 
			sendNameToServer.run
		]
		nameField.addKeyUpHandler [ 
			if (nativeKeyCode == KeyCodes::KEY_ENTER) {
				sendNameToServer.run
			}
		]
	}
	
}