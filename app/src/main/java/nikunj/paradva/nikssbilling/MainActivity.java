package nikunj.paradva.nikssbilling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;

import java.util.List;

import nikunj.paradva.nikssbilling.billing.BillingManager;

public class MainActivity extends AppCompatActivity implements PurchasesUpdatedListener {

    private Button btnBilling;
    BillingManager billingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billingManager=new BillingManager(this,this);
        initView();
    }

    private void initView() {
        btnBilling = findViewById(R.id.btn_billing);
        btnBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billingManager.startPurchaseFlow("product_id",BillingClient.SkuType.INAPP);
            }
        });
    }

    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
        if (responseCode == 0) {
            Toast.makeText(this, "Payment successfull", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Payment failed", Toast.LENGTH_SHORT).show();
        }
    }
}
