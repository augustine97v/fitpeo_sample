package com.fitpeo.sample.ui.home_detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fitpeo.sample.R
import com.fitpeo.sample.databinding.ActivityHomeDetailBinding
import com.fitpeo.sample.ui.home.HomeBean
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class HomeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeDetailBinding

    private var mData: HomeBean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        mData = intent.getSerializableExtra("data") as? HomeBean
        if (mData != null) {

            binding.idTitle.text = mData!!.title
            binding.idDesc.text = mData!!.thumbnailUrl

            Glide.with(this).load(mData!!.url).centerCrop().into(binding.imageView)


            Picasso.get().load(mData!!.url)
//                .transform(RoundedTransformation(30, 0))
//                .placeholder(context.resources.getDrawable(R.drawable.ic_launcher_foreground))//it will show placeholder image when url is not valid.
                .networkPolicy(NetworkPolicy.OFFLINE) //for caching the image url in case phone is offline
                .into(binding.imageView)

        }

    }
}