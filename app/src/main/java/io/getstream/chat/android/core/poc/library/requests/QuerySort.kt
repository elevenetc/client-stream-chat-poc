package io.getstream.chat.android.core.poc.library.requests



class QuerySort {
    private var mSort: MutableList<Map<String, Any>>? =
        null

    val data: List<Map<String, Any>>?
        get() = mSort

    fun clone(): QuerySort {
        val _this = QuerySort()
        if (mSort == null) {
            mSort = ArrayList()
        }
        _this.mSort = ArrayList(mSort!!)
        return _this
    }

    private fun add(fieldName: String, direction: Number): QuerySort {
        val v: MutableMap<String, Any> = HashMap()
        v["field"] = fieldName
        v["direction"] = direction
        val _this = clone()
        _this.mSort!!.add(v)
        return _this
    }

    fun asc(fieldName: String): QuerySort {
        return add(fieldName, ASC)
    }

    fun desc(fieldName: String): QuerySort {
        return add(fieldName, DESC)
    }

    companion object {
        private const val DESC = -1
        private const val ASC = 1
    }
}



