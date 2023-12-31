// Generated by view binder compiler. Do not edit!
package com.meerim.akchamanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.meerim.akchamanager.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityArticleBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView txtBodyArticle;

  @NonNull
  public final TextView txtTitleArticle;

  private ActivityArticleBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView txtBodyArticle, @NonNull TextView txtTitleArticle) {
    this.rootView = rootView;
    this.txtBodyArticle = txtBodyArticle;
    this.txtTitleArticle = txtTitleArticle;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityArticleBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityArticleBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_article, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityArticleBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.txtBodyArticle;
      TextView txtBodyArticle = ViewBindings.findChildViewById(rootView, id);
      if (txtBodyArticle == null) {
        break missingId;
      }

      id = R.id.txtTitleArticle;
      TextView txtTitleArticle = ViewBindings.findChildViewById(rootView, id);
      if (txtTitleArticle == null) {
        break missingId;
      }

      return new ActivityArticleBinding((ConstraintLayout) rootView, txtBodyArticle,
          txtTitleArticle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
