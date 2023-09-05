package dz_4.Interfaces;

import dz_4.Models.Carrier;

public interface ICarrierRepo {
    Carrier read(int id);
}