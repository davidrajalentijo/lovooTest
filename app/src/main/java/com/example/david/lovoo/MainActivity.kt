package com.example.david.lovoo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.david.lovoo.ui.rooms.RoomsFactFragment
import com.example.david.lovoo.ui.rooms.RoomsFragment
import kotlinx.android.synthetic.main.activity_main.*

/*
    MainActivity to manage the fragments of RoomsList and Rooms with LovooFact
 */
class MainActivity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        viewpager.adapter = mSectionsPagerAdapter

        sliding_tabs.setupWithViewPager(viewpager)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return if (position == 0) {
                RoomsFragment.newInstance(position + 1)
            } else {
                RoomsFactFragment.newInstance(position + 1)
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return if (position == 0) {
                resources.getString(R.string.tab_text_1)
            } else {
                resources.getString(R.string.tab_text_2)
            }
        }
    }
}