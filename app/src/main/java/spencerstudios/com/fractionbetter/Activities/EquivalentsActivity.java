package spencerstudios.com.fractionbetter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import spencerstudios.com.bungeelib.Bungee;
import spencerstudios.com.fractionbetter.Adapters.RvEquivalentsAdapter;
import spencerstudios.com.fractionbetter.Constants.RvItemColorBackgroundFactory;
import spencerstudios.com.fractionbetter.R;
import spencerstudios.com.fractionbetter.Utilities.EquivalentsBuilder;

public class EquivalentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equivalents);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        final TextView tvInfo = findViewById(R.id.tv_info);
        final ImageView ivBack = findViewById(R.id.iv_back);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();
        int parts = intent.getIntExtra("parts", 3);
        int total = intent.getIntExtra("total", 4);

        tvInfo.setText(Html.fromHtml("Equivalent fractions for&emsp;" + "<sup>" + parts + "</sup>/<sub>" + total + "</sub>"));

        List<String> equivalents = EquivalentsBuilder.build(parts, total);

        RecyclerView rv = findViewById(R.id.rv);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);
        rv.hasFixedSize();
        RvEquivalentsAdapter adapter = new RvEquivalentsAdapter(equivalents, RvItemColorBackgroundFactory.rvItemColors);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.slideLeft(EquivalentsActivity.this);
    }
}
