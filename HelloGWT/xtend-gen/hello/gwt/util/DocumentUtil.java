package hello.gwt.util;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("all")
public class DocumentUtil {
  public static <T extends Widget> T operator_mappedTo(final String id, final T element) {
    final RootPanel ele = RootPanel.get(id);
    if (ele!=null) ele.add(element);
    return element;
  }
}
