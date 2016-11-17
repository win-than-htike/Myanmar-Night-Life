package myanmarnightlife.lower.team1.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.data.RecommendMenu;

/**
 * Created by winthanhtike on 11/16/16.
 */
public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.MenuViewHolder> {

    private List<RecommendMenu> menuList;

    public MenuRVAdapter(List<RecommendMenu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(MyanmarNightLifeApp.getContext()).inflate(R.layout.card_menu,parent,false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        holder.tvMenu.setText("- " + menuList.get(position).getMenuName());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_menu)
        TextView tvMenu;

        public MenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
