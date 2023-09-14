// Generated by view binder compiler. Do not edit!
package com.meerim.akchamanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.meerim.akchamanager.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGoalsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final MaterialButton btnAdd;

  @NonNull
  public final ImageView imgDollar;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final TextView textView;

  @NonNull
  public final TextView txtAmount;

  private ActivityGoalsBinding(@NonNull ConstraintLayout rootView, @NonNull MaterialButton btnAdd,
      @NonNull ImageView imgDollar, @NonNull RecyclerView recyclerView, @NonNull TextView textView,
      @NonNull TextView txtAmount) {
    this.rootView = rootView;
    this.btnAdd = btnAdd;
    this.imgDollar = imgDollar;
    this.recyclerView = recyclerView;
    this.textView = textView;
    this.txtAmount = txtAmount;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGoalsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGoalsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_goals, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGoalsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnAdd;
      MaterialButton btnAdd = ViewBindings.findChildViewById(rootView, id);
      if (btnAdd == null) {
        break missingId;
      }

      id = R.id.imgDollar;
      ImageView imgDollar = ViewBindings.findChildViewById(rootView, id);
      if (imgDollar == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      id = R.id.txtAmount;
      TextView txtAmount = ViewBindings.findChildViewById(rootView, id);
      if (txtAmount == null) {
        break missingId;
      }

      return new ActivityGoalsBinding((ConstraintLayout) rootView, btnAdd, imgDollar, recyclerView,
          textView, txtAmount);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}