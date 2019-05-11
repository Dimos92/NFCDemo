package com.dong.nfcsecurity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.nfcsecurity.db.DecorationDBHelper;
import com.dong.nfcsecurity.db.model.Decoration;
import com.dong.nfcsecurity.utils.NfcUtils;
import com.dong.nfcsecurity.view.PairContentView;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DecorationDBHelper helper;

    private LinearLayout llDecoration;
    private LinearLayout llFactory;
    private LinearLayout llReseller;
    private LinearLayout llResult;
    private TextView tvHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new NfcUtils(this);
        initDB();
        initView();
    }

    private void initDB() {
        helper = new DecorationDBHelper(this);
        helper.createTable();
        helper.insertData();
    }

    private void initView() {
        llDecoration = $(R.id.ll_decoration);
        llFactory = $(R.id.ll_factory);
        llReseller = $(R.id.ll_resaler);
        llResult = $(R.id.ll_result);
        tvHint = $(R.id.tv_hint);
    }


    @Override
    protected void onResume() {
        super.onResume();
        NfcUtils.mNfcAdapter.enableForegroundDispatch(this, NfcUtils.mPendingIntent, NfcUtils.mIntentFilter, NfcUtils.mTechList);
    }

    @Override
    protected void onPause() {
        super.onPause();

        NfcUtils.mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: " + intent);
        //当该Activity接收到NFC标签时，运行该方法
        //调用工具方法，读取NFC数据
        try {
            String str = NfcUtils.readNFCFromTag(intent);
            Log.i(TAG, "readNFCFromTag == " + str);
            Decoration decoration = helper.selectDecoration(Integer.parseInt(str.replace("cn", "").trim()));
            if (decoration == null) {
                llResult.setVisibility(View.GONE);
                tvHint.setVisibility(View.VISIBLE);
                tvHint.setText("假货");
            } else {
                llResult.setVisibility(View.VISIBLE);
                tvHint.setVisibility(View.GONE);
                setContent(decoration);
            }
            Log.i(TAG, "onNewIntent: decoration == " + decoration);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setContent(Decoration decoration) {
        setDecorationView(decoration);
        Decoration.Factory factory = decoration.getFactory();
        setFactoryViews(factory);
        Decoration.Reseller reseller = decoration.getReseller();
        setResellerViews(reseller);

    }

    private void setResellerViews(Decoration.Reseller reseller) {
        llReseller.removeAllViews();
        llReseller.setVisibility(View.VISIBLE);
        PairContentView id = getPairContentView("经销商编号", reseller.getId());
        llReseller.addView(id);
        PairContentView name = getPairContentView("经销商名称", reseller.getName());
        llReseller.addView(name);
        PairContentView inDate = getPairContentView("接货日期", reseller.getInDate());
        llReseller.addView(inDate);
        PairContentView outDate = getPairContentView("出货日期", reseller.getOutDate());
        llReseller.addView(outDate);
    }

    private void setFactoryViews(Decoration.Factory factory) {
        llFactory.removeAllViews();
        llFactory.setVisibility(View.VISIBLE);
        PairContentView id = getPairContentView("厂商编号", factory.getId());
        llFactory.addView(id);
        PairContentView name = getPairContentView("厂商名称", factory.getName());
        llFactory.addView(name);
        PairContentView address = getPairContentView("厂商地址", factory.getAddress());
        llFactory.addView(address);
        PairContentView date = getPairContentView("出货时间", factory.getProductDate());
        llFactory.addView(date);
        PairContentView tel = getPairContentView("厂商电话", factory.getTelephone());
        llFactory.addView(tel);
    }

    private void setDecorationView(Decoration decoration) {
        llDecoration.removeAllViews();
        llDecoration.setVisibility(View.VISIBLE);
        PairContentView id = getPairContentView("化妆品编号", decoration.getId());
        llDecoration.addView(id);
        PairContentView name = getPairContentView("化妆品名称", decoration.getName());
        llDecoration.addView(name);
        PairContentView date = getPairContentView("化妆品生产日期", decoration.getProduceDate());
        llDecoration.addView(date);
        PairContentView expiration = getPairContentView("化妆品保质期", decoration.getExpiration());
        llDecoration.addView(expiration);
    }

    @NonNull
    private PairContentView getPairContentView(String key, String value) {
        PairContentView pairContentView = new PairContentView(this);
        pairContentView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        pairContentView.setContent(key, value);
        return pairContentView;
    }

    public <T> T $(int resID) {
        return (T) findViewById(resID);
    }
}
