package com.mbh.materialdialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.view.View.GONE;
import static com.mbh.materialdialog.Utils.getColorDrawable;
import static com.mbh.materialdialog.Utils.getLLDrawable;
import static com.mbh.materialdialog.Utils.isGoodString;

/**
 * Created By MBH on 2016-10-17.
 */

public class MD {
    private final Context mContext;
    private final OnMDButtonClicked positiveListener, negativeListener, neutralListener;
    private final OnMDDismissed dismissedListener;
    private final OnMDCanceled canceledListener;
    private final
    @StringRes
    int positiveTextRes, negativeTextRes, neutralTextRes;
    private final Drawable iconDrawable;
    private final
    @DrawableRes
    int iconDrawableRes, backgroundDrawableRes;
    private final
    @LayoutRes
    int customViewRes;
    private final
    @ColorInt
    int positiveColor, negativeColor, neutralColor,
            titleColor, messageColor, backgroundColor;
    private String positiveText;
    private String negativeText;
    private String neutralText;
    private String title, message;
    private
    @StringRes
    int titleRes;
    private
    @StringRes
    int messageRes;
    private Drawable backgroundDrawable;
    private View customView;
    private AlertDialog mAlertDialog;
    private AlertDialog.Builder mAlertDialogBuilder;
    private View contentView;
    private boolean cancalable = true, autoDismiss = true;

    private MD(Builder builder) {
        mContext = builder.context;
        positiveColor = builder.positiveColor;
        negativeColor = builder.negativeColor;
        neutralColor = builder.neutralColor;
        titleColor = builder.titleColor;
        messageColor = builder.messageColor;
        positiveListener = builder.positiveListener;
        negativeListener = builder.negativeListener;
        neutralListener = builder.neutralListener;
        dismissedListener = builder.dismissedListener;
        canceledListener = builder.canceledListener;
        cancalable = builder.cancalable;
        autoDismiss = builder.autoDismiss;

        positiveText = builder.positiveText;
        positiveTextRes = builder.positiveTextRes;
        negativeText = builder.negativeText;
        negativeTextRes = builder.negativeTextRes;
        neutralText = builder.neutralText;
        neutralTextRes = builder.neutralTextRes;

        title = builder.title;
        titleRes = builder.titleRes;
        messageRes = builder.messageRes;
        message = builder.message;
        iconDrawable = builder.iconDrawable;
        iconDrawableRes = builder.iconDrawableRes;
        customView = builder.customView;
        customViewRes = builder.customViewRes;

        backgroundColor = builder.backgroundColor;
        backgroundDrawableRes = builder.backgroundDrawableRes;
        backgroundDrawable = builder.backgroundDrawable;

        initAlertDialog();

        initContentView();
    }

    /**
     * Create a dialog with two button (quick dialog solution)
     *
     * @param context:          Context
     * @param title:            dialog title
     * @param message:          dialog message
     * @param btnTxtPositive:   Positive button text
     * @param positiveListener: positive button listener
     * @param btnTxtNegative:   negative button text
     * @param negativeListener: negative button listener
     * @return MD
     */
    public static MD simpleDoubleBtnMD(Context context,
                                       String title, String message,
                                       String btnTxtPositive,
                                       OnMDButtonClicked positiveListener,
                                       String btnTxtNegative,
                                       OnMDButtonClicked negativeListener) {
        return new MD.Builder(context)
                .title(title)
                .message(message)
                .positiveText(btnTxtPositive)
                .positiveListener(positiveListener)
                .negativeText(btnTxtNegative)
                .negativeListener(negativeListener)
                .build();
    }

    /**
     * Create a dialog with two button (quick dialog solution)
     * @param context:          Context
     * @param title:            dialog title resource
     * @param message:          dialog message resource
     * @param btnTxtPositive:   Positive button text resource
     * @param positiveListener: positive button listener
     * @param btnTxtNegative:   negative button text resource
     * @param negativeListener: negative button listener
     * @return MD
     */
    public static MD simpleDoubleBtnMD(Context context,
                                       @StringRes int title, @StringRes int message,
                                       @StringRes int btnTxtPositive,
                                       OnMDButtonClicked positiveListener,
                                       @StringRes int btnTxtNegative,
                                       OnMDButtonClicked negativeListener) {
        return simpleDoubleBtnMD(context,
                context.getString(title),
                context.getString(message),
                context.getString(btnTxtPositive), positiveListener,
                context.getString(btnTxtNegative), negativeListener);
    }

    /**
     * Create a dialog with one button (quick dialog solution)
     *
     * @param context:      Its better with context
     * @param title:        dialog title
     * @param message:      dialog message
     * @param btnTxt:       button text
     * @param onBtnClicked: Button click listener (if not important, it can be null)
     * @return MD
     */
    public static MD simpleBtnMD(Context context,
                                 String title, String message,
                                 String btnTxt, OnMDButtonClicked onBtnClicked) {
        return new MD.Builder(context)
                .title(title)
                .message(message)
                .positiveText(btnTxt)
                .positiveListener(onBtnClicked)
                .build();
    }

    /**
     * Create a dialog with one button (quick dialog solution)
     *
     * @param context:      Its better with context
     * @param title:        dialog title resource
     * @param message:      dialog message resource
     * @param btnTxt:       button text resource
     * @param onBtnClicked:
     * @return MD
     */
    public static MD simpleBtnMD(Context context,
                                 @StringRes int title, @StringRes int message,
                                 @StringRes int btnTxt, OnMDButtonClicked onBtnClicked) {
        return simpleBtnMD(context, context.getString(title),
                context.getString(message), context.getString(btnTxt), onBtnClicked);
    }

    private void initAlertDialog() {
        mAlertDialogBuilder = new AlertDialog.Builder(mContext);
        mAlertDialogBuilder.setCancelable(cancalable);
    }

    private void initContentView() {
        contentView = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_material_dialog, null);
        mAlertDialog = mAlertDialogBuilder.setView(contentView).create();

        mAlertDialog.getWindow().setBackgroundDrawableResource(R.drawable.material_dialog_window);
        mAlertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mAlertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_STATE);

        mAlertDialog.setCanceledOnTouchOutside(cancalable);

//        mAlertDialogWindow.setContentView(contentView);
        contentView.setFocusable(true);
        contentView.setFocusableInTouchMode(true);

        final View backgroudContainer = contentView.findViewById(R.id.material_background);
        if (backgroundColor != Color.WHITE) {
            backgroundDrawable = getColorDrawable(mContext, backgroundColor);
//            backgroundDrawable = getColorDrawable(mContext, backgroundColor);
        } else if (backgroundDrawableRes > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                backgroundDrawable = mContext.getResources().getDrawable(backgroundDrawableRes, mContext.getTheme());
            } else {
                backgroundDrawable = mContext.getResources().getDrawable(backgroundDrawableRes);
            }
        }
        if (backgroundDrawable != null) {
            backgroundDrawable = getLLDrawable(mContext, backgroundDrawable);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                backgroudContainer.setBackground(backgroundDrawable);
            } else {
                backgroudContainer.setBackgroundDrawable(backgroundDrawable);
            }
        } else {
            backgroudContainer.setBackgroundResource(R.drawable.material_card);
        }

        if (titleRes > 0 || isGoodString(title) || iconDrawable != null || iconDrawableRes > 0) {
            final TextView mTitleView = (TextView) contentView.findViewById(R.id.title);
            final String fTitle = titleRes > 0 ? mContext.getString(titleRes) : title;
            if (isGoodString(fTitle)) {
                mTitleView.setText(title);
                mTitleView.setTextColor(titleColor);
            } else {
                mTitleView.setVisibility(GONE);
            }
            final ImageView iv_icon = (ImageView) contentView.findViewById(R.id.iv_icon);
            if (iconDrawableRes > 0) {
                iv_icon.setImageResource(iconDrawableRes);
            } else if (iconDrawable != null) {
                iv_icon.setImageDrawable(iconDrawable);
            } else {
                iv_icon.setVisibility(GONE);
            }
        } else {
            View ll_titleContainer = contentView.findViewById(R.id.ll_titleContainer);
            ll_titleContainer.setVisibility(GONE);
        }

        final TextView mMessageView = (TextView) contentView.findViewById(R.id.message);
        String text = messageRes > 0 ? mContext.getString(messageRes) : message;
        if (isGoodString(text)) {
            mMessageView.setText(message);
            mMessageView.setTextColor(messageColor);
        } else {
            mMessageView.setVisibility(GONE);
        }

        final RelativeLayout mButtonLayout = (RelativeLayout) contentView.findViewById(R.id.buttonLayout);
//        final LinearLayout buttonPNLayout = (LinearLayout) mAlertDialogWindow.findViewById(R.id.buttonPNLayout);

        Button mPositiveButton = (Button) mButtonLayout.findViewById(R.id.btn_positive);
        Button mNegativeButton = (Button) mButtonLayout.findViewById(R.id.btn_negative);
        Button mNeutralButton = (Button) mButtonLayout.findViewById(R.id.btn_neutral);

        initButton(mNeutralButton, neutralColor, neutralText, neutralTextRes, neutralListener);
        initButton(mNegativeButton, negativeColor, negativeText, negativeTextRes, negativeListener);
        initButton(mPositiveButton, positiveColor, positiveText, positiveTextRes, positiveListener);

        if (mNeutralButton.getVisibility() == GONE &&
                mNegativeButton.getVisibility() == GONE &&
                mPositiveButton.getVisibility() == GONE) {
            mAlertDialog.setCancelable(true);
        }

        if (customViewRes > 0) {
            customView = LayoutInflater.from(mContext)
                    .inflate(customViewRes, null);
        }
        if (customView != null) {
            ViewGroup mMessageContentRoot = (ViewGroup) contentView.findViewById(
                    R.id.message_content_view);
            if (customView != null) {
                mMessageContentRoot.removeAllViews();
                mMessageContentRoot.addView(customView);
            }
        }


        if (dismissedListener != null) {
            mAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    dismissedListener.onDismissed(MD.this);
                }
            });
        }

        if (canceledListener != null) {
            mAlertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    canceledListener.onCancel(MD.this);
                }
            });
        }
    }

    private void initButton(Button button, @ColorInt int color, String text, @StringRes int textRes, final OnMDButtonClicked listener) {
        if (textRes > 0)
            text = mContext.getString(textRes);
        if (!isGoodString(text)) {
            button.setVisibility(GONE);
            return;
        }
        button.setText(text);

        setTextColorToButton(button, color);
        setListenerToButton(button, listener);
    }

    private void setTextColorToButton(Button button, @ColorInt int color) {
        button.setTextColor(color);
        if (isLollipop()) {
            button.setElevation(0);
        }
    }

    private void setListenerToButton(Button button, final OnMDButtonClicked listener) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(MD.this);
                }
                checkAutoDismiss();
            }
        });
    }

    private boolean isLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private void checkAutoDismiss() {
        if (autoDismiss) {
            dismiss();
        }
    }

    /**
     * To Dimiss the dialog if showing
     */
    public void dismiss() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

    /**
     * Check if dialog is showing
     *
     * @return true if showing false if null or not showing
     */
    public boolean isShowing() {
        return mAlertDialog != null && mAlertDialog.isShowing();
    }

    /**
     * Change the title of the Dialog
     *
     * @param title: title string resource id
     * @return MD
     */
    public MD title(@StringRes int title) {
        this.titleRes = title;
        return title(mContext.getString(title));
    }

    /**
     * Change the title of the Dialog
     *
     * @param title: title string
     * @return MD
     */
    public MD title(String title) {
        this.title = title;
        if (contentView != null) {
            final TextView tv = (TextView) contentView.findViewById(R.id.title);
            tv.setText(title);
            tv.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * Change the message of the dialog
     *
     * @param message: message string resource id
     * @return MD
     */
    public MD message(@StringRes int message) {
        messageRes = message;
        return message(mContext.getString(messageRes));
    }

    /**
     * Change the message of the dialog
     *
     * @param message: message string
     * @return MD
     */
    public MD message(String message) {
        this.message = message;
        if (contentView != null) {
            final TextView msg = (TextView) contentView.findViewById(R.id.message);
            msg.setText(message);
            msg.setVisibility(View.VISIBLE);
        }
        return this;
    }

    /**
     * Show Dialog
     *
     * @return MaterialDesign
     */
    public MD show() {
        mAlertDialog.show();
        return this;
    }

    /**
     * Building The Material Dialog with Builder pattern
     */
    public static final class Builder {
        private final Context context;
        private
        @ColorInt
        int positiveColor = Color.BLUE;
        private
        @ColorInt
        int negativeColor = Color.RED;
        private
        @ColorInt
        int neutralColor = Color.BLACK;
        private
        @ColorInt
        int titleColor = Color.BLACK;
        private
        @ColorInt
        int messageColor = Color.DKGRAY;
        private
        @ColorInt
        int backgroundColor = Color.WHITE;
        private OnMDButtonClicked positiveListener;
        private OnMDButtonClicked negativeListener;
        private OnMDButtonClicked neutralListener;
        private OnMDDismissed dismissedListener;
        private OnMDCanceled canceledListener;
        private boolean cancalable = true;
        private boolean autoDismiss = true;
        private String positiveText;
        private
        @StringRes
        int positiveTextRes = -1;
        private String negativeText;
        private
        @StringRes
        int negativeTextRes = -1;
        private String neutralText;
        private
        @StringRes
        int neutralTextRes = -1;
        private String title;
        private
        @StringRes
        int titleRes = -1;
        private String message;
        private
        @StringRes
        int messageRes = -1;
        private Drawable iconDrawable, backgroundDrawable;
        private
        @DrawableRes
        int iconDrawableRes = -1;
        private
        @DrawableRes
        int backgroundDrawableRes = -1;
        private View customView;
        private
        @LayoutRes
        int customViewRes = -1;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder positiveColor(@ColorInt int val) {
            positiveColor = val;
            return this;
        }

        public Builder negativeColor(@ColorInt int val) {
            negativeColor = val;
            return this;
        }

        public Builder neutralColor(@ColorInt int val) {
            neutralColor = val;
            return this;
        }

        public Builder titleColor(@ColorInt int val) {
            titleColor = val;
            return this;
        }

        public Builder messageColor(@ColorInt int val) {
            messageColor = val;
            return this;
        }

        public Builder positiveListener(OnMDButtonClicked val) {
            positiveListener = val;
            return this;
        }

        public Builder negativeListener(OnMDButtonClicked val) {
            negativeListener = val;
            return this;
        }

        public Builder neutralListener(OnMDButtonClicked val) {
            neutralListener = val;
            return this;
        }

        public Builder dismissedListener(OnMDDismissed val) {
            dismissedListener = val;
            return this;
        }

        public Builder canceledListener(OnMDCanceled val) {
            canceledListener = val;
            return this;
        }

        public Builder cancalable(boolean val) {
            cancalable = val;
            return this;
        }

        public Builder autoDismiss(boolean val) {
            autoDismiss = val;
            return this;
        }

        public Builder positiveText(String val) {
            positiveText = val;
            return this;
        }

        public Builder positiveText(@StringRes int val) {
            positiveTextRes = val;
            return this;
        }

        public Builder negativeText(String val) {
            negativeText = val;
            return this;
        }

        public Builder negativeText(@StringRes int val) {
            negativeTextRes = val;
            return this;
        }

        public Builder neutralText(String val) {
            neutralText = val;
            return this;
        }

        public Builder neutralText(@StringRes int val) {
            neutralTextRes = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder title(@StringRes int titleRes) {
            this.titleRes = titleRes;
            return this;
        }

        public Builder message(@StringRes int messageRes) {
            this.messageRes = messageRes;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder iconDrawable(Drawable val) {
            iconDrawable = val;
            return this;
        }

        public Builder icon(@DrawableRes int iconDrawableRes) {
            this.iconDrawableRes = iconDrawableRes;
            return this;
        }

        public Builder customView(View val) {
            customView = val;
            return this;
        }

        public Builder customView(@LayoutRes int val) {
            customViewRes = val;
            return this;
        }

        public Builder backgroundDrawable(@DrawableRes int backgroundDrawable) {
            this.backgroundDrawableRes = backgroundDrawable;
            return this;
        }

        public Builder backgroundDrawable(Drawable backgroundDrawable) {
            this.backgroundDrawable = backgroundDrawable;
            return this;
        }

        public Builder backgroundColor(@ColorInt int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public MD build() {
            return new MD(this);
        }
    }
}
