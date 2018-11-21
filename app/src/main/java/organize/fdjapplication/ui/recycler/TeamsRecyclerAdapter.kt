package organize.fdjapplication.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.teams_recycler.view.*
import organize.fdjapplication.R
import organize.fdjapplication.presenters.listener.MainRecyclerListener
import organize.fdjapplication.presenters.viewModel.TeamViewModel


class TeamsRecyclerAdapter(val teamViewModelList: List<TeamViewModel>?, val listener: MainRecyclerListener)
    : RecyclerView.Adapter<TeamsRecyclerAdapter.TeamsViewHoled>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TeamsViewHoled {
        val view = LayoutInflater.from(parent?.getContext()).inflate(R.layout.teams_recycler, parent, false)
        return TeamsViewHoled(view)
    }

    override fun getItemCount(): Int {
        teamViewModelList?.let { list ->
            return list.size
        }
        return 0
    }

    override fun onBindViewHolder(holder: TeamsViewHoled, position: Int) {
        teamViewModelList?.let { list ->
            holder.bindTeamName(list[position].teamName)
        }

    }

    inner class TeamsViewHoled(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTeamName(teamName: String) {
            itemView.team_name.text = teamName
            itemView.setOnClickListener { view ->
                listener.onItemClicked(teamViewModelList?.get(adapterPosition)?.teamName)
            }
        }
    }
}