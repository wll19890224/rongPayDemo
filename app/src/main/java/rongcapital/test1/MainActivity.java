package rongcapital.test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    String tradeFlowId;

    private static final String ACTION_PAY = "com.rongcapital.pay";
    private static final String ACTION_QUERY = "com.rongcapital.query";
    private static final String ACTION_WECHAT_REFUND = "com.rongcapital.refund";
    private static final String ACTION_PRINT = "com.rongcapital.print";
    private static final int WECHAT_VALUE = 10;
    private static final int UMS_VALUE = 30;

    TextInputEditText mEditText1;
    TextInputEditText mEditText2;
    TextInputEditText mEditText3;
    TextInputEditText mEditText4;
    TextInputEditText mEditText5;
    TextInputEditText mEditText6;
    FloatingActionButton button;
    Spinner sp;
    Spinner sp1;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mEditText1 = (TextInputEditText) findViewById(R.id.text1);
        mEditText2 = (TextInputEditText) findViewById(R.id.text2);
        mEditText3 = (TextInputEditText) findViewById(R.id.text3);
        mEditText4 = (TextInputEditText) findViewById(R.id.text4);
        mEditText5 = (TextInputEditText) findViewById(R.id.text5);
        mEditText6 = (TextInputEditText) findViewById(R.id.text6);
        tv = (TextView) findViewById(R.id.result);
        sp = (Spinner) findViewById(R.id.sp);
        sp1 = (Spinner) findViewById(R.id.sp1);
        button = (FloatingActionButton) findViewById(R.id.fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                mEditText1.setText(sdf.format(new Date()));
            }
        });

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        mEditText1.setText(sdf.format(new Date()));
    }

    public void print(View view) {
        String msg = getPrintFormatMsg(mEditText1.getText().toString());
        try {
            Intent intent = new Intent();
            Bundle args = new Bundle();
            args.putString("msg", msg);   // 打印内容规范 请参考《全民付收银台线下插件商户销售单据打印规范》
            args.putString("packageName", getPackageName());  // 应用包名
            intent.putExtra("data", args);
            intent.setAction(ACTION_PRINT);  // 操作类型
            startActivity(intent);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    public void query(View view) {
        String mercOrderNo = mEditText1.getText().toString();
        String string4 = mEditText4.getText().toString().trim();
        String string5 = mEditText5.getText().toString().trim();
        try {
            Intent intent = new Intent();
            Bundle args = new Bundle();
            args.putString("merOrderId", mercOrderNo);
            args.putString("packageName", getPackageName());  // 应用包名
            args.putString("operator", string4);                // 操作员
            args.putString("tableId", string5);                 // 台位
            args.putString("tradeFlowId", tradeFlowId);
            intent.putExtra("data", args);
            intent.setAction(ACTION_QUERY);  // 操作类型
            startActivity(intent);
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
    }

    public void weChatRefund(View view) {
        String string1 = mEditText1.getText().toString().trim();
        String string2 = mEditText2.getText().toString().trim();
        String string3 = mEditText3.getText().toString().trim();
        String string4 = mEditText4.getText().toString().trim();
        String string5 = mEditText5.getText().toString().trim();
        String string6 = mEditText6.getText().toString().trim();
        if (TextUtils.isEmpty(string1) ||
            TextUtils.isEmpty(string2) ||
            TextUtils.isEmpty(string3) ||
            TextUtils.isEmpty(string4) ||
            TextUtils.isEmpty(string5)) {
            Toast.makeText(this, "has null params!", Toast.LENGTH_LONG).show();
        } else {
            Log.i("TAG", string1 + "\t" + string2 + "\t" + string3 + "\t" + string4 + "\t");
            try {
                Intent intent = new Intent();
                Bundle args = new Bundle();
                args.putString("merOrderId", string1);   // 客户订单号
                args.putLong("amount", Long.parseLong(string2));              // 退款金额
                args.putString("packageName", getPackageName());  // 应用包名
                args.putString("operator", string4);                // 操作员
                args.putString("tradeFlowId", tradeFlowId);
                intent.putExtra("data", args);
                intent.setAction(ACTION_WECHAT_REFUND);  // 操作类型
                startActivity(intent);
            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            }
        }
    }

    public void pay(View view) {
        String string1 = mEditText1.getText().toString().trim();
        String string2 = mEditText2.getText().toString().trim();
        String string3 = mEditText3.getText().toString().trim();
        String string4 = mEditText4.getText().toString().trim();
        String string5 = mEditText5.getText().toString().trim();
        String string6 = mEditText6.getText().toString().trim();
        int payType = 0;
        if (sp.getSelectedItemPosition() == 0) {
            payType = 0;
        } else if (sp.getSelectedItemPosition() == 1) {
            payType = WECHAT_VALUE;
        } else {
            payType = UMS_VALUE;
        }
        String bizAndOrder = "10";
        if (sp.getSelectedItemPosition() == 0) {
            bizAndOrder ="10";
        } else if (sp.getSelectedItemPosition() == 1) {
            bizAndOrder = "20";
        }

        if (TextUtils.isEmpty(string1) ||
            TextUtils.isEmpty(string2) ||
            TextUtils.isEmpty(string3) ||
            TextUtils.isEmpty(string4) ||
            TextUtils.isEmpty(string5)) {
            Toast.makeText(this, "has null params!", Toast.LENGTH_LONG).show();
        } else {
            Log.i("TAG", string1 + "\t" + string2 + "\t" + string3 + "\t" + string4 + "\t");
            try {
                Intent intent = new Intent();
                Bundle args = new Bundle();
                args.putString("merOrderId", string1);              // 客户订单号
                args.putInt("payType", payType);                    // 指定收款方式（默认0，微信支付 10， 银商支付30）
                args.putLong("amount", Long.parseLong(string2));    // 收款金额 单位分
                args.putString("title", string3);                   // 订单标题
                args.putString("operator", string4);                // 操作员
                args.putString("packageName", getPackageName());    // applicationId 应用标示
                args.putString("tableId", string5);                 // 台位
                args.putString("funCode", string6);                 // 支付功能码
                args.putString("bizAndOrder", bizAndOrder);
                intent.putExtra("data", args);
                intent.setAction(ACTION_PAY);
                startActivityForResult(intent, 100);
            } catch (Exception e) {
                Log.e("TAG", e.getMessage());
            }
        }
    }

    private String getPrintFormatMsg(String orderId) {
        String text =
            "!hz l\n" +
                "!asc l\n" +
                "!gray 6\n" +
                "!yspace 4\n" +
                "*text c 菜单\n" +
                "*line\n" +
                "!hz s\n" +
                "!asc s\n" +
                "!gray 2\n" +
                "*text c 消费名称：" + "xxxxx" + "\n" +
                "*text c" + " 订单号：" + orderId + "\n" +
                "*text c" + " 支付方式：" + "1111" + "\n" +
                "*text c" + " 支付时间：" + "11111" + "\n" +
                "*text c" + " 交易类型：消费" + "\n" +
                "*text c" + " 交易金额：" + "1111.11" + "元" + "\n" +
                "*text c" + " 本人确认以上交易" + "\n" +
                "*line\n" +
                "*text c" + " 持卡人签名\n" +
                "*text c" + "\n" +
                "*line\n";
        return text;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "支付取消", Toast.LENGTH_LONG).show();
        } else if (resultCode == RESULT_OK || requestCode == 100) {
            if (data.getBundleExtra("data") == null) {
            } else {
                Bundle args = data.getBundleExtra("data");
                Long amount = args.getLong("amount", -1);
                String merorderId = args.getString("merOrderId");
                String payStatus = args.getString("payStatus");
                String title = args.getString("title");
                String operator = args.getString("operator");
                String packageName = args.getString("packageName");
                int payType = args.getInt("payType", 0);
                tradeFlowId = args.getString("tradeFlowId");                // 交易流水号
                String dealTime = args.getString("dealTime");               // 交易时间
                tv.setText("amount:" + amount + "|merorderId:" + merorderId + "|title:" + title + "|operator:" +
                    operator + "|packageName:" + packageName + "|payType:" + payType
                    + "|tradeFlowId:" + tradeFlowId + "|dealTime:" + dealTime + "|payStatus:" + payStatus);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // for filter key event back/home/menu
        if ((keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_MENU)
            && event.getRepeatCount() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}