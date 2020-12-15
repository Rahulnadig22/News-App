package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit.R;

public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.NewsCategoryHolder> {
    private final Context context;
    private final String[] categoryNames;
    private int selectedPosition = -1;
    private CategoryClickListener listener;

    public NewsCategoryAdapter(Context context){
        this.context = context;
        this.categoryNames = context.getResources().getStringArray(R.array.news_category);
    }

    public void setListener(CategoryClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsCategoryHolder(LayoutInflater.from(context).inflate(R.layout.cell_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsCategoryHolder holder, int position) {
        holder.mTextView.setText(categoryNames[position]);
        if(selectedPosition == position){
            holder.mLlRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bg_category_select,null));
            holder.mTextView.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.black,null));
        }else {
            holder.mLlRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bg_category_unselect,null));
            holder.mTextView.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.black,null));
        }

        holder.mLlRoot.setOnClickListener(v -> {
            selectedPosition = position;
            notifyDataSetChanged();
            if(listener!=null){
                listener.onCategoryClicked(categoryNames[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryNames.length;
    }

    static class NewsCategoryHolder extends RecyclerView.ViewHolder {
        private final LinearLayout mLlRoot;
        private final TextView mTextView;
        public NewsCategoryHolder(@NonNull View itemView) {
            super(itemView);
            mLlRoot = itemView.findViewById(R.id.ll_root_category);
            mTextView = itemView.findViewById(R.id.tv_category_name);

        }
    }

    public interface CategoryClickListener{
        void onCategoryClicked(String category);

    }
}
