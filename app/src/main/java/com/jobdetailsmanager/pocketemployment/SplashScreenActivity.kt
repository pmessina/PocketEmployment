/*
 * Copyright (c) 2016. Patrick Messina
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jobdetailsmanager.pocketemployment

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class SplashScreenActivity : Activity() {

    //@BindView(R.id.titleSplashScreen)
    internal var titleSplashScreen: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash_screen);

        //ButterKnife.bind(this);

        //        Typeface face= Typeface.createFromAsset(getAssets(),
        //                "ArimaMadurai-Black.ttf");
        //
        //        titleSplashScreen.setTypeface(face);

        Handler().postDelayed({
            //                Intent intent = new Intent(SplashScreenActivity.this, RecruiterCallsTodoList.class);
            //                startActivity(intent);
            finish()
        }, 3000)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        //        if (id == R.id.action_settings)
        //        {
        //            return true;
        //        }

        return super.onOptionsItemSelected(item)
    }


}
