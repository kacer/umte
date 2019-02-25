package cz.uhk.umteapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cz.uhk.umteapp.model.User
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    val list = mutableListOf<User>() // vytvoreni kolekce/listu useru

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Adapter()
        namesRecyclerView.adapter = adapter
        namesRecyclerView.layoutManager = LinearLayoutManager(context)

        addButton.setOnClickListener {
            val user = User(nameEditText.text.toString())
            nameEditText.setText("")
            list.add(user)
            adapter.notifyDataSetChanged()
        }
    }

    inner class Adapter : RecyclerView.Adapter<Adapter.Holder>() {

        override fun onCreateViewHolder(root: ViewGroup, p1: Int): Holder {
            return Holder(LayoutInflater.from(context).inflate(R.layout.row_user, root, false))
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: Holder, p1: Int) {
            holder.onBind()
        }

        inner class Holder(val item: View) : RecyclerView.ViewHolder(item), View.OnClickListener { //misto super, preda se view predkovi

            init { // metoda, ktera se vola vzdy po konstruktoru s aktualnimi hodnotami v promennych, maji vsechny tridy v kotlinu
               item.setOnClickListener(this)
            }

            fun onBind() {
                val nameTextView = item.findViewById<TextView>(R.id.nameTextView)
                val user = list[layoutPosition]

                nameTextView.text = user.name
            }

            override fun onClick(v: View?) {
                // otevrit detail uzivatele todo
            }
        }

    }

}