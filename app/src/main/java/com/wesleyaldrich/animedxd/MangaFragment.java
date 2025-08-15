package com.wesleyaldrich.animedxd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MangaFragment extends Fragment {

    private RecyclerView recyclerView;
    private MangaAdapter adapter;
    private List<Manga> mangaList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manga, container, false);
        recyclerView = view.findViewById(R.id.recycler_manga);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3)); // 3 kolom

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        int spacing = Math.round(12 * getResources().getDisplayMetrics().density); // 12dp → px
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(3, spacing));


        mangaList = new ArrayList<>();
        mangaList.add(new Manga("SAKAMOTO DAYS", "Taro Sakamoto, once a legendary assassin, now lives peacefully as a chubby shopkeeper, husband, and father. But when danger returns, can he protect his family? A wild action-comedy begins!", R.drawable.manga1));
        mangaList.add(new Manga("Kagurabachi", "Chihiro trains under his legendary swordsmith father, dreaming of following in his footsteps. But tragedy strikes, leaving Chihiro with only one goal—revenge. Intense sword-fighting action begins!", R.drawable.manga4));
        mangaList.add(new Manga("Blue Box", "Taiki Inomata, a badminton player at Eimei High, secretly loves Chinatsu Kano, a senior on the basketball team. One spring morning changes everything… A fresh story of love, youth, and sports begins!", R.drawable.manga7));
        mangaList.add(new Manga("Ichi the Witch", "In a world where witches hunt magic, a fierce battle rages between the magic king and the strongest witch. Then appears Ichi—a mere hunter with the power to upend everything. A magic-hunting fantasy begins!", R.drawable.manga2));
        mangaList.add(new Manga("Centuria", "Julian sneaks aboard a slave ship bound for a new continent with a hundred slaves seeking freedom—until fate intervenes. An intense dark fantasy adventure begins!", R.drawable.manga5));
        mangaList.add(new Manga("Otr of the Flame", "In a world frozen by the Ice Kingdom, kind-hearted Otr keeps the fortress fires burning. When a brutal attack threatens everything, can he awaken the power to fight back? A fiery hero’s journey begins!", R.drawable.manga8));
        mangaList.add(new Manga("RuriDragon", "This story follows a young dragon girl who tries her best—but often ends up being lazy again. With a mix of cute, funny, and relatable moments, watch her take on life at her own pace. A charming new series begins!", R.drawable.manga3));
        mangaList.add(new Manga("Drama Queen", "Aliens now live among humans in Japan. Nomamoto, mistreated by her alien boss, meets Kitami, whose family was killed by aliens. A bond forms—until Kitami does the unthinkable. A tale of pain, prejudice, and unexpected choices.      ", R.drawable.manga6));
        mangaList.add(new Manga("Hima-Ten!", "High school home-ec whiz Tenichi cleans houses for extra cash. When Himari, a cosmetics brand CEO, joins his class, romantic comedy chaos begins!", R.drawable.manga9));

        adapter = new MangaAdapter(mangaList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
