package com.sergiosilvajr.firebasecrudexample.login;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sergiosilvajr.firebasecrudexample.BR;

/**
 * Created by sergiosilvajr on 17/02/17.
 */

public class LoginViewModel extends BaseObservable {

    private FirebaseAuth auth;
    private String login;
    private String password;
    private OnCompleteListener<AuthResult> onCompleteListener;
    private OnNullLoginListener onNullLoginListener;
    private OnSignupListener onSignUpListener;

    public LoginViewModel(FirebaseAuth auth, Object object){
        try{
            this.auth = auth;
            this.onCompleteListener = (OnCompleteListener<AuthResult>) object;
            this.onNullLoginListener = (OnNullLoginListener) object;
            this.onSignUpListener = (OnSignupListener) object;
        }catch(ClassCastException e){
            throw new ClassCastException("This class must implement OnCompleteListener<AuthResult>, OnNullLoginListener and OnSignupListener");
        }

    }

    public View.OnClickListener getLoginButtonEvent(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (login == null || password == null) {
                    onNullLoginListener.haveNullInfo();
                }else {
                    auth.signInWithEmailAndPassword(login, password)
                            .addOnCompleteListener(onCompleteListener);
                }
            }
        };
    }

    public View.OnClickListener getSignupButtonEvent(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onSignUpListener.onSignUp();
            }
        };
    }

    public void logout(){
        auth.signOut();
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
    public interface OnNullLoginListener{
        void haveNullInfo();
    }
    public interface OnSignupListener{
        void onSignUp();
    }
}
