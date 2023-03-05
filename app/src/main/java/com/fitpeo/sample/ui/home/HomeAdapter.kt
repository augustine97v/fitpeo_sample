package com.fitpeo.sample.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fitpeo.sample.databinding.HomeListItemBinding
import com.fitpeo.sample.ui.home_detail.HomeDetailActivity
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import timber.log.Timber

class HomeAdapter(fragment: Fragment) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    val list = mutableListOf<HomeBean>()
    lateinit var context: Context

    val onClick: OnClickData = fragment as HomeFragment
    fun update(
        list: List<HomeBean>, context: Context
    ) {
        this.list.clear()
        this.list.addAll(list)
        this.context = context
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MyViewHolder {
        val view = HomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position], position, holder)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(binding: HomeListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val view = binding

        fun bind(
            data: HomeBean, position: Int, holder: MyViewHolder
        ) {
            Timber.d("$data : $position")

            view.title.text = data.title
            view.titleDescVal.text = data.thumbnailUrl

            Glide.with(context).load(data!!.url).centerCrop().into(view.idImage);

           /* Picasso.get().load(data.url)
//                .transform(RoundedTransformation(30, 0))
//                .placeholder(context.resources.getDrawable(R.drawable.ic_launcher_foreground))//it will show placeholder image when url is not valid.
                .networkPolicy(NetworkPolicy.OFFLINE) //for caching the image url in case phone is offline
                .into(view.idImage)*/



            holder.itemView.setOnClickListener {
                val intent = Intent(context, HomeDetailActivity::class.java)
                intent.putExtra("data", data)
                context.startActivity(intent)
                onClick.onClickEventsImage(position, data)
            }

        }

    }
}

