package com.knight.taiepizoo.ui.areadetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knight.taiepizoo.R;
import com.knight.taiepizoo.databinding.ComponentAreaInfoBinding;
import com.knight.taiepizoo.databinding.ComponentListHeaderBinding;
import com.knight.taiepizoo.databinding.ListItemPlantInfoBinding;
import com.knight.taiepizoo.model.AreaInfo;
import com.knight.taiepizoo.model.ClickEvent;
import com.knight.taiepizoo.model.ClickEventType;
import com.knight.taiepizoo.model.PlantInfo;
import com.knight.taiepizoo.ui.common.adapter.MixTypeRecycleViewAdapter;
import com.knight.taiepizoo.ui.common.adapter.MixTypeRecyclerViewListInfo;
import com.knight.taiepizoo.ui.common.viewholder.BaseRecyclerViewHolder;
import com.knight.taiepizoo.utils.ViewUtils;

import java.util.List;

public class AreaDetailViewAdapter extends MixTypeRecycleViewAdapter<AreaDetailViewAdapter.ViewType> {

    private AreaInfo areaInfo;
    private List<PlantInfo> plantList;

    public AreaDetailViewAdapter(Context context) {
        super(context, ViewType.class);
    }

    @Override
    protected void updateListInfo() {
        addItem(new MixTypeRecyclerViewListInfo().setViewType(ViewType.AREA_INFO).setData(areaInfo).setItemClickOberver(getClickEventObserver()));
        addItem(new MixTypeRecyclerViewListInfo().setViewType(ViewType.LIST_HEADER));
        if(plantList == null || plantList.size() <= 0) return;
        for (PlantInfo info : plantList) {
            addItem(new MixTypeRecyclerViewListInfo().setViewType(ViewType.LIST_ITEM).setData(info).setItemClickOberver(getClickEventObserver()));
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, ViewType viewType) {
        LayoutInflater layoutInflater =
            LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case AREA_INFO: {
                ComponentAreaInfoBinding itemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.component_area_info, parent, false);
                return new AreaInfoViewHolder(itemBinding);
            }
            case LIST_HEADER: {
                ComponentListHeaderBinding itemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.component_list_header, parent, false);
                return new ListHeaderViewHolder(itemBinding);
            }
            case LIST_ITEM: {
                ListItemPlantInfoBinding itemBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.list_item_plant_info, parent, false);
                return new PlantInfoViewHolder(itemBinding);
            }
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        switch (getViewType(position)) {
            case AREA_INFO:
                ((AreaInfoViewHolder) holder).bind((AreaInfo) getData(position));
                break;
            case LIST_ITEM:
                ((PlantInfoViewHolder) holder).bind((PlantInfo) getData(position));
                break;
        }
    }

    public enum ViewType {
        AREA_INFO,
        LIST_HEADER,
        LIST_ITEM
    }

    public void setAreaInfo(AreaInfo info) {
        areaInfo = info;
        updateView(true);
    }

    public void setPlantList(List<PlantInfo> plantList) {
        if (plantList == null) return;
        this.plantList = plantList;
        updateView(true);
    }

    private class AreaInfoViewHolder extends BaseRecyclerViewHolder<AreaInfo, ComponentAreaInfoBinding> {
        public AreaInfoViewHolder(ComponentAreaInfoBinding itemBinding) {
            super(itemBinding);
        }

        @Override
        public void bind(AreaInfo data) {
            super.bind(data);
            ViewUtils.loadImage(getItemBinding().imgArea, data.getImgUrl());
            getItemBinding().textInfo.setText(data.getInfo());

            getItemBinding().textMemo.setText(!TextUtils.isEmpty(data.getMemo()) ? data.getMemo() : getContext().getString(R.string.no_area_memo));
            getItemBinding().textCategory.setText(data.getCategory());
            getItemBinding().textOpenUrl.setOnClickListener(view -> setClickEvent(new ClickEvent(ClickEventType.OPEN_URL, data.getUrl())));
            if (TextUtils.isEmpty(data.getUrl()))
                getItemBinding().textOpenUrl.setVisibility(View.GONE);
        }
    }

    private class ListHeaderViewHolder extends BaseRecyclerViewHolder<String, ComponentListHeaderBinding> {
        public ListHeaderViewHolder(ComponentListHeaderBinding itemBinding) {
            super(itemBinding);
        }
    }

    private class PlantInfoViewHolder extends BaseRecyclerViewHolder<PlantInfo, ListItemPlantInfoBinding> {
        public PlantInfoViewHolder(ListItemPlantInfoBinding itemBinding) {
            super(itemBinding);
        }

        @Override
        public void bind(PlantInfo data) {
            super.bind(data);
            ViewUtils.loadImage(getItemBinding().imgPlant, data.getImgUrl());
            getItemBinding().textTitle.setText(data.getName());
            getItemBinding().textSubTitle.setText(data.getAlsoKnown());
            getItemBinding().rootView.setOnClickListener(this);
        }

        @Override
        protected ClickEvent getClickEventInfo(View view, int position) {
            return new ClickEvent(ClickEventType.LIST_ITEM_CLICK, getData());
        }
    }
}
