package spencerstudios.com.fractionbetter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import java.util.List;

import spencerstudios.com.fractionbetter.Adapters.RvEquivalentsAdapter;
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

        Intent intent = getIntent();
        int parts = intent.getIntExtra("parts", 3);
        int total = intent.getIntExtra("total", 4);

        tvInfo.setText(Html.fromHtml("Equivalent fractions for&emsp;" + "<sup>" + parts + "</sup>/<sub>" + total + "</sub>"));

        List<String> equivalents = EquivalentsBuilder.build(parts, total);

        RecyclerView rv = findViewById(R.id.rv);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);
        rv.hasFixedSize();
        RvEquivalentsAdapter adapter = new RvEquivalentsAdapter(equivalents);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
