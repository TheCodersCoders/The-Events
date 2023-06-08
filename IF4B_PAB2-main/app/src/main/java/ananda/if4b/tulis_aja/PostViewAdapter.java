package ananda.if4b.tulis_aja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ananda.if4b.tulis_aja.databinding.PostItemBinding;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {
    private List<Post> data = new ArrayList();
    private OnItemLongClickListener onItemLongClickListener;
    private Context context;

    public void setData (List<Post> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public PostViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(PostItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        Post post = data.get(pos);
        holder.postItemBinding.tvContent.setText(post.getContent());
        holder.postItemBinding.tvJudul.setText(post.getJudul());
        holder.postItemBinding.tvLokasi.setText(post.getLokasi());
        Glide.with(holder.itemView).load(post.getFoto()).into(holder.postItemBinding.ivFoto);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickListener.onItemLongClick(v, pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private PostItemBinding postItemBinding;

        public ViewHolder(@NonNull PostItemBinding itemView) {
            super(itemView.getRoot());
            postItemBinding = itemView;
        }
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }
}
