package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.booths.interfaces.Booth;

import java.util.Collection;

public class BoothRepositoryImpl<T> implements BoothRepository<Booth>{
    @Override
    public Booth getByNumber(int number) {
        return null;
    }

    @Override
    public Collection<Booth> getAll() {
        return null;
    }

    @Override
    public void add(Booth booth) {

    }
}
