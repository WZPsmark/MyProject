package app.zhujie.dataBind;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.zhujie.R;

/**
 * Created by smark on 2017/3/27.
 */

public class EmployeeAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private static final int ITEM_VIEW_TYPE_ON = 1;
    private static final int ITEM_VIEW_TYPE_OFF = 2;

    private final LayoutInflater mLayoutInflater;

    private OnItemClickListener mListener;
    private List<Employee> mEmployeeList;

    public interface OnItemClickListener {
        void onEmployeeClick(Employee employee);
    }

    public EmployeeAdapter(Context context) {
        mLayoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mEmployeeList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        final Employee employee = mEmployeeList.get(position);
        if (employee.isFired.get()) {
            return ITEM_VIEW_TYPE_OFF;
        } else {
            return ITEM_VIEW_TYPE_ON;
        }
    }


    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if (viewType == ITEM_VIEW_TYPE_ON) {
            binding = DataBindingUtil.inflate(mLayoutInflater,
                    R.layout.item_employee, parent, false);
        } else {
            binding = DataBindingUtil.inflate(mLayoutInflater,
                    R.layout.item_employee_off, parent, false);
        }
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        final Employee employee = mEmployeeList.get(position);
        holder.getBinding().setVariable(app.zhujie.BR.item, employee);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onEmployeeClick(employee);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mEmployeeList.size();
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void addAll(List<Employee> employees) {
        mEmployeeList.addAll(employees);
    }

    Random mRandom = new Random(System.currentTimeMillis());

    public void add(Employee employee) {
        int position = mRandom.nextInt(mEmployeeList.size() + 1);
        mEmployeeList.add(position, employee);
        notifyItemInserted(position);
    }

    public void remove() {
        if (mEmployeeList.size() == 0) {
            return;
        }
        int position = mRandom.nextInt(mEmployeeList.size());
        mEmployeeList.remove(position);
        notifyItemRemoved(position);
    }
}
