package com.example.arjun.vhf_school;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.example.arjun.vhf_school.xmpp.SettingsPrefs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static android.content.ContentValues.TAG;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SettingsPrefTest {

    private static final String USER = "user1";
    private static final String PASS = "123321";
    private static final String SERVER = "85.1.228.64";

    @Mock
    Context context;
    //test

    /**
     * Function to test the User
     */
    @Test
    public void UserSettingsTest() {

        //Give the mock context to object
        SettingsPrefs test = mock(SettingsPrefs.class);

        // ...when the string is returned from the object under test...
        when(test.getUser()).thenReturn(USER);

        // ...then the result should be the expected one.
        assertEquals(test.getUser(),"user1");

    }

    /**
     * Function to test the server
     */
    @Test
    public void PasswordSettingsTest(){

        //Give the mock context to object
        SettingsPrefs test = mock(SettingsPrefs.class);

        // ...when the string is returned from the object under test...
        when(test.getPassword()).thenReturn(PASS);

        // ...then the result should be the expected one.
        assertEquals(test.getPassword(),"123321");

    }

    /**
     * Function to test the save()
     */
    @Test
    public void ServerSettingsTest(){

        //Give the mock context to object
        SettingsPrefs test = mock(SettingsPrefs.class);

        // ...when the string is returned from the object under test...
        when(test.getServer()).thenReturn(SERVER);

        // ...then the result should be the expected one.
        assertEquals(test.getServer(),"85.1.228.64");


    }


    /**
     * Function to test the save()
     */
    @Test
    public void SaveSettingsTest(){

        //Give the mock context to object
        SettingsPrefs expected = Mockito.mock(SettingsPrefs.class);
        SettingsPrefs result = Mockito.mock(SettingsPrefs.class);

        // ...when the function is called from the object under test...
        expected.setUser(USER);
        expected.setPassword(PASS);
        expected.setServer(SERVER);
        expected.save();


        // ...then the result should be the expected one.
        assertEquals(expected.getUser(),result.getUser());
        assertEquals(expected.getPassword(),result.getPassword());
        assertEquals(expected.getServer(),result.getServer());

    }

}