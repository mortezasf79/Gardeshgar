package tn.gardeshgar.discovery
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

import tn.gardeshgar.discovery.Views.Fragement.FragmentHome
import tn.gardeshgar.discovery.Views.Fragement.FragmentPhotouser
import tn.gardeshgar.discovery.Views.Fragement.FragmentVideouser

class MyPagerAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa){

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                FragmentPhotouser()
            }
            else -> {
                return FragmentPhotouser()
            }
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

}