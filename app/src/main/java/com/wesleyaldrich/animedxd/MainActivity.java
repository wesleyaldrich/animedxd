package com.wesleyaldrich.animedxd;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wesleyaldrich.animedxd.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        if (intent != null) {
            String username = intent.getStringExtra("EXTRA_USERNAME");
            if (username != null) {
                TextView userNameTextView = findViewById(R.id.userNameTextView);
                if (userNameTextView != null) {
                    userNameTextView.setText(username);
                }
            }
        }

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
            binding.bottomNavigationView.setSelectedItemId(R.id.home);
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home) {
                loadFragment(new HomeFragment());
                return true;
            } else if (itemId == R.id.list) {
                loadFragment(new ListFragment());
                return true;
            } else if (itemId == R.id.about) {
                loadFragment(new AboutFragment());
                return true;
            }
            return false;
        });

        binding.menuHamburger.setOnClickListener(view -> {
            showLogoutPopup();
        });
    }

    private void showLogoutPopup() {
        View popupView = getLayoutInflater().inflate(R.layout.pop_up_logout, null);

        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        TextView btnLogout = popupView.findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(v -> {
            performLogout();
            popupWindow.dismiss();
        });

        popupWindow.setOnDismissListener(() -> {
            binding.menuHamburger.setColorFilter(null);
        });

        binding.menuHamburger.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.blue));
        popupWindow.showAsDropDown(binding.menuHamburger, 0, 0);
    }

    private void performLogout() {
        Toast.makeText(this, "Logging out...", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}