package com.wesleyaldrich.animedxd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment {

    private FragmentManager fragmentManager;
    private MaterialButton btnNews;
    private MaterialButton btnManga;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnNews = view.findViewById(R.id.btnNews);
        btnManga = view.findViewById(R.id.btnManga);

        fragmentManager = getChildFragmentManager();

        if (savedInstanceState == null) {
            loadFragment(new NewsFragment());
            // Atur tampilan tombol News sebagai terpilih secara default
            btnNews.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.blue));
            btnNews.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
            btnManga.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.white));
            btnManga.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey));
        }

        // Atur listener untuk tombol News
        btnNews.setOnClickListener(v -> {
            loadFragment(new NewsFragment());
            // Atur tampilan tombol News (aktif) dan Manga (tidak aktif)
            btnNews.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.blue));
            btnNews.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
            btnManga.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.white));
            btnManga.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey));
        });

        // Atur listener untuk tombol Manga
        btnManga.setOnClickListener(v -> {
            loadFragment(new MangaFragment());
            // ---- PERBAIKAN DI SINI ----
            // Atur tampilan tombol News (tidak aktif)
            btnNews.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.white));
            btnNews.setTextColor(ContextCompat.getColor(requireContext(), R.color.grey));
            // Atur tampilan tombol Manga (aktif)
            btnManga.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.blue));
            btnManga.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
            // -----------------------------
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.home_content_container, fragment);
        transaction.commit();
    }
}