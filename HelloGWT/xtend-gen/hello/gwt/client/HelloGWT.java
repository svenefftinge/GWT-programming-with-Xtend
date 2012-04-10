package hello.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import hello.gwt.client.GreetingService;
import hello.gwt.client.GreetingServiceAsync;
import hello.gwt.shared.FieldVerifier;
import hello.gwt.util.ClientUi;
import hello.gwt.util.ConfigurableAsyncCallback;
import hello.gwt.util.DocumentUtil;
import org.eclipse.xtext.xbase.lib.BooleanExtensions;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class HelloGWT implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static String SERVER_ERROR = new Function0<String>() {
    public String apply() {
      String _plus = StringExtensions.operator_plus("An error occurred while ", "attempting to contact the server. Please check your network ");
      String _plus_1 = StringExtensions.operator_plus(_plus, "connection and try again.");
      return _plus_1;
    }
  }.apply();
  
  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private GreetingServiceAsync greetingService = new Function0<GreetingServiceAsync>() {
    public GreetingServiceAsync apply() {
      GreetingServiceAsync _create = GWT.<GreetingServiceAsync>create(GreetingService.class);
      return _create;
    }
  }.apply();
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Button _button = new Button("Send");
    final Procedure1<Button> _function = new Procedure1<Button>() {
        public void apply(final Button it) {
          it.addStyleName("sendButton");
          DocumentUtil.<Button>operator_mappedTo(
            "sendButtonContainer", it);
        }
      };
    final Button sendButton = ClientUi.<Button>with(_button, _function);
    Label _label = new Label();
    final Label errorLabel = DocumentUtil.<Label>operator_mappedTo("errorLabelContainer", _label);
    TextBox _textBox = new TextBox();
    final Procedure1<TextBox> _function_1 = new Procedure1<TextBox>() {
        public void apply(final TextBox it) {
          it.setText("GWT User");
          DocumentUtil.<TextBox>operator_mappedTo(
            "nameFieldContainer", it);
          it.setFocus(true);
          it.selectAll();
        }
      };
    final TextBox nameField = ClientUi.<TextBox>with(_textBox, _function_1);
    Label _label_1 = new Label();
    final Label textToServerLabel = _label_1;
    HTML _hTML = new HTML();
    final HTML serverResponseLabel = _hTML;
    Button _button_1 = new Button("Close");
    final Button closeButton = _button_1;
    DialogBox _dialogBox = new DialogBox();
    final Procedure1<DialogBox> _function_2 = new Procedure1<DialogBox>() {
        public void apply(final DialogBox box) {
          box.setText("Remote Procedure Call");
          box.setAnimationEnabled(true);
          VerticalPanel _verticalPanel = new VerticalPanel();
          final Procedure1<VerticalPanel> _function = new Procedure1<VerticalPanel>() {
              public void apply(final VerticalPanel it) {
                it.addStyleName("dialogVPanel");
                HTML _hTML = new HTML("<b>Sending name to the server:</b>");
                ClientUi.operator_add(it, _hTML);
                ClientUi.operator_add(it, textToServerLabel);
                HTML _hTML_1 = new HTML("<br><b>Server replies:</b>");
                ClientUi.operator_add(it, _hTML_1);
                ClientUi.operator_add(it, serverResponseLabel);
                it.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
                final Procedure1<Button> _function = new Procedure1<Button>() {
                    public void apply(final Button it) {
                      Element _element = it.getElement();
                      _element.setId("closeButton");
                      final Procedure1<ClickEvent> _function = new Procedure1<ClickEvent>() {
                          public void apply(final ClickEvent it) {
                            box.hide();
                            sendButton.setEnabled(true);
                            sendButton.setFocus(true);
                          }
                        };
                      it.addClickHandler(new ClickHandler() {
                          public void onClick(ClickEvent event) {
                            _function.apply(event);
                          }
                      });
                    }
                  };
                Button _with = ClientUi.<Button>with(closeButton, _function);
                ClientUi.operator_add(it, _with);
              }
            };
          VerticalPanel _with = ClientUi.<VerticalPanel>with(_verticalPanel, _function);
          box.setWidget(_with);
        }
      };
    final DialogBox dialogBox = ClientUi.<DialogBox>with(_dialogBox, _function_2);
    final Procedure0 _function_3 = new Procedure0() {
        public void apply() {
          String _text = nameField.getText();
          boolean _isValidName = FieldVerifier.isValidName(_text);
          boolean _not = BooleanExtensions.operator_not(_isValidName);
          if (_not) {
            errorLabel.setText("Please enter at least four characters");
            return;
          }
          errorLabel.setText("");
          sendButton.setEnabled(false);
          String _text_1 = nameField.getText();
          textToServerLabel.setText(_text_1);
          serverResponseLabel.setText("");
          String _text_2 = nameField.getText();
          final Procedure1<ConfigurableAsyncCallback<String>> _function = new Procedure1<ConfigurableAsyncCallback<String>>() {
              public void apply(final ConfigurableAsyncCallback<String> it) {
                final Procedure1<String> _function = new Procedure1<String>() {
                    public void apply(final String it) {
                      dialogBox.setText("Remote Procedure Call");
                      serverResponseLabel.removeStyleName("serverResponseLabelError");
                      serverResponseLabel.setHTML(it);
                      dialogBox.center();
                      closeButton.setFocus(true);
                    }
                  };
                it.onSuccessDo(_function);
                final Procedure1<Throwable> _function_1 = new Procedure1<Throwable>() {
                    public void apply(final Throwable it) {
                      dialogBox.setText("Remote Procedure Call - Failure");
                      serverResponseLabel.addStyleName("serverResponseLabelError");
                      serverResponseLabel.setHTML(HelloGWT.SERVER_ERROR);
                      dialogBox.center();
                      closeButton.setFocus(true);
                    }
                  };
                it.onFailureDo(_function_1);
              }
            };
          AsyncCallback<String> _callback = ConfigurableAsyncCallback.<String>callback(_function);
          HelloGWT.this.greetingService.greetServer(_text_2, _callback);
        }
      };
    final Runnable sendNameToServer = new Runnable() {
        public void run() {
          _function_3.apply();
        }
    };
    final Procedure1<ClickEvent> _function_4 = new Procedure1<ClickEvent>() {
        public void apply(final ClickEvent it) {
          sendNameToServer.run();
        }
      };
    sendButton.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          _function_4.apply(event);
        }
    });
    final Procedure1<KeyUpEvent> _function_5 = new Procedure1<KeyUpEvent>() {
        public void apply(final KeyUpEvent it) {
          int _nativeKeyCode = it.getNativeKeyCode();
          boolean _equals = IntegerExtensions.operator_equals(_nativeKeyCode, KeyCodes.KEY_ENTER);
          if (_equals) {
            sendNameToServer.run();
          }
        }
      };
    nameField.addKeyUpHandler(new KeyUpHandler() {
        public void onKeyUp(KeyUpEvent event) {
          _function_5.apply(event);
        }
    });
  }
}
