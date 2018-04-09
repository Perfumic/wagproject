package teddy.wagproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import teddy.wagproject.R;
import teddy.wagproject.model.StackExchangeItem;
import teddy.wagproject.utils.Utils;

/**
 * Created by teddy on 4/8/18.
 * Adapter for the list RecyclerView
 */

public class WagRecyclerViewAdapter extends
        Adapter<WagRecyclerViewAdapter.WagViewHolder> {

    private static final String TAG = "WagRecyclerViewAdapter";

    private List<StackExchangeItem> mList;
    private Context mContext;

    public WagRecyclerViewAdapter(Context context, List<StackExchangeItem> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public WagRecyclerViewAdapter.WagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.waglist_row,
                parent, false);

        return new WagViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WagRecyclerViewAdapter.WagViewHolder holder, int position) {
        StackExchangeItem item = mList.get(position);

        holder.username.setText(item.userName);
        holder.bronzeCount.setText(item.bronzeCount);
        holder.silverCount.setText(item.silverCount);
        holder.goldCount.setText(item.goldCount);

        File file = new File(Utils.getOfflineFileNameForUserId(mContext, item.userId));

        if (!file.exists()) {
            Picasso.with(mContext)
                    .load(item.gravatar)
                    .into(getTarget(item.userId));
        }

        Picasso.with(mContext)
                .load(file)
                .placeholder(R.drawable.animated_loading)
                .error(R.drawable.ic_launcher_background)
                .into(holder.gravatar);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private Target getTarget(final String userId) {
        Target target = new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = new File(Utils.getOfflineFileNameForUserId(mContext, userId));
                        try {
                            file.createNewFile();
                            FileOutputStream os = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                            os.flush();
                            os.close();
                        } catch (IOException e) {
                            Log.d(TAG, "Exception loading gravatar : " + e.getLocalizedMessage());
                        }
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }

    public class WagViewHolder extends RecyclerView.ViewHolder {
        public ImageView gravatar;
        public TextView username, bronzeCount, silverCount, goldCount;

        public WagViewHolder(View view) {
            super(view);
            gravatar = view.findViewById(R.id.gravatar);
            username = view.findViewById(R.id.username);
            bronzeCount = view.findViewById(R.id.bronze_dynamic);
            silverCount = view.findViewById(R.id.silver_dynamic);
            goldCount = view.findViewById(R.id.gold_dynamic);
        }
    }
}
