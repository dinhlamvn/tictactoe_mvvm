package android.leo.tic_tac_toemvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.leo.tic_tac_toemvvm.databinding.ActivityGameBinding;
import android.leo.tic_tac_toemvvm.model.game.Player;
import android.leo.tic_tac_toemvvm.viewmodel.game.GameViewModel;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private static final String NO_WINNER = "No One";

    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    public void initDataBinding() {
        ActivityGameBinding activityGameBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_game);
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.init("P1", "P2");
        activityGameBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }

    public void setUpOnGameEndListener() {
        gameViewModel.getWinner().observe(this, new Observer<Player>() {
            @Override
            public void onChanged(@Nullable Player player) {
                onGameWinnerChange(player);
            }
        });
    }

    @VisibleForTesting
    public void onGameWinnerChange(Player winner) {
        String winnerName = (winner != null &&
                (winner.name != null && !winner.name.isEmpty())) ? winner.name : NO_WINNER;
        Toast.makeText(this, "Winner is " + winnerName, Toast.LENGTH_SHORT).show();
    }
}
