package cn.landi.playandroid.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.landi.playandroid.R;
import cn.landi.playandroid.adapter.BlueToothDevicesAdapter;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/8/18
 * @edit TODO
 */
public class BlueToothActivity extends AppCompatActivity implements BlueToothDevicesAdapter.IClickResultListener {

    @BindView(R.id.btn_search_bt)
    Button btnSearchBt;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_bond_info)
    TextView tvBondInfo;
    @BindView(R.id.recycler_bt_devices)
    RecyclerView recyclerBtDevices;
    @BindView(R.id.btn_print)
    Button btnPrint;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private BlueToothDevicesAdapter mDevicesAdapter;
    private List<BluetoothDevice> mDevices = new ArrayList<>();

    private ConnectThread connectThread;
    private ConnectedThread connectedThread;

    private static final UUID MY_UUID = UUID.fromString("e8232832-5105-4838-a432-147dc1315f2d");

    protected BroadcastReceiver mBtReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (null == intent) {
                return;
            }
            String action = intent.getAction();
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                Log.d("chenhui", "启动蓝牙搜索...");
                tvInfo.setText("启动蓝牙搜索...");
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.d("chenhui", "蓝牙搜索完成");
                tvInfo.setText("蓝牙搜索完成");
            } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                Log.d("chenhui", "蓝牙状态变化...");
                tvInfo.setText("蓝牙状态变化...");
                if (bluetoothAdapter.getState() == BluetoothAdapter.STATE_OFF) {
                    Log.d("chenhui", "蓝牙被关闭");
                    tvInfo.append("蓝牙被关闭");
                } else {
                    Log.d("chenhui", "蓝牙被打开");
                    tvInfo.append("蓝牙被打开");
                }

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                Log.d("chenhui", "搜索到设备：");
                tvInfo.setText("搜索到设备：");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (!mDevices.contains(device)) {
                    mDevices.add(device);
                    mDevicesAdapter.notifyDataSetChanged();
                }
                Log.d("chenhui", "设备信息：" + device.getAddress() + "," + device.getName());
            } else if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                Log.d("chenhui", "绑定状态变化...");
                tvInfo.setText("绑定状态变化...");
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                switch (device.getBondState()) {
                    case BluetoothDevice.BOND_BONDING://正在配对
                        Log.d("BlueToothTestActivity", "正在配对......");
                        tvInfo.append("正在配对......");
                        break;
                    case BluetoothDevice.BOND_BONDED://配对结束
                        Log.d("BlueToothTestActivity", "完成配对");
                        tvInfo.append("完成配对");
                        break;
                    case BluetoothDevice.BOND_NONE://取消配对/未配对
                        Log.d("BlueToothTestActivity", "取消配对");
                        tvInfo.append("取消配对");
                    default:
                        break;
                }

            } else if ("android.bluetooth.device.action.PAIRING_REQUEST".equals(action)) {
                Log.d("chenhui", "android.bluetooth.device.action.PAIRING_REQUEST");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);

        mDevicesAdapter = new BlueToothDevicesAdapter(mDevices, this);
        recyclerBtDevices.setLayoutManager(new LinearLayoutManager(this));
        recyclerBtDevices.setAdapter(mDevicesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        //start discovery
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        //finish discovery
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        //bluetooth status change
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        //found device
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        //bond status change
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        //pairing device
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");

        registerReceiver(mBtReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBtReceiver);
    }

    @OnClick({R.id.btn_search_bt, R.id.btn_print})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_search_bt:
                tvInfo.setText("");
                mDevices.clear();
                mDevicesAdapter.notifyDataSetChanged();

                if (!bluetoothAdapter.isEnabled()) {
                    tvInfo.setText("蓝牙未打开，请打开蓝牙");
                    return;
                }

                tvInfo.setText("蓝牙已打开，开始搜索蓝牙设备");
                bluetoothAdapter.startDiscovery();
                break;
            case R.id.btn_print:
                printTest();
                break;
        }
    }

    private void printTest() {
        Log.d("chenhui", "printTest");
        if (connectedThread != null) {
            byte[] result = new byte[2];
            result[0] = 27;
            result[1] = 64;

            Log.d("chenhui", "初始化打印机...");
            connectedThread.write(result);

            try {
                Log.d("chenhui", "打印文字...");
                connectedThread.write("测试打印\n".getBytes("gb2312"));
                connectedThread.write("测试打印\n".getBytes("gb2312"));
                connectedThread.write("测试打印\n".getBytes("gb2312"));
                connectedThread.write("测试打印\n".getBytes("gb2312"));

                result = new byte[4];
                result[0] = 29;
                result[1] = 86;
                result[2] = 66;
                result[3] = 0;
                connectedThread.write(result);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                Log.d("chenhui", "UnsupportedEncodingException");
            }
        }
    }

    @Override
    public void onClick(Object item) {
        bluetoothAdapter.cancelDiscovery();

        BluetoothDevice device = (BluetoothDevice) item;
        if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
            Log.d("chenhui", "已配对，启动连接");
            tvInfo.setText("已配对，启动连接");
            connectBlt(device);
            Set<BluetoothDevice> deviceSet = bluetoothAdapter.getBondedDevices();
            if (deviceSet == null || deviceSet.isEmpty()) {
                Log.d("chenhui", "deviceSet is null");
                return;
            }
            for (BluetoothDevice bluetoothDevice : deviceSet) {
                Log.d("chenhui", "bluetoothDevice:" + bluetoothDevice.getName() + "," + bluetoothDevice.getAddress() + ","
                        + bluetoothDevice.getBondState());
            }

            if (connectThread != null) {
                connectThread.cancel();
                connectThread = null;
            }
            connectThread = new ConnectThread(device);
            connectThread.start();

        } else {
            tvInfo.setText("未配对，启动配对");
            Log.d("chenhui", "未配对，启动配对");
            try {
                Method createBondMethod = BluetoothDevice.class.getMethod("createBond");
                createBondMethod.invoke(device);
            } catch (Exception e) {
                e.printStackTrace();
                tvInfo.setText("蓝牙绑定失败，请重试");
                Log.d("chenhui", "蓝牙绑定失败，请重试");
            }
        }
    }

    /***
     * 配对成功连接蓝牙
     * @param bluetoothDevice
     */

    private void connectBlt(BluetoothDevice bluetoothDevice) {
        if (null != mDevicesAdapter) {
            mDevicesAdapter.setConnectedDeviceAddress(bluetoothDevice.getAddress());
        }
        mDevicesAdapter.notifyDataSetChanged();
    }

    private class ConnectThread extends Thread {

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
        private String mSocketType;

        public ConnectThread(BluetoothDevice device) {
            mmDevice = device;
            BluetoothSocket tmp = null;

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
                Log.d("chenhui", "创建socket启动，启动连接...");
            } catch (Exception e) {
                Log.e("chenhui", "Socket Type: " + mSocketType + "create() failed", e);
            }
            mmSocket = tmp;
        }

        public void run() {
            try {
                Log.i("chenhui", "BEGIN mConnectThread SocketType:" + mSocketType);
                setName("ConnectThread" + mSocketType);

                // Always cancel discovery because it will slow down a connection
                bluetoothAdapter.cancelDiscovery();

                // Make a connection to the BluetoothSocket
                try {
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
                    mmSocket.connect();
                    Log.d("chenhui", "连接成功...");
                } catch (Exception e) {
                    // Close the socket
                    try {
                        mmSocket.close();
                    } catch (IOException e2) {
                        Log.e("chenhui", "unable to close() " + mSocketType
                                + " socket during connection failure", e2);
                    }
                    return;
                }

                if (connectedThread != null) {
                    connectedThread.cancel();
                    connectedThread = null;
                }
                connectedThread = new ConnectedThread(mmSocket, "");
                connectedThread.start();
                // Start the connected thread

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (Exception e) {
                Log.e("", "close() of connect " + mSocketType
                        + " socket failed", e);
            }
        }
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;

        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket, String socketType) {
            Log.d("chenhui", "create ConnectedThread: " + socketType);
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
                Log.d("chenhui", "成功获取输入输出流，输出流：" + tmpOut);

            } catch (Exception e) {
                Log.e("chenhui", "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            Log.i("chenhui", "BEGIN mConnectedThread");
            byte[] buffer = new byte[1024];
            int bytes;

            // Keep listening to the InputStream while connected
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // send data
                   Log.d("chenhui", "mmInStream read...");
                } catch (IOException e) {
                    Log.e("chenhui", "disconnected", e);
                    // Start the service over to restart listening mode
                    break;
                } catch (Exception e) {
                    Log.e("chenhui", "Exception:" + e.getMessage());
                    break;
                }
            }
        }

        /**
         * Write to the connected OutStream.
         *
         * @param buffer The bytes to write
         */
        public void write(byte[] buffer) {
            try {
                Log.d("chenhui", "发送数据...");
                mmOutStream.write(buffer);
            } catch (Exception e) {
                Log.e("chenhui", "Exception during write", e);
            }
        }

        /**
         * Write to the connected OutStream after sleep
         *
         * @param buffer The bytes to write
         */
        public void write(byte[] buffer, long sleepTime) {
            try {
                Thread.sleep(sleepTime);
                mmOutStream.write(buffer);
            } catch (Exception e) {
                Log.e("chenhui", "Exception during write", e);
            }
        }


        public void cancel() {
            try {
                mmSocket.close();
            } catch (Exception e) {
                Log.e("chenhui", "close() of connect socket failed", e);
            }
        }
    }


}
