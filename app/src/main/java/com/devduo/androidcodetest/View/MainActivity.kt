package com.devduo.androidcodetest.View

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devduo.androidcodetest.Adapter.BankRecyclerAdapter
import com.devduo.androidcodetest.LayoutManager.GridSpacingItemDecoration
import com.devduo.androidcodetest.Model.DataModel
import com.devduo.androidcodetest.R
import com.devduo.androidcodetest.ViewModel.BankApiViewModel

class MainActivity : AppCompatActivity() {
    var viewModel: BankApiViewModel? = null
    lateinit var recyclerBank: RecyclerView
    lateinit var progress: ProgressBar
    var recyclerAdapter: BankRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerBank = findViewById(R.id.recycler_bank)
        progress = findViewById(R.id.progress)

        setUpRecycler()

        viewModel = ViewModelProviders.of(this).get(BankApiViewModel::class.java)
        viewModel!!.collectBankLiveData()
        viewModel!!.liveData?.observe(this, object : Observer<DataModel> {
            override fun onChanged(t: DataModel?) {
                if (t?.success!!) {
                    recyclerAdapter = BankRecyclerAdapter(this@MainActivity, t?.baseResponse!!.data)
                    recyclerBank!!.adapter = recyclerAdapter

                    if (progress.visibility == View.VISIBLE)
                        progress.visibility = View.GONE

                    if(recyclerBank.visibility!=View.VISIBLE)
                        recyclerBank.visibility=View.VISIBLE

                    Toast.makeText(applicationContext, t.baseResponse!!.message, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel!!.collectBankLiveData()
                    Toast.makeText(applicationContext, "Retrying", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    fun setUpRecycler() {
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerBank!!.layoutManager = mLayoutManager
        recyclerBank!!.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(10), true))
        recyclerBank!!.setItemAnimator(DefaultItemAnimator())
    }


    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            )
        )
    }
}

