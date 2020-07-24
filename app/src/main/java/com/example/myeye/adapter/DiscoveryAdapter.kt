package com.example.myeye.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myeye.R
import com.example.myeye.model.Discovery
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import kotlinx.android.synthetic.main.item_banner_item.view.*
import kotlinx.android.synthetic.main.item_text_card_header_five.view.*
import kotlinx.android.synthetic.main.item_text_card_header_seven.view.*

class DiscoveryAdapter(val dataList: List<Discovery.Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class ViewType {
        UNKNOWN,
        HORIZONTAL_SCROLL_CARD,
        TEXT_CARD_HEADER4,
        TEXT_CARD_HEADER5,
        TEXT_CARD_HEADER7,
        TEXT_CARD_HEADER8,
        TEXT_CARD_FOOTER2,
        TEXT_CARD_FOOTER3,
        TAG_BRIEFCARD,
        TOPIC_BRIEFCARD

    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataList[position]
        when(holder) {
            is HorizontalScrollCardViewHolder -> {
                holder.banerViewPager.run {
                    setCanLoop(false)
                    setRoundCorner(4)
                    setIndicatorVisibility(View.GONE)
                    setAdapter(HorizontalScrollCardAdapter())
                    setOnPageClickListener {position ->
                        
                    }
                    create(item.data.itemList)
                }
            }
            is TextCardViewHeader5ViewHolder -> {
                holder.tvTitle5.text = item.data.text
            }
            is TextCardViewHeader7ViewHolder -> {
                holder.view.tv_title_7.text = item.data.text
                holder.view.tv_right_text_7.text = item.data.rightText
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = dataList[position]
        val type = item.type
        val dataType = item.data.dataType
        when(type) {
            "horizontalScrollCard" -> {
                when(dataType) {
                    "HorizontalScrollCard" -> return ViewType.HORIZONTAL_SCROLL_CARD.ordinal
                }
            }
            "textCard" -> {
                return getTextCardType(item.data.type).ordinal
            }
            "briefCard" -> {
                when (dataType) {
                    "TagBriefCard" -> return ViewType.TAG_BRIEFCARD.ordinal
                    "TopicBriefCard" -> return ViewType.TOPIC_BRIEFCARD.ordinal
                }
            }
        }
        return ViewType.UNKNOWN.ordinal
    }

    private fun getTextCardType(type: String) = when (type) {
        "header4" -> ViewType.TEXT_CARD_HEADER4
        "header5" -> ViewType.TEXT_CARD_HEADER5
        "header7" -> ViewType.TEXT_CARD_HEADER7
        "header8" -> ViewType.TEXT_CARD_HEADER8
        "footer2" -> ViewType.TEXT_CARD_FOOTER2
        "footer3" -> ViewType.TEXT_CARD_FOOTER3
        else -> ViewType.UNKNOWN
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when(viewType) {
            ViewType.HORIZONTAL_SCROLL_CARD.ordinal -> {
                return HorizontalScrollCardViewHolder(layoutInflater.inflate(R.layout.item_horizontal_scroll_card, parent, false))
            }
            ViewType.TEXT_CARD_HEADER5.ordinal -> {
                return TextCardViewHeader5ViewHolder(layoutInflater.inflate(R.layout.item_text_card_header_five, parent, false))
            }
            ViewType.TEXT_CARD_HEADER7.ordinal -> {
                return TextCardViewHeader7ViewHolder(layoutInflater.inflate(R.layout.item_text_card_header_seven, parent, false))
            }
        }
        val contactView = layoutInflater.inflate(R.layout.item_text, parent, false)
        return ViewHoder(contactView)
    }


    class ViewHoder(view: View): RecyclerView.ViewHolder(view) {

    }

    class HorizontalScrollCardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var banerViewPager: BannerViewPager<Discovery.ItemX, DiscoveryAdapter.HorizontalScrollCardAdapter.ViewHolder> = view.findViewById(R.id.banner_view)
    }

    inner class HorizontalScrollCardAdapter:
        BaseBannerAdapter<Discovery.ItemX, HorizontalScrollCardAdapter.ViewHolder>() {

        inner class ViewHolder(val view: View) : BaseViewHolder<Discovery.ItemX>(view) {
            override fun bindData(item: Discovery.ItemX, position: Int, pageSize: Int) {
                val ivPicture = view.iv_picture
                Glide.with(view.context).load(item.data.image).into(ivPicture)
            }
        }

        override fun getLayoutId(viewType: Int): Int {
            return R.layout.item_banner_item
        }

        override fun createViewHolder(
            itemView: View?,
            viewType: Int
        ): HorizontalScrollCardAdapter.ViewHolder {
            return ViewHolder(itemView!!)
        }

        override fun onBind(
            holder: HorizontalScrollCardAdapter.ViewHolder?,
            data: Discovery.ItemX?,
            position: Int,
            pageSize: Int
        ) {
            holder!!.bindData(data!!, position, pageSize)
        }
    }

    class TextCardViewHeader5ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvTitle5 = view.tv_title_5
    }

    class TextCardViewHeader7ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val tvTitle7 = view.tv_title_7
    }
}