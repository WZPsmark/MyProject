package app.zhujie.dataBind;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by smark on 2017/3/27.
 */

public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder{


    private T mBinding;
    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        mBinding=binding;
    }

    public T getBinding(){
        return mBinding;
    }
}
