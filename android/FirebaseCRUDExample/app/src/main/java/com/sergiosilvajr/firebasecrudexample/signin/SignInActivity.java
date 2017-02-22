package com.sergiosilvajr.firebasecrudexample.signin;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sergiosilvajr.firebasecrudexample.R;
import com.sergiosilvajr.firebasecrudexample.databinding.ActivitySigninBinding;
import com.sergiosilvajr.firebasecrudexample.todo.HomeActivity;

/**
 * Created by sergiosilvajr on 17/02/17.
 */

public class SignInActivity extends AppCompatActivity implements
        OnCompleteListener<AuthResult> {
    private static final String TAG = SignInActivity.class.getSimpleName();
    private SignInViewModel signInViewModel;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signInViewModel = new SignInViewModel(auth,this);

        ActivitySigninBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_signin);
        binding.setViewModel(signInViewModel);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
        if (task.isSuccessful()) {
            Toast.makeText(this, "User created",
                    Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(this, "Auth problems",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
