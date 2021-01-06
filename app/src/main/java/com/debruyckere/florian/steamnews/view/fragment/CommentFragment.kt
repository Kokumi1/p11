package com.debruyckere.florian.steamnews.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.model.Comment
import com.debruyckere.florian.steamnews.viewmodel.CommentViewModel


class CommentFragment : Fragment() {

    private lateinit var mViewModel: CommentViewModel
    private lateinit var mBundleUrl: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        mViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        mBundleUrl = requireArguments().get("newsId").toString()

        val addButton : ImageButton = view.findViewById(R.id.comment_add)
        addButton.setOnClickListener{
            //TODO: add Comment POP up
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data : MutableList<Comment> = mutableListOf()
        val rv : RecyclerView = requireView().findViewById(R.id.comment_recycler)
        rv.layoutManager = LinearLayoutManager(this.context)
        rv.adapter = CommentAdapter(data)

        mViewModel.getComment(mBundleUrl).observe(viewLifecycleOwner){list : List<Comment> ->
            run{
                data.clear()
                data.addAll(list)
                rv.adapter!!.notifyDataSetChanged()
            }
        }
    }

    class CommentAdapter(private val pData: List<Comment>): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
            val inflater : LayoutInflater = LayoutInflater.from(parent.context)
            val view : View = inflater.inflate( R.layout.comment_cell, parent, false)
            return CommentViewHolder(view)
        }

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
            holder.display(pData[position])
        }

        override fun getItemCount(): Int = pData.size

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