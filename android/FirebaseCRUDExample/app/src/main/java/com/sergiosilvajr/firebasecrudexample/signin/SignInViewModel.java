package com.sergiosilvajr.firebasecrudexample.signin;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sergiosilvajr.firebasecrudexample.BR;
import com.sergiosilvajr.firebasecrudexample.login.LoginViewModel;

/**
 * Created by sergiosilvajr on 17/02/17.
 */

public class SignInViewModel extends BaseObservable {

    private FirebaseAuth auth;
    private String login;
    private String password;

    private OnCompleteListener<AuthResult> onCompleteListener;

    public SignInViewModel(FirebaseAuth auth, Object object){
        this.auth = auth;
        try{
            this.onCompleteListener = (OnCompleteListener<AuthResult>) object;
        }catch(ClassCastException e){
            throw new ClassCastException("This class must implement OnCompleteListener<AuthResult>");
        }

    }
    @Bindable
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login);
    }
    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public void createUser(){
        auth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(onCompleteListener);
    }
}
