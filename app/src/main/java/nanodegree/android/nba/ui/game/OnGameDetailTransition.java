package nanodegree.android.nba.ui.game;

import android.view.View;

import nanodegree.android.nba.persistence.entity.GameAgg;

public interface OnGameDetailTransition {
    void transitionToGameDetail(GameAgg gameAgg, View homeView, View awayView);
}
