/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gersion.library.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gersion.library.typepool.MultiTypePoolList;
import com.gersion.library.typepool.TypePool;
import com.gersion.library.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author drakeet
 */
public abstract class MultiTypeAdapter <T , K >extends RecyclerView.Adapter<ViewHolder> {

    private static final String TAG = "MultiTypeAdapter";

    protected  @NonNull List<T> items;
    protected List<T> mSelectionList = new ArrayList<>();
    private @NonNull
    TypePool typePool;
    protected @Nullable LayoutInflater inflater;
    private @NonNull final ArrayMap<Class,Integer> mTypeArrayMap = new ArrayMap<>();


    /**
     * Constructs a MultiTypeAdapter with an empty items list.
     */
    public MultiTypeAdapter() {
        this(new ArrayList<T>());
    }


    /**
     * Constructs a MultiTypeAdapter with a items list.
     *
     * @param items the items list
     */
    public MultiTypeAdapter(@NonNull List<T> items) {
        this(items, new MultiTypePoolList());
    }


    /**
     * Constructs a MultiTypeAdapter with a items list and a TypePool.
     *
     * @param items the items list
     * @param pool the type pool
     */
    public MultiTypeAdapter(@NonNull List<T> items, @NonNull TypePool pool) {
        this.items = items;
        this.typePool = pool;
    }

    public void register(Class clazz,int layoutId){
        typePool.register(clazz,layoutId);
    }


    /**
     * Sets and updates the items atomically and safely. It is recommended to use this method
     * to update the items with a new wrapper list or consider using {@link CopyOnWriteArrayList}.
     *
     * <p>Note: If you want to refresh the list views after setting items, you should
     * call {@link RecyclerView.Adapter#notifyDataSetChanged()} by yourself.</p>
     *
     * @param items the new items list
     * @since v2.4.1
     */
    public void setItems(@NonNull List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    @NonNull
    public List<?> getItems() {
        return items;
    }


    /**
     * Set the TypePool to hold the types and view binders.
     *
     * @param typePool the TypePool implementation
     */
    public void setTypePool(@NonNull TypePool typePool) {
        this.typePool = typePool;
    }


    @NonNull
    public TypePool getTypePool() {
        return typePool;
    }


    @Override
    public final int getItemViewType(int position) {
        Object item = items.get(position);
        int itemType = typePool.getItemType(item.getClass());
        return itemType;
    }


    @Override
    public final ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        ViewHolder viewHolder = typePool.getViewHolder(parent, indexViewType);
        return viewHolder;
    }


    /**
     * This method is deprecated and unused. You should not call this method.
     * <p>
     * If you need to call the binding, use {@link RecyclerView.Adapter#onBindViewHolder(ViewHolder,
     * int, List)} instead.
     * </p>
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     * @throws IllegalAccessError By default.
     * @deprecated Call {@link RecyclerView.Adapter#onBindViewHolder(ViewHolder, int, List)}
     * instead.
     */
    @Override
    public final void onBindViewHolder(ViewHolder holder, int position) {
        convert((BaseViewHolder) holder, items.get(position));
    }


//    @Override @SuppressWarnings("unchecked")
//    public final void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
//        Object item = items.get(position);
////        ItemViewBinder binder = typePool.getItemViewBinders().get(holder.getItemViewType());
////        binder.onBindViewHolder(holder, item, payloads);
//
////        convert(holder, getItem(position));
//    }

    protected abstract void convert(BaseViewHolder helper, T item);

    @Override
    public final int getItemCount() {
        return items.size();
    }


}
