package com.morostami.mvpsample.data.local;
import com.morostami.mvpsample.domain.Coin;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Observable;

public class CryptoLocalDataSource implements CoinsDao {
    private CoinsDao coinsDao;

    @Inject
    public CryptoLocalDataSource(CoinsDao coinsdao) {
        this.coinsDao = coinsdao;
    }

    @Override
    public Completable insetCoin(Coin coin) {
        return coinsDao.insetCoin(coin);
    }

    @Override
    public Completable insertCoins(List<Coin> coins) {
        return coinsDao.insertCoins(coins);
    }

    @Override
    public Observable<List<Coin>> getAllCoins() {
        return coinsDao.getAllCoins();
    }

    @Override
    public void deleteCoin(Coin coin) {
        coinsDao.deleteCoin(coin);
    }

    @Override
    public void deleteCoins(List<Coin> coins) {
        coinsDao.deleteCoins(coins);
    }

    @Override
    public void deleteAllCoins() {
        coinsDao.deleteAllCoins();
    }
}
