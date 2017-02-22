package com.sergiosilvajr.firebasecrudexample;

import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sergiosilvajr.firebasecrudexample.login.LoginViewModel;

import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.Before;
import org.junit.Test;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by sergiosilvajr on 22/02/17.
 */
@RunWith(PowerMockRunner.class)
public class LoginViewModelTest {
    private LoginViewModel loginViewModel;

    @Mock
    FirebaseAuth firebaseAuth;
    @Mock
    ImplInterfacesLoginViewModel implInterfacesLoginViewmModel;
    @Mock
    Task<AuthResult> task;

    @Before
    public void setup() {
        loginViewModel = new LoginViewModel(firebaseAuth, implInterfacesLoginViewmModel);
    }

    @After
    public void tearDown(){
        loginViewModel = null;
    }

    @Test
    public void testGetLoginButtonEventNoLogin(){
        loginViewModel.getLoginButtonEvent().onClick(mock(View.class));
        verify(implInterfacesLoginViewmModel, Mockito.times(1)).haveNullInfo();
    }

    @Test
    public void testGetLoginButtonEventLoginData(){
        String login = "login";
        String password = "password";

        loginViewModel.setLogin(login);
        loginViewModel.setPassword(password);

        Mockito.doReturn(task).when(firebaseAuth).signInWithEmailAndPassword(login,password);
        loginViewModel.getLoginButtonEvent().onClick(mock(View.class));
        verify(firebaseAuth, Mockito.times(1)).signInWithEmailAndPassword(login,password);
    }

    @Test
    public void testGetSignupButtonEvent(){
        loginViewModel.getSignupButtonEvent().onClick(mock(View.class));
        verify(implInterfacesLoginViewmModel, Mockito.times(1)).onSignUp();
    }


    class ImplInterfacesLoginViewModel implements OnCompleteListener<AuthResult>,LoginViewModel.OnNullLoginListener,LoginViewModel.OnSignupListener{

        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

        }

        @Override
        public void haveNullInfo() {

        }

        @Override
        public void onSignUp() {

        }
    }
}
