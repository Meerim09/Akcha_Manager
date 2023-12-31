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

public final class ItemIncomeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView amountTextView;

  @NonNull
  public final TextView dateTextView;

  @NonNull
  public final TextView descriptionTextView;

  private ItemIncomeBinding(@NonNull ConstraintLayout rootView, @NonNull TextView amountTextView,
      @NonNull TextView dateTextView, @NonNull TextView descriptionTextView) {
    this.rootView = rootView;
    this.amountTextView = amountTextView;
    this.dateTextView = dateTextView;
    this.descriptionTextView = descriptionTextView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemIncomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemIncomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_income, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemIncomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.amountTextView;
      TextView amountTextView = ViewBindings.findChildViewById(rootView, id);
      if (amountTextView == null) {
        break missingId;
      }

      id = R.id.dateTextView;
      TextView dateTextView = ViewBindings.findChildViewById(rootView, id);
      if (dateTextView == null) {
        break missingId;
      }

      id = R.id.descriptionTextView;
      TextView descriptionTextView = ViewBindings.findChildViewById(rootView, id);
      if (descriptionTextView == null) {
        break missingId;
      }

      return new ItemIncomeBinding((ConstraintLayout) rootView, amountTextView, dateTextView,
          descriptionTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
