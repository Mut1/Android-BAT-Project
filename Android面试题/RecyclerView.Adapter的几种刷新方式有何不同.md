刷新全部可见的item  notifyDataSetChanged()

刷新指定item   notifyItemChanged（int）

从指定位置开始刷新指定个item  notifyItemRangeChanged(int,int)

插入、移动一个并自动刷新  notifyItemInserted(int) notifyItenMoved(int)、notifyItemRemoved(int)

局部刷新  notifyItemChanged(int,Object)

