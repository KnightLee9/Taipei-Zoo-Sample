package com.knight.taiepizoo.ui.common.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.knight.taiepizoo.model.ClickEvent;
import com.knight.taiepizoo.ui.common.viewholder.BaseRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;



abstract public class MixTypeRecycleViewAdapter<VT extends Enum<VT>> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MutableLiveData<ClickEvent> clickEventObserver ;
    private final Class<VT> viewTypeClass;
    private ArrayList<MixTypeRecyclerViewListInfo> mListInfo;
    private boolean mClickable = true;

    abstract protected void updateListInfo();

    private Context mContext;
    private int mCurrentPosition;

    abstract public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, VT viewType);


    public MixTypeRecycleViewAdapter(Context context, Class<VT> viewTypeClass) {
        mContext = context;
        this.viewTypeClass = viewTypeClass;
        updateView(true);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VT type = mapViewTypeFromInt(viewType);
        if (type == null) throw new RuntimeException("invalid view type: " + viewType);
        return onCreateViewHolder(parent, type);
    }

    private VT mapViewTypeFromInt(int intType) {
        if (intType < 0 || intType >= viewTypeClass.getEnumConstants().length) return null;

        return viewTypeClass.getEnumConstants()[intType];
    }


    public VT getViewType(int position) {
        int type = getItemViewType(position);
        return mapViewTypeFromInt(type);
    }

    public Context getContext() {
        return mContext;
    }

    public void setClickable(boolean isClickable) {
        mClickable = isClickable;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BaseRecyclerViewHolder) {
            MutableLiveData<ClickEvent> observer = getItemClickObserver(position);
            if(observer != null) {
                ((BaseRecyclerViewHolder) holder).itemClickObserver(observer);
            }
        }
        setCurrentPosition(position);
    }

    @Override

    public final int getItemViewType(int position) {
        if (mListInfo == null || position >= mListInfo.size()) return -1;
        return mListInfo.get(position).getViewType().ordinal();
    }

    public boolean isClickable() {
        return mClickable;
    }

    public void updateView(boolean isDataChange) {
        if (isDataChange) {
            initDataListInfo();
            updateListInfo();
        }
        notifyDataSetChanged();
    }

    @Override

    public int getItemCount() {
        return mListInfo == null ? 0 : mListInfo.size();
    }

    int getOrgIndex(int position) {
        if (mListInfo == null || position >= mListInfo.size())
            return -1;
        return mListInfo.get(position).getOrgIndex();
    }

    private void initDataListInfo() {
        if (mListInfo == null) {
            mListInfo = new ArrayList<>();
        }
        mListInfo.clear();
    }

    protected void addItem(MixTypeRecyclerViewListInfo item) {
        if (mListInfo == null) {
            mListInfo = new ArrayList<>();
        }
        mListInfo.add(item);
    }

    protected MixTypeRecyclerViewListInfo getItem(int position) {
        if (position >= getItemCount()) {
            return null;
        }
        return mListInfo.get(position);
    }

    List<MixTypeRecyclerViewListInfo> getItemList() {
        return mListInfo;
    }


    private void setCurrentPosition(int position) {
        mCurrentPosition = position;
    }

    int getCurrentPosition() {
        return mCurrentPosition;
    }

    protected void resetList() {
        initDataListInfo();
    }

    public <T extends Enum<T>> void enumValues(Class<T> enumType) {
        for (T c : enumType.getEnumConstants()) {
            System.out.println(c.name());
        }
    }

    public MutableLiveData<ClickEvent> getItemClickObserver(int position) {
        if (mListInfo == null || position >= mListInfo.size()) return null;
        return mListInfo.get(position).getItemClickOberver();
    }

    public Object getData(int position) {
        if (mListInfo == null || position >= mListInfo.size())
            return null;
        return mListInfo.get(position).getData();
    }
    final public MutableLiveData<ClickEvent> getClickEventObserver() {
        if(clickEventObserver == null) {
            clickEventObserver = new MutableLiveData<>();

        }
        return clickEventObserver;
    }
}
