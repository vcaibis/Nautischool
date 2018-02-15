package com.example.arjun.vhf_school;

import android.content.Context;
import android.widget.EditText;

import com.example.arjun.vhf_school.xmpp.SettingsPrefs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class SettingsPrefTest {

    private static final String USER = "user1";
    private static final String PASS = "123321";

    @Mock
    Context context;

    @Test
    public void UserSettingsTest() {

        //Give the mock context to object
        when(context.getString(R.string.user)).thenReturn(USER);

        //Declare the object to test
        SettingsPrefs myObjectUnderTest = new SettingsPrefs(context);

        // ...when the string is returned from the object under test...
        myObjectUnderTest.setUser(USER);
        myObjectUnderTest.save();

        //
        SettingsPrefs myAssertObject = new SettingsPrefs(context);
        String userResult = myAssertObject.getUser();


        // ...then the result should be the expected one.
        assertThat(USER, is(userResult));

    }

    @Test
    public void PasswordEditTextValidator(){


    }

}