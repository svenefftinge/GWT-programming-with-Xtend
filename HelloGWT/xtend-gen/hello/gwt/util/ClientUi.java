package hello.gwt.util;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ClientUi {
  public static <T extends Object> T with(final T obj, final Procedure1<? super T> init) {
    init.apply(obj);
    return obj;
  }
  
  public static void operator_add(final HasWidgets it, final Widget widget) {
    it.add(widget);
  }
}
