package com.example.app.activity;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.bugtags.library.Bugtags;
import com.example.app.db.DBHelper;

import butterknife.ButterKnife;
import io.realm.Realm;


public abstract class BaseActivity extends AppCompatActivity {

    private static ProgressDialog progressDialog;
    private Realm realm;
    protected void showProgressBar(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void hideProgressBar() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initAllMembersView(savedInstanceState);
    }

    protected abstract int getContentViewId();

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bugtags.onPause(this);
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        if(realm!=null){
            realm.close();
        }
        super.onDestroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }


    public Object getIntentExtras(String key) {
        return this.getIntent().getExtras().get(key);
    }
    public Realm getRealm() {
        if (realm == null) {
            realm = DBHelper.getRealm();
        }
        return realm;
    }

}
