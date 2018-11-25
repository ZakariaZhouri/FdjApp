package organize.fdjapplication.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.player_recycler.view.*
import organize.fdjapplication.R
import organize.fdjapplication.presenters.viewModel.PlayerViewModel

class PlayerRecyclerAdapter(val listPlayerViewModel: List<PlayerViewModel>?)
    : RecyclerView.Adapter<PlayerRecyclerAdapter.PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent?.getContext()).inflate(R.layout.player_recycler, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        listPlayerViewModel?.let {
            return it.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        listPlayerViewModel?.let { list ->
            holder.bindPlayerInformation(list[position])
        }
    }


    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bindPlayerInformation(playerViewModel: PlayerViewModel) {
            itemView.player_name.text = playerViewModel.playerName
            itemView.player_position.text = playerViewModel.position
            itemView.player_born_date.text = playerViewModel.bornDate
            itemView.player_price.text = playerViewModel.transfertPrice
            playerViewModel.playerImage?.let {
                Glide.with(itemView.context).load(it).into(itemView.player_image)
            }
        }
    }

}