package com.debruyckere.florian.steamnews.view.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debruyckere.florian.steamnews.R
import com.debruyckere.florian.steamnews.model.Comment
import com.debruyckere.florian.steamnews.viewmodel.CommentViewModel
import com.google.firebase.auth.FirebaseAuth


class CommentFragment : Fragment() {

    private lateinit var mViewModel: CommentViewModel
    private lateinit var mBundleUrl: String
    private lateinit var mLoadingBar:ProgressBar
    private lateinit var mInfoLayout: LinearLayout
    private val mAuth = FirebaseAuth.getInstance()

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comment, container, false)

        mViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        mBundleUrl = requireArguments().get("newsId").toString()

        mInfoLayout = view.findViewById(R.id.comment_info)
        mLoadingBar = view.findViewById(R.id.comment_loading)
        val addButton : ImageButton = view.findViewById(R.id.comment_add)
        addButton.setOnClickListener{
            val dialog = AlertDialog.Builder(this.context)
            dialog.setTitle("Comment")
            dialog.setMessage("write your comment")
            val alertView : View = inflater.inflate(R.layout.comment_pop,null)
            dialog.setView(alertView)

            val alertEdit = alertView.findViewById<EditText>(R.id.comment_edit)
            val alertButton = alertView.findViewById<Button>(R.id.comment_button)
            alertButton.setOnClickListener{
                mViewModel.addComment(Comment(mAuth.currentUser!!.displayName!!,alertEdit.text.toString()) , mBundleUrl)
                //TODO: Close that things!
            }

            val alertDialog = dialog.create()
            alertDialog.show()
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
                mLoadingBar.visibility = View.GONE
                if(data.isEmpty()) mInfoLayout.visibility = View.VISIBLE
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