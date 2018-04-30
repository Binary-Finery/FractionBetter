package spencerstudios.com.fractionbetter.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import spencerstudios.com.fractionbetter.R;

public class RvEquivalentsAdapter extends RecyclerView.Adapter<RvEquivalentsAdapter.ItemHolder> {

    private List<String> equivalents;

    public RvEquivalentsAdapter(List<String> equivalents) {
        this.equivalents = equivalents;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        String[] tokens = equivalents.get(position).split("/");
        holder.tvFraction.setText(Html.fromHtml("<sup>" + tokens[0] + "</sup>/<sub>" + tokens[1] + "</sub>"));
    }

    @Override
    public int getItemCount() {
        return equivalents.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvFraction;

        ItemHolder(View itemView) {
            super(itemView);
            tvFraction = itemView.findViewById(R.id.tv_list_item_fraction);
        }
    }
}
