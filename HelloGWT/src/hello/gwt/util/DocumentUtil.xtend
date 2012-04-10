package hello.gwt.util

import com.google.gwt.user.client.ui.RootPanel
import com.google.gwt.user.client.ui.Widget

class DocumentUtil {
	
	def static <T extends Widget> operator_mappedTo(String id, T element) {
		val ele = RootPanel::get(id)
		ele?.add(element)
		return element
	}
	
}