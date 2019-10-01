package com.example.tv_maze_app.test

object adapter {

    //------ Adapters:
//    private class VerticalAdapter(listener: BusinessClickListener) : RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {
//
//        private val mListener = listener
//        private var mSubCategoryList = ArrayList<SubCategory>()
//
//        fun setList(list: List<SubCategory>) {
//            mSubCategoryList.clear()
//            mSubCategoryList.addAll(list)
//            notifyDataSetChanged()
//        }
//
//        fun getItem(position: Int): SubCategory = mSubCategoryList[position]
//
//        fun getList(): List<SubCategory> = mSubCategoryList
//
//        override fun getItemCount(): Int = mSubCategoryList.size
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_subcategory_vertical, parent, false)
//            return ViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            val subCatName = getItem(position).name
//            val businessList = getItem(position).list
//            val businessListAdapter = HorizontalAdapter(mListener)
//
//            if (businessList.isNullOrEmpty())
//                holder.subCatName.visibility = View.GONE
//            else
//                holder.subCatName.visibility = View.VISIBLE
//
//            holder.subCatName.text = subCatName
//            holder.horizontalRecyclerView.apply {
//                layoutManager = LinearLayoutManager(holder.horizontalRecyclerView.context, LinearLayout.HORIZONTAL, false)
//                adapter = businessListAdapter
//
//                val diffUtilCallback = HorizontalAdapter.DiffUtilCallback(businessListAdapter.getList(), businessList!!)
//                val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
//                businessListAdapter.setBusinessList(businessList)
//                diffUtilResult.dispatchUpdatesTo(businessListAdapter)
//            }
//        }
//
//        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//            val subCatName: TextView = view.name
//            val horizontalRecyclerView: RecyclerView = view.recycler_view_horizontal
//        }
//    }
//
//    private class HorizontalAdapter(listener: BusinessClickListener) : RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {
//
//        private val mListener = listener
//        private var mBusinessList = ArrayList<Business>()
//
//        class DiffUtilCallback(private val oldList: List<Business>, private val newList: List<Business>) : DiffUtil.Callback() {
//            override fun getOldListSize() = oldList.size
//            override fun getNewListSize() = newList.size
//            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].id == newList[newItemPosition].id
//            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition] == newList[newItemPosition]
//        }
//
//        fun setBusinessList(list: List<Business>) {
//            mBusinessList.clear()
//            mBusinessList.addAll(list)
//        }
//
//        fun getItem(position: Int): Business = mBusinessList[position]
//
//        fun getList() = mBusinessList
//
//        override fun getItemCount(): Int = mBusinessList.size
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val inflater = LayoutInflater.from(parent.context)
//            val holder: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.item_business_1, parent, false)
//            return ViewHolder(holder)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.bind(getItem(position), mListener)
//        }
//
//        inner class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
//            fun bind(data: Any, listener: BusinessClickListener) {
//                binding.setVariable(BR.model, data)
//                binding.setVariable(BR.clickListener, listener)
//            }
//        }
//
//    }
}