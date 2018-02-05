package zhujj.zm.view;

import android.content.Context;

import zhujj.baselibrary.view.MocamAlertDialog;

public class ConfirmDialog extends MocamAlertDialog {
	public ConfirmDialog(Context context, String title, String content,
						 android.view.View.OnClickListener okButtonListener,
						 android.view.View.OnClickListener cancelButtonListener) {
		super(context, title, content, okButtonListener, cancelButtonListener);

	}

	public ConfirmDialog(Context context, String title, String content,
						 String okButtonLabel,
						 android.view.View.OnClickListener okButtonListener,
						 String cancelButtonLabel,
						 android.view.View.OnClickListener cancelButtonListener) {
		super(context, title, content, okButtonLabel, okButtonListener,
				cancelButtonLabel, cancelButtonListener);
	}
}
