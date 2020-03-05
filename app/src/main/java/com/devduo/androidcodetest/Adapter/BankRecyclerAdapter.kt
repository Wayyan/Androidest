package com.devduo.androidcodetest.Adapter

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.devduo.androidcodetest.Constants
import com.devduo.androidcodetest.Model.BankDataModel
import com.devduo.androidcodetest.R
import com.squareup.picasso.Picasso

class BankRecyclerAdapter(var context: Context, var bankData: List<BankDataModel>) :
    RecyclerView.Adapter<BankRecyclerAdapter.BankViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        return BankViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_bank,
                parent,
                false
            ), context
        )
    }

    override fun getItemCount(): Int {
        return bankData.size
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.setData(bankData.get(position))
    }

    open class BankViewHolder(itemView: View, var context: Context) :
        RecyclerView.ViewHolder(itemView) {
        var tvBank = itemView.findViewById<TextView>(R.id.tv_bank)
        var imgV = itemView.findViewById<ImageView>(R.id.iv_bank)
        var cvBank = itemView.findViewById<CardView>(R.id.cd_bank)

        fun setData(bankDataModel: BankDataModel) {
            tvBank.setText(bankDataModel.name)
            //imgV.loadUrl(bankDataModel.logo_url)
            cvBank.setOnClickListener({
                clicked(bankDataModel)
            })
        }

        fun ImageView.loadUrl(url: String?) {
            if (url != null) {
                Picasso.get()
                    .load(Constants.URL + url)
                    .placeholder(R.drawable.progress_loading)
                    .fit()
                    .centerInside()
                    .into(this)
            }
        }

        fun clicked(bankDataModel: BankDataModel) {
            var dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_info)
            dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation

            var tvBank = dialog.findViewById<TextView>(R.id.tv_bank_dialog)
            var imgBank = dialog.findViewById<ImageView>(R.id.img_bank_dialog)
            var tvMajor = dialog.findViewById<TextView>(R.id.tv_major_dialog)

            tvBank.setText(bankDataModel.name)
            imgBank.loadUrl(bankDataModel.logo_url)

            if (bankDataModel.is_major)
                tvMajor.text = "(It is major bank)"
            else
                tvMajor.text = "(It is minor bank)"

            dialog.show()
        }
    }
}