package com.sergiosilvajr.firebasecrudexample.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sergiosilvajr.firebasecrudexample.R;
import com.sergiosilvajr.firebasecrudexample.databinding.ActivityMainBinding;
import com.sergiosilvajr.firebasecrudexample.signin.SignInActivity;
import com.sergiosilvajr.firebasecrudexample.todo.HomeActivity;

public class MainActivity extends AppCompatActivity implements
        OnCompleteListener<AuthResult>,
        LoginViewModel.OnNullLoginListener,
        LoginViewModel.OnSignupListener{

    private FirebaseAuth auth = FirebaseAuth.getInstance();

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int LOGIN_REQUEST_CODE = 999;

    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loginViewModel = new LoginViewModel(auth, this);
        binding.setViewModel(loginViewModel);

        Button enterButton = binding.enterButton;
        Button signupButton = binding.signinButton;
        enterButton.setOnClickListener(loginViewModel.getLoginButtonEvent());
        signupButton.setOnClickListener(loginViewModel.getSignupButtonEvent());
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
        if (task.isSuccessful()) {
            startActivityForResult(new Intent(this, HomeActivity.class), LOGIN_REQUEST_CODE);
        }else{
            Toast.makeText(this, "Auth problems",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE &&  resultCode == RESULT_CANCELED){
            loginViewModel.logout();
            Toast.makeText(this, "Logout",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void haveNullInfo() {
        Toast.makeText(this, "Please type your user and/or password",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUp() {
        startActivity(new Intent(this, SignInActivity.class));
    }
}
