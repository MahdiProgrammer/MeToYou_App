package noori.mahdi.mtu.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.switchmaterial.SwitchMaterial;

import noori.mahdi.mtu.R;

public class SecurityPrivacyActivity extends AppCompatActivity {

    private static final String PREF_SECURITY = "pref_security_privacy";
    private static final String KEY_APP_LOCK = "key_app_lock";
    private static final String KEY_BIOMETRIC = "key_biometric";

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_privacy);

        preferences = getSharedPreferences(PREF_SECURITY, MODE_PRIVATE);
        findViewById(R.id.securityPrivacyRoot).setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);

        setupHeader();
        setupRows();
        applyHeaderInsets();
    }

    private void setupHeader() {
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }

    private void setupRows() {
        setupSwitchRow(
                R.id.item_app_lock,
                R.string.app_lock_title,
                R.string.app_lock_subtitle,
                R.drawable.ic_lock,
                KEY_APP_LOCK
        );

        setupSwitchRow(
                R.id.item_biometric,
                R.string.biometric_title,
                R.string.biometric_subtitle,
                R.drawable.ic_fingerprint,
                KEY_BIOMETRIC
        );

        setupNavigationRow(
                R.id.item_change_pin,
                R.string.change_pin_title,
                R.string.change_pin_subtitle,
                R.drawable.ic_pin,
                v -> Toast.makeText(this, R.string.change_pin_placeholder, Toast.LENGTH_SHORT).show()
        );

        setupNavigationValueRow(
                R.id.item_auto_lock,
                R.string.auto_lock_title,
                R.string.auto_lock_subtitle,
                R.drawable.ic_auto_lock,
                R.string.auto_lock_value_30_seconds,
                v -> Toast.makeText(this, R.string.auto_lock_placeholder, Toast.LENGTH_SHORT).show()
        );
    }

    private void setupSwitchRow(
            @IdRes int rowId,
            @StringRes int titleRes,
            @StringRes int subtitleRes,
            @DrawableRes int iconRes,
            String preferenceKey
    ) {
        MaterialCardView row = findViewById(rowId);
        TextView titleText = row.findViewById(R.id.titleText);
        TextView subtitleText = row.findViewById(R.id.subtitleText);
        ImageView iconView = row.findViewById(R.id.iconView);
        SwitchMaterial switchControl = row.findViewById(R.id.switchControl);

        titleText.setText(titleRes);
        subtitleText.setText(subtitleRes);
        iconView.setImageResource(iconRes);

        boolean isEnabled = preferences.getBoolean(preferenceKey, false);
        switchControl.setChecked(isEnabled);
        switchControl.setOnCheckedChangeListener((buttonView, checked) ->
                preferences.edit().putBoolean(preferenceKey, checked).apply()
        );

        row.setOnClickListener(v -> switchControl.toggle());
        row.setClickable(true);
        row.setFocusable(true);
    }

    private void setupNavigationRow(
            @IdRes int rowId,
            @StringRes int titleRes,
            @StringRes int subtitleRes,
            @DrawableRes int iconRes,
            View.OnClickListener clickListener
    ) {
        MaterialCardView row = findViewById(rowId);
        TextView titleText = row.findViewById(R.id.titleText);
        TextView subtitleText = row.findViewById(R.id.subtitleText);
        ImageView iconView = row.findViewById(R.id.iconView);

        titleText.setText(titleRes);
        subtitleText.setText(subtitleRes);
        iconView.setImageResource(iconRes);

        row.setOnClickListener(clickListener);
        row.setClickable(true);
        row.setFocusable(true);
    }

    private void setupNavigationValueRow(
            @IdRes int rowId,
            @StringRes int titleRes,
            @StringRes int subtitleRes,
            @DrawableRes int iconRes,
            @StringRes int valueRes,
            View.OnClickListener clickListener
    ) {
        MaterialCardView row = findViewById(rowId);
        TextView titleText = row.findViewById(R.id.titleText);
        TextView subtitleText = row.findViewById(R.id.subtitleText);
        TextView valueText = row.findViewById(R.id.valueText);
        ImageView iconView = row.findViewById(R.id.iconView);

        titleText.setText(titleRes);
        subtitleText.setText(subtitleRes);
        valueText.setText(valueRes);
        iconView.setImageResource(iconRes);

        row.setOnClickListener(clickListener);
        row.setClickable(true);
        row.setFocusable(true);
    }

    private void applyHeaderInsets() {
        View header = findViewById(R.id.headerContainer);
        final int paddingStart = header.getPaddingStart();
        final int paddingEnd = header.getPaddingEnd();
        final int paddingBottom = header.getPaddingBottom();
        final int baseTopPadding = getResources().getDimensionPixelSize(R.dimen.settings_header_top_inset_base);

        ViewCompat.setOnApplyWindowInsetsListener(header, (view, windowInsets) -> {
            Insets statusInsets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars());
            view.setPaddingRelative(paddingStart, baseTopPadding + statusInsets.top, paddingEnd, paddingBottom);
            return windowInsets;
        });
        ViewCompat.requestApplyInsets(header);
    }
}
