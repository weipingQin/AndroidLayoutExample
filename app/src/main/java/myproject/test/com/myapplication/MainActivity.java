package myproject.test.com.myapplication;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private List<String> mDataList;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //通过getWindow().addFlags() 来设置透明
       // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)；
        initData();
        init();
    }

    private void initData(){
        mDataList = new ArrayList<>();
        for(int i = 0;i<10;i++){
            mDataList.add(String.valueOf(i));
        }
    }

    private void init(){
        mAdapter = new NewDeviceListAdpater(mDataList);
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_adddevice);
        FullyLinearManager linearLayoutManager = new FullyLinearManager(this,LinearLayoutManager.VERTICAL,true);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        //解决滑动冲突
        mRecyclerView.setHasFixedSize(true);
    }

    class NewDeviceListAdpater extends  RecyclerView.Adapter<NewDeviceListAdpater.NewDeviceViewHolder>{
        private List<String> mData;
        public NewDeviceListAdpater(List<String> list){
            this.mData = list;
        }

        @Override
        public NewDeviceListAdpater.NewDeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            NewDeviceViewHolder holder = new NewDeviceViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_list_item,parent,false));
            return holder;
        }

        @Override
        public void onBindViewHolder(NewDeviceListAdpater.NewDeviceViewHolder holder, int position) {
            holder.mtv.setText(mDataList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
        class NewDeviceViewHolder extends RecyclerView.ViewHolder{
            TextView mtv;
            public NewDeviceViewHolder(View view){
                super(view);
                mtv = (TextView)view.findViewById(R.id.tv_fragment_devicelist_item);
            }
        }
    }
}
