package noori.mahdi.mtu.ui.settings;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

import noori.mahdi.mtu.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        findViewById(R.id.settingsRoot).setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);

        setupHeader();
        setupRows();
        applyHeaderInsets();
    }

    private void setupHeader() {
        ImageButton buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }

    private void setupRows() {
        setupNavigationRow(
                R.id.item_package_activation,
                R.string.package_activation_title,
                R.string.package_activation_subtitle,
                R.drawable.ic_package_activation,
                v -> showFeaturePlaceholder(R.string.package_activation_title)
        );

        setupNavigationRow(
                R.id.item_companies,
                R.string.companies_title,
                R.string.companies_subtitle,
                R.drawable.ic_companies,
                v -> showFeaturePlaceholder(R.string.companies_title)
        );

        MaterialCardView securityRow = setupNavigationRow(
                R.id.item_security_privacy,
                R.string.security_privacy_item_title,
                R.string.security_privacy_item_subtitle,
                R.drawable.ic_security_privacy,
                v -> startActivity(new Intent(this, SecurityPrivacyActivity.class))
        );
        setHighlighted(securityRow, true);

        setupNavigationRow(
                R.id.item_backup,
                R.string.backup_title,
                R.string.backup_subtitle,
                R.drawable.ic_backup,
                v -> showFeaturePlaceholder(R.string.backup_title)
        );

        setupNavigationRow(
                R.id.item_about,
                R.string.about_title,
                R.string.about_subtitle,
                R.drawable.ic_about,
                v -> showFeaturePlaceholder(R.string.about_title)
        );
    }

    private MaterialCardView setupNavigationRow(
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
        return row;
    }

    private void setHighlighted(MaterialCardView row, boolean highlighted) {
        int backgroundColor = highlighted
                ? ContextCompat.getColor(this, R.color.settings_card_highlight_background)
                : ContextCompat.getColor(this, R.color.settings_card_background);
        int strokeColor = highlighted
                ? ContextCompat.getColor(this, R.color.settings_card_highlight_stroke)
                : ContextCompat.getColor(this, R.color.settings_card_stroke);
        int iconTint = highlighted
                ? ContextCompat.getColor(this, R.color.settings_icon_accent_tint)
                : ContextCompat.getColor(this, R.color.settings_icon_tint);

        row.setCardBackgroundColor(backgroundColor);
        row.setStrokeColor(strokeColor);

        FrameLayout iconContainer = row.findViewById(R.id.iconContainer);
        ImageView iconView = row.findViewById(R.id.iconView);
        if (iconContainer != null) {
            iconContainer.setBackgroundResource(
                    highlighted
                            ? R.drawable.bg_setting_icon_container_accent
                            : R.drawable.bg_setting_icon_container
            );
        }
        if (iconView != null) {
            iconView.setImageTintList(ColorStateList.valueOf(iconTint));
        }
    }

    private void showFeaturePlaceholder(@StringRes int featureTitleRes) {
        String featureTitle = getString(featureTitleRes);
        String message = getString(R.string.settings_feature_placeholder, featureTitle);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
