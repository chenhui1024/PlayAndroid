package cn.landi.playandroid.adapter;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.landi.playandroid.R;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/18
 * @edit TODO
 */
public class BlueToothDevicesAdapter extends RecyclerView.Adapter<BlueToothDevicesAdapter.ViewHolder> {

    private List<BluetoothDevice> devices;
    private IClickResultListener listener;
    private String mConnectedDeviceAddress;

    public BlueToothDevicesAdapter(List<BluetoothDevice> devices, IClickResultListener listener) {
        this.devices = devices;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blue_tooth_device, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                if (listener != null) {
                    listener.onClick(devices.get(pos));
                }
            }
        });
        return viewHolder;
    }

    public void setConnectedDeviceAddress(String address) {
        mConnectedDeviceAddress = address;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BluetoothDevice device = devices.get(position);

        String btName = TextUtils.isEmpty(device.getName()) ? "未知设备" : device.getName();
        holder.tvBtName.setText(btName);

        String address = TextUtils.isEmpty(device.getAddress()) ? "未知地址" : device.getAddress();
        holder.tvBtAddress.setText(address);

        int state = device.getBondState();
        if (state == BluetoothDevice.BOND_BONDED) {
            if (TextUtils.equals(mConnectedDeviceAddress, address)) {
                holder.tvState.setText("已连接");
            } else {
                holder.tvState.setText("已配对");
            }
        } else {
            holder.tvState.setText("未配对");
        }

        holder.setPos(position);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvBtName;
        public TextView tvBtAddress;
        public TextView tvState;

        public ViewHolder(View itemView) {
            super(itemView);

            tvBtName = itemView.findViewById(R.id.tv_bt_name);
            tvBtAddress = itemView.findViewById(R.id.tv_bt_addresss);
            tvState = itemView.findViewById(R.id.tv_bond_state);
        }

        public void setPos(int pos) {
            itemView.setTag(pos);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            if (itemView != null) {
                itemView.setOnClickListener(listener);
            }
        }
    }

    public interface IClickResultListener {
        void onClick(Object item);
    }

}
