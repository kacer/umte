package cz.uhk.umteapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cz.uhk.umteapp.model.User
import cz.uhk.umteapp.model.UserDAO
import cz.uhk.umteapp.model.UserDB
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    val UPDATE_USER_CODE_REQ = 100

    val list = mutableListOf<User>() // vytvoreni kolekce/listu useru
    val dbList = mutableListOf<UserDB>()

    val adapter = Adapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbList.addAll(UserDAO().selectAll())

        namesRecyclerView.adapter = adapter
        namesRecyclerView.layoutManager = LinearLayoutManager(context)

        addButton.setOnClickListener {
            val user = User(nameEditText.text.toString(), "", 0, 0)
            list.add(user)

            val userDb = UserDB()
            userDb.name = nameEditText.text.toString()
            dbList.add(userDb)
            UserDAO().createOrUpdate(userDb)

            nameEditText.setText("")
            adapter.notifyDataSetChanged()
        }
    }

    inner class Adapter : RecyclerView.Adapter<Adapter.Holder>() {

        override fun onCreateViewHolder(root: ViewGroup, p1: Int): Holder {
            return Holder(LayoutInflater.from(context).inflate(R.layout.row_user, root, false))
        }

        override fun getItemCount(): Int {
            return dbList.size
        }

        override fun onBindViewHolder(holder: Holder, p1: Int) {
            holder.onBind()
        }

        inner class Holder(val item: View) : RecyclerView.ViewHolder(item), View.OnClickListener, View.OnLongClickListener { //misto super, preda se view predkovi

            init { // metoda, ktera se vola vzdy po konstruktoru s aktualnimi hodnotami v promennych, maji vsechny tridy v kotlinu
                item.setOnClickListener(this)
                item.setOnLongClickListener(this)
            }

            fun onBind() {
                val nameTextView = item.findViewById<TextView>(R.id.nameTextView)
                val user = dbList[layoutPosition]

                nameTextView.text = user.name
            }

            override fun onClick(v: View?) {
                val user = dbList[layoutPosition]
                val intent = Intent(this@ListFragment.context, UserDetailActivity::class.java)
                intent.putExtra("user", user)
                startActivityForResult(intent, UPDATE_USER_CODE_REQ)
            }

            override fun onLongClick(v: View?): Boolean {
                val user = dbList[layoutPosition]
                UserDAO().delete(user)
                dbList.removeAt(layoutPosition)
                notifyItemRemoved(layoutPosition)

                return true
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            when(requestCode) {     // nahrada za switch
                UPDATE_USER_CODE_REQ -> {
                    val updatedUser = data?.getSerializableExtra("user") as UserDB
                    UserDAO().createOrUpdate(updatedUser)
                    // TODO updatnout dbList -> updatedUser je jina instance nez ktera byla poslana
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}