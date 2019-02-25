package jp.tomiyama.noir.qiitasample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<ItemEntity> {

    List<ItemEntity> mItemEntities;

    public ItemAdapter(Context context, int layoutResourceId, List<ItemEntity> objects) {
        super(context, layoutResourceId, objects);

        mItemEntities = objects;
    }

    @Override
    public int getCount() {
        return mItemEntities.size();
    }

    @Override
    public ItemEntity getItem(int position) {
        return mItemEntities.get(position);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemEntity itemEntity = getItem(position);

        if(itemEntity != null){
            viewHolder.titleText.setText(itemEntity.getTitle());
            viewHolder.bodyText.setText(itemEntity.getBody());
        }

        return convertView;
    }

    public static class ViewHolder{
        TextView titleText;
        TextView bodyText;

        public ViewHolder(View view){
            titleText = view.findViewById(R.id.item_title);
            bodyText = view.findViewById(R.id.item_body);
        }
    }
}
