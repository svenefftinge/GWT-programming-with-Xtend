package hello.gwt.util

import com.google.gwt.user.client.ui.HasWidgets
import com.google.gwt.user.client.ui.Widget

class ClientUi {
	
	def static <T> T with(T obj, (T)=>void init) {
		init.apply(obj)
		return obj
	}
	
	def static void operator_add(HasWidgets it, Widget widget) {
		it.add(widget)
	}
}