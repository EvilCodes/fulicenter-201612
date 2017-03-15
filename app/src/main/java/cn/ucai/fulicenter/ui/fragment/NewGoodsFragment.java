package cn.ucai.fulicenter.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;
import cn.ucai.fulicenter.model.net.INewGoodsModel;
import cn.ucai.fulicenter.model.net.NewGoodsModel;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.model.utils.L;

/**
 * Created by clawpo on 2017/3/15.
 */

public class NewGoodsFragment extends Fragment {
    private static final String TAG = NewGoodsFragment.class.getSimpleName();

    @BindView(R.id.rv_goods)
    RecyclerView mRvGoods;
    Unbinder bind;
    INewGoodsModel model;
    int pageId = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_good, container, false);
        bind = ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model = new NewGoodsModel();
        initData();
    }

    private void initData() {
        model.loadData(getContext(), pageId, new OnCompleteListener<NewGoodsBean[]>() {
            @Override
            public void onSuccess(NewGoodsBean[] result) {
                L.e(TAG,"initData,result = "+result);
                if (result!=null && result.length>0){
                    L.e(TAG,"initData,result.length = "+result.length);
                }
            }

            @Override
            public void onError(String error) {
                L.e(TAG,"initData,error = "+error);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (bind!=null){
            bind.unbind();
        }
    }
}