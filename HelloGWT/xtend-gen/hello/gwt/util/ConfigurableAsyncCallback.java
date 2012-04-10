package hello.gwt.util;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class ConfigurableAsyncCallback<T extends Object> implements AsyncCallback<T> {
  public static <T extends Object> AsyncCallback<T> onSuccess(final Procedure1<? super T> onSuccess) {
    ConfigurableAsyncCallback<T> _configurableAsyncCallback = new ConfigurableAsyncCallback<T>();
    final ConfigurableAsyncCallback<T> x = _configurableAsyncCallback;
    x.onSuccessDo(onSuccess);
    return x;
  }
  
  public static <T extends Object> AsyncCallback<T> callback(final Procedure1<? super ConfigurableAsyncCallback<T>> init) {
    ConfigurableAsyncCallback<T> _configurableAsyncCallback = new ConfigurableAsyncCallback<T>();
    final ConfigurableAsyncCallback<T> x = _configurableAsyncCallback;
    init.apply(x);
    return x;
  }
  
  private Procedure1<? super Throwable> onFailure = new Function0<Procedure1<? super Throwable>>() {
    public Procedure1<? super Throwable> apply() {
      final Procedure1<Throwable> _function = new Procedure1<Throwable>() {
          public void apply(final Throwable it) {
          }
        };
      return _function;
    }
  }.apply();
  
  private Procedure1<? super T> onSuccess = new Function0<Procedure1<? super T>>() {
    public Procedure1<? super T> apply() {
      final Procedure1<T> _function = new Procedure1<T>() {
          public void apply(final T it) {
          }
        };
      return _function;
    }
  }.apply();
  
  public void onSuccessDo(final Procedure1<? super T> onSuccess) {
    this.onSuccess = onSuccess;
  }
  
  public void onFailureDo(final Procedure1<? super Throwable> onFailure) {
    this.onFailure = onFailure;
  }
  
  public void onFailure(final Throwable caught) {
    this.onFailure.apply(caught);
  }
  
  public void onSuccess(final T result) {
    this.onSuccess.apply(result);
  }
}
