package com.app.purvapractical

import android.view.View


interface RecyclerItemClickListener {
    fun onItemClick(view: View?, position: Int)
    fun onChildClick(view: View?, position: Int, data: Any)


}