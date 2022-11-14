// Generated code from Butter Knife. Do not modify!
package com.in.githubapi.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.in.githubapi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.mainLayout = Utils.findRequiredViewAsType(source, R.id.sample_main_layout, "field 'mainLayout'", LinearLayout.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.imgview, "field 'imageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mainLayout = null;
    target.imageView = null;
  }
}
