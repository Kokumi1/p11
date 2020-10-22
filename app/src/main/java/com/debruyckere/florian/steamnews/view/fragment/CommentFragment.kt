package com.debruyckere.florian.steamnews.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.model.Comment


class CommentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv : RecyclerView = requireView().findViewById(R.id.comment_recycler)
        rv.layoutManager = LinearLayoutManager(this.context)
        rv.adapter = CommentAdapter()
    }

    class CommentAdapter: RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

        var data = listOf<Comment>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
            val inflater : LayoutInflater = LayoutInflater.from(parent.context)
            val view : View = inflater.inflate( R.layout.comment_cell, parent, false)
            return CommentViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            holder.display(data[position])
        }

        override fun getItemCount(): Int = data.size

        class CommentViewHolder(pView: View): RecyclerView.ViewHolder(pView){

            private val userText : TextView = itemView.findViewById(R.id.comment_user)
            private val contentText : TextView = itemView.findViewById(R.id.comment_content)

            fun display(pComment: Comment){
                userText.text = pComment.user
                contentText.text = pComment.content
            }
        }
    }
}