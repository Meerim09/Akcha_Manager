package com.meerim.akchamanager.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.meerim.akchamanager.adapters.ArticleAdapter;
import com.meerim.akchamanager.models.ArticleModel;

import java.util.ArrayList;
import java.util.List;

public class ArticleListActivity extends AppCompatActivity {
    private com.meerim.akchamanager.databinding.ActivityArticleListBinding binding;
    private ArrayList<ArticleModel> models = new ArrayList<>();
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.meerim.akchamanager.databinding.ActivityArticleListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<ArticleModel> models = new ArrayList<>();

        models.add(new ArticleModel("Акча деген эмне?"));
        models.add(new ArticleModel("Акча үнөмдөөнүн жеңил 7 ыкмасы"));
        models.add(new ArticleModel("Кредит деген эмне? Кандай колдонуу керек?"));
        models.add(new ArticleModel("Улуттук банктын максаттары жана милдеттери"));
        models.add(new ArticleModel("Инвестиция деген эмне?"));
        models.add(new ArticleModel("10 каржылык адаттар"));
        models.add(new ArticleModel("Жаштар үчүн финансалык билимдин маанилүүлүгү"));

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArticleAdapter adapter = new ArticleAdapter(models, this);
        binding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
