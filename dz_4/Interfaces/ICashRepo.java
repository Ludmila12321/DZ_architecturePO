package dz_4.Interfaces;

public interface ICashRepo {
    boolean transaction(int payment, long cardFrom, long cardTo);
}