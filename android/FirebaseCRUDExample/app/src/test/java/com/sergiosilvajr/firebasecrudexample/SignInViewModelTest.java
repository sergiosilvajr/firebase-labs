package com.sergiosilvajr.firebasecrudexample;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sergiosilvajr.firebasecrudexample.signin.SignInViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by sergiosilvajr on 22/02/17.
 */
@RunWith(PowerMockRunner.class)
public class SignInViewModelTest {
    private SignInViewModel signInViewModel;

    @Mock
    FirebaseAuth firebaseAuth;
    @Mock
    OnCompleteListener<AuthResult> onCompleteListener;
    @Mock
    Task<AuthResult> task;

    @Before
    public void setup() {
        signInViewModel = new SignInViewModel(firebaseAuth, onCompleteListener);
    }

    @After
    public void tearDown(){
        signInViewModel = null;
    }

    @Test
    public void testGetLoginButtonEventNoLogin(){
        String login = "login";
        String password = "password";

        signInViewModel.setLogin(login);
        signInViewModel.setPassword(password);

        doReturn(task).when(firebaseAuth).createUserWithEmailAndPassword(login,password);
        signInViewModel.createUser();
        verify(firebaseAuth, times(1)).createUserWithEmailAndPassword(login,password);
    }

}
